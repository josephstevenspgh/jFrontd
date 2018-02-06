/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jFrontd.Classes;

import Classes.Globals;
import jFrontd.UI.MainWindow;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author sj
 */
public class RomTreeRenderer extends JLabel implements TreeCellRenderer{
    private String IconList[];
    private final File ConfigDir                = new File(Globals.Home+Globals.Separator+".jFrontd");
    private final File IconDir                  = new File(ConfigDir+Globals.Separator+"Icons");
    private final File FolderDir                = new File(ConfigDir+Globals.Separator+"Folders");
    private final File EmulatorDir              = new File(ConfigDir+Globals.Separator+"Emulators");
    private MainWindow mainwindow;

    //constructor
    public RomTreeRenderer(MainWindow mainwindow){
        this.mainwindow = mainwindow;
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        //check to see if favorites are selected
        if(mainwindow.getFavoritesSelected()){
            //favorites is selected
            //load favorite icons
            String[] FavoriteIcons = mainwindow.getFavoriteIcons();
            int sel = row-1;
            if(sel < 0){
                //set text and icon
                this.setIcon(new ImageIcon(IconDir+Globals.Separator+mainwindow.FavoriteIcon));
                this.setText(value.toString());
            }else{
                //set text and icon
                this.setIcon(new ImageIcon(IconDir+Globals.Separator+FavoriteIcons[sel]));
                this.setText(value.toString());
            }
        }else{
            //favorites are not selected
            //set the icon
            int sel = row-1;
            if(sel < 0){
                this.setIcon(new ImageIcon(IconDir+Globals.Separator+mainwindow.getSelectedFolder().getSystemIcon()));
            }else{
                this.setIcon(new ImageIcon(IconDir+Globals.Separator+mainwindow.getSelectedFolder().getGameIcon()));
            }
            this.setText(value.toString());
        }
        //set colors
        Color background = Color.WHITE;
        Color foreground = Color.BLACK;
        //set selection colors
        if (selected){
            background = new Color(57, 105, 138);
            foreground = Color.WHITE;
        }
        setBackground(background);
        setForeground(foreground);
        return this;
    }
}
