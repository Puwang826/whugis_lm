package com.whu.service.impl;

import com.whu.dao.BuildingDao;
import com.whu.dao.impl.BuildingDaoImpl;
import com.whu.pojo.Building;
import com.whu.service.BuildingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingServiceImpl implements BuildingService {
    private BuildingDao buildingDao = new BuildingDaoImpl();

    @Override
    public List<Building> fuzzyQuery(String name) {
        return buildingDao.queryBuildingByName(name);
    }
    public Map<String, Object> typesCount() {
        Map<String, Object> typeCountMap = new HashMap<>();
        List<Object> allTypes = buildingDao.getallType();
        for (Object type : allTypes) {
            if (type != null) {
                Object count = buildingDao.countBuildingsByType(type.toString());
                typeCountMap.put(type.toString(), count);
            }
        }
        return typeCountMap;
    }

    @Override
    public Map<String, Building> getFacility(Double[] coordinates) {
        Map<String, Building> FacilityMap = new HashMap<>();
        Building Hospital = buildingDao.getHospital(coordinates);
        Building Canteen = buildingDao.getCanteen(coordinates);
        Building Library = buildingDao.getLibrary(coordinates);
        FacilityMap.put("Hospital", Hospital);
        FacilityMap.put("Canteen", Canteen);
        FacilityMap.put("Library", Library);
        return FacilityMap;
    }

}
