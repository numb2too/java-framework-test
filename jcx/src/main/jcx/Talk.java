package jcx;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Talk {

    private final Logger logger = Logger.getLogger(Talk.class.getName());

    public Talk() {
        logger.log(Level.INFO, "new talk");

    }

    public Talk(String name) {
        logger.log(Level.INFO, "new talk name:  \"{0}\"", name);
    }

    public String[][] queryFromPool(String sql) {
        logger.log(Level.INFO, "queryFromPool: {0}", sql);
        return new String[][]{};
    }

    public int execFromPool(String sql) {
        logger.log(Level.INFO, "execFromPool: {0}", sql);
        return 0;
    }

    public int execFromPool(String[] sql) {
        logger.log(Level.INFO, "execFromPool: {0}", sql);
        return 0;
    }


}
