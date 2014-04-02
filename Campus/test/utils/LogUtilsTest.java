/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author biron
 */
public class LogUtilsTest {
  
  private static final String PATH_LOG_FILE = "./log/log.xml";
  
  private Document document;
  
  
  
  public LogUtilsTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of addLog method, of class LogUtils.
   */
  @Test
  public void testAddLog() throws JDOMException, IOException {
    LogUtils.addLog("test", "ceci est un test, suppr cette ligne");
    
    // on "ouvre"
    SAXBuilder sxb  = new SAXBuilder();
    document        = sxb.build(new File(PATH_LOG_FILE));
    
    int nbElemLog = document.getRootElement().getChildren().size();
    Element lastElement = document.getRootElement().getChildren().get(nbElemLog-1);
    boolean result = lastElement.getChild("description").getValue().equals("ceci est un test, suppr cette ligne")
                      && lastElement.getAttribute("userlogin").getValue().equals("test");
    
    // on "ferme"
    XMLOutputter exit = new XMLOutputter(Format.getPrettyFormat());
    exit.output(document, new FileOutputStream(PATH_LOG_FILE));
    
    assertTrue(result);
    LogUtils.deleteLastLog();
  }
  
  /**
   * Test of deleteLastLog method, of class LogUtils.
   */
  @Test
  public void testDeleteLastLog() throws JDOMException, IOException {
    LogUtils.addLog("test_a_suppr", "ceci est un test, suppr cette ligne");
    LogUtils.deleteLastLog();
    
    // on "ouvre"
    SAXBuilder sxb  = new SAXBuilder();
    document        = sxb.build(new File(PATH_LOG_FILE));
    
    int nbElemLog = document.getRootElement().getChildren().size();
    Element lastElement = document.getRootElement().getChildren().get(nbElemLog-1);
    boolean result = !lastElement.getChild("description").getValue().equals("ceci est un test, suppr cette ligne")
                      && !lastElement.getAttribute("userlogin").getValue().equals("test");
    
    // on "ferme"
    XMLOutputter exit = new XMLOutputter(Format.getPrettyFormat());
    exit.output(document, new FileOutputStream(PATH_LOG_FILE));
    
    assertTrue(result);
  }
  
}
