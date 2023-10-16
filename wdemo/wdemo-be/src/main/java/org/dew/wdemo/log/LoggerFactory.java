package org.dew.wdemo.log;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public 
class LoggerFactory 
{
  public static final String LOG_FOLDER  = System.getProperty("user.home") + File.separator + "log";
  public static final String LOGGER_NAME = "org.dew.wdemo";
  public static final Level  LOG_LEVEL   = Level.ALL;
  
  static {
    
    init();
    
  }
  
  private static
  void init()
  {
    try {
      // Check log folder
      File fileLogFolder = new File(LOG_FOLDER);
      if(!fileLogFolder.exists()) fileLogFolder.mkdirs();
      
      // Logger configuration
      Logger logger = Logger.getLogger(LOGGER_NAME);
      
      LogFormatter formatter = new LogFormatter();
      
      Handler consoleHandler = new ConsoleHandler();
      consoleHandler.setFormatter(formatter);
      // Overwrite ConsoleHandler level
      consoleHandler.setLevel(LOG_LEVEL);
      
      Handler fileHandler = new FileHandler(LOG_FOLDER + File.separator + LOGGER_NAME + ".log", true);
      fileHandler.setFormatter(formatter);
      
      logger.addHandler(consoleHandler);
      logger.addHandler(fileHandler);
      logger.setLevel(LOG_LEVEL);
      logger.setUseParentHandlers(false);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public static
  Logger getLogger()
  {
    return Logger.getLogger(LOGGER_NAME);
  }
  
  public static
  Logger getLogger(String name)
  {
    if(name == null || name.length() == 0) {
      return Logger.getLogger(LOGGER_NAME);
    }
    return Logger.getLogger(name);
  }
  
  public static
  Logger getLogger(Class<?> c)
  {
    if(c == null) {
      return Logger.getLogger(LOGGER_NAME);
    }
    return Logger.getLogger(c.getName());
  }
}
