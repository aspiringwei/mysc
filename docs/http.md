## 图解HTTP

#### http 协议 无状态协议

- http协议自身不对请求和响应之间的通信状态进行保存
- 状态保存功能：cookie 管理 session
```
GET /image/ HTTP/1.1
Host: www.baidu.com
Cookie: sid=123456
```
- REST Representational State Transfer 表征状态转移

- http方法
	1. GET: 获取资源 GET /index HTTP/1.1
	2. POST: 传输实体主体
	3. PUT: 传输文件 http/1.1的put方法自身不带校验机制
	4. HEAD: 获取报文首部
	5. DELETE: 删除文件 同3
	6. OPTIONS: 询问支持的方法 OPTIONS * HTTP/1.1
	7. TRACE: 追踪路径 容易引发XST(cross-site tracing 跨站攻击)
	8. CONNECT: 要求用隧道协议连接代理
- 持久链接 默认
	- keep-alive 只要任意一端没有明确提出断开连接，则保持tcp连接状态
	- 管线化 并行发送多个请求

- 压缩传输的内容编码:
	- Accept-Encoding:gzip, deflate, br
- 分块传输编码 Chunked Transfer Coding

- 多部分对象集合
	- multipart/form-data web表单文件上传时使用
	- multipart/byteranges 状态码206 响应报文包含了多个范围的内容时使用

- http状态码
	1. 2XX 成功 200 ok 206 partial content 204 no content
	2. 3XX 重定向
	3. 4XX 客户端错误
		- 400 Bad Request 请求报文存在语法错误
		- 401 Unauthorized 请求需要通过认证信息
		- 403 Forbidden 请求被服务器拒绝
		- 404 Not Found 服务器没有找到请求资源
	4. 5XX 服务器错误
		- 500 Internal Server Error 服务器在请求时发生错误 可能应用存在bug或临时故障
		- 503 Service Unavailable 服务器暂时处理超负载或停机维护

- web服务器
	1. host 域名配置
	2. 通信数据转发程序
		- 代理 利用缓存减少网络带宽流量，访问控制，获取访问日志
		- 网关 提高通信安全性，能使通信线路上的服务器提供非http协议服务
		- 隧道 确保客户端能与服务器进行安全的通信 如利用SSL加密手段

- http首部 阅读文档**

- https http+加密+认证+完整性保护
	- 共享秘钥加密
	- 公开秘钥加密 public-key 两把秘钥加密 一对非对称的秘钥
		- 使用公开秘钥进行加密，使用私有秘钥进行解密。利用密文和公开秘钥恢复原文的异常困难的
	- 混合加密
		- 使用公开秘钥进行加密的方式交换共享秘钥加密中要使用的秘钥，然后利用共享秘钥加密的方式进行通信 公开秘钥使用由CA或其相关机关颁发的数字证书
	- EV SSL证书的作用 1. 证明服务器是否规范 2. 验证运营的企业是否真实存在 客户端证书同理
- 认证**
	- basic认证(基本认证) 用户名密码以base64方式编码(并非加密处理)，另外浏览器无法实现认证注销。安全性低使用不够便捷灵活
	- digest认证(摘要认证)
	- SSL客户端认证 导入/费用
	- formBase认证(基于表单认证) 最常用
		- salt(passwd)给密码加盐 salt就是服务器随机生成的(足够长)

- ajax asynchronous javascript and xml 异步JavaScript和XML
	- 核心API XMLHttpRequest
- comet
- websocket

[note]
第六章**  

#### spdy协议***

- 多路复用流
- 赋予请求优先级
- 压缩HTTP首部
- 推送功能
- 服务器提示功能

#### websocket协议*** 全双工
- 推送功能
- 减少通信量
- 握手·请求 upgrade: websocket
- 握手·响应
- websocket api


#### http/2.0*** p190
- spdy
- http speed + mobility
- network-friendly http upgrade

