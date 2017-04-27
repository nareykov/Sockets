package source.classes;

/**
 * Created by narey on 27.04.2017.
 */
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class JDOMParser {
    public static Case readXML(File file) {

        SAXBuilder builder = new SAXBuilder();

        Case newCase = new Case();

        try {

            Document document = (Document) builder.build(file);
            Element rootNode = document.getRootElement();

            newCase.setSurname(rootNode.getChildText("surname"));
            newCase.setName(rootNode.getChildText("name"));
            newCase.setPatronymic(rootNode.getChildText("patronymic"));
            newCase.setPhone(rootNode.getChildText("phone"));
            newCase.setJob(rootNode.getChildText("job"));
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }

        return newCase;
    }
}
