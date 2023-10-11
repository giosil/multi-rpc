package org.dew.wdemo.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dew.wdemo.bl.Contact;

public 
class WSContacts 
{
  protected static List<Contact> data = new ArrayList<Contact>();
  
  static {
    init();
  }

  public static int init() {
    System.out.println("[wdemo-be] WSContacts.init()...");
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
    System.out.println("[wdemo-be] WSContacts.clear()...");
    data.clear();
    return true;
  }

  public static List<Contact> find() {
    System.out.println("[wdemo-be] WSContacts.find()...");
    return data;
  }

  public static List<Contact> find(Contact filter) {
    System.out.println("[wdemo-be] WSContacts.find(" + filter + ")...");
    
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
    System.out.println("[wdemo-be] WSContacts.list()...");
    return data;
  }

  public static boolean add(Contact contact) throws Exception {
    System.out.println("[wdemo-be] WSContacts.add(" + contact + ")...");
    if(contact == null || contact.getName() == null) {
      System.err.println("[wdemo-be] WSContacts.add(" + contact + ") -> Invalid contact");
      throw new Exception("Invalid contact");
    }
    String name = contact.getName();
    if(name.equalsIgnoreCase("err")) {
      System.err.println("[wdemo-be] WSContacts.add(" + contact + ") -> Test error");
      throw new Exception("Test error");
    }
    if(data.indexOf(contact) >= 0) {
      return false;
    }
    data.add(contact);
    return true;
  }

  public static int remove(Contact contact) throws Exception {
    System.out.println("[wdemo-be] WSContacts.remove(" + contact + ")...");
    if(contact == null || contact.getName() == null) {
      System.err.println("[wdemo-be] WSContacts.remove(" + contact + ") -> Invalid contact");
      throw new Exception("Invalid contact");
    }
    
    int result = 0;
    
    boolean applyFilterName  = false;
    boolean applyFilterEmail = false;
    
    String filterNameLC  = null;
    String filterEmailLC = null;
    if(contact != null) {
      filterNameLC = contact.getName();
      if(filterNameLC != null) {
        filterNameLC = filterNameLC.trim().toLowerCase();
        applyFilterName = filterNameLC.length() > 0;
      }
      filterEmailLC = contact.getEmail();
      if(filterEmailLC != null) {
        filterEmailLC = filterEmailLC.trim().toLowerCase();
        applyFilterEmail = filterEmailLC.length() > 0;
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
        itemName  = itemName.toLowerCase();
        itemEmail = itemEmail.toLowerCase();
        if(itemName.indexOf(filterNameLC) >= 0 && itemEmail.indexOf(filterEmailLC) >= 0) {
          iterator.remove();
          result++;
        }
      }
      else if(applyFilterName) {
        if(itemName == null) continue;
        itemName = itemName.toLowerCase();
        if(itemName.indexOf(filterNameLC) >= 0) {
          iterator.remove();
          result++;
        }
      }
      else if(applyFilterEmail) {
        if(itemEmail == null) continue;
        itemEmail = itemEmail.toLowerCase();
        if(itemEmail.indexOf(filterEmailLC) >= 0) {
          iterator.remove();
          result++;
        }
      }
    }
    return result;
  }
}
