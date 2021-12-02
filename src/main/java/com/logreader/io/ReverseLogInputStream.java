package com.logreader.io;

import java.io.*;

/**
 * Implementation input stream class for reading the log reverse.
 *
 * @author Venki Chandran
 */
public class ReverseLogInputStream extends InputStream {

    long start = -1;
    long end = -1;
    long position = -1;
    long lastPosition = -1;
    long lastChar = -1;

    RandomAccessFile input;

    /**
     * Constructor
     *
     * @param file
     * @throws FileNotFoundException
     */
    public  ReverseLogInputStream(File file) throws FileNotFoundException {
        // Read the file
        input = new RandomAccessFile(file, "r");
        // Initialize the variables for reading
        start = file.length();
        end = file.length();
        position = end;
        lastPosition = file.length() - 1;
    }

    @Override
    public int read() throws IOException {
        if (position < end ) {
            input.seek(position++);
            int readByte = input.readByte();
            return readByte;
        } else if (position > lastPosition && start < end) {
            // last line in file (first returned)
            findPreviousLine();
            if (lastChar != '\n' && lastChar != '\r') {
                // last line is not terminated
                return '\n';
            } else {
                return read();
            }
        } else if (position < 0) {
            return -1;
        } else {
            findPreviousLine();
            return read();
        }
    }

    /**
     * Finds the previous line for reverse reading.
     *
     * @throws IOException
     */
    private void findPreviousLine() throws IOException {

        // For the first time read, position at the end
        if (lastChar == -1) {
            input.seek(lastPosition);
            lastChar = input.readByte();
        }
        end = start;

        if (end == 0) {
            // This means, its in top of the file nothing to read, simply return
            end = start = position = -1 ;
            return;
        }
        // Read the file
        long currentPointer = start - 1;
        while (true) {
            currentPointer--;
            // Break if its in top of the file
            if (currentPointer < 0) {
                break;
            }
            input.seek(currentPointer);
            int readByte = input.readByte();
            // Ignore the last LF in the file. search backwards to find the previous LF.
            if (readByte == 0xA && currentPointer != lastPosition ) {
                break;
            }
        }
        // we want to start at pointer +1 so we are after the LF we found or at 0 the start of the file.
        start = currentPointer + 1;
        position = start;
    }

    @Override
    public void close() throws IOException {
        if (input != null) {
            input.close();
            input = null;
        }
    }

}
