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

// Train class using TreeSet (SortedSet)
class Train {
    String trainName;
    SortedSet<String> bogieIds; // automatically sorted
    ArrayList<Bogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        bogieIds = new TreeSet<>(); // TreeSet = SortedSet
        bogies = new ArrayList<>();
    }

    // Add bogie (unique + sorted)
    void addBogie(Bogie b) {
        if (bogieIds.add(b.bogieId)) { // add returns false if duplicate
            bogies.add(b);
            System.out.println("Bogie added: " + b);
        } else {
            System.out.println("Duplicate Bogie ID not allowed: " + b.bogieId);
        }
    }

    // Display all bogies
    void displayBogies() {
        System.out.println("\nTrain: " + trainName);
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    // Display sorted bogie IDs
    void displaySortedIDs() {
        System.out.println("\nSorted Bogie IDs: " + bogieIds);
    }

    // Show subset (range)
    void displayRange(String from, String to) {
        System.out.println("\nRange (" + from + " to " + to + "): " +
                ((TreeSet<String>) bogieIds).subSet(from, to));
    }
}

// Main class
public class UC4MaintainOrderedBogieIDs {

    public static void main(String[] args) {

        Train train = new Train("Chennai Express");

        train.addBogie(new Bogie("S2", "Sleeper"));
        train.addBogie(new Bogie("A1", "AC"));
        train.addBogie(new Bogie("S1", "Sleeper"));
        train.addBogie(new Bogie("G1", "General"));

        // Duplicate test
        train.addBogie(new Bogie("S1", "Sleeper"));

        train.displayBogies();
        train.displaySortedIDs();

        // Range query
        train.displayRange("A1", "S2");
    }
}