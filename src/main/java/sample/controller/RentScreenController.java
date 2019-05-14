package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.*;

import sample.model.Cars;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.pmw.tinylog.Logger;

/**
 * This class shows the avaiable cars to rent, and gives the possiblitiy to rent a car.
 */
public class RentScreenController implements Initializable {

    /**
     * A TextField, where the renting person's name is specified.
     */
    @FXML
    TextField AddName;

    /**
     * A TextField, where the renting person's phone number is specified.
     */
    @FXML
    TextField AddPhone;

    /**
     * A DatePicker, where the beginning date of rent is specified.
     */
    @FXML
    DatePicker AddDateFrom;

    /**
     * A DatePicker, where the ending date of rent is specified.
     */
    @FXML
    DatePicker AddDateTo;

    /**
     * The main element of the table, which holds the avaiable car list.
     */
    @FXML
    private TableView<Cars> RentTable;

    /**
     * A TableColumn that holds the License Plate Number of the cars.
     */
    @FXML
    public TableColumn<Cars, String> RentPlateNumber;

    /**
     * A TableColumn that holds the Manufacturer of the cars.
     */
    @FXML
    public TableColumn<Cars, String> RentManufact;

    /**
     * A TableColumn that holds the model name of the cars.
     */
    @FXML
    public TableColumn<Cars, String> RentModel;

    /**
     * A TableColumn that holds the Engine type of the cars.
     */
    @FXML
    public TableColumn<Cars, String> RentEngine;

    /**
     * A TableColumn that holds the Rental cost of one day for the cars.
     */
    @FXML
    public TableColumn<Cars, Integer> RentCost;

    /**
     * Parameter used by JPA, to estabilish the connection.
     */
    private static EntityManagerFactory emf;

    /**
     * Parameter used by JPA, to estabilish the connection.
     */
    private static EntityManager em;

    /**
     * After the window opens up, this will initialize the database, to be shown in the table.
     * @param url A location
     * @param resourceBundle Resource
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Logger.info("Initializing database...");
        LoadDatabase();
    }

    /**
     * This method represents the button, which takes back to the Main Screen Window.
     * @param event A button press down.
     */
    @FXML
    public void MainClickRent(ActionEvent event) {
        Logger.info("Returning to the Main Screen...");
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));

            stage.setTitle("Main Screen");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException e){
            Logger.error("IOException", new IOException(e));
        }

    }

    /**
     * This method represents the button, which saves the specified informations of the renting person.
     * @param event A button press down.
     */
    @FXML
    public void RentSelectedClick(ActionEvent event) {
        Logger.info("Updating the table, with the renting person's specified informations...");

        try {
            emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
            em = emf.createEntityManager();

            em.getTransaction().begin();
            String selectedCar = RentTable.getSelectionModel().getSelectedItem().getRendszam();

            TypedQuery<Cars> keres = em.createQuery("SELECT s FROM Cars s Where s.rendszam='"+selectedCar+"'", Cars.class);
            List<Cars> eredmeny = keres.getResultList();

            eredmeny.get(0).setNev(AddName.getText());
            eredmeny.get(0).setTelefon(AddPhone.getText());
            eredmeny.get(0).setKezdet(AddDateFrom.getValue().toString());
            eredmeny.get(0).setLeadas(AddDateTo.getValue().toString());
            em.getTransaction().commit();

            em.close();
            emf.close();
            LoadDatabase();
            AddName.clear();
            AddPhone.clear();
            AddDateFrom.setValue(null);
            AddDateTo.setValue(null);
        }
        catch (RuntimeException e){
            Logger.error("RuntimeException", new RuntimeException(e));
        }

    }

    /**
     * This method is called, when the window is opened up, and loads the data from the database.
     */
    private void LoadDatabase(){
        Logger.info("Processing...");
        try {
            emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
            em = emf.createEntityManager();
            String available = "N";//kölcsönözhető-e(ha N akkor igen)

            TypedQuery<Cars> keres = em.createQuery("SELECT s FROM Cars s Where s.kezdet='"+available+"'", Cars.class);
            List<Cars> eredmeny = keres.getResultList();

            RentPlateNumber.setCellValueFactory(new PropertyValueFactory<>("rendszam"));
            RentManufact.setCellValueFactory(new PropertyValueFactory<>("marka"));
            RentModel.setCellValueFactory(new PropertyValueFactory<>("tipus"));
            RentEngine.setCellValueFactory(new PropertyValueFactory<>("motor"));
            RentCost.setCellValueFactory(new PropertyValueFactory<>("koltseg"));

            ObservableList<Cars> rekordok = FXCollections.observableArrayList();
            for (int i = 0; i < eredmeny.size(); i++) {
                rekordok.add(new Cars(
                        eredmeny.get(i).getRendszam(),
                        eredmeny.get(i).getMarka(),
                        eredmeny.get(i).getTipus(),
                        eredmeny.get(i).getMotor(),
                        eredmeny.get(i).getKoltseg(),
                        eredmeny.get(i).getKezdet(),
                        eredmeny.get(i).getLeadas(),
                        eredmeny.get(i).getNev(),
                        eredmeny.get(i).getTelefon()
                ));
            }
            RentTable.setItems(rekordok);

            em.close();
            emf.close();
        }
        catch (RuntimeException e){
            Logger.error("RuntimeException", new RuntimeException(e));
        }
    }
}
