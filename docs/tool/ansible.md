```
#!/bin/bash
cd build/
#获取当前服务的工程名
DD=`echo   ${JOB_NAME} | awk -F  '-' '{print $1}'`
len=`expr length ${JOB_NAME}`
serverName=`echo   ${JOB_NAME} | cut  -c4-${len}`
#打tar包
rm -rf ${serverName}.tar
tar cvf ${serverName}.tar config/ libs/
#ansible部署
cd /data/jenkis/yaml
hosType=`cat  ${DD}port | grep $serverName | awk -F ',' '{print $1}'`
prot=`cat  ${DD}port | grep $serverName | awk -F ',' '{print $3}'`
Sport=`cat  ${DD}port | grep $serverName | awk -F ',' '{print $4}'`
Uport=`cat  ${DD}port | grep $serverName | awk -F ',' '{print $5}'`
label=`cat  ${DD}port | grep $serverName | awk -F ',' '{print $6}'`

echo "省份编码：$DD"
echo "$serverName $prot $Sport $Uport $Uport"
#ansible-playbook -i ${DD}${hosType}.cfg ${DD}${hosType}.yaml --extra-vars "serverName="${serverName}" prot="${prot}" Sport="${Sport}" Uport="${Uport}" label="${label}"  workspace="${WORKSPACE}" " -vvv
cd /data/jenkis/yaml
ansible-playbook -i ${DD}${hosType}.cfg ${DD}${hosType}.yaml -e serverName=${serverName} -e  prot=${prot} -e Sport=${Sport} -e Uport=${Uport} -e label=${label}  -e workspace=${WORKSPACE}


```