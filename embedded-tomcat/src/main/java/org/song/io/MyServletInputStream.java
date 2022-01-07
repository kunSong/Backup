package org.song.io;

import org.apache.catalina.connector.CoyoteInputStream;
import org.apache.catalina.connector.InputBuffer;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MyServletInputStream extends ServletInputStream {
    private ByteArrayInputStream ba;

    public MyServletInputStream(ByteArrayInputStream ba) {
        this.ba = ba;
    }
    @Override
    public boolean isFinished() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        //no op
    }

    @Override
    public int read() throws IOException {
        return ba.read();
    }
}
