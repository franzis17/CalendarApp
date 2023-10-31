#!/bin/bash

clean() {
    echo ">>> Cleaning gradle build..."
    ./gradlew clean
}

build() {
    echo ">>> Building gradle project..."
    ./gradlew build
}

run() {
    echo ">>> Running gradle project..."
    ./gradlew run --args="./src/main/resources/calendar.utf8.cal"
}

runPlain() {
    ./gradlew --console=plain run --args="./src/main/resources/calendar.utf8.cal"
}


if [ "$#" -eq 0 ]; then
    echo "No arguments provided"
else
    # Loop through the script's arguments
    for arg in "$@"; do
        case "$arg" in
            -clean)
                clean
                ;;
            -b)
                build
                ;;
            -r)
                run
                ;;
            -rp)
                runPlain
                ;;
            -cbr)
                clean
                build
                run
                ;;
            -cbrp)
                clean
                build
                runPlain
                ;;
            *)
                # Handle other arguments or display an error message
                echo "The argument does not exist."
                echo "Unknown argument: $arg"
                exit 1
                ;;
        esac
    done
fi