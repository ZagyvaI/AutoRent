package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sample.model.Cars;

public class AllTests {

    @Test
    void taxedCostTest(){
        Cars car = new Cars("ACF-475", "Suzuki", "Vitara", "diesel", 1000, "N", "N", "N", "N");
        assertEquals(1270, car.koltsegwithtax(car));
    }

    @Test
    void taxedCostTest2(){
        Cars car = new Cars("GJC-754", "Mercedes", "CLK 500", "diesel", 2000, "N", "N", "N", "N");
        assertEquals(2540, car.koltsegwithtax(car));
    }

    @Test
    void autoTest(){
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
    void autoTest2(){
        Cars car = new Cars("PGJ-582", "Fiat", "Punto","benzin",560,"2019-05-04","2019-05-20","Soós Péter","+36307543128");
        assertEquals("PGJ-582", car.getRendszam());
        assertEquals("Fiat", car.getMarka());
        assertEquals("Punto", car.getTipus());
        assertEquals("benzin", car.getMotor());
        assertEquals(560, car.getKoltseg());
        assertEquals("2019-05-04", car.getKezdet());
        assertEquals("2019-05-20", car.getLeadas());
        assertEquals("Soós Péter", car.getNev());
        assertEquals("+36307543128", car.getTelefon());
    }

    @Test
    void rentalDaysTest(){
        Cars car = new Cars("VLF-754", "Opel", "Vectra","benzin",970,"2019-05-14","2019-05-18","Kiss Gábor","+36504625753");
        assertEquals(4, car.idotartam(car));
    }

    @Test
    void rentalDaysTest2(){
        Cars car = new Cars("ADS-451", "Opel", "Meriva","disel",650,"2019-05-01","2019-05-25","Nagy Zsolt","+36502451697");
        assertEquals(24, car.idotartam(car));
    }

    @Test
    void rentalCostTest(){
        Cars car = new Cars("CPR-784", "Audi", "A4","benzin",1400,"2019-05-10","2019-05-20","Kiss Márton","+36509875315");
        assertEquals(14000, car.osszkoltseg(car));
    }

    @Test
    void rentalCostTest2(){
        Cars car = new Cars("IDC-684", "BMW", "M300","benzin",1750,"2019-05-2","2019-05-24","Szabó Gábor","+36506551697");
        assertEquals(38500, car.osszkoltseg(car));
    }

    @Test
    void rentalCostTest3(){
        Cars car = new Cars("PDG-451", "Suzuki", "Ignis","benzin",920,"2019-05-16","2019-05-18","Barna Péter","+36509765364");
        assertNotEquals(920, car.osszkoltseg(car));
    }


}
