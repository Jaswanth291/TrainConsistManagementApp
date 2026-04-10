import java.util.*;

// Public class for the application
public class TrainConsistManagementApp {

    // ✅ Custom Exception for invalid search input
    static class InvalidSearchException extends Exception {
        public InvalidSearchException(String message) {
            super(message);
        }
    }

    // ✅ Custom Exception when bogie is not found
    static class BogieNotFoundException extends Exception {
        public BogieNotFoundException(String message) {
            super(message);
        }
    }

    // ✅ Custom Exception for empty dataset
    static class EmptyDatasetException extends Exception {
        public EmptyDatasetException(String message) {
            super(message);
        }
    }

    // ✅ Bogie class implementing Comparable for sorting
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

    // 🔍 Method to perform binary search with exception handling
    public static Bogie searchBogie(Bogie[] bogies, String searchId)
            throws InvalidSearchException, BogieNotFoundException, EmptyDatasetException {

        if (bogies == null || bogies.length == 0) {
            throw new EmptyDatasetException("No bogies available for search.");
        }

        if (searchId == null || searchId.trim().isEmpty()) {
            throw new InvalidSearchException("Search ID cannot be empty.");
        }

        searchId = searchId.toUpperCase();

        // Ensure the array is sorted before searching
        Arrays.sort(bogies);

        // Create a dummy key for binary search
        Bogie key = new Bogie(searchId, 0, "");

        int index = Arrays.binarySearch(bogies, key);

        if (index < 0) {
            throw new BogieNotFoundException(
                    "Bogie with ID '" + searchId + "' not found.");
        }

        return bogies[index];
    }

    // Method to display all bogies
    public static void displayBogies(Bogie[] bogies) {
        System.out.println("\n=== List of Bogies ===");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    // ✅ Main Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial dataset
        Bogie[] bogies = {
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car")
        };

        int choice = 0;

        System.out.println("=== Train Consist Management - Search Operations ===");

        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Search Bogie by ID");
                System.out.println("2. Display All Bogies");
                System.out.println("3. Clear Dataset (Simulate Empty)");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Bogie ID to search: ");
                        String searchId = scanner.nextLine();

                        Bogie result = searchBogie(bogies, searchId);
                        System.out.println("✅ Bogie Found:");
                        System.out.println(result);
                        break;

                    case 2:
                        displayBogies(bogies);
                        break;

                    case 3:
                        bogies = new Bogie[0]; // Simulate empty dataset
                        System.out.println("⚠️ Dataset cleared successfully.");
                        break;

                    case 4:
                        System.out.println("Exiting application. Thank you!");
                        break;

                    default:
                        System.out.println("❌ Invalid menu choice. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a numeric choice.");
                scanner.nextLine(); // Clear invalid input
            } catch (InvalidSearchException |
                     BogieNotFoundException |
                     EmptyDatasetException e) {
                System.out.println("❌ Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
            } finally {
                System.out.println("🔄 Operation completed.");
            }

        } while (choice != 4);

        scanner.close();
    }
}