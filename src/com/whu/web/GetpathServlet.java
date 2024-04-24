package com.whu.web;


import com.whu.service.PathService;
import com.whu.service.impl.PathServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GetpathServlet extends HttpServlet {
    private PathService pathService = new PathServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        // 提取经纬度
        double startLat = Double.parseDouble(req.getParameter("startLat"));
        double startLng = Double.parseDouble(req.getParameter("startLng"));
        double endLat = Double.parseDouble(req.getParameter("endLat"));
        double endLng = Double.parseDouble(req.getParameter("endLng"));
        Double[][] coordinates = new Double[2][2];
        // 为每个坐标赋值
        coordinates[0][0] = startLng;
        coordinates[0][1] = startLat;
        coordinates[1][0] = endLng;
        coordinates[1][1] = endLat;
        // 将查询结果放入对象中
        resp.setCharacterEncoding("UTF-8");
        // 将 JSON 响应写回到前端
        resp.setContentType("application/json");
        resp.getWriter().write(pathService.anypath(coordinates));
    }
}