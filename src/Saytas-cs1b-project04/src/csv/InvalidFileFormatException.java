package csv;

import java.io.IOException;

/**
 *  InvalidFileFormatException class extends java.io.IOException
 *  and throws an object of this type when a class wants to indicate
 *  that the format of the file is not valid
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class InvalidFileFormatException extends IOException
{
    private String message;
    private String fileName;

    /**
     * Constructs InvalidFileFormatException object describing the error
     * with the specified fileName and message parameters
     * @param fileName  The name of the file
     * @param message   The content of the message
     */
    public InvalidFileFormatException(String fileName, String message)
    {
        super();
        this.message = message;
        this.fileName = fileName;
    }

    /**
     * Accessor method receives no argument and returns a String
     * message which includes the fileName and the message
     * describing the nature of the error
     * @return  Output which is the message
     */
    public String getMessage()
    {
        String output;
        output = fileName + ", " + message;
        return output;
    }
}
