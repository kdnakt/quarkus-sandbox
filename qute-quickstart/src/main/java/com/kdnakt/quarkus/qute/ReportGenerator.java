package com.kdnakt.quarkus.qute;

import javax.inject.Inject;

import io.quarkus.qute.Template;
import io.quarkus.qute.api.ResourcePath;
import io.quarkus.scheduler.Scheduled;

public class ReportGenerator {

    @Inject
    SampleService service;

    @ResourcePath("reports/v1/report_01") 
    Template report;

    @Scheduled(every="10s") 
    void generate() {
        String result = report
            .data("samples", service.get())
            .data("now", java.time.LocalDateTime.now())
            .render(); 
        // Write the result somewhere...
        System.out.println(result);
    }
}