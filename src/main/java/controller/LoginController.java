package controller;

import datastorage.ConnectionBuilder;
import datastorage.JdbcDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    /**
     * diese Funktion ueberprueft, ob die uebergebenen Einlogdaten valide sind.
     * falls Login Daten falsch -> visuelle Ausgabe.
     * wenn logindaten richtig -> entweder NormalUser GUI ansonsten, wenn User= Admin -> AdminUser GUI
     * @param event
     * @throws SQLException
     */
    @FXML
    public void login(ActionEvent event) throws SQLException {

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
            if(jdbcDao.Admin(user,password)) {
                mainAdminWindow(Main.primaryStage);
            }else{
               mainUserWindow(Main.primaryStage);
            }
        }
    }

    /**
     * 
     * @param infoMessage
     * @param headerText
     * @param title
     */
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

    public void mainAdminWindow(Stage mainStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            mainStage.setTitle("NHPlus");
            mainStage.setScene(scene);
            mainStage.setResizable(true);
            mainStage.show();

            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void mainUserWindow(Stage mainStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowUserView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            mainStage.setTitle("NHPlus");
            mainStage.setScene(scene);
            mainStage.setResizable(true);
            mainStage.show();

            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}