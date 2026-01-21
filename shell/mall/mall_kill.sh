#! /bin/bash
tomcat_home=/home/mallapp/apache-tomcat-8.5.61
cur_date="`date +%Y%m%d`""`date +%H%M%S`"

_pid=`ps -ef |grep $tomcat_home |grep -v 'grep'|grep -v 'sh'|awk '{print $2}'`
echo "tomcat pid $_pid"
if [ "$_pid" = "" ]; then
echo "tomcat pid not exist"
else 
kill -9 $_pid
echo "============tomcat terminated============"
fi
