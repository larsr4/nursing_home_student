package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    //statische Stage, damit diese auch vom LoginController aus aufgerufen werden kann.
    public static Stage primaryStage;

    /**
     * startet das Programm nach dem launch Befehl in der main. Hier wird loginPage aufgerufen und die Stage gesetzt.
     * @param primaryStage setzt this.primaryStage auf Übergabeparameter zur instanziierung.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        loginPage();
    }

    /**
     * die Login GUI von der fxml datei LoginView wird aufgerufen und die Scene wird gesetzt und angezeigt.
     * @throws IOException
     */
    public void loginPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        primaryStage.setTitle("User Login NHPlus");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    /**
     * main Methode der Anwendung. Startet diese Anwendung, wenn ausgeführt.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}