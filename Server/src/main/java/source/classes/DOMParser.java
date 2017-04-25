package source.classes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by narey on 26.04.2017.
 */
public class DOMParser {
    public static Case readXML(File file) {
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();


            Node node = document.getDocumentElement();

            Element element = (Element) node;
            String surname = element.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
            String name = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
            String patronymic = element.getElementsByTagName("patronymic").item(0).getChildNodes().item(0).getNodeValue();
            String phone = element.getElementsByTagName("phone").item(0).getChildNodes().item(0).getNodeValue();
            String job = element.getElementsByTagName("job").item(0).getChildNodes().item(0).getNodeValue();


            return new Case(surname, name, patronymic, phone, job);
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
