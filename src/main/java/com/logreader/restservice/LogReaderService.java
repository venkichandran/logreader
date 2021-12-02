package com.logreader.restservice;

import com.logreader.restservice.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Service interface to process the log reader request.
 *
 * @author Venki Chandran
 */
@Component
public interface LogReaderService {

    List<LogOutput> processRequest(LogInput input) throws InvalidInputException, FileNotFoundException, IOException;
}
