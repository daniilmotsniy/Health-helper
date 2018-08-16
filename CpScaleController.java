package HealthHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class CpScaleController extends MainController{

    @FXML
    Button btnBack;
    @FXML
    Button btnReset;
    @FXML
    ComboBox mno;
    @FXML
    ComboBox encefalopatia;
    @FXML
    ComboBox ascit;
    @FXML
    ComboBox albumin;
    @FXML
    ComboBox bilirubin;
    @FXML
    Text total;
    @FXML
    Text estimate;

    //Результирующий массив
    private int[] result = new int[5];

    //Лист с балами
    ArrayList<Integer> point = new ArrayList<Integer>();

    //Подсчет
    private int score;

    // Ф-я для оценки
    private void estimateFunctiion (){
        //Добавляем общий бал
        result[0] = point.get(0);
        //Вывод балла
        score = result[0] + result[1] + result[2] + result[3] + result[4];
        total.setText(String.valueOf(score));
        //Оценка
        if(score < 7){
            estimate.setText("Клас A. Тривалість життя: 15-20 років. Периопераційна летальність при абдомінальних операціях: 10%.");
        } else if (7 <= score & score < 10){
            estimate.setText("Клас B. Значне функціональне погіршення. Периопераційна летальність при абдомінальних операціях: 30%.");
        } else if(score > 10){
            estimate.setText("Клас C. Тривалість життя: 1-3 роки. Периопераційна летальність при абдомінальних операціях: 82%.");
        }

    }

    @FXML
    void initialize() {

        //Наполнение и обработка ComboBox-ов, оценка результата

        //ComboBox (bilirubin)
        bilirubin.getItems().addAll("<34.2", "34.2-51.3", ">51.3");
        bilirubin.setOnAction(event -> {
            int i0 = bilirubin.getSelectionModel().getSelectedIndex();
            if(i0 == 0){
                point.add(0, 1);
            } else if (i0 == 1) {
                point.add(0, 2);
            } else if (i0 == 2) {
                point.add(0, 3);
            }
            //Добавляем общий бал
            result[0] = point.get(0);

            // Ф-я для оценки
            estimateFunctiion();

        });

        //ComboBox (albumin)
        albumin.getItems().addAll(">35", "28-35", "<28");
        albumin.setOnAction(event -> {
            int i1 = albumin.getSelectionModel().getSelectedIndex();
            if(i1 == 0){
                point.add(1, 1);
            } else if (i1 == 1) {
                point.add(1, 2);
            } else if (i1 == 2) {
                point.add(1, 3);
            }

            //Добавляем общий бал
            result[1] = point.get(1);



            // Ф-я для оценки
            estimateFunctiion();
        });

        //ComboBox (mno)
        mno.getItems().addAll("<1.7", "1.7-2.2", ">2.2");
        mno.setOnAction(event -> {
            int i2 = mno.getSelectionModel().getSelectedIndex();
            if(i2 == 0){
                point.add(2, 1);
            } else if (i2 == 1) {
                point.add(2, 2);
            } else if (i2 == 2) {
                point.add(2, 3);
            }

            //Добавляем общий бал
            result[2] = point.get(2);

            // Ф-я для оценки
            estimateFunctiion();

        });

        //ComboBox (ascit)
        ascit.getItems().addAll("Відсутній", "Легкий", "Середній або важкий");
        ascit.setOnAction(event -> {
            int i3 = ascit.getSelectionModel().getSelectedIndex();
            if(i3 == 0){
                point.add(3, 1);
            } else if (i3 == 1) {
                point.add(3, 2);
            } else if (i3 == 2) {
                point.add(3, 3);
            }

            //Добавляем общий бал
            result[3] = point.get(3);

            // Ф-я для оценки
            estimateFunctiion();
        });

        //ComboBox (encefalopatia)
        encefalopatia.getItems().addAll("Відсутня", "1-2 ступінь", "3-4 ступінь");
        encefalopatia.setOnAction(event -> {
            int i4 = encefalopatia.getSelectionModel().getSelectedIndex();
            if(i4 == 0){
                point.add(4, 1);
            } else if (i4 == 1) {
                point.add(4, 2);
            } else if (i4 == 2) {
                point.add(4, 3);
            }

            //Добавляем общий бал
            result[4] = point.get(4);

            // Ф-я для оценки
            estimateFunctiion();
        });

        //Кнопка сбросса
        btnReset.setOnAction(event -> {
            albumin.getSelectionModel().select(-1);
            encefalopatia.getSelectionModel().select(-1);
            ascit.getSelectionModel().select(-1);
            bilirubin.getSelectionModel().select(-1);
            mno.getSelectionModel().select(-1);
            score = 0;
            result[0] = 0;
            result[1] = 0;
            result[2] = 0;
            result[3] = 0;
            result[4] = 0;
            total.setText("");
            estimate.setText("");
        });

        //Обработка событий по возвращению в главное меню
        btnBack.setOnAction(event -> {
            PageLoader("fxml/main.fxml", btnBack);
        });

    }
}
