package com.kdnakt.quarkus.qute;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SampleService {

    public List<Sample> get() {
        final List<Sample> list = new ArrayList<>();
        Sample s1 = new Sample();
        s1.data = "s1";
        s1.name = "Sample 1";
        s1.valid = true;
        list.add(s1);
        Sample s2 = new Sample();
        s2.data = "s2";
        s2.name = "Sample 2";
        s2.valid = false;
        list.add(s2);
        Sample s3 = new Sample();
        list.add(s3);
        return list;
    }

}