package HealthHelper;

import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class Send extends MainController {

    Date td  = new Date();

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
            Conn.Conn();
            Conn.GetEmail(doctors);
            Conn.CloseDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnBackSend.setOnAction(event -> {
            PageLoader("fxml_folder/main.fxml", btnBackSend);

        });

        btnSend.setOnAction(event -> {

            try {
                Conn.Conn();
                Conn.CloseDB();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String email = doctors.getSelectionModel().getSelectedItem().toString();

            String res = "Results " + td + " from Health Helper : temperature " + temperature.getText() + ", " +  "pulse " + pulse.getText() + ", " + "pressure " + pressure.getText() +  ", breathing rate " + ft.getText() + ", " +  "blood glucose " + gk.getText() +  ".";

            if(id.getText().length()!=0) {

                empty.setVisible(false);

                try {
                    Conn.Conn();
                    Conn.WriteDBT(temperature.getText(), pulse.getText(), pressure.getText());
                    Email.sendMail(email, res, id.getText());
                    Conn.CloseDB();

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
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
