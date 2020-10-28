package HealthHelper;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;

public class DiagramController {
    @FXML
    Button btn_back;
    @FXML
    private LineChart<String, Integer> line_chart;

    private ArrayList<User> data = new ArrayList<>();

    @FXML
    void initialize() {
        try {
            Conn.conn();
            Conn.getUsers(data);
            Conn.closeDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        XYChart.Series<String, Integer> rate = new XYChart.Series<>();
        rate.setName("Rate");
        XYChart.Series<String, Integer> pulse = new XYChart.Series<>();
        pulse.setName("Pulse");
        XYChart.Series<String, Integer> temperature = new XYChart.Series<>();
        temperature.setName("Temperature");
        XYChart.Series<String, Integer> glucose = new XYChart.Series<>();
        glucose.setName("Glucose");

        for (User el : data) {
            String date = el.getDate().substring(4, 16);
            Data data = el.getData();
            temperature.getData().add(new XYChart.Data<>(date,(int) data.getTemperature()));
            glucose.getData().add(new XYChart.Data<>(date, (int) data.getGlucose()));
            pulse.getData().add(new XYChart.Data<>(date, data.getPulse()));
            rate.getData().add(new XYChart.Data<>(date, data.getRate()));
        }

        line_chart.getData().addAll(pulse, rate, temperature, glucose);

        btn_back.setOnAction(event -> {
            Main.PageLoader("fxml_folder/main.fxml", btn_back );
        });
    }
}
