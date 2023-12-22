import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;

import com.cab.*;

public class InvoiceTest {
    @Test
    void checkFare() {
        CabInvoice ob = new CabInvoice();
        double calculateTotalFare = ob.calculateFare(7, 15, "Normal"); 
        assertEquals(85, calculateTotalFare); 
    }
    
    @Test
    void minFare() {
        CabInvoice ob = new CabInvoice();
        double minTotalFare = ob.calculateFare(2, 5, "Normal"); 
        assertEquals(25, minTotalFare); 
    }
    
    @Test
void multipleRides() {
    CabInvoice ob = new CabInvoice();
    String userId = "1";
    RideRepository ob1 = new RideRepository(userId);
    
    // Adding rides with different distances and times
    ob1.addMultipleRides();
    ob1.listOfRides().add(new Rides(8, 12, "Premium")); // Adding an additional ride

    double totalFare = ob.calculateTotalFare(ob1.listOfRides());
    assertEquals(332, totalFare); 
}

@Test
void checkInvoice() {
    CabInvoice ob = new CabInvoice();
    String userId = "1";
    RideRepository ob1 = new RideRepository(userId);
    ob1.addMultipleRides();
    assertEquals(3, ob.countRides(ob1.listOfRides()));
    assertEquals(200, ob.calculateTotalFare(ob1.listOfRides())); 
   
}



    @Test
    void checkInvoices() {
        CabInvoice ob = new CabInvoice();
        HashMap<String, RideRepository> userRides = new HashMap<>();
        String[] userIds = {"1", "2", "3"};
    
        // Create RideRepository instances with different userIds
        RideRepository ob1 = new RideRepository(userIds[0]);
        ob1.addMultipleRides();
        RideRepository ob2 = new RideRepository(userIds[1]);  // Use different userId here
        ob2.addMultipleRides();
        RideRepository ob3 = new RideRepository(userIds[2]);  // Use different userId here
        ob3.addMultipleRides();
    
        // Populate the userRides map with correct userIds
        userRides.put(userIds[0], ob1);
        userRides.put(userIds[1], ob2);
        userRides.put(userIds[2], ob3);
    
        // Fetch the RideRepository with the correct key ("2")
        RideRepository expectedUser = userRides.get("2");
        List<Rides> rides = expectedUser.listOfRides();
        Double[] cost = {60.0, 35.0, 105.0};
        
        // Iterate through rides and compare the calculated fare with expected cost
        for (int i = 0; i < rides.size(); i++) {
            assertEquals(cost[i], ob.calculateFare(rides.get(i).getDistance(), rides.get(i).getTime(), rides.get(i).getType()));
        }
    }

    @Test
void checkEmptyRides() {
    CabInvoice ob = new CabInvoice();
    String userId = "1";
    RideRepository ob1 = new RideRepository(userId);

    double totalFare = ob.calculateTotalFare(ob1.listOfRides());
    assertEquals(0, totalFare); // No rides added, so the total fare should be 0
}


@Test
void checkAverageWithZeroRides() {
    CabInvoice ob = new CabInvoice();
    String userId = "1";
    RideRepository ob1 = new RideRepository(userId);

    double averageFare = ob.averageFare(ob1.listOfRides());
    assertEquals(0.0, averageFare); // No rides added, so the average fare should be 0.0
}

@Test
void checkNegativeValues() {
    CabInvoice ob = new CabInvoice();
    double calculateTotalFare = ob.calculateFare(-5, -10, "Normal");
    assertEquals(0.0, calculateTotalFare); 
}


    
}
