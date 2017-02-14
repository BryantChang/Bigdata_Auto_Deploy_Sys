#!/bin/bash
export CUR_NAME=`whoami`

function usage(){
    echo "Usage:$0 node_config_file cmd"
}

function file_not_found(){
    echo "the node config file not found"
}

#Check if the count of params is leagle
if [ $# -lt 2 ] || [ "$1" = "-help" ]
then
    usage
    exit
fi

ip_list_file=$1
batch_cmd=$2

#check if the passwd config file is leagle
if [ ! -f $ip_list_file ]; then
    file_not_found
    exit
fi

#generate ssh keys on all nodes in config file
for config_line in $(sed 's/ //g' $ip_list_file)
do
    #ignore the '#'
    if [ ${config_line:0:1} == "#" ] 
    then
        continue;
    fi
    ip=`echo "$config_line" | cut -f1 -d "="`
    passwd=`echo "$config_line" | cut -f2 -d "="`
    echo ''
    echo "############################################################################"
    echo "executing the $batch_cmd on $ip"
    echo "############################################################################"
    echo ''
    expect -c "
        set timeout 86400  
        spawn ssh $CUR_NAME@$ip $batch_cmd
        expect {
          \"*yes/no*\" {send \"yes\r\"; exp_continue}
          \"*password*\" {send \"$passwd\r\"; exp_continue}
        }
    "
    echo "finish"

done
echo "all finish"
