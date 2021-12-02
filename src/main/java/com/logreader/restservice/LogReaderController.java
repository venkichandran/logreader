package com.logreader.restservice;

import java.io.IOException;
import java.util.List;

import com.logreader.restservice.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Log reader controller.
 *
 * @author Venki Chandran
 */
@RestController
public class LogReaderController {

	@Autowired
	LogReaderService logReaderService;

	@PostMapping("/logreader")
	public List<LogOutput> searchLogs(@RequestBody LogInput input) throws InvalidInputException, IOException {
		// Call to service for processing the request
		return logReaderService.processRequest(input);
	}
}
