package com.github.xuyafan.lifelog.calendarlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.data.Record;
import com.github.xuyafan.lifelog.data.RecordDataSupport;
import com.github.xuyafan.lifelog.event.RecordEvent;
import com.github.xuyafan.lifelog.utils.DateUtil;
import com.kelin.calendarlistview.library.CalendarHelper;
import com.kelin.calendarlistview.library.CalendarListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;


public class SecondCalendarFragment extends Fragment {

    public static final SimpleDateFormat DAY_FORMAT = DateUtil.DAY_FORMAT;
    public static final SimpleDateFormat YEAR_MONTH_FORMAT =DateUtil.YEAR_MONTH_FORMAT;
    public static final SimpleDateFormat YEAR_MONTH_TITLE_FORMAT =new SimpleDateFormat("yyyy年MM月");
    private static final String TAG = "SecondCalendarFragment";
    protected View rootView;
    protected CalendarListView calendarListview;
    private RecordListAdapter recordListAdapter;
    private CalendarItemAdapter calendarItemAdapter;
    //保存每一天下面的数据列表 key:date "yyyy-mm-dd" format.
    private TreeMap<String, List<Record>> listTreeMap = new TreeMap<>();

    public SecondCalendarFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second_calendar, container, false);
        calendarListview = rootView.findViewById(R.id.calendar_listview);


        recordListAdapter = new RecordListAdapter(getContext());
        calendarItemAdapter = new CalendarItemAdapter(getContext());
        calendarListview.setCalendarListViewAdapter(calendarItemAdapter, recordListAdapter);

        //获得所有数据
        Calendar calendar = Calendar.getInstance();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(YEAR_MONTH_TITLE_FORMAT.format(calendar.getTime()));

        List<Record> recordList = RecordDataSupport.getRecords();
        onListDataLoadFinish(recordList);

        calendarListview.setOnListPullListener(new CalendarListView.onListPullListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

        //
        calendarListview.setOnMonthChangedListener(new CalendarListView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(String yearMonth) {
                Calendar calendar = CalendarHelper.getCalendarByYearMonth(yearMonth);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(YEAR_MONTH_TITLE_FORMAT.format(calendar.getTime()));
                loadCalendarData(yearMonth);
                Toast.makeText(getContext(), YEAR_MONTH_FORMAT.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });

        calendarListview.setOnCalendarViewItemClickListener(new CalendarListView.OnCalendarViewItemClickListener() {
            @Override
            public void onDateSelected(View View, String selectedDate, int listSection, SelectedDateRegion selectedDateRegion) {
//                Toast.makeText(getContext(),"onDateSelected",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


    private void loadCalendarData(String yearMonth) {


        if (yearMonth.equals(calendarListview.getCurrentSelectedDate().substring(0, 7))) {
            for (String d : listTreeMap.keySet()) {
                if (yearMonth.equals(d.substring(0, 7))) {
                    CustomCalendarItemModel customCalendarItemModel = calendarItemAdapter.getDayModelList().get(d);
                    if (customCalendarItemModel != null) {
                        customCalendarItemModel.setRecordCount(listTreeMap.get(d).size());
                        int totalScore =0;
                        for(Record record:listTreeMap.get(d)){
                            totalScore+=record.getScore();
                        }
                        customCalendarItemModel.setFav(totalScore>0);
                    }
                }
            }
            calendarItemAdapter.notifyDataSetChanged();
        }
    }



    private void onListDataLoadFinish(List<Record> records){

        for(Record record:records) {
            addRecord(record);
        }
        notifyAdapter();
    }


    @Subscribe
    public void onRecordEvent(RecordEvent recordEvent) {
        listTreeMap.clear();
        onListDataLoadFinish(RecordDataSupport.getRecords());
    }

    private void addRecord(Record record){
        //获取数据的日期
        String day=DAY_FORMAT.format(record.getAddTime());
        //给listTreeMap 添加数据
        if (listTreeMap.get(day) != null) {
            List<Record> list = listTreeMap.get(day);
            list.add(record);
        } else {
            List<Record> list = new ArrayList<>();
            list.add(record);
            listTreeMap.put(day, list);
        }


        CustomCalendarItemModel customCalendarItemModel = calendarItemAdapter.getDayModelList().get(day);
        if (customCalendarItemModel != null) {
            customCalendarItemModel.setRecordCount(listTreeMap.get(day).size());
            int totalScore =0;
            for(Record r:listTreeMap.get(day)){
                totalScore+=r.getScore();
            }
            customCalendarItemModel.setFav(totalScore>0);
        }

    }


    private void notifyAdapter(){
        recordListAdapter.setDateDataMap(listTreeMap);
        recordListAdapter.notifyDataSetChanged();
        calendarItemAdapter.notifyDataSetChanged();
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
