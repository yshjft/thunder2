package com.example.thunder2;

public class DTOaboutContest {
    private String date;
    private String deadline;
    private String etc;
    private int event;
    private String host;
    private String how;
    private String location;
    private String name;
    private String prize;
    private String quali;
    private String uid;
    private String key;
    private String image;

    public DTOaboutContest() { }
    public DTOaboutContest(String date, String deadline, String ETC, int event, String host, String how, String location, String name, String prize, String quali, String uid, String key, String image) {
        this.date = date;
        this.deadline = deadline;
        etc = ETC;
        event = event;
        this.host = host;
        how = how;
        this.location = location;
        this.name = name;
        this.prize = prize;
        this.quali = quali;
        this.uid=uid;
        this.key=key;
        this.image=image;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getETC() {
        return etc;
    }

    public void setETC(String ETC) {
        etc = ETC;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getQuali() {
        return quali;
    }

    public void setQuali(String quali) {
        this.quali = quali;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getkey(){return key;}

    public void setkey(String key){this.key=key;}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


