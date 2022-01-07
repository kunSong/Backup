package org.song.request;

import org.song.io.MyServletInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyRequestProxy implements InvocationHandler {
    private HttpServletRequest target;
    private byte[] buffer;

    public MyRequestProxy(HttpServletRequest request) {
        this.target = request;
    }

    public HttpServletRequest getRequestProxy(){
        return (HttpServletRequest) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(buffer == null && method.getName().equals("getInputStream")) {
            ServletInputStream inputStream = (ServletInputStream) method.invoke(target, args);
            int data = 0;
            int index = 0;
            buffer = new byte[1024];
            while((data = inputStream.read()) != -1){
                buffer[index] = (byte)data;
                index++;
                if(index == buffer.length) {
                    byte[] bufferNew = new byte[buffer.length * 2];
                    System.arraycopy(buffer, 0, bufferNew, 0, buffer.length);
                    buffer = bufferNew;
                }
            }
            ByteArrayInputStream ba = new ByteArrayInputStream(buffer);
            return new MyServletInputStream(ba);
        } else if(buffer != null && method.getName().equals("getInputStream")){
            ByteArrayInputStream ba = new ByteArrayInputStream(buffer);
            return new MyServletInputStream(ba);
        }
        return method.invoke(target, args);
    }


}
