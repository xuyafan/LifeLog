package com.github.xuyafan.lifelog.event;

import com.github.xuyafan.lifelog.data.Record;

/**
 * 新增Record时能发送到订阅者RecordList的Event
 * Created by xuyafan on 2017/12/17.
 */

public class RecordEvent {

    public final Record record;

    public RecordEvent(Record record) {

        this.record = record;
    }

}
