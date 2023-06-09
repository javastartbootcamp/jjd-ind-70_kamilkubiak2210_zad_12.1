import java.io.*;
import java.util.List;

public class Main {
    static final String inputFile = "src/main/operations.txt";
    static final String outputFile = "src/main/results.txt";

    public static void main(String[] args) {

        try {
            OperationReader operationReader = new OperationReader();
            List<Operation> operationList = operationReader.readOperation(inputFile);
            if (operationList != null && operationList.size() != 0) {
                printOperationsWithResults(operationList);
                saveOperationsWithResults(operationList);
            } else {
                System.err.println("Pusta lista operacji");
            }
        } catch (IOException e) {
            System.err.println("Nie można odczytać pliku " + inputFile);
        }
    }

    private static void printOperationsWithResults(List<Operation> list) {
        for (Operation line : list) {
            System.out.println(line);
        }
    }

    private static void saveOperationsWithResults(List<Operation> list) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (Operation line : list) {
                bufferedWriter.write(String.valueOf(line));
                bufferedWriter.newLine();
            }
        }
    }
}