package HealthHelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class SendController {

    private Date td  = new Date();
    @FXML
    Text empty;
    @FXML
    private Button btnBackSend;
    @FXML
    private Button btnSend;
    @FXML
    private TextField gk;
    @FXML
    private TextField ft;
    @FXML
    private TextField temperature;
    @FXML
    private TextField pulse;
    @FXML
    private TextField pressure;
    @FXML
    TextField id;
    @FXML
    ComboBox doctors;

    @FXML
    void initialize()  {
        try {
            Conn.conn();
            Conn.getDocEmail(doctors);
            Conn.closeDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        btnBackSend.setOnAction(event -> {
            Main.PageLoader("fxml_folder/main.fxml", btnBackSend);
        });

        btnSend.setOnAction(event -> {
            try {
                Conn.conn();
                Conn.closeDB();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            String doc_email = doctors.getSelectionModel().getSelectedItem().toString();

            String res = "Results " + td + " from Health Helper: temperature " + temperature.getText() + ", " +  "pulse " + pulse.getText() + ", " + "pressure " + pressure.getText() +  ", breathing rate " + ft.getText() + ", " +  "blood glucose " + gk.getText() +  ".";

            if(!id.getText().equals("")) {
                empty.setVisible(false);

                try {
                    Conn.conn();

                    //TODO FIX TIME (in one session) AND EMAIL
                    Conn.writeDataTable(Integer.parseInt(id.getText()), Float.parseFloat(temperature.getText()), Float.parseFloat(gk.getText()), Integer.parseInt(pulse.getText()), Integer.parseInt(ft.getText()), pressure.getText(), Calendar.getInstance().getTime().toString());
                    Email.sendMail(doc_email, res, id.getText());
                    Conn.closeDB();

                } catch (ClassNotFoundException | SQLException | MessagingException | IOException e) {
                    e.printStackTrace();
                }

                id.setText("");
                     gk.setText("");
                     ft.setText("");
                     temperature.setText("");
                     pulse.setText("");
                     pressure.setText("");

            }  else {
                empty.setVisible(true);
            }
        });
    }
}
