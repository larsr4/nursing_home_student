package controller;

import datastorage.ConnectionBuilder;
import datastorage.JdbcDAO;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
     * @throws SQLException wirft bei fehlgeschlagenen Verbindungsaufbau eine SQLException
     */
    @FXML
    public void login() throws SQLException {

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
     * Eine visuelle Ausgabe einer Box mit dem Textinhalt infoMessage, dem Titel title
     * @param infoMessage Text, welcher in der Box stehen wird
     * @param headerText header von der Box
     * @param title Titel der Box
     */
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    /**
     * eine Warnungsbox, welche ausgegeben wird, falls Angaben fehelen.
     * Diese gibt den Typen alertType, den Besitzer der Nachricht owner, den Titel der Box titel und die anzuzeigende Nachricht message aus
     * @param alertType Typ des Alerts
     * @param owner Besitzer der Nachricht
     * @param title Titel der Nachricht
     * @param message Text innerhalb der Box
     */
    private static void showAlert(AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    /**
     * das GUI vom Admin wird aufgerufen. Hier können Einträge bearbeitet, gelöscht, gesperrt oder erstellt werden.
     * @param mainStage das angezeigte GUI vom Typ Stage.
     */
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
    /**
     * das GUI vom User wird aufgerufen. Hier können Einträge bearbeitet, gesperrt oder erstellt werden.
     * @param mainStage das angezeigte GUI vom Typ Stage.
     */
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