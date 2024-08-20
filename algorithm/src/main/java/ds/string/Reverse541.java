package ds.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CaptainWang
 * @since 2024/8/20
 */
public class Reverse541 {
    public String reverseStr(String s, int k) {
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length - 1; i = i + 2 * k) {
            reverseString(ss, i, Math.min(i + k - 1, ss.length - 1));
        }
        return String.valueOf(ss);

    }

    private void reverseString(char[] s, int left, int right) {
        char tem = ' ';
        if (s != null && s.length > 1) {
            while (right >= left) {
                tem = s[left];
                s[left] = s[right];
                s[right] = tem;
                right--;
                left++;
            }
        }
    }

    public static void main(String[] args) {
        Reverse541 reverse541 = new Reverse541();
        System.out.println(reverse541.reverseStr("abcdefg",2));

        for (String s : "aaa bbb  ccc  ddd".split(" ")) {
            System.out.println(s);
        }


    }
}
