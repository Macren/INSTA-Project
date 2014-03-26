/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Madeleine
 */
public class UIUtils {

    /**
     * Center a JInternalFrame in the DesktopPane
     * ------------------------------------------
     * @param jif 
     */
        public static void centerJIF(JInternalFrame jif, JDesktopPane desktopPane) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
}
