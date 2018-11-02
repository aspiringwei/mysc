
##### maven 安装
```
$ ## 下载
$ wget http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
$ ## 解压
$ tar xzvf apache-maven-3.5.0-bin.tar.gz
$ ## 配置环境变量
$ vi ~/.bash_profile
    M2_HOME=maven目录
    $PATH:$M2_HOME/bin
$ source ~/.bash_profile
```

##### skip test
1. gradle build -x test
2. mvn install-Dmaven.test.skip=true