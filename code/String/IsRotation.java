package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/8 11:26
 * @description 判断两个字符串是否互为旋转词
 * <p>
 * 如果一个字符串为 str，把字符串 str 前面任意的部分挪到后面形成的字符串叫作 str 的旋转词。
 * 比如 str="12345"，str 的旋转词有 "12345"、"23451"、"34512"、"45123"和" 51234"。
 * 给定两个字符串 a 和 b，请判断 a 和 b 是否互为旋转词。
 */
public class IsRotation {
    public static boolean isRotation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        String str = str2 + str2;

        return KMP.getIndexOf(str, str1) != -1;
    }

    public static void main(String[] args) {
        String a = "cdabas1";
        String b = "abcdsa2";

        System.out.println(isRotation(a, b));

        a = "1ab21";
        b = "ab121";
        System.out.println(isRotation(a, b));

        a = "2ab1";
        b = "ab12";
        System.out.println(isRotation(a, b));
    }
}
