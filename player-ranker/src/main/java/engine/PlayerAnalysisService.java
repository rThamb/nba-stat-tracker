package engine;

import analysis.StatAnalyzer;
import kafka.KafkaClient;
import logger.MyLogger;
import model.PlayerStat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAnalysisService {

    private static final Logger log = MyLogger.getLogger();
    private KafkaClient kafkaClient;
    private String KAFKA_GUARD_TOPIC = "guard-rank";
    private String KAFKA_FORWARD_TOPIC = "forward-rank";

    public PlayerAnalysisService(){
        this.kafkaClient = new KafkaClient();
    }

    public void run(){
        KafkaConsumer consumer = kafkaClient.getConsumerHandle();

        //now tell the consumer to listen to player data, sub to multiple topic by using a list of topics
        consumer.subscribe(Collections.singleton("player"));
        log.info("Listening to messages from kafka");
        while(true){
            try {
                //The message will only be consumed when the service is ready
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                if (records.count() > 0) {
                    log.info("Received Message");
                    for (ConsumerRecord record : records) {
                        System.out.println(record.value());
                        List<PlayerStat> players = deserialize(record.value().toString());

                        StatAnalyzer analysis = new StatAnalyzer(players);
                        List<PlayerStat> guards = analysis.getGuardRanks();
                        List<PlayerStat> forwards = analysis.getForwardRanks();
                        produceMessage(KAFKA_GUARD_TOPIC, guards);
                        //produceMessage(KAFKA_FORWARD_TOPIC, forwards);
                    }
                } else {
                    log.info("No Message");
                }
            }catch (Exception e){
                log.info("FAILED OPERATION: Current Message failed");
            }
        }

    }

    private List<PlayerStat> deserialize(String kafkaMessage){
        JSONArray playerJSON = new JSONArray(kafkaMessage);
        List<PlayerStat> players = new ArrayList<>();
        for(int i = 0; i < playerJSON.length(); i++){
            JSONObject obj = playerJSON.getJSONObject(i);
            players.add(new PlayerStat(obj));
        }
        return players;
    }

    private void produceMessage(String topic, List<PlayerStat> playersWithRank){
        kafkaClient.produceMessage(topic, formMessage(playersWithRank));
    }

    private String formMessage(List<PlayerStat> players){
        JSONArray arr = new JSONArray();
        for(PlayerStat p : players){
            arr.put(p.toJson());
        }
        return arr.toString();
    }
}
