#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
DIR=`cd $bin/../; pwd`
CONF_DIR=$DIR/conf
LOG_DIR=$DIR/logs
IFCONFIG_PATH=/sbin/ifconfig
JAR=$DIR/AutoSysAgent.jar

function need_ifocnfig(){
	echo "The command ifconfig not installed, please install it!"
}

if [[ ! -f $IFCONFIG_PATH ]]; then
	need_ifocnfig
	exit
fi


echo "starting agent"
mkdir -p $LOG_DIR

ip=$(/sbin/ifconfig | grep "inet addr" | grep -v 127.0.0.1 | awk '{print $2}' | awk -F ':' '{print $2}')

if [ -z $ip ]
then
	ip=$(/sbin/ifconfig | grep "inet 地址" | grep -v 127.0.0.1 | awk '{print $2}' | awk -F ':' '{print $2}')
fi

nohup java -jar $JAR -f $CONF_DIR/agent.properties -a $ip > $LOG_DIR/agent.log &