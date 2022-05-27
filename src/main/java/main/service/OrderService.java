package main.service;

import main.cache.PatientCache;
import main.configuration.DataBaseFrameworkConfiguration;
import main.configuration.URLConfiguration;
import main.exception.OrderNotFoundException;
import main.exception.PatientNotFoundException;
import main.mapper.OrderMapper;
import main.model.Order;
import main.model.OrderItem;
import main.model.Patient;
import main.repository.OrderItemsRepository;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private DataBaseFrameworkConfiguration dataBaseFrameworkConfiguration;

    @Autowired
    private URLConfiguration urlConfiguration;

    @Autowired
    private PatientCache patientCache;

    public void createOrder(Order order) throws ResourceAccessException, PatientNotFoundException {
        patientNonNull(order.getPatient());
        Patient patientFromCache = patientCache.exist(order.getPatient());
        if (patientFromCache != null) {
            order.setPatientId(patientFromCache.getId());
            if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
                orderMapper.insertOrder(order);
            } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
                orderRepository.insert(order.getPatientId(), order.getDate());
                for (OrderItem oi : order.getOrderItems()) {
                    orderItemsRepository.insert(oi.getMedicineServiceId(), order.getId());
                }
            }
        } else {
            RestTemplate restTemplate = new RestTemplate();
            Patient patient = restTemplate.postForObject(urlConfiguration.getPatientURL(), order.getPatient(), Patient.class);

            patientNonNull(patient);

            order.setPatient(patient);
            order.setPatientId(Objects.requireNonNull(patient).getId());
            if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
                orderMapper.insertOrder(order);
            } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
                orderRepository.insert(order.getPatientId(), order.getDate());
                for (OrderItem oi : order.getOrderItems()) {
                    orderItemsRepository.insert(oi.getMedicineServiceId(), order.getId());
                }
            }
        }
    }

    public List<Order> readAllOrders() throws ResourceAccessException {
        List<Order> orders;
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            orders = orderMapper.selectAllOrders();
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            orders = orderRepository.findAll();
            for (Order o : orders) {
                o.setOrderItems(new ArrayList<>());
                for (OrderItem oi : orderItemsRepository.selectOrderItemsById(o.getId())) {
                    if (o.getId().equals(oi.getOrderId())) {
                        o.getOrderItems().add(oi);
                        break;
                    }
                }
            }
        } else {
            orders = new ArrayList<>();
        }
        if (!patientCache.isEmpty()) {
            loadPatientsFromCache(orders);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Patient[]> response = restTemplate.getForEntity(urlConfiguration.getPatientURL(), Patient[].class);
            loadPatientsToCache(response.getBody());
            loadPatientsFromCache(orders);
        }
        return orders;
    }

    public void deleteOrder(Integer id) {
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            orderMapper.deleteOrder(id);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            orderItemsRepository.deleteAllById(id);
            orderRepository.deleteById(id);
        }
    }

    public void updateOrder(Order order, Integer id) throws ResourceAccessException, PatientNotFoundException, HttpClientErrorException {
        order.setId(id);
        for (OrderItem item : order.getOrderItems()) {
            item.setOrderId(id);
        }
        patientNonNull(order.getPatient());
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            orderMapper.updateOrder(order);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            orderRepository.insert(order.getPatientId(), order.getDate());
            for (OrderItem oi : order.getOrderItems()) {
                orderItemsRepository.insert(oi.getMedicineServiceId(), oi.getOrderId());
            }
        }
        if (!patientCache.getPatientById(order.getPatientId()).equals(order.getPatient())) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(urlConfiguration.getPatientURL() + "/" + order.getPatientId(), order.getPatient(), Patient.class);
            patientCache.patientMap().put(order.getPatientId(), order.getPatient());
        }
    }

    public Order readOrderById(Integer id) throws ResourceAccessException, OrderNotFoundException, PatientNotFoundException {
        Order order;
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            order = orderMapper.selectOrderById(id);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            order = orderRepository.findById(id).get();
            order.setOrderItems(orderItemsRepository.selectOrderItemsById(id));
        } else {
            order = null;
        }

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (patientCache.containsPatientById(order.getPatientId())) {
            order.setPatient(patientCache.getPatientById(order.getPatientId()));
        } else {
            RestTemplate restTemplate = new RestTemplate();
            Patient patient = restTemplate.getForObject(urlConfiguration.getPatientURL() + "/" + order.getPatientId(), Patient.class);

            patientNonNull(patient);

            order.setPatient(patient);
            patientCache.patientMap().put(patient.getId(), patient);
        }
        return order;
    }

    private void loadPatientsFromCache(List<Order> orders) {
        for (Order order : orders) {
            order.setPatient(patientCache.getPatientById(order.getPatientId()));
        }
    }

    private void loadPatientsToCache(Patient[] patients) {
        for (Patient patient : Objects.requireNonNull(patients)) {
            patientCache.patientMap().put(patient.getId(), patient);
        }
    }

    private void patientNonNull(Patient patient) throws PatientNotFoundException {
        if (patient == null) {
            throw new PatientNotFoundException();
        }
    }
}
