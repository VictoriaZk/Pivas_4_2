package view;
import controller.Controller;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
import widgets.TableComposite;
import java.io.File;

public class MainDisplay {
    private static final int HEIGHT = 30;
    private static final int WIDTH = 100;
    private static final int COORDINATEY = 10;
    private Display display = new Display();
    private Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
    private int counter = 0;
    private Controller controller = new Controller();
    private TableComposite tableComposite;
    private final MessageBox WARNING_OF_EXITING_FILE = new MessageBox(shell, SWT.COLOR_RED);

    {
        WARNING_OF_EXITING_FILE.setMessage("Choose any file, please!");
        WARNING_OF_EXITING_FILE.setText("File open error");
    }

    public MainDisplay() {
        Color gray = display.getSystemColor(SWT.COLOR_WHITE);
        shell.setBackground(gray);
        shell.setText("Laboratory work 2:");
        shell.setSize(1100, 600);
        centerWindow();
        initFirstWindow();
        tableComposite = new TableComposite(shell, SWT.NULL);
        tableComposite.initTable(controller);
        tableComposite.setBounds(50, 10, 990, 600);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private void centerWindow() {
        Rectangle rectangle = shell.getDisplay().getBounds();
        Point p = shell.getSize();
        int nLeft = (rectangle.width - p.x) / 2;
        int nTop = (rectangle.height - p.y) / 2;
        shell.setBounds(nLeft, nTop, p.x, p.y);
    }

    private void initFirstWindow(){
        Button addNewClient = new Button(shell, SWT.PUSH);
        addNewClient.setBounds(50, COORDINATEY, WIDTH, HEIGHT);
        addNewClient.setText("To add client");
        Button searchClient = new Button(shell, SWT.PUSH);
        searchClient.setBounds(170, COORDINATEY, WIDTH, HEIGHT);
        searchClient.setText("To search client");
        Button deleteClient = new Button(shell, SWT.PUSH);
        deleteClient.setBounds(290, COORDINATEY, WIDTH, HEIGHT);
        deleteClient.setText("To delete client");
        Button load = new Button(shell, SWT.PUSH);
        load.setBounds(410, COORDINATEY, WIDTH, HEIGHT);
        load.setText("To load file");
        Button save = new Button(shell, SWT.PUSH);
        save.setBounds(530, COORDINATEY, WIDTH, HEIGHT);
        save.setText("To save file");

        Menu menu = new Menu(shell, SWT.BAR);
        menu.setLocation(40, 190);
        MenuItem file = new MenuItem(menu, SWT.CASCADE);
        file.setText("File");
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        file.setMenu(fileMenu);
        MenuItem openItem = new MenuItem(fileMenu, SWT.PUSH);
        openItem.setText("Open");

        save.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("To save file");
                fileDialog.setFilterPath("D:\\studing\\pivas\\sem4\\Pivas_4_2_");
                String[] filterExtension = {"*.xml"};
                fileDialog.setFilterExtensions(filterExtension);
                String selected = fileDialog.open();
                if (selected == null){
                   WARNING_OF_EXITING_FILE.open();
                }
                else{
                    controller.saveFile(new File(selected));
                }
            }
        });

        load.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("To Open file");
                fileDialog.setFilterPath("D:\\studing\\pivas\\sem4\\Pivas_4_2_");
                String[] fileExtension = {"*.xml"};
                fileDialog.setFilterExtensions(fileExtension);
                String file = fileDialog.open();
                if(file == null){
                    WARNING_OF_EXITING_FILE.open();
                }
                else {
                    controller.openFile(new File(file));
                }
                counter = controller.getAllRecords().getRecords().size();
                controller.setLabel(counter);
                tableComposite.clear();
                tableComposite.draw(0, counter, counter, controller.getLabel(), controller);
            }
        });

        addNewClient.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               counter++;
               AddDisplay addDisplay = new AddDisplay(display, controller, tableComposite);
            }
        });

        searchClient.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SearchDisplay searchDisplay = new SearchDisplay(display, controller);
            }
        });

        deleteClient.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DeleteDisplay deleteDisplay = new DeleteDisplay(display, tableComposite, controller);
            }
        });
    }
}
