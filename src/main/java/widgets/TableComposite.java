package widgets;
import controller.Controller;
import model.Client;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class TableComposite extends Composite{
    private Table table = new Table(this, SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
    private Label countItems = new Label(this, SWT.NONE);
    private Label allItems = new Label(this, SWT.NONE);
    private Label currentPage = new Label(this, SWT.NONE);
    private Label allPages = new Label(this, SWT.NONE);
    private int currentPages = 1;
    private int count = 0;
    private int label = 0;

    public TableComposite(Composite composite, int index) {
        super(composite, index);
    }

    private void incrementCurrentPages(){
        currentPages++;
    }

    private void reduceCurrentPages(){
        currentPages--;
    }

    public void draw(int start, int end, int count, int label, Controller controller) {
        this.count = count;
        this.label = label;
        for (Client client : controller.getAllRecords().getRecords().subList(start, end)) {
            TableItem tableItem = new TableItem(table, SWT.PUSH);
            tableItem.setText(0, client.clientsFIO.toString());
            tableItem.setText(1, client.number.getAccountNumber());
            tableItem.setText(2, client.placeOfResidence.toString());
            tableItem.setText(3, client.number.getMobilePhoneNumber());
            tableItem.setText(4, client.number.getCityPhoneNumber());
        }
        countItems.setText(table.getItems().length + " items on the page");
        if (table.getItems().length == 0) {
            allPages.setText("There are " + 1 + " pages at all");
        } else {
            allPages.setText("There are " + controller.getAllRecords().getRecords().size() / table.getItems().length
                    + " pages at all");
        }
        allItems.setText(controller.getAllRecords().getRecords().size() + " items at all");
        if (table.getItems().length == 0) {
            currentPage.setText("current page is " + 1);
        } else {
            currentPage.setText("current page is " + currentPages);
        }
    }

    public void draw(int start, int end, Controller controller) {
        for (Client client : controller.getAllRecords().getRecords().subList(start, end)) {
            TableItem tableItem = new TableItem(table, SWT.PUSH);
            tableItem.setText(0, client.clientsFIO.toString());
            tableItem.setText(2, client.placeOfResidence.toString());
            tableItem.setText(1, client.number.getAccountNumber());
            tableItem.setText(3, client.number.getMobilePhoneNumber());
            tableItem.setText(4, client.number.getCityPhoneNumber());
        }
    }

    public void initTable(final Controller controller){
        allPages.setBounds(700, 500, 120, 30);
        allPages.setText("There are " + 1 + " pages");
        currentPage.setBounds(500, 500, 120, 30);
        allItems.setText(controller.getAllRecords().getRecords().size() + " items at all");
        allItems.setBounds(300, 500, 120, 30);
        int countItemOnThePage = 0;
        countItems.setText(countItemOnThePage + " items on the page");
        countItems.setBounds(100, 500, 120, 30);
        table.setBounds(50, 50, 892, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        Button nextPage = new Button(this, SWT.PUSH);
        nextPage.setText("next page");
        nextPage.setBounds(848, 360, 100, 30);

        Button prevPage = new Button(this, SWT.PUSH);
        prevPage.setText("previous page");
        prevPage.setBounds(728, 360, 100, 30);

        Button lastPage = new Button(this, SWT.PUSH);
        lastPage.setText("Last page");
        lastPage.setBounds(848, 450, 100, 30);

        Button firstPage = new Button(this, SWT.PUSH);
        firstPage.setText("first page");
        firstPage.setBounds(728, 450, 100, 30);

        final Text countPages = new Text(this, SWT.CHECK);
        countPages.setBounds(50, 400, 100, 30);
        Label countPagesText = new Label(this, SWT.NONE);
        countPagesText.setText("Input count of elements");
        countPagesText.setBounds(160, 408, 200, 30);

        TableColumn fioColumn = new TableColumn(table, SWT.LEFT);
        fioColumn.setText("Clients FIO");
        fioColumn.setResizable(true);
        fioColumn.setWidth(270);

        Button generate = new Button(this, SWT.PUSH);
        generate.setText("Generate");
        generate.setBounds(50, 450, 100, 30);

        TableColumn accountNumberColumn = new TableColumn(table, SWT.CENTER);
        accountNumberColumn.setText("Account Number");
        accountNumberColumn.setResizable(true);
        accountNumberColumn.setWidth(130);

        TableColumn placeOdResidenceColumn = new TableColumn(table, SWT.CENTER);
        placeOdResidenceColumn.setText("Place of residence");
        placeOdResidenceColumn.setResizable(true);
        placeOdResidenceColumn.setWidth(200);

        TableColumn mobilePhoneColumn = new TableColumn(table, SWT.RIGHT);
        mobilePhoneColumn.setText("Mobile phone number");
        mobilePhoneColumn.setResizable(true);
        mobilePhoneColumn.setWidth(161);

        TableColumn cityPhoneColumn = new TableColumn(table, SWT.RIGHT);
        cityPhoneColumn.setText("City phone number");
        cityPhoneColumn.setResizable(true);
        cityPhoneColumn.setWidth(130);

        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (currentPages < controller.getAllRecords().getRecords().size() / table.getItems().length
                && label < controller.getAllRecords().getRecords().size() - count ) {
                    incrementCurrentPages();
                    table.removeAll();
                    draw(label, label+ count, count, label, controller);
                    label += count;
                }
            }
        });

        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (currentPages > 1 && label > controller.getAllRecords().getRecords().size()+count) {
                    reduceCurrentPages();
                    table.removeAll();
                    draw(label - count, label, count, label, controller);
                    label -= count;
                }
            }
        });

        lastPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                currentPages = controller.getAllRecords().getRecords().size() / table.getItems().length;
                table.removeAll();
                draw(controller.getAllRecords().getRecords().size() - count,
                        controller.getAllRecords().getRecords().size(), count, label, controller);
            }
        });

        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                currentPages = 1;
                table.removeAll();
                draw(0, count,  count, label, controller);
            }
        });


        generate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                label = 0;
                if (!countPages.getText().isEmpty()) {
                    table.removeAll();
                    count = Integer.parseInt(countPages.getText());
                    if (count <= controller.getAllRecords().getRecords().size()) {
                        draw(0, count,  count, label, controller);
                    }
                    controller.setGenerate(true);
                }
            }
        });
        currentPage.setText("current page is " + 1);
    }
    public void clear() {
        table.removeAll();
    }

}
