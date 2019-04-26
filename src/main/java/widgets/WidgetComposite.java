package widgets;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class WidgetComposite {
    private Shell shell;
    private Text placeOfResidence;
    private Text accountNumber;
    private  Text surname;
    private Text typeOfPhoneNumber;
    private Text fio;
    private Text typeOfPhone1Number;

    public WidgetComposite(Shell shell){
        this.shell = shell;
    }

    public void fillFields(){
        placeOfResidence = new Text(shell, SWT.CHECK);
        placeOfResidence.setBounds(10, 40, 182, 30);
        Label placeOfResidenceText = new Label(shell, SWT.NONE);
        placeOfResidenceText.setText("Place of residence");
        placeOfResidenceText.setBounds(200, 40, 150, 30);
        accountNumber = new Text(shell, SWT.CHECK);
        accountNumber.setBounds(10, 90, 182, 31);
        Label accountNumberText = new Label(shell, SWT.NONE);
        accountNumberText.setText("Account number");
        accountNumberText.setBounds(200, 91, 150, 30);

        surname = new Text(shell, SWT.CHECK);
        surname.setBounds(350, 40, 182, 30);
        Label surnameText1 = new Label(shell, SWT.NONE);
        surnameText1.setText("Surname");
        surnameText1.setBounds(560, 40, 182, 30);
        typeOfPhoneNumber = new Text(shell, SWT.CHECK);
        typeOfPhoneNumber.setBounds(350, 90, 182, 30);
        final Combo typeOfPhone = new Combo(shell, SWT.READ_ONLY);
        typeOfPhone.setBounds(560, 90, 182, 30);
        typeOfPhone.add("mobile phone");
        typeOfPhone.add("city phone");

        fio = new Text(shell, SWT.CHECK);
        fio.setBounds(750, 40, 182, 29);
        Label fioText = new Label(shell, SWT.NONE);
        fioText.setText("FIO");
        fioText.setBounds(1000, 40, 182, 30);
        typeOfPhone1Number = new Text(shell, SWT.CHECK);
        typeOfPhone1Number.setBounds(750, 90, 180, 30);
        final Combo typeOfPhone1 = new Combo(shell, SWT.READ_ONLY);
        typeOfPhone1.setBounds(1000, 90, 182, 30);
        typeOfPhone1.add("mobile phone");
        typeOfPhone1.add("city phone");
    }

    public Text getPlaceOfResidence() {
        return placeOfResidence;
    }

    public Text getAccountNumber() {
        return accountNumber;
    }

    public Text getSurname() {
        return surname;
    }

    public Text getTypeOfPhoneNumber() {
        return typeOfPhoneNumber;
    }

    public Text getFio() {
        return fio;
    }

    public Text getTypeOfPhone1Number() {
        return typeOfPhone1Number;
    }
}
