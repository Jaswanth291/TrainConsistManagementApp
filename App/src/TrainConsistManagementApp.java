import java.util.*;

public class TrainConsistManagementApp {

    // ✅ Custom Exception for Invalid Capacity
    static class InvalidBogieCapacityException extends Exception {
        public InvalidBogieCapacityException(String message) {
            super(message);
        }
    }

    // ✅ Bogie Class
    static class Bogie {
        private String bogieId;
        private int capacity;
        private String type;

        public Bogie(String bogieId, int capacity, String type)
                throws InvalidBogieCapacityException {
            this.bogieId = bogieId;
            this.type = type;
            setCapacity(capacity); // Validation through setter
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

        // Method to determine if a bogie is a passenger bogie
        public boolean isPassengerBogie() {
            return type.equalsIgnoreCase("Sleeper") ||
                    type.equalsIgnoreCase("AC") ||
                    type.equalsIgnoreCase("General") ||
                    type.equalsIgnoreCase("Chair Car");
        }

        // ✅ Capacity validation logic
        public void setCapacity(int capacity)
                throws InvalidBogieCapacityException {

            if (capacity < 0) {
                throw new InvalidBogieCapacityException(
                        "Capacity cannot be negative for Bogie ID: " + bogieId);
            }

            if (isPassengerBogie() && capacity == 0) {
                throw new InvalidBogieCapacityException(
                        "Passenger bogie must have capacity greater than zero. Bogie ID: "
                                + bogieId);
            }

            if (capacity > 200) {
                throw new InvalidBogieCapacityException(
                        "Capacity exceeds the maximum allowed limit (200). Bogie ID: "
                                + bogieId);
            }

            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    // ✅ Main Method
    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();

        // Sample data including invalid cases
        Object[][] sampleData = {
                {"B1", 72, "Sleeper"},
                {"A1", 50, "AC"},
                {"G1", 90, "General"},
                {"L1", 0, "Luggage"},      // Valid
                {"P1", 0, "Power Car"},    // Valid
                {"S1", -10, "Sleeper"},    // Invalid: Negative
                {"C1", 0, "Chair Car"},    // Invalid: Zero capacity for passenger
                {"X1", 250, "AC"}          // Invalid: Exceeds limit
        };

        System.out.println("=== Bogie Capacity Validation ===");

        for (Object[] data : sampleData) {
            String id = (String) data[0];
            int capacity = (int) data[1];
            String type = (String) data[2];

            try {
                Bogie bogie = new Bogie(id, capacity, type);
                bogies.add(bogie);
                System.out.println("✅ Added: " + bogie);
            } catch (InvalidBogieCapacityException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        // Display valid bogies
        System.out.println("\n=== Valid Bogies in Train ===");
        bogies.forEach(System.out::println);
    }
}