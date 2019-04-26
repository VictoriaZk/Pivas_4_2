package controller;
import model.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class SAXReader extends DefaultHandler {
    private static final String LIST_CLIENT = "listClient";
    private static final String CLIENT = "client";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String PATRONYMIC = "patronymic";
    private static final String ACCOUNT_NUMBER = "account_number";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String FLAT = "flat";
    private static final String MOBILE_PHONE_NUMBER = "mobile_phone";
    private static final String CITY_PHONE_NUMBER = "city_phone";
    private List<Client> clients;
    private Client client ;
    private String currentElement;

    public List<Client> getRecords() {
        return clients;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        switch (currentElement){
            case LIST_CLIENT: {
                clients = new ArrayList<>();
            }break;
            case CLIENT: {
                client = new Client();
            }break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        if(text.contains("<") || currentElement == null){
            return;
        }
        switch (currentElement) {
            case SURNAME:{
                client.clientsFIO.setSurname(text);
            }break;

            case NAME:{
                client.clientsFIO.setName(text);
            }break;

            case PATRONYMIC:{
                client.clientsFIO.setPatronymic(text);
            }break;

            case ACCOUNT_NUMBER:{
                client.number.setAccountNumber(text);
            }break;

            case COUNTRY:{
                client.placeOfResidence.setCountry(text);
            }break;
            case CITY:{
                client.placeOfResidence.setCity(text);
            }break;
            case STREET:{
                client.placeOfResidence.setStreet(text);
            }break;
            case HOUSE:{
                client.placeOfResidence.setHouse(text);
            }break;
            case FLAT:{
                client.placeOfResidence.setFlat(text);
            }break;
            case MOBILE_PHONE_NUMBER:{
                client.number.setMobilePhoneNumber(text);
            }break;

            case CITY_PHONE_NUMBER:{
                client.number.setCityPhoneNumber(text);
            }break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        switch (qName) {
            case CLIENT: {
                clients.add(client);
            }break;
            case SURNAME: {
                currentElement = null;
            }break;
            case NAME: {
                currentElement = null;
            }break;
            case PATRONYMIC: {
                currentElement = null;
            }break;
            case ACCOUNT_NUMBER: {
                currentElement = null;
            }break;
            case COUNTRY: {
                currentElement = null;
            }break;
            case CITY: {
                currentElement = null;
            }break;
            case STREET: {
                currentElement = null;
            }break;
            case HOUSE: {
                currentElement = null;
            }break;
            case FLAT: {
                currentElement = null;
            }break;
            case MOBILE_PHONE_NUMBER: {
                currentElement = null;
            }break;
            case CITY_PHONE_NUMBER: {
                currentElement = null;
            }break;
        }
    }
}
