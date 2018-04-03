/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jetty9;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author hoang
 */
public class LoggingHandler {

    public Logger logger;
    private FileHandler fileTxt;
    private SimpleFormatter formatTxt;

    public LoggingHandler(String filename) throws IOException {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        fileTxt = new FileHandler(filename);
        formatTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatTxt);
        logger.addHandler(fileTxt);
        logger.setLevel(Level.ALL);
    }

    public LoggingHandler() throws IOException {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        fileTxt = new FileHandler("Log.txt");
        formatTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatTxt);
        logger.addHandler(fileTxt);
        logger.setLevel(Level.ALL);
    }

    public void setSevereMsg(String msg) {
        logger.severe(msg);
    }

    public void setWarningMsg(String msg) {
        logger.warning(msg);
    }

    public void setInfoMsg(String msg) {
        logger.info(msg);
    }

    public void setConfigMsg(String msg) {
        logger.config(msg);
    }

    public void setFineMsg(String msg) {
        logger.fine(msg);
    }

}
