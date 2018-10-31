package com.test.github;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wujc
 * @ClassName DuplicateEncoder
 * @Description: TODO
 * @create 2018-10-11 16:18
 */
public class DuplicateEncoder {
    public static String encode(String world) {
        String lowerWorld = world.toLowerCase();
        char[] arr = lowerWorld.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < lowerWorld.length(); i++) {
            Character one = lowerWorld.charAt(i);
            if (one.toString().equals("(") || one.toString().equals(")")) {
                continue;
            }
            if (!hm.containsKey(one)) {
                hm.put(one, 1);
            }else{
                hm.put(one, hm.get(one) + 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            Character one = lowerWorld.charAt(i);
            if (null != hm.get(one)) {
                if (hm.get(one) != 1) {
                    arr[i] = ')';
                }else{
                    arr[i] = '(';
                }
            }

        }
        return new String(arr);
    }

    public static String encode1(String word) {
        char[] arr = word.toCharArray();
        boolean[] replaceArr = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (replaceArr[i]) {
                continue;
            }
            Character ch = arr[i];
            Character oppo = null;
            if (Character.isLetter(ch)) {
                oppo = Character.isLowerCase(ch) ? Character.toUpperCase(ch):Character.toLowerCase(ch);
            }
            Set set = new HashSet();
            set.add(word.indexOf(ch));
            set.add(word.lastIndexOf(ch));
            if (word.contains(String.valueOf(oppo))) {
                set.add(word.indexOf(oppo));
                set.add(word.lastIndexOf(oppo));
            }
            if (set.size() > 1) {
                for (int j = 0; j < arr.length; j++) {
                    if (!replaceArr[j]) {
                        if (arr[j] == ch || oppo != null && arr[j] == oppo) {
                            arr[j] = ')';
                            replaceArr[j] = true;
                        }
                    }
                }
            } else if (set.size() == 1) {
                for (int j = 0; j < arr.length; j++) {
                    if (!replaceArr[j]) {
                        if (arr[j] == ch) {
                            arr[j] = '(';
                            replaceArr[j] = true;
                        }
                    }
                }
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(DuplicateEncoder.encode1("<)))[)()])))>"));
    }
}
