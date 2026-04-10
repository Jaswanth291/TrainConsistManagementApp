import java.util.*;
import java.util.stream.*;

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

        // Creating a list of bogies in the train
        List<Bogie> bogies = Arrays.asList(
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("B2", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("A2", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car")
        );

        // Display all bogies
        System.out.println("=== Train Bogies ===");
        bogies.forEach(System.out::println);

        // Calculate total seats using reduce()
        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)     // Extract capacities
                .reduce(0, Integer::sum);    // Sum all capacities

        System.out.println("\nTotal Seats in Train (All Bogies): " + totalSeats);

        // Calculate total seats only for passenger bogies
        int passengerSeats = bogies.stream()
                .filter(Bogie::isPassengerBogie)
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seats in Passenger Bogies: " + passengerSeats);

        // Alternative approach using Optional reduce (without identity)
        Optional<Integer> optionalTotal = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(Integer::sum);

        optionalTotal.ifPresent(total ->
                System.out.println("Total Seats (Using Optional Reduce): " + total)
        );

        // Comparison using sum() for reference
        int totalUsingSum = bogies.stream()
                .mapToInt(Bogie::getCapacity)
                .sum();

        System.out.println("Total Seats (Using sum()): " + totalUsingSum);
    }
}