package com.github.xuyafan.lifelog.utils;

import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by xyf18 on 2017/12/13.
 */

public class CheckUtil {

    public static boolean isRadioGroupChecked(RadioGroup radioGroup) {

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radiobtn = (RadioButton) radioGroup.getChildAt(i);
            if (radiobtn.isChecked()) {
                return true;
            }
        }
        return false;
    }
}
