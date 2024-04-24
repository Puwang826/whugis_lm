package com.whu.dao.impl;

import com.whu.dao.BuildingDao;
import com.whu.dao.PathDao;
import org.postgresql.util.PGobject;

public class PathDaoImpl extends BaseDao implements PathDao {
    @Override
    public String getpath(Double[][] coordinates) {
        String sql = "SELECT json_build_object(\n" +
                "    'type', 'FeatureCollection',\n" +
                "    'features', array_to_json(array_agg(feature_row.feature))\n" +
                ") AS geojson\n" +
                "FROM (\n" +
                "    SELECT json_build_object(\n" +
                "        'type', 'Feature',\n" +
                "        'geometry', ST_AsGeoJSON(ST_MakeLine(route.geom))::json,\n" +
                "        'properties', '{}'::json\n" +
                "    ) AS feature\n" +
                "    FROM (\n" +
                "        SELECT geom FROM wrk_fromAtoB('road', CAST(? AS numeric), CAST(? AS numeric), CAST(? AS numeric), CAST(? AS numeric)) ORDER BY seq\n" +
                "    ) AS route\n" +
                ") AS feature_row;\n";
        Object result = queryForSingleValue(sql, coordinates[0][0],coordinates[0][1],coordinates[1][0],coordinates[1][1]);
        if (result instanceof PGobject) {
            PGobject pgObject = (PGobject) result;
            // 获取数据库返回的 JSON 字符串
            String geoJson = pgObject.getValue();
            return geoJson;
        }
        return null; // 或者根据需要返回合适的值

    }
}
