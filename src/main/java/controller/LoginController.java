package controller;

import datastorage.ConnectionBuilder;
import datastorage.JdbcDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;


public class LoginController {
    Main main;

    @FXML
    private TextField username;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event ) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        if (username.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String user = username.getText();
        String password = passwordField.getText();

        JdbcDAO jdbcDao = new JdbcDAO(ConnectionBuilder.getConnection());
        boolean flag = jdbcDao.validate(user, password);

        if (!flag) {
            infoBox("Please enter correct Username and Password", null, "Failed");
        } else {
            infoBox("Correct Username and Password", null, "Success");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}