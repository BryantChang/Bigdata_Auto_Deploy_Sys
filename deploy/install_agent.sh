#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
BASE_DIR=`cd $bin/../; pwd`
SCRIPT_DIR=$BASE_DIR/deploy
WEB_DIR=$BASE_DIR/web
AGENT_DIR=$BASE_DIR/agent
conf_file=$AGENT_DIR/conf/agent.properties
node_list_file=$SCRIPT_DIR/agent_node_list

echo "set some environments..."
. "${SCRIPT_DIR}/env.sh" 

#Modify the agent config
#Hadoop configs
sed -i "/HADOOP_HOME=/ c HADOOP_HOME=${HADOOP_HOME}" $conf_file
#Spark configs
sed -i "/SPARK_HOME=/ c SPARK_HOME=${SPARK_HOME}" $conf_file
sed -i "/SPARK_MASTER_PORT=/ c SPARK_MASTER_PORT=${SPARK_MASTER_PORT}" $conf_file
#Agent configs
sed -i "/AGENT_PORT=/ c AGENT_PORT=${AGENT_PORT}" $conf_file

echo "generate password config file..."

cp $node_list_file $AGENT_DIR/conf/node_passwd

##Compiling and packaging 
echo "compiling and packaging programme..."
cd $AGENT_DIR && mvn clean package 

if [[ ! -e $AGENT_DIR/target/deploy ]]; then
	echo "compile failed"
	exit
else
	echo "copy agent to the nodes..."
	sh $SCRIPT_DIR/mscp.sh $node_list_file dir $AGENT_DIR/target/deploy $AGENT_INSTALL_HOME
fi











