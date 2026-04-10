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

        // Identify passenger bogies
        public boolean isPassengerBogie() {
            return type.equalsIgnoreCase("Sleeper") ||
                    type.equalsIgnoreCase("AC") ||
                    type.equalsIgnoreCase("General") ||
                    type.equalsIgnoreCase("Chair Car");
        }

        @Override
        public String toString() {
            return "Bogie ID: " + bogieId +
                    ", Capacity: " + capacity +
                    ", Type: " + type;
        }
    }

    // 🔹 Bubble Sort Algorithm for Bogies
    public static void bubbleSortByCapacity(List<Bogie> bogies) {
        int n = bogies.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (bogies.get(j).getCapacity() >
                        bogies.get(j + 1).getCapacity()) {

                    // Swap bogies
                    Bogie temp = bogies.get(j);
                    bogies.set(j, bogies.get(j + 1));
                    bogies.set(j + 1, temp);
                    swapped = true;
                }
            }

            // Optimization: Stop if no swaps occurred
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        // Creating a list of bogies
        List<Bogie> allBogies = Arrays.asList(
                new Bogie("B1", 72, "Sleeper"),
                new Bogie("A1", 50, "AC"),
                new Bogie("G1", 90, "General"),
                new Bogie("C1", 60, "Chair Car"),
                new Bogie("L1", 0, "Luggage"),
                new Bogie("P1", 0, "Power Car"),
                new Bogie("S1", 72, "Sleeper")
        );

        // Filter only passenger bogies
        List<Bogie> passengerBogies = new ArrayList<>();
        for (Bogie b : allBogies) {
            if (b.isPassengerBogie()) {
                passengerBogies.add(b);
            }
        }

        // Display before sorting
        System.out.println("=== Passenger Bogies Before Sorting ===");
        passengerBogies.forEach(System.out::println);

        // Apply Bubble Sort
        bubbleSortByCapacity(passengerBogies);

        // Display after sorting
        System.out.println("\n=== Passenger Bogies After Sorting (Ascending) ===");
        passengerBogies.forEach(System.out::println);
    }
}