package view;
import controller.Controller;
import model.ClientsFIO;
import model.Client;
import model.PlaceOfResidence;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;
import widgets.FieldsClean;
import widgets.TableComposite;

class AddDisplay{
    private Display display;
    private Shell shell;
    private Client allInfo = new Client();
    private Controller controller;
    private FieldsClean fieldsClean = new FieldsClean();

   AddDisplay(Display display, Controller controller, TableComposite tableComposite) {
        this.display = display;
        this.controller = controller;
        shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
        Color gray = display.getSystemColor(SWT.COLOR_LINK_FOREGROUND);
        shell.setBackground(gray);
        shell.setSize(550, 600);
        shell.setText("To add the new record");
        initAddDisplay(tableComposite);
        shell.open();
    }

    private void initAddDisplay(final TableComposite tableComposite){
        final Text surname = new Text(shell, SWT.CHECK);
        surname.setBounds(10, 40, 180, 30);
        Label surnameText = new Label(shell, SWT.NONE);
        surnameText.setText("Surname");
        surnameText.setBounds(200, 40, 200, 20);

        final Text name = new Text(shell, SWT.CHECK);
        name.setBounds(10, 80, 180, 30);
        Label nameText = new Label(shell, SWT.NONE);
        nameText.setText("Name");
        nameText.setBounds(200, 80, 200, 20);

        final Text patronymic = new Text(shell, SWT.CHECK);
        patronymic.setBounds(10, 120, 180, 30);
        Label patronymicText = new Label(shell, SWT.NONE);
        patronymicText.setText("Patronymic");
        patronymicText.setBounds(200, 120, 200, 20);

        final Text accountNumber = new Text(shell, SWT.CHECK);
        accountNumber.setBounds(10, 160, 180, 30);
        Label accountNumberText = new Label(shell, SWT.NONE);
        accountNumberText.setText("Account number");
        accountNumberText.setBounds(200, 160, 200, 20);

        final Text country = new Text(shell, SWT.CHECK);
        country.setBounds(10, 200, 180, 30);
        Label countryText = new Label(shell, SWT.NONE);
        countryText.setText("Country");
        countryText.setBounds(200, 200, 200, 20);

        final Text city = new Text(shell, SWT.CHECK);
        city.setBounds(10, 240, 180, 30);
        Label cityText = new Label(shell, SWT.NONE);
        cityText.setText("City");
        cityText.setBounds(200, 240, 200, 20);

        final Text street = new Text(shell, SWT.CHECK);
        street.setBounds(10, 280, 180, 30);
        final Label streetText = new Label(shell, SWT.NONE);
        streetText.setText("Street");
        streetText.setBounds(200, 280, 200, 20);

        final Text house = new Text(shell, SWT.CHECK);
        house.setBounds(10, 320, 180, 30);
        Label houseText = new Label(shell, SWT.NONE);
        houseText.setText("House");
        houseText.setBounds(200, 320, 200, 20);

        final Text flat = new Text(shell, SWT.CHECK);
        flat.setBounds(10, 360, 180, 30);
        Label flatText = new Label(shell, SWT.NONE);
        flatText.setText("Flat");
        flatText.setBounds(200, 360, 200, 20);

        final Text mobilePhone = new Text(shell, SWT.CHECK);
        mobilePhone.setBounds(10, 400, 180, 30);
        Label mobilePhoneText = new Label(shell, SWT.NONE);
        mobilePhoneText.setText("Mobile phone");
        mobilePhoneText.setBounds(200, 400, 200, 20);

        final Text cityPhone = new Text(shell, SWT.CHECK);
        cityPhone.setBounds(10, 440, 180, 30);
        Label cityPhoneText = new Label(shell, SWT.NONE);
        cityPhoneText.setText("City phone");
        cityPhoneText.setBounds(200, 440, 200, 20);

        Button addClient = new Button(shell, SWT.PUSH);
        addClient.setBounds(90, 480, 100, 30);
        addClient.setText("Add information");

        Button buttonAbort = new Button(shell, SWT.PUSH);
        buttonAbort.setBounds(200,480,100,30);
        buttonAbort.setText("Cancel");

        addClient.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Client client = new Client( new ClientsFIO(surname.getText(), name.getText(), patronymic.getText()),
                        new PlaceOfResidence(country.getText(), city.getText(), street.getText(),
                                house.getText(), flat.getText()),
                        new model.Number(accountNumber.getText(), mobilePhone.getText(),
                        cityPhone.getText()));
                    if (client.clientsFIO.getSurname().isEmpty() || client.clientsFIO.getName().isEmpty() ||
                        client.clientsFIO.getPatronymic().isEmpty()|| client.number.getCityPhoneNumber().isEmpty() ||
                        client.number.getMobilePhoneNumber().isEmpty() || client.number.getAccountNumber().isEmpty()||
                        client.placeOfResidence.getCountry().isEmpty() || client.placeOfResidence.getCity().isEmpty() ||
                        client.placeOfResidence.getStreet().isEmpty() || client.placeOfResidence.getHouse().isEmpty()||
                        client.placeOfResidence.getFlat().isEmpty()) {
                    MessageBox WAR = new MessageBox(shell);
                    WAR.setMessage("Some fields are empty!");
                    WAR.open();
                    }
                    else {

                        controller.add(client);
                        allInfo.addRecord(client);
                        fieldsClean.clean(surname, name, patronymic, country, city, street,
                            house, flat, accountNumber, mobilePhone, cityPhone);
                    MessageBox message = new MessageBox(shell);
                    message.setMessage("The record is successfully added!");
                    message.open();
                    tableComposite.clear();
                    tableComposite.draw(0, controller.getAllRecords().getRecords().size(),
                            controller.getAllRecords().getRecords().size(), controller.getLabel(), controller);
                    shell.close();
                }
            }
        });

        buttonAbort.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        shell.pack();
    }
}
