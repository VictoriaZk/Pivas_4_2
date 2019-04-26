package widgets;
import org.eclipse.swt.widgets.Text;
import java.util.ArrayList;
import java.util.List;

public class FieldsClean {

    public void clean(Text firstField, Text secondField) {
        firstField.setText("");
        secondField.setText("");
    }

    public void clean(Text firstField, Text secondField, Text thirdField, Text forthField, Text fifthField, Text sixField,
                      Text seventhField, Text eigthField, Text ninthField, Text tenthField, Text eleventhField){
        List <Text> fields = new ArrayList<>();
        addField(firstField, secondField, thirdField, forthField, fifthField, fields);
        addField(sixField, seventhField, eigthField, ninthField, tenthField, fields);
        fields.add(eleventhField);
        for(Text index: fields){
            index.setText("");
        }
    }

    private void addField(Text sixField, Text seventhField, Text eigthField, Text ninthField,
                          Text tenthField, List<Text> fields) {
        fields.add(sixField);
        fields.add(seventhField);
        fields.add(eigthField);
        fields.add(ninthField);
        fields.add(tenthField);
    }
}
