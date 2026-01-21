#! /bin/bash
#需要改动变量
tomcat_home=/home/amallapp/apache-tomcat-8.5.61
appname=wechatmall.war
#固定变量
cur_dateTime="`date +%Y-%m-%d,%H:%m:%s`"
shutdown=$tomcat_home/bin/shutdown.sh
startup=$tomcat_home/bin/startup.sh
echo "======第一步：开始校验投产包========="
#war包检查
if [ ! -f "$tomcat_home/$appname" ]; then
echo "$tomcat_home目录下的$appname包没有发现，请确定本次发版的war包是否已经上传"
exit 0
fi
echo "======第一步：投产包校验成功========="

echo "======第二步：开始备份运行war包======"
cp $tomcat_home/webapps/$appname  $tomcat_home/webapps/$appname.$cur_dateTime
echo "======第二步：备份运行war包成功======"

echo "======第三步：开始停止应用==========="
$shutdown 
sleep 3
ps -ef |grep tomcat |grep $tomcat_home |grep -v 'grep'|awk '{print $2}' | xargs kill -9 
echo "======第三步：应用停止成功==========="

echo "======第四步：开始启动应用==========="
rm $tomcat_home/webapps/$appname 
mv $tomcat_home/$appname  $tomcat_home/webapps 

if [ ! -f "$tomcat_home/webapps/$appname" ]; then
echo "$tomcat_home/webapps/$appname，请确定上线包是否完整"
exit 0
fi
$startup
echo "============启动成功，请观察应用日志==================="
