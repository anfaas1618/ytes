import java.util.HashMap;
import java.util.Map;

public class main2 {

    // Paytable represented as a two-dimensional array
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
    // Reel window represented as a two-dimensional array
    private static final int[][] reelWindow = {
            {0, 0, 8, 4, 5},
            {0, 5, 9, 3, 4},
            {11, 9, 0, 12, 7},
            {7, 8, 12, 11, 7},
            {8, 6, 3, 4, 8}
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
        HashMap<Integer, cordinate> payMap;
        payMap = new HashMap<>();
        payMap.put(0, new cordinate(0, 0));
        payMap.put(1, new cordinate(0, 1));
        payMap.put(2, new cordinate(0, 2));
        payMap.put(3, new cordinate(0, 3));
        payMap.put(4, new cordinate(0, 4));
        payMap.put(5, new cordinate(1, 0));
        payMap.put(6, new cordinate(1, 1));
        payMap.put(7, new cordinate(1, 2));
        payMap.put(8, new cordinate(1, 3));
        payMap.put(9, new cordinate(1, 4));
        payMap.put(10, new cordinate(2, 0));
        payMap.put(11, new cordinate(2, 1));
        payMap.put(12, new cordinate(2, 2));
        payMap.put(13, new cordinate(2, 3));
        payMap.put(14, new cordinate(2, 4));
        payMap.put(15, new cordinate(3, 0));
        payMap.put(16, new cordinate(3, 1));
        payMap.put(17, new cordinate(3, 2));
        payMap.put(18, new cordinate(3, 3));
        payMap.put(19, new cordinate(3, 4));
        payMap.put(20, new cordinate(4, 0));
        payMap.put(21, new cordinate(4, 1));
        payMap.put(22, new cordinate(4, 2));
        payMap.put(23, new cordinate(4, 3));
        payMap.put(24, new cordinate(4, 4));

        int[] symbolCounts = new int[reelWindow.length]; // Use reelWindow length
        cordinate value = payMap.get(payline[0]);
        int count = 0;
        int symbol = reelWindow[value.getX()][value.getY()];
        //    count++;
        // Count symbols on the payline

        for (int position : payline) {
            int row = position / 5;
            int col = position % 5;
            int gotSymbol = reelWindow[row][col];
            if (symbol == 0) {
                symbol = gotSymbol;
            }
            if ((gotSymbol == symbol) || (gotSymbol == 0)) {
                count++;
            } else {
                break;
            }
        }
//        for (int i = 1; i < payline.length; i++) {
//            cordinate valueLine = payMap.get(payline[i]);
//            int gotSymbol = reelWindow[valueLine.getX()][valueLine.getY()];
//            if (symbol == 0) {
//                symbol = gotSymbol;
//            }
//            if ((gotSymbol == symbol) || (gotSymbol == 0)) {
//                count++;
//            } else {
//                break;
//            }
//        }

        // Check for matches in the paytable and calculate payout
        int payout = 0;

        if (count == 3) {
            payout += paytable[symbol][0];
        }
        if (count == 4) {
            payout += paytable[symbol][1];
        }
        if (count == 5) {
            payout += paytable[symbol][2];
        }
        System.out.print(payout + " count:" + count);
        return payout;
    }

    // Method to calculate total payout for all paylines
    private static int calculateTotalPayout(int[][] reelWindow) {
        int totalPayout = 0;

        // Calculate payout for each payline
        for (int[] payline : paylines) {
            totalPayout += calculatePaylinePayout(payline);
            // Print symbols and positions for reference
            System.out.print("Payline: ");
            for (int position : payline) {
                int row = position / 5;
                int col = position % 5;
                System.out.print(reelWindow[row][col] + " ");
            }
            System.out.println();
        }

        return totalPayout;
    }

    public static class cordinate {
        int x;
        int y;

        public cordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) {

        int totalPayout = calculateTotalPayout(reelWindow);
        System.out.println("Total payout: " + totalPayout);
    }
}
