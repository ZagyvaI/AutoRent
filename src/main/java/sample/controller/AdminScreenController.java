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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Cars;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.pmw.tinylog.Logger;

/**
 * This class represents the Admin mode of the program, where cars can be added, or removed from the database.
 */
public class AdminScreenController implements Initializable {

    /**
     * The main element of the table, which holds the car list.
     */
    @FXML
    private TableView<Cars> AdminTable;

    /**
     * A TableColumn that holds the License Plate Number of the cars.
     */
    @FXML
    public TableColumn<Cars, String> AdminPlateNumber;

    /**
     * A TableColumn that holds the Manufacturer of the cars.
     */
    @FXML
    public TableColumn<Cars, String> AdminManufact;

    /**
     * A TableColumn that holds the Model name of the cars.
     */
    @FXML
    public TableColumn<Cars, String> AdminModel;

    /**
     * A TableColumn that holds the Engine type of the cars.
     */
    @FXML
    public TableColumn<Cars, String> AdminEngine;

    /**
     * A TableColumn that holds the Rental cost of one day for the cars.
     */
    @FXML
    public TableColumn<Cars, Integer> AdminCost;

    /**
     * A TableColumn, where the beginning date of rent is specified.
     */
    @FXML
    public TableColumn<Cars, String> AdminFrom;

    /**
     * A TableColumn, where the ending date of rent is specified.
     */
    @FXML
    public TableColumn<Cars, String> AdminTo;

    /**
     * A TableColumn, where the renting person's name is specified.
     */
    @FXML
    public TableColumn<Cars, String> AdminName;

    /**
     * A TableColumn, where the renting person's phone number is specified.
     */
    @FXML
    public TableColumn<Cars, String> AdminPhone;

    /**
     * A TextField, specifies the License Plate Number of the car, which car is to be added to the Database.
     */
    @FXML
    TextField AdminAddPlateNumber;

    /**
     * A TextField, specifies the Manufacturer of the car, which car is to be added to the Database.
     */
    @FXML
    TextField AdminAddManufact;

    /**
     * A TextField, specifies the Model of the car, which car is to be added to the Database.
     */
    @FXML
    TextField AdminAddModel;

    /**
     * A TextField, specifies the Engine type of the car, which car is to be added to the Database.
     */
    @FXML
    TextField AdminAddEngine;

    /**
     * A TextField, specifies the Rental cost per day of the car, which car is to be added to the Database.
     */
    @FXML
    TextField AdminAddCost;


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
    public void MainClickAdmin(ActionEvent event) {
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
     * This method represents the button which deletes the selected car from the database, then reloads the table.
     * @param event A button press down.
     */
    @FXML
    public void AdminDelClick(ActionEvent event) {
        Logger.info("Deleting a car from database...");
        try {
            emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
            em = emf.createEntityManager();

            em.getTransaction().begin();
            String selectedCar = AdminTable.getSelectionModel().getSelectedItem().getRendszam();

            TypedQuery<Cars> keres = em.createQuery("SELECT s FROM Cars s Where s.rendszam='"+selectedCar+"'", Cars.class);
            List<Cars> eredmeny = keres.getResultList();
            Cars deletable = eredmeny.get(0);
            em.remove(deletable);
            em.getTransaction().commit();

            em.close();
            emf.close();
            LoadDatabase();
        }
        catch (RuntimeException e){
            Logger.error("RuntimeException", new RuntimeException(e));
        }
    }

    /**
     * This method representsthe button which adds a new car element to the database.
     * @param event A button press down.
     */
    @FXML
    public void AdminAddClick(ActionEvent event) {
        Logger.info("Addig a car to the database...");
        AddCar(AdminAddPlateNumber.getText(),
                AdminAddManufact.getText(),
                AdminAddModel.getText(),
                AdminAddEngine.getText(),
                Integer.parseInt(AdminAddCost.getText()),
                "N", "N", "N", "N"
        );
        LoadDatabase();
        AdminAddPlateNumber.clear();
        AdminAddManufact.clear();
        AdminAddModel.clear();
        AdminAddEngine.clear();
        AdminAddCost.clear();
    }

    /**
     * This method is called, when the window is opened up, and loads the data from the database.
     */
    @FXML
    private void LoadDatabase() {
        Logger.info("Processing...");
        try {
            emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
            em = emf.createEntityManager();

            TypedQuery<Cars> keres = em.createQuery("SELECT s FROM Cars s", Cars.class);
            List<Cars> eredmeny = keres.getResultList();

            AdminPlateNumber.setCellValueFactory(new PropertyValueFactory<>("rendszam"));
            AdminManufact.setCellValueFactory(new PropertyValueFactory<>("marka"));
            AdminModel.setCellValueFactory(new PropertyValueFactory<>("tipus"));
            AdminEngine.setCellValueFactory(new PropertyValueFactory<>("motor"));
            AdminCost.setCellValueFactory(new PropertyValueFactory<>("koltseg"));
            AdminFrom.setCellValueFactory(new PropertyValueFactory<>("kezdet"));
            AdminTo.setCellValueFactory(new PropertyValueFactory<>("leadas"));
            AdminName.setCellValueFactory(new PropertyValueFactory<>("nev"));
            AdminPhone.setCellValueFactory(new PropertyValueFactory<>("telefon"));

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
            AdminTable.setItems(rekordok);

            em.close();
            emf.close();
        }
        catch (RuntimeException e) {
            Logger.error("RuntimeException", new RuntimeException(e));
        }
    }

    /**
     * This method is called by the AdminAddClick event.
     * @param rendszam String, License Plate Number of the car.
     * @param marka String, the Manufacturer of the car.
     * @param tipus String, the Model type of the car.
     * @param motor String the Engine type of the car.
     * @param koltseg Int, the per day cost of the car.
     * @param kezdet String, the beginning date of rent.
     * @param leadas String, the ending date of the rent.
     * @param nev String, the name of the renter person.
     * @param telefon String, the phone number of the renter person.
     */
    private void AddCar(String rendszam, String marka, String tipus, String motor, int koltseg, String kezdet, String leadas, String nev, String telefon){
        Logger.info("Processing...");
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();

        Cars car = new Cars(rendszam, marka, tipus, motor, koltseg, kezdet, leadas, nev, telefon);

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
