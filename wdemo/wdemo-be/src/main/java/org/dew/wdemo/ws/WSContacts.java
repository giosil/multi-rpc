package org.dew.wdemo.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.dew.wdemo.bl.Contact;
import org.dew.wdemo.log.LoggerFactory;

public 
class WSContacts 
{
  protected static Logger logger = LoggerFactory.getLogger(WSContacts.class);
  
  protected static List<Contact> data = new ArrayList<Contact>();
  
  static {
    init();
  }

  public static int init() {
    logger.fine("init()...");
    
    if(data == null) data = new ArrayList<Contact>();
    data.clear();
    data.add(new Contact("Clark Kent",      "superman@dew.org"));
    data.add(new Contact("Peter Parker",    "spiderman@dew.org"));
    data.add(new Contact("Tony Stark",      "ironman@dew.org"));
    data.add(new Contact("Bruce Banner",    "hulk@dew.org"));
    data.add(new Contact("Steve Rogers",    "captainamerica@dew.org"));
    data.add(new Contact("Stephen Strange", "doctorstrange@dew.org"));
    return data.size();
  }

  public static boolean clear() {
    logger.fine("clear()...");
    
    data.clear();
    return true;
  }

  public static List<Contact> find() {
    logger.fine("find()...");
    
    return data;
  }

  public static List<Contact> find(Contact filter) {
    logger.fine("find(" + filter + ")...");
    
    boolean applyFilterName  = false;
    boolean applyFilterEmail = false;
    
    String filterNameLC  = null;
    String filterEmailLC = null;
    if(filter != null) {
      filterNameLC = filter.getName();
      if(filterNameLC != null) {
        filterNameLC = filterNameLC.trim().toLowerCase();
        applyFilterName = filterNameLC.length() > 0;
      }
      filterEmailLC = filter.getEmail();
      if(filterEmailLC != null) {
        filterEmailLC = filterEmailLC.trim().toLowerCase();
        applyFilterEmail = filterEmailLC.length() > 0;
      }
    }
    
    if(!applyFilterName && !applyFilterEmail) {
      return data;
    }
    
    List<Contact> result = new ArrayList<Contact>();
    
    Iterator<Contact> iterator = data.iterator();
    while(iterator.hasNext()) { 
      Contact item = iterator.next();
      if(item == null) continue;
      
      String itemName  = item.getName();
      String itemEmail = item.getEmail();
      
      if(applyFilterName && applyFilterEmail) {
        if(itemName == null || itemEmail == null) continue;
        itemName  = itemName.toLowerCase();
        itemEmail = itemEmail.toLowerCase();
        if(itemName.indexOf(filterNameLC) >= 0 && itemEmail.indexOf(filterEmailLC) >= 0) {
          result.add(item);
        }
      }
      else if(applyFilterName) {
        if(itemName == null) continue;
        itemName = itemName.toLowerCase();
        if(itemName.indexOf(filterNameLC) >= 0) {
          result.add(item);
        }
      }
      else if(applyFilterEmail) {
        if(itemEmail == null) continue;
        itemEmail = itemEmail.toLowerCase();
        if(itemEmail.indexOf(filterEmailLC) >= 0) {
          result.add(item);
        }
      }
    }
    
    return result;
  }
  
  public static List<Contact> list() {
    logger.fine("list()...");
    
    return data;
  }

  public static boolean add(Contact contact) throws Exception {
    logger.fine("add(" + contact + ")...");
    // Validate
    if(contact == null || contact.getName() == null) {
      logger.severe("add(" + contact + ") -> Invalid contact");
      throw new Exception("Invalid contact");
    }
    String name = contact.getName();
    if(name.equalsIgnoreCase("err")) {
      logger.severe("add(" + contact + ") -> Test error");
      throw new Exception("Test error");
    }
    if(data.indexOf(contact) >= 0) {
      return false;
    }
    // Add
    data.add(contact);
    return true;
  }

  public static int remove(Contact contact) throws Exception {
    logger.fine("remove(" + contact + ")...");
    if(contact == null || contact.getName() == null) {
      logger.severe("remove(" + contact + ") -> Invalid contact");
      throw new Exception("Invalid contact");
    }
    
    int result = 0;
    
    boolean applyFilterName  = false;
    boolean applyFilterEmail = false;
    
    String filterName  = null;
    String filterEmail = null;
    if(contact != null) {
      filterName = contact.getName();
      if(filterName != null) {
        filterName = filterName.trim();
        applyFilterName = filterName.length() > 0;
      }
      filterEmail = contact.getEmail();
      if(filterEmail != null) {
        filterEmail = filterEmail.trim();
        applyFilterEmail = filterEmail.length() > 0;
      }
    }
    
    if(!applyFilterName && !applyFilterEmail) {
      return result;
    }
    
    Iterator<Contact> iterator = data.iterator();
    while(iterator.hasNext()) { 
      Contact item = iterator.next();
      if(item == null) continue;
      
      String itemName  = item.getName();
      String itemEmail = item.getEmail();
      
      if(applyFilterName && applyFilterEmail) {
        if(itemName == null || itemEmail == null) continue;
        if(itemName.equals(filterName) && itemEmail.equals(filterEmail)) {
          iterator.remove();
          result++;
        }
      }
      else if(applyFilterName) {
        if(itemName == null) continue;
        if(itemName.equals(filterName)) {
          iterator.remove();
          result++;
        }
      }
      else if(applyFilterEmail) {
        if(itemEmail == null) continue;
        if(itemEmail.equals(filterEmail)) {
          iterator.remove();
          result++;
        }
      }
    }
    return result;
  }
}
