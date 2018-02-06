package jFrontd.UI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Classes.Globals;
import jFrontd.Classes.Emulator;
import jFrontd.Classes.Folder;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/*
 * FolderEdit.java
 *
 * Created on May 12, 2009, 2:24:41 PM
 */

/**
 *
 * @author sj
 */
public class FolderEditWindow extends javax.swing.JDialog {
    int Emulator;
    private final File ConfigDir                = new File(Globals.Home+Globals.Separator+".jFrontd");
    private final File IconDir                  = new File(ConfigDir+Globals.Separator+"Icons");
    private final File FolderDir                = new File(ConfigDir+Globals.Separator+"Folders");
    private final File EmulatorDir              = new File(ConfigDir+Globals.Separator+"Emulators");
    private Folder myfolder;
    private MainWindow mw;
    private Emulator[] emulator;
    protected String Name;
    protected String Path;
    protected String SystemIcon;
    protected String GameIcon;
    protected String IconList[];
    protected int EmuID;
    protected boolean showExtensions            = false;
    protected boolean mameROMs                  = false;
    protected boolean hideUnsupportedFiletypes  = false;
    protected boolean goodmerged                = false;


    public FolderEditWindow(java.awt.Frame parent, boolean modal,
            Folder f, MainWindow mainwindow) {
        super(parent, modal);
        myfolder = f;
        mw = mainwindow;
        initComponents();
        init();
    }

    private void init() {
        //populate combo box of emulators
        createEmulators();
        String[] list = new String[emulator.length];
        for(int i=0;i<emulator.length;i++){
            list[i]=emulator[i].getName();
        }
        //populate combo box of Icons
        IconList = IconDir.list();
        Arrays.sort(IconList);
        jCmbSystemIcon.removeAllItems();
        jCmbGameIcon.removeAllItems();
        for(int i=0;i<IconList.length;i++){
            jCmbSystemIcon.addItem(new ImageIcon(IconDir+Globals.Separator+IconList[i]));
            jCmbGameIcon.addItem(new ImageIcon(IconDir+Globals.Separator+IconList[i]));
        }
        jCmbEmulator.setModel(new javax.swing.DefaultComboBoxModel (list));
        //set some variables
        Name                        = myfolder.getName();
        Path                        = myfolder.getPath();
        SystemIcon                  = myfolder.getSystemIcon();
        GameIcon                    = myfolder.getGameIcon();
        Emulator                    = myfolder.getEmu();
        showExtensions              = myfolder.getShowExtensions();
        mameROMs                    = myfolder.getMameROMs();
        goodmerged                  = myfolder.getGoodmerged();
        hideUnsupportedFiletypes    = myfolder.getHideUnsupportedFiletypes();
        //find icon index
        int SystemIndex = 0;
        int GameIndex   = 0;
        for(int i=0;i<IconList.length;i++){
            if(SystemIcon.equals(IconList[i])){
                SystemIndex = i;
            }
            if(GameIcon.equals(IconList[i])){
                GameIndex = i;
            }
        }
        //display some information
        jTxtName        .setText(Name);
        jTxtPath        .setText(Path);
        jCmbEmulator    .setSelectedIndex(Emulator);
        jCmbSystemIcon  .setSelectedIndex(SystemIndex);
        jCmbGameIcon    .setSelectedIndex(GameIndex);
        jChkExtensions  .setSelected(showExtensions);
        jChkMame        .setSelected(mameROMs);
        jChkMerge       .setSelected(goodmerged);
        jChkfiletypes   .setSelected(hideUnsupportedFiletypes);
    }
    
