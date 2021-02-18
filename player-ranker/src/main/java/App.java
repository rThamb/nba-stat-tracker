import kafka.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

public class App {

    public static void main(String[] args){

        KafkaClient client = new KafkaClient();

        KafkaConsumer consumer = client.getConsumerHandle();

        //now tell the consumer to listen to player data, sub to multiple topic by using a list of topics
        consumer.subscribe(Collections.singleton("player"));

        while(true){

            //The message will only be consumed when the service is ready
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord record: records){
                System.out.println(record.value());
            }
        }


    }
}
