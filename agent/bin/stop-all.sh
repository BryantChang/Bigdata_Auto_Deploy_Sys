#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
DIR=`cd $bin/../; pwd`
CONF_DIR=$DIR/conf
sh $DIR/bin/ssh_cmd.sh $CONF_DIR/node_passwd "sh $DIR/bin/stop-agent.sh"