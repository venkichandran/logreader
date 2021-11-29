package com.logreader.restservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.logreader.exception.InvalidInputException;
import com.logreader.exception.LogReaderException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogReaderController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/logreader")
	public LogOutput readLogs(@RequestParam(value = "fileName", defaultValue = "World") String fileName) {
		return new LogOutput(counter.incrementAndGet(), String.format(template, fileName));
	}

	@PostMapping("/logstreamer")
	public List<LogOutput> searchLogs(@RequestBody LogInput input) throws InvalidInputException, FileNotFoundException, IOException {
//		List<LogOutput> output = new ArrayList<LogOutput>();
//		output.add(new LogOutput(counter.incrementAndGet(), String.format(template, input.getFileName())));
//		return  output;
//		try {
//			output = new LogReader().processRequest(input);
//		} catch (Exception ex) {
//
//		}
//		return output;
		return new LogReader().processRequest(input);
	}
}
