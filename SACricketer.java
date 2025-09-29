import java.util.Scanner;


public class SACricketer {

    // headings 
     private static final String APP_TITLE     = "South African CRICKETER ";
    private static final String REPORT_TITLE  = "Runs SCORED REPORT";
    private static final String TOTALS_TITLE  = "total RUNS AT STADIUMS";

    
    private static final boolean USE_SAMPLE_DATA = false;

    public static void main(String[] args) {
        // stores names of the invidivuisals who are batting
        String[] batsmen = {
                "Jacques Kallis",
                "Hashim Amla",
                "AB de Villiers"
        };

        String[] stadiums = {
                "KINGSmead",   
                "ST GEORGES",
                "WANDERERS"
        };

        // stores runs for batsman b at stadium
        int[][] runs = new int[batsmen.length][stadiums.length];

        // nested loop used for staduims and outer loop for the batsmen
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(APP_TITLE);
            System.out.println();

            if (USE_SAMPLE_DATA) {
               
                runs[0][0] = 5000; runs[1][0] = 3800; runs[2][0] = 4200; 
                runs[0][1] = 3500; runs[1][1] = 3700; runs[2][1] = 3900; 
                runs[0][2] = 6200; runs[1][2] = 5000; runs[2][2] = 5200; 
                
                echoSamplePrompts();
            } else {
               
                for (int s = 0; s < stadiums.length; s++) {
                    for (int b = 0; b < batsmen.length; b++) {
                        String prompt = String.format(
                                "Enter the number runs scored by %s at %s: ",
                                batsmen[b], stadiums[s]
                        );
                        runs[b][s] = readNonNegativeInt(sc, prompt);
                    }
                }
            }
        }

        // 
        System.out.println();
        System.out.println(REPORT_TITLE);
        System.out.println();

        for (int b = 0; b < batsmen.length; b++) {
            for (int s = 0; s < stadiums.length; s++) {
                // e.g., "Jacques Kallis runs scored at KINGSmead: 5000"
                System.out.printf("%s runs scored at %s: %d%n", batsmen[b], stadiums[s], runs[b][s]);
            }
            System.out.println(); // line between batsmen blocks
        }

        // the number of totals per stadium 
        System.out.println(TOTALS_TITLE);
        System.out.println();

        int[] stadiumTotals = new int[stadiums.length];
        int maxIndex = 0; // tracks which stadium has equals the largest values

        for (int s = 0; s < stadiums.length; s++) {
            int sum = 0;
            for (int b = 0; b < batsmen.length; b++) {
                sum += runs[b][s];
            }
            stadiumTotals[s] = sum;

            // prints in two colums 
            System.out.printf("%-12s %7d%n", padForSample(stadiums[s]), stadiumTotals[s]);

            if (stadiumTotals[s] > stadiumTotals[maxIndex]) {
                maxIndex = s;
            }
        }

        System.out.println();
        System.out.printf("STADIUM WITH THE MOST RUNS: %s%n", stadiums[maxIndex]);
    }

    
    //loops till the user types a valid integer
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

    // formatter 
    private static String padForSample(String stadium) {
        if ("KINGSmead".equals(stadium))   return "KINGSmead";
        if ("ST GEORGES".equals(stadium))  return "ST GEORGES";
      if ("WANDERERS".equals(stadium))   return "WANDERERS";
        return stadium;
    }

    
    private static void echoSamplePrompts() {
        System.out.println("Enter the number runs scored by Jacques Kallis at KINGSmead: 5000");
        System.out.println("Enter the number runs scored by Hashim Amla at KINGSmead: 3800");
        System.out.println("Enter the number runs scored by AB de Villiers at KINGSmead: 4200");
        System.out.println("Enter the number runs scored by Jacques Kallis at ST GEORGES: 3500");
        System.out.println("Enter the number runs scored by Hashim Amla at ST GEORGES: 3700");
        System.out.println("Enter the number runs scored by AB de Villiers at ST GEORGES: 3900");
        System.out.println("Enter the number runs scored by Jacques Kallis at WANDERERS: 6200");
        System.out.println("Enter the number runs scored by Hashim Amla at WANDERERS: 5000");
        System.out.println("Enter the number runs scored by AB de Villiers at WANDERERS: 5200");
        System.out.println();
    }
}
