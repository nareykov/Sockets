package source;

import java.io.File;
import java.sql.*;

/**
 * Created by narey on 20.04.2017.
 */
public class DataBase {
    private Connection c = null;

    private Statement stmt = null;

    private final String salt = "eshgfure";

    //private static final Logger log = Logger.getLogger(DataBase.class);

    /** Устанавливает соединение с базой данных.
     * Перед подключением к базе данных производим проверку на её существование.
     * В зависимости от результата производим открытие базы данных или её восстановление
     */
    public void connectToDataBase() {
        if(!new File("database.db").exists()){
            if (!this.restoreDataBase()) {
                //log.error("Tables not created");
                System.out.println("Tables not created");
            }
        } else {
            this.openDataBase();
        }
    }

    /**
     * Востановление базы данных.
     * Создается файл базы данных и таблицы.
     * @return false - файл или таблица не создались, true - успех)
     */
    private boolean restoreDataBase() {
        if (this.openDataBase()) {
            if (!this.createUsers() || !this.createFileBase()) {
                return false;
            } else {
                return true;
            }
        } else {
            //log.error("Restore database failed");
            System.out.println("Restore database failed");
            return false;
        }
    }

    /**
     * Открытие базы данных или, создание и открытие.
     * @return false - возникло исключение при создании файла БД, true - в случае успеха
     */
    private boolean openDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        //log.info("Opened database successfully");
        System.out.println("Opened database successfully");
        return true;
    }

    /**
     * Создание таблицы пользователей.
     * @return true - таблица успешно создана, false - исключение
     */
    private boolean createUsers() {
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE Users " +
                    "(Username      TEXT           NOT NULL," +
                    " Pass          TEXT           NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            //log.error(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        //log.info("Table Users created successfully");
        System.out.println("Table Users created successfully");
        return true;
    }

    /**
     * Создание таблицы файлов.
     * @return true - таблица успешно создана, false - исключение
     */
    private boolean createFileBase() {
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE FileBase " +
                    "(id       TEXT                NOT NULL," +
                    " Name     TEXT                NOT NULL," +
                    " Path     TEXT                NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            //log.error(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        //log.info("Table FileBase created successfully");
        System.out.println("Table FileBase created successfully");
        return true;
    }

    /**
     * Закрывает базу данных.
     */
    public void closeDataBase() {
        try {
            c.close();
        } catch (SQLException e) {
            //log.error(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(e.getErrorCode());
        }
        //log.info("Database closed successfully");
        System.out.println("Database closed successfully");
    }

    public void insertIntoUsers(String username, String pass) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO Users (Username, Pass) " +
                    "VALUES ('" + username + "', '" +  pass + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            //log.error(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //log.info("Recorded into Users successfully");
        System.out.println("Recorded into Users successfully");
    }

    /**
     * Запись в таблицу файлов имени файла и полного пути
     * @param path Полный путь
     */
    public void insertIntoFileBase(String id, String name, String path) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO FileBase (id, Name, Path) " +
                    "VALUES ('" + id + "', '" + name + "', '" + path + "');";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch ( Exception e ) {
            //log.error(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //log.info("Recorded into FileBase successfully");
        System.out.println("Recorded into FileBase successfully");
    }

    /**
     * Проверка логина и пароля пользователя при нажатии на Enter в окне входа.
     * @param email Мыло пользователя
     * @param pass Пароль
     * @return true - верный логин и пароль, false - неверные логин или пароль
     */
    /*public boolean enter(String email, String pass) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Email = '" + email + "';" );
            if ((pass + salt).equals(rs.getString("Pass"))) {
                rs.close();
                stmt.close();
                return true;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            log.info("Incorrect login or password");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }*/

    /**
     * ищет в базе данных файл по имени и, в случае успеха, возвращает полный путь.
     * Если не найден, возвращает null.
     * @param name имя файла
     * @return Возвращает полный путь искомого файла
     */
    /*public String searchInFileBase(String name) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM FileBase WHERE Name = '" + name + "';" );
            if (rs.getString("Name") != null) {
                String path = rs.getString("Path");
                rs.close();
                stmt.close();
                return path;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            return null;
        }
        return null;
    }*/

    /**
     * Проверяет был ли зарегистрирован пользователь ранее.
     * @param email Мыло пользователя
     * @return Если уже зарегистрирован - true, если нет - false.
     */
    /*public boolean isRegistered(String email) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Email = '" + email + "';" );
            if (rs.getString("Email") != null) {
                rs.close();
                stmt.close();
                return true;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            return false;
        }
        return false;
    }*/

    /**
     * Достает из базы данных и возвращает кол-во оставшихся байт у текущего пользователя.
     * @param currUser Мыло текущего польвателя
     * @return Возвращает кол-во доступных пользователю байт
     */


    /**
     * Удаляет из базы данных файл/папку
     * @param path путь
     */
    /*public void removeFromFileBase(String path) {
        try {
            stmt = c.createStatement();

            String sql = "DELETE FROM FileBase WHERE Path = '" + path + "';";
            stmt.executeUpdate(sql);

        } catch ( Exception e ) {
            log.error("File " + path + " not removed from FileBase");
            System.out.println("File " + path + " not removed from FileBase");
            return;
        }
    }*/
}
