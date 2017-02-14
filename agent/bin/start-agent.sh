#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
DIR=`cd $bin/../; pwd`
CONF_DIR=$DIR/conf
LOG_DIR=$DIR/logs
JAR=$DIR/AutoSysAgent.jar
echo "starting agent"
mkdir -p $LOG_DIR

ip=$(/sbin/ifconfig | grep "inet addr" | grep -v 127.0.0.1 | awk '{print $2}' | awk -F ':' '{print $2}')

if [ -z $ip ]
then
	ip=$(/sbin/ifconfig | grep "inet 地址" | grep -v 127.0.0.1 | awk '{print $2}' | awk -F ':' '{print $2}')
fi

nohup java -jar $JAR -f $CONF_DIR/agent.properties -a $ip > $LOG_DIR/agent.log &