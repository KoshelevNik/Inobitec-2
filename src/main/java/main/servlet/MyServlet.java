package main.servlet;

import main.model.MedicineService;
import main.model.Order;
import main.model.Patient;
import main.service.MedicineServiceService;
import main.service.OrderService;
import main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;

@WebServlet("/servlet/*")
public class MyServlet extends HttpServlet {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineServiceService medicineServiceService;

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
                objectToXMLElement(orderService.readOrderById(Integer.parseInt(pathInfo.substring(7))), element);
            } else if (pathInfo.startsWith("/order")) {
                Element ordersElement = element.getOwnerDocument().createElement("orders");
                for (Order order : orderService.readAllOrders()) {
                    objectToXMLElement(order, ordersElement);
                }
                element.appendChild(ordersElement);
            } else if (pathInfo.matches("/patient/\\d+")) {
                objectToXMLElement(patientService.readPatientById(Integer.parseInt(pathInfo.substring(9))), element);
            } else if (pathInfo.startsWith("/patient")) {
                Element patientsElement = element.getOwnerDocument().createElement("patients");
                for (Patient patient : patientService.readAllPatients()) {
                    objectToXMLElement(patient, patientsElement);
                }
                element.appendChild(patientsElement);
            } else if (pathInfo.matches("/medicineService/\\d+")) {
                objectToXMLElement(medicineServiceService.readMedicineServiceById(Integer.parseInt(pathInfo.substring(17))), element);
            } else if (pathInfo.startsWith("/medicineService")) {
                Element medicineServicesElement = element.getOwnerDocument().createElement("medicineServices");
                for (MedicineService medicineService : medicineServiceService.readAllMedicineServices()) {
                    objectToXMLElement(medicineService, medicineServicesElement);
                }
                element.appendChild(medicineServicesElement);
            }

            document.appendChild(element);
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, new StreamResult(writer));

            response.getWriter().println(writer.getBuffer().toString());
        } catch (Exception e) {
            response.getWriter().println("Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

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
}
