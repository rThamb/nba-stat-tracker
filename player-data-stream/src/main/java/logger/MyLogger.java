package logger;

import engine.PlayerDataProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

    private static final Logger log = LoggerFactory.getLogger(PlayerDataProducer.class);
    
    public static Logger getLogger(){
        return log;
    }
}
