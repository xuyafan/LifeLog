package com.github.xuyafan.lifelog.calendarviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.xuyafan.lifelog.R;
import com.test.tudou.library.model.CalendarDay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WeekContentFragment extends Fragment {
    @BindView(R.id.textView)
    TextView textView;
    Unbinder unbinder;
    private CalendarDay mCalendarDay;

    public static WeekContentFragment newInstance(CalendarDay calendarDay) {
        WeekContentFragment weekContentFragment = new WeekContentFragment();
        weekContentFragment.mCalendarDay = calendarDay;
        return weekContentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mCalendarDay != null) {
            textView.setText(mCalendarDay.getDayString());
        } else {
            textView.setText(" no calendar day");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.textView)
    public void onViewClicked() {
        Toast.makeText(getContext(), mCalendarDay.getDayString(), Toast.LENGTH_SHORT).show();
    }
}
