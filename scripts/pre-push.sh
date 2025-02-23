#!/bin/sh
echo "*********************************************************"
echo "Running git pre-push hook. Running Unit Tests... "
echo "*********************************************************"

# ./gradlew detekt ktlintCheck --daemon

./gradlew test

status=$?

if [ "$status" = 0 ] ; then
    echo "Tests passed, push code to Github."
    exit 0
else
    echo "*********************************************************"
    echo "       ********************************************      "
    echo 1>&2 "Tests failed, fix failing tests"
    echo "       ********************************************      "
    echo "*********************************************************"
    exit 1
fi

#if [ "$status" = 0 ] ; then
#    echo "Static analysis found no problems."
#    exit 0
#else
#    echo "*********************************************************"
#    echo "       ********************************************      "
#    echo 1>&2 "Static analysis found violations it could not fix."
#    echo "Run ./gradlew ktlintFormat to fix formatting related issues."
#    echo "       ********************************************      "
#    echo "*********************************************************"
#    exit 1
#fi