package HealthHelper;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {
    //Объявление кнопок перехода
    @FXML
    private Button ruthTest_button;

    @FXML
    private Button bwiTest_button;

    @FXML
    private  Button cpScale_button;

    @FXML
    void initialize() {
        //Загружаем страницу с Руфье
        ruthTest_button.setOnAction(event -> {
            PageLoader("fxml/ruthTestPage.fxml", bwiTest_button);
        });
        //Загрузка страницы теста ИМТ
        bwiTest_button.setOnAction(event -> {
            PageLoader("fxml/bwiTest.fxml", bwiTest_button);
        });
        //Загрузка страницы Шкалы Чайлд-Пью
        cpScale_button.setOnAction(event -> {
            PageLoader("fxml/cpScale.fxml", cpScale_button);
        });
    }

    //Ф-я для загрузки страницы при нажатии на кнопку
    public void PageLoader(String fxml_url, Button btn_name){
        //Скрываем текущею страницу
        btn_name.getScene().getWindow().hide();
        //Заружаем новую
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml_url));
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


}
