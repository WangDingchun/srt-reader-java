package com.ver.subtitle.parser.srt;

/**
 * An exception while reading an SRT file.
 * 
 * @author fredy
 */
public class SRTReaderException extends SRTException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public SRTReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public SRTReaderException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public SRTReaderException(Throwable cause) {
        super(cause);
    }
}
