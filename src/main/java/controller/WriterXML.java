package controller;

import model.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class WriterXML {
    private File file;
    private Document document;
    private List<Client> clients;

    public WriterXML(File file, List<Client> clients) {
        this.file = file;
        this.clients = clients;
    }

    public WriterXML(List<Client> clients) {
        this.clients = clients;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void write() throws ParserConfigurationException, TransformerException{
            if (file != null && clients != null) {
                document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element elementList = document.createElement("listClient");
                for (Client client : clients) {
                    Element elementClient = document.createElement("client");
                    Element elementSurname = document.createElement("surname");
                    elementSurname.setTextContent(client.clientsFIO.getSurname());
                    elementClient.appendChild(elementSurname);
                    Element elementName = document.createElement("name");
                    elementName.setTextContent(client.clientsFIO.getName());
                    elementClient.appendChild(elementName);
                    Element elementPatronymic = document.createElement("patronymic");
                    elementPatronymic.setTextContent(client.clientsFIO.getPatronymic());
                    elementClient.appendChild(elementPatronymic);
                    Element elementAccountNumber = document.createElement("account_number");
                    elementAccountNumber.setTextContent(client.number.getAccountNumber());
                    elementClient.appendChild(elementAccountNumber);
                    Element elementPlaceOfResidence = document.createElement("place_of_residence");
                    elementClient.appendChild(elementPlaceOfResidence);
                    Element elementCountry = document.createElement("country");
                    elementPlaceOfResidence.appendChild(elementCountry);
                    elementCountry.setTextContent(client.placeOfResidence.getCountry());
                    Element elementCity = document.createElement("city");
                    elementPlaceOfResidence.appendChild(elementCity);
                    elementCity.setTextContent(client.placeOfResidence.getCity());
                    Element elementStreet = document.createElement("street");
                    elementPlaceOfResidence.appendChild(elementStreet);
                    elementStreet.setTextContent(client.placeOfResidence.getStreet());
                    Element elementHouse = document.createElement("house");
                    elementPlaceOfResidence.appendChild(elementHouse);
                    elementHouse.setTextContent(client.placeOfResidence.getHouse());
                    Element elementFlat = document.createElement("flat");
                    elementPlaceOfResidence.appendChild(elementFlat);
                    elementFlat.setTextContent(client.placeOfResidence.getFlat());
                    Element elementPhoneNumber = document.createElement("phone");
                    Element elementMobilePhoneNumber = document.createElement("mobile_phone");
                    elementMobilePhoneNumber.setTextContent(client.number.getMobilePhoneNumber());
                    elementPhoneNumber.appendChild(elementMobilePhoneNumber);
                    Element elementCityPhoneNumber = document.createElement("city_phone");
                    elementCityPhoneNumber.setTextContent(client.number.getCityPhoneNumber());
                    elementPhoneNumber.appendChild(elementCityPhoneNumber);
                    elementClient.appendChild(elementPhoneNumber);
                    elementList.appendChild(elementClient);
                }
                document.appendChild(elementList);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);
            }
    }
}
