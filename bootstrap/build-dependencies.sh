#!/bin/sh
#
# Script to resolve dependencies for building

##
# download source files
##
if [ "`which wget`" == "" ]; then
	curl -o sbt-0.13.5.tgz -L "http://dl.bintray.com/sbt/native-packages/sbt/0.13.5/sbt-0.13.5.tgz" 
else
  wget -nc http://dl.bintray.com/sbt/native-packages/sbt/0.13.5/sbt-0.13.5.tgz -O sbt-0.13.5.tgz
fi

tar zvxf sbt-0.13.5.tgz
export PATH=$PATH:$PWD/sbt/bin