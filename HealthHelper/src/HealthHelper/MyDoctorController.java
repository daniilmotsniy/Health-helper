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

public class MyDoctorController extends MenuController {

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

    private static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void initialize() {

        try {
            Conn.conn();
            Conn.createDocTable();
            Conn.conn();
            Conn.readDocIntoComboBox(doctors);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        btnBackDoctor.setOnAction(event -> {
            Main.PageLoader("fxml_folder/main.fxml", btnBackDoctor );
            try {
                Conn.closeDB();
            } catch (ClassNotFoundException | SQLException e) {
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
               Conn.conn();
               Conn.writeDocTable(lastname.getText(), email.getText());
               Conn.readDocIntoComboBox(doctors);
               Conn.closeDB();
           } catch (ClassNotFoundException | SQLException e) {
               e.printStackTrace();
           }

           lastname.setText("");
           email.setText("");

        });

    }
}






