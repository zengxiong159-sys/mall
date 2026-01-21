#! /bin/sh
tomcat_home=/home/omsapp/apache-tomcat-8.5.61
appname=oms.war
startup=$tomcat_home/bin/startup.sh

if [ ! -f "$tomcat_home/webapps/$appname" ]; then
echo "$tomcat_home/webapps/$appname，上线包不存在"
else 
echo "======开始启动应用==========="
echo "remove dir"
rm -rf $tomcat_home/webapps/oms
nohup sh $startup &
sleep 2
echo "============启动成功，请观察应用日志=============="
fi
