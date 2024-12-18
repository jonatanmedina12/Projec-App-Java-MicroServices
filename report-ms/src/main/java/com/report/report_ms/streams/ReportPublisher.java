package com.report.report_ms.streams;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class ReportPublisher {

    @Autowired
    private StreamBridge streamBridge;





    /*
    * topic name-> consumerReport
    *  */

    public void publishReport (String report){

        this.streamBridge.send("consumerReport",report);

        this.streamBridge.send("consumerReport-in-0",report);

        this.streamBridge.send("consumerReport-out-0",report);

    }


}
