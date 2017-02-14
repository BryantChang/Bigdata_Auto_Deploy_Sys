#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
DIR=`cd $bin/../; pwd`
CONF_DIR=$DIR/conf
LOG_DIR=$DIR/logs
JAR=$DIR/AutoSysAgent.jar
echo "stopping agent"
PID=$(jps | grep AutoSysAgent.jar | cut -d ' ' -f1)
kill -9 $PID