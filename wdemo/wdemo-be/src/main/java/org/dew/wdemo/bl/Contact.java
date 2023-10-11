package org.dew.wdemo.bl;

import java.io.Serializable;

public 
class Contact implements Serializable 
{
  private static final long serialVersionUID = 7068280822811696879L;
  
  private String name;
  private String email;

  public Contact() {
  }

  public Contact(String name) {
    this.name = name;
  }

  public Contact(String name, String email) {
    this.name  = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Contact) {
      String objName  = ((Contact) object).getName();
      String objEmail = ((Contact) object).getEmail();
      String objVal   = objName + ":" + objEmail;
      return objVal.equals(name + ":" + email);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (name + ":" + email).hashCode();
  }

  @Override
  public String toString() {
    return "Contact(" + name + "," + email + ")";
  }
}
