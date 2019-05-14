package sample.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;


/**
 * <p>Main class.</p>
 *
 * @author Zagyva István
 * @version 1.0
 */
public class Main extends Application {

    /**
     * This method opens the main menu screen, this is the application's start point.
     * @param primaryStage main screen menu
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Logger.info("Application starting in progress...");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
        }
        catch (IOException e){
            Logger.error("IOException", new IOException(e));
        }

        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(root, 490, 290));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Call for main method.
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
