#! /usr/bin/sh

set -e
BASE_PATH=/home/cmsapp/apache-tomcat-8.5.61
APP_NAME=cms.war
WAR_DATE="`date +%Y%m%d`""`date +%H%M%S`"
_PATTERN=$APP_NAME.

#验证不通过 强制停止进程
_pid=`ps -ef |grep $BASE_PATH |grep -v 'grep'|awk '{print $2}'`
echo "tomcat pid $_pid"
if [ "$_pid" != "" ]; then
   echo "found tomcat pid, stop tomcat now"
   kill -9 $_pid
else 
   echo "tomcat pid not exist, do nothing"
fi

_list=`cd $BASE_PATH/webapps;ls -r|grep $APP_NAME` 
#删除有问题的war包
if [[ -f "$BASE_PATH/webapps/$APP_NAME" ]]; then
  mv $BASE_PATH/webapps/$APP_NAME /tmp/$APP_NAME.$WAR_DATE.error       
fi
#确定使用哪个war包进行回滚
for i in $_list;
 do
   if [[ $i == *$_PATTERN* ]]; then
      read -p "使用$i进行回滚, 是否继续(y/n):" _ok
      if [ $_ok == 'y' ] ; then
         mv $BASE_PATH/webapps/$i $BASE_PATH/webapps/$APP_NAME
      else 
         read -p "请输入需要回滚的war包名称：" _war
         if [[ ! -f "$BASE_PATH/webapps/$_war" ]]; then 
	          echo "$BASE_PATH/webapps/$_war不存在,请检查后重新执行回滚脚本"
            exit 0
	        else
            mv $BASE_PATH/webapps/$_war $BASE_PATH/webapps/$APP_NAME
	       fi
      fi
      break
   fi
done
#检查war包是否存在
if [[ ! -f "$BASE_PATH/webapps/$APP_NAME" ]]; then
  echo "$BASE_PATH/webapps/$APP_NAME不存在"
  exit 0
fi
#启动tomcat
echo "开始启动tomcat"
cd $BASE_PATH/bin
./startup.sh
count=1
_start=0
while [[ $count -lt 5 ]]
do
_PID=`ps -ef |grep $BASE_PATH |grep -v 'grep'|awk '{print $2}'`
if [ "$_PID" != "" ]; then
  echo "tomcat启动成功，请进行生产验证"
  _start=1
  break
else
  sleep 1
fi
(( count++ ))
done
if [[ $_start == 0 ]]; then
  echo "未检查到tomcat进程，请手动检查"
fi