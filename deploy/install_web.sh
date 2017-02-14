#!/bin/bash
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
BASE_DIR=`cd $bin/../; pwd`
SCRIPT_DIR=$BASE_DIR/deploy
WEB_DIR=$BASE_DIR/web
AGENT_DIR=$BASE_DIR/agent
JAVA_CONSTANT_FILE_DIR=$WEB_DIR/src/main/java/com/bryantchang/autodepsys/constant/Constants.java
install_mysql="false"
initial_db="false"


function usage(){
	echo "Usage: $0 -i(will initial database, including create database and tables)"
}

if [[ $1 = "-help" || $1 = "-h" ]]; then
	usage
	exit
fi
#Set some environments
echo "set some environments..."

. "${SCRIPT_DIR}/env.sh" 


#parse the input arguments
while getopts "i" arg 
do
    case $arg in
        i)
			initial_db="true"
            ;;
        ?)
        usage
        exit 1
        ;;
    esac
done



#Modify the Constant file
#mysql configs
sed -i "/MYSQL_URL = / c \    public static final String MYSQL_URL = \"${MYSQL_URL}\";" $JAVA_CONSTANT_FILE_DIR
sed -i "/MYSQL_USR = / c \    public static final String MYSQL_USR = \"${MYSQL_USR}\";" $JAVA_CONSTANT_FILE_DIR
sed -i "/MYSQL_PASS = / c \    public static final String MYSQL_PASS = \"${MYSQL_PASS}\";" $JAVA_CONSTANT_FILE_DIR
#Web server
sed -i "/BaseUrl = / c \    public static final String BaseUrl = \"${BaseUrl}\";" $JAVA_CONSTANT_FILE_DIR
#Tomcat configs
sed -i "/CATALINA_HOME = / c \    public static final String CATALINA_HOME = \"${CATALINA_HOME}\";" $JAVA_CONSTANT_FILE_DIR
#Hadoop configs
sed -i "/HADOOP_HOME = / c \    public static final String HADOOP_HOME = \"${HADOOP_HOME}\";" $JAVA_CONSTANT_FILE_DIR
#Spark configs
sed -i "/SPARK_HOME = / c \    public static final String SPARK_HOME = \"${SPARK_HOME}\";" $JAVA_CONSTANT_FILE_DIR
#Agent configs
sed -i "/AGENTPORT = / c \    public static final String AGENTPORT = \"${AGENT_PORT}\";" $JAVA_CONSTANT_FILE_DIR

##Compiling and packaging 
echo "compiling and packaging programme..."
cd $WEB_DIR && mvn clean package 

if [[ ! -f $WEB_DIR/target/autodeploymentsys.war ]]; then
	echo "compile failed"
	exit
else
	echo "compile success copying to the tomcat..."
	cp $WEB_DIR/target/autodeploymentsys.war $CATALINA_HOME/webapps
fi

