/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jFrontd.Classes;

import Classes.Globals;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sj
 */
public class SystemTrayThreaded extends Thread {
    SystemTray tray;
    TrayIcon trayico;
    Process proc;
    public SystemTrayThreaded(Process p){
        proc = p;
    }

    public void doTray() {
        if(!SystemTray.isSupported()){
            return;
        }
        try {
            ActionListener ALstop = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //hide the window
                        proc.destroy();
                    }
                };
            //get the system tray instance
            tray = SystemTray.getSystemTray();
            //load an image
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/jFrontd/data/sonic.png"));
            //create the popup window
            PopupMenu popup = new PopupMenu("jFrontd");
            //make the stop menu
            MenuItem MnuStop = new MenuItem("Stop game");
            MnuStop.addActionListener(ALstop);
            popup.add(MnuStop);
            trayico = new TrayIcon(image, "jFrontd", popup);
            trayico.setImageAutoSize(true);
            tray.add(trayico);
        } catch (AWTException ex) {
            Globals.ShowError(ex);
        }
        try {
            SystemTrayThreaded.sleep(10);
        } catch (InterruptedException ex) {
            Globals.ShowError(ex);
        }
    }
    public void done() {
        if(!SystemTray.isSupported()){
            return;
        }
        tray.remove(trayico);
    }

}
