package view;
import controller.Controller;
import model.Client;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;
import widgets.FieldsClean;
import widgets.TableComposite;
import widgets.WidgetComposite;

class DeleteDisplay {
    private Shell shell;
    private Controller controller;
    private WidgetComposite widgetComposite;
    private FieldsClean fieldsClean = new FieldsClean();

    DeleteDisplay(Display display, TableComposite tableComposite, Controller controller) {
        shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
        this.controller = controller;
        Color gray = display.getSystemColor(SWT.COLOR_INFO_BACKGROUND);
        shell.setBackground(gray);
        shell.setText("Delete window");
        shell.setSize(1200, 400);
        widgetComposite = new WidgetComposite(shell);
        widgetComposite.fillFields();
        deleteClient(tableComposite);
        shell.open();
    }

   private void deleteClient(final TableComposite tableComposite) {
        Button delete1 = new Button(shell, SWT.PUSH);
        delete1.setText("Delete");
        delete1.setBounds(10, 145, 180, 30);

        Button delete2 = new Button(shell, SWT.PUSH);
        delete2.setText("Delete");
        delete2.setBounds(450, 145, 180, 30);

        Button delete3 = new Button(shell, SWT.PUSH);
        delete3.setText("Delete");
        delete3.setBounds(900, 145, 180, 30);

        delete1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (controller.findClientByAccountNumberAndPlaceOfResidence(
                        widgetComposite.getAccountNumber().getText(),
                        widgetComposite.getPlaceOfResidence().getText()).size() == 0) {
                    MessageBox message = new MessageBox(shell);
                    message.setText("Not found");
                    message.setMessage("Client is not found!");
                    message.open();
                } else {
                    for (Client client : controller.findClientByAccountNumberAndPlaceOfResidence
                            (widgetComposite.getAccountNumber().getText(),
                                    widgetComposite.getPlaceOfResidence().getText())) {
                        tableComposite.draw(controller.getAllRecords().getRecords().indexOf(client),
                                controller.getAllRecords().getRecords().indexOf(client) + 1, controller);
                        controller.getAllRecords().deleteRecord(client);
                    }
                    fieldsClean.clean(widgetComposite.getPlaceOfResidence(), widgetComposite.getAccountNumber());
                    afterDeleting(tableComposite);
                }
            }
        });

        delete2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (controller.findClientBySurnameAndPhoneNumber(widgetComposite.getSurname().getText(),
                        widgetComposite.getTypeOfPhoneNumber().getText()).size() == 0) {
                    MessageBox message = new MessageBox(shell);
                    message.setText("Not found");
                    message.setMessage("Client is not found!");
                    message.open();
                } else {
                    for (Client client : controller.findClientBySurnameAndPhoneNumber(
                            widgetComposite.getSurname().getText(), widgetComposite.getTypeOfPhoneNumber().getText())){
                        controller.getAllRecords().deleteRecord(client);
                    }
                    fieldsClean.clean(widgetComposite.getSurname(), widgetComposite.getTypeOfPhoneNumber());
                    afterDeleting(tableComposite);
                }
            }
        });

        delete3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (controller.findClientByFIOAndPhoneNumber(
                        widgetComposite.getFio().getText(),
                        widgetComposite.getTypeOfPhone1Number().getText()).size() == 0) {
                    MessageBox message = new MessageBox(shell);
                    message.setText("Not found");
                    message.setMessage("Client is not found!");
                    message.open();
                } else {
                    for (Client client : controller.findClientByFIOAndPhoneNumber(
                            widgetComposite.getFio().getText(), widgetComposite.getTypeOfPhone1Number().getText())) {
                        tableComposite.draw(controller.getAllRecords().getRecords().indexOf(client),
                                controller.getAllRecords().getRecords().indexOf(client) + 1, controller);
                        controller.getAllRecords().deleteRecord(client);
                    }
                    fieldsClean.clean(widgetComposite.getFio(), widgetComposite.getTypeOfPhone1Number());
                    afterDeleting(tableComposite);
                }
            }
        });
    }

    private void afterDeleting(TableComposite tableComposite) {
        tableComposite.clear();
        tableComposite.draw(0, controller.getAllRecords().getRecords().size(),
                controller.getAllRecords().getRecords().size(), controller.getLabel(), controller);
        MessageBox message = new MessageBox(shell);
        message.setMessage("The record is successfully deleted!");
        message.open();
        shell.close();
    }
}


