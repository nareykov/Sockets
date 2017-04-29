package source.packet;

import java.io.*;
import java.net.Socket;

/**
 * Абстракция для отправляемых пакетов
 */
public abstract class OPacket {

    /**
     * Получает айди пакета
     * @return айди
     */
    public abstract short getId();

    /**
     * Записывает данные в поток oos
     * @param oos поток для записи
     * @throws IOException
     */
    public abstract void write(ObjectOutputStream oos) throws IOException;

    /**
     * Считывает данные из потока ois
     * @param ois поток для считывания
     * @throws IOException
     */
    public abstract void read(ObjectInputStream ois) throws IOException;

    /**
     * Обрабатывает полученные данные
     */
    public abstract void handle();

}
