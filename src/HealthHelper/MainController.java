package HealthHelper;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;

public class MainController extends Main {
    //Объявление кнопок перехода и смены языка
    @FXML
    private Button ruthTest_button;

    @FXML
    private Button bwiTest_button;

    @FXML
    private  Button dm_button;

    @FXML
    private Button send;

    @FXML
    private Button doctor_btn;

    @FXML
    private  Button ukr_btn;

    @FXML
    private  Button rus_btn;

    @FXML
    private  Button eng_btn;

    @FXML
    public ImageView ukr_img;

    @FXML
    public ImageView rus_img;

    @FXML
    public ImageView eng_img;

    //Создаем  иконки языков

    static String rus_img_href = "lang_png/rus.png";
    static String ukr_img_href = "lang_png/ukr.png";
    static String eng_img_href = "lang_png/eng.png";

    public Image rus = new Image(this.getClass().getResource(rus_img_href).toString());
    public Image ukr = new Image(this.getClass().getResource(ukr_img_href).toString());
    public Image eng = new Image(this.getClass().getResource(eng_img_href).toString());

    @FXML
    void initialize() {
        //Иниц-я иконок-кнопок
         rus_img.setImage(rus);
         ukr_img.setImage(ukr);
         eng_img.setImage(eng);

        //Загружаем страницу с Руфье
        ruthTest_button.setOnAction(event -> {
            PageLoader("fxml_folder/ruthTestPage.fxml", ruthTest_button);
        });
        //Загрузка страницы теста ИМТ
        bwiTest_button.setOnAction(event -> {
            PageLoader("fxml_folder/bwiTest.fxml", bwiTest_button);
        });

        dm_button.setOnAction(event -> {
            PageLoader("fxml_folder/dm.fxml", dm_button);
        });

        doctor_btn.setOnAction(event -> {
            PageLoader("fxml_folder/myDoctor.fxml", doctor_btn);
        });

        send.setOnAction(event -> {
            PageLoader("fxml_folder/send.fxml", send);
        });
    }

    //Ф-я изменения языка

    public void eng_c(MouseEvent mouseEvent) {
        //Анимация для иконок-кнопок
        eng_img_href = "animated/eng.png";
        rus_img_href = "lang_png/rus.png";
        ukr_img_href = "lang_png/ukr.png";
        //Загрузка языкового пакета  и страницы
        bundleStart = ResourceBundle.getBundle("HealthHelper.lang.lang_eng");
        PageLoader("fxml_folder/main.fxml", eng_btn);

    }

    public void rus_c(MouseEvent mouseEvent){
        rus_img_href = "animated/rus.png";
        eng_img_href = "lang_png/eng.png";
        ukr_img_href = "lang_png/ukr.png";

        bundleStart = ResourceBundle.getBundle("HealthHelper.lang.lang_rus");
        PageLoader("fxml_folder/main.fxml", rus_btn);
    }

    public void ukr_c(MouseEvent mouseEvent) {
        ukr_img_href = "animated/ukr.png";
        eng_img_href = "lang_png/eng.png";
        rus_img_href = "lang_png/rus.png";


        bundleStart = ResourceBundle.getBundle("HealthHelper.lang.lang_ukr");
        PageLoader("fxml_folder/main.fxml", ukr_btn);
    }

}
