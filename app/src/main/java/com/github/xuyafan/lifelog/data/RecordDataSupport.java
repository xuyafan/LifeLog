package com.github.xuyafan.lifelog.data;

import com.github.xuyafan.lifelog.utils.DateUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyafan on 2017/12/18.
 */

public class RecordDataSupport {

    public static List<Record> getRecords(){
        return DataSupport.findAll(Record.class);
    }

    public static List<Record> getRecordListByDate(Date date){
        List<Record> recordList = DataSupport.findAll(Record.class);
        List<Record> listByDate =new ArrayList<>();
        for(Record record :recordList){
            if(DateUtil.isSameDay(date,record.getAddTime())){
                listByDate.add(record);
            }
        }
        return  listByDate;
    }

    public static List<Record> getRecordListOfLastDay(){
        Record record = DataSupport.findLast(Record.class);
        if(record==null){
            return null;
        }
        return getRecordListByDate(record.getAddTime());
    }
}
