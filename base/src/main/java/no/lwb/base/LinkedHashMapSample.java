package no.lwb.base;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用 LinkedHashMap 将最不常被访问的对象释放掉
 */
public class LinkedHashMapSample {

    private static final LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16, 0.75F, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            // 实现自定义删除策略，否则行为就和普遍 Map 没有区别
            return size() > 3;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            accessOrderedMap.put("Project1"+i,"Project1"+i);
        }

        accessOrderedMap.forEach((s, s2) -> {
            System.out.println(s + ":" + s2);
        });
        System.out.println("----------------------");
        accessOrderedMap.get("Project12");
        accessOrderedMap.get("Project13");
        accessOrderedMap.get("Project11");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
        System.out.println("----------------------");
        accessOrderedMap.put("Project15", "Project15");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}