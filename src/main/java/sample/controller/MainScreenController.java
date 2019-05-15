package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.pmw.tinylog.Logger;

/**
 * This represents the program's main menu controller.
 */
public class MainScreenController {

    /**
     * This method opens the "Renting Screen" Window, on a click event.
     * @param event A button press down.
     */
    @FXML
    public void rentClick(ActionEvent event) {
        Logger.info("Opening the Renting Screen Window...");
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/rentScreen.fxml"));

            stage.setTitle("Renting Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            Logger.error("IOException", new IOException(e));
        }

    }

    /**
     * This method opens the "View Cars Screen" Window, on a click event.
     * @param event A button press down.
     */
    @FXML
    public void viewClick(ActionEvent event) {
        Logger.info("Opening the View Cars Screen Window...");
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/viewScreen.fxml"));

            stage.setTitle("View Cars Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            Logger.error("IOException", new IOException(e));
        }
    }

    /**
     * This method opens the "Admin Screen" Window, on a click event.
     * @param event A button press down.
     */
    @FXML
    public void adminClick(ActionEvent event) {
        Logger.info("Opening the Admin Screen Window...");
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminScreen.fxml"));

            stage.setTitle("Admin Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            Logger.error("IOException", new IOException(e));
        }
    }
}
