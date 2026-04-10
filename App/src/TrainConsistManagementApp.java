import java.util.*;
import java.util.stream.*;

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

        public int getCapacity() {
            return capacity;
        }

        public String getType() {
            return type;
        }

        // Method to identify passenger bogies
        public boolean isPassengerBogie() {
            return type.equalsIgnoreCase("Sleeper") ||
                    type.equalsIgnoreCase("AC") ||
                    type.equalsIgnoreCase("General") ||
                    type.equalsIgnoreCase("Chair Car");
        }
    }

    public static void main(String[] args) {

        // Generate a large dataset of bogies for performance testing
        List<Bogie> bogies = new ArrayList<>();
        String[] types = {"Sleeper", "AC", "General", "Chair Car", "Luggage", "Power Car"};
        Random random = new Random();

        int dataSize = 1_000_000; // Number of bogies

        for (int i = 0; i < dataSize; i++) {
            String type = types[random.nextInt(types.length)];
            int capacity = type.equals("Luggage") || type.equals("Power Car")
                    ? 0
                    : 40 + random.nextInt(60); // Capacity between 40 and 100
            bogies.add(new Bogie("B" + i, capacity, type));
        }

        System.out.println("Dataset size: " + dataSize);

        // 🔹 1. Using Traditional For-Loop
        long startLoop = System.nanoTime();

        int totalSeatsLoop = 0;
        for (Bogie b : bogies) {
            if (b.isPassengerBogie()) {
                totalSeatsLoop += b.getCapacity();
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // 🔹 2. Using Sequential Stream
        long startStream = System.nanoTime();

        int totalSeatsStream = bogies.stream()
                .filter(Bogie::isPassengerBogie)
                .mapToInt(Bogie::getCapacity)
                .sum();

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // 🔹 3. Using Parallel Stream (Optional Enhancement)
        long startParallel = System.nanoTime();

        int totalSeatsParallel = bogies.parallelStream()
                .filter(Bogie::isPassengerBogie)
                .mapToInt(Bogie::getCapacity)
                .sum();

        long endParallel = System.nanoTime();
        long parallelTime = endParallel - startParallel;

        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Total Seats (Loop): " + totalSeatsLoop);
        System.out.println("Total Seats (Stream): " + totalSeatsStream);
        System.out.println("Total Seats (Parallel Stream): " + totalSeatsParallel);

        System.out.println("\n=== Performance Comparison (in milliseconds) ===");
        System.out.printf("For-Loop Time: %.3f ms%n", loopTime / 1_000_000.0);
        System.out.printf("Stream Time: %.3f ms%n", streamTime / 1_000_000.0);
        System.out.printf("Parallel Stream Time: %.3f ms%n", parallelTime / 1_000_000.0);

        // Determine the fastest approach
        long minTime = Math.min(loopTime, Math.min(streamTime, parallelTime));
        System.out.println("\nFastest Approach: " +
                (minTime == loopTime ? "For-Loop"
                        : (minTime == streamTime ? "Sequential Stream"
                        : "Parallel Stream")));
    }
}