package org.song.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/lang")
public class LanguageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String lang = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("lang")) {
                lang = cookie.getValue();
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.write(lang);
        writer.flush();
    }
}
