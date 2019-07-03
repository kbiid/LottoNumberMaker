#!/bin/bash

export WORK_HOME=/home/kbiid/dist/lib
export CLASSPATH=.
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/log4j-1.2.17.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/logback-classic-1.2.3.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/logback-core-1.2.3.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/slf4j-api-1.7.26.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/slf4j-log4j12-1.7.26.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/slf4j-simple-1.7.26.jar
export CLASSPATH=$CLASSPATH:"$WORK_HOME"/lotto-1.0.0.jar

java -Dconfig.properties=/home/kbiid/dist/conf/application.properties -classpath "$CLASSPATH" kr.co.torpedo.Main

