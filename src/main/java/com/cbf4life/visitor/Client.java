package com.cbf4life.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-10-20 16:15
 */
public class Client {
    public static void main(String[] args) {
        IVisitor visitor = new Visitor();
        for (Employee emp : mockEmployee()) {
            emp.accept(visitor);
        }
        System.out.println("本公司的月工资总额是："+visitor.getTotalSalary());
    }

    private static List<Employee> mockEmployee() {
        List<Employee> empList = new ArrayList<Employee>();
        //产生张三这个员工
        CommonEmployee zhangsan = new CommonEmployee();
        zhangsan.setJob("编写java程序，绝对的蓝领、苦工加搬运工");
        zhangsan.setName("张三");
        zhangsan.setSalary(1800);
        zhangsan.setSex(Employee.MALE);
        empList.add(zhangsan);

        //产生李四这个员工
        CommonEmployee lisi = new CommonEmployee();
        lisi.setJob("页面美工，审美素质太不流行了");
        lisi.setName("李四");
        lisi.setSalary(1900);
        lisi.setSex(Employee.FEMALE);
        empList.add(lisi);

        //再产生一个经理
        Manager wangwu = new Manager();
        wangwu.setName("王五");
        wangwu.setPerformance("基本上是负值，但是我会拍马屁啊");
        wangwu.setSalary(18750);
        wangwu.setSex(Employee.MALE);
        empList.add(wangwu);

        return empList;
    }
}
