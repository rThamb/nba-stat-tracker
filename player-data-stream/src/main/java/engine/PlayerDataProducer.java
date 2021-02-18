package engine;

import data.PlayerRepo;
import data.YahooPlayerData;
import kafka.KafkaClient;
import logger.MyLogger;
import model.Jsonify;
import model.PlayerStat;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlayerDataProducer {

    private static final String KAFKA_TOPIC = "player";
    private static final Logger log = MyLogger.getLogger();
    private PlayerRepo datacenter;
    private KafkaClient kafka;

    public PlayerDataProducer(){
        this.datacenter = new YahooPlayerData();
        this.kafka = new KafkaClient();
    }

    //polling process will pull data once a day
    public void run(){

        log.info("** Getting Info from API **");
        String producerRecord = serializerData(datacenter.getPlayers());

        log.info("** Sending data to message broker **");
        kafka.produceMessage(KAFKA_TOPIC, producerRecord);

        log.info("Iteration complete. Waiting for next period ...");

    }


    private String serializerData(List<PlayerStat> players){
        JSONArray array = new JSONArray();

        for(Jsonify player: players)
            array.put(player.toJson());

        return array.toString();

    }
}
