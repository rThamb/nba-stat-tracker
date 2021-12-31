package engine;

import data.PlayerRepo;
import data.ApiPlayerData;
import kafka.KafkaClient;
import logger.MyLogger;
import model.Jsonify;
import model.PlayerStat;
import org.json.JSONArray;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

public class PlayerDataProducer {

    private static final String KAFKA_TOPIC = "player";
    private static final Logger log = MyLogger.getLogger();
    private PlayerRepo datacenter;
    private KafkaClient kafka;

    private static final int ITERATION_WAIT = 60000;

    public PlayerDataProducer() throws IOException {
        this.datacenter = new ApiPlayerData();
        this.kafka = new KafkaClient();
    }

    //polling process will pull data once a day
    public void run(){

        while(true) {

            log.info("** Getting Info from API **");
            String producerRecord = serializerData(datacenter.getPlayers());

            log.info("** Sending data to message broker **");
            try {
                kafka.produceMessage(KAFKA_TOPIC, producerRecord);
            }catch (Exception e){
                log.info("*** Failed to produce message to KAFKA ***");
                e.printStackTrace();
            }

            log.info("Iteration complete. Waiting for next period ...");
            try {
                Thread.sleep(ITERATION_WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private String serializerData(List<PlayerStat> players){
        JSONArray array = new JSONArray();

        for(Jsonify player: players)
            array.put(player.toJson());

        return array.toString();

    }
}
