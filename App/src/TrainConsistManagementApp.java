import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // Regex pattern for validating cargo codes
    private static final Pattern CARGO_CODE_PATTERN =
            Pattern.compile("^CG[A-Z]{3}\\d{3}$");

    // Custom Exception for invalid cargo assignment
    static class InvalidCargoException extends Exception {
        public InvalidCargoException(String message) {
            super(message);
        }
    }

    // GoodsBogie class representing a cargo bogie
    static class GoodsBogie {
        private String bogieId;
        private boolean brakeCertified;
        private boolean hazardous;
        private boolean hazardCompliant;
        private String cargoCode;
        private double loadInTons;

        public GoodsBogie(String bogieId, boolean brakeCertified,
                          boolean hazardous, boolean hazardCompliant) {
            this.bogieId = bogieId;
            this.brakeCertified = brakeCertified;
            this.hazardous = hazardous;
            this.hazardCompliant = hazardCompliant;
        }

        // Method to assign cargo safely
        public void assignCargo(String cargoCode, double loadInTons)
                throws InvalidCargoException {

            if (!CARGO_CODE_PATTERN.matcher(cargoCode).matches()) {
                throw new InvalidCargoException(
                        "Invalid Cargo Code for Bogie ID: " + bogieId);
            }

            if (loadInTons <= 0 || loadInTons > 100) {
                throw new InvalidCargoException(
                        "Load must be between 1 and 100 tons for Bogie ID: " + bogieId);
            }

            if (!brakeCertified) {
                throw new InvalidCargoException(
                        "Brake system not certified for Bogie ID: " + bogieId);
            }

            if (hazardous && !hazardCompliant) {
                throw new InvalidCargoException(
                        "Hazardous cargo compliance missing for Bogie ID: " + bogieId);
            }

            this.cargoCode = cargoCode;
            this.loadInTons = loadInTons;
        }

        @Override
        public String toString() {
            return "GoodsBogie{" +
                    "bogieId='" + bogieId + '\'' +
                    ", cargoCode='" + cargoCode + '\'' +
                    ", loadInTons=" + loadInTons +
                    ", brakeCertified=" + brakeCertified +
                    ", hazardous=" + hazardous +
                    ", hazardCompliant=" + hazardCompliant +
                    '}';
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Safe Cargo Assignment ===");

        // Sample goods bogies
        GoodsBogie[] bogies = {
                new GoodsBogie("G001", true, false, true),
                new GoodsBogie("G002", true, true, true),
                new GoodsBogie("G003", false, false, true),
                new GoodsBogie("G004", true, true, false)
        };

        // Cargo assignments: {cargoCode, load}
        Object[][] cargoAssignments = {
                {"CGOIL123", 80.0},   // Valid
                {"CGGAS456", 95.0},   // Valid hazardous
                {"INVALID", 50.0},    // Invalid cargo code
                {"CGCHE789", 120.0}   // Load exceeds limit
        };

        // Perform safe cargo assignment
        for (int i = 0; i < bogies.length; i++) {
            GoodsBogie bogie = bogies[i];
            String cargoCode = (String) cargoAssignments[i][0];
            double load = (double) cargoAssignments[i][1];

            try {
                System.out.println("\nAssigning cargo to Bogie ID: " + bogie.bogieId);
                bogie.assignCargo(cargoCode, load);
                System.out.println("✅ Cargo assigned successfully.");
            } catch (InvalidCargoException e) {
                System.out.println("❌ Error: " + e.getMessage());
            } finally {
                // This block always executes
                System.out.println("🔄 Assignment attempt completed for Bogie ID: "
                        + bogie.bogieId);
            }
        }

        // Display final state of all bogies
        System.out.println("\n=== Final Bogie Status ===");
        for (GoodsBogie bogie : bogies) {
            System.out.println(bogie);
        }
    }
}