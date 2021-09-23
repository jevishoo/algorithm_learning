package code.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 2020/12/16 15:04
 * @Created by Jevis_Hoo
 * @Description 派对的最大快乐值
 * 整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。树的头节点是公司唯一的老板，除老板外，每个员工都有唯一的直接上级。
 * 叶节点是没有任何下属的基层员工(subordinates 列表为空)，除基层员工外，每个员工都有一 个或多个直接下级。
 * 这个公司现在要办 party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下规则。
 * <p>
 * >>> 如果某个员工来了，那么这个员工的所有直接下级都不能来。
 * >>> 派对的整体快乐值是所有到场员工快乐值的累加。
 * >>> 目标是让派对的整体快乐值尽量大。
 */
public class MaxHappy {
    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级

        public Employee() {
        }

        public Employee(int happy) {
            this.happy = happy;
        }
    }

    static class ReturnType {
        int yesHeadMax;
        int noHeadMax;

        public ReturnType(int yesHeadMax, int noHeadMax) {
            this.yesHeadMax = yesHeadMax;
            this.noHeadMax = noHeadMax;
        }
    }

    public static int getMaxHappy(Employee employee) {
        ReturnType head = process(employee);
        return Math.max(head.noHeadMax, head.yesHeadMax);
    }

    public static ReturnType process(Employee employee) {
//        System.out.println(employee.happy);
        if (employee.subordinates == null) {
            return new ReturnType(employee.happy, 0);
        } else {
            int yesE = employee.happy;
            int noE = 0;

            for (Employee e : employee.subordinates) {
                ReturnType sub = process(e);
                yesE += sub.noHeadMax;
                noE += Math.max(sub.yesHeadMax, sub.noHeadMax);
            }

            return new ReturnType(yesE, noE);
        }
    }

    public static void main(String[] args) {
        Employee boss = new Employee();
        boss.happy = 100;

        List<Employee> sub = new ArrayList<>();
        Employee sub_a = new Employee(1);
        Employee sub_b = new Employee(2);
        Employee sub_c = new Employee(3);
        sub.add(sub_a);
        sub.add(sub_b);
        sub.add(sub_c);
        boss.subordinates = sub;

        List<Employee> sub_sub_a = new ArrayList<>();
        Employee sub_a_a = new Employee(1);
        Employee sub_a_b = new Employee(2);
        Employee sub_a_c = new Employee(3);
        sub_sub_a.add(sub_a_a);
        sub_sub_a.add(sub_a_b);
        sub_sub_a.add(sub_a_c);
        sub_a.subordinates = sub_sub_a;

        List<Employee> sub_sub_b = new ArrayList<>();
        Employee sub_b_a = new Employee(1);
        Employee sub_b_b = new Employee(2);
        Employee sub_b_c = new Employee(3);
        sub_sub_b.add(sub_b_a);
        sub_sub_b.add(sub_b_b);
        sub_sub_b.add(sub_b_c);
        sub_b.subordinates = sub_sub_b;

        List<Employee> sub_sub_c = new ArrayList<>();
        Employee sub_c_a = new Employee(1);
        Employee sub_c_b = new Employee(2);
        Employee sub_c_c = new Employee(3);
        sub_sub_c.add(sub_c_a);
        sub_sub_c.add(sub_c_b);
        sub_sub_c.add(sub_c_c);
        sub_c.subordinates = sub_sub_c;


        for (Employee employee : sub) {
            System.out.println(employee.happy);
        }

        System.out.println(getMaxHappy(boss));
    }
}
