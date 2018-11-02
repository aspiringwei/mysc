### 两个名词
**BPM** Business Process Management 业务流程管理
**BPMN** Business Process Modeling Notation 业务流程建模符号

### 例子
bpmn-demo.xml
```
<?xml version=1"1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:activiti="http://activiti.org/bpmn" 
	xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" 
	xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" 
	xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" 
	typeLanguage="http://www.w3.org/2001/XMLSchema" 
	expressionLanguage="http://www.w3.org/1999/XPath" 
	targetNamespace="http://www.activiti.org/processdef">
	
	<process id="onboarding" name="Onboarding" isExecutable="true">
	<!-- 启动事件 -->
	<startEvent id="startOnboarding" name="Start" activiti:initiator="initiator">
		<timerEventDefinition>
			<timeDate>2011-03-11T12:13:14</timeDate>
  		</timerEventDefinition>
	</startEvent>
	<!-- 结束事件 -->
	<endEvent id="endOnboarding" name="End"></endEvent>
	<!-- 顺序流 活动和网关间的连线 -->
	<sequenceFlow id="sid-1337EA98-7364-4198-B5D9-30F5341D6918" sourceRef="startOnboarding" targetRef="enterOnboardingData">
		<!-- Condition条件 -->
		<!-- 在运行时,只有当condition条件结果为true,顺序流才会被执行 条件可以放在 ${}中-->
		<conditionExpression xsi:type="tFormalExpression"><![CDATA[${yearsOfExperience > 3}]]></conditionExpression>
	</sequenceFlow>
	<!-- 唯一网关 XOR 网关 -->
	<exclusiveGateway id="decision" name="Years of Experience" default="automatedIntroPath"></exclusiveGateway>
	<!-- 用户任务 -->
	<userTask id="enterOnboardingData" name="Enter Data" activiti:assignee="${initiator}" activiti:candidateGroups="managers">
            <extensionElements>
                <activiti:formProperty id="fullName" name="Full Name" type="string"></activiti:formProperty>
                <activiti:formProperty id="yearsOfExperience" name="Years of Experience" type="long" required="true"></activiti:formProperty>
            </extensionElements>
    </userTask>
    <!-- 服务任务 -->
    <serviceTask id="automatedIntro" name="Generic and Automated Data Entry" activiti:class="com.example.AutomatedDataDelegate"></serviceTask>
    <!-- 脚本任务 -->
	<scriptTask id="automatedIntro" name="Generic and Automated Data Entry" 
		scriptFormat="javascript" activiti:autoStoreVariables="false">
            <script><![CDATA[var dateAsString = new Date().toString();
		execution.setVariable("autoWelcomeTime", dateAsString);]]></script>
    </scriptTask>
```

### 相关概念介绍
##### Events 事件
1. Catching event：当流程执行到该事件时,等待触发器执行的事件 eg:开始事件   
1. Throwing event：当流程执行到该事件时,触发器立即执行的事件 eg:结束事件
##### Sequence Flow 顺序流
```xml
<sequenceFlow id="flow1" sourceRef="theStart" targetRef="theTask" />
```
##### Gateways 网关 控制流程流向
1. XOR 网关：排他网关 exclusiveGateway
1. 并行网关：parallelGateway 
	并行网关拥有多个进入顺序流和一个外出顺序流 叫做'并行归并'或 AND-join
	并行网关拥有一个进入顺序流的和多于一个的外出顺序流 叫做'并行切分或 'AND-split'
1. 包含网关：
##### Tasks 常用任务
1. [User Task](https://www.activiti.org/userguide/#bpmnUserTask) ***** 用户，组，角色 candidateGroups candidateUsers Assignee
1. [Script Task](https://www.activiti.org/userguide/#bpmnScriptTask)
1. [Java Service Task](https://www.activiti.org/userguide/#bpmnJavaServiceTask) 
1. [Web Service Task](https://www.activiti.org/userguide/#bpmnWebserviceTask)
1. [Business Rule Task](https://www.activiti.org/userguide/#bpmnBusinessRuleTask)
1. [Email Task](https://www.activiti.org/userguide/#bpmnEmailTask)
1. Execution listener
##### 

### 入门实例
http://gitlab-console.asiainfo.com/xmc/ActivitiDeveloperQuickStart.git
### 核心组件

ProcessEngineConfigurationImpl
--------------
repositoryService
runtimeService
historyService
identityService
taskService
formService
managementService
dynamicBpmnService
--------------

流程设计(xml or 插件)process diagram
启动一个流程实例starting a process instance
任务列表task lists
签收任务claiming the task
完成任务completing the task
结束流程


### 参考资料
[用户指南](https://www.activiti.org/userguide)
[jbpm](http://www.mossle.com/docs/jbpm4devguide/html/bpmn2.html)
[bpmn20](https://www.activiti.org/userguide/#bpmn20)
[Activiti与BPMN2.0规范](http://www.jianshu.com/p/e25fdc595f86)
[Activiti事件分类简介](http://www.bug315.com/article/163.htm)


http://blog.csdn.net/u012373815/article/details/52092218