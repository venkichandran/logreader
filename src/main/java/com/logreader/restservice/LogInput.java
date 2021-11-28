package com.logreader.restservice;

import java.util.List;

public class LogInput {

    private String fileName;
    private int occurance;
    private String event;
    private List<String> hosts;

    public LogInput(String fileName, int occurance, String event, List<String> hosts) {
        this.fileName = fileName;
        this.occurance = occurance;
        this.event = event;
        this.hosts = hosts;
    }

    /**
     * get field
     *
     * @return fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * set field
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get field
     *
     * @return occurance
     */
    public int getOccurance() {
        return this.occurance;
    }

    /**
     * set field
     *
     * @param occurance
     */
    public void setOccurance(int occurance) {
        this.occurance = occurance;
    }

    /**
     * get field
     *
     * @return event
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * set field
     *
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * get field
     *
     * @return hosts
     */
    public List<String> getHosts() {
        return this.hosts;
    }

    /**
     * set field
     *
     * @param hosts
     */
    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
