import java.util.Scanner;
import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // Regex patterns for validation
    private static final String TRAIN_ID_REGEX = "^[A-Z]{2}\\d{4}$";
    private static final String CARGO_CODE_REGEX = "^CG[A-Z]{3}\\d{3}$";

    // Precompiled patterns for better performance
    private static final Pattern TRAIN_ID_PATTERN = Pattern.compile(TRAIN_ID_REGEX);
    private static final Pattern CARGO_CODE_PATTERN = Pattern.compile(CARGO_CODE_REGEX);

    // Method to validate Train ID
    public static boolean isValidTrainId(String trainId) {
        return TRAIN_ID_PATTERN.matcher(trainId).matches();
    }

    // Method to validate Cargo Code
    public static boolean isValidCargoCode(String cargoCode) {
        return CARGO_CODE_PATTERN.matcher(cargoCode).matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Train Consist Management - Validation ===");

        // Input and validate Train ID
        System.out.print("Enter Train ID (e.g., SR1234): ");
        String trainId = scanner.nextLine().trim();

        if (isValidTrainId(trainId)) {
            System.out.println("✅ Valid Train ID.");
        } else {
            System.out.println("❌ Invalid Train ID. Format must be: Two uppercase letters followed by four digits (e.g., SR1234).");
        }

        // Input and validate Cargo Code
        System.out.print("\nEnter Cargo Code (e.g., CGOIL123): ");
        String cargoCode = scanner.nextLine().trim();

        if (isValidCargoCode(cargoCode)) {
            System.out.println("✅ Valid Cargo Code.");
        } else {
            System.out.println("❌ Invalid Cargo Code. Format must be: 'CG' + three uppercase letters + three digits (e.g., CGOIL123).");
        }

        // Demonstration with sample values
        System.out.println("\n=== Sample Validation Tests ===");
        String[] sampleTrainIds = {"SR1234", "er5678", "WR0001", "S12345", "AB12C4"};
        String[] sampleCargoCodes = {"CGOIL123", "CGFOO456", "CGcar789", "CG12A345", "CGABC12"};

        System.out.println("\nTrain ID Tests:");
        for (String id : sampleTrainIds) {
            System.out.println(id + " -> " +
                    (isValidTrainId(id) ? "Valid" : "Invalid"));
        }

        System.out.println("\nCargo Code Tests:");
        for (String code : sampleCargoCodes) {
            System.out.println(code + " -> " +
                    (isValidCargoCode(code) ? "Valid" : "Invalid"));
        }

        scanner.close();
    }
}