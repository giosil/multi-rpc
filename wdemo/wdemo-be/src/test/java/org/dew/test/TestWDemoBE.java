package org.dew.test;

import java.util.List;

import org.dew.wdemo.bl.Contact;
import org.dew.wdemo.ws.WSContacts;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestWDemoBE extends TestCase {
  
  public TestWDemoBE(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestWDemoBE.class);
  }
  
  public void testApp() throws Exception {
    Contact contact = new Contact("test", "test@dew.org");
    
    System.out.println("Test WSContacts.clear...");
    WSContacts.clear();
    
    System.out.println("Test WSContacts.add...");
    WSContacts.add(contact);
    
    System.out.println("Test WSContacts.list...");
    List<Contact> listContact = WSContacts.list();
    assertNotNull(listContact);
    
    assertEquals(1, listContact.size());
    
    System.out.println("Test WSContacts.remove...");
    int removeResult = WSContacts.remove(contact);
    assertEquals(1, removeResult);
    
    System.out.println("Test WSContacts.init...");
    WSContacts.init();
    
    System.out.println("Test WSContacts.find...");
    List<Contact> listFindContact = WSContacts.find(new Contact("Peter"));
    assertNotNull(listFindContact);
    
    assertTrue(listFindContact.size() > 0);
    
    listFindContact = WSContacts.find(new Contact("Peter", "test"));
    assertNotNull(listFindContact);
    assertTrue(listFindContact.size() == 0);
  }
}
