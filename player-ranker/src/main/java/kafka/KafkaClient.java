package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;

import java.util.Properties;

public class KafkaClient {

    private String bootstrapServers = "localhost:9092";
    private Properties propertiesProducer;
    private Properties propertiesConsumer;


    public KafkaClient(){
        this.propertiesProducer = new Properties();
        propertiesProducer.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //Give a name for logging purposes
        propertiesProducer.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "player-data-process");
        //Tell kafka how to serialize the data to send through network
        propertiesProducer.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propertiesProducer.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        propertiesConsumer = new Properties();
        propertiesConsumer.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propertiesConsumer.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propertiesConsumer.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propertiesConsumer.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "player-ranker");

        //Options (latest/earliest/none)
        propertiesConsumer.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    }

    public void produceMessage(String kafkaTopicKey, String data) {
        KafkaProducer producer = new KafkaProducer<String, String>(propertiesProducer);
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopicKey, data);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e != null){

                }
            }
        });
        //you'll need to flush the data as it runs in a background process. flush to send data and resume main thread.
        producer.flush();
        producer.close();
    }

    public KafkaConsumer getConsumerHandle(){

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(propertiesConsumer);
        return consumer;
    }
}
