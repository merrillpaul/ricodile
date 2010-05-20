/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;

/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class ByteArrayDataSource implements DataSource {

    private byte[] data;
    private String type;
    private String name;

    public ByteArrayDataSource(byte[] data, String type) {
        this.type = type;
        this.data = data;
    }

    public InputStream getInputStream() throws IOException {
        if (data == null) {
            throw new IOException("No data.");
        }

        return new ByteArrayInputStream(data);
    }

    public OutputStream getOutputStream() throws IOException {
        throw new IOException("Not supported.");
    }

    public String getContentType() {
        return type;
    }

    public void setContentType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
