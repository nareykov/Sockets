package source.classes;

/**
 * Created by narey on 27.04.2017.
 */
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class SAXPars extends DefaultHandler{

    Case newCase = new Case();
    String thisElement = "";

    public Case getResult(){
        return newCase;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("surname")) {
            newCase.setSurname(new String(ch, start, length));
        }
        if (thisElement.equals("name")) {
            newCase.setName(new String(ch, start, length));
        }
        if (thisElement.equals("patronymic")) {
            newCase.setPatronymic(new String(ch, start, length));
        }
        if (thisElement.equals("phone")) {
            newCase.setPhone(new String(ch, start, length));
        }
        if (thisElement.equals("job")) {
            newCase.setJob(new String(ch, start, length));
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }
}
