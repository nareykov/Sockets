package source;

import org.apache.log4j.Logger;

import java.io.File;
import java.sql.*;

/**
 * Класс с методамидля работы с базой данных
 */
public class DataBase {
    private Connection c = null;

    private Statement stmt = null;

    private static final Logger log = Logger.getLogger(DataBase.class);

    /** Устанавливает соединение с базой данных.
     * Перед подключением к базе данных производим проверку на её существование.
     * В зависимости от результата производим открытие базы данных или её восстановление
     */
    public void connectToDataBase() {
        if(!new File("database.db").exists()){
            if (!this.restoreDataBase()) {
                log.error("Tables not created");
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
            log.error("Restore database failed");
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
        log.info("Opened database successfully");
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
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        log.info("Table Users created successfully");
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
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        log.info("Table FileBase created successfully");
        return true;
    }

    /**
     * Закрывает базу данных.
     */
    public void closeDataBase() {
        try {
            c.close();
        } catch (SQLException e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(e.getErrorCode());
        }
        log.info("Database closed successfully");
    }

    /**
     * Записывает в базу данных данных о новом юзере
     * @param username имя юзера
     * @param pass пароль
     * @param priority уровень прав
     */
    public void insertIntoUsers(String username, String pass, int priority) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO Users (Username, Pass, Priority) " +
                    "VALUES ('" + username + "', '" +  pass + "', '" + Integer.toString(priority) +"');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("Recorded into Users successfully");
    }

    /**
     * Запись в базу данных нового архива
     * @param id айди архива
     * @param name имя архива
     */
    public void insertIntoFileBase(int id, String name) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO FileBase (id, Name) " +
                    "VALUES ('" + Integer.toString(id) + "', '" + name + "');";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch ( Exception e ) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("Recorded into FileBase successfully");
    }

    /**
     * Проверка логина и пароля пользователя при нажатии на Enter в окне входа.
     * @param username Мыло пользователя
     * @param pass Пароль
     * @return true - верный логин и пароль, false - неверные логин или пароль
     */
    public boolean enter(String username, String pass) {
        log.info(username + " enter");
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Username = '" + username + "';" );
            if ((pass).equals(rs.getString("Pass"))) {
                rs.close();
                stmt.close();
                return true;
            }
        } catch ( SQLException e ) {
            log.error(e.toString());
            return false;
        }
        return false;
    }

    /**
     * Получаем таблицу юзеров из базы данных
     * @return таблица
     */
    public ResultSet getUsersResultSet() {

        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
            return rs;

        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }

    /**
     * Получаем таблицу архивов из базы данных
     * @return таблица
     */
    public ResultSet getFileBaseResultSet() {

        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM FileBase;" );
            return rs;

        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }

    /**
     * Меняет имя архива
     * @param id айди архива
     * @param newName новое имя
     */
    public void changeName(int id, String newName) {

        log.info("Changing name: id(" + id + ") to " + newName);

        try {
            stmt = c.createStatement();

            String sql = "UPDATE FileBase SET Name = '" + newName + "' WHERE id = '" + Integer.toString(id) + "';";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            log.error(e.toString());
        }
    }

    /**
     * Меняет уровень прав юзера
     * @param username имя юзера
     * @param priority новый уровень прав
     */
    public void changePriority(String username, int priority) {

        log.info("Changing " + username + " priority to " + priority);

        try {
            stmt = c.createStatement();

            String sql = "UPDATE Users SET Priority = " + Integer.toString(priority) + " WHERE Username = '" + username + "';";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            log.error(e.toString());
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
            log.error(e.toString());
            return false;
        }
        return false;
    }

    /**
     * Получает уровень прав юзера
     * @param username имя юзера
     * @return уровень прав
     */
    public int getPriority(String username) {
        try {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE Username = '" + username + "';" );
            int priority = Integer.parseInt(rs.getString("Priority"));
            return priority;

        } catch ( Exception e ) {
            log.error(e.toString());
            return -1;
        }
    }

    /**
     * Удаляет архив из базы данных
     * @param id айди архива
     */
    public void removeFromFileBase(int id) {
        try {
            stmt = c.createStatement();

            String sql = "DELETE FROM FileBase WHERE id = '" + Integer.toString(id) + "';";
            stmt.executeUpdate(sql);

        } catch ( Exception e ) {
            log.error("File " + id + " not removed from FileBase");
            return;
        }
    }
}
