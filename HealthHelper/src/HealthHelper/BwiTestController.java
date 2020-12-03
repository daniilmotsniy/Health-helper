package HealthHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BwiTestController implements Calculable {
    @FXML
    private TextField heightFieldBwi;
    @FXML
    private TextField weightFieldBwi;
    @FXML
    private Text resultBwiText;
    @FXML
    private Text perfectBwiText;
    @FXML
    private Button resultButtonBwi;
    @FXML
    private Label class_estimate_bwi_a;
    @FXML
    private Label class_estimate_bwi_b;
    @FXML
    private Label class_estimate_bwi_c;
    @FXML
    private Label class_estimate_bwi_d;
    @FXML
    Button btnBackBwiTest;

    @FXML
    void initialize() {
        // Подсчет результата ИМТ
        resultButtonBwi.setOnAction(event -> {
            if(heightFieldBwi.getText().equals("") || weightFieldBwi.getText().equals("")){
                resultBwiText.setText("Error");
            } else {
                double height = Double.parseDouble(heightFieldBwi.getText());
                double weight = Double.parseDouble(weightFieldBwi.getText());
                double resultBwi = weight / (height * height);
                resultBwiText.setText(Double.toString(resultBwi));

                if (resultBwi <= 16) {
                    class_estimate_bwi_a.setVisible(true);
                    class_estimate_bwi_b.setVisible(false);
                    class_estimate_bwi_c.setVisible(false);
                    class_estimate_bwi_d.setVisible(false);
                } else if (resultBwi > 16 & resultBwi <= 25) {
                    class_estimate_bwi_a.setVisible(false);
                    class_estimate_bwi_b.setVisible(true);
                    class_estimate_bwi_c.setVisible(false);
                    class_estimate_bwi_d.setVisible(false);
                } else if (resultBwi > 25 & resultBwi <= 30) {
                    class_estimate_bwi_a.setVisible(false);
                    class_estimate_bwi_b.setVisible(false);
                    class_estimate_bwi_c.setVisible(true);
                    class_estimate_bwi_d.setVisible(false);
                } else if (resultBwi > 30 & resultBwi <= 60) {
                    class_estimate_bwi_a.setVisible(false);
                    class_estimate_bwi_b.setVisible(false);
                    class_estimate_bwi_c.setVisible(false);
                    class_estimate_bwi_d.setVisible(true);
                } else {
                    System.out.println("Error");
                }

                double perfectBwi = calc(height, weight, 21);
                perfectBwiText.setText(Double.toString(perfectBwi));
            }
        });

        //Обработка событий по возвращению в главное меню
        btnBackBwiTest.setOnAction(event -> {
            Main.PageLoader("fxml_folder/main.fxml", btnBackBwiTest);
        });
    }

    @Override
    public double calc(double input1, double input2, double bwi_constant) {
        return (input1 * input2) * bwi_constant;
    }
}
