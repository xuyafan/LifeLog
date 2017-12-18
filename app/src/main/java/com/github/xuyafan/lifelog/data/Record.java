package com.github.xuyafan.lifelog.data;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by xyf18 on 2017/12/13.
 */

public class Record extends DataSupport{
    private String type;
    private String description;
    private int score;

    private Date addTime;
    private Date startTime;
    private Date endTime;

    public Record(String type, String description, int score) {
        this.type = type;
        this.description = description;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }
}
