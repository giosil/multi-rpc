package org.dew.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestWDemoFE extends TestCase {
  
  public TestWDemoFE(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    return new TestSuite(TestWDemoFE.class);
  }
  
  public void testApp() throws Exception {
  }
}
