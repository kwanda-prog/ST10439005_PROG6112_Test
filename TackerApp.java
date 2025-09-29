import java.util.Scanner;


public class TackerApp {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            
            System.out.print("The cricketer name: ");
            String batsman = sc.nextLine().trim();

            System.out.print("Enter the stadium: ");
            String stadium = sc.nextLine().trim();

            // Compose the third prompt using the values just entered
            int runs = readNonNegativeInt(
                sc,
                String.format("Enter the total runs scored by %s at %s: ", batsman, stadium)
            );

            System.out.println(); 

            // --- Domain object ---
            CricketRunsScored result = new CricketRunsScored(batsman, stadium, runs);
            result.printReport(); 
        }
    }

    //helping method used to non- negative whole numbers in full 
    private static int readNonNegativeInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < 0) {
                    System.out.println("Please enter a non-negative whole number (e.g., 0, 123, 5000).");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter digits only (e.g., 0, 123, 5000).");
            }
        }
    }
}


//ICricket interface
interface ICricket {
 String getBatsman();
 String getStadium();
    int getRunsScored();
}


//Abstract base class to implement ICritcket ,will decalre an abstract printreport to  be implemented 

abstract class Cricket implements ICricket {



    private final String batsman;
    private final String stadium;
    private final int runsScored;

    // runs scoed are must equal 0, with batman not being empty
    protected Cricket(String batsman, String stadium, int runsScored) {
        if (batsman == null || batsman.isBlank()) {
            throw new IllegalArgumentException("Batsman's name cannot be empty.");
        }
        if (stadium == null || stadium.isBlank()) {
            throw new IllegalArgumentException("Stadium name cannot be empty.");
        }
        if (runsScored < 0) {
            throw new IllegalArgumentException("Runs scored cannot be negative.");
        }

        this.batsman = batsman;
        this.stadium = stadium;
        this.runsScored = runsScored;
    }

    // --- ICricket interface ---
    @Override
    public String getBatsman() {
        return batsman;
    }

    @Override
    public String getStadium() {
        return stadium;
    }

    @Override
    public int getRunsScored() {
        return runsScored;
    }

    // Subclasses providing the report
    public abstract void printReport();
}

//subclass prints out report in full
class CricketRunsScored extends Cricket {

    // constructor for the abstract base class
    public CricketRunsScored(String batsman, String stadium, int runsScored) {
        super(batsman, stadium, runsScored);
    }

    
    @Override
    public void printReport() {
        System.out.println("BATSMAN RUNS SCORED REPORT");
        System.out.println("**************************");
      System.out.printf("CRICKET PLAYER: %s%n", getBatsman());
        System.out.printf("STADIUM: %s%n", getStadium());
     System.out.printf("TOTAL RUNS SCORED: %d%n", getRunsScored());
        System.out.println(); // trailing newline (optional)
    }
}
