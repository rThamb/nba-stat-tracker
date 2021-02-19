package com.ren.playersocketapi.background;

import com.ren.playersocketapi.broadcast.PlayerStatBroadcaster;
import com.ren.playersocketapi.kafka.PlayerConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

/**
 * This object is intended to run on a background thread to listen for player data
 * sent by the kafka message broker.
 */

@Component
public class PlayerDataListener implements Runnable {

    @Autowired
    PlayerStatBroadcaster broadcaster;

    private String KAFKA_GUARD_TOPIC = "guard-rank";
    private String KAFKA_FORWARD_TOPIC = "forward-rank";

    @Autowired
    PlayerConsumer kafkaConsumer;


    @Override
    public void run() {

        log(" **** Starting Listener in the background ***");

        KafkaConsumer<String, String> consumer = kafkaConsumer.getConsumerHandle();

        //now tell the consumer to listen to player data, sub to multiple topic by using a list of topics
        consumer.subscribe(Collections.singleton(KAFKA_GUARD_TOPIC));
        log("Listening to messages from kafka");
        while(true){
            try {
                //The message will only be consumed when the service is ready
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));

                if (records.count() > 0) {
                    log("(KAFKA): Received Message");
                    for (ConsumerRecord record : records) {
                        System.out.println(record.value());

                        //send message to all listening web sockets
                        broadcaster.broadcastMessage(record.value().toString());
                    }
                } else {
                    log("(KAFKA): No Message");
                }
            }catch (Exception e){
                log("(KAFKA) FAILED OPERATION: Current Message failed");
            }
        }


    }

    private static void log(String mes){
        System.out.println("BACKGROUND LISTENER: " + mes);
    }
}
