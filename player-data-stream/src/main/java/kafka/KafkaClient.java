package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaClient {

    private String bootstrapServers = "localhost:9092";
    private Properties properties;


    public KafkaClient(){
        this.properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //Give a name for logging purposes
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "player-data-process");
        //Tell kafka how to serialize the data to send through network
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    public void produceMessage(String kafkaTopicKey, String data) {
        KafkaProducer producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopicKey, data);
        producer.send(record);
        //you'll need to flush the data as it runs in a background process. flush to send data and resume main thread.
        producer.flush();
        producer.close();
    }
    
    public void consumeMessage(){

    }
}
