package com.cbf4life.visitor;

/**
 * @author wujc
 * @ClassName IVisitor
 * @Description: 访问者，我要去访问人家的数据了
 * @create 2018-10-20 16:26
 */
public interface IVisitor {
    //首先定义我可以访问普通员工
    public void visit(CommonEmployee commonEmployee);

    //其次定义，我还可以访问部门经理
    public void visit(Manager manager);

    //统计所有员工工资总和
    public int getTotalSalary();
}
