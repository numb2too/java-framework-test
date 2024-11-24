package jcx;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Base {
    private final Logger logger = Logger.getLogger(Base.class.getName());

    // Get a talk instance by ID
    protected Talk getTalk(String name) {
        return new Talk(name);
    }

    protected Talk getTalk() {
        return new Talk();
    }

    protected String getValue(String objName) {
        return objName + " value";
    }

    protected void setValue(String objName, String value) {
        logger.log(Level.INFO, "setValue objName: {0}, value: {1}", new Object[]{objName, value});
    }

    protected String getName() {
        return "getName";
    }
    protected String getUser() {
        return "50745";
    }
}
