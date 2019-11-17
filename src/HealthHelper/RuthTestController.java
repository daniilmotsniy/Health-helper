package HealthHelper;

import HealthHelper.email.Email;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
    Text labelEstimate_no;
    @FXML
    Text labelEstimate_bad;
    @FXML
    Text labelEstimate_yes;
    @FXML
    Text labelEstimate_good;
    @FXML
    Text labelEstimate_perfect;
    @FXML
    private Button btnSend_rt;
    @FXML
    ComboBox doctors_rt;
    Date td  = new Date();

    @FXML
    private Text labelResult;
    @FXML
    private Text empty_rt;
    @FXML
    private TextField id_rt;
    @FXML Button btnCheck;
    @FXML Button btnBackRuthTest;

    public void setVisble(Text visible) {
        labelEstimate_no.setVisible(false);
        labelEstimate_bad.setVisible(false);
        labelEstimate_yes.setVisible(false);
        labelEstimate_perfect.setVisible(false);
        labelEstimate_good.setVisible(false);
        visible.setVisible(true);
    }

    @FXML
    void initialize() {

        try {
            Conn.Conn();
            Conn.GetEmail(doctors_rt);
            Conn.CloseDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Обробка
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

            if(result > 0) {

                //Оценка результата 7-8 лет
                if (age >= 7 & age <= 8 & result >= 21) {
                    setVisble(labelEstimate_no);
                } else if (age >= 7 & age <= 8 & result >= 17 & result < 21) {
                    setVisble(labelEstimate_bad);
                } else if (age >= 7 & age <= 8 & result >= 12 & result < 17) {
                    setVisble(labelEstimate_yes);
                } else if (age >= 7 & age <= 8 & result >= 6.5 & result < 12) {
                    setVisble(labelEstimate_good);
                } else if (age >= 7 & age <= 8 & result < 6.5) {
                    setVisble(labelEstimate_perfect);

                    //Оценка результата 9-10 лет

                } else if (age >= 9 & age <= 10 & result >= 19.5) {
                    setVisble(labelEstimate_no);
                } else if (age >= 9 & age <= 10 & result >= 14.5 & result < 19.5) {
                    setVisble(labelEstimate_bad);
                } else if (age >= 9 & age <= 10 & result >= 10.5 & result < 14.5) {
                    setVisble(labelEstimate_yes);
                } else if (age >= 9 & age <= 10 & result >= 5 & result < 10.5) {
                    setVisble(labelEstimate_good);
                } else if (age >= 9 & age <= 10 & result < 5) {
                    setVisble(labelEstimate_perfect);

                    //Оценка результата 11-12 лет
                } else if (age >= 11 & age <= 12 & result >= 18) {
                    setVisble(labelEstimate_no);
                } else if (age >= 11 & age <= 12 & result >= 13 & result < 18) {
                    setVisble(labelEstimate_bad);
                } else if (age >= 11 & age <= 12 & result >= 8 & result < 13) {
                    setVisble(labelEstimate_yes);
                } else if (age >= 11 & age <= 12 & result >= 3.5 & result < 8) {
                    setVisble(labelEstimate_good);
                } else if (age >= 11 & age <= 12 & result < 3.5) {
                    setVisble(labelEstimate_perfect);

                    //Оценка результата 13-14 лет

                } else if (age >= 13 & age <= 14 & result >= 16.5) {
                    setVisble(labelEstimate_no);
                } else if (age >= 13 & age <= 14 & result >= 12 & result < 16.5) {
                    setVisble(labelEstimate_bad);
                } else if (age >= 13 & age <= 14 & result >= 6.5 & result < 12) {
                    setVisble(labelEstimate_yes);
                } else if (age >= 13 & age <= 14 & result >= 2 & result < 6.5) {
                    setVisble(labelEstimate_good);
                } else if (age >= 13 & age <= 14 & result < 2) {
                    setVisble(labelEstimate_perfect);

                    //Оценка результата 15-18 лет

                } else if (age >= 15 & age <= 18 & result >= 15) {
                    setVisble(labelEstimate_no);
                } else if (age >= 15 & age <= 18 & result >= 10 & result < 15) {
                    setVisble(labelEstimate_bad);
                } else if (age >= 15 & age <= 18 & result >= 5 & result < 10) {
                    setVisble(labelEstimate_yes);
                } else if (age >= 15 & age <= 18 & result >= 0.5 & result < 5) {
                    setVisble(labelEstimate_good);
                } else if (age >= 15 & age <= 18 & result < 0.5) {
                    setVisble(labelEstimate_perfect);
                } else {
                    System.out.println("Error");
                }
            } else {
                labelResult.setText("Error");
            }

        });


        btnSend_rt.setOnAction(event -> {




            String email = doctors_rt.getSelectionModel().getSelectedItem().toString();

            String res = "Results " + td + " from Health Helper : the Ruth test is " + labelResult.getText() +  ".";

            if(id_rt.getText().length()!=0) {
                empty_rt.setVisible(false);
                try {
                    Email.sendMail(email, res, id_rt.getText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    id_rt.setText("");
                    labelInput0.setText("");
                    labelInput2.setText("");
                    labelInput3.setText("");
                    labelInput1.setText("");
                    labelResult.setText("");
                    labelEstimate_no.setText("");
                    labelEstimate_bad.setText("");
                    labelEstimate_yes.setText("");
                    labelEstimate_good.setText("");
                    labelResult.setText("");

            }  else {
                empty_rt.setVisible(true);
                System.out.print("Error");
            }


            try {
                Conn.CloseDB();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


        //Обработка событий по возвращению в главное меню
        btnBackRuthTest.setOnAction(event -> {
            PageLoader("fxml_folder/main.fxml", btnBackRuthTest );
        });



    }

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
        f = !f;
        Thread s1 = new Thread(new Runnable(){ // создаем поток
            public void run(){
                while (f){ // пока секундомер ВКЛ, то будем делать следующее
                    try {
                        Thread.sleep(1000); // пауза в 1 секунду
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(()->
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
