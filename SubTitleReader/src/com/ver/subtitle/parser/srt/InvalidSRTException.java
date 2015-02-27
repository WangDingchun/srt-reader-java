package com.ver.subtitle.parser.srt;

/**
 * An exception for an invalid SRT format.
 * 
 * @author fredy
 */
public class InvalidSRTException extends SRTException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public InvalidSRTException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public InvalidSRTException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public InvalidSRTException(Throwable cause) {
        super(cause);
    }
}
