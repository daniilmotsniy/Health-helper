package HealthHelper;

import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conn {
    private static Connection conn;
    private static Statement stamp;
    private static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    static void conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:DATABASE.s3db");
    }

    // --------Создание таблицы--------
    static void createDocTable() throws ClassNotFoundException, SQLException
    {
        stamp = conn.createStatement();
        stamp.execute("CREATE TABLE if not exists 'doctors' ('name' text, 'email' text);");
    }

    // --------Заполнение таблицы--------
    static void writeDocTable(String lastname, String email) throws SQLException
    {
        stamp.execute("INSERT INTO 'doctors' ('name', 'email') VALUES ('" + lastname + "', '" + email + "'); ");
    }

    // --------Удаление БД--------
    public static void deleteTable(String dbname) throws SQLException
    {
        stamp.execute("DROP TABLE '" + dbname + "';");
    }

    // -------- Вывод таблицы--------
    static void readDocIntoComboBox(ComboBox doctors) throws ClassNotFoundException, SQLException
    {
        resSet = stamp.executeQuery("SELECT * FROM doctors");

        while(resSet.next())
        {
            String  name = resSet.getString("name");
            String  email = resSet.getString("email");
            doctors.getItems().addAll(name);
        }
    }

    // -------- Вывод таблицы--------
    static void getDocEmail(ComboBox cb) throws ClassNotFoundException, SQLException
    {
        resSet = stamp.executeQuery("SELECT * FROM doctors");

        while(resSet.next())
        {
            String  email = resSet.getString("email");
            cb.getItems().addAll(email);
        }
    }


    // --------Закрытие--------
    static void closeDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        stamp.close();
        resSet.close();

    }

    // --------Создание таблицы data--------
    static void createDataTable() throws ClassNotFoundException, SQLException
    {
        stamp = conn.createStatement();
        stamp.execute("CREATE TABLE if not exists 'data' ('id' INTEGER, 'temp' FLOAT, 'glucose' FLOAT, 'pulse' INTEGER, 'rate' INTEGER, 'press' TEXT, 'date' TEXT);");
    }

    // --------Заполнение таблицы data--------
    static void writeDataTable(int id, float temp, float gluc, int puls, int rate, String pres, String time) throws SQLException
    {
        stamp.execute("INSERT INTO 'data' ('id', 'temp', 'glucose', 'pulse', 'rate','press', 'date') VALUES ('"+id+"', '"+temp+"', '"+gluc+"', '"+puls+"', '"+rate+"', '"+pres+"', '" + time + "'); ");
    }

    // -------- Вивід таблиці data--------
   static void readData(ArrayList<User> users) throws ClassNotFoundException, SQLException {
        resSet = stamp.executeQuery("SELECT * FROM 'data' ");

        while(resSet.next()) {
            int id = resSet.getInt("id");
            float temp = resSet.getFloat("temp");
            float glucose = resSet.getFloat("glucose");
            int pulse = resSet.getInt("pulse");
            int rate = resSet.getInt("rate");
            String press = resSet.getString("press");
            String date = resSet.getString("date");

            Data data = new Data(temp, glucose, pulse, rate, press);

            users.add(new User(id, data, date));

            System.out.println("ID = " + id);
            System.out.println("Temp = " + temp);
            System.out.println("Glucose = " + glucose);
            System.out.println("Pulse = " + pulse);
            System.out.println("Rate = " + rate);
            System.out.println("Press = " + press);
            System.out.println("Date = " + date);
            System.out.println();
        }

    }

}
