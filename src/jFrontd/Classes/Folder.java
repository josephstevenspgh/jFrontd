package jFrontd.Classes;

import Classes.FileManager;
import Classes.Globals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Joseph Stevens
 */
public class Folder {
    //variables
    private final File ConfigDir = new File(Globals.Home+Globals.Separator+".jFrontd");
    private final File FolderDir = new File(ConfigDir+Globals.Separator+"Folders");
    private String newline = Globals.Newline;
    private int ID;
    private String Name;
    private String Path;
    private String SystemIcon;
    private String GameIcon;
    private int EmuID;
    private boolean showExtensions;
    private boolean mameROMs;
    private boolean hideUnsupportedFiletypes;
    private boolean goodmerged;
    //directory variables
    private BigInteger MD5;
    private String[] FileList;
    private String[] DisplayedList;

    public Folder(int i){
        ID = i;
        ReadFile();
    }

    public Folder(String Name, String Path, int EmuID, boolean showExtensions,
            boolean mameROMs, boolean hideUnsupportedFiletypes,
            boolean goodmerged, String SystemIcon, String GameIcon) {
        ID                              = FolderDir.list().length;
        this.Name                       = Name;
        this.Path                       = Path;
        this.EmuID                      = EmuID;
        this.showExtensions             = showExtensions;
        this.mameROMs                   = mameROMs;
        this.hideUnsupportedFiletypes   = hideUnsupportedFiletypes;
        this.goodmerged                 = goodmerged;
        this.SystemIcon                 = SystemIcon;
        this.GameIcon                   = GameIcon;
        //write new file
        MakeNewFile();
    }

