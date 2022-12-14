# Minecraft Itembingo (Lite)
A simple Minecraft Itembingo plugin for Minecraft 1.19.x!\
You can also use this plugin in combination with [my timer plugin](https://github.com/dvarlan/Minecraft-Timer)!

## Downloading the plugin

- Click on "Releases"\
![firefox_atpWK2R7Uw](https://user-images.githubusercontent.com/95964411/205129489-1b47b9d5-1949-4871-a709-5451eb925d9d.png)
- Under "Assets" download "Itembingo.jar"
- Copy the .jar into the plugins folder of your server
- Done

## Usage / Commands

- `/bingohelp`: Shows an overview of all the commands & how to use them
- `/team`: Lets the user manipulate / interact with teams (create, delete, join, leave, list)
- `/top`: Teleports the player back to the surface or the overworld if in the nether
- `/bingoboard`: Shows the current bingoboard
- `/bingoboard add ITEM_1 ITEM_2 ... ITEM_9`: Adds the 9 specified items to the board \
   **Note:** The itemnames have to be the ones from [here](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html) for the command to work!\
   E.g. BLUE_DYE, ACACIA_PRESSURE_PLATE, COAL_ORE ...
- `/bingoboard random`: Randomly fills the bingoboard with items \
    **Note:** If there are empty spots on the board rerun the command!
- `/bingo`: Checks if the player / team obtained all items & ends the game \
