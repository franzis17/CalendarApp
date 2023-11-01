[How to run app]
Two ways you can run the app: script (runApp.sh) or directly via ./gradlew.
Script is just for convenience. Change argument (file location) via the script.

[Running the app via runApp.sh script]
1. Change permission of script: chmod+x runApp.sh
2. If you want to read another file, edit `runApp.sh` script. (Preferrably put file in directory: app > src > main > resources)
3. Run the script in terminal as below:

./runApp.sh <arg>

arg can be one of the following:
    -clean  = clean gradle project
    -b      = build gradle project
    -r      = run gradle project
    -rp     = run gradle project in plain mode, which removes the annoying progress bar

[Running the app via ./gradlew]
1. run command:  ./gradlew run --args="./src/main/resources/calendar.utf8.cal"
Change the filepath as you wish.
2. If you want to get rid of the pesky progress bar, run the following in plain mode.
run command:  ./gradlew --console=plain run --args="./src/main/resources/calendar.utf8.cal"


--------------[ TRANSLATIONS ]--------------

Added filipino language as the another language of the app.
To use the filipino language, select numeric option of "change locale" and type "fl" as the locale.
