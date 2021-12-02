package com.logreader.restservice;

import com.logreader.restservice.exception.InvalidInputException;
import com.logreader.io.ReverseLogInputStream;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

import java.util.List;

/**
 * Implementation class to process the log reader request.
 *
 * @author Venki Chandran
 */
@Service
public class LogReaderServiceImpl implements LogReaderService {

    static final String LOG_LOCATION = "/var/log";

    @Override
    public List<LogOutput> processRequest(LogInput input) throws InvalidInputException, IOException {

        List<LogOutput> output = new ArrayList<LogOutput>();
        //try {
        int counter = 1;
        // Validate the input and proceed only if there are valid input
        if (valildateInput(input)) {
            boolean searchLocalOnly = true;
            // Check for slave request in the input
            List<String> hosts = input.getHosts();
            if (hosts != null) {
                searchLocalOnly = false;
            }
            if (!searchLocalOnly) {
                for (String host : hosts) {
                    // Fire the API to slave
                    LogInput slaveInput = new LogInput(input.getFileName(),
                            input.getOccurance(), input.getEvent(), null);
                    List<LogOutput> slaveOut = processSlaveRequest(slaveInput);
                    output.addAll(slaveOut);
                }
            } else {
                // Read the log
                String filePath = LOG_LOCATION + '/' + input.getFileName();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new ReverseLogInputStream(new File(filePath))));
                while (true) {
                    String line = reader.readLine();
                    // Break if the line null or the number of occurances met
                    if (line == null || input.getOccurance() == counter - 1) {
                        break;
                    }
                    // Search for the event and add to the log
                    if (line.contains(input.getEvent())) {
                        LogOutput out = new LogOutput(counter, line);
                        counter++;
                        output.add(out);
                    }
                }
            }
        }
        return output;
    }

    /**
     * Processes slave requests.
     *
     * @param input
     *
     * @return List<LogOutput>
     *
     * @throws InvalidInputException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<LogOutput> processSlaveRequest(LogInput input) throws InvalidInputException, FileNotFoundException, IOException {

        List<LogOutput> output = new ArrayList<LogOutput>();
        // TODO Build slave request input and trigger the API
        return output;
    }

    /**
     * Validates the input.
     *
     * @param input
     * @return
     * @throws InvalidInputException
     */
    protected boolean valildateInput(LogInput input) throws InvalidInputException {
        boolean valid = true;
        String msg = "";
        if (input == null) {
            valid = false;
            msg = "Empty input, please provide valid input and retry";
        }
        // Validate for mandatory fields
        else if (input.getFileName() == null || input.getFileName().isEmpty()) {
            valid = false;
            msg = "Invalid file name, please provide valid file name and retry";
        }
        else if (input.getEvent() == null || input.getEvent().isEmpty()) {
            valid = false;
            msg = "Invalid event, please provide valid event and retry";
        }
        else if (input.getOccurance()  <= 0) {
            valid = false;
            msg = "Invalid occurance, please provide valid occurance and retry";
        }
        // Validate the file
        else {
            String filePath = LOG_LOCATION + '/' + input.getFileName();
            File file = null;
            try {
                file = new File(filePath);
                if (!file.exists()) {
                    msg = "File doesn't exist, please provide valid file name and retry";
                    valid = false;
                }
            } catch (NullPointerException ex) {
                msg = "Invalid file name, please provide valid file name and retry";
                valid = false;
            }
        }

        if (valid == false) {
            throw new InvalidInputException(msg);
        }
        return valid;
    }
}
