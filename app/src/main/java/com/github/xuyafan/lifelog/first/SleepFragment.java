package com.github.xuyafan.lifelog.first;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.xuyafan.lifelog.R;
import com.github.xuyafan.lifelog.constant.Type;
import com.github.xuyafan.lifelog.first.FirstTabFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends FirstTabFragment {



    protected RadioButton radioBtnSleep;
    protected RadioButton radioBtnNap;


    public SleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sleep, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        radioBtnSleep = (RadioButton) rootView.findViewById(R.id.radio_btn_sleep);
        radioBtnNap = (RadioButton) rootView.findViewById(R.id.radio_btn_nap);
        radioGroupType = (RadioGroup) rootView.findViewById(R.id.radioGroup_type);
        etDescription = (EditText) rootView.findViewById(R.id.et_description);
        etScore = (EditText) rootView.findViewById(R.id.et_score);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    protected String getType(RadioGroup radioGroup) {
        String type ="";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_btn_sleep:
                type = Type.Sleep.SLEEP;
                break;
            case R.id.radio_btn_nap:
                type = Type.Sleep.nap;
                break;
        }
        return type;
    }
}
