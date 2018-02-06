/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jFrontd.Classes;

import Classes.FileManager;
import Classes.Globals;
import jFrontd.UI.MainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author sj
 */
public class FavoriteGame {
    private final File ConfigDir = new File(Globals.Home+Globals.Separator+".jFrontd");
    private final File FavoritesDir = new File(ConfigDir+Globals.Separator+"Favorites");
    private String newline = Globals.Newline;
    private int ID;
    private String Name;
    private String Path;
    private int EmulatorID;
    private String Icon;
    private String Comment;
    private int TimesPlayed;
    private long TimeWasted;

    public FavoriteGame(String Name, String Path, int EmulatorID, String Icon, String Comment){
        //create file
        ID                  = FavoritesDir.list().length;
        this.Name           = Name;
        this.Path           = Path;
        this.EmulatorID     = EmulatorID;
        this.Icon           = Icon;
        this.Comment        = Comment;
        this.TimesPlayed    = 0;
        this.TimeWasted     = 0;
        //write file
        MakeNewFile();
    }

    public FavoriteGame(int ID){
        this.ID = ID;
        ReadFile();
        //load file
    }
    
    private void MakeNewFile(){
        //create the file variable
        File newfile = new File(FavoritesDir+Globals.Separator+ID);
        //If the file exists, delete it, and remake it, because it shouldn't.
        if (newfile.exists()){
            try {
                newfile.delete();
                newfile.createNewFile();
            } catch (Exception ex) {
                Globals.ShowError(ex);
            }
        }
        //save new information to the file
        UpdateFile();
    }

    private void ReadFile(){

        //read the information from the file
        try {
            File newfile        = new File(FavoritesDir + Globals.Separator + ID);
            FileReader fr       = new FileReader(newfile);
            BufferedReader br   = new BufferedReader(fr);

            Name                = br.readLine();
            Path                = br.readLine();
            EmulatorID          = Integer.parseInt(br.readLine());
            Icon                = br.readLine();
            Comment             = br.readLine();
            TimesPlayed         = Integer.parseInt(br.readLine());
            TimeWasted          = Long.parseLong(br.readLine());

            br.close();
            fr.close();
        } catch (Exception ex) {
            Globals.ShowError(ex);
        }
    }

    private void UpdateFile(){
        try {
            //check arguments
            if (Comment.isEmpty()) {
                Comment = new String(" ");
            }
            //update the file
            File oldfile = new File(FavoritesDir + Globals.Separator + ID);
            oldfile.delete();
            oldfile.createNewFile();
            //save new information to the file
            FileWriter fw = new FileWriter(oldfile);
            fw.write(Name);
            fw.write(newline);
            fw.write(Path);
            fw.write(newline);
            fw.write(String.valueOf(EmulatorID));
            fw.write(newline);
            fw.write(Icon);
            fw.write(newline);
            fw.write(Comment);
            fw.write(newline);
            fw.write(String.valueOf(TimesPlayed));
            fw.write(newline);
            fw.write(String.valueOf(TimeWasted));
            fw.close();
        } catch (IOException ex) {
            Globals.ShowError(ex);
        }
    }

    public void DeleteFile(){
        //put the reminder files into an array
        int DirLength = FavoritesDir.list().length;
        File newfiles[] = new File[DirLength];
        for(int i=0;i<DirLength;i++)
            newfiles[i] = new File(FavoritesDir+Globals.Separator+i);
        //delete the current reminder
        File todelete = new File(FavoritesDir+Globals.Separator+ID);
        todelete.delete();
        //move all of the ones past the deleted file to -1
        for(int i=ID+1;i<DirLength;i++)
            newfiles[i].renameTo(newfiles[i-1]);
    }

    public void MoveUp(){
        if(ID==0){
            //already at the top
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(FavoritesDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(FavoritesDir+Globals.Separator+(ID-1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
    }

    public void MoveDown(){
        if(ID==(FavoritesDir.list().length-1)){
            //already at the bottom
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(FavoritesDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(FavoritesDir+Globals.Separator+(ID+1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
    }

    public void MoveTo(int newID){
        if(newID < 0){
            //you can't move it past the firs
        }else if(newID >= FavoritesDir.list().length){
            //you can't move it past the last
        }else if(newID == ID+1){
            //just use move down
            MoveDown();
        }else if (newID == ID-1){
            //just use move up
            MoveUp();
        }else{
            //move it to the selected ID
            //put the current in a temporary file
            File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
            File ThisFavorite = new File(FavoritesDir+Globals.Separator+ID);
            File NewFavorite = new File(FavoritesDir+Globals.Separator+newID);
            //create all files
            File[] AllFiles = new File[FavoritesDir.list().length];
            for(int i=0;i<AllFiles.length;i++){
                AllFiles[i] = new File(FavoritesDir+Globals.Separator+i);
            }
            //move ID to temporary file
            FileManager.MoveFile(ThisFavorite, Temp);
            //decide if we are moving forwards or backwards
            if(ID > newID){
                //moving it backwards
                //SO, move from newID, to one before the current ID, forwards
                //move first file on its own
                for(int i=(ID-1);i>=newID;i--){
                    FileManager.MoveFile(AllFiles[i], AllFiles[i+1]);
                }
                //move temp file into newID
                FileManager.MoveFile(Temp, AllFiles[newID]);
            }else if(ID < newID){
                //moving it forwards
                //SO, move from newID, to one after the current ID, backwards
                //move first file on its own
                for(int i=(ID+1);i<=newID;i++){
                    FileManager.MoveFile(AllFiles[i], AllFiles[i-1]);
                }
                //move temp file into newID
                FileManager.MoveFile(Temp, NewFavorite);
            }
        }
    }

    public String getName(){
        return Name;
    }

    public String getPath(){
        return Path;
    }

    public int getEmulator(){
        return EmulatorID;
    }

    public String getIcon(){
        return Icon;
    }

    public String getComment(){
        return Comment;
    }

    public int getTimesPlayed(){
        return TimesPlayed;
    }

    public void setName(String Name){
        this.Name = Name;
        UpdateFile();
    }

    public void setPath(String Path){
        this.Path = Path;
        UpdateFile();
    }

    public void setEmulator(int EmulatorID){
        this.EmulatorID = EmulatorID;
        UpdateFile();
    }

    public void setIcon(String Icon){
        this.Icon = Icon;
        UpdateFile();
    }

    public void setComment(String Comment){
        this.Comment = Comment;
        UpdateFile();
    }

    public void IncreaseTimesPlayed(){
        TimesPlayed++;
        UpdateFile();
    }

    public void IncreaseTimeWasted(long TimeWasted){
        this.TimeWasted+=TimeWasted;
        UpdateFile();
    }
    
    public long getTimeWasted(){
        return TimeWasted;
    }

    public void setID(int ID){
        this.ID = ID;
    }
}
