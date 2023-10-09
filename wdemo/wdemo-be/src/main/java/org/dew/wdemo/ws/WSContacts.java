package org.dew.wdemo.ws;

import java.util.ArrayList;
import java.util.List;

import org.dew.wdemo.bl.Contact;

public 
class WSContacts 
{
  protected static List<Contact> data = new ArrayList<Contact>();
  static {
    data.add(new Contact("Clark Kent"));
    data.add(new Contact("Peter Parker"));
  }

  public static boolean clear() {
    System.out.println("[wdemo-be] WSContacts.clear()...");
    data.clear();
    return true;
  }

  public static List<Contact> find(Contact filter) {
    System.out.println("[wdemo-be] WSContacts.find(" + filter + ")...");
    String filterNameLC = null;
    if(filter != null) {
      filterNameLC = filter.getName();
      if(filterNameLC != null) {
        filterNameLC = filterNameLC.trim().toLowerCase();
      }
    }
    if(filterNameLC != null && filterNameLC.length() > 0) {
      List<Contact> result = new ArrayList<Contact>();
      for(int i = 0; i < data.size(); i++) {
        Contact item = data.get(i);
        if(item == null) continue;
        String itemName = item.getName();
        if(itemName == null || itemName.length() == 0) continue;
        itemName = itemName.toLowerCase();
        if(itemName.indexOf(filterNameLC) >= 0) {
          result.add(item);
        }
      }
      return result;
    }
    return data;
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

  public static boolean remove(Contact contact) throws Exception {
    System.out.println("[wdemo-be] WSContacts.remove(" + contact + ")...");
    if(contact == null || contact.getName() == null) {
      System.err.println("[wdemo-be] WSContacts.remove(" + contact + ") -> Invalid contact");
      throw new Exception("Invalid contact");
    }
    return data.remove(contact);
  }
}
