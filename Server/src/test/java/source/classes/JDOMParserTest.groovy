package source.classes

/**
 * Created by narey on 29.04.2017.
 */
class JDOMParserTest extends GroovyTestCase {
    void testReadXML() {
        Case newCase = new Case("sdfgh", "aefsrg", "fg", "dfergt", "werget");

        File xmlFile = DOMParser.writeXML(newCase);

        assertEquals(newCase.getName(), JDOMParser.readXML(xmlFile).getName());
        assertEquals(newCase.getSurname(), JDOMParser.readXML(xmlFile).getSurname());
        assertEquals(newCase.getPatronymic(), JDOMParser.readXML(xmlFile).getPatronymic());
        assertEquals(newCase.getPhone(), JDOMParser.readXML(xmlFile).getPhone());
        assertEquals(newCase.getJob(), JDOMParser.readXML(xmlFile).getJob());
    }
}
