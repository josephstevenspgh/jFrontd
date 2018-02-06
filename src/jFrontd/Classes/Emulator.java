package jFrontd.Classes;

import Classes.FileManager;
import Classes.Globals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Joseph Stevens
 */
public class Emulator {
    //variables
    private final File ConfigDir = new File(Globals.Home+Globals.Separator+".jFrontd");
    private final File EmulatorDir = new File(ConfigDir+Globals.Separator+"Emulators");
    private final File FolderDir = new File(ConfigDir+Globals.Separator+"Folders");
    private final File FavoritesDir = new File(ConfigDir+Globals.Separator+"Favorites");
    private final String newline = new String("\n");
    protected int ID;
    protected String Name;
    protected String Path;
    protected String Description;
    protected String Filetypes;
    protected String Arguments;

    public Emulator(int ID){
        //initialize variables
        this.ID = ID;
        ReadFile();
    }

    public Emulator(String Name, String Path, String Description,
            String Arguments, String Filetypes){
        ID                  = EmulatorDir.list().length;
        this.Name           = Name;
        this.Path           = Path;
        this.Description    = Description;
        this.Arguments      = Arguments;
        this.Filetypes      = Filetypes;
        MakeNewFile();
    }

    private void MakeNewFile(){
        //create the variables
        File newfile = new File(EmulatorDir+Globals.Separator+ID);
        //If the file exists, delete it, and remake it, because it shouldn't.
        if (newfile.exists()){
            newfile.delete();
            try {
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
        try {
            File newfile        = new File(EmulatorDir + Globals.Separator + ID);
            FileReader fr       = new FileReader(newfile);
            BufferedReader br   = new BufferedReader(fr);

            Name                = br.readLine();
            Description         = br.readLine();
            Path                = br.readLine();
            Filetypes           = br.readLine();
            Arguments           = br.readLine();

            br.close();
            fr.close();
        } catch (Exception ex) {
            Globals.ShowError(ex);
        }
    }

    public void DeleteFile(){
        //put the reminder files into an array
        int DirLength = EmulatorDir.list().length;
        File newfiles[] = new File[DirLength];
        for(int i=0;i<DirLength;i++)
            newfiles[i] = new File(EmulatorDir+Globals.Separator+i);
        //delete the current reminder
        File todelete = new File(EmulatorDir+Globals.Separator+ID);
        todelete.delete();
        //move all of the ones past the deleted file to -1
        for(int i=ID+1;i<DirLength;i++)
            newfiles[i].renameTo(newfiles[i-1]);
    }

    private void UpdateFile(){
        try {
            //check arguments
            if (Arguments.isEmpty()) {
                Arguments = new String(" ");
            }
            //update the file
            File oldfile = new File(EmulatorDir + Globals.Separator + ID);
            oldfile.delete();
            oldfile.createNewFile();
            //save new information to the file
            FileWriter fw = new FileWriter(oldfile);
            fw.write(Name);
            fw.write(newline);
            fw.write(Description);
            fw.write(newline);
            fw.write(Path);
            fw.write(newline);
            fw.write(Filetypes);
            fw.write(newline);
            fw.write(Arguments);
            fw.close();
        } catch (IOException ex) {
            Globals.ShowError(ex);
        }
    }

    public void MoveUp(){
        if(ID==0){
            //already at the top
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(EmulatorDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(EmulatorDir+Globals.Separator+(ID-1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
        //fix folders
        for(int i=0;i<FolderDir.list().length;i++){
            Folder folder = new Folder(i);
            //if the folder used this ID
            if(folder.getEmu() == ID){
                //change the ID of the emulator
                folder.setEmu(ID-1);
            } else if (folder.getEmu() == ID-1){
                folder.setEmu(ID);
            }
        }
        //fix favorites - same as above
        for(int i=0;i<FavoritesDir.list().length;i++){
            FavoriteGame FG = new FavoriteGame(i);
            if(FG.getEmulator() == ID){
                FG.setEmulator(ID-1);
            } else if (FG.getEmulator() == ID-1){
                FG.setEmulator(ID);
            }
        }
    }

    public void MoveDown(){
        if(ID==(EmulatorDir.list().length-1)){
            //already at the bottom
            return;
        }
        //this method will change the filename/ID so that it moves up in the displayed lists
        File ThisFavorite = new File(EmulatorDir+Globals.Separator+ID);
        File Temp = new File(ConfigDir+Globals.Separator+"TempFile");
        File PrevFavorite = new File(EmulatorDir+Globals.Separator+(ID+1));
        //move files around
        FileManager.MoveFile(PrevFavorite, Temp);
        FileManager.MoveFile(ThisFavorite, PrevFavorite);
        FileManager.MoveFile(Temp, ThisFavorite);
        //fix folders
        for(int i=0;i<FolderDir.list().length;i++){
            Folder folder = new Folder(i);
            //if the folder used this ID
            if(folder.getEmu() == ID){
                //change the ID of the emulator
                folder.setEmu(ID+1);
            } else if (folder.getEmu() == ID+1){
                folder.setEmu(ID);
            }
        }
        //fix favorites - same as above
        for(int i=0;i<FavoritesDir.list().length;i++){
            FavoriteGame FG = new FavoriteGame(i);
            if(FG.getEmulator() == ID){
                FG.setEmulator(ID+1);
            } else if (FG.getEmulator() == ID+1){
                FG.setEmulator(ID);
            }
        }
    }

    public void MoveTo(int newID){
        if(newID < 0){
            //you can't move it past the firs
        }else if(newID >= EmulatorDir.list().length){
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
            File ThisFavorite = new File(EmulatorDir+Globals.Separator+ID);
            File NewFavorite = new File(EmulatorDir+Globals.Separator+newID);
            //create all files
            File[] AllFiles = new File[EmulatorDir.list().length];
            for(int i=0;i<AllFiles.length;i++){
                AllFiles[i] = new File(EmulatorDir+Globals.Separator+i);
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
            //fix folders
            for(int i=0;i<FolderDir.list().length;i++){
                Folder folder = new Folder(i);
                //if the folder used this ID
                if(folder.getEmu() == ID){
                    //change the ID of the emulator
                    folder.setEmu(newID);
                } else if (folder.getEmu() == newID){
                    //swap IDs
                    folder.setEmu(ID);
                }
            }
            //fix favorites - same as above
            for(int i=0;i<FavoritesDir.list().length;i++){
                FavoriteGame FG = new FavoriteGame(i);
                if(FG.getEmulator() == ID){
                    FG.setEmulator(newID);
                } else if (FG.getEmulator() == newID){
                    FG.setEmulator(ID);
                }
            }
        }
    }

    public String getName(){
        return Name;
    }

    public void setName(String tName){
        Name = tName;
        UpdateFile();
    }

    public String getPath(){
        return Path;
    }

    public void setPath(String tPath){
        Path = tPath;
        UpdateFile();
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(String tDescription){
        Description = tDescription;
        UpdateFile();
    }

    public String getArguments(){
        return Arguments;
    }

    public void setArguments(String tArguments){
        Arguments = tArguments;
        UpdateFile();
    }

    public String getFileTypes(){
        return Filetypes;
    }

    public String[] getFileTypesArray(){
        String[] FileTypeArray = null;
        FileTypeArray = Filetypes.split(",");
        return FileTypeArray;
    }

    public void setFileTypes(String tFileTypes){
        Filetypes = tFileTypes;
        UpdateFile();
    }

    public void setFileTypesArray(String[] tFiletypes){
        Filetypes = tFiletypes[0];
        for(int i=1;i<tFiletypes.length;i++){
            Filetypes.concat(",");
            Filetypes.concat(tFiletypes[i]);
        }
        UpdateFile();
    }
}

