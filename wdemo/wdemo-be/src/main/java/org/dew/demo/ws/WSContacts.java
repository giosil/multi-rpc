package org.dew.demo.ws;

import java.util.ArrayList;
import java.util.List;

import org.dew.demo.bl.Contact;

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
