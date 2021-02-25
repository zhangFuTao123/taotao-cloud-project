#!/bin/bash

wget http://www.apache.org/dyn/closer.lua/flume/1.9.0/apache-flume-1.9.0-bin.tar.gz

tar -zxvf apache-flume-1.9.0-bin.tar.gz /root/taotao-bigdata/

mv /root/taotao-bigdata/apache-flume-1.9.0-bin /root/taotao-bigdata/flume1.9.0

vim .bashrc
export FLUME_HOME="/root/taotao-bigdata/flume1.9.0"
export PATH=$PATH:$FLUME_HOME/bin


cd /root/taotao-bigdata/flume1.9.0/conf
cp flume-env.sh.template flume-env.sh
vim flume-env.sh
# export JAVA_HOME=/root/taotao-common/jdk1.8.0_211

cp $HADOOP_HOME/share/hadoop/client/*.jar $FLUME_HOME/lib
cp $HADOOP_HOME/share/hadoop/common/*.jar $FLUME_HOME/lib
cp $HADOOP_HOME/share/hadoop/hdfs/*.jar $FLUME_HOME/lib
cp $HADOOP_HOME/share/hadoop/common/lib/*.jar $FLUME_HOME/lib
cp $HADOOP_HOME/share/hadoop/hdfs/lib/*.jar $FLUME_HOME/lib

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-access-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-access-log-dir-agent.conf -Dflume.root.logger=INFO,console &

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-access-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-access-log-dir-agent.conf -Dflume.root.logger=INFO,console \
-Dflume.monitoring.type=http -Dflume.monitoring.port=31001

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-access-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-access-log-kafka-agent.conf -Dflume.root.logger=INFO,console &

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-access-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-access-log-kafka-agent.conf -Dflume.root.logger=INFO,console \
-Dflume.monitoring.type=http -Dflume.monitoring.port=31001 &

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-log-kafka-agent.conf -Dflume.root.logger=INFO,console &

nohup ${FLUME_HOME}/bin/flume-ng agent -n taotao-cloud-log -c $FLUME_HOME/conf \
-f ${FLUME_HOME}/conf/taotao-cloud-log-kafka-agent.conf -Dflume.root.logger=INFO,console \
-Dflume.monitoring.type=http -Dflume.monitoring.port=31001
