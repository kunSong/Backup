package org.song.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if(user == null) {
            writer.write("<html>");
            writer.write("<head>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<div>");
            writer.write("<a href=\"/signin\">Sign In</a>");
            writer.write("</div>");
            writer.write("</body>");
            writer.write("</html>");
        } else {
            writer.write("<html>");
            writer.write("<head>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<div>");
            writer.write("<a href=\"/signout\">Sign Out</a>");
            writer.write("</div>");
            writer.write("</body>");
            writer.write("</html>");
        }
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            System.out.println(cookies.length);
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getDomain()); // null 应该是localhost，域名
                System.out.println(cookie.getPath()); // null
                System.out.println(cookie.getName()); // JSESSIONID lang
                System.out.println(cookie.getValue()); // BC831FAD86A4A7D7102CC85DD7D4346E zh-CN
                System.out.println(cookie.getMaxAge()); // -1
            }
        }
        Cookie langCookie = new Cookie("lang", "zh-CN");
        langCookie.setPath("/"); // 只有这个路径下能获得cookie，
        langCookie.setMaxAge(86400); // 过期时间秒 浏览器端 服务器不会保存cookie
        langCookie.setSecure(true); // 否则https不会发送cookie
        langCookie.setHttpOnly(true); // 防止人家在cookie中加东西，通过document可以获取cookie，XSS攻击
        resp.addCookie(langCookie);
        writer.flush();
    }

}
