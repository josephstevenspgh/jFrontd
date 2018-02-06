/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Info.java
 *
 * Created on Nov 30, 2009, 2:12:50 PM
 */

package jFrontd.UI;

import jFrontd.Classes.Emulator;
import jFrontd.Classes.FavoriteGame;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author sj
 */
public class Info extends javax.swing.JFrame {
    protected final String    Separator           = new String(System.getProperty("file.separator"));
    protected final String    home                = new String(System.getProperty("user.home"));
    protected final File      ConfigDir           = new File(home+Separator+".jFrontd");
    protected final File      ConfigFile          = new File(ConfigDir+Separator+"jFrontd.cfg");
    protected final File      FolderDir           = new File(ConfigDir+Separator+"Folders");
    protected final File      IconDir             = new File(ConfigDir+Separator+"Icons");
    protected final File      EmulatorDir         = new File(ConfigDir+Separator+"Emulators");
    protected final File      FavoriteDir         = new File(ConfigDir+Separator+"Favorites");
    private FavoriteGame FG;
    private FavoriteGame[] favoritegame;
    private MainWindow mainwindow;

    /** Creates new form Info */
    public Info(MainWindow mainwindow, FavoriteGame[] favoritegame) {
        this.mainwindow = mainwindow;
        this.favoritegame = favoritegame;
        initComponents();
        jTabbedPane1.remove(0);
        init();
    }

    public Info(MainWindow mainwindow, FavoriteGame FG, FavoriteGame[] favoritegame){
        this.mainwindow = mainwindow;
        this.FG = FG;
        this.favoritegame = favoritegame;
        initComponents();
        init();
    }
    private String CalcTimeWasted(long TimeWasted){
            if(TimeWasted < 1000){
                return "N/A";
            }
            
            int SecondsWasted = (int) (TimeWasted/1000);

            int MinutesWasted = (SecondsWasted/60);
            SecondsWasted = (SecondsWasted - (60*MinutesWasted));

            int HoursWasted = (MinutesWasted/60);
            MinutesWasted = (MinutesWasted - (60*HoursWasted));

            int DaysWasted = (HoursWasted/24);
            HoursWasted = (HoursWasted - (24*DaysWasted));

            int MonthsWasted = (DaysWasted/30);
            DaysWasted = (DaysWasted - (30*MonthsWasted));

            int YearsWasted = (MonthsWasted/12);
            MonthsWasted = (MonthsWasted - (12*YearsWasted));

            String StringTimeWasted = new String("");

            if(YearsWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(MonthsWasted + " Years, ");
            }

            if(MonthsWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(MonthsWasted + " Months, ");
            }

            if(DaysWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(DaysWasted + " Days, ");
            }

            if(HoursWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(HoursWasted + " Hours, ");
            }

            if(MinutesWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(MinutesWasted + " Minutes, ");
            }

            if(SecondsWasted > 0){
                StringTimeWasted = StringTimeWasted.concat(SecondsWasted + " Seconds, ");
            }

            StringTimeWasted = StringTimeWasted.substring(0, StringTimeWasted.length()-2);

            return StringTimeWasted;
    }

