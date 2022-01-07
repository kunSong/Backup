package org.song.filter;

import org.song.request.MyRequestProxy;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/upload")
public class UploadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletRequest requestProxy = new MyRequestProxy((HttpServletRequest) servletRequest).getRequestProxy();
        ServletInputStream inputStream = requestProxy.getInputStream();
        int data = 0;
        StringBuilder sb = new StringBuilder();
        while((data = inputStream.read()) != -1) {
            sb.append((char)data);
        }
        System.out.println(sb.toString().trim());
        filterChain.doFilter(requestProxy, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
