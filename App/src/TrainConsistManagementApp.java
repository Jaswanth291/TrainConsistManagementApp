import java.util.*;

// Bogie (Coach) class
class Bogie {
    String bogieId;
    String type; // Sleeper / AC / General
    int capacity;

    Bogie(String bogieId, String type, int capacity) {
        this.bogieId = bogieId;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return bogieId + " (" + type + ", Cap:" + capacity + ")";
    }
}

// Train class using ArrayList
class Train {
    String trainName;
    ArrayList<Bogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        bogies = new ArrayList<>();
    }

    // Add bogie at end
    void addBogie(Bogie b) {
        bogies.add(b);
        System.out.println("Bogie added: " + b);
    }

    // Add bogie at specific position
    void addBogieAt(int index, Bogie b) {
        if (index >= 0 && index <= bogies.size()) {
            bogies.add(index, b);
            System.out.println("Bogie added at position " + index + ": " + b);
        } else {
            System.out.println("Invalid position!");
        }
    }

    // Remove bogie
    void removeBogie(String bogieId) {
        Iterator<Bogie> it = bogies.iterator();
        while (it.hasNext()) {
            Bogie b = it.next();
            if (b.bogieId.equals(bogieId)) {
                it.remove();
                System.out.println("Bogie removed: " + bogieId);
                return;
            }
        }
        System.out.println("Bogie not found!");
    }

    // Display all bogies
    void displayBogies() {
        System.out.println("\nTrain: " + trainName);
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }
}

// Main class
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        // Add bogies
        train.addBogie(new Bogie("S1", "Sleeper", 72));
        train.addBogie(new Bogie("S2", "Sleeper", 72));
        train.addBogie(new Bogie("A1", "AC", 50));

        // Insert at specific position
        train.addBogieAt(1, new Bogie("G1", "General", 100));

        // Display
        train.displayBogies();

        // Remove a bogie
        train.removeBogie("S2");

        // Display again
        train.displayBogies();
    }
}