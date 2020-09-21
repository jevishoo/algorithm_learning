package code.StackQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/21 14:37
 * @Created by Jevis_Hoo
 * @Description Mini Parser
 * <p>
 * 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
 * <p>
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * <p>
 * >>> 字符串非空
 * >>> 字符串不包含空格
 * >>> 字符串只包含数字0-9、[、-、,、]
 */
public class MiniParser {
    char[] chars;
    int cur = 0;

    public NestedInteger deserialize(String s) {
        chars = s.toCharArray();
        //本身不是一个集合而是一个整数的情况
        if (chars[0] != '[') return new NestedInteger(Integer.valueOf(s));
        //调用递归函数返回根集合
        return getNest();
    }

    private NestedInteger getNestInteger() {
        NestedInteger nest = new NestedInteger();
        int num = 0;//num用于缓存用逗号分割的整数类型的值
        int sign = 1;//当前记录的整数的符号，1代表整数，-1代表负数
        StringBuilder s = new StringBuilder();
        while (cur != chars.length - 1) {
            cur++;
            if (chars[cur] == ',') continue;
            if (chars[cur] == '[') nest.add(getNestInteger());//遇到[递归获取子集合
            else if (chars[cur] == ']') return nest;
            else if (chars[cur] == '-') sign = -1;
            else {//是数字的情况
                s.append(chars[cur]);
                num = 10 * num + sign * (chars[cur] - '0');
                //如果下一个字符是,或者]说明当前数字已经记录完了，需要加入集合中
                if (chars[cur + 1] == ',' || chars[cur + 1] == ']') {
                    nest.add(new NestedInteger(Integer.parseInt(String.valueOf(s))));
                    num = 0;
                    sign = 1;
                }
            }
        }
        return null;
    }


    private NestedInteger getNest() {
        NestedInteger nest = new NestedInteger();
        StringBuilder s = new StringBuilder();
        while (cur != chars.length - 1) {
            cur++;
            if (chars[cur] == ',') continue;
            if (chars[cur] == '[') nest.add(getNest());//遇到[递归获取子集合
            else if (chars[cur] == ']') return nest;
            else {//是数字的情况
                s.append(chars[cur]);
                //如果下一个字符是,或者]说明当前数字已经记录完了，需要加入集合中
                if (chars[cur + 1] == ',' || chars[cur + 1] == ']') {
                    nest.add(new NestedInteger(Integer.parseInt(String.valueOf(s))));
                    s = new StringBuilder();
                }
            }
        }
        return null;
    }


    public static String printNi(NestedInteger nestedInteger, StringBuilder sb) {
        if (nestedInteger.list != null) {
            sb.append("[");
            for (NestedInteger ni : nestedInteger.list) {
                if (ni.isInteger()) {
                    sb.append(ni.integer);
                    sb.append(",");
                } else {
                    printNi(ni, sb);
                }
            }
            sb.append("]");
        } else {
            sb.append(nestedInteger.integer);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MiniParser miniParser = new MiniParser();
//        String s = "[123,[456,[789]]]";
        String s = "[-1,-2]";
        StringBuilder sb = new StringBuilder();

        NestedInteger ni = miniParser.deserialize(s);
        System.out.println(printNi(ni, sb));
    }
}


class NestedInteger {
    public ArrayList<NestedInteger> list;
    public Integer integer;

    public NestedInteger(ArrayList<NestedInteger> list) {
        this.list = list;
    }

    public void add(NestedInteger nestedInteger) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(nestedInteger);
    }

    public void setInteger(int num) {
        this.integer = num;
    }

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    public NestedInteger() {
        this.list = new ArrayList<>();
    }

    public boolean isInteger() {
        return integer != null;
    }

    public Integer getInteger() {
        return integer;
    }

    public List<NestedInteger> getList() {
        return list;
    }
}