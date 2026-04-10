import java.util.*;

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

    // 🔍 Binary Search Method
    public static int binarySearch(Bogie[] bogies, String targetId) {
        int left = 0;
        int right = bogies.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = bogies[mid].getBogieId()
                    .compareToIgnoreCase(targetId);

            if (comparison == 0) {
                return mid; // Found
            } else if (comparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating an array of bogies (unsorted)
        Bogie[] bogies = {
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car")
        };

        // 🔹 Step 1: Sort bogies by Bogie ID
        Arrays.sort(bogies, Comparator.comparing(Bogie::getBogieId,
                String.CASE_INSENSITIVE_ORDER));

        System.out.println("=== Sorted Bogies by ID ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // 🔹 Step 2: Accept user input
        System.out.print("\nEnter Bogie ID to search: ");
        String searchId = scanner.nextLine().trim();

        // 🔹 Step 3: Perform Binary Search
        int index = binarySearch(bogies, searchId);

        // 🔹 Step 4: Display Result
        if (index != -1) {
            System.out.println("\n✅ Bogie Found at Index: " + index);
            System.out.println("Details: " + bogies[index]);
        } else {
            System.out.println("\n❌ Bogie with ID '" + searchId + "' not found.");
        }

        scanner.close();
    }
}