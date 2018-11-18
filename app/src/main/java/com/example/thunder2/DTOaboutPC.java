package com.example.thunder2;

public class DTOaboutPC {
    private String Location;
    private String SeatKind;
    private int Seat_total;
    private String Spec;
    private String name;
    private String notice;

    public DTOaboutPC() {
    }

    public DTOaboutPC(String location, String seatKind, int seat_total, String spec, String name, String notice) {
        Location = location;
        SeatKind = seatKind;
        Seat_total = seat_total;
        Spec = spec;
        this.name = name;
        this.notice = notice;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSeatKind() {
        return SeatKind;
    }

    public void setSeatKind(String seatKind) {
        SeatKind = seatKind;
    }

    public int getSeat_total() {
        return Seat_total;
    }

    public void setSeat_total(int seat_total) {
        Seat_total = seat_total;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
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
}
