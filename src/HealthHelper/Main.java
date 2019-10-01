package HealthHelper;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {

    //Создаем "resourse bundle" и делаем его статическим
    public static ResourceBundle bundleStart = ResourceBundle.getBundle("HealthHelper.lang.lang_ukr");

    //Метод для загрузки новой страницы
    public void PageLoader(String fxml_url, Button btn_name){
        //Скрываем текущею страницу
        btn_name.getScene().getWindow().hide();
        //Заружаем новую
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml_url));
        loader.setResources(bundleStart);
        //Проверка исключений
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Устанавливаем параметры сцены
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Health Helper");
        stage.show();
        //Добавляем иконку
        Image icon = new Image(getClass().getResourceAsStream("icon/cross.png"));
        stage.getIcons().add(icon);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Загрузка головного меню
        Parent root = FXMLLoader.load(getClass().getResource("fxml_folder/main.fxml"), bundleStart);

        primaryStage.setScene(new Scene(root, 460, 480));
        primaryStage.setTitle("Health Helper");
        primaryStage.setResizable(false);
        primaryStage.show();
        //Додаємо іконку
        Image icon = new Image(getClass().getResourceAsStream("icon/cross.png"));
        primaryStage.getIcons().add(icon);

    }
    public static void main(String[] args)throws ClassNotFoundException, SQLException, MessagingException, IOException {


        Conn.Conn(); //Підключення до БД
        Conn.CreateDB(); // Створення локальної БД
        Conn.CreateDBT(); // Створення таблиці з результатами
        Conn.DeleteTable("doctors");
        launch(args);
    }
}
