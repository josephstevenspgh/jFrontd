                        *********
                    ****         ****
                ****     jFrontd     ****
****************                         ************************
*                                                               *
* Author:       Joseph Stevens                                  *
*                                                               *
* Version:      0.5 beta                                        *
*                                                               *
* Description:  A lightweight multi-emulator Frontend           *
*                                                               *
* Website:      http://sj.arc-nova.org/?page=jFrontd            *
*                                                               *
*    *******************************************************    *
******                                                     ******

************
* Contents *
************
    * Whats new?
    * Known Issues
    * Description
    * Usage
    * More info
    * Source/Bugs
    * Contact


**********************
* Whats new in 0.5b? *
**********************

    * Many, many interface changes
        * Drag/Drop to re-arrange folders, favorites, and lists in settings
        * Sorting for folders, and favorites
        * Menus changed
    * Favorites
    * Icons
    * List of last played games
    * More I forgot about
    * Kenai Project made

****************
* Known Issues *
****************
    "Goodmerge" and "MAME" folder options are still unimplemented.
    Arguments shouldn't work.
    Comments are useless but required.
    I don't know of any others but theres is a lot of testing to do: E-Mail me if you find any bugs!
	

*******************************
* A more detailed description *
*******************************
	I created this front end because its one of the biggest	things I miss about using Windows was the front end QuickPlay (http://quickplay.sourceforge.net/). I'll be aiming to steal a lot of features from this front end, but for now, this is a lightweight multi-emulator front end written in Java, so I can use it on any OS.

	If you have any questions, comments, or feedback, don't	be afraid to contact me. I'm aware that this is probably a very buggy program, but if you notice any, let me know and I'll try to fix it as soon as possible.

*********
* Usage *
*********
	To start the program, "java -jar jFrontd"

	Set up the folders and emulators, from the Edit->Prefrences menu. You can also manage folders by right clicking the folder tree. Some more detailed information on the different fields is below.

	To run your game, double click the rom, hit enter while it is selected, or select Game->Play from the menu.

*********************************************
* More information on Folders and Emulators *
*********************************************

	This has been revamped in version 0.2b, but here is some information on the different fields when creating or editing folders, and emulators.
    Redone again in 0.4b, but not much on the outside.

	Folder Information:

		Name: The name of the folder
		Path: The path the folder will read from
        Emulator: The emulator to load the roms form this folder in.

	Emulator Information:

		Name: The name of the emulator
		Description: A short description of the emulator (Currently unused)
		Path: A path to the executable of the emulator
		Filetypes: A list of filetypes the emulator supports
		Arguments: (Currently unused)

*************************************
* Source download, and bug tracking *
*************************************
    http://kenai.com/projects/jfrontd

***********************
* Contact Information *
***********************

    E-Mail:     Joseph.Stevens.Pgh@gmail.com
    MSN:        skiffain@hotmail.com
    AIM:        setz0x00
