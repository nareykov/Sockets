package source.classes;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by narey on 27.04.2017.
 */
public class Parser {
    public static Case readXML(File file) {
        int type = getTypeOfParser();

        switch (type) {
            case 0: {
                return DOMParser.readXML(file);
            }
            case 1: {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = null;
                try {
                    parser = factory.newSAXParser();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                SAXPars saxp = new SAXPars();

                try {
                    parser.parse(file, saxp);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return saxp.getResult();
            }
            case 2: {
                return StAXParser.readXML(file);
            }
            case 3: {
                return JDOMParser.readXML(file);
            }
            default: {
                return null;
            }
        }
    }

    public static int getTypeOfParser(){
        File typeParserFile = new File("typeParserFile");
        int typeOfParser = -1;
        try {
            Scanner scanner = new Scanner(typeParserFile);
            typeOfParser = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return typeOfParser;
    }
}
