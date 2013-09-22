qspriter
========

qspriter is a sprite generator for the Quintus Html5 game engine (http://html5quintus.com).

Basically it takes all images (*.png or *.jpg) from a directory an creates a sprite file for these images. 
Furthermore it creates a json file that describes the sprite file.

Both files are ready to be used together with the Quintus game engine.

E.g.
If you have a directory called assets that has all your individual image frames, run:

    java -jar qspriter.jar assets

This will generate a sprites.json and sprites.png file that can be loaded into Quintus.

Asset files should share an initial root and then end in incrementing numbers, e.g.:

    mario01.png
    mario02.png
    mario03.png
    luigi01.png
    luigi02.png

Run
---------

In the directory _/bin_ there is an executeable which can be run on the command line 

    java -jar qspriter.jar [DIRECTORY]
    
_Note:_ You need to have at least Java 7 installed.    
  

Build
---------

If you prefer to compile the source by yourself you can do it with maven

    mvn clean compile
    
To build an executable you can also use maven

    mvn clean compile assembly:single
    
Credits
---------

This sprite generator is heavily inspired by cykod's spriter (https://github.com/cykod/Spriter). 
Unfortunately you need a Node.js installation to run spriter. With qspriter you only need java installed.
