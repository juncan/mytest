package com;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xingkong
 * @Title:
 * @Description:
 * @date 2023/6/6 16:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AClothClassVo {
    /**
     * id
     */
    private String id;

    /**
     * 站名
     */
    private String station;

    private String parentId;

    /**
     * 排序
     */
    private Integer sort;



    public static void main(String[] args) {
        // 构建数据
        List<AClothClassVo> lists = CollUtil.newArrayList();
        lists.add(new AClothClassVo("-1", "1号线", "0", 2));
        lists.add(new AClothClassVo("-2", "2号线", "0", 9));
        lists.add(new AClothClassVo("5761", "镇海路", "-1", 1));
        lists.add(new AClothClassVo("5762", "中山公园", "-1", 0));
        lists.add(new AClothClassVo("5763", "将军祠", "-1", 2));


        lists.add(new AClothClassVo("5785", "天竺山", "-2", 5));
        lists.add(new AClothClassVo("5786", "东孚", "-2", 1));


        TreeNodeConfig config = new TreeNodeConfig();

        // 树形数据中id的属性名,写成id1方便区分,实际上写AClothClassVo实体类的id属性名
        config.setIdKey("id");
        config.setParentIdKey("pid");
        config.setChildrenKey("childrenList");
        //排序字段
        config.setWeightKey("sort");

        config.setDeep(2);


        /**
         * 入参
         * tree:  最终要返回的数据
         * node:  lists数据
         *
         * 返回
         * Tree<String>
         *   Tree: 转换的实体 为数据源里的对象类型
         *   String: ID类型
         *
         */

        List<Tree<String>> list = TreeUtil.build(lists, "0", config, (node, tree) -> {
            //tree.setId(node.getId());
            //tree.setName(node.getStation());
            //tree.setParentId(node.getParentId());
            tree.putExtra("id", node.getId());
            tree.putExtra("name",node.getStation());
            tree.putExtra("pid",node.getParentId());

            // 额外的值
            tree.putExtra("sort", node.getSort());

        });

        System.out.println(JSON.toJSONString(list));
    }
}
