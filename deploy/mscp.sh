#!/bin/bash
export CUR_NAME=`whoami`

function usage(){
    echo "Usage:$0 ip_config_file dir/file local_path remote_dir"
}

function file_not_found(){
    echo "the node config file not found"
}

function check_expect() {
    type expect >/dev/null 2>&1 || { echo -e >&2 "Expect is not install\nInstall method:\nfor centos: yum -y install expect\nfor ubuntu: apt-get install expect" && exit 1; }
}
#Check if the count of params is leagle
if [ $# -lt 4 ] || [ "$1" = "-help" ]
then
    usage
    exit
fi

check_expect


ip_list_file=$1
scp_type=$2
local_path=$3
remote_dir=$4

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
    echo "creating the directory"
    echo "############################################################################"
    echo ''
    expect -c "
        set timeout 86400  
        spawn ssh $CUR_NAME@$ip mkdir -p $remote_dir
        expect {
          \"*yes/no*\" {send \"yes\r\"; exp_continue}
          \"*password*\" {send \"$passwd\r\"; exp_continue}
        }
    "
    if [[ $scp_type = "dir" ]]; then
        echo ''
        echo "############################################################################"
        echo "copying the directory to the $ip on $remote_dir"
        echo "############################################################################"
        echo ''
        expect -c "
            set timeout 86400  
            spawn scp -r $local_path $CUR_NAME@$ip:$remote_dir/
            expect {
              \"*yes/no*\" {send \"yes\r\"; exp_continue}
              \"*password*\" {send \"$passwd\r\"; exp_continue}
            }
        "
    else
        echo ''
        echo "############################################################################"
        echo "copying the file to the $ip on $remote_dir"
        echo "############################################################################"
        echo ''
        expect -c "
            set timeout 86400  
            spawn scp $local_path $CUR_NAME@$ip:$remote_dir/
            expect {
              \"*yes/no*\" {send \"yes\r\"; exp_continue}
              \"*password*\" {send \"$passwd\r\"; exp_continue}
            }
        "
       
    fi
    echo "finish"

done
echo "all finish"
