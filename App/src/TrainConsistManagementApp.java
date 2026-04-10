import java.util.*;

// Public class renamed as requested
public class TrainConsistManagementApp {

    // Bogie class representing each train bogie
    static class Bogie {
        private String bogieId;
        private int capacity;
        private String type;

        // Constructor
        public Bogie(String bogieId, int capacity, String type) {
            this.bogieId = bogieId;
            this.capacity = capacity;
            this.type = type;
        }

        // Getter methods
        public String getBogieId() {
            return bogieId;
        }

        public int getCapacity() {
            return capacity;
        }

        public String getType() {
            return type;
        }

        // toString method for displaying bogie details
        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    // Comparator to sort bogies by capacity (Ascending Order)
    static class CapacityComparator implements Comparator<Bogie> {
        @Override
        public int compare(Bogie b1, Bogie b2) {
            return Integer.compare(b1.getCapacity(), b2.getCapacity());
        }
    }

    // Main method
    public static void main(String[] args) {
        // Creating a list of bogies
        List<Bogie> bogies = new ArrayList<>();

        // Adding sample bogie data
        bogies.add(new Bogie("B1", 72, "Sleeper"));
        bogies.add(new Bogie("A1", 50, "AC"));
        bogies.add(new Bogie("G1", 90, "General"));
        bogies.add(new Bogie("S1", 72, "Sleeper"));
        bogies.add(new Bogie("A2", 60, "AC"));

        // Display bogies before sorting
        System.out.println("=== Before Sorting ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Sorting bogies using the Comparator (Ascending Order)
        Collections.sort(bogies, new CapacityComparator());

        System.out.println("\n=== After Sorting (Ascending by Capacity) ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Sorting bogies in Descending Order
        Collections.sort(bogies, new CapacityComparator().reversed());

        System.out.println("\n=== After Sorting (Descending by Capacity) ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Alternative: Sorting using Lambda Expression (Java 8+)
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\n=== Sorted Again Using Lambda (Ascending) ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }
}