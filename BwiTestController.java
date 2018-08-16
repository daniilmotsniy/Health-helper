package HealthHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BwiTestController extends MainController{

    @FXML
    private TextField heightFieldBwi;

    @FXML
    private TextField weightFieldBwi;

    @FXML
    private Text resultBwiText;

    @FXML
    private Text estimateBwiText;

    @FXML
    private Text perfectBwiText;

    @FXML
    private Button resultButtonBwi;

    @FXML
    Button btnBackBwiTest;

    @FXML
    void initialize() {
        // Подсчет результата ИМТ
        resultButtonBwi.setOnAction(event -> {

            Double heigth = Double.parseDouble(heightFieldBwi.getText());
            Double weight = Double.parseDouble(weightFieldBwi.getText());
            Double resultBwi = weight / (heigth * heigth);
            resultBwiText.setText(resultBwi.toString());

            if (resultBwi <= 16) {
                estimateBwiText.setText("Виражений дефіцит маси");
            } else if (resultBwi > 16 & resultBwi <= 18.5) {
                estimateBwiText.setText("Недостатня маса тіла");
            } else if (resultBwi > 18.5 & resultBwi <= 25) {
                estimateBwiText.setText("Норма");
            } else if (resultBwi > 25 & resultBwi <= 30) {
                estimateBwiText.setText("Предожиріння");
            } else if (resultBwi > 30 & resultBwi <= 35) {
                estimateBwiText.setText("Ожиріння першого ступеня");
            } else if (resultBwi > 35 & resultBwi <= 40) {
                estimateBwiText.setText("Ожиріння другого ступеня");
            } else if (resultBwi > 40) {
                estimateBwiText.setText("Ожиріння третього ступеня");
            } else {
                estimateBwiText.setText("Помилка");
            }

            Double perfectBwi = (heigth * heigth) * 21;
            perfectBwiText.setText(perfectBwi.toString());
        });

        //Обработка событий по возвращению в главное меню
        btnBackBwiTest.setOnAction(event -> {
            PageLoader("fxml/main.fxml", btnBackBwiTest);
        });
    }
}
