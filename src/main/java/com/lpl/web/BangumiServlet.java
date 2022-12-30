package com.lpl.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lpl.pojo.Bangumi;
import com.lpl.pojo.Page;
import com.lpl.pojo.User;
import com.lpl.servlce.BangumiService;
import com.lpl.servlce.impl.BangumiServiceimpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
@SuppressWarnings("all")
@WebServlet("/bangumiServlet/*")
public class BangumiServlet extends BaseServlet {
    private BangumiService bangumiService = new BangumiServiceimpl();
    protected void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Bangumi bangumi = JSON.parseObject(params, Bangumi.class);

        if (bangumi==null){
            bangumi=new Bangumi();
        }
        String id = request.getParameter("id");
        bangumi.setUserId(Integer.parseInt(id));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        int start = (currentPage - 1) * pageSize;
        int count = pageSize;


        Page<Bangumi> page = bangumiService.selectByCondition(bangumi, start, count);

        String jsonString = JSONObject.toJSONString(page);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
//        response.sendRedirect("http://localhost/anime/page/bangumi/etrieval.html");
    }

    protected void addBangumi (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getReader().readLine();
        Bangumi bangumi = JSON.parseObject(params, Bangumi.class);

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        bangumi.setUserId(userId);

        String s = bangumiService.addBangumi(bangumi);

        response.getWriter().write(s);

    }

    protected void update (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getReader().readLine();
        Bangumi bangumi = JSON.parseObject(params, Bangumi.class);

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        bangumi.setUserId(userId);

        bangumiService.update(bangumi);

        response.getWriter().write("success");

    }

    protected void deleteSingle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        bangumiService.deleteSingle(Integer.parseInt(id));

        response.getWriter().write("success");
    }

    protected void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getReader().readLine();

        int[] ids = JSON.parseObject(params, int[].class);

        bangumiService.deleteByIds(ids);

        response.getWriter().write("success");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
