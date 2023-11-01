# HOW TO RUN

Two ways you can run the app: via script (runApp.sh) or directly via ./gradlew.
Script is just for convenience. If you want to parse another file, change argument (file location) via the script.

## [Run method 1: Running the app via runApp.sh script]

1. Change permission of script: chmod+x runApp.sh
2. If you want to parse another file, edit `runApp.sh` script. (Preferrably put file in directory: app > src > main > resources)
3. Run the script in terminal as below:

./runApp.sh <arg>

arg can be one of the following:
    -clean  = clean gradle project
    -b      = build gradle project
    -r      = run gradle project
    -rp     = run gradle project in plain mode, which removes the annoying progress bar


## [Run method 2: Running the app via ./gradlew]

Two ways to run, with the progressbar or without:

1. run command:  ./gradlew run --args="./src/main/resources/calendar.utf8.cal"
Change the argument for the filepath as you wish.

2. If you want to get rid of the pesky progress bar, run the following in plain mode.
run command:  ./gradlew --console=plain run --args="./src/main/resources/calendar.utf8.cal"


# TRANSLATIONS

Available languages:
  - 'en-au' = Australian English (default)
  - 'fl'    = Filipino/Tagalog")

To change locale, select "change locale" and type one of the language tags listed in the "available languages".
