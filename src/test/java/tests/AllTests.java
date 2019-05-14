package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sample.model.Cars;

public class AllTests {

    @Test
    void TaxedCostTest(){
        Cars car = new Cars("ACF-475", "Suzuki", "Vitara", "diesel", 1000, "N", "N", "N", "N");
        assertEquals(1270, car.koltsegwithtax(car));
    }

    @Test
    void AutoTest(){
        Cars car = new Cars("XCG-324", "Opel", "Astra","benzin",870,"2019-05-14","2019-05-18","Varga Zoltán","+36504569785");
        assertEquals("XCG-324", car.getRendszam());
        assertEquals("Opel", car.getMarka());
        assertEquals("Astra", car.getTipus());
        assertEquals("benzin", car.getMotor());
        assertEquals(870, car.getKoltseg());
        assertEquals("2019-05-14", car.getKezdet());
        assertEquals("2019-05-18", car.getLeadas());
        assertEquals("Varga Zoltán", car.getNev());
        assertEquals("+36504569785", car.getTelefon());
    }

    @Test
    void RentalDaysTest(){
        Cars car = new Cars("VLF-754", "Opel", "Vectra","benzin",970,"2019-05-14","2019-05-18","Kiss Gábor","+36504625753");
        assertEquals(4, car.idotartam(car));
    }

    @Test
    void RentalCostTest(){
        Cars car = new Cars("CPR-784", "Audi", "A4","benzin",1400,"2019-05-10","2019-05-20","Kiss Márton","+36509875315");
        assertEquals(14000, car.osszkoltseg(car));
    }
}
