package com.ren.playersocketapi.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Scope("prototype")
public class PlayerConsumer {

    private String bootstrapServers = "localhost:9092";
    private Properties properties;

    public PlayerConsumer(){
        this.properties = new Properties();

        properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ranker-client");

        //Options (latest/earliest/none)
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    }

    public KafkaConsumer getConsumerHandle(){
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        return consumer;
    }
}
