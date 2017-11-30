package com.ideal.audit.common.framework;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by xingsen on 15/2/27.
 */
public class StringJspWriter extends JspWriter {
    private StringBuilder sb = null;

    public StringJspWriter() {
        this(8192, true);
    }

    public StringJspWriter(int bufferSize, boolean autoFlush) {
        super(bufferSize, autoFlush);
        sb = new StringBuilder(bufferSize);
    }

    public String toString() {
        return getString();
    }

    public String getString() {
        return sb.toString();
    }

    public void close() throws IOException {
    }


    public void flush() throws IOException {
    }

    public void clear() throws IOException {
        sb = new StringBuilder();
    }

    public void clearBuffer() throws IOException {
        sb = new StringBuilder();
    }

    public int getRemaining() {
        return 0;
    }

    public void newLine() throws IOException {
        sb.append("\n");
    }

    public void print(boolean arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(char arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(int arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(long arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(float arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(double arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(char[] arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(String arg0) throws IOException {
        sb.append(arg0);
    }

    public void print(Object arg0) throws IOException {
        sb.append(arg0);
    }

    public void println() throws IOException {
        sb.append("\n");
    }

    public void println(boolean arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(char arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(int arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(long arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(float arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(double arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(char[] arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(String arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void println(Object arg0) throws IOException {
        sb.append(arg0);
        println();
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        sb.append(cbuf, off, len);
    }
}