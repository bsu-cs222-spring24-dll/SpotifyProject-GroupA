package edu.bs.cs;

import edu.bsu.cs.model.ErrorCatcher;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCatcherTest {
    @Test
    public void testStatusResponse(){
        int statusCode = 450;
        ErrorCatcher ec = new ErrorCatcher();
        String response = ec.statusError(statusCode);
        assertEquals("Unknown status code: 450", response);

    }

    @Test
    public void testSearchNotFound(){
        ErrorCatcher ec = new ErrorCatcher();
        String response = String.valueOf(ec.statusError(400));

        assertEquals("Bad Request - The request could not be understood by the server due to malformed syntax. The message body will contain more information; see Response Schema.", response);
    }
    @Test
    public void testConfigPropertiesError(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));
        ErrorCatcher.configPropertiesError();
        String errorMessage = outputStreamCaptor.toString().trim();
        assertEquals("'NumberFormatException' Error!!! -- This is usually caused by your configuration.properties file containing a mistyped or invalid token.", errorMessage);
    }
    @Test
    public void testConfigFileNotFoundError(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));
        ErrorCatcher.configFileNotFoundError();
        String errorMessage = outputStreamCaptor.toString().trim();
        assertEquals("'FileNotFound' Error!!! -- Please verify that you have the 'configuration.properties' file in your project.", errorMessage);

    }
    @Test
    public void testNullPointerError(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));
        ErrorCatcher.nullPointerExceptionError();
        String errorMessage = outputStreamCaptor.toString().trim();
        assertEquals("'NullPointerException' Error!!! -- Please verify that the 'configuration.properties' file has been added to your project.", errorMessage);

    }
    @Test
    public void testPrintUnknownError(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));
        ErrorCatcher.PrintUnknownError();
        String errorMessage = outputStreamCaptor.toString().trim();
        assertEquals("Unknown error detected", errorMessage);

    }
    @Test
    public void testSleepError(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));
        ErrorCatcher.sleepError();
        String errorMessage = outputStreamCaptor.toString().trim();
        assertEquals("Error with Thread.sleep() function call. Please check code for unreachable statements or loops.", errorMessage);

    }
}


