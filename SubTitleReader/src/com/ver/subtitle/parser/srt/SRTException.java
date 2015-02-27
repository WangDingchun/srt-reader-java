package com.ver.subtitle.parser.srt;

/**
 * Any exceptions related to SRT.
 * 
 * @author fredy
 */
public class SRTException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public SRTException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public SRTException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public SRTException(Throwable cause) {
        super(cause);
    }
}
