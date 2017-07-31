package com.interview.model;

/**
 * Created by robert.j.ssemmanda on 31/07/2017.
 */
public class MessageStatics {
    private String name;
    private Long stat;

    public MessageStatics(){

    }

    public MessageStatics(String name, Long stat) {
        this.name = name;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStat() {
        return stat;
    }

    public void setStat(Long stat) {
        this.stat = stat;
    }
}
