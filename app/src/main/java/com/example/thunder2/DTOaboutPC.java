package com.example.thunder2;

public class DTOaboutPC {
    private String location;
    private String seatKind;
    private int seat_total;
    private String spec;
    private String name;
    private String notice;
    private String uid;
    private int seatUnuse;

    public DTOaboutPC() {
    }

    public DTOaboutPC(String location, String seatKind, int seat_total, String spec, String name, String notice, String UID) {
        this.location = location;
        this.seatKind = seatKind;
        this.seat_total = seat_total;
        this.spec = spec;
        this.name = name;
        this.notice = notice;
        this.uid=UID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeatKind() {
        return seatKind;
    }

    public void setSeatKind(String seatKind) {
        this.seatKind = seatKind;
    }

    public int getSeat_total() {
        return seat_total;
    }

    public void setSeat_total(int seat_total) {
        this.seat_total = seat_total;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getUID(){return uid;}

    public void setUID(String UID){this.uid=UID;}

    public int getSeatUnuse(){return seatUnuse;}

    public void setSeatUnuse(int SeatUnuse){this.seatUnuse=SeatUnuse;}

}
