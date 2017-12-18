package com.github.xuyafan.lifelog.calendarlist;

import com.kelin.calendarlistview.library.BaseCalendarItemModel;

/**
 * Created by xuyafan on 16-7-20.
 */
public class CustomCalendarItemModel  extends BaseCalendarItemModel{

    private int recordCount;
    private boolean isFav;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
