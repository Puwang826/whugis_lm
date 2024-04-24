package com.whu.dao.impl;

import com.whu.dao.BuildingDao;
import com.whu.pojo.Building;
import org.postgresql.util.PGobject;

import java.util.List;

public class BuildingDaoImpl extends BaseDao implements BuildingDao {

    @Override
    public List<Building> queryBuildingByName(String name) {
        String sql = "select gid, name, ST_AsGeoJSON(geom) as json, type from building where name LIKE ?";
        String likeParameter = "%" + name + "%";
        return queryForList(Building.class, sql, likeParameter);
    }

    @Override
    public Building queryBuildingByGid(int id) {
        String sql = "select gid, name, ST_AsGeoJSON(geom) as json, type from building where gid = ?";
        return queryForOne(Building.class, sql, id);
    }

    @Override
    public Object countBuildingsByType(Object type) {
        String sql = "SELECT COUNT(*) FROM building WHERE type = ?";
        return queryForSingleValue(sql, type);
    }

    @Override
    public List<Object> getallType() {
        String sql = "SELECT DISTINCT type FROM building";
        return queryForListValue(sql,null);
    }

    @Override
    public Building getHospital(Double[] coordinates) {
        String sql ="SELECT gid, name, ST_AsGeoJSON(geom) as json, type\n" +
                "FROM building\n" +
                "WHERE type = 'hospital'\n" +
                "ORDER BY geom <-> ST_SetSRID(ST_MakePoint(?, ?), ST_SRID(geom))\n" +
                "LIMIT 1;";
        return queryForOne(Building.class, sql, coordinates[0],coordinates[1]);
    }

    @Override
    public Building getCanteen(Double[] coordinates) {
        String sql ="SELECT gid, name, ST_AsGeoJSON(geom) as json, type\n" +
                "FROM building\n" +
                "WHERE type = 'canteen'\n" +
                "ORDER BY geom <-> ST_SetSRID(ST_MakePoint(?, ?), ST_SRID(geom))\n" +
                "LIMIT 1;";
        return queryForOne(Building.class, sql, coordinates[0],coordinates[1]);
    }

    @Override
    public Building getLibrary(Double[] coordinates) {
        String sql ="SELECT gid, name, ST_AsGeoJSON(geom) as json, type\n" +
                "FROM building\n" +
                "WHERE type = 'library'\n" +
                "ORDER BY geom <-> ST_SetSRID(ST_MakePoint(?, ?), ST_SRID(geom))\n" +
                "LIMIT 1;";
        return queryForOne(Building.class, sql, coordinates[0],coordinates[1]);
    }

}
