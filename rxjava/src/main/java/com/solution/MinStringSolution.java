package com.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 查找最短匹配字符串
 * 1.先从头开始遍历查找到符合target字符的子字符串，记录其左右起始点left和right；
 * 2.移动left，判断其是否还满足条件，不满足移动right使其再次满足条件；
 * 3.循环步骤2，直至right到达source尾部。
 */
public class MinStringSolution {

    public static void main(String[] args){
        String subString = minSubString("abc","ac");
        subString = minSubString("adobecodebanc","abc");
        subString = minSubString("abc","aa");
    }


    private static String minSubString(String source,String target){
        if (source == null || source.equals("") || target == null || target.equals("")){
            return "";
        }

        if (source.length() < target.length()){
            return "";
        }

        Map<Character,Integer> targetMap = new HashMap<>(); //记录target中每个字符出现的次数
        Map<Character,Integer> sourceMap = new HashMap<>(); //记录source一段字符串中出现target中的字符和对应次数
        for (char c : target.toCharArray()){
            targetMap.put(c,targetMap.getOrDefault(c,0) + 1);
        }

        /**
         * 记录source一段字符串中匹配的字符数，等于targetMap.size则表示完全匹配
         */
        int validNum = 0;
        int minLen = source.length() + 1;
        String subString = "";
        int j = 0;
        for (int i = 0;i < source.length();i++){
            while (validNum < targetMap.size()){
                /**
                 * 遍历source中的字符，判断其是否被包含在target中，是则记录该字符出现的次数
                 */
                if (j >= source.length()){
                    break;
                }
                char key = source.charAt(j);
                if (targetMap.containsKey(key)){
                    sourceMap.put(key,sourceMap.getOrDefault(key,0) + 1);
                    if (sourceMap.get(key).intValue() == targetMap.get(key).intValue()){
                        validNum++;
                    }
                }
                j++;
            }

            if (validNum == targetMap.size()){
                if (j - i < minLen){
                    //更新最短匹配字符串
                    subString = source.substring(i,j);
                    minLen = j - i;
                }

                //尝试移除匹配字符串的首字符，查找最短匹配字符串
                char key = source.charAt(i);
                if (sourceMap.containsKey(key)){
                    sourceMap.put(key,sourceMap.get(key)-1);
                    if (sourceMap.get(key) < targetMap.get(key)){
                        validNum--;
                    }
                }
            }else if (validNum < targetMap.size()){
                break;
            }
        }
        String msg = "source:"+source + ",target:" + target + ",最短匹配:";
        if (subString.length() > 0){
            System.out.println(msg + subString);
        }else {
            System.out.println(msg + "无");
        }

        return subString;
    }
}
