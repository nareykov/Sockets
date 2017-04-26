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
                    " Pass          TEXT           NOT NULL," +
                    " Priority      TEXT           NOT NULL)";
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
                    " Name     TEXT                NOT NULL)";
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

    public void insertIntoUsers(String username, String pass, int priority) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO Users (Username, Pass, Priority) " +
                    "VALUES ('" + username + "', '" +  pass + "', '" + Integer.toString(priority) +"');";
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
     * */

    public void insertIntoFileBase(int id, String name) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO FileBase (id, Name) " +
                    "VALUES ('" + Integer.toString(id) + "', '" + name + "');";
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
     * @param username Мыло пользователя
     * @param pass Пароль
     * @return true - верный логин и пароль, false - неверные логин или пароль
     */
    public boolean enter(String username, String pass) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Username = '" + username + "';" );
            if ((pass).equals(rs.getString("Pass"))) {
                rs.close();
                stmt.close();
                return true;
            }
        } catch ( SQLException e ) {
            return false;
        }
        return false;
    }

    public ResultSet getUsersResultSet() {

        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getFileBaseResultSet() {

        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM FileBase;" );
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void changeName(int id, String newName) {
        try {
            stmt = c.createStatement();

            String sql = "UPDATE FileBase SET Name = '" + newName + "' WHERE id = '" + Integer.toString(id) + "';";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void changePriority(String username, int priority) {
        try {
            stmt = c.createStatement();

            String sql = "UPDATE Users SET Priority = " + Integer.toString(priority) + " WHERE Username = '" + username + "';";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * Проверяет был ли зарегистрирован пользователь ранее.
     * @return Если уже зарегистрирован - true, если нет - false.
     */
    public boolean isRegistered(String username) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Username = '" + username + "';" );
            if (rs.getString("Username") != null) {
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
    }

    public int getPriority(String username) {
        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Username = '" + username + "';" );
            int priority = Integer.parseInt(rs.getString("Priority"));
            return priority;

        } catch ( Exception e ) {
            //log.error("User not found");
            //System.out.println("User not found");
            return -1;
        }
    }


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