    //creates CRC, FileList, and RealList
    public void LoadDirectory(){
        //create array of files in directory
        File Directory = new File(Path);
        FileList = Directory.list();
        Arrays.sort(FileList);
        //create byte array of FileList
        int ByteArrayLength = 0;
        for(int i=0;i<FileList.length;i++){
            ByteArrayLength += FileList[i].length();
        }
        byte[] ByteArray = new byte[ByteArrayLength];
        //populate byte array
        for(int i=0;i<FileList.length;i++){
            byte[] buffer = FileList[i].getBytes();
            for(int j=0;j<buffer.length;j++){
                ByteArray[i+j] = buffer[j];
            }
        }
        //create new MD5
        BigInteger newMD5;
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            MD.update(ByteArray);
            newMD5 = new BigInteger(MD.digest());
            System.out.println(newMD5);
        } catch (NoSuchAlgorithmException ex) {
            Globals.ShowError(ex);
        }
        //compare new MD5 with old MD5, if there is a change, create new lists
    }
    
    private void MakeNewFile(){
        //create the file variable
        File newfile = new File(FolderDir+Globals.Separator+ID);
        //If the file exists, delete it, and remake it, because it shouldn't.
        if (newfile.exists()){
            try {
                newfile.delete();
                newfile.createNewFile();
            } catch (IOException ex) {
                Globals.ShowError(ex);
            }
        }
        //save new information to the file
        UpdateFile();
    }

    private void ReadFile(){
        //read the information from the file
        FileReader fr = null;
        try {
            File newfile = new File(FolderDir + Globals.Separator + ID);
            if (!newfile.exists()) {
                Globals.ShowError("You've hit a bug!",
                        "You've hit a bug, please contact the author: Folder File does not exist.\nskiffain@gmail.com");
                return;
            }
            //read in variables
            fr = new FileReader(newfile);
            BufferedReader br           = new BufferedReader(fr);
            Name                        = br.readLine();
            Path                        = br.readLine();
            EmuID                       = Integer.parseInt(br.readLine());
            showExtensions              = Boolean.valueOf(br.readLine());
            mameROMs                    = Boolean.valueOf(br.readLine());
            hideUnsupportedFiletypes    = Boolean.valueOf(br.readLine());
            goodmerged                  = Boolean.valueOf(br.readLine());
            SystemIcon                  = br.readLine();
            GameIcon                    = br.readLine();
            br.close();
        } catch (Exception ex) {
            Globals.ShowError(ex);
        }
    }

    //deletes the current folder file, renames the others to fall in line
    public void Delete(){
        //put the reminder files into an array
        int DirLength = FolderDir.list().length;
        File newfiles[] = new File[DirLength];
        for(int i=0;i<DirLength;i++)
            newfiles[i] = new File(FolderDir+Globals.Separator+i);
        //delete the current reminder
        File todelete = new File(FolderDir+Globals.Separator+ID);
        todelete.delete();
        //move all of the ones past the deleted file to -1
        for(int i=ID+1;i<DirLength;i++)
            newfiles[i].renameTo(newfiles[i-1]);
    }

    //saves new information to file
    private void UpdateFile(){
        try {
            //update the file
            File oldfile = new File(FolderDir + Globals.Separator + ID);
            oldfile.delete();
            oldfile.createNewFile();
            //save new information to the file
            FileWriter fw = new FileWriter(oldfile);
            fw.write(Name);
            fw.write(newline);
            fw.write(Path);
            fw.write(newline);
            fw.write(String.valueOf(EmuID));
            fw.write(newline);
            fw.write(String.valueOf(showExtensions));
            fw.write(newline);
            fw.write(String.valueOf(mameROMs));
            fw.write(newline);
            fw.write(String.valueOf(hideUnsupportedFiletypes));
            fw.write(newline);
            fw.write(String.valueOf(goodmerged));
            fw.write(newline);
            fw.write(String.valueOf(SystemIcon));
            fw.write(newline);
            fw.write(String.valueOf(GameIcon));
            fw.close();
        } catch (IOException ex) {
            Globals.ShowError(ex);
        }
    }

    //moves the folder ID/Filename to one previous
    public void MoveUp(){
        if(ID==0){
            //already at the top
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(FolderDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(FolderDir+Globals.Separator+(ID-1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
    }

    //moves the folder ID/Filename to the next
    public void MoveDown(){
        if(ID==(FolderDir.list().length-1)){
            //already at the bottom
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(FolderDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(FolderDir+Globals.Separator+(ID+1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
    }

    //changes the folder ID/Filename
    public void MoveTo(int newID){
        if(newID < 0){
            //you can't move it past the firs
        }else if(newID >= FolderDir.list().length){
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
            File ThisFavorite = new File(FolderDir+Globals.Separator+ID);
            File NewFavorite = new File(FolderDir+Globals.Separator+newID);
            //create all files
            File[] AllFiles = new File[FolderDir.list().length];
            for(int i=0;i<AllFiles.length;i++){
                AllFiles[i] = new File(FolderDir+Globals.Separator+i);
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

    //set/get methods

    public String   getName(){
        return Name;
    }
    public String   getPath(){
        return Path;
    }
    public int      getEmu(){
        return EmuID;
    }
    public boolean  getShowExtensions(){
        return showExtensions;
    }
    public boolean  getMameROMs(){
        return mameROMs;
    }
    public boolean  getHideUnsupportedFiletypes(){
        return hideUnsupportedFiletypes;
    }
    public boolean  getGoodmerged(){
        return goodmerged;
    }
    public String   getGameIcon(){
        return GameIcon;
    }
    public String   getSystemIcon(){
        return SystemIcon;
    }

    public void     setPath(String tPath){
        Path = tPath;
        UpdateFile();
    }
    public void     setEmu(int tEmu){
        EmuID = tEmu;
        UpdateFile();
    }
    public void     setName(String tName){
        Name = tName;
        UpdateFile();
    }
    public void     setShowExtensions(boolean showExtensions){
        this.showExtensions = showExtensions;
        UpdateFile();
    }
    public void     setMameROMs(boolean mameROMs){
        this.mameROMs = mameROMs;
        UpdateFile();
    }
    public void     setHideUnsupportedFiletypes(boolean hideUnsupportedFiletypes){
        this.hideUnsupportedFiletypes = hideUnsupportedFiletypes;
        UpdateFile();
    }
    public void     setGoodmerged( boolean goodmerged){
        this.goodmerged = goodmerged;
        UpdateFile();
    }
    public void     setGameIcon(String Icon){
        this.GameIcon = Icon;
        UpdateFile();
    }
    public void     setSystemIcon(String Icon){
        this.SystemIcon = Icon;
        UpdateFile();
    }
}
