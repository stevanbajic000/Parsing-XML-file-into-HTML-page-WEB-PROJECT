package user1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String first_name;
    public String last_name;
    public String address;
    public String city;
    public String email;
    public String password;
    public String country;


    public static List<User> parseXML() {
        List<User> list = new ArrayList<>();
        User user = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("C:\\Users\\User\\IdeaProjects\\untitled2\\Web project - XML and HTML\\src\\main\\xml\\users.xml"));
            String country = null;
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("country")) {
                        //Get the 'id' attribute from Employee element
                        Attribute idAttr = startElement.getAttributeByName(new QName("name"));
                        if (idAttr != null) {
                            country = idAttr.getValue();
                        }
                    } else if (startElement.getName().getLocalPart().equals("user")) {
                        user = new User();
                        user.setCountry(country);
                    }
                    //set the other varibles from xml elements
                    else if (startElement.getName().getLocalPart().equals("first_name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        user.setFirst_name(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equals("last_name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        user.setLast_name(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equals("address")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        user.setAddress(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equals("city")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        user.setCity(xmlEvent.asCharacters().getData());

                    } else if (startElement.getName().getLocalPart().equals("email")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        user.setEmail(xmlEvent.asCharacters().getData());
                    }
                }
                //if Employee end element is reached, add employee object to list
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("user")) {
                        list.add(user);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deledteUser(String emaill) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException, TransformerException, SAXException, ParserConfigurationException, TransformerException {

        DocumentBuilderFactory dbfact = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbfact.newDocumentBuilder();
        Document d = builder.parse("C:\\Users\\User\\IdeaProjects\\untitled2\\Web project - XML and HTML\\src\\main\\xml\\users.xml");
        XPath xp = XPathFactory.newInstance().newXPath();

        NodeList nl = (NodeList)xp.compile("//user[email = '"+emaill+"']").evaluate(d, XPathConstants.NODESET);
        for (int i = nl.getLength() - 1; i >= 0; i--)
        {
            nl.item(i).getParentNode().removeChild(nl.item(i));
        }
        DOMSource xmlDOC = new DOMSource(d);
        StreamResult result = new StreamResult(new FileOutputStream("C:\\Users\\User\\IdeaProjects\\untitled2\\Web project - XML and HTML\\src\\main\\xml\\users.xml"));
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.transform(xmlDOC, result);


    }


    public static boolean saveUser(User userss) {
        List<User> users = parseXML();
        boolean eCheck = true;
        for (User user : users) {
            if (userss.getEmail().equals(user.getEmail())) {
                eCheck = false;


            }
        }
        if (eCheck) {


            try {




                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document document = documentBuilder.parse("C:\\Users\\User\\IdeaProjects\\untitled2\\Web project - XML and HTML\\src\\main\\xml\\users.xml");

                document.getDocumentElement().normalize();

                Element root = document.getDocumentElement();

                Element user = document.createElement("user");

                Element first_name = document.createElement("first_name");
                Element last_name = document.createElement("last_name");
                Element adres = document.createElement("address");
                Element cityElement = document.createElement("city");
                Element emailElement = document.createElement("email");
                Element passwordElement = document.createElement("password");

                user.appendChild(first_name);

                user.appendChild(last_name);

                user.appendChild(adres);

                user.appendChild(cityElement);

                user.appendChild(emailElement);

                user.appendChild(passwordElement);


                String u = userss.country;
                switch (userss.getCountry()) {
                    case "United States":
                        NodeList continents = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents.getLength(); k++) {
                            Element continent = (Element) continents.item(k);
                            if (continent.getAttribute("name").equals("North America")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("United States")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;

                    case "Canada":
                        NodeList continents4 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents4.getLength(); k++) {
                            Element continent = (Element) continents4.item(k);
                            if (continent.getAttribute("name").equals("North America")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("Canada")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;

                    case "France":
                        NodeList continents2 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents2.getLength(); k++) {
                            Element continent = (Element) continents2.item(k);
                            if (continent.getAttribute("name").equals("Europe")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("France")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;


                    case "Germany":
                        NodeList continents3 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents3.getLength(); k++) {
                            Element continent = (Element) continents3.item(k);
                            if (continent.getAttribute("name").equals("Europe")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("Germany")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;
                    case "United Kingdom":
                        NodeList continents1 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents1.getLength(); k++) {
                            Element continent = (Element) continents1.item(k);
                            if (continent.getAttribute("name").equals("Europe")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("United Kingdom")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;


                    case "Japan":
                        NodeList continents5 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents5.getLength(); k++) {
                            Element continent = (Element) continents5.item(k);
                            if (continent.getAttribute("name").equals("Asia")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("Japan")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;

                    case "China":
                        NodeList continents6 = root.getElementsByTagName("continent");
                        for (int k = 0; k < continents6.getLength(); k++) {
                            Element continent = (Element) continents6.item(k);
                            if (continent.getAttribute("name").equals("Asia")) {
                                NodeList countries = continent.getElementsByTagName("country");
                                for (int i = 0; i < countries.getLength(); i++) {
                                    Element country = (Element) countries.item(i);
                                    if (country.getAttribute("name").equals("China")) {
                                        country.appendChild(user);
                                    }
                                }
                            }
                        }
                        break;


                }

                first_name.setTextContent(userss.getFirst_name());
                last_name.setTextContent(userss.getLast_name());
                adres.setTextContent(userss.getAddress());
                cityElement.setTextContent(userss.getCity());
                emailElement.setTextContent(userss.getEmail());
                passwordElement.setTextContent(userss.getPassword());

                DOMSource xmlDOC = new DOMSource(document);
                StreamResult result = new StreamResult(new FileOutputStream("C:\\Users\\User\\IdeaProjects\\untitled2\\Web project - XML and HTML\\src\\main\\xml\\users.xml"));
                TransformerFactory transFactory = TransformerFactory.newInstance();
                Transformer transformer = transFactory.newTransformer();
                transformer.transform(xmlDOC, result);


            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            return false;
        }
        return true;

    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}


/*
* try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            Reader reader = new FileReader("C:\\Users\\HP\\Desktop\\untitled1\\src\\main\\xml\\users.xml");

            XMLStreamReader xmlEventReader = xmlInputFactory.createXMLStreamReader(reader);


            while (xmlEventReader.hasNext()){

                switch (xmlEventReader.next()){

                    case XMLStreamReader.START_ELEMENT: {
                        // System.out.println(xmlEventReader.getName());
                        String element= xmlEventReader.getName().toString();

                        switch (element){

                            case "continent":
                                for (int i = 0; i <xmlEventReader.getAttributeCount(); i++) {
                                    String attname = xmlEventReader.getAttributeLocalName(i);
                                    String attvalue = xmlEventReader.getAttributeValue(i);
                                    System.out.println(attname +" = "+attvalue);
                                    System.out.println();
                                }
                                break;
                            case "country":
                               // System.out.println("Title: "+xmlEventReader.getElementText());
                                for (int i = 0; i <xmlEventReader.getAttributeCount(); i++) {
                                    String attname = xmlEventReader.getAttributeLocalName(i);
                                    String attvalue = xmlEventReader.getAttributeValue(i);
                                    System.out.println(attname +" = "+attvalue);
                                    System.out.println();
                                }
                                break;

                           case "first_name":
                                System.out.println("first_name: "+xmlEventReader.getElementText());

                                break;


                            case "last_name":
                                System.out.println("last_name: "+xmlEventReader.getElementText());

                                break;


                            case "address":
                                System.out.println("address: "+xmlEventReader.getElementText());

                                break;

                            case "city":
                                System.out.println("city: "+xmlEventReader.getElementText());

                                break;


                            case "email":
                                System.out.println("email: "+xmlEventReader.getElementText());

                                break;


                            case "password":
                                System.out.println("password: "+xmlEventReader.getElementText());
                                System.out.println();
                                break;
                        }

                    }
                    break;

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }*/
