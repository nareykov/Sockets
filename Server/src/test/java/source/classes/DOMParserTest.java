package source.classes;

import java.io.File;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by narey on 28.04.2017.
 */
public class DOMParserTest {
    @Test
    public void readXML() throws Exception {
        Case newCase = new Case("sdfgh", "aefsrg", "fg", "dfergt", "werget");

        File xmlFile = DOMParser.writeXML(newCase);

        assertEquals(newCase.getName(), DOMParser.readXML(xmlFile).getName());
        assertEquals(newCase.getSurname(), DOMParser.readXML(xmlFile).getSurname());
        assertEquals(newCase.getPatronymic(), DOMParser.readXML(xmlFile).getPatronymic());
        assertEquals(newCase.getPhone(), DOMParser.readXML(xmlFile).getPhone());
        assertEquals(newCase.getJob(), DOMParser.readXML(xmlFile).getJob());
    }

}