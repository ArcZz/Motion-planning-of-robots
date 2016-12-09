/*
 * Project:    TestForMain
 * File:       ParseException.java
 * Author:     Michael Esker
 * Date:       Dec 8, 2016
 * Time:       2:22:22 PM
 */
package project;

/**
 *
 * @author Michael Esker
 */
public class ParseException extends Exception{
    
    public ParseException() {
        
    }
    
    public ParseException(String message) {
        super(message);
    }
}
