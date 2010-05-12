/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flytxt.commons.reporting.parameter;

/**
 *
 * @author merrill.paul
 */
public class ParameterProviderException extends Exception {

    /**
     * Creates a new instance of <code>ParameterProviderException</code> without detail message.
     */
    public ParameterProviderException() {
    }

    public ParameterProviderException(Throwable cause) {
        super(cause);
    }


    /**
     * Constructs an instance of <code>ParameterProviderException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ParameterProviderException(String msg) {
        super(msg);
    }
}
