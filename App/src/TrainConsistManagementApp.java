import java.util.*;
import java.util.stream.Collectors;

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

        // Method to determine if a bogie is a passenger bogie
        public boolean isPassengerBogie() {
            return type.equalsIgnoreCase("Sleeper") ||
                    type.equalsIgnoreCase("AC") ||
                    type.equalsIgnoreCase("General") ||
                    type.equalsIgnoreCase("Chair Car");
        }

        // toString method for displaying bogie details
        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    public static void main(String[] args) {
        // Creating a list of bogies
        List<Bogie> bogies = new ArrayList<>();

        // Adding sample bogie data
        bogies.add(new Bogie("B1", 72, "Sleeper"));
        bogies.add(new Bogie("A1", 50, "AC"));
        bogies.add(new Bogie("G1", 90, "General"));
        bogies.add(new Bogie("L1", 0, "Luggage"));
        bogies.add(new Bogie("P1", 0, "Power Car"));
        bogies.add(new Bogie("C1", 60, "Chair Car"));

        // Display all bogies
        System.out.println("=== All Bogies ===");
        bogies.forEach(System.out::println);

        // Filtering passenger bogies using Streams
        List<Bogie> passengerBogies = bogies.stream()
                .filter(Bogie::isPassengerBogie)
                .collect(Collectors.toList());

        // Display filtered passenger bogies
        System.out.println("\n=== Passenger Bogies ===");
        passengerBogies.forEach(System.out::println);

        // Example: Filtering passenger bogies with capacity greater than 60
        List<Bogie> highCapacityPassengerBogies = bogies.stream()
                .filter(Bogie::isPassengerBogie)
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());

        System.out.println("\n=== High Capacity Passenger Bogies (> 60) ===");
        highCapacityPassengerBogies.forEach(System.out::println);
    }
}