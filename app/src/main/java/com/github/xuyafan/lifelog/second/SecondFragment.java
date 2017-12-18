package com.github.xuyafan.lifelog.second;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.event.RecordEvent;
import com.github.xuyafan.lifelog.data.Record;
import com.github.xuyafan.lifelog.utils.DateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";

    protected View rootView;
    protected TextView tvTodayScore;
    protected RecyclerView recycle;


    private RecordAdapter recordAdapter;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        initView(rootView);

        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recordAdapter = new RecordAdapter(getActivity(),getRecordListByDate(new Date()));
        recycle.setAdapter(recordAdapter);

        calcScore();
        return rootView;
    }


    private void initView(View rootView) {
        tvTodayScore = (TextView) rootView.findViewById(R.id.tv_todayScore);
        recycle = (RecyclerView) rootView.findViewById(R.id.recycle);

    }


    public void updateRecordList() {
        //新增record通过eventbus，直接通知刷新列表，该方法用于下拉刷新，从数据库重新获取
        recordAdapter.getRecordList().clear();
        recordAdapter.getRecordList().addAll(getRecordListByDate(new Date()));
        recordAdapter.notifyDataSetChanged();
        calcScore();
    }



    public List<Record> getRecordListByDate(Date date){
        List<Record> recordList = DataSupport.findAll(Record.class);
        List<Record> listByDate =new ArrayList<>();
        for(Record record :recordList){
            if(DateUtil.isSameDay(date,record.getAddTime())){
                listByDate.add(record);
            }
        }
        return  listByDate;
    }


    private void calcScore() {

        int totalScore = 0;
        for (Record record : recordAdapter.getRecordList()) {
            totalScore += record.getScore();
        }
        tvTodayScore.setText(String.valueOf(totalScore));
        tvTodayScore.setTextColor(totalScore >= 0 ?
                getContext().getResources().getColor(R.color.green) :
                getContext().getResources().getColor(R.color.red));
    }

    @Subscribe
    public void onRecordEvent(RecordEvent recordEvent) {
        //way 1
        recordAdapter.getRecordList().add(recordEvent.record);
        recordAdapter.notifyDataSetChanged();
        calcScore();

        //way 2
//        updateRecordList();
    }




    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



}
