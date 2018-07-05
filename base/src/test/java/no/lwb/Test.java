package no.lwb;


import java.nio.ByteBuffer;

/**
 *  -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics
 *  -XX:-TieredCompilation -XX:+UseParallelGC -XX:+UseSerialGC
 *  reserved 保留空间
 *  committed 实际可用空间
 *  ManagementFactory sigar
 */
public class Test {

    public static void main(String[] args) {
        // Direct buffer memory
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*1024*128);
        System.out.println("hello world");
    }
}
