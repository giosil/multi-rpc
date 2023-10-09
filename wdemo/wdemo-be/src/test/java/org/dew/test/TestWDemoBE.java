package org.dew.test;

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
  }
}
