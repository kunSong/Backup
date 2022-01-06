package org.song.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello2")
public class Hello2Servlcet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<meta charset=\"utf-8\">");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<div id=\"app\">");
        writer.write("<h1>");
        writer.write("{{ info }}");
        writer.write("</h1>");
        writer.write("</div>");
        writer.write("<script src=\"https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js\"></script>");
        writer.write("<script>");
        writer.write("var vm = new Vue({el:'#app',data:{info:'hello vue2'}});");
        writer.write("</script>");
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }
}
