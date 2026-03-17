import java.util.*;

/**
 * ===============================================================
 * MAIN CLASS - UseCase6RoomAllocationService
 * ===============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation System\n");

        // Step 1: Create queue (UC5)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));
        bookingQueue.add(new Reservation("David", "Single"));

        // Step 2: Inventory (UC3)
        RoomInventory inventory = new RoomInventory();

        // Step 3: Allocation Service (UC6)
        BookingService bookingService = new BookingService(inventory);

        // Step 4: Process queue (FIFO)
        while (!bookingQueue.isEmpty()) {
            Reservation request = bookingQueue.poll();
            bookingService.processReservation(request);
        }

        // Display final allocation
        bookingService.displayAllocations();
    }
}


/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}


/**
 * ===============================================================
 * CLASS - BookingService
 * ===============================================================
 *
 * Handles allocation + confirmation
 */
class BookingService {

    private RoomInventory inventory;

    // Map roomType -> allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    // Global set to ensure uniqueness
    private Set<String> allRoomIds;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
        allRoomIds = new HashSet<>();
    }

    /**
     * Process reservation (FIFO)
     */
    public void processReservation(Reservation r) {

        String type = r.getRoomType();
        String guest = r.getGuestName();

        System.out.println("Processing: " + guest + " (" + type + ")");

        // Check availability
        if (inventory.getAvailability(type) > 0) {

            // Generate unique room ID
            String roomId = generateRoomId(type);

            // Store allocation
            allocatedRooms.putIfAbsent(type, new HashSet<>());
            allocatedRooms.get(type).add(roomId);
            allRoomIds.add(roomId);

            // Update inventory (atomic step)
            inventory.decrement(type);

            System.out.println("Confirmed → Room ID: " + roomId);
        } else {
            System.out.println("Rejected → No rooms available");
        }

        System.out.println();
    }

    /**
     * Generate unique room ID
     */
    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 1).toUpperCase() + (int)(Math.random() * 100);
        } while (allRoomIds.contains(id)); // ensure uniqueness

        return id;
    }

    /**
     * Display final allocations
     */
    public void displayAllocations() {

        System.out.println("Final Room Allocations:\n");

        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " Rooms: " + allocatedRooms.get(type));
        }
    }
}