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


public class WasteFragment extends FirstTabFragment {


    protected RadioButton radioBtnH;
    protected RadioButton radioBtnLive;
    protected RadioButton radioBtnFiction;
    protected RadioButton radioBtnGame;
    protected RadioButton radioBtnOthers;

    public WasteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_waste, container, false);
        initView(rootView);
        return rootView;
    }


    @Override
    protected String getType(RadioGroup radioGroup) {
        String type = "";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_btn_h:
                type = Type.Waste.H;
                break;
            case R.id.radio_btn_live:
                type = Type.Waste.LIVE;
                break;
            case R.id.radio_btn_fiction:
                type = Type.Waste.FICTION;
                break;
            case R.id.radio_btn_game:
                type = Type.Waste.GAME;
                break;
            case R.id.radio_btn_others:
                type = Type.Waste.OTHERS;
                break;
        }
        return type;

    }

    private void initView(View rootView) {
        radioBtnH = (RadioButton) rootView.findViewById(R.id.radio_btn_h);
        radioBtnLive = (RadioButton) rootView.findViewById(R.id.radio_btn_live);
        radioBtnFiction = (RadioButton) rootView.findViewById(R.id.radio_btn_fiction);
        radioBtnGame = (RadioButton) rootView.findViewById(R.id.radio_btn_game);
        radioBtnOthers = (RadioButton) rootView.findViewById(R.id.radio_btn_others);
        radioGroupType = (RadioGroup) rootView.findViewById(R.id.radioGroup_type);
        etDescription = (EditText) rootView.findViewById(R.id.et_description);
        etScore = (EditText) rootView.findViewById(R.id.et_score);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }
}
