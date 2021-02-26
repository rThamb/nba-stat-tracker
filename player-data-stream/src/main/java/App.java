import data.ApiPlayerData;
import engine.PlayerDataProducer;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        PlayerDataProducer engine = new PlayerDataProducer();
        engine.run();
    }
}
