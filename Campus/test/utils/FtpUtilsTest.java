/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.File;
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
public class FtpUtilsTest {
  
  private static final String NAME_FILE_IMG_ON_FTP = "123.png";
  
  private static final String PATH_TO_A_FILE_IMG_LOCAL = "/home/biron/Desktop/icone_boss2.png";
  private static final String PATH_DOSSIER_LOCAL = "./remoteFiles/";
  
  public FtpUtilsTest() {
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
   * Test of browseFileNamesOnFtp method, of class FtpUtils.
   */
  @Test
  public void testBrowseFileNamesOnFtp() {
    boolean result = FtpUtils.browseFileNamesOnFtp().length > 0;
    assertTrue(result);
  }

  /**
   * Test of uploadFileOnFtp method, of class FtpUtils.
   */
  @Test
  public void testUploadFileOnFtp() {
    boolean result = FtpUtils.uploadFileOnFtp("toto.png", new File(PATH_TO_A_FILE_IMG_LOCAL));
    if(result){
      FtpUtils.deleteFileOnFtp("toto.png");
    }
    assertTrue(result);
  }

  /**
   * Test of deleteFileOnFtp method, of class FtpUtils.
   */
  @Test
  public void testDeleteFileOnFtp() {
    boolean resultBis = FtpUtils.uploadFileOnFtp("toto.png", new File(PATH_TO_A_FILE_IMG_LOCAL));
    boolean result = false;
    if(resultBis){
      result = FtpUtils.deleteFileOnFtp("toto.png");
    }
    assertTrue(result);
  }

  /**
   * Test of downloadFromFtp method, of class FtpUtils.
   */
  @Test
  public void testDownloadFromFtp() {
    boolean result = FtpUtils.downloadFromFtp(NAME_FILE_IMG_ON_FTP);
    // On supprime le fichier s'il existe
    File file = new File(PATH_DOSSIER_LOCAL + NAME_FILE_IMG_ON_FTP);
    if(file.exists()) {
      file.delete();
    }
    assertTrue(result);
  }
  
}
