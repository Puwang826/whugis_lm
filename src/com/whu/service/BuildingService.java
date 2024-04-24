package com.whu.service;

import com.whu.pojo.Building;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    public List<Building> fuzzyQuery(String name);
    public Map<String, Object> typesCount();

    public Map<String, Building> getFacility(Double[] coordinates);
}
