package alphabet.adviserconn.InvitationFromDarkNet.utils;


import alphabet.adviserconn.R;

/**
 * Created by user on 2018/4/14.
 */

public class ConvertString {
    public static int convert(String str){
        int resId = 0;
        switch (str){
            case "$season2_day1$":
                resId = R.raw.season2_day1;
                break;
            case "$season2_day1_b1$":
                resId = R.raw.season2_day1_b1;
                break;
            case "$season2_day1_b2$":
                resId = R.raw.season2_day1_b2;
                break;
            case "$season2_day2$":
                resId = R.raw.season2_day2;
                break;
            case "$season2_day2_evening$":
                resId = R.raw.season2_day2_evening;
                break;

            case "$season2_day2_b1$":
                resId = R.raw.season2_day2_b1;
                break;
            case "$season2_day2_b2$":
                resId = R.raw.season2_day2_b2;
                break;
            case "$season2_day2_b3$":
                resId = R.raw.season2_day2_b3;
                break;
            case "$season2_day2_b4$":
                resId = R.raw.season2_day2_b4;
                break;

            case "$season2_day2_virus$":
                resId = R.raw.season2_day2_virus;
                break;
            case "$season2_day2_virus_b1$":
                resId = R.raw.season2_day2_virus_b1;
                break;
            case "$season2_day2_virus_b2$":
                resId = R.raw.season2_day2_virus_b2;
                break;
            case "$season2_day2_virus_b3$":
                resId = R.raw.season2_day2_virus_b3;
                break;
            case "$season2_day2_virus_b4$":
                resId = R.raw.season2_day2_virus_b4;
                break;
            case "R.mipmap.chat1rar":
                resId = R.mipmap.chat1rar;
                break;
            case "R.mipmap.girl_content":
                resId = R.mipmap.girl_content;
                break;
            case "R.mipmap.chat1psw":
                resId = R.mipmap.chat1psw;
                break;
            case "R.mipmap.chat_edit_send":
                resId = R.mipmap.chat_edit_send;
                break;
            case "R.mipmap.reload_time":
                resId = R.mipmap.reload_time;
                break;
            case "R.mipmap.explorer_icon_s2":
                resId = R.mipmap.explorer_icon_s2;
                break;
            case "R.mipmap.doll_yellow":
                resId = R.mipmap.doll_yellow;
                break;

            case "R.mipmap.xea_avatar_s2":
                resId = R.mipmap.xea_avatar_s2;
                break;
        }
        return resId;
    }

}
