package com.example.thunder2;

public class DataForm_forContest {
    private String contest_name;

    public DataForm_forContest(String contest_name){
        this.contest_name=contest_name;
    }

    public String getContest_name(){
        return contest_name;
    }

    public void setContest_name(String contest_name){
        this.contest_name=contest_name;
    }

}
