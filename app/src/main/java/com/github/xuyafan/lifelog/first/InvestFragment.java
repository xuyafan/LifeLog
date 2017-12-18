package com.github.xuyafan.lifelog.first;

import android.os.Bundle;
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
 * Created by xyf18 on 2017/12/14.
 */

public class InvestFragment extends FirstTabFragment {

    protected RadioButton radioBtnCode;
    protected RadioButton radioBtnRead;
    protected RadioButton radioBtnEnglish;
    protected RadioButton radioBtnOthers;

    public InvestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_invest, container, false);
        initView(rootView);
        return rootView;
    }


    private void initView(View rootView) {

        radioBtnCode = (RadioButton) rootView.findViewById(R.id.radio_btn_code);
        radioBtnRead = (RadioButton) rootView.findViewById(R.id.radio_btn_read);
        radioBtnEnglish = (RadioButton) rootView.findViewById(R.id.radio_btn_english);
        radioBtnOthers = (RadioButton) rootView.findViewById(R.id.radio_btn_others);

        radioGroupType = (RadioGroup) rootView.findViewById(R.id.radioGroup_type);
        etDescription = (EditText) rootView.findViewById(R.id.et_description);
        etScore = (EditText) rootView.findViewById(R.id.et_score);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

    }


    @Override
    protected String getType(RadioGroup radioGroup) {
        String type = "";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_btn_code:
                type = Type.Invest.CODE;
                break;
            case R.id.radio_btn_read:
                type = Type.Invest.READ;
                break;
            case R.id.radio_btn_english:
                type = Type.Invest.ENGLISH;
                break;
            case R.id.radio_btn_others:
                type = Type.Invest.OTHERS;
                break;
        }
        return type;

    }

}
