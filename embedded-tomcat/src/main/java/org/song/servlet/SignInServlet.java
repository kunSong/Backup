package org.song.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    private Map<String, String> dbMap; // thread-unsafe

    public SignInServlet() {
        dbMap = new HashMap<>();
        dbMap.put("songkun", "123456");
        dbMap.put("chenhuihui", "123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<div>");
        writer.write("<form action=\"/signin\" method=\"post\">");
        writer.write("Username: <input type=\"text\" name=\"username\"/><br/>");
        writer.write("Password: <input type=\"password\" name=\"password\"/><br/>");
        writer.write("<button type=\"submit\">Submit</button>");
        writer.write("<a href=\"/index\">Cancel</a>");
        writer.write("</form>");
        writer.write("</div>");
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果没有session会创建session，并把JSESSIONID放到cookie，JSESSIONID是用来找对应的session对象，ConncurrentHashMap存放
        // session中attribute也是使用ConncurrentHashMap存放
        HttpSession session = req.getSession();
        // 使用getParamater相当于从ServletInputStream中解析，然后放到map中缓存
        // 如果再去读ServletInputStream，就没有东西了，会报错
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("password"));
        ServletInputStream inputStream = req.getInputStream();
        int data = 0;
        StringBuilder sb = new StringBuilder();
        while((data = inputStream.read()) != -1) {
            sb.append((char)data);
        }
        String parms = sb.toString().trim();
        System.out.println(parms);
        String username = parms.split("&")[0].split("=")[1];
        String password = parms.split("&")[1].split("=")[1];
        if(dbMap.get(username) != null && dbMap.get(username).equals(password)) {
            session.setAttribute("user", username);
            resp.sendRedirect("/index");
        } else {
            resp.sendError(400);
        }
    }
}
