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
public class SportFragment extends FirstTabFragment {


    protected RadioButton radioBtnFuwocheng;
    protected RadioButton radioBtnShendun;
    protected RadioButton radioBtnPaobu;

    public SportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sport, container, false);
        initView(rootView);
        return rootView;
    }

    protected String getType(RadioGroup radioGroup) {
        String type = "";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_btn_fuwocheng:
                type = Type.Sport.FU_WO_CHENG;
                break;
            case R.id.radio_btn_shendun:
                type = Type.Sport.SHEN_DUN;
                break;
            case R.id.radio_btn_paobu:
                type = Type.Sport.PAO_BU;
                break;
        }
        return type;

    }


    private void initView(View rootView) {
        radioBtnFuwocheng = (RadioButton) rootView.findViewById(R.id.radio_btn_fuwocheng);
        radioBtnShendun = (RadioButton) rootView.findViewById(R.id.radio_btn_shendun);
        radioBtnPaobu = (RadioButton) rootView.findViewById(R.id.radio_btn_paobu);
        radioGroupType = (RadioGroup) rootView.findViewById(R.id.radioGroup_type);
        etDescription = (EditText) rootView.findViewById(R.id.et_description);
        etScore = (EditText) rootView.findViewById(R.id.et_score);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }
}
