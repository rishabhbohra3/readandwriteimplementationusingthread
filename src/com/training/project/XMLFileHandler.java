package com.training.project;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class XMLFileHandler implements MyFileHandler {


    @Override
    public Employee readData(int readCounter) {
        try {


            File xmlFile = new File("employee.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");

            Employee readValue = new Employee();
            Node nNode = nList.item(readCounter);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;

                readValue.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                readValue.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());

                String sDate = eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                Date date = new SimpleDateFormat("MM/dd/yyy").parse(sDate);
                readValue.setDateOfBirth(date);

                String sExperience = eElement.getElementsByTagName("experience").item(0).getTextContent();
                Double experience = Double.parseDouble(sExperience);

                readValue.setExperience(experience);

                return readValue;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeData(Employee writeVal) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            JAXBElement<Employee> jaxbElement = new JAXBElement<Employee>(new QName("", "employee"), Employee.class, writeVal);
            OutputStream xmlFile = new FileOutputStream("employee2.xml", true);
            jaxbMarshaller.marshal(jaxbElement, xmlFile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
