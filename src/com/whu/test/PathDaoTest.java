package com.whu.test;

import com.whu.dao.PathDao;
import com.whu.dao.impl.PathDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathDaoTest {
    PathDao pathDao = new PathDaoImpl();
    @Test
    void getpath() {
        Double[][] coordinates = new Double[2][2];
        // 为每个坐标赋值
        coordinates[0][0] = 114.357682;
        coordinates[0][1] = 30.5395272;
        coordinates[1][0] = 114.3595876;
        coordinates[1][1] = 30.5344746;
        System.out.println(pathDao.getpath(coordinates));
    }
}