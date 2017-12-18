package com.github.xuyafan.lifelog.first;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.event.RecordEvent;
import com.github.xuyafan.lifelog.data.Record;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FirstTabFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected RadioGroup radioGroupType;
    protected EditText etDescription;
    protected EditText etScore;
    protected Button btnSave;

    public FirstTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first_tab, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            if (TextUtils.isEmpty(etScore.getText())) {
                Toast.makeText(getActivity(), "请输入分数", Toast.LENGTH_SHORT).show();
                return;
            }

            int score = Integer.valueOf(etScore.getText().toString());
            String description = etDescription.getText().toString();
            String type = getType(radioGroupType);
            Record record = new Record(type, description, score);
            Date currentTime = Calendar.getInstance().getTime();
            record.setAddTime(currentTime);
            record.save();

            EventBus.getDefault().post(new RecordEvent(record));

            Toast.makeText(getActivity(), "已保存", Toast.LENGTH_SHORT).show();

            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            etDescription.setText("");
            etScore.setText("");
        }
    }

    private void initView(View rootView) {
        radioGroupType = (RadioGroup) rootView.findViewById(R.id.radioGroup_type);
        etDescription = (EditText) rootView.findViewById(R.id.et_description);
        etScore = (EditText) rootView.findViewById(R.id.et_score);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(FirstTabFragment.this);
    }

    protected abstract String getType(RadioGroup radioGroup) ;
}
