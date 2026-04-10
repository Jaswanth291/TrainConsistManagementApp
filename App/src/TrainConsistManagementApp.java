import java.util.*;

// Public class name as per previous use cases
public class TrainConsistManagementApp {

    // Bogie class implementing Comparable for natural sorting by bogieId
    static class Bogie implements Comparable<Bogie> {
        private String bogieId;
        private int capacity;
        private String type;

        public Bogie(String bogieId, int capacity, String type) {
            this.bogieId = bogieId.toUpperCase(); // Normalize for case-insensitive search
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

        // Natural ordering based on Bogie ID
        @Override
        public int compareTo(Bogie other) {
            return this.bogieId.compareToIgnoreCase(other.bogieId);
        }

        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    // Method to display all bogies
    public static void displayBogies(Bogie[] bogies) {
        System.out.println("\n=== List of Bogies ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize an array of bogies (unsorted)
        Bogie[] bogies = {
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car"),
                new Bogie("S1", 72, "Sleeper"),
                new Bogie("A2", 55, "AC")
        };

        // Step 1: Sort the bogies using Arrays.sort()
        Arrays.sort(bogies);

        System.out.println("=== Bogies Sorted by Bogie ID ===");
        displayBogies(bogies);

        // Step 2: Menu-driven binary search
        int choice;
        do {
            System.out.println("\n=== Binary Search Menu ===");
            System.out.println("1. Search for a Bogie by ID");
            System.out.println("2. Display All Bogies");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Bogie ID to search: ");
                    String searchId = scanner.nextLine().trim().toUpperCase();

                    // Create a dummy bogie object for searching
                    Bogie key = new Bogie(searchId, 0, "");

                    // Perform binary search
                    int index = Arrays.binarySearch(bogies, key);

                    if (index >= 0) {
                        System.out.println("✅ Bogie Found at Index: " + index);
                        System.out.println("Details: " + bogies[index]);
                    } else {
                        System.out.println("❌ Bogie with ID '" + searchId + "' not found.");
                    }
                    break;

                case 2:
                    displayBogies(bogies);
                    break;

                case 3:
                    System.out.println("Exiting application. Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }

        } while (choice != 3);

        scanner.close();
    }
}