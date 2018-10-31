package com.cbf4life.Composite;

import java.util.ArrayList;

/**
 * @author wujc
 * @ClassName Client
 * @Description: Client 的作用是组装这棵树，并遍历一遍
 * @create 2018-08-12 20:22
 */
public class Client {
    public static void main(String[] args) {
        /*//首先产生了一个根节点
        IRoot ceo = new Root("王大麻子", "总经理", 100000);

        //产生三个部门经理，也就是树枝节点
        IBranch developDep = new Branch("刘大爷", "研发部门经理", 1000);
        IBranch salesDep = new Branch("马二拐子", "销售部门经理", 1000);
        IBranch financeDep = new Branch("赵三驼子", "财务部经理", 1000);

        //再把三个小组长产生出来
        IBranch firstDevGroup = new Branch("杨三乜斜", "开发一组组长", 500);
        IBranch secondDevGroup = new Branch("吴大棒槌", "开发二组组长", 500);

        ILeaf a = new Leaf("a", "开发人员", 2000);
        ILeaf b = new Leaf("b", "开发人员", 2000);
        ILeaf c = new Leaf("c", "开发人员", 2000);
        ILeaf d = new Leaf("d", "开发人员", 2000);
        ILeaf e = new Leaf("e", "开发人员", 2000);
        ILeaf f = new Leaf("f", "开发人员", 2000);
        ILeaf g = new Leaf("g", "开发人员", 2000);
        ILeaf h = new Leaf("h", "开发人员", 2000);
        ILeaf i = new Leaf("i", "开发人员", 2000);
        ILeaf j = new Leaf("j", "开发人员", 2000);
        ILeaf k = new Leaf("k", "开发人员", 2000);
        ILeaf zhenglaoliu = new Leaf("郑老六", "研发部副总", 20000);

        //该产生的人都产生出来了，然后我们怎么组装这棵树
        //首先是定义总经理下有三个部门经理
        ceo.add(developDep);
        ceo.add(salesDep);
        ceo.add(financeDep);
        //总经理下还有一个秘书
        ceo.add(k);

        //定义研发部门下的结构
        developDep.add(firstDevGroup);
        developDep.add(secondDevGroup);

        //研发部经理下还有一个副总
        developDep.add(zhenglaoliu);

        //看看开发两个开发小组下有什么
        firstDevGroup.add(a);
        firstDevGroup.add(b);
        firstDevGroup.add(c);

        secondDevGroup.add(d);
        secondDevGroup.add(e);
        secondDevGroup.add(f);

        //再看销售部下的人员情况
        salesDep.add(h);
        salesDep.add(i);
        //最后一个财务
        financeDep.add(j);

        //树状机构写完毕，然后我们打印出来
        System.out.println(ceo.getInfo());

        //打印出来整个树形
        getAllSubordinateInfo(ceo.getSubordinateInfo());*/

        //首先是组装一个组织结构出来
        Branch ceo = compositeCorpTree();

        //首先把CEO的信息打印出来
        System.out.println(ceo.getInfo());

        //然后是所有员工信息
        System.out.println(getTreeInfo(ceo));


    }

    private static String getTreeInfo(Branch root) {
        ArrayList<ICorp> subordinateList = root.getSubordinateInfo();
        String info = "";
        for (ICorp s : subordinateList) {
            if (s instanceof Leaf) {
                info = info + s.getInfo() + "\n";
            }else{
                info = info + s.getInfo() + "\n" + getTreeInfo((Branch) s);
            }
        }
        return info;
    }

    private static Branch compositeCorpTree() {
        //首先产生总经理CEO
        Branch root = new Branch("王大麻子", "总经理", 100000);
        //把三个部门经理产生出来
        Branch developDep = new Branch("刘大", "研发部门经理", 100000);
        Branch salesDep = new Branch("马二拐子", "销售部门经理", 10000);
        Branch financeDep = new Branch("赵三驼子", "财务部经理", 1000);

        //再把三个小组长产生出来
        Branch firstDevGroup = new Branch("杨三乜斜", "开发一组组长", 500);
        Branch secondDevGroup = new Branch("吴大棒槌", "开发二组组长", 500);

        ICorp a = new Leaf("a", "开发人员", 2000);
        ICorp b = new Leaf("b", "开发人员", 2000);
        ICorp c = new Leaf("c", "开发人员", 2000);
        ICorp d = new Leaf("d", "开发人员", 2000);
        ICorp e = new Leaf("e", "开发人员", 2000);
        ICorp f = new Leaf("f", "开发人员", 2000);
        ICorp g = new Leaf("g", "开发人员", 2000);
        ICorp h = new Leaf("h", "开发人员", 2000);
        ICorp i = new Leaf("i", "开发人员", 2000);
        ICorp j = new Leaf("j", "开发人员", 2000);
        ICorp k = new Leaf("k", "开发人员", 2000);
        ICorp zhenglaoliu = new Leaf("郑老六", "研发部副总", 20000);

        //开始组装
        //CEO下有三个部门经理和一个秘书
        root.addSubordinate(k);
        root.addSubordinate(developDep);
        root.addSubordinate(salesDep);
        root.addSubordinate(financeDep);

        //研发部经理
        developDep.addSubordinate(zhenglaoliu);
        developDep.addSubordinate(firstDevGroup);
        developDep.addSubordinate(secondDevGroup);

        //看看开发两个开发小组有什么
        firstDevGroup.addSubordinate(a);
        firstDevGroup.addSubordinate(b);
        firstDevGroup.addSubordinate(c);
        secondDevGroup.addSubordinate(d);
        secondDevGroup.addSubordinate(e);
        secondDevGroup.addSubordinate(f);
        
        //再看销售部下的人员情况
        salesDep.addSubordinate(h);
        salesDep.addSubordinate(i);
        //最后一个财务
        financeDep.addSubordinate(j);

        return root;
    }

    /*//遍历所有的树枝节点，打印信息
    private static void getAllSubordinateInfo(ArrayList subordinateInfo) {
        int length = subordinateInfo.size();
        for (int m = 0; m < length; m++) { //定义一个ArrayList长度，不要在for循环中每次计算
            Object s = subordinateInfo.get(m);
            if (s instanceof Leaf) { //是叶子节点，也就是员工
                ILeaf employee = (ILeaf) s;
                System.out.println(((ILeaf) s).getInfo());
            }else {
                IBranch branch = (IBranch) s;
                System.out.println(branch.getInfo());

                //在递归调用
                getAllSubordinateInfo(branch.getSubordinateInfo());

            }

        }
    }*/
}
