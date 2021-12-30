package com.hk.logging.filters;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachedBodyServletInputStream extends ServletInputStream {

    private final InputStream inputStream;

    public CachedBodyServletInputStream(byte[] cachedBody) {
        this.inputStream = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public boolean isFinished() {
        try {
            return this.inputStream.available() == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
    }

    @Override
    public int read() throws IOException {
        return this.inputStream.read();
    }
}
