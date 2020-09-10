package code.StackQueue;

import java.util.LinkedList;

/**
 * @Date 2020/9/10 14:26
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
    public static String getSimplifyPath(String s) {
        LinkedList<Character> list = new LinkedList<>();

        list.addLast(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != '/' && c != '.') {
                list.addLast(c);
            } else if (c == '/') {
                if (list.getLast() == '.') {
                    list.pollLast();
                    list.pollLast();
                }

                if (i != s.length() - 1 && list.getLast() != '/') {
                    list.addLast(c);
                } else if (i == s.length() - 1 && list.getLast() == '/' && list.size() != 1) {
                    list.pollLast();
                }

            } else {
                if (list.getLast() == '.') {
                    list.pollLast();
                    if (!list.isEmpty())
                        list.pollLast();
                    if (list.isEmpty()) list.addLast('/');

                    while (list.getLast() != '/') {
                        list.pollLast();
                    }

                    if (i == s.length() - 1) {
                        list.pollLast();
                    }
                } else {
                    if (list.size() != 1 && i == s.length() - 1)
                        list.addLast(c);
                    else if (s.length() > 2 && s.charAt(2)=='.')
                        list.addLast(c);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while (!list.isEmpty()) {
            res.append(String.valueOf(list.pollFirst()));
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
        String s8 = "/...";

        System.out.println(getSimplifyPath(s1)); // "/home"
        System.out.println(getSimplifyPath(s2)); // "/"
        System.out.println(getSimplifyPath(s3)); // "/home/foo"
        System.out.println(getSimplifyPath(s4)); // "/c"
        System.out.println(getSimplifyPath(s5)); // "/c"
        System.out.println(getSimplifyPath(s6)); // "/a/b/c"
        System.out.println(getSimplifyPath(s7)); // "/"
        System.out.println(getSimplifyPath(s8)); // "/..."
    }
}
