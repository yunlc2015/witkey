/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 页面请求耗时计时过滤器
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
@Component
public class DurationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 非GET请求不做处理，直接调用下一个Filter
        if (!"GET".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // 特定类型的请求不做处理，直接调用下一个Filter
        String url = request.getRequestURI().toLowerCase(Locale.ROOT);
        if (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif") ||
            url.endsWith(".eot") || url.endsWith(".svg") || url.endsWith(".ttf") || url.endsWith(".woff") || url.endsWith(".woff2") ||
            url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".map") ||
            url.startsWith("/manage") ) {
            chain.doFilter(request, response);
            return;
        }

        // 创建一个定制的Response对象，用于将 执行时长 替换到响应内容中
        CustomServletResponseWrapper customResponse = new CustomServletResponseWrapper(response);

        long timestamp = System.currentTimeMillis();
        // 调用下一个Filter
        chain.doFilter(request, customResponse);

        if (customResponse.getStatus()==200) {
            // 计算执行时长
            long duration = System.currentTimeMillis() - timestamp;
            // 读取响应内容，并替换预设的占位符
            String body = customResponse.getResponseBody();
            body = body.replace("{{execute_duration}}", duration+"ms");
            // 输出替换后的内容。
            response.getOutputStream().write(body.getBytes(response.getCharacterEncoding()));
        }
    }

    /**
     * 封装Response
     */
    private class CustomServletResponseWrapper extends HttpServletResponseWrapper {

        private ByteArrayOutputStream bos;

        public CustomServletResponseWrapper(HttpServletResponse response) {
            super(response);
            this.bos = new ByteArrayOutputStream();
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {

                }

                @Override
                public void write(int b) throws IOException {
                    bos.write(b);
                }

                @Override
                public void write(byte[] b) throws IOException {
                    bos.write(b);
                }
            };
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new OutputStreamWriter(bos));
        }

        public String getResponseBody() throws IOException {
            ServletOutputStream outputStream = this.getOutputStream();
            outputStream.flush();

            PrintWriter writer = this.getWriter();
            writer.flush();

            return bos.toString(this.getCharacterEncoding());
        }
    }

}
