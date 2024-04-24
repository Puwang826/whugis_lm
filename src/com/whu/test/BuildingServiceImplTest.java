package com.whu.test;

import com.whu.service.BuildingService;
import com.whu.service.impl.BuildingServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingServiceImplTest {
    BuildingService buildingService = new BuildingServiceImpl();
    @org.junit.jupiter.api.Test
    void fuzzyQuery() {
        System.out.println(buildingService.fuzzyQuery("枫园"));
    }

    @Test
    void typesCount() {
        System.out.println(buildingService.typesCount());
    }

    @Test
    void getFacility() {
        Double[] coordinates = new Double[2];
        coordinates[0] = 114.357682;
        coordinates[1] = 30.5395272;
        System.out.println(buildingService.getFacility(coordinates));
    }
}