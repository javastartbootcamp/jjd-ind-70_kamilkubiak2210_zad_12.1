import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void shouldWorkForAddition() throws IOException {
        // given
        String fileContent = "1 + 2";
        writeToInputFile(fileContent);

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).satisfiesAnyOf(
                content -> assertThat(content).contains("1.0 + 2.0 = 3.0"),
                content -> assertThat(content).contains("1 + 2 = 3")
        );
    }

    @Test
    void shouldWorkForMultiply() throws IOException {
        // given
        String fileContent = "2 * 3";
        writeToInputFile(fileContent);

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).satisfiesAnyOf(
                content -> assertThat(content).contains("2.0 * 3.0 = 6.0"),
                content -> assertThat(content).contains("2 * 3 = 6")
        );
    }

    @Test
    void shouldWorkForDivision() throws IOException {
        // given
        String fileContent = "11 / 2";
        writeToInputFile(fileContent);

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).satisfiesAnyOf(
                content -> assertThat(content).contains("11.0 / 2.0 = 5.5"),
                content -> assertThat(content).contains("11 / 2 = 5.5")
        );
    }

    @Test
    void shouldWorkForDoubles() throws IOException {
        // given
        String fileContent = "5.5 + 12.1";
        writeToInputFile(fileContent);

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).contains("5.5 + 12.1 = 17.6");
    }

    @Test
    void shouldWorkForSubstract() throws IOException {
        // given
        String fileContent = "10 - 2";
        writeToInputFile(fileContent);

        // when
        Main.main(new String[]{});

        // then
        assertThat(outContent.toString()).satisfiesAnyOf(
                content -> assertThat(content).contains("10.0 - 2.0 = 8.0"),
                content -> assertThat(content).contains("10 - 2 = 8")
        );
    }

    private void writeToInputFile(String fileContent) throws IOException {
        File file = new File("src/main/operations.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    @BeforeEach
    void init() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
    }

}
