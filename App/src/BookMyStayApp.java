import java.util.HashMap;

/**
 * ===============================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ===============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * Allows guests to view available rooms without
 * modifying system state.
 *
 * @version 4.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Search Results\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Perform search (read-only)
        SearchService searchService = new SearchService(inventory);
        searchService.displayAvailableRooms();
    }
}


/**
 * ===============================================================
 * CLASS - SearchService
 * ===============================================================
 *
 * Handles read-only search operations.
 *
 * @version 4.1
 */
class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays only available rooms (availability > 0)
     */
    public void displayAvailableRooms() {

        System.out.println("Available Rooms:\n");

        // Room objects (domain model)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        displayIfAvailable("Single", single);
        displayIfAvailable("Double", doubleRoom);
        displayIfAvailable("Suite", suite);
    }

    /**
     * Helper method to check availability and display
     */
    private void displayIfAvailable(String type, Room room) {
        int available = inventory.getAvailability(type);

        if (available > 0) {
            System.out.println(type + " Room:");
            room.displayRoomDetails();
            System.out.println("Available: " + available);
            System.out.println();
        }
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Centralized inventory using HashMap.
 *
 * @version 4.1
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    /**
     * Read-only access (NO modification)
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


/**
 * ===============================================================
 * ABSTRACT CLASS - Room
 * ===============================================================
 *
 * Represents a generic hotel room.
 *
 * @version 4.1
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}


/**
 * ===============================================================
 * CLASS - SingleRoom
 * ===============================================================
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}


/**
 * ===============================================================
 * CLASS - DoubleRoom
 * ===============================================================
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}


/**
 * ===============================================================
 * CLASS - SuiteRoom
 * ===============================================================
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}