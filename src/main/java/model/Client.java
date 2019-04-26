package model;
import java.util.ArrayList;
import java.util.List;


public class Client {
    private List<Client> records = new ArrayList<>();
    public ClientsFIO clientsFIO;
    public PlaceOfResidence placeOfResidence;
    public Number number;

    public Client(){
        clientsFIO = new ClientsFIO();
        placeOfResidence = new PlaceOfResidence();
        number = new model.Number();
    }

    public Client(ClientsFIO clientsFIO, PlaceOfResidence placeOfResidence, Number number){
        this.clientsFIO = clientsFIO;
        this.placeOfResidence = placeOfResidence;
        this.number = number;
    }

    public List<Client> getRecords() {
//        return records;
        return new ArrayList<>(records);
    }

    public void addRecord(Client record) {
        records.add(record);
    }

    public void deleteRecord(Client record){
        records.remove(record);
    }

}
