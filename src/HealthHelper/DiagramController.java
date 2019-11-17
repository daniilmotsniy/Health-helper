package HealthHelper;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.sql.SQLException;

public class DiagramController extends MainController {

    @FXML
    Button btnBack;

    @FXML
    private BarChart<String, Integer> diagram;

    @FXML
    void initialize() {

        //Обрабка подій для повернення в головне меню
        btnBack.setOnAction(event -> {
            PageLoader("fxml_folder/main.fxml", btnBack);
        });

        XYChart.Series<String, Integer> pulse = new XYChart.Series<>();
        XYChart.Series<String, Integer> temp = new XYChart.Series<>();
        XYChart.Series<String, Integer> press = new XYChart.Series<>();
        //XYChart.Data для кожного показника.

        pulse.setName("Pulse");
        temp.setName("Temperature");
        press.setName("Pressure");

        // Додаємо їх за допомогою метода FillDiagram.
        try {
            Conn.Conn();
            Conn.CreateDBT();
            Conn.FillDiagram(pulse, temp, press);
            Conn.CloseDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Відображаємо діаграму
        diagram.getData().addAll(pulse, temp, press);


    }

}




