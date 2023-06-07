import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String inputFile = "src/main/operations.txt";
    static final String outputFile = "src/main/results.txt";

    public static void main(String[] args) {
        List<Operation> operationList = null;
        try {
            OperationReader operationReader = new OperationReader();
            operationList = operationReader.readOperation(inputFile);

        } catch (IOException e) {
            System.err.println("Nie można odczytać pliku " + inputFile);
        }

        if (operationList != null && operationList.size() != 0) {
            List<Double> results = calculateResults(operationList);
            List<String> operationsAndResults = makeOperationsAndResultsInOneList(operationList, results);
            printOperationsWithResults(operationsAndResults);
            try {
                saveOperationsWithResults(operationsAndResults);
            } catch (IOException e) {
                System.err.println("Nie udało się zapisać pliku" + outputFile);
            }
        } else {
            System.err.println("Pusta lista operacji");
        }
    }

    private static void printOperationsWithResults(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    private static List<Double> calculateResults(List<Operation> list) {
        List<Double> newList = new ArrayList<>();
        for (Operation operation : list) {
            double result = switch (operation.getOperation()) {
                case "+" -> operation.getX() + operation.getY();
                case "-" -> operation.getX() - operation.getY();
                case "*" -> operation.getX() * operation.getY();
                case "/" -> operation.getX() / operation.getY();
                default -> throw new IllegalStateException("Nieznana wartość " + operation.getOperation());
            };
            newList.add(result);
        }
        return newList;
    }

    private static List<String> makeOperationsAndResultsInOneList(List<Operation> operations, List<Double> results) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            String line = (operations.get(i) + " = " + results.get(i));
            newList.add(line);
        }
        return newList;
    }

    private static void saveOperationsWithResults(List<String> list) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : list) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }
}