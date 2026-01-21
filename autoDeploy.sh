#/bin/sh

set -e
cur_date="`date "+%Y-%m-%d %H:%M:%S"`"
war_date="`date +%Y%m%d`""`date +%H%M%S`"

cms_tomcat_path_sit1=/home/cmsapp/apache-tomcat-8.5.61
cms_tomcat_path_sit2=/home/cmsapp/apache-tomcat-8.5.61
cms_tomcat_path_uat=/home/cmsapp/apache-tomcat-8.5.61
cms_war_name=cms.war
cms_user=cmsapp
cms_ip_sit1=10.1.94.226
cms_ip_sit2=10.238.145.196
cms_ip_uat=10.1.94.231

oms_tomcat_path_sit1=/home/omsapp/apache-tomcat-8.5.61
oms_tomcat_path_sit2=/home/omsapp/apache-tomcat-8.5.61
oms_tomcat_path_uat=/home/omsapp/apache-tomcat-8.5.61
oms_war_name=oms.war
oms_user=omsapp
oms_ip_sit1=10.1.94.225
oms_ip_sit2=10.238.145.195
oms_ip_uat=10.1.94.230

mall_tomcat_path_sit1=/home/mallapp/apache-tomcat-8.5.61
mall_tomcat_path_sit2=/home/mallapp/apache-tomcat-8.5.61
mall_tomcat_path_uat=/home/mallapp/apache-tomcat-8.5.61
mall_war_name=mall.war
mall_user=mallapp
mall_ip_sit1=10.1.94.227
mall_ip_sit2=10.238.145.194
mall_ip_uat=10.1.94.232

tomcat_path_sit1=""
tomcat_path_sit2=""
tomcat_path_uat=""
war_name=""
_user=""
ip_sit1=""
ip_sit2=""
ip_uat=""

up_to_date="up to date"
_branch="unknown"
u_name="user.name"

function deploy ()
{
    echo "******** upload war *******"
	scp $1 $2@$3:$4

	echo "******** backup war *******"
	ssh $2@$3 "mv $4/webapps/$war_name $4/webapps/$war_name.$war_date"

	echo "******** move war *******"
	ssh $2@$3 "mv $4/$war_name $4/webapps/"

	echo "******** save deploy info *******"
	local info="["$cur_date"] branch: "$6", deployed by: "$5
	ssh $2@$3 "echo $info >> $4/deploy.info"

	echo "******** restart tomcat *******"

	ssh $2@$3 "sh $4/bin/$7_kill.sh"
	sleep 5
	ssh $2@$3 "sh $4/bin/$7_fire.sh"

	echo "******** deploy end ***********"
}

echo "********** "$cur_date" *******"

echo "********** "STEP 1: 选择部署系统" *******"
read -p "请输入需要部署的系统(cms/oms/mall):" system
if [ $system == 'cms' ] ; then
    tomcat_path_sit1=$cms_tomcat_path_sit1
    tomcat_path_sit2=$cms_tomcat_path_sit2
    tomcat_path_uat=$cms_tomcat_path_uat
    war_name=$cms_war_name
    _user=$cms_user
    ip_sit1=$cms_ip_sit1
    ip_sit2=$cms_ip_sit2
    ip_uat=$cms_ip_uat
elif [[ $system == 'oms' ]] ; then
    tomcat_path_sit1=$oms_tomcat_path_sit1
    tomcat_path_sit2=$oms_tomcat_path_sit2
    tomcat_path_uat=$oms_tomcat_path_uat
    war_name=$oms_war_name
    _user=$oms_user
    ip_sit1=$oms_ip_sit1
    ip_sit2=$oms_ip_sit2
    ip_uat=$oms_ip_uat
elif [[ $system == 'mall' ]] ; then
    tomcat_path_sit1=$mall_tomcat_path_sit1
    tomcat_path_sit2=$mall_tomcat_path_sit2
    tomcat_path_uat=$mall_tomcat_path_uat
    war_name=$mall_war_name
    _user=$mall_user
    ip_sit1=$mall_ip_sit1
    ip_sit2=$mall_ip_sit2
    ip_uat=$mall_ip_uat
else
	echo "不支持的系统参数"
	exit 0
fi

