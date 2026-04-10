import java.util.*;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Array of bogie names (IDs)
        String[] bogieNames = {
                "B1", "A1", "G1", "S1", "C1", "L1", "P1", "A2", "B2"
        };

        // Display original array
        System.out.println("=== Original Bogie Names ===");
        System.out.println(Arrays.toString(bogieNames));

        // 🔹 1. Sort in Ascending Order (Natural/Alphabetical Order)
        Arrays.sort(bogieNames);
        System.out.println("\n=== Sorted Bogie Names (Ascending) ===");
        System.out.println(Arrays.toString(bogieNames));

        // 🔹 2. Sort in Descending Order
        Arrays.sort(bogieNames, Collections.reverseOrder());
        System.out.println("\n=== Sorted Bogie Names (Descending) ===");
        System.out.println(Arrays.toString(bogieNames));

        // 🔹 3. Case-Insensitive Sorting Example
        String[] mixedCaseBogieNames = {
                "b1", "A1", "g1", "S1", "c1", "L1", "p1"
        };

        System.out.println("\n=== Mixed Case Bogie Names (Original) ===");
        System.out.println(Arrays.toString(mixedCaseBogieNames));

        Arrays.sort(mixedCaseBogieNames, String.CASE_INSENSITIVE_ORDER);
        System.out.println("\n=== Sorted Bogie Names (Case-Insensitive) ===");
        System.out.println(Arrays.toString(mixedCaseBogieNames));
    }
}