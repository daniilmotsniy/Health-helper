package HealthHelper;

import java.awt.*;
import java.lang.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class MyDoctorController extends MainController {

    @FXML
    private Button btnBackDoctor;
    @FXML
    private Button add;
    @FXML
    private Button find;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    ComboBox doctors;

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @FXML
    void initialize() {

        try {
            Conn.Conn();
            Conn.CreateDB();
            Conn.Conn();
            Conn.ReadDB(doctors);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnBackDoctor.setOnAction(event -> {
            PageLoader("fxml_folder/main.fxml", btnBackDoctor );
            try {
                Conn.CloseDB();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


        find.setOnAction(event -> {
            String lastname = (String) doctors.getValue();
            System.out.println(lastname);
            if(lastname!=null) {
                try {
                    openWebpage(new URI ("https://helsi.me/find/?area&name=" +  URLEncoder.encode(lastname) + "&searchMode=name"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });


       add.setOnAction(event -> {
           try {
               Conn.Conn();
               Conn.WriteDB(lastname.getText(), email.getText());
               Conn.ReadDB(doctors);
               Conn.CloseDB();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (SQLException e) {
               e.printStackTrace();
           }

           lastname.setText("");
           email.setText("");

        });

    }
}






