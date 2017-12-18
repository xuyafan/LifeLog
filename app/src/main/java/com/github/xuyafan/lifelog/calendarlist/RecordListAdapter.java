package com.github.xuyafan.lifelog.calendarlist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.data.Record;
import com.github.xuyafan.lifelog.event.RecordEvent;
import com.github.xuyafan.lifelog.utils.DateUtil;
import com.kelin.calendarlistview.library.BaseCalendarListAdapter;
import com.kelin.calendarlistview.library.CalendarHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;


public class RecordListAdapter extends BaseCalendarListAdapter<Record> {


    public RecordListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getSectionHeaderView(String date, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        List<Record> recordList = dateDataMap.get(date);
        if (convertView != null) {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.listitem_calendar_header, null);
            headerViewHolder = new HeaderViewHolder();
            headerViewHolder.dayText = convertView.findViewById(R.id.header_day);
            headerViewHolder.yearMonthText = convertView.findViewById(R.id.header_year_month);
            headerViewHolder.tvTodayScore = convertView.findViewById(R.id.tv_todayScore);
            convertView.setTag(headerViewHolder);
        }

        Calendar calendar = CalendarHelper.getCalendarByYearMonthDay(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day < 10) {
            dayStr = "0" + dayStr;
        }
        headerViewHolder.dayText.setText(dayStr);
        headerViewHolder.yearMonthText.setText(DateUtil.YEAR_MONTH_FORMAT.format(calendar.getTime()));
        int todayScore = 0;
        for (Record record : recordList) {
            todayScore += record.getScore();
        }
        headerViewHolder.tvTodayScore.setText(todayScore + "");
        return convertView;
    }

    @Override
    public View getItemView(final Record record, String date, int pos, View convertView, ViewGroup parent) {
        ContentViewHolder contentViewHolder;
        if (convertView != null) {
            contentViewHolder = (ContentViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.listitem_calendar_content, null);
            contentViewHolder = new ContentViewHolder();
            contentViewHolder.tvType = convertView.findViewById(R.id.tv_type);
            contentViewHolder.tvDescription = convertView.findViewById(R.id.tv_description);
            contentViewHolder.tvScore = convertView.findViewById(R.id.tv_score);
            contentViewHolder.tvAddTime = convertView.findViewById(R.id.tv_addTime);
            convertView.setTag(contentViewHolder);
        }

        contentViewHolder.tvType.setText(record.getType());
        contentViewHolder.tvDescription.setText(record.getDescription());
        contentViewHolder.tvScore.setText(record.getScore() + "");
        contentViewHolder.tvAddTime.setText(DateUtil.DateFormat(record.getAddTime()));

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_record, null);
                final EditText etDescription = dialogView.findViewById(R.id.et_description);
                etDescription.setText(record.getDescription());
                final EditText etScore = dialogView.findViewById(R.id.et_score);
                etScore.setText(String.valueOf(record.getScore()));
                final EditText etType = dialogView.findViewById(R.id.et_type);
                etType.setText(record.getType());
                builder.setView(dialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                record.setDescription(etDescription.getText().toString());
                                record.setScore(Integer.valueOf(etScore.getText().toString()));
                                record.setType(etType.getText().toString());
                                record.save();

                                EventBus.getDefault().post(new RecordEvent(record));

                            }
                        })
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                record.delete();
                                EventBus.getDefault().post(new RecordEvent(record));

                            }
                        });
                builder.create().show();

            }
        });

        return convertView;
    }


    private static class HeaderViewHolder {
        TextView dayText;
        TextView yearMonthText;
        TextView tvTodayScore;

    }

    private static class ContentViewHolder {

        TextView tvType;
        TextView tvDescription;
        TextView tvScore;
        TextView tvAddTime;
    }

}
