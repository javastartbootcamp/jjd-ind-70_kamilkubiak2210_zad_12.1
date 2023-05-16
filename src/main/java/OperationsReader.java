import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OperationsReader {
    public  List<String> readDataOperations(String file) throws IOException {
        List<String> list = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
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
}
