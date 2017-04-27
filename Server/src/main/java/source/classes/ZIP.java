package source.classes;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by narey on 28.04.2017.
 */
public class ZIP {
    public static void toZIP(File file, String name) {
        File ZIPFile = new File(name);

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(ZIPFile));
            FileInputStream fis = new FileInputStream(file))
        {
            ZipEntry entry1 = new ZipEntry(file.getPath());
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static File fromZIP(File ZIPFile) {

        File file = new File("resultFromZip");

        try {
            ZipFile zip = new ZipFile(ZIPFile.getPath());
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println(entry.getName());

                write(zip.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
            }

            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
}
