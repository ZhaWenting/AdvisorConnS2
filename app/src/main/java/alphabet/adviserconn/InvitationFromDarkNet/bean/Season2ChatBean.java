package alphabet.adviserconn.InvitationFromDarkNet.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ALPHABET on 2016/10/12.
 */

public class Season2ChatBean implements Parcelable {
    /**
     * type : player_select
     * player_select : ["重金求子？","你难道是？"]
     * hero_answer : ["$season2_day1_branch1$","season2_day1_branch2"]
     */

    private String type;
    private List<String> player_select;
    private List<String> hero_answer;
    private String player_text;
    private List<String> player_detail;
    private String hero_text;
    private String hero_image;
    private String interval;
    private String content;
    private String selection_num;
    private String return_chapter;
    private String player_single;
    private String wait_flag;
    private String restart_flag;
    private String player_single_branch;
    private List<String> hero_answer_multi;
    private String correct_answer;
    private List<String> player_select_multi;
    private String show_toast;
    private String hero_text_linked;
    private String linked_word;
    private String toast_pic;
    private String next_day;
    private String date;
    private String start_flag;
    private String ending;


    public Season2ChatBean() {
    }

    protected Season2ChatBean(Parcel in) {
        type = in.readString();
        player_select = in.createStringArrayList();
        hero_answer = in.createStringArrayList();
        player_text = in.readString();
        player_detail = in.createStringArrayList();
        hero_text = in.readString();
        hero_image = in.readString();
        interval = in.readString();
        content = in.readString();
        selection_num = in.readString();
        return_chapter = in.readString();
        player_single = in.readString();
        wait_flag = in.readString();
        restart_flag = in.readString();
        player_single_branch = in.readString();
        hero_answer_multi = in.createStringArrayList();
        correct_answer = in.readString();
        player_select_multi = in.createStringArrayList();
        show_toast = in.readString();
        hero_text_linked = in.readString();
        linked_word = in.readString();
        toast_pic = in.readString();
        next_day = in.readString();
        date = in.readString();
        start_flag = in.readString();
        ending = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeStringList(player_select);
        dest.writeStringList(hero_answer);
        dest.writeString(player_text);
        dest.writeStringList(player_detail);
        dest.writeString(hero_text);
        dest.writeString(hero_image);
        dest.writeString(interval);
        dest.writeString(content);
        dest.writeString(selection_num);
        dest.writeString(return_chapter);
        dest.writeString(player_single);
        dest.writeString(this.wait_flag);
        dest.writeString(this.restart_flag);
        dest.writeString(player_single_branch);
        dest.writeStringList(hero_answer_multi);
        dest.writeString(correct_answer);
        dest.writeStringList(player_select_multi);
        dest.writeString(show_toast);
        dest.writeString(hero_text_linked);
        dest.writeString(linked_word);
        dest.writeString(toast_pic);
        dest.writeString(next_day);
        dest.writeString(date);
        dest.writeString(start_flag);
        dest.writeString(ending);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Season2ChatBean> CREATOR = new Creator<Season2ChatBean>() {
        @Override
        public Season2ChatBean createFromParcel(Parcel in) {
            return new Season2ChatBean(in);
        }

        @Override
        public Season2ChatBean[] newArray(int size) {
            return new Season2ChatBean[size];
        }
    };


    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getStart_flag() {
        return start_flag;
    }

    public void setStart_flag(String start_flag) {
        this.start_flag = start_flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNext_day() {
        return next_day;
    }

    public void setNext_day(String next_day) {
        this.next_day = next_day;
    }

    public String getToast_pic() {
        return toast_pic;
    }

    public void setToast_pic(String toast_pic) {
        this.toast_pic = toast_pic;
    }

    public String getLinked_word() {
        return linked_word;
    }

    public void setLinked_word(String linked_word) {
        this.linked_word = linked_word;
    }

    public String getHero_text_linked() {
        return hero_text_linked;
    }

    public void setHero_text_linked(String hero_text_linked) {
        this.hero_text_linked = hero_text_linked;
    }

    public String getShow_toast() {
        return show_toast;
    }

    public void setShow_toast(String show_toast) {
        this.show_toast = show_toast;
    }

    public List<String> getPlayer_select_multi() {
        return player_select_multi;
    }

    public void setPlayer_select_multi(List<String> player_select_multi) {
        this.player_select_multi = player_select_multi;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public List<String> getHero_answer_multi() {
        return hero_answer_multi;
    }

    public void setHero_answer_multi(List<String> hero_answer_multi) {
        this.hero_answer_multi = hero_answer_multi;
    }

    public String getPlayer_single_branch() {
        return player_single_branch;
    }

    public void setPlayer_single_branch(String player_single_branch) {
        this.player_single_branch = player_single_branch;
    }

    public String getRestart_flag() {
        return restart_flag;
    }

    public void setRestart_flag(String restart_flag) {
        this.restart_flag = restart_flag;
    }

    public String getWait_flag() {
        return wait_flag;
    }

    public void setWait_flag(String wait_flag) {
        this.wait_flag = wait_flag;
    }

    public String getPlayer_single() {
        return player_single;
    }

    public void setPlayer_single(String player_single) {
        this.player_single = player_single;
    }

    public String getReturn_chapter() {
        return return_chapter;
    }

    public void setReturn_chapter(String return_chapter) {
        this.return_chapter = return_chapter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPlayer_select() {
        return player_select;
    }

    public void setPlayer_select(List<String> player_select) {
        this.player_select = player_select;
    }

    public List<String> getHero_answer() {
        return hero_answer;
    }

    public void setHero_answer(List<String> hero_answer) {
        this.hero_answer = hero_answer;
    }

    public String getPlayer_text() {
        return player_text;
    }

    public void setPlayer_text(String player_text) {
        this.player_text = player_text;
    }

    public List<String> getPlayer_detail() {
        return player_detail;
    }

    public void setPlayer_detail(List<String> player_detail) {
        this.player_detail = player_detail;
    }

    public String getHero_text() {
        return hero_text;
    }

    public void setHero_text(String hero_text) {
        this.hero_text = hero_text;
    }

    public String getHero_image() {
        return hero_image;
    }

    public void setHero_image(String hero_image) {
        this.hero_image = hero_image;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSelection_num() {
        return selection_num;
    }

    public void setSelection_num(String selection_num) {
        this.selection_num = selection_num;
    }


}
