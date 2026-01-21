自动部署脚本运行前准备：

1.  安装maven并配置环境变量, 执行mvn -v验证是否安装, 修改maven仓库地址为<mirror><id>nexus-aliyun</id><mirrorOf>central</mirrorOf><name>Nexus aliyun</name><url>http://maven.aliyun.com/nexus/content/groups/public</url></mirror>
2.  安装git, 鼠标右键出现git bash here则说明已安装
3.  打开git bash, ssh-keygen -t rsa生成密钥, 如果提示已存在可跳过
4.  商户:
    	1.  配置sit环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub cmsapp@10.1.94.226 (L1313l@#@L)
    	2.  配置uat环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub cmsapp@10.1.94.231 (L1313l@#@L)

    银行:
    	1.  配置sit环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub omsapp@10.1.94.225 (L1313l@#@L)
    	2.  配置uat环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub omsapp@10.1.94.230 (L1313l@#@L)

    C端:
    	1.  配置sit环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub mallapp@10.1.94.227 (L1313l@#@L)
    	2.  配置uat环境免密码登录 ssh-copy-id -i ~/.ssh/id_rsa.pub mallapp@10.1.94.232 (L1313l@#@L)

6.  git clone http://10.1.89.44:8080/sh_xyk_center/mall-project.git
7.  sh autoDeploy.sh
