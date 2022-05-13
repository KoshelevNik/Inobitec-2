package main.service;

import main.cache.PatientCache;
import main.configuration.URLConfiguration;
import main.exception.OrderNotFoundException;
import main.exception.PatientNotFoundException;
import main.model.Order;
import main.model.OrderItem;
import main.model.Patient;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private URLConfiguration urlConfiguration;

    @Autowired
    private PatientCache patientCache;

    public void createOrder(Order order) throws ResourceAccessException, PatientNotFoundException {
        patientNonNull(order.getPatient());
        Patient patientFromCache = patientCache.exist(order.getPatient());
        if (patientFromCache != null) {
            order.setPatientId(patientFromCache.getId());
            orderRepository.insertOrder(order);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            Patient patient = restTemplate.postForObject(urlConfiguration.getPatientURL(), order.getPatient(), Patient.class);

            patientNonNull(patient);

            order.setPatient(patient);
            order.setPatientId(Objects.requireNonNull(patient).getId());
            orderRepository.insertOrder(order);
        }
    }

    public List<Order> readAllOrders() throws ResourceAccessException {
        List<Order> orders = orderRepository.selectAllOrders();
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
        orderRepository.deleteOrder(id);
    }

    public void updateOrder(Order order, Integer id) throws ResourceAccessException, PatientNotFoundException, HttpClientErrorException {
        order.setId(id);
        for (OrderItem item : order.getOrderItems()) {
            item.setOrderId(id);
        }
        patientNonNull(order.getPatient());
        orderRepository.updateOrder(order);
        if (!patientCache.getPatientById(order.getPatientId()).equals(order.getPatient())) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(urlConfiguration.getPatientURL() + "/" + order.getPatientId(), order.getPatient(), Patient.class);
            patientCache.patientMap().put(order.getPatientId(), order.getPatient());
        }
    }

    public Order readOrderById(Integer id) throws ResourceAccessException, OrderNotFoundException, PatientNotFoundException {
        Order order = orderRepository.selectOrderById(id);

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
