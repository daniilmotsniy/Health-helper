package HealthHelper;

import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Conn {

    static Date today = new Date();
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // Підключаємось до БД
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:DATABASE.s3db");

    }

    // Створюємо таблицю
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'doctors' ('name' text, 'email' text);");
    }

    // Заповнюємо таблицю
    public static void WriteDB(String lastname, String email) throws SQLException
    {
        statmt.execute("INSERT INTO 'doctors' ('name', 'email') VALUES ('" + lastname + "', '" + email + "'); ");
    }

    // Видалити таблицю
    public static void DeleteTable(String dbname) throws SQLException
    {
        statmt.execute("DROP TABLE '" + dbname + "';");
    }

    // Виводимо таблицю
    public static void ReadDB(ComboBox doctors) throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM doctors");

        while(resSet.next())
        {
            String  name = resSet.getString("name");
            String  email = resSet.getString("email");
            doctors.getItems().addAll(name);
        }

    }
    // Виводимо таблиці

    public static void GetEmail(ComboBox cb) throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM doctors");

        while(resSet.next())
        {
            String  email = resSet.getString("email");
            cb.getItems().addAll(email);

        }


    }


    // Закриття
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

    }

    // Створюємо таблицю (текст)
    public static void CreateDBT() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'values' ('temp' text, 'pulse' text, 'press' text, 'date' text);");

    }

    // Заповнюємо таблицю (текст)
    public static void WriteDBT(String temp, String puls, String pres ) throws SQLException
    {
        statmt.execute("INSERT INTO 'values' ('temp', 'pulse', 'press', 'date') VALUES ('"+temp+"', '"+puls+"', '"+pres+"', '" + today.toString() + "'); ");

    }

    // -------- Вивід таблиці--------
 /*  public static void ReadDBT() throws ClassNotFoundException, SQLException {
        resSet = statmt.executeQuery("SELECT * FROM 'values' ");


        String temp = resSet.getString("temp");
        String pulse = resSet.getString("pulse");
        String press = resSet.getString("press");
        String date = resSet.getString("date");

        while(resSet.next()) {

            System.out.println("Temp = " + temp);
            System.out.println("Pulse = " + pulse);
            System.out.println("Press = " + press);
            System.out.println("Date = " + date);
            System.out.println(" ");
        }

    }
    */
    //Метод для заповнення діаграми значеннями с локальної бази данних
    public static void FillDiagram(XYChart.Series<String, Integer> pulse_d,  XYChart.Series<String, Integer> temp_d, XYChart.Series<String, Integer> press_d  ) throws ClassNotFoundException, SQLException {

        resSet = statmt.executeQuery("SELECT * FROM 'values' ");

        while(resSet.next()) {
            String temp = resSet.getString("temp");
            String pulse = resSet.getString("pulse");
            String press = resSet.getString("press");
            String date = resSet.getString("date");
            pulse_d.getData().add(new XYChart.Data<>(date, Integer.parseInt(pulse)));
            temp_d.getData().add(new XYChart.Data<>(date, Integer.parseInt(temp)));
            press_d.getData().add(new XYChart.Data<>(date, Integer.parseInt(press)));
        }
    }

}
