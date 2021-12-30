package com.hk.logging.filters;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class RequestWrapper  extends HttpServletRequestWrapper {
    private final byte[] cachedBody;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        this.cachedBody = StreamUtils.copyToByteArray(inputStream);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader()  {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }
}
