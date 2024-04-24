package com.whu.web;

import com.google.gson.Gson;
import com.whu.service.BuildingService;
import com.whu.service.impl.BuildingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounttypeServlet extends HttpServlet {
    private BuildingService buildingService = new BuildingServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object countResult = buildingService.typesCount();
        // 使用 Gson 库将对象转换为 JSON 字符串
        Gson gson = new Gson();
        String jsonResult = gson.toJson(countResult);
        // 设置返回值为 UTF-8
        resp.setCharacterEncoding("UTF-8");
        // 将 JSON 响应写回到前端
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResult);
    }
}
