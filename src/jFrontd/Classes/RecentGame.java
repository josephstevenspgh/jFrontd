package jFrontd.Classes;

/**
 *
 * @author sj
 */
public class RecentGame {
    private String Path;
    private Emulator emulator;
    private String Name;
    private boolean Exists;
    private boolean Favorite;
    private int FavoriteIndex;

    public RecentGame(){
        Exists = false;
        Name = new String("");
        Favorite = false;
    }

    public RecentGame(String Name, String Path, Emulator emulator){
        this.Name       = Name;
        this.Path       = Path;
        this.emulator   = emulator;
        Exists = true;
        Favorite = false;
    }

    public void MakeFavorite(int FavoriteIndex){
        Favorite = true;
        this.FavoriteIndex = FavoriteIndex;
    }

    public boolean isFavorite(){
        return Favorite;
    }

    public int getFavoriteIndex(){
        return FavoriteIndex;
    }

    public boolean getExists(){
        return Exists;
    }

    public String getPath(){
        return Path;
    }

    public String getName(){
        return Name;
    }

    public Emulator getEmulator(){
        return emulator;
    }

    public void setEmulator(Emulator emulator){
        this.emulator = emulator;
    }

    public void setPath(String Path){
        this.Path = Path;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void printInfo() {
        if(Exists){
            System.out.println("Name:     "+Name);
            System.out.println("Path:     "+Path);
            System.out.println("Emulator: "+emulator.getName());
        }else{
            System.out.println("Doesn't Exist");
        }
    }
}
