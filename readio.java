import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readio {
    public static void main(String[] args) {
        String fileName = "test.txt"; // Change this to your file name

        try {
            List<String> tableData = readTableData(fileName);
            for (String table : tableData) {
                // Display each table
                System.out.println("Table:");
                System.out.println(table);
                System.out.println();

                // Process the table if needed
                // You can call a method here to parse the table data and store it in a 2D matrix
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static List<String> readTableData(String fileName) throws IOException {
        List<String> tableData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder tableBuilder = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Skip empty lines
                    continue;
                }

                if (line.startsWith("=")) {
                    // If line starts with "=", it's a table delimiter
                    if (tableBuilder.length() > 0) {
                        tableData.add(tableBuilder.toString());
                        tableBuilder = new StringBuilder(); // Reset the table builder
                    }
                }

                // Append the current line to the table builder
                tableBuilder.append(line).append("\n");
            }

            // Add the last table if there's any remaining
            if (tableBuilder.length() > 0) {
                tableData.add(tableBuilder.toString());
            }
        }

        return tableData;
    }
}