    private boolean LoadValuesFromForm(){
        //make sure data is entered
        if(jTxtName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a Name for the Folder.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(jTxtPath.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a Path for the Folder.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            //step one: get the variables
            Name            = jTxtName.getText();
            EmuID           = jCmbEmulator.getSelectedIndex();
            Path            = jTxtPath.getText();
            SystemIcon      = IconList[jCmbSystemIcon.getSelectedIndex()];
            GameIcon        = IconList[jCmbGameIcon.getSelectedIndex()];
            //get values from the checkboxes
            showExtensions              = jChkExtensions.isSelected();
            mameROMs                    = jChkMame.isSelected();
            goodmerged                  = jChkMerge.isSelected();
            hideUnsupportedFiletypes    = jChkfiletypes.isSelected();
            //return from the function
            return true;
        }
    }

    private void createEmulators() {
        //Check to make sure the emulator directory exists, if not: make it
        if (!EmulatorDir.exists()){
            EmulatorDir.mkdir();
        }
        //initialize folder array
        emulator = new Emulator[EmulatorDir.list().length];
        //create folder variables
        for (int i=0;i<EmulatorDir.list().length;i++){
            emulator[i] = new Emulator(i);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlFolder = new javax.swing.JPanel();
        jPnlSpecialOptions = new javax.swing.JPanel();
        jChkExtensions = new javax.swing.JCheckBox();
        jChkfiletypes = new javax.swing.JCheckBox();
        jChkMame = new javax.swing.JCheckBox();
        jChkMerge = new javax.swing.JCheckBox();
        jBtnOK = new javax.swing.JButton();
        jBtnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtName = new javax.swing.JTextField();
        jTxtPath = new javax.swing.JTextField();
        jBtnBrowse = new javax.swing.JButton();
        jCmbEmulator = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLblSystemIcon = new javax.swing.JLabel();
        jCmbSystemIcon = new javax.swing.JComboBox();
        jLblGameIcon = new javax.swing.JLabel();
        jCmbGameIcon = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Folder");

        jPnlSpecialOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Folder Options"));
        jPnlSpecialOptions.setLayout(new java.awt.GridLayout(2, 0));

        jChkExtensions.setText("Show file extensions");
        jChkExtensions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlSpecialOptions.add(jChkExtensions);

        jChkfiletypes.setText("Show unsupported filetypes");
        jChkfiletypes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlSpecialOptions.add(jChkfiletypes);

        jChkMame.setText("MAME roms");
        jChkMame.setToolTipText("This option will rename the roms so you dont have to look at 8.3 filenames");
        jChkMame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlSpecialOptions.add(jChkMame);

        jChkMerge.setText("GoodMerge roms");
        jChkMerge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPnlSpecialOptions.add(jChkMerge);

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

        jLabel1.setText("Name");

        jLabel2.setText("Path");

        jLabel3.setText("Emulator");

        jTxtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });

        jTxtPath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });

        jBtnBrowse.setText("Browse");
        jBtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBrowseActionPerformed(evt);
            }
        });
        jBtnBrowse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ButtonKeyReleased(evt);
            }
        });

        jCmbEmulator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbEmulator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Icons"));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLblSystemIcon.setText("System Icon");
        jPanel1.add(jLblSystemIcon);

        jCmbSystemIcon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbSystemIcon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPanel1.add(jCmbSystemIcon);

        jLblGameIcon.setText("Game Icon");
        jPanel1.add(jLblGameIcon);

        jCmbGameIcon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbGameIcon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GenericKeyReleased(evt);
            }
        });
        jPanel1.add(jCmbGameIcon);

        javax.swing.GroupLayout jPnlFolderLayout = new javax.swing.GroupLayout(jPnlFolder);
        jPnlFolder.setLayout(jPnlFolderLayout);
        jPnlFolderLayout.setHorizontalGroup(
            jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlFolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(jPnlSpecialOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlFolderLayout.createSequentialGroup()
                        .addComponent(jBtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnOK))
                    .addGroup(jPnlFolderLayout.createSequentialGroup()
                        .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addGroup(jPnlFolderLayout.createSequentialGroup()
                                .addComponent(jTxtPath, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnBrowse))
                            .addComponent(jCmbEmulator, javax.swing.GroupLayout.Alignment.LEADING, 0, 358, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPnlFolderLayout.setVerticalGroup(
            jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlFolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCmbEmulator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlSpecialOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOK)
                    .addComponent(jBtnCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlFolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-434)/2, (screenSize.height-349)/2, 434, 349);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOKActionPerformed
        if(LoadValuesFromForm()){
            myfolder.setName(Name);
            myfolder.setPath(Path);
            myfolder.setSystemIcon(SystemIcon);
            myfolder.setGameIcon(GameIcon);
            myfolder.setEmu(EmuID);
            myfolder.setGoodmerged(goodmerged);
            myfolder.setHideUnsupportedFiletypes(hideUnsupportedFiletypes);
            myfolder.setMameROMs(mameROMs);
            myfolder.setShowExtensions(showExtensions);
            mw.init();
            this.dispose();
        }
}//GEN-LAST:event_jBtnOKActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
        this.dispose();
}//GEN-LAST:event_jBtnCancelActionPerformed

    private void jBtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBrowseActionPerformed
        //add a file chooser to browse directories
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //open it
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File tempfile = fc.getSelectedFile();
            //tempfile was selected
            jTxtPath.setText(tempfile.getPath());
        }
}//GEN-LAST:event_jBtnBrowseActionPerformed

    private void GenericKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GenericKeyReleased
        KeyHit(evt.getKeyCode());
    }//GEN-LAST:event_GenericKeyReleased

    private void ButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ButtonKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER){
            GenericKeyReleased(evt);
        }
    }//GEN-LAST:event_ButtonKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBrowse;
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnOK;
    private javax.swing.JCheckBox jChkExtensions;
    private javax.swing.JCheckBox jChkMame;
    private javax.swing.JCheckBox jChkMerge;
    private javax.swing.JCheckBox jChkfiletypes;
    private javax.swing.JComboBox jCmbEmulator;
    private javax.swing.JComboBox jCmbGameIcon;
    private javax.swing.JComboBox jCmbSystemIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLblGameIcon;
    private javax.swing.JLabel jLblSystemIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlFolder;
    private javax.swing.JPanel jPnlSpecialOptions;
    private javax.swing.JTextField jTxtName;
    private javax.swing.JTextField jTxtPath;
    // End of variables declaration//GEN-END:variables

    private void KeyHit(int KeyCode) {
        switch(KeyCode){
            case KeyEvent.VK_ESCAPE:
                //escape hit: act as if it were the cancel button
                jBtnCancel.doClick();
                break;
            case KeyEvent.VK_ENTER:
                //act as if OK were hit
                jBtnOK.doClick();
                break;
        }
    }
}
