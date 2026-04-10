import java.util.Scanner;

public class TrainConsistManagementApp {

    // Bogie class representing each train bogie
    static class Bogie {
        private String bogieId;
        private int capacity;
        private String type;

        public Bogie(String bogieId, int capacity, String type) {
            this.bogieId = bogieId;
            this.capacity = capacity;
            this.type = type;
        }

        public String getBogieId() {
            return bogieId;
        }

        public int getCapacity() {
            return capacity;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    // 🔍 Linear Search Method
    public static int linearSearch(Bogie[] bogies, String targetId) {
        for (int i = 0; i < bogies.length; i++) {
            if (bogies[i].getBogieId().equalsIgnoreCase(targetId)) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating an array of bogies
        Bogie[] bogies = {
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car")
        };

        // Display all bogies
        System.out.println("=== Train Bogies ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Accept user input for search
        System.out.print("\nEnter Bogie ID to search: ");
        String searchId = scanner.nextLine().trim();

        // Perform linear search
        int index = linearSearch(bogies, searchId);

        // Display result
        if (index != -1) {
            System.out.println("\n✅ Bogie Found at Index: " + index);
            System.out.println("Details: " + bogies[index]);
        } else {
            System.out.println("\n❌ Bogie with ID '" + searchId + "' not found.");
        }

        scanner.close();
    }
}