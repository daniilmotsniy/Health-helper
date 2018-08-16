package HealthHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Создаем главное окно
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        //Устанавливаем параметры сцены
        primaryStage.setTitle("Health Helper");
        primaryStage.setScene(new Scene(root, 460, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
        //Добавляем иконку
        Image icon = new Image(getClass().getResourceAsStream("icon/cross.png"));
        primaryStage.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
