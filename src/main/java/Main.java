import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static final String inputFile = "src/main/operations.txt";
    static final String outputFile = "src/main/results.txt";

    public static void main(String[] args) {
        List<String> operations;
        try {
            operations = readDataOperations();
            List<Double> results = calculateResultsAndReturnList(operations);
            List<String> operationsAndResults = makeOperationsAndResultsInOneList(operations, results);
            printOperationsWithResults(operationsAndResults);
            saveOperationsWithResults(operationsAndResults);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printOperationsWithResults(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    private static List<String> readDataOperations() throws IOException {
        List<String> list = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split("\\s+");
                String word0 = word[0];
                String word1 = word[1];
                String word2 = word[2];

                double v = Double.parseDouble(word0);
                double v1 = Double.parseDouble(word2);
                String resultString = v + " " + word1 + " " + v1;
                list.add(resultString);
            }
        }
        return list;
    }

    private static List<Double> calculateResultsAndReturnList(List<String> list) {
        List<Double> newList = new ArrayList<>();

        for (String line : list) {
            String[] strings = line.split("\\s+");
            double result = switch (strings[1]) {
                case "+" -> Double.parseDouble(strings[0]) + Double.parseDouble(strings[2]);
                case "-" -> Double.parseDouble(strings[0]) - Double.parseDouble(strings[2]);
                case "*" -> Double.parseDouble(strings[0]) * Double.parseDouble(strings[2]);
                case "/" -> Double.parseDouble(strings[0]) / Double.parseDouble(strings[2]);
                default -> throw new IllegalStateException("Nieznana wartość " + strings[1]);
            };
            newList.add(result);
        }
        return newList;
    }

    private static List<String> makeOperationsAndResultsInOneList(List<String> strings, List<Double> doubles) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < doubles.size(); i++) {
            String line = (strings.get(i) + " = " + doubles.get(i));
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


