package source.packet;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import source.classes.Case;
import source.classes.DOMParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 25.04.2017.
 */
public class PacketCreateCase extends OPacket {

    File file;
    String name;

    public PacketCreateCase() {

    }

    public PacketCreateCase(Case newCase) {
        name = newCase.getSurname() + " " + newCase.getName();
        file = DOMParser.writeXML(newCase);
    }

    @Override
    public short getId() {
        return 6;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeObject(file);
        oos.writeUTF(name);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {

    }
}
