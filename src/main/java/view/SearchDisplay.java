package view;
import controller.Controller;
import model.Client;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import widgets.FieldsClean;
import widgets.TableComposite;
import widgets.WidgetComposite;

class SearchDisplay {
    private Shell shell;
    private Controller controller;
    private TableComposite tableComposite;
    private WidgetComposite widgetComposite;
    private FieldsClean fieldsClean = new FieldsClean();


    SearchDisplay(Display display, Controller controller) {
        shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
        shell.setText("Search window");
        shell.setSize(1200, 1000);
        tableComposite = new TableComposite(shell, SWT.NONE);
        this.controller = controller;
        tableComposite.initTable(controller);
        tableComposite.setBounds(50, 200, 992, 600);
        widgetComposite = new WidgetComposite(shell);
        widgetComposite.fillFields();
        findClient();
        shell.open();
    }

   private void findClient() {
        Button find1 = new Button(shell, SWT.PUSH);
        find1.setText("Search");
        find1.setBounds(10, 145, 190, 30);

        Button find2 = new Button(shell, SWT.PUSH);
        find2.setText("Search");
        find2.setBounds(450, 145, 190, 30);

        Button find3 = new Button(shell, SWT.PUSH);
        find3.setText("Search");
        find3.setBounds(900, 145, 190, 30);

        find1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                for (Client client : controller.findClientByAccountNumberAndPlaceOfResidence(
                        widgetComposite.getAccountNumber().getText(),
                        widgetComposite.getPlaceOfResidence().getText())) {
                    tableComposite.draw(controller.getAllRecords().getRecords().indexOf(client),
                            controller.getAllRecords().getRecords().indexOf(client) + 1, controller);
                }
                fieldsClean.clean(widgetComposite.getPlaceOfResidence(), widgetComposite.getAccountNumber());
            }
        });

        find2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                for (Client client : controller.findClientBySurnameAndPhoneNumber(widgetComposite.getSurname().getText(),
                        widgetComposite.getTypeOfPhoneNumber().getText())) {
                    tableComposite.draw(controller.getAllRecords().getRecords().indexOf(client),
                            controller.getAllRecords().getRecords().indexOf(client) + 1, controller);
                }
                fieldsClean.clean(widgetComposite.getSurname(), widgetComposite.getTypeOfPhoneNumber());
            }
        });

       find3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                for (Client client : controller.findClientByFIOAndPhoneNumber(
                        widgetComposite.getFio().getText(), widgetComposite.getTypeOfPhone1Number().getText())) {
                    tableComposite.draw(controller.getAllRecords().getRecords().indexOf(client),
                            controller.getAllRecords().getRecords().indexOf(client) + 1, controller);
                }
                fieldsClean.clean(widgetComposite.getFio(), widgetComposite.getTypeOfPhone1Number());
            }
        });
    }

    }

