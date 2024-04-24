package com.whu.test;

import com.whu.dao.BuildingDao;
import com.whu.dao.impl.BuildingDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingDaoImplTest {
    BuildingDao buildingDao = new BuildingDaoImpl();
    @Test
    void countBuildingsByType() {
        System.out.println(buildingDao.getallType());
    }

    @Test
    void getallType() {
    }
}