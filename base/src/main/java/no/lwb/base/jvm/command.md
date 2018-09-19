1. 查看jvm运行多久
ps -p pid -o etime
2. 
java -XX:+PrintFlagsFinal -version | grep Metaspace

可参考的健康的GC状况：

- YoungGC频率不超过2秒/次；
- CMS GC频率不超过1天/次；
- 每次YoungGC的时间不超过15ms；
- FullGC频率尽可能完全杜绝； 

-XX:+DisableExplicitGC 屏蔽system.gc()触发full gc




参考文章：

- [一次堆外内存泄露的排查过程](https://mp.weixin.qq.com/s/bkzVCjm0qeREpCnF_8xX2A])
- [由「Metaspace容量不足触发CMS GC」从而引发的思考](https://mp.weixin.qq.com/s/1VP7l9iuId_ViP1Z_vCA-w)
