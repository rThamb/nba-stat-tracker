import engine.PlayerAnalysisService;
import kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

public class App {

    public static void main(String[] args){
        PlayerAnalysisService service = new PlayerAnalysisService();
        service.run();
    }
}
