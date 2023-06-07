import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OperationReader {
    public List<Operation> readOperation(String fileName) throws IOException {
        List<Operation> operationList = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split("\\s+");
                double x = Double.parseDouble(word[0]);
                String operation = word[1];
                double y = Double.parseDouble(word[2]);
                operationList.add(new Operation(x, operation, y));
            }
        }
        return operationList;
    }
}