if [[ $initial_db = "true" ]]; then
	echo "initialing db..."
	echo "modify the sql file..."
	if [[ -f $SCRIPT_DIR/autosys.sql ]]; then
		rm -rf $SCRIPT_DIR/autosys.sql
	fi
	cp $SCRIPT_DIR/autosys.sql.templet $SCRIPT_DIR/autosys.sql
	#count the available hadoop node to initiallized
	hadoop_node_count=$(sed '/^#/d' $SCRIPT_DIR/hadoopnodes | wc -l )

	if [[ $hadoop_node_count -eq 0 ]]; then
		echo "no hadoop nodes continue..."
	else
		index=0
		echo "INSERT INTO \`hadoopnode\` (\`hostname\`,\`ip\`,\`os\`,\`sshport\`,\`sshuser\`,\`sshpass\`,\`issudo\`,\`namenode\`,\`datanode\`,\`secondarynamenode\`,\`resourcemanager\`,\`nodemanager\`,\`isformatted\`)  VALUE" >> $SCRIPT_DIR/autosys.sql
		for node_line in $(sed 's/ //g' $SCRIPT_DIR/hadoopnodes)
		do
		    #ignore the '#'
		    if [ ${node_line:0:1} == "#" ] 
		    then
		        continue;
		    fi
		    let index++
		    hadoop_node_host=`echo "$node_line" | cut -f1 -d ":"`
		    hadoop_node_ip=`echo "$node_line" | cut -f2 -d ":"`
		    hadoop_node_os=`echo "$node_line" | cut -f3 -d ":"`
		    hadoop_node_sshport=`echo "$node_line" | cut -f4 -d ":"`
		    hadoop_node_sshuser=`echo "$node_line" | cut -f5 -d ":"`
		    hadoop_node_sshpasswd=`echo "$node_line" | cut -f6 -d ":"`
		    hadoop_node_issudo=`echo "$node_line" | cut -f7 -d ":" | cut -f2 -d "_"`
		    hadoop_node_namenode=`echo "$node_line" | cut -f8 -d ":" | cut -f2 -d "_"`
		    hadoop_node_datanode=`echo "$node_line" | cut -f9 -d ":" | cut -f2 -d "_"`
		    hadoop_node_secondarynamenode=`echo "$node_line" | cut -f10 -d ":" | cut -f2 -d "_"`
		    hadoop_node_resourcemanager=`echo "$node_line" | cut -f11 -d ":" | cut -f2 -d "_"`
		    hadoop_node_nodemanager=`echo "$node_line" | cut -f12 -d ":" | cut -f2 -d "_"`
		    hadoop_node_isformatted=`echo "$node_line" | cut -f13 -d ":" | cut -f2 -d "_"`
			if [[ $index -eq $hadoop_node_count ]]; then
				insert_sql_str="('$hadoop_node_host','$hadoop_node_ip','$hadoop_node_os','$hadoop_node_sshport','$hadoop_node_sshuser','$hadoop_node_sshpasswd','$hadoop_node_issudo','$hadoop_node_namenode','$hadoop_node_datanode','$hadoop_node_secondarynamenode','$hadoop_node_resourcemanager','$hadoop_node_nodemanager','$hadoop_node_isformatted');"
			else
				insert_sql_str="('$hadoop_node_host','$hadoop_node_ip','$hadoop_node_os','$hadoop_node_sshport','$hadoop_node_sshuser','$hadoop_node_sshpasswd','$hadoop_node_issudo','$hadoop_node_namenode','$hadoop_node_datanode','$hadoop_node_secondarynamenode','$hadoop_node_resourcemanager','$hadoop_node_nodemanager','$hadoop_node_isformatted'),"
			fi
			echo $insert_sql_str >> $SCRIPT_DIR/autosys.sql
		done
	fi

	#count the available hadoop node to initiallized
	spark_node_count=$(sed '/^#/d' $SCRIPT_DIR/sparknodes | wc -l )

	if [[ $spark_node_count -eq 0 ]]; then
		echo "no spark nodes continue..."
	else
		index=0
		echo "INSERT INTO \`sparknode\` (\`hostname\`,\`ip\`,\`os\`,\`sshport\`,\`sshuser\`,\`sshpass\`,\`master\`,\`slave\`,\`issudo\`)  VALUE" >> $SCRIPT_DIR/autosys.sql
		for node_line in $(sed 's/ //g' $SCRIPT_DIR/sparknodes)
		do
		    #ignore the '#'
		    if [ ${node_line:0:1} == "#" ] 
		    then
		        continue;
		    fi
		    let index++
		    spark_node_host=`echo "$node_line" | cut -f1 -d ":"`
		    spark_node_ip=`echo "$node_line" | cut -f2 -d ":"`
		    spark_node_os=`echo "$node_line" | cut -f3 -d ":"`
		    spark_node_sshport=`echo "$node_line" | cut -f4 -d ":"`
		    spark_node_sshuser=`echo "$node_line" | cut -f5 -d ":"`
		    spark_node_sshpasswd=`echo "$node_line" | cut -f6 -d ":"`
		    spark_node_master=`echo "$node_line" | cut -f7 -d ":" | cut -f2 -d "_"`
		    spark_node_slave=`echo "$node_line" | cut -f8 -d ":" | cut -f2 -d "_"`
		    spark_node_issudo=`echo "$node_line" | cut -f9 -d ":" | cut -f2 -d "_"`
			if [[ $index -eq $spark_node_count ]]; then
				insert_sql_str="('$spark_node_host','$spark_node_ip','$spark_node_os','$spark_node_sshport','$spark_node_sshuser','$spark_node_sshpasswd','$spark_node_master','$spark_node_slave','$spark_node_issudo');"
			else
				insert_sql_str="('$spark_node_host','$spark_node_ip','$spark_node_os','$spark_node_sshport','$spark_node_sshuser','$spark_node_sshpasswd','$spark_node_master','$spark_node_slave','$spark_node_issudo'),"
			fi
			echo $insert_sql_str >> $SCRIPT_DIR/autosys.sql
		done
		mysql -u$MYSQL_USR -p$MYSQL_PASS < $SCRIPT_DIR/autosys.sql
	fi
	echo "finish"
fi
echo "all finish"










