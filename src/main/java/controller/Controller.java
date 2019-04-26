package controller;
import model.Client;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Client allRecords = new Client();
    private WriterXML writerXML;
    private SAXReader saxReader;
    private int label;

    public Controller(){}

    public void add(Client record){
        allRecords.addRecord(record);
    }

    public void set(List<Client>records){
        for(Client record: records){
            add(record);
        }
    }

    public void setGenerate(boolean b){
        boolean generate = b;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public void openFile(File file) {
        if (saxReader == null) {
            saxReader = new SAXReader();
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, saxReader);
            set(saxReader.getRecords());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public boolean saveFile(File file){
        if (writerXML == null) {
            writerXML = new WriterXML(allRecords.getRecords());
        }
        writerXML.setFile(file);
        try {
            writerXML.write();
            return true;
        } catch (TransformerException | ParserConfigurationException e) {
            return false;
        }
    }

    public List<Client> findClientBySurnameAndPhoneNumber(String surname, String phoneNumber){
        List<Client> clients = new ArrayList<>();
        for (Client record : allRecords.getRecords()){
            if (record.clientsFIO.getSurname().equals(surname)
                    || (record.number.getMobilePhoneNumber().equals(phoneNumber) ||
                    record.number.getCityPhoneNumber().equals(phoneNumber))){
                clients.add(record);
            }
        }
        return clients;
    }

    public List<Client> findClientByAccountNumberAndPlaceOfResidence(String accountNumber, String placeOfResidence){
        List<Client> clients = new ArrayList<>();
        for (Client record : allRecords.getRecords()){
            if (record.number.getAccountNumber().equals(accountNumber) ||
                    record.placeOfResidence.toString().equals(placeOfResidence)){
                clients.add(record);
            }
        }
        return clients;
    }

    public List<Client> findClientByFIOAndPhoneNumber(String FIO, String phoneNumber){
        List<Client> clients = new ArrayList<>();

        for (Client record : allRecords.getRecords()){
            if ((record.number.getMobilePhoneNumber().contains(phoneNumber) ||
                    record.number.getCityPhoneNumber().contains(phoneNumber)) &&
                    record.clientsFIO.toString().contains(FIO)) {
                clients.add(record);
            }
        }
        return clients;
    }

    public Client getAllRecords() {
        return allRecords;
    }

}
