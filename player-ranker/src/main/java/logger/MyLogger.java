package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

    private static final Logger log = LoggerFactory.getLogger(MyLogger.class);

    public static Logger getLogger(){
        return log;
    }
}
