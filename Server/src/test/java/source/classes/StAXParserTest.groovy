package source.classes

/**
 * Created by narey on 29.04.2017.
 */
class StAXParserTest extends GroovyTestCase {
    void testReadXML() {
        Case newCase = new Case("sdfgh", "aefsrg", "fg", "dfergt", "werget");

        File xmlFile = DOMParser.writeXML(newCase);

        assertEquals(newCase.getName(), StAXParser.readXML(xmlFile).getName());
        assertEquals(newCase.getSurname(), StAXParser.readXML(xmlFile).getSurname());
        assertEquals(newCase.getPatronymic(), StAXParser.readXML(xmlFile).getPatronymic());
        assertEquals(newCase.getPhone(), StAXParser.readXML(xmlFile).getPhone());
        assertEquals(newCase.getJob(), StAXParser.readXML(xmlFile).getJob());
    }
}
