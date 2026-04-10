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
        System.out.println("=== All Bogies ===");
        bogies.forEach(System.out::println);

        // Group bogies by type (case-insensitive)
        Map<String, List<Bogie>> bogiesByType = bogies.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getType().toUpperCase()
                ));

        // Display grouped bogies
        System.out.println("\n=== Bogies Grouped by Type ===");
        for (Map.Entry<String, List<Bogie>> entry : bogiesByType.entrySet()) {
            System.out.println("\nType: " + entry.getKey());
            entry.getValue().forEach(b -> System.out.println("  " + b));
        }

        // Additional Example: Count the number of bogies in each type
        Map<String, Long> bogieCountByType = bogies.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getType().toUpperCase(),
                        Collectors.counting()
                ));

        System.out.println("\n=== Number of Bogies by Type ===");
        bogieCountByType.forEach((type, count) ->
                System.out.println("Type: " + type + " -> Count: " + count)
        );

        // Additional Example: Total capacity per bogie type
        Map<String, Integer> totalCapacityByType = bogies.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getType().toUpperCase(),
                        Collectors.summingInt(Bogie::getCapacity)
                ));

        System.out.println("\n=== Total Capacity by Bogie Type ===");
        totalCapacityByType.forEach((type, totalCapacity) ->
                System.out.println("Type: " + type + " -> Total Capacity: " + totalCapacity)
        );
    }
}