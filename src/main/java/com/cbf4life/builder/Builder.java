package com.cbf4life.builder;

/**
 * @author xingkong
 * @date 2022/9/25 11:28
 */
public interface Builder {
    public void buildBasement();

    public void buildWall();

    public void buildRoof();

    public Building getBuilding();
}
