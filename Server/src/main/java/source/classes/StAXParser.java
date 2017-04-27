package source.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXParser {
    public static Case readXML(File file) {

        Case newCase = new Case();

        boolean bSurname = false;
        boolean bName = false;
        boolean bPatronymic = false;
        boolean bPhone = false;
        boolean bJob = false;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(
                            new FileReader(file.getAbsolutePath()));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("surname")) {
                            bSurname = true;
                        } else if (qName.equalsIgnoreCase("name")) {
                            bName = true;
                        } else if (qName.equalsIgnoreCase("patronymic")) {
                            bPatronymic = true;
                        } else if (qName.equalsIgnoreCase("phone")) {
                            bPhone = true;
                        } else if (qName.equalsIgnoreCase("job")) {
                            bJob = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bSurname) {
                            newCase.setSurname(characters.getData());
                            bSurname = false;
                        }
                        if(bName) {
                            newCase.setName(characters.getData());
                            bName = false;
                        }
                        if(bPatronymic) {
                            newCase.setPatronymic(characters.getData());
                            bPatronymic = false;
                        }
                        if(bPhone) {
                            newCase.setPhone(characters.getData());
                            bPhone = false;
                        }
                        if(bJob) {
                            newCase.setJob(characters.getData());
                            bJob = false;
                        }

                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return newCase;
    }
}
