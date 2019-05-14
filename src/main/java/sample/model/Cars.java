package sample.model;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * This class represents an individual car, to be created.
 */
@Entity
public class Cars {

    /**
     * The id of the car, unique, and automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * the License Plate Number of the car.
     */
    @Column(name = "rendszam")
    private String rendszam;

    /**
     * The Manufacturer of the car.
     */
    @Column(name = "marka")
    private String marka;

    /**
     * The Model type of the car.
     */
    @Column(name = "tipus")
    private String tipus;

    /**
     * The Engine type of the car.
     */
    @Column(name = "motor")
    private String motor;

    /**
     * The per day cost of the car.
     */
    @Column(name = "koltseg")
    private int koltseg;

    /**
     * The beginnig date of the rent.
     */
    @Column(name = "kezdet")
    private String kezdet;

    /**
     * The ending date of the rent.
     */
    @Column(name = "leadas")
    private String leadas;

    /**
     * The renting person's name.
     */
    @Column(name = "nev")
    private String nev;

    /**
     * The renting person's phone number.
     */
    @Column(name = "telefon")
    private String telefon;

    /**
     * An empty constructor used by JPA.
     */
    public Cars(){
    }

    /**
     * The parameterized constructor, to sets up a car element.
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
    public Cars(String rendszam, String marka, String tipus, String motor, int koltseg, String kezdet, String leadas, String nev, String telefon) {
        this.rendszam = rendszam;
        this.marka = marka;
        this.tipus = tipus;
        this.motor = motor;
        this.koltseg = koltseg;
        this.kezdet = kezdet;
        this.leadas = leadas;
        this.nev = nev;
        this.telefon = telefon;
    }

    /**
     * This method returns the per day cost of the specified car, but with tax.
     * @param car a specified, individual car.
     * @return returns the taxed per day cost.
     */
    public int koltsegwithtax(Cars car){
        return (int)(Math.round(car.getKoltseg() * 1.27));
    }

    /**
     * This method returns the number of days between the beginning, and the ending day of the rental.
     * @param car a specified, individual car.
     * @return returns the number of the rental days.
     */
    public int idotartam(Cars car){
        String[] kezdonap = car.getKezdet().split("-");
        String[] lejaratinap = car.getLeadas().split("-");

        return Math.abs(Integer.parseInt(kezdonap[2]) - Integer.parseInt(lejaratinap[2]));
    }

    /**
     * This method returns the total cost (without tax) of the rent.
     * @param car a specified, individual car.
     * @return returns the total cost.
     */
    public int osszkoltseg(Cars car){
        String[] kezdonap = car.getKezdet().split("-");
        String[] lejaratinap = car.getLeadas().split("-");

        return (car.getKoltseg()) * (Math.abs(Integer.parseInt(kezdonap[2]) - Integer.parseInt(lejaratinap[2])));
    }

    /**
     * Getter of the id parameter.
     * @return returns the id parameter of the car.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the id parameter.
     * @param id Sets the valuse id parameter of the car.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the License Plate Number parameter.
     * @return returns the License Plate Number of the car.
     */
    public String getRendszam() {
        return rendszam;
    }

    /**
     * Setter if the License Plate Number parameter.
     * @param rendszam Sets the value of License Plate Number parameter.
     */
    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    /**
     * Getter of the Manufacturer parameter.
     * @return returns the Manufacturer of the car.
     */
    public String getMarka() {
        return marka;
    }

    /**
     * Setter of the Manufacturer parameter.
     * @param marka Sets the value of Manufacturer parameter.
     */
    public void setMarka(String marka) {
        this.marka = marka;
    }

    /**
     * Getter of the Model parameter.
     * @return returns the Model of the car.
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Setter of the Model parameter.
     * @param tipus Sets the value of Model parameter.
     */
    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    /**
     * Getter of the Engine type parameter.
     * @return returns the Engine type of the car.
     */
    public String getMotor() {
        return motor;
    }

    /**
     * Setter of the Engine type parameter.
     * @param motor Sets the value of Engine type parameter.
     */
    public void setMotor(String motor) {
        this.motor = motor;
    }

    /**
     * Getter of the per day cost parameter.
     * @return returns the per day cost of the car.
     */
    public int getKoltseg() {
        return koltseg;
    }

    /**
     * Setter of the per day cost parameter.
     * @param koltseg Sets the value of per day cost parameter.
     */
    public void setKoltseg(int koltseg) {
        this.koltseg = koltseg;
    }

    /**
     * Getter of the rental beginning date parameter.
     * @return returns the rental beginning date of the car.
     */
    public String getKezdet() {
        return kezdet;
    }

    /**
     * Setter of the rental beginning date parameter.
     * @param kezdet Sets the value of rental beginning date parameter.
     */
    public void setKezdet(String kezdet) {
        this.kezdet = kezdet;
    }

    /**
     * Getter of the rental ending date parameter.
     * @return returns the rental ending date of the car.
     */
    public String getLeadas() {
        return leadas;
    }

    /**
     * Setter of the rental ending date parameter.
     * @param leadas Sets the value of rental ending date parameter.
     */
    public void setLeadas(String leadas) {
        this.leadas = leadas;
    }

    /**
     * Getter of the name parameter.
     * @return returns the name of the person.
     */
    public String getNev() {
        return nev;
    }

    /**
     * Setter of the name parameter.
     * @param nev Sets the value of name parameter.
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Getter of the phone number parameter.
     * @return returns the phone number of the person.
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Setter of the phone number parameter.
     * @param telefon Sets the value of phone number parameter.
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
