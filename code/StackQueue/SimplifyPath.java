package code.StackQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @since 2020/9/10 14:26
 * @Created by Jevis_Hoo
 * @Description SimplifyPath
 * <p>
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words,
 * convert it to the canonical path.
 * <p>
 * Note that the returned canonical path must always begin with a slash /,
 * and there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing /.
 * Also, the canonical path must be the shortest string representing the absolute path.
 */
public class SimplifyPath {
    public static String simplifyPath(String s) {
        String[] split = s.split("/");
        List<String> solution = new LinkedList<>();
        int skip = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            String value = split[i];
            if (value.equals("..")) skip++;
            else if (!value.equals(".") && !value.equals(""))
                if (skip != 0) skip--;
                else solution.add(0, value);
        }
        return "/" + String.join("/", solution);
    }

    public static String getSimplifyPath(String s) {
        LinkedList<String> list = new LinkedList<>();
        String[] sequence = s.split("/");

        for (String seq : sequence) {
            if (seq.equals("..")) {
                if (!list.isEmpty()) {
                    list.removeLast();
                }
            } else if (!seq.equals("") && !seq.equals(".")) {
                list.addLast(seq);
            }
        }

        if (list.isEmpty()) {
            return "/";
        }

        StringBuilder res = new StringBuilder();
        while (!list.isEmpty()) {
            res.append('/').append(list.pollFirst());
        }

        return res.toString();
    }

    public static void main(String[] args) {
        String s1 = "/home/";
        String s2 = "/../";
        String s3 = "/home//foo/";
        String s4 = "/a/./b/../../c/";
        String s5 = "/a/../../b/../c//.//";
        String s6 = "/a//b////c/d//././/..";
        String s7 = "/.";
        String s8 = "/..";
        String s9 = "/...";
        String s10 = "/.../";

        System.out.println(getSimplifyPath(s1)); // "/home"
        System.out.println(getSimplifyPath(s2)); // "/"
        System.out.println(getSimplifyPath(s3)); // "/home/foo"
        System.out.println(getSimplifyPath(s4)); // "/c"
        System.out.println(getSimplifyPath(s5)); // "/c"
        System.out.println(getSimplifyPath(s6)); // "/a/b/c"
        System.out.println(getSimplifyPath(s7)); // "/"
        System.out.println(getSimplifyPath(s8)); // "/"
        System.out.println(getSimplifyPath(s9)); // "/..."
        System.out.println(getSimplifyPath(s10)); // "/..."
    }
}
