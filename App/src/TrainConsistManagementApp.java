import java.util.*;

// Bogie class
class Bogie {
    String bogieId;
    String type;

    Bogie(String bogieId, String type) {
        this.bogieId = bogieId;
        this.type = type;
    }

    @Override
    public String toString() {
        return bogieId + " (" + type + ")";
    }
}

// Train class using LinkedHashSet
class Train {
    String trainName;
    LinkedHashSet<String> bogieIds; // preserves insertion order
    ArrayList<Bogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        bogieIds = new LinkedHashSet<>();
        bogies = new ArrayList<>();
    }

    // Add bogie (unique + maintains order)
    void addBogie(Bogie b) {
        if (bogieIds.add(b.bogieId)) {
            bogies.add(b);
            System.out.println("Bogie added: " + b);
        } else {
            System.out.println("Duplicate Bogie ID not allowed: " + b.bogieId);
        }
    }

    // Display bogies in insertion order
    void displayBogies() {
        System.out.println("\nTrain: " + trainName);
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    // Display IDs in insertion order
    void displayBogieIDs() {
        System.out.println("\nBogie IDs (Insertion Order): " + bogieIds);
    }
}

// Main class
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        train.addBogie(new Bogie("S2", "Sleeper"));
        train.addBogie(new Bogie("A1", "AC"));
        train.addBogie(new Bogie("S1", "Sleeper"));
        train.addBogie(new Bogie("G1", "General"));

        // Duplicate test
        train.addBogie(new Bogie("S2", "Sleeper"));

        train.displayBogies();
        train.displayBogieIDs();
    }
}