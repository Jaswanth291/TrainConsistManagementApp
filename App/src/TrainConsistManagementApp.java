import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // Regex pattern for validating cargo codes
    private static final Pattern CARGO_CODE_PATTERN =
            Pattern.compile("^CG[A-Z]{3}\\d{3}$");

    // GoodsBogie class representing cargo bogies
    static class GoodsBogie {
        private String bogieId;
        private String cargoCode;
        private double loadInTons;
        private boolean brakeCertified;
        private boolean hazardous;
        private boolean hazardCompliant;

        public GoodsBogie(String bogieId, String cargoCode,
                          double loadInTons, boolean brakeCertified,
                          boolean hazardous, boolean hazardCompliant) {
            this.bogieId = bogieId;
            this.cargoCode = cargoCode;
            this.loadInTons = loadInTons;
            this.brakeCertified = brakeCertified;
            this.hazardous = hazardous;
            this.hazardCompliant = hazardCompliant;
        }

        public String getBogieId() {
            return bogieId;
        }

        public String getCargoCode() {
            return cargoCode;
        }

        public double getLoadInTons() {
            return loadInTons;
        }

        public boolean isBrakeCertified() {
            return brakeCertified;
        }

        public boolean isHazardous() {
            return hazardous;
        }

        public boolean isHazardCompliant() {
            return hazardCompliant;
        }

        // Method to check safety compliance
        public boolean isSafetyCompliant() {
            boolean validCargoCode =
                    CARGO_CODE_PATTERN.matcher(cargoCode).matches();
            boolean withinLoadLimit = loadInTons <= 100;
            boolean brakeOk = brakeCertified;
            boolean hazardOk = !hazardous || hazardCompliant;

            return validCargoCode && withinLoadLimit && brakeOk && hazardOk;
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

        // Sample goods bogies
        List<GoodsBogie> goodsBogies = Arrays.asList(
                new GoodsBogie("G001", "CGOIL123", 80, true, false, true),
                new GoodsBogie("G002", "CGCHE456", 120, true, false, true), // Overload
                new GoodsBogie("G003", "CGGAS789", 70, false, true, true),  // Brake not certified
                new GoodsBogie("G004", "INVALID", 60, true, false, true),   // Invalid cargo code
                new GoodsBogie("G005", "CGTOX111", 90, true, true, false),  // Hazard non-compliance
                new GoodsBogie("G006", "CGFOO222", 95, true, true, true)    // Fully compliant
        );

        // Display all goods bogies
        System.out.println("=== All Goods Bogies ===");
        goodsBogies.forEach(System.out::println);

        // Filter compliant bogies
        List<GoodsBogie> compliantBogies = goodsBogies.stream()
                .filter(GoodsBogie::isSafetyCompliant)
                .collect(Collectors.toList());

        // Filter non-compliant bogies
        List<GoodsBogie> nonCompliantBogies = goodsBogies.stream()
                .filter(b -> !b.isSafetyCompliant())
                .collect(Collectors.toList());

        // Display results
        System.out.println("\n=== Safety Compliant Goods Bogies ===");
        compliantBogies.forEach(System.out::println);

        System.out.println("\n=== Non-Compliant Goods Bogies ===");
        nonCompliantBogies.forEach(System.out::println);

        // Compliance summary
        long compliantCount = compliantBogies.size();
        long nonCompliantCount = nonCompliantBogies.size();

        System.out.println("\n=== Compliance Summary ===");
        System.out.println("Total Goods Bogies: " + goodsBogies.size());
        System.out.println("Compliant Bogies: " + compliantCount);
        System.out.println("Non-Compliant Bogies: " + nonCompliantCount);
    }
}