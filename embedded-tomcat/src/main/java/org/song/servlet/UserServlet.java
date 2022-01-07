package org.song.servlet;

import com.mitchellbosecke.pebble.error.PebbleException;
import org.song.engine.ViewEngine;
import org.song.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        User user = new User();
        user.setName("songkun");
        user.setAge(34);
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        ViewEngine engine = new ViewEngine(req.getServletContext());
        try {
            engine.render("user", writer, model);
        } catch (PebbleException e) {
            e.printStackTrace();
        }
        writer.flush();
    }
}
