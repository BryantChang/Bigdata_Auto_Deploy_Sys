## 大数据平台自助管理系统

该系统为用户提供一套基于浏览器的可视化大数据平台自助管理系统。用户可以利用该系统方便地管理和维护大数据平台，从而更加专注于应用的开发。由于还是学生，该系统都是利用业余时间进行开发与维护，难免有些设计不周到的地方，所以还请大家多提建议。

## 目录结构

```
-- agent(各个节点的守护进程)
-- deploy(安装部署脚本)
-- web(Web服务端程序，部署在Tomcat上)
-- docs(文档)
```

## 安装部署

### 1、下载源代码

```
git clone https://github.com/BryantChang/Bigdata_Auto_Deploy_Sys.git
```

### 2、部署Web应用程序

* 进入deploy目录
* 设置环境变量（编辑env.sh，包括hadoop,spark,tomcat安装目录以及mysql相关配置信息等）
* 若未安装mysql,则首先安装mysql。具体方法使用sh install_mysql.sh进行查看（注意需要在root用户下进行安装）
* 执行web程序安装脚本


```
sh install_web.sh
注意：若需要对数据库进行初始化，则执行-i选项。具体使用方式使用: sh install_web.sh -h。
```

### 3、部署agent

* 配置相关节点列表（编辑agent_node_list文件）
* 安装部署agent

```
执行sh install_agent.sh
```

## 相关配置文件格式

### 1、agent_node_list

```
格式：host/ip=passwd
```

### 2、hadoopnodes

```
格式：host:ip:os:sshport:sshuser:sshpassword:issudo_(1/0):namenode_(1/0):datanode_(1/0):secondarynamenode_(1/0):resourcemanager_(1/0):nodemanager_(1/0):isformatted_(1/0)
```

### 3、sparknodes

```
格式：host:ip:os:sshport:sshuser:sshpassword:master_(1/0):slave_(1/0):issudo_(1/0)
```