    private void init(){
        if(FG != null){
            //load emulators for displaying one
            Emulator[] emulator = new Emulator[EmulatorDir.list().length];
            for (int i=0;i<EmulatorDir.list().length;i++){
                emulator[i] = new Emulator(i);
            }
            //calculate average time wasted
            String TimeWastedAvg = new String("N/A");
            if(FG.getTimeWasted() > 0){
                long AverageTimeWasted = (((long)FG.getTimeWasted())/((long)FG.getTimesPlayed()));
                TimeWastedAvg = CalcTimeWasted(AverageTimeWasted);
            }
            //format time for displaying
            String TimeWasted = CalcTimeWasted(FG.getTimeWasted());
            //if a FG was shown, display some information
            jLblCommentOutput.setText(FG.getComment());
            jLblEmulatorOutput.setText(emulator[FG.getEmulator()].getName());
            jLblIconOutput.setIcon(new ImageIcon(IconDir + Separator + FG.getIcon()));
            jLblNameOutput.setText(FG.getName());
            jTabbedPane1.setTitleAt(0, FG.getName());
            jTxtPath.setText(FG.getPath());
            jLblTimeWastedOutput.setText(TimeWasted);
            jLblAverageTimeWastedOutput.setText(TimeWastedAvg);
            jLblTimesPlayedOutput.setText(Integer.toString(FG.getTimesPlayed()));
        }
        //load some variables for displaying
        long OtherTimeWasted = mainwindow.OtherTimeWasted;
        int OtherTimesPlayed = mainwindow.OtherTimesPlayed;
        //get favorites
        long FavoriteTimeWasted = 0;
        int FavoriteTimesPlayed = 0;
        for(int i=0;i<favoritegame.length;i++){
            FavoriteTimeWasted  += favoritegame[i].getTimeWasted();
            FavoriteTimesPlayed += favoritegame[i].getTimesPlayed();
        }
        //calculate totals
        long TotalTimeWasted = OtherTimeWasted + FavoriteTimeWasted;
        int TotalTimesPlayed = OtherTimesPlayed + FavoriteTimesPlayed;
        //find most played favorites
        int MostPlayedFavorite = 0;
        int MostTimeWasted = 0;
        for(int i=1;i<favoritegame.length;i++){
            if(favoritegame[MostPlayedFavorite].getTimesPlayed() < favoritegame[i].getTimesPlayed()){
                MostPlayedFavorite = i;
            }
            if(favoritegame[MostTimeWasted].getTimeWasted() < favoritegame[i].getTimeWasted()){
                MostTimeWasted = i;
            }
        }
        //display values
        jLblTotalGamesPlayedOutput.setText(Integer.toString(TotalTimesPlayed));
        jLblTotalTimeWastedOutput.setText(CalcTimeWasted(TotalTimeWasted));
        jLblTopFavoriteOutput.setText(favoritegame[MostPlayedFavorite].getName());
        jLblTopTimeWastedOutput.setText(favoritegame[MostTimeWasted].getName());
        jLblOtherPlayedOutput.setText(Integer.toString(OtherTimesPlayed));
        jLblOtherWastedOutput.setText(CalcTimeWasted(OtherTimeWasted));
        //pack
        pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlTop = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPnlGame = new javax.swing.JPanel();
        jLblName = new javax.swing.JLabel();
        jLblNameOutput = new javax.swing.JLabel();
        jLblPath = new javax.swing.JLabel();
        jTxtPath = new javax.swing.JTextField();
        jLblTimesPlayed = new javax.swing.JLabel();
        jLblTimesPlayedOutput = new javax.swing.JLabel();
        jLblTimeWasted = new javax.swing.JLabel();
        jLblTimeWastedOutput = new javax.swing.JLabel();
        jLblAverageTimeWasted = new javax.swing.JLabel();
        jLblAverageTimeWastedOutput = new javax.swing.JLabel();
        jLblEmulator = new javax.swing.JLabel();
        jLblEmulatorOutput = new javax.swing.JLabel();
        jLblIcon = new javax.swing.JLabel();
        jLblIconOutput = new javax.swing.JLabel();
        jLblComment = new javax.swing.JLabel();
        jLblCommentOutput = new javax.swing.JLabel();
        jPnlTotals = new javax.swing.JPanel();
        jLblTotalGamesPlayed = new javax.swing.JLabel();
        jLblTotalGamesPlayedOutput = new javax.swing.JLabel();
        jLblTotalTimeWasted = new javax.swing.JLabel();
        jLblTotalTimeWastedOutput = new javax.swing.JLabel();
        jLblTopFavorite = new javax.swing.JLabel();
        jLblTopFavoriteOutput = new javax.swing.JLabel();
        jLblTopTimeWasted = new javax.swing.JLabel();
        jLblTopTimeWastedOutput = new javax.swing.JLabel();
        jLblOtherPlayed = new javax.swing.JLabel();
        jLblOtherPlayedOutput = new javax.swing.JLabel();
        jLblOtherWasted = new javax.swing.JLabel();
        jLblOtherWastedOutput = new javax.swing.JLabel();
        jPnlBottom = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Info");
        setMinimumSize(new java.awt.Dimension(320, 240));

        jPnlGame.setMaximumSize(new java.awt.Dimension(162, 105));
        jPnlGame.setLayout(new java.awt.GridLayout(8, 0));

        jLblName.setText("Name");
        jPnlGame.add(jLblName);

        jLblNameOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblNameOutput);

        jLblPath.setText("Path");
        jPnlGame.add(jLblPath);

        jTxtPath.setEditable(false);
        jTxtPath.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPnlGame.add(jTxtPath);

        jLblTimesPlayed.setText("Times Played");
        jPnlGame.add(jLblTimesPlayed);

        jLblTimesPlayedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblTimesPlayedOutput);

        jLblTimeWasted.setText("Time Wasted");
        jPnlGame.add(jLblTimeWasted);

        jLblTimeWastedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblTimeWastedOutput);

        jLblAverageTimeWasted.setText("Average Play Length");
        jPnlGame.add(jLblAverageTimeWasted);
        jPnlGame.add(jLblAverageTimeWastedOutput);

        jLblEmulator.setText("Emulator");
        jPnlGame.add(jLblEmulator);

        jLblEmulatorOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblEmulatorOutput);

        jLblIcon.setText("Icon");
        jPnlGame.add(jLblIcon);

        jLblIconOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblIconOutput);

        jLblComment.setText("Comment");
        jPnlGame.add(jLblComment);

        jLblCommentOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlGame.add(jLblCommentOutput);

        jTabbedPane1.addTab("Game X", jPnlGame);

        jPnlTotals.setLayout(new java.awt.GridLayout(6, 0));

        jLblTotalGamesPlayed.setText("Total amount of games played");
        jPnlTotals.add(jLblTotalGamesPlayed);

        jLblTotalGamesPlayedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblTotalGamesPlayedOutput);

        jLblTotalTimeWasted.setText("Total amount of time wasted");
        jPnlTotals.add(jLblTotalTimeWasted);

        jLblTotalTimeWastedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblTotalTimeWastedOutput);

        jLblTopFavorite.setText("Most played Favorite");
        jPnlTotals.add(jLblTopFavorite);

        jLblTopFavoriteOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblTopFavoriteOutput);

        jLblTopTimeWasted.setText("Most time wasted on a favorite");
        jPnlTotals.add(jLblTopTimeWasted);

        jLblTopTimeWastedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblTopTimeWastedOutput);

        jLblOtherPlayed.setText("Non-Favorites played");
        jPnlTotals.add(jLblOtherPlayed);

        jLblOtherPlayedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblOtherPlayedOutput);

        jLblOtherWasted.setText("Time wasted on non-favorites");
        jPnlTotals.add(jLblOtherWasted);

        jLblOtherWastedOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPnlTotals.add(jLblOtherWastedOutput);

        jTabbedPane1.addTab("Totals", jPnlTotals);

        javax.swing.GroupLayout jPnlTopLayout = new javax.swing.GroupLayout(jPnlTop);
        jPnlTop.setLayout(jPnlTopLayout);
        jPnlTopLayout.setHorizontalGroup(
            jPnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlTopLayout.setVerticalGroup(
            jPnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlBottomLayout = new javax.swing.GroupLayout(jPnlBottom);
        jPnlBottom.setLayout(jPnlBottomLayout);
        jPnlBottomLayout.setHorizontalGroup(
            jPnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBottomLayout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPnlBottomLayout.setVerticalGroup(
            jPnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnlTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnlBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-428)/2, (screenSize.height-317)/2, 428, 317);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLblAverageTimeWasted;
    private javax.swing.JLabel jLblAverageTimeWastedOutput;
    private javax.swing.JLabel jLblComment;
    private javax.swing.JLabel jLblCommentOutput;
    private javax.swing.JLabel jLblEmulator;
    private javax.swing.JLabel jLblEmulatorOutput;
    private javax.swing.JLabel jLblIcon;
    private javax.swing.JLabel jLblIconOutput;
    private javax.swing.JLabel jLblName;
    private javax.swing.JLabel jLblNameOutput;
    private javax.swing.JLabel jLblOtherPlayed;
    private javax.swing.JLabel jLblOtherPlayedOutput;
    private javax.swing.JLabel jLblOtherWasted;
    private javax.swing.JLabel jLblOtherWastedOutput;
    private javax.swing.JLabel jLblPath;
    private javax.swing.JLabel jLblTimeWasted;
    private javax.swing.JLabel jLblTimeWastedOutput;
    private javax.swing.JLabel jLblTimesPlayed;
    private javax.swing.JLabel jLblTimesPlayedOutput;
    private javax.swing.JLabel jLblTopFavorite;
    private javax.swing.JLabel jLblTopFavoriteOutput;
    private javax.swing.JLabel jLblTopTimeWasted;
    private javax.swing.JLabel jLblTopTimeWastedOutput;
    private javax.swing.JLabel jLblTotalGamesPlayed;
    private javax.swing.JLabel jLblTotalGamesPlayedOutput;
    private javax.swing.JLabel jLblTotalTimeWasted;
    private javax.swing.JLabel jLblTotalTimeWastedOutput;
    private javax.swing.JPanel jPnlBottom;
    private javax.swing.JPanel jPnlGame;
    private javax.swing.JPanel jPnlTop;
    private javax.swing.JPanel jPnlTotals;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTxtPath;
    // End of variables declaration//GEN-END:variables

}
