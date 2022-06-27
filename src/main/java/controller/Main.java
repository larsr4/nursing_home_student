package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
   public static Stage primaryStage;

    /**
     * Main auslagern & nur Main anlaufen lassen und den Inhalt von Main in neue Klasse, welche mit Loginkotroller verbunden ist.
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        loginPage();
    }

    public void loginPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        primaryStage.setTitle("User Login NHPlus");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}