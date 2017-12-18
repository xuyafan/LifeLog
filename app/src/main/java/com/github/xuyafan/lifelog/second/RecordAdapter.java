package com.github.xuyafan.lifelog.second;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.data.Record;
import com.github.xuyafan.lifelog.utils.DateUtil;

import java.util.List;

/**
 * Created by xuyafan on 2017/12/17.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<Record> recordList;
    private Context context;

    public RecordAdapter(Context context, List<Record> recordList) {
        this.recordList = recordList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击分数", Toast.LENGTH_SHORT).show();

            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                final int position = viewHolder.getAdapterPosition();
                final Record record = recordList.get(position);
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

                            }
                        })
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                record.delete();
                                recordList.remove(record);

                            }
                        });
                builder.create().show();
                return true;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Record record = recordList.get(position);

        holder.tvType.setText(record.getType());
        holder.tvDescription.setText(record.getDescription());
        holder.tvScore.setText(String.valueOf(record.getScore()));
        holder.tvAddTime.setText(DateUtil.DateFormat(record.getAddTime()));

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }


    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView tvType;
        TextView tvDescription;
        TextView tvScore;
        TextView tvAddTime;

        private ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvType = itemView.findViewById(R.id.tv_type);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvAddTime = itemView.findViewById(R.id.tv_addTime);
        }

    }


}