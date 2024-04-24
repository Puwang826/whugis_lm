package com.whu.dao;

import com.whu.pojo.Building;

import java.util.List;

public interface BuildingDao {
    public List<Building> queryBuildingByName(String name);
    public Building queryBuildingByGid(int id);
    public Object countBuildingsByType(Object type);
    public List<Object> getallType();
    public Building getHospital(Double[] coordinates);
    public Building getCanteen(Double[] coordinates);
    public Building getLibrary(Double[] coordinates);

}

