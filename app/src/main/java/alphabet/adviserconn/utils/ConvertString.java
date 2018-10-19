package alphabet.adviserconn.utils;


import alphabet.adviserconn.R;

/**
 * Created by user on 2018/4/14.
 */

public class ConvertString {
    public static int convert(String str){
        int resId = 0;
        switch (str){


            case "$chapter1$":
                resId = R.raw.chapter1;
                break;
            case "$chapter2$":
                resId = R.raw.chapter2;
                break;
            case "$chapter3$":
                resId = R.raw.chapter3;
                break;
            case "$chapter4$":
                resId = R.raw.chapter4;
                break;
            case "$branch1_1$":
                resId = R.raw.branch1_1;
                break;
            case "$branch1_2$":
                resId = R.raw.branch1_2;
                break;
            case "$branch2_1$":
                resId = R.raw.branch2_1;
                break;
            case "$branch2_2$":
                resId = R.raw.branch2_2;
                break;
            case "$ending_amnesia$":
                resId = R.raw.ending_amnesia;
                break;
            case "$branch3_2$":
                resId = R.raw.branch3_2;
                break;
            case "$branch4_1$":
                resId = R.raw.branch4_1;
                break;
            case "$branch4_2$":
                resId = R.raw.branch4_2;
                break;
            case "$ending_cutoff$":
                resId = R.raw.ending_cutoff;
                break;
            case "$branch6_1$":
                resId = R.raw.branch6_1;
                break;
            case "$ending_cutoff2$":
                resId = R.raw.ending_cutoff2;
                break;
            case "$branch8_1$":
                resId = R.raw.branch8_1;
                break;
            case "$branch9_1$":
                resId = R.raw.branch9_1;
                break;
            case "$branch10_1$":
                resId = R.raw.branch10_1;
                break;
            case "$branch10_2$":
                resId = R.raw.branch10_2;
                break;
            case "$branch11_1$":
                resId = R.raw.branch11_1;
                break;
            case "$branch11_2$":
                resId = R.raw.branch11_2;
                break;
            case "$branch12_1$":
                resId = R.raw.branch12_1;
                break;

            case "$branch13_1$":
                resId = R.raw.branch13_1;
                break;
            case "$ending_cutoff3$":
                resId = R.raw.ending_cutoff3;
                break;
            case "$branch14_1$":
                resId = R.raw.branch14_1;
                break;
            case "$branch14_2$":
                resId = R.raw.branch14_2;
                break;
            case "$branch15_1$":
                resId = R.raw.branch15_1;
                break;
            case "$branch15_2$":
                resId = R.raw.branch15_2;
                break;
            case "$branch16_1$":
                resId = R.raw.branch16_1;
                break;
            case "$branch16_2$":
                resId = R.raw.branch16_2;
                break;
            case "$branch17_1$":
                resId = R.raw.branch17_1;
                break;
            case "$branch_secret1$":
                resId = R.raw.branch_secret1;
                break;
            case "$branch_secret2$":
                resId = R.raw.branch_secret2;
                break;
            case "$ending_chaos$":
                resId = R.raw.ending_chaos;
                break;
            case "$ending_infinity$":
                resId = R.raw.ending_infinity;
                break;
            case "$ending_rebirth$":
                resId = R.raw.ending_rebirth;
                break;
            case "$ending_wakeup$":
                resId = R.raw.ending_wakeup;
                break;
        }
        return resId;
    }

}
