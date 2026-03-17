import java.util.HashMap;

/**
 * ===============================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ===============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Demonstrates centralized inventory using HashMap.
 *
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Inventory Initialization\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Update availability
        System.out.println("\nUpdating Inventory...\n");
        inventory.updateAvailability("Single", -1); // booking
        inventory.updateAvailability("Suite", +1);  // cancellation

        // Display updated inventory
        inventory.displayInventory();
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Manages room availability using a centralized HashMap.
 *
 * @version 3.1
 */
class RoomInventory {

    /** Stores room type -> availability */
    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes inventory
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    /**
     * Get availability of a room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Update availability safely
     */
    public void updateAvailability(String roomType, int change) {
        int current = getAvailability(roomType);
        int updated = current + change;

        if (updated >= 0) {
            inventory.put(roomType, updated);
        } else {
            System.out.println("Cannot reduce below zero for " + roomType);
        }
    }

    /**
     * Display full inventory
     */
    public void displayInventory() {
        System.out.println("Current Room Inventory:");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Rooms Available: " + inventory.get(roomType));
        }
    }
}