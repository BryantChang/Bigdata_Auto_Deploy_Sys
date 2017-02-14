#!/bin/bash
#script to install mysql
function need_be_root(){
	echo "you need to switch to root user to execute this scirpt"
}

function usage(){
	echo "Usage: $0 <user for authorized> <os version> <passwd(default:123456)>"
}

#get the dir of this script
bin=`dirname "$0"`
DIR=`cd "$bin"; pwd`

#some macro vars
CUR_USER=`whoami`
MYSQL_CONFIG_DIR="/tools/mysql"
SRC_DIR="/tools/src"
MYSQL_CONFIG_FILE=$DIR/my.cnf

#check the parameter
if [[ $1 == "-h" || $1 == "-help" || $# -lt 2 ]]; then
	usage
	exit
fi

mysql_user=$1
os=$2
mysql_passwd=$3


#This script must be executed by root
if [[ $CUR_USER != "root" ]]; then
	need_be_root
	exit 1
fi

if [[ -z $mysql_user ]]; then
	echo "mysql user is null\n"
	exit 1
fi

if [[ -z $mysql_passwd ]]; then
	mysql_passwd="123456"
fi

#initialize user and directory
/usr/sbin/groupadd mysql
/usr/sbin/useradd -g mysql mysql
mkdir -p $MYSQL_CONFIG_DIR/3306/data/
mkdir -p $MYSQL_CONFIG_DIR/3306/binlog/
mkdir -p $MYSQL_CONFIG_DIR/3306/relaylog/
chown -R mysql:mysql $MYSQL_CONFIG_DIR

#install the dependency libs
echo "installing the extra libs..."

if [[ $os == "ubuntu" ]]; then
	sudo apt-get install build-essential libncurses5-dev cmake
elif [[ $os == "centos" ]]; then
	yum -y install gcc gcc-c++ autoconf libtool cmake cmake-devel make freetype freetype-devel libxml2 libxml2-devel libjpeg libjpeg-devel libpng libpng-devel libwebp-devel libjpeg-turbo-devel libmcrypt libmcrypt-devel mcrypt mhash zlib zlib-devel glibc glibc-devel glib2 glib2-devel bzip2 bzip2-devel
	yum -y install svn svn-devel curl curl-devel libcurl-devel openssl openssl-devel openldap openldap-devel nss_ldap openldap-clients openldap-servers ncurses ncurses-devel e2fsprogs e2fsprogs-devel krb5 krb5-devel libidn libidn-devel 
	yum -y install libevent libevent-devel libmemcached libmemcached-devel ImageMagick ImageMagick-devel db4-devel gdbm-devel libXpm-devel mysql-devel sqlite-devel libtidy-devel libxslt-devel 
fi

cp -frp /usr/lib64/libldap* /usr/lib/



#download the source of mysql
echo "downloading mysql..."
mkdir -p $MYSQL_CONFIG_DIR
cd $MYSQL_CONFIG_DIR
wget http://downloads.mysql.com/archives/get/file/mysql-5.5.31.tar.gz

#install mysql
echo "installing..."
tar zxvf mysql-5.5.31.tar.gz
cd mysql-5.5.31/
cmake -DCMAKE_INSTALL_PREFIX=/usr/local/mysql -DMYSQL_DATADIR=$MYSQL_CONFIG_DIR/3306/data -DSYSCONFDIR=/etc -DMYSQL_UNIX_ADDR=$MYSQL_CONFIG_DIR/mysql.sock -DWITH_DEBUG=0 -DENABLED_LOCAL_INFILE=1 -DENABLE_PROFILING=1 -DENABLE_ASSEMBLER=1 -DENABLE_THREAD_SAFE_CLIENT=1 -DWITH_BIG_TABLES=1 -DWITH_READLINE=1 -DWITH_SSL=system -DWITH_ZLIB=system -DEXTRA_CHARSETS=complex -DDEFAULT_CHARSET=utf8 -DDEFAULT_COLLATION=utf8_general_ci -DMYSQL_TCP_PORT=3306 -DMYSQL_USER=mysql -DWITH_MYISAM_STORAGE_ENGINE=1 -DWITH_INNOBASE_STORAGE_ENGINE=1 -DWITH_ARCHIVE_STORAGE_ENGINE=1 -DWITH_BLACKHOLE_STORAGE_ENGINE=1 -DWITH_MEMORY_STORAGE_ENGINE=1 -DWITH_PARTITION_STORAGE_ENGINE=1 -DDOWNLOAD_BOOST=1 -DWITH_BOOST=/usr/local/boost
make && make install

#configure the mysql
echo "configuring mysql..."
chmod +w /usr/local/mysql
chown -R mysql:mysql /usr/local/mysql
ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql
cp $DIR/my.cnf /etc/my.cnf
chmod 644 /etc/my.cnf
chmod 755 /usr/local/mysql/scripts/mysql_install_db

#start mysql and authorization user
echo "prepare and start..."
/usr/local/mysql/scripts/mysql_install_db --basedir=/usr/local/mysql --datadir=$MYSQL_CONFIG_DIR/3306/data --user=mysql
cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
chmod 755 /etc/init.d/mysql
chkconfig mysql on
echo 'export PATH=/usr/local/mysql/bin:$PATH' >> /etc/profile 
service mysql start

#warning can be executed only once
echo "initialzing..."
/usr/local/mysql/bin/mysqladmin -u root password '123456'
/usr/local/mysql/bin/mysqladmin -u $mysql_user password $mysql_passwd

echo "authorizing the user root and the customer user"
/usr/local/mysql/bin/mysql -uroot -p123456 -e "
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'127.0.0.1' IDENTIFIED BY '123456';
"

/usr/local/mysql/bin/mysql -uroot -p123456 -e "
GRANT ALL PRIVILEGES ON *.* TO '${mysql_user}'@'localhost' IDENTIFIED BY '${mysql_passwd}';
GRANT ALL PRIVILEGES ON *.* TO '${mysql_user}'@'127.0.0.1' IDENTIFIED BY '${mysql_passwd}';
"

/usr/local/mysql/bin/mysql -uroot -p123456 -e "
GRANT ALL PRIVILEGES ON *.* TO '${mysql_user}'@'localhost' IDENTIFIED BY '${mysql_passwd}';
GRANT ALL PRIVILEGES ON *.* TO '${mysql_user}'@'127.0.0.1' IDENTIFIED BY '${mysql_passwd}';
GRANT ALL PRIVILEGES ON *.* TO '${mysql_user}'@'%' IDENTIFIED BY '${mysql_passwd}';
"













