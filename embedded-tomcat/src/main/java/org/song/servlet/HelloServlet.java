package org.song.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        resp.setContentType("text/html");
//        writer.write("<html>");
//        writer.write("<head>");
//        writer.write("<meta charset=\"utf-8\">");
//        writer.write("</head>");
//        writer.write("<body>");
//        writer.write("<h1>");
//        writer.write("Hello Vue");
//        writer.write("</h1>");
//        writer.write("</body>");
//        writer.write("</html>");
//        writer.flush();
//        临时重定向 返回给浏览器重新发送请求 地址会变
//        resp.sendRedirect("/hello2");
//        永久重定向
//        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY); // 301
//        resp.setHeader("location", "/hello2");
//        请求转发 由服务器内部转发 地址不会变（仅在带参数的情况下，如果无参则地址还是会变化）
//        转发流程
//        1 获取ApplicationDisaptcher，根据新path创建mapping，在容器StandardWrapper中设置servlet class
//        2 容器会去从其stack结构的pool中找allocate servlet，如果没有会反射创建，用完之后deallocate，返回pool
//        3 dispatcher得到servlet后，创建filterChain，调用filter，并最终执行servlet处理请求
        req.getRequestDispatcher("/hello2").forward(req, resp);
    }

}
