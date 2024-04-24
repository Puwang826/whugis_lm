package com.whu.web;


import com.whu.service.BuildingService;
import com.whu.service.impl.BuildingServiceImpl;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class GetfacilityServlet extends HttpServlet {
    private BuildingService buildingService = new BuildingServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        // 提取经纬度
        double Lat = Double.parseDouble(req.getParameter("Lat"));
        double Lng = Double.parseDouble(req.getParameter("Lng"));
        Double[]coordinates = new Double[2];
        // 为每个坐标赋值
        coordinates[0] = Lng;
        coordinates[1] = Lat;
        Gson gson = new Gson();
        String jsonResult = gson.toJson(buildingService.getFacility(coordinates));
        // 将查询结果放入对象中
        resp.setCharacterEncoding("UTF-8");
        // 将 JSON 响应写回到前端
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResult);
    }
}