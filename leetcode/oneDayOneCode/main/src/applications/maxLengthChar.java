package applications;

import java.util.HashMap;

public class maxLengthChar {
    public static void main(String[] args) {
        String str = "jiangjinjain";
        System.out.println(new maxLengthChar().maxLengthBetweenEqualCharacters(str));
    }
    public int maxLengthBetweenEqualCharacters(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int max = -1;
        for (int i=0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                max = Math.max(max, i - map.get(chars[i]) - 1);
            } else {
                map.put(chars[i], i);
            }
        }
        return max;
    }
}
