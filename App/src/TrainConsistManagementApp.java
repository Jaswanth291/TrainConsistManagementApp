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

// Train class using HashSet
class Train {
    String trainName;
    HashSet<String> bogieIds; // stores unique IDs
    ArrayList<Bogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        bogieIds = new HashSet<>();
        bogies = new ArrayList<>();
    }

    // Add bogie only if ID is unique
    void addBogie(Bogie b) {
        if (bogieIds.contains(b.bogieId)) {
            System.out.println("Duplicate Bogie ID not allowed: " + b.bogieId);
        } else {
            bogieIds.add(b.bogieId);
            bogies.add(b);
            System.out.println("Bogie added: " + b);
        }
    }

    // Display bogies
    void displayBogies() {
        System.out.println("\nTrain: " + trainName);
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    // Display unique IDs
    void displayUniqueIDs() {
        System.out.println("\nUnique Bogie IDs: " + bogieIds);
    }
}

// Main class
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        train.addBogie(new Bogie("S1", "Sleeper"));
        train.addBogie(new Bogie("S2", "Sleeper"));
        train.addBogie(new Bogie("A1", "AC"));

        // Duplicate attempt
        train.addBogie(new Bogie("S1", "Sleeper"));

        train.displayBogies();
        train.displayUniqueIDs();
    }
}