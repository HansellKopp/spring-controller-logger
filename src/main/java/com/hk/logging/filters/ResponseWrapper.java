package com.hk.logging.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {

    private TeeServletOutputStream teeStream;

    private PrintWriter teeWriter;

    private ByteArrayOutputStream bos;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public String getContent() {
        return bos.toString();
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        if (this.teeWriter == null) {
            this.teeWriter = new PrintWriter(new OutputStreamWriter(getOutputStream()));
        }
        return this.teeWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        if (teeStream == null) {
            bos = new ByteArrayOutputStream();
            teeStream = new TeeServletOutputStream(getResponse().getOutputStream(), bos);
        }
        return teeStream;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (teeStream != null) {
            teeStream.flush();
        }
        if (this.teeWriter != null) {
            this.teeWriter.flush();
        }
    }
}