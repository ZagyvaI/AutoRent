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
 * This class shows in a table all the cars, not depending on ints renting state.
 */
public class ViewScreenController implements Initializable {

    /**
     * The main element of the table, which holds the car list.
     */
    @FXML
    private TableView<Cars> ViewTable;

    /**
     * A TableColumn that holds the License Plate Number of the cars.
     */
    @FXML
    public TableColumn<Cars, String> ViewPlateNumber;

    /**
     * A TableColumn that holds the Manufacturer of the cars.
     */
    @FXML
    public TableColumn<Cars, String> ViewManufact;

    /**
     * A TableColumn that holds the model name of the cars.
     */
    @FXML
    public TableColumn<Cars, String> ViewModel;

    /**
     * A TableColumn that holds the Engine type of the cars.
     */
    @FXML
    public TableColumn<Cars, String> ViewEngine;

    /**
     * A TableColumn that holds the Rental cost of one day for the cars.
     */
    @FXML
    public TableColumn<Cars, Integer> ViewCost;

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
    public void MainClickView(ActionEvent event) {
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

            ViewPlateNumber.setCellValueFactory(new PropertyValueFactory<>("rendszam"));
            ViewManufact.setCellValueFactory(new PropertyValueFactory<>("marka"));
            ViewModel.setCellValueFactory(new PropertyValueFactory<>("tipus"));
            ViewEngine.setCellValueFactory(new PropertyValueFactory<>("motor"));
            ViewCost.setCellValueFactory(new PropertyValueFactory<>("koltseg"));

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
            ViewTable.setItems(rekordok);

            em.close();
            emf.close();
        }
        catch (RuntimeException e) {
            Logger.error("RuntimeException", new RuntimeException(e));
        }

    }

}
