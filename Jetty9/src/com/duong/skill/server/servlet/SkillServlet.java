/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.skill.server.servlet;

import com.duong.skill.Skill;
import com.duong.skill.thrift_connection.SkillClientProvider;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.duong.skill.server.Jetty9;
import org.apache.thrift.TException;
import org.json.JSONObject;

/**
 *
 * @author hoang
 */
public class SkillServlet extends HttpServlet {

    private SkillClientProvider provider;

    public SkillServlet(SkillClientProvider clientProvider) {
        if (clientProvider == null) {
            throw new NullPointerException();
        }
        this.provider = clientProvider;
    }
    final Gson gson = new Gson();

    static Pattern IDS_REGEX = Pattern.compile("\\d+(,\\d+)*");

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            //get path info
            String pathInfo = req.getPathInfo();
            //path not have id
            if (pathInfo == null) {
                //return error code and error message
                Response r = new Response();
                r.errorCode = 400;
                r.message = "ERROR DELETE WITHOUT ID";
                r.data = null;
                String json = gson.toJson(r);
                writer.write(json);
                return;
            }
            switch (pathInfo) {
                case "":
                case "/": {
                    // return error
                    Response r = new Response();
                    r.errorCode = 400;
                    r.message = "ERROR DELETE WITHOUT ID";
                    r.data = null;
                    String json = gson.toJson(r);
                    writer.write(json);
                    break;
                }
                //path had string
                default:
                    String path = normalizePathInfo(req.getPathInfo());
                    Matcher matcher = IDS_REGEX.matcher(path.trim());
                    //wrong string (not number)
                    if (matcher.matches() == false) {
                        //return error
                        Response r = new Response();
                        r.errorCode = 0;
                        r.message = "ERROR PUT STRING";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    // number -> delete skill by id
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
                    provider.getClient().multiDelete(listIDs);
                    break;
            }
        } catch (TException ex) {

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (PrintWriter writer = resp.getWriter()) {
            String path = normalizePathInfo(req.getPathInfo());
            switch (path) {
                //if path is empty
                case "":
                case "/": {
                    // Get all skills data
                    List allSkills = provider.getClient().findAllSkills();
                    Response r = new Response();
                    r.errorCode = 0;
                    r.message = "OK";
                    r.data = allSkills;
                    String json = gson.toJson(r);
                    writer.write(json);
                    break;
                }
                default:
                    //check path is number ?
                    Matcher matcher = IDS_REGEX.matcher(path.trim());
                    //if wrong, return error
                    if (matcher.matches() == false) {
                        Response r = new Response();
                        r.errorCode = 0;
                        r.message = "ERROR GET";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    //handle path, split id 
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
                    //get id -> show client
                    List getSkills = provider.getClient().multiGetByIDs(listIDs);
                    Response r = new Response();
                    r.errorCode = 0;
                    r.message = "OK";
                    r.data = getSkills;
                    String json = gson.toJson(r);
                    writer.write(json);
                    break;
            }
            writer.flush();
        } catch (TException ex) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try (PrintWriter writer = resp.getWriter()) {
            switch (pathInfo) {
                case "":
                case "/": {

                    String line = null;
                    try {
                        BufferedReader reader = req.getReader();
                        List<Skill> skills = new ArrayList<>();
                        //get json
                        while ((line = reader.readLine()) != null) {
                            JSONObject jsonObject = new JSONObject(line);
                            String name = jsonObject.getString("name");
                            int id = jsonObject.getInt("id");
                            Skill skill = new Skill(id, name);
                            skills.add(skill);
                        }
                        //insert item
                        provider.getClient().multiInsert(skills);
                    } catch (Exception e) {
                        Response r = new Response();
                        r.errorCode = 400;
                        r.message = "WRONG FIELD NAME OR FIELD TYPE";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    break;
                }
                default:
                    String path = normalizePathInfo(req.getPathInfo());
                    //check path is number ?
                    Matcher matcher = IDS_REGEX.matcher(path.trim());
                    //if wrong, return error
                    if (matcher.matches() == false) {
                        Response r = new Response();
                        r.errorCode = 0;
                        r.message = "THAT NOT ID";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    //is number
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
                    try {
                        BufferedReader reader = req.getReader();
                        //get json
                        String line = reader.readLine();
                        JSONObject jsonObject = new JSONObject(line);
                        String name = jsonObject.getString("name");
                        Skill skill = new Skill(listIDs.get(0), name);
                        //insert item (1)
                        provider.getClient().insertSkill(skill);
                    } catch (Exception e) {
                        //error field name
                        Response r = new Response();
                        r.errorCode = 400;
                        r.message = "WRONG FIELD NAME OR FIELD TYPE";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try (PrintWriter writer = resp.getWriter()) {
            switch (pathInfo) {
                case "":
                case "/": {

                    String line = null;
                    try {
                        BufferedReader reader = req.getReader();
                        List<Skill> skills = new ArrayList<>();
                        //get json
                        while ((line = reader.readLine()) != null) {
                            JSONObject jsonObject = new JSONObject(line);
                            String name = jsonObject.getString("name");
                            int id = jsonObject.getInt("id");
                            Skill skill = new Skill(id, name);
                            skills.add(skill);
                        }
                        //update item
                        provider.getClient().multiUpdate(skills);
                    } catch (Exception e) {
                        Response r = new Response();
                        r.errorCode = 400;
                        r.message = "WRONG FIELD NAME OR FIELD TYPE";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    break;
                }
                default:
                    String path = normalizePathInfo(req.getPathInfo());
                    //check path is number ?
                    Matcher matcher = IDS_REGEX.matcher(path.trim());
                    //if wrong, return error
                    if (matcher.matches() == false) {
                        Response r = new Response();
                        r.errorCode = 0;
                        r.message = "THAT NOT ID";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
                    //is number
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
                    try {
                        BufferedReader reader = req.getReader();
                        //get json
                        String line = reader.readLine();
                        JSONObject jsonObject = new JSONObject(line);
                        String name = jsonObject.getString("name");
                        Skill skill = new Skill(listIDs.get(0), name);
                        //update item (1)
                        provider.getClient().updateByID(skill);
                    } catch (Exception e) {
                        //error field name
                        Response r = new Response();
                        r.errorCode = 400;
                        r.message = "WRONG FIELD NAME OR FIELD TYPE";
                        r.data = null;
                        String json = gson.toJson(r);
                        writer.write(json);
                        return;
                    }
            }
        }
    }

    static String normalizePathInfo(String s) {
        if (s == null) {
            return "";
        }
        if (s.startsWith(Jetty9.CONTEXT_PATH)) {
            s = s.replace(Jetty9.CONTEXT_PATH, "");
        }
        if (s.startsWith("/")) {
            return s.substring(1);
        }
        return s;
    }

    static List<Integer> extractIDFromPath(String path) {
        List<Integer> IDS = new ArrayList<Integer>();
        Matcher matcher = IDS_REGEX.matcher(path.trim());

        if (matcher.matches()) {
            String[] split = path.split(",");
            for (String s : split) {
                Integer id = Integer.parseInt(s);
                IDS.add(id);
            }
        } else {
            throw new IllegalArgumentException("Malformed id");

        }
        return IDS;
    }

    static class Response {

        int errorCode;
        String message;
        Object data;
    }

}
