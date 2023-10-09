package org.dew.wdemo.bl;

import java.io.Serializable;

public 
class Contact implements Serializable 
{
  private static final long serialVersionUID = 9167300340509706571L;
  
  private String name;

  public Contact() {
  }

  public Contact(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Contact) {
      String objName = ((Contact) object).getName();
      if(objName == null && this.name == null) return true;
      return objName != null && objName.equals(this.name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    if(name == null) return 0;
    return name.hashCode();
  }

  @Override
  public String toString() {
    return "Contact(" + name + ")";
  }
}
