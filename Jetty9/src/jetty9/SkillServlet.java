/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jetty9;

import com.duong.skill.Skill;
import com.duong.skill.skillManager;
import com.duong.skill.thrift_connection.SkillClientProvider;
import com.google.gson.Gson;
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
import jetty9.Jetty9;
import org.apache.thrift.TException;

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
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            Response r = new Response();
            r.errorCode = 400;
            r.message = "ERROR DELETE WITHOUT ITEM";
            r.data = null;
            String json = gson.toJson(r);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
            return;
        }
        switch (pathInfo) {
            case "":
            case "/": {
                // return error
                Response r = new Response();
                r.errorCode = 400;
                r.message = "ERROR PUT STRING";
                r.data = null;
                String json = gson.toJson(r);
                PrintWriter writer = resp.getWriter();
                writer.write(json);
                break;
            }
            default:
                // number -> delete skill by id
                // other -> return error
                try (PrintWriter writer = resp.getWriter()) {
                    String path = normalizePathInfo(req.getPathInfo());
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
                    //provider.getClient().multiDelete(listIDs);
                } catch (Exception ex) {

                } finally {
                    Response r = new Response();
                    r.errorCode = 400;
                    r.message = "ERROR PUT STRING";
                    r.data = null;
                    String json = gson.toJson(r);
                    PrintWriter writer = resp.getWriter();
                    writer.write(json);
                    break;
                }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (PrintWriter writer = resp.getWriter()) {
            String path = normalizePathInfo(req.getPathInfo());
            switch (path) {
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
                    // number -> get skill by id
                    List<Integer> listIDs = new ArrayList<>();
                    listIDs = extractIDFromPath(path);
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
            ex.printStackTrace();
        } finally {
            Response r = new Response();
            r.errorCode = 400;
            r.message = "ERROR PUT STRING";
            r.data = null;
            String json = gson.toJson(r);
            PrintWriter writer = resp.getWriter();
            writer.write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);
        switch (pathInfo) {
            case "":
            case "/":
                // return error
                break;
            default:
                // number -> delete skill by id
                // other -> return error
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);
        switch (pathInfo) {
            case "":
            case "/":
                // return error
                break;
            default:
                // number -> delete skill by id
                // other -> return error
                break;
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

    static boolean isNum(String strNum) {
        boolean flag = true;
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    static class Response {

        int errorCode;
        String message;
        Object data;
    }

}
