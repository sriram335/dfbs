package hs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class BufferedHttpServletResponse extends HttpServletResponseWrapper {
    private PrintWriter responseWriter;
    private ByteArrayServletStream _bass;
    private boolean _getOutputStreamCalled = false;
    private boolean _getWriterCalled = false;
    private boolean _sendRedirectCalled = false;

    private class ByteArrayServletStream extends ServletOutputStream {

        private ByteArrayOutputStream baos;

        public ByteArrayServletStream() {
            this.baos = new ByteArrayOutputStream();
        }

        public void write(int asciiValue) {
            baos.write(asciiValue);
        }

        public void flush() throws IOException {
            baos.flush();
        }

        public ByteArrayOutputStream getByteArrayOutputStream() {
            return baos;
        }

        @Override
        public boolean isReady() {
            // TODO Implement this method
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            // TODO Implement this method
        }
    }

    public BufferedHttpServletResponse(HttpServletResponse httpResponse) {
        super(httpResponse);
        _bass = new ByteArrayServletStream();
    }

    public void setContentLength(int contentLength) {
        // Ignore this it will be set by the filter.
    }

    public ServletOutputStream getOutputStream() {
        if (_getWriterCalled) {
            throw new IllegalStateException();
        }

        _getOutputStreamCalled = true;
        return _bass;
    }

    public PrintWriter getWriter() {
        if (_getOutputStreamCalled) {
            throw new IllegalStateException();
        } else if (responseWriter == null) {
            _getWriterCalled = true;
            responseWriter = new PrintWriter(_bass);
            return responseWriter;
        } else {
            return responseWriter;
        }
    }

    public boolean wasGetWriterCalled() {
        return _getWriterCalled;
    }

    public byte[] getBuffer() {
        return _bass.getByteArrayOutputStream().toByteArray();
    }

    public void sendRedirect(String location) throws IOException {
        _sendRedirectCalled = true;
        super.sendRedirect(location);
    }

    public boolean sendRedirectCalled() {
        return _sendRedirectCalled;
    }
}
