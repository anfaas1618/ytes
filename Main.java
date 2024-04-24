import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    // Reel window represented the output got from reels.
    private static final String[][] reelWindow = {
            {"BB", "AA", "JJ", "CC", "HH"},
            {"JJ", "EE", "JJ", "CC", "BB"},
            {"JJ", "EE", "JJ", "AA", "BB"},
            {"JJ", "EE", "DD", "AA", "BB"},
            {"GG", "JJ", "DD", "AA", "FF"}
    };

 static    Map<String, Integer> symbolPayableMap = new HashMap<>();
  static {
      symbolPayableMap.put("WD", 0);
      symbolPayableMap.put("AA", 1);
      symbolPayableMap.put("BB", 2);
      symbolPayableMap.put("CC", 3);
      symbolPayableMap.put("DD", 4);
      symbolPayableMap.put("EE", 5);
      symbolPayableMap.put("FF", 6);
      symbolPayableMap.put("GG", 7);
      symbolPayableMap.put("HH", 8);
      symbolPayableMap.put("II", 9);
      symbolPayableMap.put("JJ", 10);
      symbolPayableMap.put("KK", 11);
      symbolPayableMap.put("SC", 12);
  }
    // Payable represents the payout against each symbol.
    // the symbol is mapped against each row with corresponding payout in the columns.
    private static final int[][] paytable = {
            // 3  4   5
            {600, 3000, 12000}, // WD 0
            {600, 3000, 12000}, // AA 1
            {400, 2000, 8000},  // BB 2
            {300, 1500, 6000},  // CC 3
            {200, 1000, 4000},  // DD 4
            {160, 800, 3200},   // EE 5
            {120, 600, 2400},   // FF 6
            {60, 300, 1200},    // GG 7
            {60, 300, 1200},    // HH 8
            {40, 200, 800},     // II 9
            {40, 200, 800},     // JJ 10
            {40, 200, 800},     // KK 11
            {0, 0, 0}           // SC 12
    };


    // Paylines provided
    private static final int[][] paylines = {
            {0, 6, 12, 8, 4},
            {0, 6, 17, 8, 4},
            {0, 6, 22, 8, 4},
            {0, 11, 7, 13, 4},
            {0, 11, 17, 13, 4},
            {0, 11, 22, 13, 4},
            {0, 16, 7, 18, 4},
            {0, 16, 12, 18, 4},
            {0, 16, 22, 18, 4},
            {0, 21, 7, 23, 4},
            {0, 21, 12, 23, 4},
            {0, 21, 17, 23, 4},
            {5, 1, 12, 3, 9},
            {5, 1, 17, 3, 9},
            {5, 1, 22, 3, 9},
            {5, 11, 2, 13, 9},
            {5, 11, 17, 13, 9},
            {5, 11, 22, 13, 9},
            {5, 16, 2, 18, 9},
            {5, 16, 12, 18, 9},
            {5, 16, 22, 18, 9},
            {5, 21, 2, 23, 9},
            {5, 21, 12, 23, 9},
            {5, 21, 17, 23, 9},
            {10, 1, 7, 3, 14},
            {10, 1, 17, 3, 14},
            {10, 1, 22, 3, 14},
            {10, 6, 2, 8, 14},
            {10, 6, 17, 8, 14},
            {10, 6, 22, 8, 14},
            {10, 16, 2, 18, 14},
            {10, 16, 7, 18, 14},
            {10, 16, 22, 18, 14},
            {10, 21, 2, 23, 14},
            {10, 21, 7, 23, 14},
            {10, 21, 17, 23, 14},
            {15, 1, 7, 3, 19},
            {15, 1, 12, 3, 19},
            {15, 1, 22, 3, 19},
            {15, 6, 2, 8, 19},
            {15, 6, 12, 8, 19},
            {15, 6, 22, 8, 19},
            {15, 11, 2, 13, 19},
            {15, 11, 7, 13, 19},
            {15, 11, 22, 13, 19},
            {15, 21, 2, 23, 19},
            {15, 21, 7, 23, 19},
            {15, 21, 12, 23, 19},
            {20, 1, 7, 3, 24},
            {20, 1, 12, 3, 24}
    };


    // Method to calculate payout for a single payline
    private static int calculatePaylinePayout(int[] payline) {
        int count = 0;
        String symbol = reelWindow[payline[0] / 5][payline[0] % 5];

        for (int position : payline) {
            int row = position / 5;
            int col = position % 5;
            String symbolGotFromPayline = reelWindow[row][col];
            if (Objects.equals(symbol, "WD")) {  // wild replaces all symbols.
                symbol = symbolGotFromPayline;
            }
            if ((Objects.equals(symbolGotFromPayline, symbol)) || (Objects.equals(symbolGotFromPayline, "WD"))) { // add count if symbol matches or wild occurs.
                count++;
            } else {
                break;
            }
        }

        // Check for matches in the paytable and calculate payout
        int payout = 0;

        if (count == 3) {
            payout += paytable[symbolPayableMap.get(symbol)][0];
        }
        if (count == 4) {
            payout += paytable[symbolPayableMap.get(symbol)][1];
        }
        if (count == 5) {
            payout += paytable[symbolPayableMap.get(symbol)][2];
        }
        System.out.print(payout + " count:" + count);
        return payout;
    }

    // Method to calculate total payout for all pay lines
    private static int calculateTotalPayout() {
        int totalPayout = 0;

        // Calculate payout for each payline
        for (int[] payline : paylines) {
            totalPayout += calculatePaylinePayout(payline);
            // Print symbols and positions for reference
            System.out.print("Payline: ");
            for (int position : payline) {
                int row = position / 5;
                int col = position % 5;
                System.out.print(Main.reelWindow[row][col] + " ");
            }
            System.out.println();
        }

        return totalPayout;
    }

    public static void main(String[] args) {

        int totalPayout = calculateTotalPayout();
        System.out.println("Total payout: " + totalPayout);
    }
}
