package jehon;

import java.util.HashMap;
import java.util.HashSet;

public class CollectionTest {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        System.out.println(hashMap.put(null, null));
        System.out.println(hashMap.put(null, null));
        System.out.println(hashMap.put(null, null));
        System.out.println(hashMap.get(null));
        System.out.println(hashMap.size());

        HashSet hashSet = new HashSet<String>();
        System.out.println(hashSet.add(null));
        System.out.println(hashSet.add(null));
        System.out.println(hashSet.add("1"));
        System.out.println(hashSet.add("1"));

//        TreeMap<String, String> treeMap = new TreeMap<>();
//        System.out.println(treeMap.put(null, null));
//        treeMap.put(null, null);

//        TreeSet<String> treeSet = new TreeSet<>();
//        System.out.println(treeSet.add(null));
    }
}
