    package jFrontd.UI;

//imports
import Classes.Globals;
import jFrontd.Classes.Emulator;
import jFrontd.Classes.FavoriteGame;
import jFrontd.Classes.Folder;
import jFrontd.Classes.FolderTreeRenderer;
import jFrontd.Classes.RecentGame;
import jFrontd.Classes.RomTreeRenderer;
import jFrontd.Classes.SystemTrayThreaded;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainWindow extends javax.swing.JFrame {
    protected final String    Separator           = new String(System.getProperty("file.separator"));
    protected final String    home                = new String(System.getProperty("user.home"));
    protected final File      ConfigDir           = new File(home+Separator+".jFrontd");
    protected final File      ConfigFile          = new File(ConfigDir+Separator+"jFrontd.cfg");
    protected final File      FolderDir           = new File(ConfigDir+Separator+"Folders");
    protected final File      IconDir             = new File(ConfigDir+Separator+"Icons");
    protected final File      EmulatorDir         = new File(ConfigDir+Separator+"Emulators");
    protected final File      FavoriteDir         = new File(ConfigDir+Separator+"Favorites");
    protected boolean         FavoritesSelected   = true;
    private Folder          currentfolder;
    private String[]        shownlist;
    private String[]        reallist;
    private Folder[]        folder;
    private Emulator[]      emulator;
    private FavoriteGame[]  favoritegame;
    //recent variables
    private RecentGame[]    Recentgame;
    private int             RecentTimeWasted;
    //preference file variables
    public String           FavoriteIcon;
    public String           FavoriteGameIcon;
    public int              OtherTimesPlayed;
    public int              OtherTimeWasted;
    //mouse drag/drop variables
    private int         FavoriteDragIndex, FavoriteDragToIndex;
    private int         FolderDragIndex, FolderDragToIndex;
    private boolean     FavoriteDragging    = false;
    private boolean     FolderDragging      = false;

    private static MainWindow mainwindow;

    public MainWindow() {
        //create recent game var
        Recentgame = new RecentGame[10];
        BlankRecents();
        try {
            //set ui
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            Globals.ShowError(ex);
        }
        initComponents();
        //clear the ROM tree
        jTreeROMs.setModel(new DefaultTreeModel(null));
        //init code
        init();
        jTreeFolders.setSelectionRows(new int[]{0});
        testcode();
    }

    private void testcode(){

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopROMs = new javax.swing.JPopupMenu();
        jPopMnuPlay = new javax.swing.JMenu();
        jPopMnuPlaySelected = new javax.swing.JMenuItem();
        jPopMnuPlayRandomFolder = new javax.swing.JMenuItem();
        jPopMnuPlayRandomEverywhere = new javax.swing.JMenuItem();
        jPopMnuFavorites = new javax.swing.JMenu();
        jPopMnuFavoritesAdd = new javax.swing.JMenuItem();
        jPopMnuFavoritesEdit = new javax.swing.JMenuItem();
        jPopMnuFavoritesDelete = new javax.swing.JMenuItem();
        jPopMnuSort = new javax.swing.JMenu();
        jPopMnuSortName = new javax.swing.JMenuItem();
        jPopMnuSortIcon = new javax.swing.JMenuItem();
        jPopMnuSortTimesPlayed = new javax.swing.JMenuItem();
        jPopMnuSortTimeWasted = new javax.swing.JMenuItem();
        jPopMnuInfo = new javax.swing.JMenuItem();
        jPopFolders = new javax.swing.JPopupMenu();
        jMnuFoldersInfo = new javax.swing.JMenuItem();
        jSepFolder = new javax.swing.JSeparator();
        jMnuFoldersAddFolder = new javax.swing.JMenuItem();
        jMnuFoldersEditFolder = new javax.swing.JMenuItem();
        jMnuFoldersDeleteFolder = new javax.swing.JMenuItem();
        jSepFolder2 = new javax.swing.JSeparator();
        jMnuSortFolders = new javax.swing.JMenuItem();
        mainPanel = new javax.swing.JPanel();
        mainSplitpane = new javax.swing.JSplitPane();
        jScrlFolders = new javax.swing.JScrollPane();
        jTreeFolders = new javax.swing.JTree();
        fileSplitpane = new javax.swing.JSplitPane();
        FilterSplitpane = new javax.swing.JSplitPane();
        btnFilter = new javax.swing.JButton();
        jTxtFilter = new javax.swing.JTextField();
        jScrlROMs = new javax.swing.JScrollPane();
        jTreeROMs = new javax.swing.JTree();
        mainMenuBar = new javax.swing.JMenuBar();
        jMnuFileMenu = new javax.swing.JMenu();
        jMnuRecent = new javax.swing.JMenu();
        jMnuListGames = new javax.swing.JMenuItem();
        jMnuExit = new javax.swing.JMenuItem();
        jMnuEditMenu = new javax.swing.JMenu();
        jMnuFavorites = new javax.swing.JMenu();
        jMnuAddFavorite = new javax.swing.JMenuItem();
        jMnuEditFavorite = new javax.swing.JMenuItem();
        jMnuRemoveFavorite = new javax.swing.JMenuItem();
        jMnuPreferences = new javax.swing.JMenuItem();
        jMnuGameMenu = new javax.swing.JMenu();
        jMnuPlay = new javax.swing.JMenu();
        jMnuPlaySelected = new javax.swing.JMenuItem();
        jMnuRandomFolder = new javax.swing.JMenuItem();
        jMnuRandomAll = new javax.swing.JMenuItem();
        jMnuSort = new javax.swing.JMenu();
        jMnuSortSortByname = new javax.swing.JMenuItem();
        jMnuSortSortByIcon = new javax.swing.JMenuItem();
        jMnuSortSortByTimesPlayed = new javax.swing.JMenuItem();
        jMnuSortSortByTimeWasted = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMnuSortSortFolders = new javax.swing.JMenuItem();
        jMnuInfo = new javax.swing.JMenuItem();
        jMnuHelpMenu = new javax.swing.JMenu();
        jMnuHelp = new javax.swing.JMenuItem();
        jMnuWhatsnew = new javax.swing.JMenuItem();
        jMnuAbout = new javax.swing.JMenuItem();

        jPopMnuPlay.setText("Play");

        jPopMnuPlaySelected.setText("Selected");
        jPopMnuPlaySelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuPlaySelectedActionPerformed(evt);
            }
        });
        jPopMnuPlay.add(jPopMnuPlaySelected);

        jPopMnuPlayRandomFolder.setText("Random (This Folder)");
        jPopMnuPlayRandomFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuPlayRandomFolderActionPerformed(evt);
            }
        });
        jPopMnuPlay.add(jPopMnuPlayRandomFolder);

        jPopMnuPlayRandomEverywhere.setText("Random (All)");
        jPopMnuPlayRandomEverywhere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuPlayRandomEverywhereActionPerformed(evt);
            }
        });
        jPopMnuPlay.add(jPopMnuPlayRandomEverywhere);

        jPopROMs.add(jPopMnuPlay);

        jPopMnuFavorites.setText("Favorites");
        jPopMnuFavorites.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jPopMnuFavoritesMenuSelected(evt);
            }
        });

        jPopMnuFavoritesAdd.setText("Add");
        jPopMnuFavoritesAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuFavoritesAddActionPerformed(evt);
            }
        });
        jPopMnuFavorites.add(jPopMnuFavoritesAdd);

        jPopMnuFavoritesEdit.setText("Edit");
        jPopMnuFavoritesEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuFavoritesEditActionPerformed(evt);
            }
        });
        jPopMnuFavorites.add(jPopMnuFavoritesEdit);

        jPopMnuFavoritesDelete.setText("Delete");
        jPopMnuFavoritesDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuFavoritesDeleteActionPerformed(evt);
            }
        });
        jPopMnuFavorites.add(jPopMnuFavoritesDelete);

        jPopROMs.add(jPopMnuFavorites);

        jPopMnuSort.setText("Sort");
        jPopMnuSort.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jPopMnuSortMenuSelected(evt);
            }
        });

        jPopMnuSortName.setText("Name");
        jPopMnuSortName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuSortNameActionPerformed(evt);
            }
        });
        jPopMnuSort.add(jPopMnuSortName);

        jPopMnuSortIcon.setText("Icon");
        jPopMnuSortIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuSortIconActionPerformed(evt);
            }
        });
        jPopMnuSort.add(jPopMnuSortIcon);

        jPopMnuSortTimesPlayed.setText("Times Played");
        jPopMnuSortTimesPlayed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuSortTimesPlayedActionPerformed(evt);
            }
        });
        jPopMnuSort.add(jPopMnuSortTimesPlayed);

        jPopMnuSortTimeWasted.setText("Time Wasted");
        jPopMnuSortTimeWasted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuSortTimeWastedActionPerformed(evt);
            }
        });
        jPopMnuSort.add(jPopMnuSortTimeWasted);

        jPopROMs.add(jPopMnuSort);

        jPopMnuInfo.setText("Info");
        jPopMnuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopMnuInfoActionPerformed(evt);
            }
        });
        jPopROMs.add(jPopMnuInfo);

        jMnuFoldersInfo.setText("Title");
        jPopFolders.add(jMnuFoldersInfo);
        jPopFolders.add(jSepFolder);

        jMnuFoldersAddFolder.setText("Add Folder..");
        jMnuFoldersAddFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuFoldersAddFolderActionPerformed(evt);
            }
        });
        jPopFolders.add(jMnuFoldersAddFolder);

        jMnuFoldersEditFolder.setText("Edit Folder..");
        jMnuFoldersEditFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuFoldersEditFolderActionPerformed(evt);
            }
        });
        jPopFolders.add(jMnuFoldersEditFolder);

        jMnuFoldersDeleteFolder.setText("Delete Folder");
        jMnuFoldersDeleteFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuFoldersDeleteFolderActionPerformed(evt);
            }
        });
        jPopFolders.add(jMnuFoldersDeleteFolder);
        jPopFolders.add(jSepFolder2);

        jMnuSortFolders.setText("Sort");
        jMnuSortFolders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortFoldersActionPerformed(evt);
            }
        });
        jPopFolders.add(jMnuSortFolders);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("jFrontd");

        mainSplitpane.setBorder(null);
        mainSplitpane.setDividerLocation(150);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeFolders.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeFolders.setAutoscrolls(true);
        jTreeFolders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeFoldersMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTreeFoldersMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTreeFoldersMouseReleased(evt);
            }
        });
        jTreeFolders.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeFoldersValueChanged(evt);
            }
        });
        jTreeFolders.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTreeFoldersMouseDragged(evt);
            }
        });
        jScrlFolders.setViewportView(jTreeFolders);

        mainSplitpane.setLeftComponent(jScrlFolders);

        fileSplitpane.setDividerSize(-1);
        fileSplitpane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        FilterSplitpane.setBorder(null);
        FilterSplitpane.setDividerLocation(100);
        FilterSplitpane.setDividerSize(-1);

        btnFilter.setText("Filter List");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        FilterSplitpane.setLeftComponent(btnFilter);

        jTxtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFilterKeyPressed(evt);
            }
        });
        FilterSplitpane.setRightComponent(jTxtFilter);

        fileSplitpane.setLeftComponent(FilterSplitpane);

        jTreeROMs.setAutoscrolls(true);
        jTreeROMs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeROMsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTreeROMsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTreeROMsMouseReleased(evt);
            }
        });
        jTreeROMs.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTreeROMsMouseDragged(evt);
            }
        });
        jTreeROMs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTreeROMsKeyReleased(evt);
            }
        });
        jScrlROMs.setViewportView(jTreeROMs);

        fileSplitpane.setRightComponent(jScrlROMs);

        mainSplitpane.setRightComponent(fileSplitpane);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainSplitpane, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainSplitpane, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jMnuFileMenu.setText("File");

        jMnuRecent.setText("Recent");
        jMnuRecent.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMnuRecentMenuSelected(evt);
            }
        });
        jMnuFileMenu.add(jMnuRecent);

        jMnuListGames.setText("List Games");
        jMnuListGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuListGamesActionPerformed(evt);
            }
        });
        jMnuFileMenu.add(jMnuListGames);

        jMnuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMnuExit.setText("Exit");
        jMnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuExitActionPerformed(evt);
            }
        });
        jMnuFileMenu.add(jMnuExit);

        mainMenuBar.add(jMnuFileMenu);

        jMnuEditMenu.setText("Edit");

        jMnuFavorites.setText("Favorites");
        jMnuFavorites.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMnuFavoritesMenuSelected(evt);
            }
        });

        jMnuAddFavorite.setText("Add Favorite");
        jMnuAddFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuAddFavoriteActionPerformed(evt);
            }
        });
        jMnuFavorites.add(jMnuAddFavorite);

        jMnuEditFavorite.setText("Edit Favorite");
        jMnuEditFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuEditFavoriteActionPerformed(evt);
            }
        });
        jMnuFavorites.add(jMnuEditFavorite);

        jMnuRemoveFavorite.setText("Delete Favorite");
        jMnuRemoveFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuRemoveFavoriteActionPerformed(evt);
            }
        });
        jMnuFavorites.add(jMnuRemoveFavorite);

        jMnuEditMenu.add(jMnuFavorites);

        jMnuPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMnuPreferences.setText("Preferences");
        jMnuPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuPreferencesActionPerformed(evt);
            }
        });
        jMnuEditMenu.add(jMnuPreferences);

        mainMenuBar.add(jMnuEditMenu);

        jMnuGameMenu.setText("Game");

        jMnuPlay.setText("Play");

        jMnuPlaySelected.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_MASK));
        jMnuPlaySelected.setText("Play Selected");
        jMnuPlaySelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuPlaySelectedActionPerformed(evt);
            }
        });
        jMnuPlay.add(jMnuPlaySelected);

        jMnuRandomFolder.setText("Play Random (This Folder)");
        jMnuRandomFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuRandomFolderActionPerformed(evt);
            }
        });
        jMnuPlay.add(jMnuRandomFolder);

        jMnuRandomAll.setText("Play Random (All)");
        jMnuRandomAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuRandomAllActionPerformed(evt);
            }
        });
        jMnuPlay.add(jMnuRandomAll);

        jMnuGameMenu.add(jMnuPlay);

        jMnuSort.setText("Sort");
        jMnuSort.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMnuSortMenuSelected(evt);
            }
        });

        jMnuSortSortByname.setText("By Name");
        jMnuSortSortByname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortSortBynameActionPerformed(evt);
            }
        });
        jMnuSort.add(jMnuSortSortByname);

        jMnuSortSortByIcon.setText("By Icon");
        jMnuSortSortByIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortSortByIconActionPerformed(evt);
            }
        });
        jMnuSort.add(jMnuSortSortByIcon);

        jMnuSortSortByTimesPlayed.setText("By Times Played");
        jMnuSortSortByTimesPlayed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortSortByTimesPlayedActionPerformed(evt);
            }
        });
        jMnuSort.add(jMnuSortSortByTimesPlayed);

        jMnuSortSortByTimeWasted.setText("By Time Wasted");
        jMnuSortSortByTimeWasted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortSortByTimeWastedActionPerformed(evt);
            }
        });
        jMnuSort.add(jMnuSortSortByTimeWasted);
        jMnuSort.add(jSeparator1);

        jMnuSortSortFolders.setText("Folders");
        jMnuSortSortFolders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSortSortFoldersActionPerformed(evt);
            }
        });
        jMnuSort.add(jMnuSortSortFolders);

        jMnuGameMenu.add(jMnuSort);

        jMnuInfo.setText("Info");
        jMnuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuInfoActionPerformed(evt);
            }
        });
        jMnuGameMenu.add(jMnuInfo);

        mainMenuBar.add(jMnuGameMenu);

        jMnuHelpMenu.setText("Help");

        jMnuHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMnuHelp.setText("Help");
        jMnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuHelpActionPerformed(evt);
            }
        });
        jMnuHelpMenu.add(jMnuHelp);

        jMnuWhatsnew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMnuWhatsnew.setText("Whats new?");
        jMnuWhatsnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuWhatsnewActionPerformed(evt);
            }
        });
        jMnuHelpMenu.add(jMnuWhatsnew);

        jMnuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMnuAbout.setText("About");
        jMnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuAboutActionPerformed(evt);
            }
        });
        jMnuHelpMenu.add(jMnuAbout);

        mainMenuBar.add(jMnuHelpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuExitActionPerformed
        //Exit the program
        System.exit(0);
}//GEN-LAST:event_jMnuExitActionPerformed

    public void SortFGByName(){
        //sort favorites by name
        for(int counter=0;counter<favoritegame.length-1;counter++){
            for(int index=0;index<favoritegame.length-1-counter;index++){
                int comparison = favoritegame[index].getName().compareToIgnoreCase(favoritegame[index+1].getName());
                if(comparison > 0){
                    //swap items
                    favoritegame[index].MoveDown();
                    //fix IDs
                    LoadFavorites();
                }
            }
        }
        //reload Tree if favorites are selected
        if(FavoritesSelected){
            init();
            if(jTreeROMs.isSelectionEmpty()){
                jTreeFolders.setSelectionRow(0);
            }else{
                int selIndex = jTreeROMs.getSelectionRows()[0];
                jTreeFolders.setSelectionRow(0);
                jTreeROMs.setSelectionRow(selIndex);
            }
        }
    }

    public void SortFoldersByName(){
        //sort favorites by name
        for(int counter=0;counter<folder.length-1;counter++){
            for(int index=0;index<folder.length-1-counter;index++){
                int comparison = folder[index].getName().compareToIgnoreCase(folder[index+1].getName());
                if(comparison > 0){
                    //swap items
                    folder[index].MoveDown();
                    //fix IDs
                    init();
                }
            }
        }
    }

    public void SortEmulatorsByName(){
        //sort favorites by name
        for(int counter=0;counter<emulator.length-1;counter++){
            for(int index=0;index<emulator.length-1-counter;index++){
                int comparison = emulator[index].getName().compareToIgnoreCase(emulator[index+1].getName());
                if(comparison > 0){
                    //swap items
                    emulator[index].MoveDown();
                    //fix IDs
                    init();
                }
            }
        }
    }

    public void SortFGByIcon(){
        //sort favorites by Icon
        for(int counter=0;counter<favoritegame.length-1;counter++){
            for(int index=0;index<favoritegame.length-1-counter;index++){
                int comparison = favoritegame[index].getIcon().compareToIgnoreCase(favoritegame[index+1].getIcon());
                if(comparison > 0){
                    //swap items
                    favoritegame[index].MoveDown();
                    //fix IDs
                    LoadFavorites();
                }
            }
        }
        //reload Tree if favorites are selected
        if(FavoritesSelected){
            init();
            if(jTreeROMs.isSelectionEmpty()){
                jTreeFolders.setSelectionRow(0);
            }else{
                int selIndex = jTreeROMs.getSelectionRows()[0];
                jTreeFolders.setSelectionRow(0);
                jTreeROMs.setSelectionRow(selIndex);
            }
        }
    }

    public void SortFGByTimesPlayed(){
        //sort favorites by Icon
        for(int counter=0;counter<favoritegame.length-1;counter++){
            for(int index=0;index<favoritegame.length-1-counter;index++){
                if(favoritegame[index].getTimesPlayed() < favoritegame[index+1].getTimesPlayed()){
                    //swap items
                    favoritegame[index].MoveDown();
                    //fix IDs
                    LoadFavorites();
                }
            }
        }
        //reload Tree if favorites are selected
        if(FavoritesSelected){
            init();
            if(jTreeROMs.isSelectionEmpty()){
                jTreeFolders.setSelectionRow(0);
            }else{
                int selIndex = jTreeROMs.getSelectionRows()[0];
                jTreeFolders.setSelectionRow(0);
                jTreeROMs.setSelectionRow(selIndex);
            }
        }
    }

    public void SortFGByTimeWasted(){
        //sort favorites by Icon
        for(int counter=0;counter<favoritegame.length-1;counter++){
            for(int index=0;index<favoritegame.length-1-counter;index++){
                if(favoritegame[index].getTimeWasted() < favoritegame[index+1].getTimeWasted()){
                    //swap items
                    favoritegame[index].MoveDown();
                    //fix IDs
                    LoadFavorites();
                }
            }
        }
        
        //reload Tree if favorites are selected
        if(FavoritesSelected){
            init();
            if(jTreeROMs.isSelectionEmpty()){
                jTreeFolders.setSelectionRow(0);
            }else{
                int selIndex = jTreeROMs.getSelectionRows()[0];
                jTreeFolders.setSelectionRow(0);
                jTreeROMs.setSelectionRow(selIndex);
            }
        }
    }

    public void LoadConfig(){
        //load settings from config file
        try{
            FileReader fr       = new FileReader(ConfigFile);
            BufferedReader br   = new BufferedReader(fr);
            
            FavoriteIcon        = br.readLine();
            FavoriteGameIcon    = br.readLine();
            OtherTimesPlayed    = Integer.parseInt(br.readLine());
            OtherTimeWasted     = Integer.parseInt(br.readLine());
            br.close();
            fr.close();
        }catch(Exception ex){
            Globals.ShowError(ex);
        }
    }
    
    public void SaveConfig(){
        try {
            //delete the file if it exists
            if (ConfigFile.exists()) {
                ConfigFile.delete();
            }
            //save new file
            ConfigFile.createNewFile();
            FileWriter fw = new FileWriter(ConfigFile);

            //variables to write
            fw.write(FavoriteIcon);
            fw.write(Globals.Newline);
            fw.write(FavoriteGameIcon);
            fw.write(Globals.Newline);
            fw.write(Integer.toString(OtherTimesPlayed));
            fw.write(Globals.Newline);
            fw.write(Integer.toString(OtherTimeWasted));
            

            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Folder getSelectedFolder() {
        return currentfolder;
    }

    public String[] getSystemIcons(){
        String[] Icons = new String[folder.length];
        for(int i=0;i<folder.length;i++){
            Icons[i] = folder[i].getSystemIcon();
        }
        return Icons;
    }

    public String getGameIcon(){
        return currentfolder.getGameIcon();
    }

    private void PopulateFolderTree() {
        //set up the folders
        //Get the directory of folder datafiles
        String[] newinfo = FolderDir.list();

        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Favorites");
        //Set up the model with Directories, make folder objects
        for (int i=0;i<newinfo.length;++i) {
            //Set up the model with nodes
            DefaultMutableTreeNode node2 =
                    new DefaultMutableTreeNode(folder[i].getName());
            node.add(node2);
        }
        //populate the tree with the model
        FolderTreeRenderer FTR = new FolderTreeRenderer(this);
        jTreeFolders.setCellRenderer(FTR);
        jTreeFolders.setModel(new DefaultTreeModel(node));
    }

    private void PopulateROMTree() {
        //blank jTreeROMs
        jTreeROMs.setModel(null);
        //set up the folders
        //Get the directory of folder datafiles
        //or, if favorites are selected, dont
        DefaultMutableTreeNode node = null;
        if(FavoritesSelected){
            node = new DefaultMutableTreeNode("Favorites");
        }else{
            node = new DefaultMutableTreeNode(currentfolder.getName());
        }
        //Set up the model with Directories, make folder objects
        for (int i=0;i<shownlist.length;++i) {
            //Set up the model with nodes
            DefaultMutableTreeNode node2 =
                    new DefaultMutableTreeNode(shownlist[i]);
            node.add(node2);
        }

        //populate the tree with the model
        RomTreeRenderer RTR = new RomTreeRenderer(this);
        jTreeROMs.setCellRenderer(RTR);
        jTreeROMs.setModel(new DefaultTreeModel(node));
    }

    private int getFolder(){
        //get the currently selected folder
        DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)
                jTreeFolders.getLastSelectedPathComponent();

        //if the node is null, cancel
        if (node1==null) return 0;

        //get the path of the folder
        for (int i=0;i<FolderDir.list().length;i++) {
            String temp1 = folder[i].getName();
            String temp2 = node1.toString();
            if(temp1.compareTo(temp2)==0) {
                return i;
            }
        }
        //nothing
        return 0;
    }

    private String getSelectedGame() {
        //if favorites, do it another way
        if(FavoritesSelected){
            return "Favorites Selected";
        }else if(currentfolder.getMameROMs()){
            String game = null;
            game = reallist[jTreeROMs.getSelectionRows()[0] -1];
            return game;
        }else{
            //this method will return a string with the filename of the currently
            //selected game
            String game = null;
            if(jTreeROMs.getSelectionRows()[0] < 1){
                return "failed";
            }
            int selected = (jTreeROMs.getSelectionRows()[0]-1);

            //find the entry on the real list
            //if extensions are hidden, do some extra shit
            //first, create a duplicate of reallist, but without the extensions, like in shown list
            String[] TempList = new String[reallist.length];
            System.arraycopy(reallist, 0, TempList, 0, reallist.length);
            StripExtensions(TempList, currentfolder);
            for(int i=0;i<reallist.length;i++){
                if(shownlist[selected].equals(TempList[i])){
                    game = reallist[i];
                    i = reallist.length;
                }
            }
            return game;
        }
    }

    private void jTreeFoldersValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeFoldersValueChanged
        if(jTreeFolders.isSelectionEmpty()){
            return;
        }
        //clear filter text
        jTxtFilter.setText("");
        //when a new value is selected on the tree
        //load the directory of the new folder
        int[] selection = jTreeFolders.getSelectionRows();
        if(selection[0]>0){
            FavoritesSelected = false;
            //change currentfolder to the currently selected folder
            currentfolder = folder[selection[0]-1];
            LoadDirectory();
        } else {
            //use the favorites setting
            //...if there even are any
            if(FavoriteDir.list().length <= 0){
                return;
            }
            FavoritesSelected = true;
            LoadDirectory();
            DisplayFavorites();
            PopulateROMTree();
            currentfolder = null;
        }
    }//GEN-LAST:event_jTreeFoldersValueChanged

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        //when the filter button is pressed
        //run the filterdir method
        filterList(jTxtFilter.getText());
    }//GEN-LAST:event_btnFilterActionPerformed

    private void jMnuPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuPreferencesActionPerformed
        //show the settings window
        SettingsWindow sw = new SettingsWindow(this, true, this);
}//GEN-LAST:event_jMnuPreferencesActionPerformed

    private void jTxtFilterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFilterKeyPressed
        //if the enter key was pressed
        if (evt.getKeyCode()==10) {
            //filter the list
            filterList(jTxtFilter.getText());
        }
    }//GEN-LAST:event_jTxtFilterKeyPressed

    private void jMnuPlaySelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuPlaySelectedActionPerformed
        //"Play" menu item
        Play();
}//GEN-LAST:event_jMnuPlaySelectedActionPerformed

    private void jMnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuAboutActionPerformed
        //Show the About Window
        AboutWindow aw = new AboutWindow();
        aw.setVisible(true);
}//GEN-LAST:event_jMnuAboutActionPerformed

    private void jMnuHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuHelpActionPerformed
        HelpWindow help = new HelpWindow();
        help.setVisible(true);
}//GEN-LAST:event_jMnuHelpActionPerformed

    private void jMnuWhatsnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuWhatsnewActionPerformed
        WhatsNewWindow wn = new WhatsNewWindow();
        wn.setVisible(true);
}//GEN-LAST:event_jMnuWhatsnewActionPerformed

    private void jTreeFoldersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeFoldersMouseClicked
        //highlight currently selected folder
        jTreeFolders.setSelectionRow(jTreeFolders.getClosestRowForLocation(evt.getX(), evt.getY()));
        //on right click: pull up a menu
        if (evt.getButton()==3){
            //change the title of the header item
            if(FavoritesSelected){
                jMnuFoldersInfo.setText("Favorites");
                jMnuFoldersDeleteFolder.setEnabled(false);
                jMnuFoldersEditFolder.setEnabled(false);
            }else{
                String title = folder[getFolder()].getName();
                jMnuFoldersInfo.setText(title);
                jMnuFoldersDeleteFolder.setEnabled(true);
                jMnuFoldersEditFolder.setEnabled(true);
            }
            //show the pop-up menu
            jPopFolders.show(jTreeFolders, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTreeFoldersMouseClicked

    private void jMnuFoldersAddFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuFoldersAddFolderActionPerformed
        FolderAddWindow faw = null;
        faw = new FolderAddWindow(this, true, this);
        faw.setVisible(true);
    }//GEN-LAST:event_jMnuFoldersAddFolderActionPerformed

    private void jMnuFoldersEditFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuFoldersEditFolderActionPerformed
        DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)
                jTreeFolders.getLastSelectedPathComponent();
        FolderEditWindow few = null;
        few = new FolderEditWindow(this, true, currentfolder, mainwindow);
        few.setVisible(true);
    }//GEN-LAST:event_jMnuFoldersEditFolderActionPerformed

    private void jMnuFoldersDeleteFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuFoldersDeleteFolderActionPerformed
        //ask if the user is sure that they want to delete the folder
        if(JOptionPane.showConfirmDialog
                (null, "Are you sure you want to delete this folder?",
                "Delete Folder",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) <1){
            //delete the folder
            currentfolder.Delete();
            //refresh folder list
            JOptionPane.showMessageDialog(null, "Folder deleted");
            init();
        }
    }//GEN-LAST:event_jMnuFoldersDeleteFolderActionPerformed

    private void jTreeROMsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeROMsMouseClicked
        //highlight currently selected game
        jTreeROMs.setSelectionRow(jTreeROMs.getClosestRowForLocation(evt.getX(), evt.getY()));
        //if we're in the favorites menu, deselect and select certain menu items, if not do the other
        //on right click: pull up a menu
        if (evt.getButton()==3){
            //change the title of the header item
            //show the pop-up menu
            jPopROMs.show(jTreeROMs, evt.getX(), evt.getY());
        }
        //on double click: play rom
        if (evt.getClickCount() >= 2){
            Play();
        }
    }//GEN-LAST:event_jTreeROMsMouseClicked

    private void jMnuAddFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuAddFavoriteActionPerformed
        AddFavorite();
    }//GEN-LAST:event_jMnuAddFavoriteActionPerformed

    private void jMnuRemoveFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuRemoveFavoriteActionPerformed
        DeleteFavorite();
    }//GEN-LAST:event_jMnuRemoveFavoriteActionPerformed

    private void jMnuEditFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuEditFavoriteActionPerformed
        EditFavorite();
    }//GEN-LAST:event_jMnuEditFavoriteActionPerformed

    private void jMnuFavoritesMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMnuFavoritesMenuSelected
        //if favorites are selected, enable/disable
        jMnuEditFavorite.setEnabled(FavoritesSelected);
        jMnuRemoveFavorite.setEnabled(FavoritesSelected);
    }//GEN-LAST:event_jMnuFavoritesMenuSelected

    private void jTreeROMsMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeROMsMouseDragged
        //only work if favorites are selected
        if(FavoritesSelected){
            //set dragging to true if needed
            if(!FavoriteDragging){
                FavoriteDragging = true;
            }
            //change drag-to index
            FavoriteDragToIndex = (jTreeROMs.getClosestRowForLocation(evt.getX(), evt.getY())-1);
            if(FavoriteDragToIndex < 0){
                //dont go past the first or last
                return;
            }else if(FavoriteDragToIndex >= favoritegame.length){
                return;
            }
            //Move the game up/down
            if(FavoriteDragIndex == FavoriteDragToIndex){
                //no change, do nothing
                return;
            }else{
                favoritegame[FavoriteDragIndex].MoveTo(FavoriteDragToIndex);
                FavoriteDragIndex = FavoriteDragToIndex;
                //update lists etc
                init();
                LoadFavorites();
                LoadDirectory();
                jTreeFolders.setSelectionRow(0);
                jTreeROMs.setSelectionRow(FavoriteDragIndex+1);
            }
        }
    }//GEN-LAST:event_jTreeROMsMouseDragged

    private void jTreeROMsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeROMsMousePressed
        //if favorites are selected, change the drag index
        if(FavoritesSelected){
            FavoriteDragIndex = (jTreeROMs.getClosestRowForLocation(evt.getX(), evt.getY())-1);
        }
    }//GEN-LAST:event_jTreeROMsMousePressed

    private void jTreeROMsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeROMsMouseReleased
        //reset dragging
        if(FavoritesSelected){
            if(FavoriteDragging){
                FavoriteDragging = false;
            }
        }
    }//GEN-LAST:event_jTreeROMsMouseReleased


    private void jTreeFoldersMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeFoldersMouseDragged
        //set dragging to true if needed
        if(!FolderDragging){
            FolderDragging = true;
        }
        //change drag-to index
        FolderDragToIndex = (jTreeFolders.getClosestRowForLocation(evt.getX(), evt.getY())-1);
        if(FolderDragToIndex < 0){
            //dont go past the first or last
            return;
        }else if(FolderDragToIndex >= folder.length){
            return;
        }
        //Move the game up/down
        if(FolderDragIndex == FolderDragToIndex){
            //no change, do nothing
            return;
        }else{
            //move it
            folder[FolderDragIndex].MoveTo(FolderDragToIndex);
            FolderDragIndex = FolderDragToIndex;
            //update lists etc
            init();
            jTreeFolders.setSelectionRow(FolderDragToIndex+1);
        }
    }//GEN-LAST:event_jTreeFoldersMouseDragged

    private void jTreeFoldersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeFoldersMousePressed
        //if favorites are selected, change the drag index
        FolderDragIndex = (jTreeFolders.getClosestRowForLocation(evt.getX(), evt.getY())-1);
    }//GEN-LAST:event_jTreeFoldersMousePressed

    private void jTreeFoldersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeFoldersMouseReleased
        if(FolderDragging){
            FolderDragging = false;
        }
    }//GEN-LAST:event_jTreeFoldersMouseReleased

    private void jMnuRandomFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuRandomFolderActionPerformed
        if(jTreeFolders.isSelectionEmpty()){
            Globals.ShowError("No folder selected");
        }else{
            PlayRandom();
        }
    }//GEN-LAST:event_jMnuRandomFolderActionPerformed

    private void jMnuRandomAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuRandomAllActionPerformed
        PlayRandomAll();
    }//GEN-LAST:event_jMnuRandomAllActionPerformed

    private void jTreeROMsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTreeROMsKeyReleased
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ENTER:
                //run game
                Play();
                break;
        }
    }//GEN-LAST:event_jTreeROMsKeyReleased

    private void jMnuSortSortBynameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortSortBynameActionPerformed
        SortFGByName();
    }//GEN-LAST:event_jMnuSortSortBynameActionPerformed

    private void jMnuSortSortByIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortSortByIconActionPerformed
        SortFGByIcon();
    }//GEN-LAST:event_jMnuSortSortByIconActionPerformed

    private void jMnuSortSortByTimesPlayedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortSortByTimesPlayedActionPerformed
        SortFGByTimesPlayed();
    }//GEN-LAST:event_jMnuSortSortByTimesPlayedActionPerformed

    private void jMnuSortSortByTimeWastedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortSortByTimeWastedActionPerformed
        SortFGByTimeWasted();
    }//GEN-LAST:event_jMnuSortSortByTimeWastedActionPerformed

    private void jMnuSortSortFoldersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortSortFoldersActionPerformed
        SortFoldersByName();
    }//GEN-LAST:event_jMnuSortSortFoldersActionPerformed

    private void jMnuSortFoldersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSortFoldersActionPerformed
        SortFoldersByName();
    }//GEN-LAST:event_jMnuSortFoldersActionPerformed

    private void jPopMnuPlaySelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuPlaySelectedActionPerformed
        Play();
    }//GEN-LAST:event_jPopMnuPlaySelectedActionPerformed

    private void jPopMnuPlayRandomFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuPlayRandomFolderActionPerformed
        PlayRandom();
    }//GEN-LAST:event_jPopMnuPlayRandomFolderActionPerformed

    private void jPopMnuPlayRandomEverywhereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuPlayRandomEverywhereActionPerformed
        PlayRandomAll();
    }//GEN-LAST:event_jPopMnuPlayRandomEverywhereActionPerformed

    private void jPopMnuFavoritesAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuFavoritesAddActionPerformed
        AddFavorite();
    }//GEN-LAST:event_jPopMnuFavoritesAddActionPerformed

    private void jPopMnuFavoritesEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuFavoritesEditActionPerformed
        EditFavorite();
    }//GEN-LAST:event_jPopMnuFavoritesEditActionPerformed

    private void jPopMnuFavoritesDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuFavoritesDeleteActionPerformed
        DeleteFavorite();
    }//GEN-LAST:event_jPopMnuFavoritesDeleteActionPerformed

    private void jPopMnuSortNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuSortNameActionPerformed
        SortFGByName();
    }//GEN-LAST:event_jPopMnuSortNameActionPerformed

    private void jPopMnuSortIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuSortIconActionPerformed
        SortFGByIcon();
    }//GEN-LAST:event_jPopMnuSortIconActionPerformed

    private void jPopMnuSortTimesPlayedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuSortTimesPlayedActionPerformed
        SortFGByTimesPlayed();
    }//GEN-LAST:event_jPopMnuSortTimesPlayedActionPerformed

    private void jPopMnuSortTimeWastedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuSortTimeWastedActionPerformed
        SortFGByTimeWasted();
    }//GEN-LAST:event_jPopMnuSortTimeWastedActionPerformed

    private void jPopMnuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopMnuInfoActionPerformed
        ShowInfo();
    }//GEN-LAST:event_jPopMnuInfoActionPerformed

    private void jMnuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuInfoActionPerformed
        ShowInfo();
    }//GEN-LAST:event_jMnuInfoActionPerformed

    private void jMnuListGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuListGamesActionPerformed
        ExportHTML export = new ExportHTML(favoritegame, folder, emulator, FavoriteIcon);
        export.setVisible(true);
    }//GEN-LAST:event_jMnuListGamesActionPerformed

    private void jMnuRecentMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMnuRecentMenuSelected
        //create Recent Game list
        CreateRecentMenu();
    }//GEN-LAST:event_jMnuRecentMenuSelected

    private void jMnuSortMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMnuSortMenuSelected
        jMnuSortSortByIcon.setEnabled(FavoritesSelected);
        jMnuSortSortByTimeWasted.setEnabled(FavoritesSelected);
        jMnuSortSortByTimesPlayed.setEnabled(FavoritesSelected);
        jMnuSortSortByname.setEnabled(FavoritesSelected);
    }//GEN-LAST:event_jMnuSortMenuSelected

    private void jPopMnuSortMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jPopMnuSortMenuSelected
        jPopMnuSortIcon.setEnabled(FavoritesSelected);
        jPopMnuSortName.setEnabled(FavoritesSelected);
        jPopMnuSortTimeWasted.setEnabled(FavoritesSelected);
        jPopMnuSortTimesPlayed.setEnabled(FavoritesSelected);
    }//GEN-LAST:event_jPopMnuSortMenuSelected

    private void jPopMnuFavoritesMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jPopMnuFavoritesMenuSelected
        jPopMnuFavoritesDelete.setEnabled(FavoritesSelected);
        jPopMnuFavoritesEdit.setEnabled(FavoritesSelected);
    }//GEN-LAST:event_jPopMnuFavoritesMenuSelected

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainwindow = new MainWindow();
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainwindow.setVisible(true);
            }
        });
    }

    protected void init() {
        //if the configuration directory does not exist
        if(!ConfigDir.exists()){
            //create it
            ConfigDir.mkdir();
        }
        //create variables
        createEmulators();
        createFolders();
        //load config file
        LoadConfig();
        //populate the tree
        PopulateFolderTree();
        //populate popup menus
    }
    
    private void AddFavorite(){
        if(jTreeROMs.isSelectionEmpty()){
            //empty selection---Empty Add Favorite window
            FavoriteAddWindow AF = new FavoriteAddWindow(this);
            AF.setVisible(true);
        }else if(jTreeROMs.getSelectionRows()[0]==0){
            //empty selection
            FavoriteAddWindow AF = new FavoriteAddWindow(this);
            AF.setVisible(true);
        }else{
            //selection should be filled: add a ROM
            int selection = jTreeROMs.getSelectionRows()[0]-1;
            FavoriteAddWindow AF = new FavoriteAddWindow(this, shownlist[selection], currentfolder.getPath()+Globals.Separator+getSelectedGame(), currentfolder);
            AF.setVisible(true);
        }
    }
    
    private void EditFavorite(){
        //remove rom from the favorites listing
        if(jTreeROMs.isSelectionEmpty()){
            //empty selection, do nothing
        }else if(jTreeROMs.getSelectionRows()[0]>0){
            //top not selected, but something is
            //edit the favorite
            FavoriteEditWindow FEW = new FavoriteEditWindow(this, favoritegame[jTreeROMs.getSelectionRows()[0]-1]);
            FEW.setVisible(true);
        }
    }

    private void DeleteFavorite(){
        //remove rom from the favorites listing
        if(jTreeROMs.isSelectionEmpty()){
            //empty selection, do nothing
        }else if(jTreeROMs.getSelectionRows()[0]>0){
            //top not selected, but something is
            //add counts to totals
            OtherTimeWasted += favoritegame[jTreeROMs.getSelectionRows()[0]-1].getTimeWasted();
            OtherTimesPlayed += favoritegame[jTreeROMs.getSelectionRows()[0]-1].getTimesPlayed();
            SaveConfig();
            //remove the game
            favoritegame[jTreeROMs.getSelectionRows()[0]-1].DeleteFile();
            //update lists etc
            init();
            LoadFavorites();
            jTreeFolders.setSelectionRows(new int[]{0});
        }
    }

    private String DisplayTimeWasted(int ID){
        long TimeWasted = favoritegame[ID].getTimeWasted();
        String FormattedTime = new String();

        return FormattedTime;
    }

    private void createFolders() {
        //Check to make sure the folder directory exists, if not: make it
        if (!FolderDir.exists()){
            FolderDir.mkdir();
        }
        //initialize folder array
        folder = new Folder[FolderDir.list().length];
        //create folder variables
        for (int i=0;i<FolderDir.list().length;i++){
            folder[i] = new Folder(i);
        }
    }

    private void createEmulators(){
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

    private void ShowInfo(){
        Info info;
        //Make sure something is selected
        if(jTreeROMs.isSelectionEmpty()){
            info = new Info(this, favoritegame);
        }else{
            String SelectedGame = getSelectedGame();
            if (SelectedGame.compareTo("failed")==0){
                info = new Info(this, favoritegame);
            }
        }
        if(FavoritesSelected){
            if(jTreeROMs.isSelectionEmpty()){
                info = new Info(this, favoritegame);
            }else if(jTreeROMs.getSelectionRows()[0] == 0){
                info = new Info(this, favoritegame);
            }else{
                info = new Info(this, favoritegame[jTreeROMs.getSelectionRows()[0]-1], favoritegame);
            }
        }else{
            info = new Info(this, favoritegame);
        }
        info.setVisible(true);
    }

    private String getEmulatorPath(){
        if(FavoritesSelected){
            //do it another way
            return "Favorites Selected";
        }else{
            return emulator[currentfolder.getEmu()].getPath();
        }
    }

    private void PlayRandomAll(){
        //get random number
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        int randomnum = r.nextInt(folder.length+1);
        jTreeFolders.setSelectionRow(randomnum);
        PlayRandom();
    }

    private void PlayRandom(){
        //hide window
        mainwindow.setVisible(false);
        //get random number
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        int randomnum = 0;
        if(FavoritesSelected){
            //if favorites are selected, this is easier
            randomnum = r.nextInt(favoritegame.length);
        }else{
            //oh wait, its still easy
            randomnum = r.nextInt(reallist.length);
        }
        final int randomgame = randomnum;
        Thread rungame = new Thread(){
            @Override
            public void run(){
                //time wasted start
                long TimeWastedStart = Calendar.getInstance().getTimeInMillis();
                //get the commands and put them in an array
                String commands = new String();
                String emupath = new String();
                //if favorites are selected, load the commands and paths from it
                if(FavoritesSelected){
                    favoritegame[randomgame].IncreaseTimesPlayed();
                    FavoriteGame selectedFavorite = favoritegame[randomgame];
                    commands = new String(selectedFavorite.getPath());
                    emupath = new String(emulator[selectedFavorite.getEmulator()].getPath());
                    //recent game
                    RecentGame recent = new RecentGame(selectedFavorite.getName(), selectedFavorite.getPath(), emulator[selectedFavorite.getEmulator()]);
                    recent.MakeFavorite(jTreeROMs.getSelectionRows()[0] -1);
                    MoveRecentGamesDown();
                    Recentgame[0] = recent;
                }else{
                    //if not, get the game from the current folder
                    String SelectedGame = reallist[randomgame];
                    emupath = getEmulatorPath();
                    commands = new String(currentfolder.getPath()
                        +Separator+SelectedGame);
                    //recent game
                    String RecentName, RecentPath;
                    Emulator RecentEmu;
                    RecentName = new String(shownlist[randomgame]);
                    RecentPath = currentfolder.getPath()+Separator+SelectedGame;
                    RecentEmu = emulator[currentfolder.getEmu()];
                    RecentGame recent = new RecentGame(RecentName, RecentPath, RecentEmu);
                    MoveRecentGamesDown();
                    Recentgame[0] = recent;
                }
                String[] runthis = new String[] { emupath, commands };
                try {
                    //run the process
                    Process proc = Runtime.getRuntime().exec(runthis);
                    try {
                        SystemTrayThreaded stt = new SystemTrayThreaded(proc);
                        //create the system tray thread
                        if(SystemTray.isSupported()){
                            stt.start();
                            stt.doTray();
                        }
                        //wait for the game to finish being played
                        proc.waitFor();
                        //destroy the shit
                        proc.destroy();
                        if(SystemTray.isSupported()){
                            stt.done();
                        }
                        //show the window again
                        mainwindow.setVisible(true);
                        proc.destroy();
                    } catch (InterruptedException ex) {
                        Globals.ShowError(ex);
                    }
                } catch (IOException ex) {
                    Globals.ShowError(ex);
                }
                long TimeWastedEnd = Calendar.getInstance().getTimeInMillis();
                long TimeWastedTotal = (TimeWastedEnd - TimeWastedStart);
                if(FavoritesSelected){
                    favoritegame[randomgame].IncreaseTimeWasted(TimeWastedTotal);
                }else{
                    OtherTimeWasted += TimeWastedTotal;
                    OtherTimesPlayed++;
                    SaveConfig();
                }
                //set scrollbar positioning
                SetScrollbar();
            }

            private void SetScrollbar() {

                //set selection
                jTreeROMs.setSelectionRow(randomgame+1);
                //scroll to position in list
                double ScrollMaxValue = jScrlROMs.getVerticalScrollBar().getMaximum();
                //find percentage of game in real list
                double ListLength = reallist.length;
                double ListPosition = randomgame;
                double ListPercent = (ListPosition / ListLength);
                //find position in list
                int ScrollPosition = (int) (ScrollMaxValue * ListPercent);
                //set scroll position
                jScrlROMs.getVerticalScrollBar().setValue(ScrollPosition);
            }
        };
        rungame.start();
    }

    private void Play() {
        //hide window
        mainwindow.setVisible(false);
        //thread for running game
        Thread rungame = new Thread(){
            @Override
            public void run(){
                //time wasted start
                long TimeWastedStart = Calendar.getInstance().getTimeInMillis();
                //get the commands and put them in an array
                String commands = new String();
                String emupath = new String();
                if(FavoritesSelected){
                    favoritegame[(jTreeROMs.getSelectionRows()[0] - 1)].IncreaseTimesPlayed();
                    FavoriteGame selectedFavorite = favoritegame[(jTreeROMs.getSelectionRows()[0] - 1)];
                    commands = new String(selectedFavorite.getPath());
                    emupath = new String(emulator[selectedFavorite.getEmulator()].getPath());
                    //recent game
                    RecentGame recent = new RecentGame(selectedFavorite.getName(), selectedFavorite.getPath(), emulator[selectedFavorite.getEmulator()]);
                    recent.MakeFavorite(jTreeROMs.getSelectionRows()[0] -1);
                    MoveRecentGamesDown();
                    Recentgame[0] = recent;
                }else{
                    String SelectedGame = getSelectedGame();
                    if (SelectedGame.compareTo("failed")==0){
                        return;
                    }
                    emupath = getEmulatorPath();
                    commands = new String(currentfolder.getPath()
                        +Separator+SelectedGame);
                    //recent game
                    String RecentName, RecentPath;
                    Emulator RecentEmu;
                    RecentName = new String(shownlist[jTreeROMs.getSelectionRows()[0]-1]);
                    RecentPath = currentfolder.getPath()+Separator+SelectedGame;
                    RecentEmu = emulator[currentfolder.getEmu()];
                    RecentGame recent = new RecentGame(RecentName, RecentPath, RecentEmu);
                    MoveRecentGamesDown();
                    Recentgame[0] = recent;
                }
                String[] runthis = new String[] { emupath, commands };
                try {
                    //run the process
                    Process proc = Runtime.getRuntime().exec(runthis);
                    try {
                        SystemTrayThreaded stt = new SystemTrayThreaded(proc);
                        //create the system tray thread
                        if(SystemTray.isSupported()){
                            stt.start();
                            stt.doTray();
                        }
                        //wait for the game to finish being played
                        proc.waitFor();
                        //destroy the shit
                        proc.destroy();
                        if(SystemTray.isSupported()){
                            stt.done();
                        }
                        //show the window again
                        mainwindow.setVisible(true);
                        proc.destroy();
                    } catch (InterruptedException ex) {
                        Globals.ShowError(ex);
                    }
                } catch (IOException ex) {
                    Globals.ShowError(ex);
                }
                long TimeWastedEnd = Calendar.getInstance().getTimeInMillis();
                long TimeWastedTotal = (TimeWastedEnd - TimeWastedStart);
                if(FavoritesSelected){
                    favoritegame[(jTreeROMs.getSelectionRows()[0] - 1)].IncreaseTimeWasted(TimeWastedTotal);
                }else{
                    OtherTimeWasted += TimeWastedTotal;
                    OtherTimesPlayed++;
                    SaveConfig();
                }
            }
        };
        rungame.start();
    }

    private void filterList(String searchstring) {
        //if searchstring is 0 len reload dir
        if(searchstring.length() == 0){
            LoadDirectory();
            if(FavoritesSelected){
                DisplayFavorites();
            }
            PopulateROMTree();
            return;
        }
        //reload ROM list
        LoadDirectory();
        if(FavoritesSelected){
            DisplayFavorites();
        }
        //count how many times string is found
        int SearchCount = 0;
        for(int i=0;i<shownlist.length;i++){
            //if the string is there at all
            if(shownlist[i].toLowerCase().indexOf(searchstring.toLowerCase(), 0) != -1){
                SearchCount++;
            }
        }
        //create new lists
        String[] newShownList = new String[SearchCount];
        String[] newRealList  = new String[SearchCount];
        FavoriteGame[] newFG  = new FavoriteGame[SearchCount];
        //search again, this time copying over data where needed
        SearchCount = 0;
        for(int i=0;i<shownlist.length;i++){
            if(shownlist[i].toLowerCase().indexOf(searchstring.toLowerCase(), 0) != -1){
                if(FavoritesSelected){
                    newFG[SearchCount] = favoritegame[i];
                }else{
                    newShownList[SearchCount] = shownlist[i];
                    newRealList [SearchCount] = reallist[i];
                }
                SearchCount++;
            }
        }
        //copy arrays
        if(FavoritesSelected){
            favoritegame = Arrays.copyOf(newFG, newFG.length);
            DisplayFavorites();
        }else{
            reallist = Arrays.copyOf(newRealList, newRealList.length);
            shownlist = Arrays.copyOf(newShownList, newShownList.length);
        }
        //display info
        PopulateROMTree();
    }

    private String[] StripExtensions(String[] RomList, Folder curfolder){
        for(int i=0;i<RomList.length;i++){
            String[] Filters = emulator[curfolder.getEmu()].getFileTypesArray();
            for(int j=0;j<Filters.length;j++){
                if(RomList[i].endsWith(Filters[j])){
                    int endindex = (RomList[i].length() - Filters[j].length());
                    RomList[i] = RomList[i].substring(0, endindex);
                }
            }
        }
        return RomList;
    }

    private void LoadDirectory() {
        //if favorites are selected: change it up
        if(FavoritesSelected){
            LoadFavorites();
            return;
        }
        currentfolder.LoadDirectory();
        //load the directory with games
        File dir = new File(currentfolder.getPath());
        //create the list
        java.util.Arrays.sort(reallist = dir.list());
        //filter the list to only show the supported filetype extensions
        String[] ftypes = emulator[currentfolder.getEmu()].getFileTypesArray();
        int counter=0;
        //first, count the amount of times the extension is shown
        for(int i=0;i<ftypes.length;i++){
            for(int j=0;j<reallist.length;j++){
                if(reallist[j].endsWith(ftypes[i])){
                    counter++;
                }
            }
        }
        String[] templist = new String[counter];
        counter=0;
        for(int i=0;i<ftypes.length;i++){
            for(int j=0;j<reallist.length;j++){
                if(reallist[j].endsWith(ftypes[i])){
                    templist[counter] = reallist[j];
                    counter++;
                }
            }
        }
        //hide unsupported filetypes
        if(!currentfolder.getHideUnsupportedFiletypes()){
            reallist = new String[counter];
            System.arraycopy(templist, 0, reallist, 0, templist.length);
        }
        //show mame roms, or strip file extensions if you have to
        if(currentfolder.getMameROMs()){
            String[] mameFull = new String[50000];
            System.out.println(emulator[currentfolder.getEmu()].getPath());
            //create command array
            String[] cmdarray = new String[]{
                emulator[currentfolder.getEmu()].getPath(),
                "-listfull"
            };
            //run the program etc
            try {
                //run program
                Process proc = Runtime.getRuntime().exec(cmdarray);
                Process proc2 = Runtime.getRuntime().exec(cmdarray);
                //get input: amount of lines
                System.out.println("Check 1");
                BufferedReader BReder = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                int linecount = 0;
                String tmpStr;
                while ((tmpStr = BReder.readLine()) != null) {
                    mameFull[linecount] = tmpStr;
                    linecount++;
                }
                BReder.close();
                /*
                System.out.println("Check 2");
                BufferedReader BReader = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
                //get input: contents of lines into an array
                System.out.println("Check 3");
                System.out.println(linecount);
                for(int i=0;i<linecount-1;i++){
                    System.out.println(i);
                    mameFull[i] = BReader.readLine();
                }
                System.out.println("Check 4");
                //close reader
                BReader.close();*/
                //seperate array into listShort and listLong
                String[] mameShort = new String[linecount-1];
                String[] mameLong  = new String[linecount-1];
                //shortnames: from beginning of line to first space
                for(int i=1;i<linecount-1;i++){
                    int FirstSpace = mameFull[i].indexOf(" ");
                    mameShort[i-1] = mameFull[i].substring(0, FirstSpace);
                }
                //longnames: inbetween the quotes
                for(int i=1;i<linecount-1;i++){
                    int QuoteBegin  = mameFull[i].indexOf("\"");
                    int QuoteEnd    = mameFull[i].indexOf("\"", QuoteBegin+1);
                    //int QuoteEnd    = mameFull[i].indexOf(mameFull[i].substring(QuoteBegin), QuoteBegin);
                    mameLong[i-1]   = mameFull[i].substring(QuoteBegin+1, QuoteEnd);
                }
                //lists generated
                //strip extensions
                shownlist = StripExtensions(templist, currentfolder);
                //create a shownlist based off reallist, mameShort, mameLong
                for(int i=0;i<reallist.length;i++){
                    //compare reallist[i] to mameshort[all] until a match, then use
                    //mamelong[x]
                    for(int x=0;x<mameShort.length;x++){
                        if(shownlist[i].equals(mameShort[x])){
                            shownlist[i] = mameLong[x];
                            x = mameShort.length;
                        }
                    }
                }
            } catch (IOException ex) {
                Globals.ShowError(ex);
            }
        }else if(!currentfolder.getShowExtensions()){
            shownlist = StripExtensions(templist, currentfolder);
        }else{
            shownlist = new String[reallist.length];
            System.arraycopy(reallist, 0, shownlist, 0, reallist.length);
        }
        PopulateROMTree();
    }

    public boolean getFavoritesSelected(){
        return FavoritesSelected;
    }

    protected void LoadFavorites(){
        favoritegame = new FavoriteGame[FavoriteDir.list().length];
        for(int i=0;i<favoritegame.length;i++){
            favoritegame[i] = new FavoriteGame(i);
        }
    }

    public String[] getFavoriteIcons(){
        String[] FavIcons = new String[favoritegame.length];
        //load favorite icons
        for(int i=0;i<favoritegame.length;i++){
            FavIcons[i] = favoritegame[i].getIcon();
        }
        return FavIcons;
    }

    public String[] getFavoriteText(){
        String[] FavText = new String[favoritegame.length];
        //load favorite icons
        for(int i=0;i<favoritegame.length;i++){
            FavText[i] = favoritegame[i].getName();
        }
        return FavText;
    }

    private void DisplayFavorites() {
        //create list of Games
        reallist    = new String[favoritegame.length];
        shownlist   = new String[favoritegame.length];
        for(int i=0;i<favoritegame.length;i++){
            reallist[i]     = favoritegame[i].getPath();
            shownlist[i]    = favoritegame[i].getName();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane FilterSplitpane;
    private javax.swing.JButton btnFilter;
    private javax.swing.JSplitPane fileSplitpane;
    private javax.swing.JMenuItem jMnuAbout;
    private javax.swing.JMenuItem jMnuAddFavorite;
    private javax.swing.JMenuItem jMnuEditFavorite;
    private javax.swing.JMenu jMnuEditMenu;
    private javax.swing.JMenuItem jMnuExit;
    private javax.swing.JMenu jMnuFavorites;
    private javax.swing.JMenu jMnuFileMenu;
    private javax.swing.JMenuItem jMnuFoldersAddFolder;
    private javax.swing.JMenuItem jMnuFoldersDeleteFolder;
    private javax.swing.JMenuItem jMnuFoldersEditFolder;
    private javax.swing.JMenuItem jMnuFoldersInfo;
    private javax.swing.JMenu jMnuGameMenu;
    private javax.swing.JMenuItem jMnuHelp;
    private javax.swing.JMenu jMnuHelpMenu;
    private javax.swing.JMenuItem jMnuInfo;
    private javax.swing.JMenuItem jMnuListGames;
    private javax.swing.JMenu jMnuPlay;
    private javax.swing.JMenuItem jMnuPlaySelected;
    private static javax.swing.JMenuItem jMnuPreferences;
    private javax.swing.JMenuItem jMnuRandomAll;
    private javax.swing.JMenuItem jMnuRandomFolder;
    private javax.swing.JMenu jMnuRecent;
    private javax.swing.JMenuItem jMnuRemoveFavorite;
    private javax.swing.JMenu jMnuSort;
    private javax.swing.JMenuItem jMnuSortFolders;
    private javax.swing.JMenuItem jMnuSortSortByIcon;
    private javax.swing.JMenuItem jMnuSortSortByTimeWasted;
    private javax.swing.JMenuItem jMnuSortSortByTimesPlayed;
    private javax.swing.JMenuItem jMnuSortSortByname;
    private javax.swing.JMenuItem jMnuSortSortFolders;
    private javax.swing.JMenuItem jMnuWhatsnew;
    private javax.swing.JPopupMenu jPopFolders;
    private javax.swing.JMenu jPopMnuFavorites;
    private javax.swing.JMenuItem jPopMnuFavoritesAdd;
    private javax.swing.JMenuItem jPopMnuFavoritesDelete;
    private javax.swing.JMenuItem jPopMnuFavoritesEdit;
    private javax.swing.JMenuItem jPopMnuInfo;
    private javax.swing.JMenu jPopMnuPlay;
    private javax.swing.JMenuItem jPopMnuPlayRandomEverywhere;
    private javax.swing.JMenuItem jPopMnuPlayRandomFolder;
    private javax.swing.JMenuItem jPopMnuPlaySelected;
    private javax.swing.JMenu jPopMnuSort;
    private javax.swing.JMenuItem jPopMnuSortIcon;
    private javax.swing.JMenuItem jPopMnuSortName;
    private javax.swing.JMenuItem jPopMnuSortTimeWasted;
    private javax.swing.JMenuItem jPopMnuSortTimesPlayed;
    private javax.swing.JPopupMenu jPopROMs;
    private javax.swing.JScrollPane jScrlFolders;
    private javax.swing.JScrollPane jScrlROMs;
    private javax.swing.JSeparator jSepFolder;
    private javax.swing.JSeparator jSepFolder2;
    private javax.swing.JSeparator jSeparator1;
    protected javax.swing.JTree jTreeFolders;
    protected javax.swing.JTree jTreeROMs;
    private javax.swing.JTextField jTxtFilter;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane mainSplitpane;
    // End of variables declaration//GEN-END:variables

    private void CreateRecentMenu() {
        //clear out recent menu items
        jMnuRecent.removeAll();
        JMenuItem[] RecentMenuItem = new JMenuItem[Recentgame.length];
        JSeparator JSep = new JSeparator();
        JMenuItem Clear = new JMenuItem("Clear");
        //
        for(int i=0;i<Recentgame.length;i++){
            RecentMenuItem[i] = new JMenuItem((i+1)+". "+Recentgame[i].getName());
        }
        //add menu actions
        for(int i=0;i<Recentgame.length;i++){
            if(Recentgame[i].getExists()){
                final int index = i;
                RecentMenuItem[i].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        PlayRecent(index);
                        pack();
                    }
                });
            }
        }
        //add menu action to clear
        Clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                BlankRecents();
            }
        });
        //add menu items
        for(int i=0;i<Recentgame.length;i++){
            if(Recentgame[i].getExists()){
                jMnuRecent.add(RecentMenuItem[i]);
            }
        }
        jMnuRecent.add(JSep);
        jMnuRecent.add(Clear);
    }

    private void PlayRecent(final int index){
        //hide window
        mainwindow.setVisible(false);
        //create a thread to run the game in
        Thread rungame = new Thread(){
            @Override
            public void run(){
                //time wasted start
                long TimeWastedStart = Calendar.getInstance().getTimeInMillis();
                //get the commands and put them in an array
                String commands = new String();
                String emupath = new String();
                commands = Recentgame[index].getPath();
                emupath = Recentgame[index].getEmulator().getPath();
                String[] runthis = new String[] { emupath, commands };
                try {
                    //run the process
                    Process proc = Runtime.getRuntime().exec(runthis);
                    try {
                        SystemTrayThreaded stt = new SystemTrayThreaded(proc);
                        //create the system tray thread
                        if(SystemTray.isSupported()){
                            stt.start();
                            stt.doTray();
                        }
                        //wait for the game to finish being played
                        proc.waitFor();
                        //destroy the shit
                        proc.destroy();
                        if(SystemTray.isSupported()){
                            stt.done();
                        }
                        //show the window again
                        mainwindow.setVisible(true);
                        proc.destroy();
                    } catch (InterruptedException ex) {
                        Globals.ShowError(ex);
                    }
                } catch (IOException ex) {
                    Globals.ShowError(ex);
                }
                long TimeWastedEnd = Calendar.getInstance().getTimeInMillis();
                long TimeWastedTotal = (TimeWastedEnd - TimeWastedStart);
                if(Recentgame[index].isFavorite()){
                    favoritegame[Recentgame[index].getFavoriteIndex()].IncreaseTimesPlayed();
                    favoritegame[Recentgame[index].getFavoriteIndex()].IncreaseTimeWasted(TimeWastedTotal);
                }else{
                    OtherTimeWasted += TimeWastedTotal;
                    OtherTimesPlayed++;
                    SaveConfig();
                }
            }
        };
        rungame.start();
    }

    private void MoveRecentGamesDown() {
        for(int i=(Recentgame.length-1);i>0;i--){
            Recentgame[i] = Recentgame[i-1];
        }
    }

    private void BlankRecents() {
        for(int i=0;i<Recentgame.length;i++){
            Recentgame[i] = new RecentGame();
        }
    }
}