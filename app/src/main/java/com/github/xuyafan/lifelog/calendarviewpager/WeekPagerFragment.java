package com.github.xuyafan.lifelog.calendarviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.xuyafan.lifelog.R;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import com.test.tudou.library.weekpager.adapter.WeekPagerAdapter;
import com.test.tudou.library.weekpager.adapter.WeekViewAdapter;
import com.test.tudou.library.weekpager.view.WeekDayViewPager;
import com.test.tudou.library.weekpager.view.WeekRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekPagerFragment extends Fragment {


    @BindView(R.id.week_view)
    WeekRecyclerView weekView;
    @BindView(R.id.text_label)
    TextView textLabel;
    @BindView(R.id.content_viewPager)
    WeekDayViewPager contentViewPager;
    Unbinder unbinder;

    private WeekContentPagerAdapter weekContentPagerAdapter;
    private WeekViewAdapter weekViewAdapter;

    public WeekPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_pager, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpPager();
        setUpData();
        return view;
    }


    private void setUpPager() {
        weekContentPagerAdapter = new WeekContentPagerAdapter(getFragmentManager());
        contentViewPager.setOffscreenPageLimit(1);
        contentViewPager.setAdapter(weekContentPagerAdapter);
        contentViewPager.setWeekRecyclerView(weekView);
        contentViewPager.setDayScrollListener(new WeekDayViewPager.DayScrollListener() {
            @Override
            public void onDayPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                textLabel.setText(weekContentPagerAdapter.getDatas().get(position).getDayString());

            }

            @Override
            public void onDayPageSelected(int position) {

            }

            @Override
            public void onDayPageScrollStateChanged(int state) {

            }
        });

        weekViewAdapter = new WeekViewAdapter(getContext(), contentViewPager);
        weekViewAdapter.setTextNormalColor(getResources().getColor(android.R.color.darker_gray));
        weekView.setAdapter(weekViewAdapter);
    }

    private void setUpData() {
        ArrayList<CalendarDay> reachAbleDays = new ArrayList<>();
        reachAbleDays.add(new CalendarDay(2015, 5, 1));
        reachAbleDays.add(new CalendarDay(2015, 5, 4));
        reachAbleDays.add(new CalendarDay(2015, 5, 6));
        reachAbleDays.add(new CalendarDay(2015, 5, 20));
        weekViewAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1), reachAbleDays);
        weekContentPagerAdapter.setData(reachAbleDays.get(0), reachAbleDays.get(reachAbleDays.size() - 1));
        contentViewPager.setCurrentPosition(DayUtils.calculateDayPosition(weekViewAdapter.getFirstShowDay(), new CalendarDay(2015, 5, 20)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public class WeekContentPagerAdapter extends WeekPagerAdapter {

        public WeekContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createFragmentPager(int position) {
            return WeekContentFragment.newInstance(mDays.get(position));
        }
    }


}



