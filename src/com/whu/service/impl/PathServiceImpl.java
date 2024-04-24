package com.whu.service.impl;

import com.google.gson.Gson;
import com.whu.dao.PathDao;
import com.whu.dao.impl.PathDaoImpl;
import com.whu.service.PathService;

import java.nio.file.Path;

public class PathServiceImpl implements PathService {
    private PathDao pathDao = new PathDaoImpl();
    @Override
    public String anypath(Double[][] coordinates) {
        String Pathjson = pathDao.getpath(coordinates);
        Gson gson = new Gson();
        return gson.toJson(Pathjson);
    }
}
