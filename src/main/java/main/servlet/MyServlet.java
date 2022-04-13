package main.servlet;

import main.model.MedicineService;
import main.model.Order;
import main.model.Patient;
import main.service.MedicineServiceService;
import main.service.OrderService;
import main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.*;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.rmi.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/servlet/*")
public class MyServlet extends HttpServlet {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineServiceService medicineServiceService;

    public static int ORDER_ID_START = 7;
    public static int PATIENT_ID_START = 9;
    public static int MEDICINE_SERVICE_ID_START = 17;

    public static int OBJECT_ITEM_POSITION = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("xml");
        response.setCharacterEncoding("utf8");

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            Element element = document.createElement("message");

            String pathInfo = request.getPathInfo();
            if (pathInfo.matches("/order/\\d+")) {
                objectToXMLElement(orderService.readOrderById(Integer.parseInt(pathInfo.substring(ORDER_ID_START))), element);
            } else if (pathInfo.matches("/order")) {
                Element ordersElement = element.getOwnerDocument().createElement("orders");
                for (Order order : orderService.readAllOrders()) {
                    objectToXMLElement(order, ordersElement);
                }
                element.appendChild(ordersElement);
            } else if (pathInfo.matches("/patient/\\d+")) {
                objectToXMLElement(patientService.readPatientById(Integer.parseInt(pathInfo.substring(PATIENT_ID_START))), element);
            } else if (pathInfo.matches("/patient")) {
                Element patientsElement = element.getOwnerDocument().createElement("patients");
                for (Patient patient : patientService.readAllPatients()) {
                    objectToXMLElement(patient, patientsElement);
                }
                element.appendChild(patientsElement);
            } else if (pathInfo.matches("/medicineService/\\d+")) {
                objectToXMLElement(medicineServiceService.readMedicineServiceById(Integer.parseInt(pathInfo.substring(MEDICINE_SERVICE_ID_START))), element);
            } else if (pathInfo.matches("/medicineService")) {
                Element medicineServicesElement = element.getOwnerDocument().createElement("medicineServices");
                for (MedicineService medicineService : medicineServiceService.readAllMedicineServices()) {
                    objectToXMLElement(medicineService, medicineServicesElement);
                }
                element.appendChild(medicineServicesElement);
            } else {
                throw new ServletException();
            }

            document.appendChild(element);
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, new StreamResult(writer));

            response.getWriter().println(writer.getBuffer().toString());
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Not found");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Server error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ServletInputStream reader = request.getInputStream();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(reader);
            Node rootNode = document.getFirstChild();
            String pathInfo = request.getPathInfo();
            if (pathInfo.matches("/order")) {
                Order order = new Order();
                XMLToObject(order, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                orderService.createOrder(order);
                reader.close();
            } else if (pathInfo.matches("/patient")) {
                Patient patient = new Patient();
                XMLToObject(patient, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                patientService.createPatient(patient);
                reader.close();
            } else if (pathInfo.matches("/medicineService")) {
                MedicineService medicineService = new MedicineService();
                XMLToObject(medicineService, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                medicineServiceService.createMedicineService(medicineService);
                reader.close();
            } else {
                reader.close();
                throw new ServletException();
            }
        } catch (SAXParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Bad request");
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Not found");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Server error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ServletInputStream reader = request.getInputStream();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(reader);
            Node rootNode = document.getFirstChild();
            String pathInfo = request.getPathInfo();
            if (pathInfo.matches("/order/\\d+")) {
                Order order = new Order();
                XMLToObject(order, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                orderService.updateOrder(order, Integer.parseInt(pathInfo.substring(ORDER_ID_START)));
                reader.close();
            } else if (pathInfo.matches("/patient/\\d+")) {
                Patient patient = new Patient();
                XMLToObject(patient, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                patientService.updatePatient(patient, Integer.parseInt(pathInfo.substring(PATIENT_ID_START)));
                reader.close();
            } else if (pathInfo.matches("/medicineService/\\d+")) {
                MedicineService medicineService = new MedicineService();
                XMLToObject(medicineService, rootNode.getChildNodes().item(OBJECT_ITEM_POSITION));
                medicineServiceService.updateMedicineService(medicineService, Integer.parseInt(pathInfo.substring(MEDICINE_SERVICE_ID_START)));
                reader.close();
            } else {
                reader.close();
                throw new ServletException();
            }
        } catch (SAXParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Bad request");
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Not found");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Server error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo.matches("/order/\\d+")) {
                orderService.deleteOrder(Integer.parseInt(pathInfo.substring(ORDER_ID_START)));
            } else if (pathInfo.matches("/patient/\\d+")) {
                patientService.deletePatient(Integer.parseInt(pathInfo.substring(PATIENT_ID_START)));
            } else if (pathInfo.matches("/medicineService/\\d+")) {
                medicineServiceService.deleteMedicineService(Integer.parseInt(pathInfo.substring(MEDICINE_SERVICE_ID_START)));
            } else {
                throw new ServletException();
            }
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Not found");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Server error");
        }
    }

    private void objectToXMLElement(Object object, Element parentElement) throws IllegalAccessException {
        Element objectElement = parentElement.getOwnerDocument().createElement(object.getClass().getSimpleName());
        Field[] objectFields = object.getClass().getDeclaredFields();
        for (Field field : objectFields) {
            field.setAccessible(true);
            if (!field.getType().getPackageName().startsWith("java")) {
                objectToXMLElement(field.get(object), objectElement);
            } else if (field.getType().equals(List.class)) {
                Element childElement = objectElement.getOwnerDocument().createElement(field.getName());
                List<Object> objectList = (List<Object>) field.get(object);
                for (Object o : objectList) {
                    objectToXMLElement(o, childElement);
                }
                objectElement.appendChild(childElement);
            } else {
                Element childElement = objectElement.getOwnerDocument().createElement(field.getName());
                Text childElementValue = childElement.getOwnerDocument().createTextNode(field.get(object).toString());
                childElement.appendChild(childElementValue);
                objectElement.appendChild(childElement);
            }
        }
        parentElement.appendChild(objectElement);
    }

    private void XMLToObject(Object object, Node objectNode) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, ParseException {
        Field[] fields = object.getClass().getDeclaredFields();
        NodeList nodeList = objectNode.getChildNodes();
        for (Field field : fields) {
            field.setAccessible(true);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals(field.getName())) {
                    if (node.getChildNodes().getLength() > 1) {
                        if (field.getType().equals(List.class)) {
                            NodeList subNodeList = node.getChildNodes();
                            Class<?> clazz = getGenericClass(field.getGenericType().getTypeName());
                            List<Object> list = new ArrayList<>();
                            for (int j = 0; j < subNodeList.getLength(); j++) {
                                if (subNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    Object o = clazz.getDeclaredConstructor().newInstance();
                                    XMLToObject(o, subNodeList.item(j));
                                    list.add(o);
                                }
                            }
                            field.set(object, list);
                        } else {
                            Class<?> clazz = Class.forName(field.getGenericType().getTypeName());
                            Object o = clazz.getDeclaredConstructor().newInstance();
                            XMLToObject(o, node);
                            field.set(object, o);
                        }
                    } else if (node.getChildNodes().getLength() == 1) {
                        if (isShort(node.getFirstChild().getNodeValue()) && field.getType().equals(Short.class)) {
                            field.set(object, Short.valueOf(node.getFirstChild().getNodeValue()));
                        } else if (isDouble(node.getFirstChild().getNodeValue()) && field.getType().equals(Double.class)) {
                            field.set(object, Double.valueOf(node.getFirstChild().getNodeValue()));
                        } else if (isInteger(node.getFirstChild().getNodeValue()) && field.getType().equals(Integer.class)) {
                            field.set(object, Integer.valueOf(node.getFirstChild().getNodeValue()));
                        } else if (isDate(node.getFirstChild().getNodeValue()) && field.getType().equals(Date.class)) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                            field.set(object, simpleDateFormat.parse(node.getFirstChild().getNodeValue()));
                        } else {
                            field.set(object, node.getFirstChild().getNodeValue());
                        }
                    }
                    break;
                }
            }
        }
    }

    private Class<?> getGenericClass(String listTypeName) throws ClassNotFoundException {
        return Class.forName(listTypeName.substring(listTypeName.indexOf('<') + 1, listTypeName.indexOf('>')));
    }

    private boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean isShort(String string) {
        try {
            Short.valueOf(string);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean isDouble(String string) {
        try {
            Double.valueOf(string);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean isDate(String string) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            simpleDateFormat.parse(string);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
