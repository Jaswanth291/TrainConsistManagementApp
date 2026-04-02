import java.util.*;

// Coach class
class Coach {
    String coachId;
    String type; // Sleeper / AC / General
    int capacity;

    Coach(String coachId, String type, int capacity) {
        this.coachId = coachId;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return coachId + " (" + type + ", Cap:" + capacity + ")";
    }
}

// Train class
class Train {
    String trainName;
    List<Coach> coaches;

    Train(String trainName) {
        this.trainName = trainName;
        this.coaches = new ArrayList<>();
    }

    // Add coach
    void addCoach(Coach coach) {
        coaches.add(coach);
    }

    // Display summary
    void displaySummary() {
        System.out.println("Train Name: " + trainName);
        System.out.println("Total Coaches: " + coaches.size());

        int totalCapacity = 0;
        Map<String, Integer> typeCount = new HashMap<>();

        for (Coach c : coaches) {
            totalCapacity += c.capacity;

            typeCount.put(c.type, typeCount.getOrDefault(c.type, 0) + 1);
        }

        System.out.println("Total Capacity: " + totalCapacity);

        System.out.println("Coach Type Summary:");
        for (String type : typeCount.keySet()) {
            System.out.println(type + ": " + typeCount.get(type));
        }

        System.out.println("\nCoach Details:");
        for (Coach c : coaches) {
            System.out.println(c);
        }
    }
}

// Main class
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        // Initialize coaches
        train.addCoach(new Coach("S1", "Sleeper", 72));
        train.addCoach(new Coach("S2", "Sleeper", 72));
        train.addCoach(new Coach("A1", "AC", 50));
        train.addCoach(new Coach("G1", "General", 100));

        // Display summary
        train.displaySummary();
    }
}