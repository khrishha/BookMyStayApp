import java.util.LinkedList;
import java.util.Queue;

/**
 * ===============================================================
 * MAIN CLASS - UseCase5BookingRequestQueue
 * ===============================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * Stores booking requests in FIFO order using Queue.
 *
 * @version 5.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue (FIFO)\n");

        // Create booking queue
        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single"));
        bookingQueue.addRequest(new Reservation("Bob", "Double"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite"));
        bookingQueue.addRequest(new Reservation("David", "Single"));

        // Display queue
        bookingQueue.displayQueue();
    }
}


/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
 *
 * Represents a booking request.
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

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}


/**
 * ===============================================================
 * CLASS - BookingQueue
 * ===============================================================
 *
 * Manages booking requests using FIFO Queue.
 */
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Add booking request to queue
     */
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    /**
     * Display all booking requests in order
     */
    public void displayQueue() {
        System.out.println("\nCurrent Booking Queue:\n");

        for (Reservation r : queue) {
            r.display();
        }
    }
}