package systematic.section17_BruteForcingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: duccio
 * @Date: 21, 04, 2022
 * @Description: Given a string, print all of its permutations.
 * @Note:   Ver1. naive recursion by deleting and adding back each character.
 *          Ver2. improved by swapping each character.
 *          Ver3. no repeat by Ver2 plus pruning.
 */
public class Code03_PrintAllPermutations {

    public static void main(String[] args) {
        List<String> ans1 = permutation1("abc");
        for (String s : ans1) {
            System.out.println(s);
        }
        System.out.println("======");
        List<String> ans2 = permutation2("abc");
        for (String s : ans2) {
            System.out.println(s);
        }
        System.out.println("======");
        List<String> ans3 = permutationNoRepeat("acc");
        for (String s : ans3) {
            System.out.println(s);
        }
    }

    public static List<String> permutation1(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null) {
            return ans;
        }
        ArrayList<Character> chars = new ArrayList<>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }
        process1(chars, "", ans);
        return ans;
    }

    public static void process1(ArrayList<Character> remains, String path, List<String> ans) {
        if (remains.isEmpty()) {
            ans.add(path);
        } else {
            for (int i = 0; i < remains.size(); i++) {
                char cha = remains.get(i);
                remains.remove(i);
                process1(remains, path + cha, ans);
                remains.add(i, cha);
            }
        }
    }

    public static List<String> permutation2(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null) {
            return ans;
        }
        char[] chars = str.toCharArray();
        process2(chars, 0, ans);
        return ans;
    }

    public static void process2(char[] chars, int idx, List<String> ans) {
        if (idx == chars.length - 1) {
            ans.add(String.valueOf(chars));
        } else {
            for (int i = idx; i < chars.length; i++) {
                swap(chars, idx, i);
                process2(chars, idx + 1, ans);
                swap(chars, idx, i);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static List<String> permutationNoRepeat(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null) {
            return ans;
        }
        char[] chars = str.toCharArray();
        processNoRepeat(chars, 0, ans);
        return ans;
    }

    public static void processNoRepeat(char[] chars, int idx, List<String> ans) {
        if (idx == chars.length - 1) {
            ans.add(String.valueOf(chars));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = idx; i < chars.length; i++) {
                if (visited[chars[i]]) {
                    continue;
                }
                visited[chars[i]] = true;
                swap(chars, idx, i);
                processNoRepeat(chars, idx + 1, ans);
                swap(chars, idx, i);
            }
        }
    }
}
