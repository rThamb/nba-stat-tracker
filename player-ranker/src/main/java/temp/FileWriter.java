package temp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {

    public static void write(String content) throws IOException {
        String filepath = "data/data.json";
        Files.write(Paths.get(filepath), content.getBytes());
    }

    public static String read() throws IOException {
        String filepath = "data/data.json";
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        return new String(bytes);
    }

}
