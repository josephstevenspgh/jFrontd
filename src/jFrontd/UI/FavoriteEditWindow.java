package jFrontd.UI;

import Classes.Globals;
import jFrontd.Classes.Emulator;
import jFrontd.Classes.FavoriteGame;
import jFrontd.Classes.Folder;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class FavoriteEditWindow extends javax.swing.JFrame {
    private final String    Separator           = new String(System.getProperty("file.separator"));
    private final String    home                = new String(System.getProperty("user.home"));
    private final File      ConfigDir           = new File(home+Separator+".jFrontd");
    private final File      ConfigFile          = new File(ConfigDir+Separator+"jFrontd.cfg");
    private final File      FolderDir           = new File(ConfigDir+Separator+"Folders");
    private final File      IconDir             = new File(ConfigDir+Separator+"Icons");
    private final File      EmulatorDir         = new File(ConfigDir+Separator+"Emulators");
    private final File      FavoriteDir         = new File(ConfigDir+Separator+"Favorites");
    private String Name;
    private String Path;
    private int EmulatorID;
    private String Icon;
    private String Comment;
    private String[] IconList;
    private Emulator[] Emulators;
    private MainWindow mainwindow;
    private FavoriteGame favoritegame;
    private SettingsWindow settingswindow;

    public FavoriteEditWindow(MainWindow mainwindow, FavoriteGame favoritegame) {
        this.mainwindow     = mainwindow;
        this.favoritegame   = favoritegame;
        initComponents();
        init();
        jTxtName.setText(favoritegame.getName());
        jTxtPath.setText(favoritegame.getPath());
        jTxtComment.setText(Comment);
        jCmbEmulator.setSelectedIndex(favoritegame.getEmulator());
        Icon = favoritegame.getIcon();
        //find iconindex
        int IconIndex = 0;
        for(int i=0;i<IconList.length;i++){
            if(Icon.equals(IconList[i])){
                IconIndex = i;
            }
        }
        jCmbIcon.setSelectedIndex(IconIndex);
    }

    public FavoriteEditWindow(MainWindow mainwindow, FavoriteGame favoritegame, SettingsWindow settingswindow){
        this.settingswindow = settingswindow;
        this.mainwindow     = mainwindow;
        this.favoritegame   = favoritegame;
        initComponents();
        init();
        jTxtName.setText(favoritegame.getName());
        jTxtPath.setText(favoritegame.getPath());
        jTxtComment.setText(Comment);
        jCmbEmulator.setSelectedIndex(favoritegame.getEmulator());
        Icon = favoritegame.getIcon();
        //find iconindex
        int IconIndex = 0;
        for(int i=0;i<IconList.length;i++){
            if(Icon.equals(IconList[i])){
                IconIndex = i;
            }
        }
        jCmbIcon.setSelectedIndex(IconIndex);
    }

    public FavoriteEditWindow(MainWindow mainwindow, String Name, String Path, Folder folder) {
        this.mainwindow = mainwindow;
        this.Name = Name;
        this.Path = Path;
        this.EmulatorID = folder.getEmu();
        this.Icon = folder.getGameIcon();
        this.Comment = folder.getName() + " game";
        initComponents();
        init();
        jTxtName.setText(Name);
        jTxtPath.setText(Path);
        jTxtComment.setText(Comment);
        jCmbEmulator.setSelectedIndex(EmulatorID);
        //search for selected icon
        int IconIndex = 0;
        for(int i=0;i<IconList.length;i++){
            if(Icon.equals(IconList[i])){
                IconIndex = i;
            }
        }
        jCmbIcon.setSelectedIndex(IconIndex);
    }

    private void init(){
        //clean out combo lists
        jCmbEmulator.removeAllItems();
        jCmbIcon.removeAllItems();

        //load icons
        IconList = IconDir.list();
        Arrays.sort(IconList);
        //display icons
        for(int i=0;i<IconList.length;i++){
            jCmbIcon.addItem(new ImageIcon(IconDir+Globals.Separator+IconList[i]));
        }

        //load emulators
        LoadEmulators();
        for(int i=0;i<Emulators.length;i++){
            jCmbEmulator.addItem(Emulators[i].getName());
        }
    }

    private void KeyHit(int KeyCode){
        switch(KeyCode){
            case KeyEvent.VK_ESCAPE:
                //escape hit, act as if cancel was hit
                jBtnCancel.doClick();
                break;
            case KeyEvent.VK_ENTER:
                //enter hit, act as if OK was hit
                jBtnOK.doClick();
                break;
        }
    }

    private boolean LoadValuesFromForm(){
        Name        = jTxtName.getText();
        Path        = jTxtPath.getText();
        EmulatorID  = jCmbEmulator.getSelectedIndex();
        Icon        = IconList[jCmbIcon.getSelectedIndex()];
        Comment     = jTxtComment.getText();
        //check to see shit is filled out
        if(Name.isEmpty()){
            Globals.ShowError("Please enter a Name.");
            return false;
        }else if(Path.isEmpty()){
            Globals.ShowError("Please enter a Path.");
            return false;
        }else if(!new File(Path).exists()){
            Globals.ShowError("Please enter a Path that exists.");
            return false;
        }else{
            return true;
        }
    }

    private void LoadEmulators() {
        //Check to make sure the emulator directory exists, if not: make it
        if (!EmulatorDir.exists()){
            EmulatorDir.mkdir();
        }
        //initialize emulator array
        Emulators = new Emulator[EmulatorDir.list().length];
        //create emulator variables
        for (int i=0;i<EmulatorDir.list().length;i++){
            Emulators[i] = new Emulator(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnCancel = new javax.swing.JButton();
        jBtnOK = new javax.swing.JButton();
        jPnlFavorites = new javax.swing.JPanel();
        jLblName = new javax.swing.JLabel();
        jTxtName = new javax.swing.JTextField();
        jLblPath = new javax.swing.JLabel();
        jPnlPath = new javax.swing.JPanel();
        jTxtPath = new javax.swing.JTextField();
        jBtnBrowsePath = new javax.swing.JButton();
        jLblEmulator = new javax.swing.JLabel();
        jCmbEmulator = new javax.swing.JComboBox();
        jLblIcon = new javax.swing.JLabel();
        jCmbIcon = new javax.swing.JComboBox();
        jLblComment = new javax.swing.JLabel();
        jTxtComment = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Favorite");
        setMinimumSize(new java.awt.Dimension(500, 200));
        setResizable(false);

        jBtnCancel.setText("Cancel");
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelActionPerformed(evt);
            }
        });
        jBtnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ButtonKeyReleased(evt);
            }
        });

        jBtnOK.setText("OK");
        jBtnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOKActionPerformed(evt);
            }
        });
        jBtnOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ButtonKeyReleased(evt);
            }
        });

        jPnlFavorites.setLayout(new java.awt.GridLayout(5, 0));

        jLblName.setText("Name");
        jPnlFavorites.add(jLblName);

        jTxtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlFavorites.add(jTxtName);

        jLblPath.setText("Path");
        jPnlFavorites.add(jLblPath);

        jTxtPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });

        jBtnBrowsePath.setText("Browse");
        jBtnBrowsePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBrowsePathActionPerformed(evt);
            }
        });
        jBtnBrowsePath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPnlPathLayout = new javax.swing.GroupLayout(jPnlPath);
        jPnlPath.setLayout(jPnlPathLayout);
        jPnlPathLayout.setHorizontalGroup(
            jPnlPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlPathLayout.createSequentialGroup()
                .addComponent(jTxtPath, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnBrowsePath))
        );
        jPnlPathLayout.setVerticalGroup(
            jPnlPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnBrowsePath)
                .addComponent(jTxtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnlFavorites.add(jPnlPath);

        jLblEmulator.setText("Emulator");
        jPnlFavorites.add(jLblEmulator);

        jCmbEmulator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbEmulator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlFavorites.add(jCmbEmulator);

        jLblIcon.setText("Icon");
        jPnlFavorites.add(jLblIcon);

        jCmbIcon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbIcon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlFavorites.add(jCmbIcon);

        jLblComment.setText("Comment");
        jPnlFavorites.add(jLblComment);

        jTxtComment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlFavorites.add(jTxtComment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPnlFavorites, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnOK)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlFavorites, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOK)
                    .addComponent(jBtnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-500)/2, (screenSize.height-200)/2, 500, 200);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBrowsePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBrowsePathActionPerformed
        //add a file chooser to browse for the file
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //open it
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File tempfile = fc.getSelectedFile();
            //tempfile was selected
            jTxtPath.setText(tempfile.getPath());
        }
    }//GEN-LAST:event_jBtnBrowsePathActionPerformed

    private void jBtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOKActionPerformed
        //make sure everything loads ok
        if(!LoadValuesFromForm()){
            return;
        }
        //edit favorite game
        favoritegame.setComment(Comment);
        favoritegame.setEmulator(EmulatorID);
        favoritegame.setIcon(Icon);
        favoritegame.setName(Name);
        favoritegame.setPath(Path);
        //update lists and close
        mainwindow.init();
        mainwindow.LoadFavorites();
        if(mainwindow.FavoritesSelected){
            mainwindow.jTreeFolders.setSelectionRows(new int[]{0});
        }
        if(settingswindow != null){
            settingswindow.FillList(2);
        }
        this.dispose();
    }//GEN-LAST:event_jBtnOKActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnCancelActionPerformed

    private void GenericKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GenericKeyReleased
        KeyHit(evt.getKeyCode());
    }//GEN-LAST:event_GenericKeyReleased

    private void ButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER){
            KeyHit(evt.getKeyCode());
        }
    }//GEN-LAST:event_ButtonKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBrowsePath;
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnOK;
    private javax.swing.JComboBox jCmbEmulator;
    private javax.swing.JComboBox jCmbIcon;
    private javax.swing.JLabel jLblComment;
    private javax.swing.JLabel jLblEmulator;
    private javax.swing.JLabel jLblIcon;
    private javax.swing.JLabel jLblName;
    private javax.swing.JLabel jLblPath;
    private javax.swing.JPanel jPnlFavorites;
    private javax.swing.JPanel jPnlPath;
    private javax.swing.JTextField jTxtComment;
    private javax.swing.JTextField jTxtName;
    private javax.swing.JTextField jTxtPath;
    // End of variables declaration//GEN-END:variables
}
