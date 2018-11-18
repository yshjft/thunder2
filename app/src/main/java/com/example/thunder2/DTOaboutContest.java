package com.example.thunder2;

public class DTOaboutContest {
    private String Date;
    private String Deadline;
    private String ETC;
    private String Event;
    private String Host;
    private String How;
    private String Location;
    private String Name;
    private String Prize;
    private int qual; //나이를 숫자로 입력하도록 설정



    public DTOaboutContest(String date, String deadline, String ETC, String event, String host, String how, String location, String name, String prize, int qual) {
        Date = date;
        Deadline = deadline;
        this.ETC = ETC;
        Event = event;
        Host = host;
        How = how;
        Location = location;
        Name = name;
        Prize = prize;
        this.qual = qual;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getETC() {
        return ETC;
    }

    public void setETC(String ETC) {
        this.ETC = ETC;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getHow() {
        return How;
    }

    public void setHow(String how) {
        How = how;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }

    public int getQual() {
        return qual;
    }

    public void setQual(int qual) {
        this.qual = qual;
    }


}
