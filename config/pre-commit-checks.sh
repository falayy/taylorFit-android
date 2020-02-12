#!/bin/sh

echo "Attempting to auto-format your files with spotless plugin..."

./gradlew spotlessApply

status=$?

if [ "$status" = 0 ] ; then
    echo "Auto-format successful, no problem 😄"
    exit 0
else
    echo 1>&2 "☹️ Spotless plugin found code-format issues it could not fix, you might want to check through your files manually."
    exit 1
fi