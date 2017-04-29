package source.classes;

/**
 * JDOM парсер
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class JDOMParser {

    private static final Logger log = Logger.getLogger(JDOMParser.class);

    /**
     * Считывает архив из xml файла
     * @param file xml файл
     * @return архив
     */
    public static Case readXML(File file) {
        log.info("Чтение xml файла (JDOM)");

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
            log.error(io.toString());
        } catch (JDOMException jdomex) {
            log.error(jdomex.toString());
        }

        return newCase;
    }
}
