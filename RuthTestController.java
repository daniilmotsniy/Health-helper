package HealthHelper;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class RuthTestController extends MainController {
    @FXML
    private TextArea labelInput0;

    @FXML
    private TextArea labelInput2;

    @FXML
    private TextArea labelInput3;

    @FXML
    private TextArea labelInput1;

    @FXML
    private Text labelEstimate;

    @FXML
    private Text labelResult;

    @FXML Button btnCheck;

    @FXML Button btnBackRuthTest;

    @FXML
    void initialize() {
        //Обработка событий при нажатии на кнопку подсчета
        btnCheck.setOnAction(event -> {
            //Установка показаний
            int age = Integer.valueOf(labelInput0.getText());

            Double input1 = Double.parseDouble(labelInput1.getText());
            Double input2 = Double.parseDouble(labelInput2.getText());
            Double input3 = Double.parseDouble(labelInput3.getText());
            //

            //Формула Руфье
            Double result = (4 * (input1 + input2 + input3) - 200) / 10;
            //

            //Вывод результата
            labelResult.setText(result.toString());
            //

            //Оценка результата 7-8 лет
            if (age >= 7 & age <= 8 & result >= 21) {
                labelEstimate.setText("Незадовільно");
            } else if (age >= 7 & age <= 8 & result >= 17 & result < 21) {
                labelEstimate.setText("Слабо");
            } else if (age >= 7 & age <= 8 & result >= 12 & result < 17) {
                labelEstimate.setText("Задовілььно");
            } else if (age >= 7 & age <= 8 & result >= 6.5 & result < 12) {
                labelEstimate.setText("Гарно");
            } else if (age >= 7 & age <= 8 & result < 6.5) {
                labelEstimate.setText("Відмінно");

                //Оценка результата 9-10 лет

            } else if (age >= 9 & age <= 10 & result >= 19.5) {
                labelEstimate.setText("Незадовільно");
            } else if (age >= 9 & age <= 10 & result >= 14.5 & result < 19.5) {
                labelEstimate.setText("Слабо");
            } else if (age >= 9 & age <= 10 & result >= 10.5 & result < 14.5) {
                labelEstimate.setText("Задовілььно");
            } else if (age >= 9 & age <= 10 & result >= 5 & result < 10.5) {
                labelEstimate.setText("Гарно");
            } else if (age >= 9 & age <= 10 & result < 5) {
                labelEstimate.setText("Отлично");

                //Оценка результата 11-12 лет
            } else if (age >= 11 & age <= 12 & result >= 18) {
                labelEstimate.setText("Незадовільно");
            } else if (age >= 11 & age <= 12 & result >= 13 & result < 18) {
                labelEstimate.setText("Слабо");
            } else if (age >= 11 & age <= 12 & result >= 8 & result < 13) {
                labelEstimate.setText("Задовільно");
            } else if (age >= 11 & age <= 12 & result >= 3.5 & result < 8) {
                labelEstimate.setText("Гарно");
            } else if (age >= 11 & age <= 12 & result < 3.5) {
                labelEstimate.setText("Отлично");

                //Оценка результата 13-14 лет

            } else if(age >= 13 & age <= 14 & result >= 16.5){
                labelEstimate.setText("Незадовільно");
            } else if(age >= 13 & age <= 14 & result >= 12 & result < 16.5) {
                labelEstimate.setText("Слабо");
            } else if(age >= 13 & age <= 14 & result >= 6.5 & result < 12) {
                labelEstimate.setText("Задовілььно");
            } else if(age >= 13 & age <= 14 & result >= 2 & result < 6.5) {
                labelEstimate.setText("Гарно");
            } else if(age >= 13 & age <= 14 & result < 2) {
                labelEstimate.setText("Відмінно");

                //Оценка результата 15-18 лет

            } else if(age >= 15 & age <= 18 & result >= 15){
                labelEstimate.setText("Незадовільно");
            } else if(age >= 15 & age <= 18 & result >= 10 & result < 15) {
                labelEstimate.setText("Слабо");
            } else if(age >= 15 & age <= 18 & result >= 5 & result < 10) {
                labelEstimate.setText("Задовільно");
            } else if(age >= 15 & age <= 18 & result >= 0.5 & result < 5) {
                labelEstimate.setText("Гарно");
            } else if(age >= 15 & age <= 18 & result < 0.5) {
                labelEstimate.setText("Відмінно");
            } else {
                labelEstimate.setText("Ви невірно вказали дані");
            }
        });

        //Обработка событий по возвращению в главное меню
        btnBackRuthTest.setOnAction(event -> {
            PageLoader("fxml/main.fxml", btnBackRuthTest );
        });

    }

    // СЕКУНДОМЕР НАЧАЛО ПРИЛОЖЕНИЯ
    // *Концепция сделана c помощью форума для программистов*

    boolean f = false; // вкл/выкл секундомера
    int sec = 0; // секунды

    @FXML
    private Label time_sec; // секунды

    // кнопка очистить
    @FXML
    void time_clear(ActionEvent event) {
        f = false; // выключаем секундомер
        sec = 0; // все значения равны нулю
        time_sec.setText(sec+""); // отобразим это в приложении
    }

    // основная кнопка старт-стоп секундомера
    @FXML
    void time_go(ActionEvent event) {
        f = !f; // инвертируем наш "показатель" вкл/выкл
        Thread s1 = new Thread(new Runnable(){ // создаем поток
            public void run(){
                while (f){ // пока секундомер ВКЛ, то будем делать следующее
                    try {
                        Thread.sleep(1000); // пауза в 1 секунду
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(()-> // это форма для потока в JavaFX
                    {
                        sec++; // увеличиваем секунду на 1 (пауза то была)
                        if (sec==60){ // если секунд 60, то
                            sec = 0; // обновляем
                        }
                        time_sec.setText(sec+""); // выводим наше время на экран
                    });
                }
            }
        });

        s1.start(); // сообственно старт самого потока выше
    }
    //СЕКУНДОМЕР КОНЕЦ ПРИЛОЖЕНИЯ

}
