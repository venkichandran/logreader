package com.logreader.restservice;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogReaderController {

    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/logreader")
    public LogOutput readLogs(@RequestParam(value = "fileName", defaultValue = "World") String fileName) {
        return new LogOutput(counter.incrementAndGet(), String.format(template, fileName));
    }
}