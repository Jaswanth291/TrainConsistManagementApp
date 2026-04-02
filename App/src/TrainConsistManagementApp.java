import java.util.*;

// Bogie class
class Bogie {
    String bogieId;
    String type;
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

// Train class using HashMap
class Train {
    String trainName;
    HashMap<String, Integer> bogieCapacityMap; // bogieId → capacity

    Train(String trainName) {
        this.trainName = trainName;
        bogieCapacityMap = new HashMap<>();
    }

    // Add bogie
    void addBogie(Bogie b) {
        bogieCapacityMap.put(b.bogieId, b.capacity);
        System.out.println("Added: " + b);
    }

    // Get capacity of a bogie
    void getCapacity(String bogieId) {
        if (bogieCapacityMap.containsKey(bogieId)) {
            System.out.println("Capacity of " + bogieId + ": " + bogieCapacityMap.get(bogieId));
        } else {
            System.out.println("Bogie not found: " + bogieId);
        }
    }

    // Remove bogie
    void removeBogie(String bogieId) {
        if (bogieCapacityMap.remove(bogieId) != null) {
            System.out.println("Removed bogie: " + bogieId);
        } else {
            System.out.println("Bogie not found!");
        }
    }

    // Display all bogies
    void displayAll() {
        System.out.println("\nTrain: " + trainName);
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println(entry.getKey() + " → Capacity: " + entry.getValue());
        }
    }

    // Total capacity
    void totalCapacity() {
        int total = 0;
        for (int cap : bogieCapacityMap.values()) {
            total += cap;
        }
        System.out.println("\nTotal Capacity: " + total);
    }
}

// Main class
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        train.addBogie(new Bogie("S1", "Sleeper", 72));
        train.addBogie(new Bogie("S2", "Sleeper", 72));
        train.addBogie(new Bogie("A1", "AC", 50));
        train.addBogie(new Bogie("G1", "General", 100));

        train.displayAll();

        // Lookup
        train.getCapacity("A1");

        // Remove
        train.removeBogie("S2");

        train.displayAll();

        // Total capacity
        train.totalCapacity();
    }
}