echo "********** "STEP 2: 选择部署环境和分支" *******"
read -p "请输入部署环境(sit1/sit2/uat/prod):" env
if [ $env == 'sit1' ] ; then
	read -p "请输入分支名称:" branch
    git checkout $branch
	_branch=$branch
	echo "******** checkout *******"
	_pull=`git pull 2>&1`
	echo "$_pull"
	if [[ $_pull == *$up_to_date* ]] ; then
		read -p "当前分支代码已经是最新，是否重新打包(y/n):" pk
		if [ $pk == 'y' ] ; then
			echo "******** package *******"
			mvn clean package -Dmaven.test.skip=true -Psit -f pom.xml
		else
			read -p "是否使用已有war包继续上传(y/n):" al
			if [ $al != 'y' ] ; then
				echo "do not package, exit"
				exit 0
			fi
		fi
	else
		echo "******** package *******"
		mvn clean package -Dmaven.test.skip=true -Psit -f pom.xml
	fi
elif [[ $env == 'sit2' ]] ; then
    read -p "请输入分支名称:" branch
    git checkout $branch
	_branch=$branch
	echo "******** checkout *******"
	_pull=`git pull 2>&1`
	echo "$_pull"
	if [[ $_pull == *$up_to_date* ]] ; then
		read -p "当前分支代码已经是最新，是否重新打包(y/n):" pk
		if [ $pk == 'y' ] ; then
			echo "******** package *******"
			mvn clean package -Dmaven.test.skip=true -Psit2 -f pom.xml
		else
			read -p "是否使用已有war包继续上传(y/n):" al
			if [ $al != 'y' ] ; then
				echo "do not package, exit"
				exit 0
			fi
		fi
	else
		echo "******** package *******"
		mvn clean package -Dmaven.test.skip=true -Psit2 -f pom.xml
	fi
elif [[ $env == 'uat' ]] ; then
	read -p "请输入分支名称:" branch
	git checkout $branch
	_branch=$branch
	echo "******** checkout *******"
	_pull=`git pull 2>&1`
	echo "$_pull"
	if [[ $_pull == *$up_to_date* ]] ; then
		read -p "当前分支代码已经是最新，是否重新打包(y/n):" pk
		if [ $pk == 'y' ] ; then
			echo "******** package *******"
			mvn clean package -Dmaven.test.skip=true -Pprod -f pom.xml
		else
			read -p "是否使用已有war包继续上传(y/n):" al
			if [ $al != 'y' ] ; then
				echo "do not package, exit"
				exit 0
			fi
		fi
	else
		echo "******** package *******"
		mvn clean package -Dmaven.test.skip=true -Pprod -f pom.xml
	fi
elif [[ $env == 'prod' ]] ; then
  read -p "请输入分支名称:" branch
	_branch=$branch
else
	echo "不支持的环境参数"
	exit 0
fi

_list=`git config --list 2>&1`
_name="unknown"
for i in $_list;
	do
		if [[ $i == *$u_name* ]] ; then
			_name=${i#*user.name=}
		fi
done

if [[ $env == 'sit1' || $env == 'sit2' || $env == 'uat' ]]; then
    if [ $system == 'cms' ] ; then
        cd mall-cms/target/
    elif [[ $system == 'oms' ]] ; then
        cd mall-web/target/
    elif [[ $system == 'mall' ]] ; then
        cd mall-front/target/
    fi

    if [ ! -f ./$war_name ]; then
      echo "$war_name not exist"
      exit 0
    fi
fi

echo "********** "STEP 3: 代码上传" *******"
if [ $env == 'sit1' ] ; then
	deploy $war_name $_user $ip_sit1 $tomcat_path_sit1 $_name $_branch $system
elif [ $env == 'sit2' ] ; then
    deploy $war_name $_user $ip_sit2 $tomcat_path_sit2 $_name $_branch $system
elif [ $env == 'uat' ] ; then
	deploy $war_name $_user $ip_uat $tomcat_path_uat $_name $_branch $system
elif [ $env == 'prod' ] ; then
  #1.检查当前输入的分支名称和服务器上上次部署的分支名称是否一致
  #2.如果一致则将webapps下的war上传到FTP服务器目录
  read -p "请输入生产ftp路径（格式：20220107/ALMxxx-XXX/微信小程序系统/上线包/后端包）:" remote
    if [[ -z $remote ]]; then
	    echo "ftp路径不能为空"
	    exit 0
	fi
	_info="["`date "+%Y-%m-%d %H:%M:%S"`"] branch: "$_branch", deployed by: "$_name
	#echo "info:$_info"
	ssh $_user@$ip_uat "echo $_info >> $tomcat_path_uat/prod_deploy.info"
    ssh $_user@$ip_uat "sh $tomcat_path_uat/bin/upload_war.sh $remote $_branch"
fi