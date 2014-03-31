/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Madeleine
 */
public class UIUtils {

    /**
     * Center a JInternalFrame in the DesktopPane
     * ------------------------------------------
     * @param jif 
     * @param desktopPane 
     */
        public static void centerJIF(JInternalFrame jif, JDesktopPane desktopPane) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
        
        /**
     * Center a JInternalFrame in the DesktopPane
     * ------------------------------------------
     * @param panel
     * @param jif 
     */
        public static void centerPanel(JPanel panel, JDesktopPane jif) {
//        Dimension jInternalFrameSize = jif.getSize();
//        Dimension  panelSize = panel.getSize();
//        int width = (jInternalFrameSize.width - panelSize.width) / 2;
//        int height = (jInternalFrameSize.height - panelSize.height) / 2;
//        panel.setLocation(width, height);
    }
    

    /**
     * Set a JInternalFrame with DesktopPane size
     * ------------------------------------------
     * @param jif 
     * @param desktopPane 
     */
        public static void maxJIF(JInternalFrame jif, JDesktopPane desktopPane) {
        Rectangle desktopBounds = desktopPane.getBounds();
        
        jif.setBounds(desktopBounds);
        jif.setVisible(true);
    }
}
