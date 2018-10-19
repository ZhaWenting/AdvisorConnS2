package alphabet.adviserconn.ImaginarySpace;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ALPHABET on 2016/10/12.
 */

public class ChatBean implements Parcelable {


    /**
     * type : left_text
     * hero_text : 我遇到了一点问题
     */



    private String type;
    private String hero_text;
    /**
     * player_option1 : 重金求子？
     * hero_answer1 : 我是雄的！我不要你的钱！
     * player_option2 : 发生了什么？
     * hero_answer2 : 说出来你可能不信
     */

    private String player_option1;
    private String hero_answer1;
    private String player_option2;
    private String hero_answer2;
    /**
     * hero_voice : 那个，不好意思，打扰一下
     */

    private String hero_voice;
    /**
     * other_voice : 你忘记了很重要的事，这把钥匙就是答案
     */

    private String other_voice;
    private String heroin_words;

    /**
     * show_status : 对方正在解决生理问题
     */

    private String show_status;
    /**
     * interval_time : 10000，5000，1000
     */

    private String interval_time;
    /**
     * show_tips : 亮度显示了与目标间的距离
     */

    private String show_tips;
    /**
     * player_text : 车没有撞到你？
     */
    private String next_chapter;

    private String player_text;

    private String answer;

    private String content;

    private String chapter_name;

    private String ending_name;
    private String others;
    private String clue_tag;




    public String getClue_tag() {
        return clue_tag;
    }

    public void setClue_tag(String clue_tag) {
        this.clue_tag = clue_tag;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getNext_chapter() {
        return next_chapter;
    }

    public void setNext_chapter(String next_chapter) {
        this.next_chapter = next_chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public String getHeroin_words() {
        return heroin_words;
    }

    public void setHeroin_words(String heroin_words) {
        this.heroin_words = heroin_words;
    }

    public String getEnding_name() {
        return ending_name;
    }

    public void setEnding_name(String ending_name) {
        this.ending_name = ending_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHero_text() {
        return hero_text;
    }

    public void setHero_text(String hero_text) {
        this.hero_text = hero_text;
    }

    public String getPlayer_option1() {
        return player_option1;
    }

    public void setPlayer_option1(String player_option1) {
        this.player_option1 = player_option1;
    }

    public String getHero_answer1() {
        return hero_answer1;
    }

    public void setHero_answer1(String hero_answer1) {
        this.hero_answer1 = hero_answer1;
    }

    public String getPlayer_option2() {
        return player_option2;
    }

    public void setPlayer_option2(String player_option2) {
        this.player_option2 = player_option2;
    }

    public String getHero_answer2() {
        return hero_answer2;
    }

    public void setHero_answer2(String hero_answer2) {
        this.hero_answer2 = hero_answer2;
    }

    public String getHero_voice() {
        return hero_voice;
    }

    public void setHero_voice(String hero_voice) {
        this.hero_voice = hero_voice;
    }

    public String getOther_voice() {
        return other_voice;
    }

    public void setOther_voice(String other_voice) {
        this.other_voice = other_voice;
    }

    public String getShow_status() {
        return show_status;
    }

    public void setShow_status(String show_status) {
        this.show_status = show_status;
    }

    public String getInterval_time() {
        return interval_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }

    public String getShow_tips() {
        return show_tips;
    }

    public void setShow_tips(String show_tips) {
        this.show_tips = show_tips;
    }

    public String getPlayer_text() {
        return player_text;
    }

    public void setPlayer_text(String player_text) {
        this.player_text = player_text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.hero_text);
        dest.writeString(this.player_option1);
        dest.writeString(this.hero_answer1);
        dest.writeString(this.player_option2);
        dest.writeString(this.hero_answer2);
        dest.writeString(this.hero_voice);
        dest.writeString(this.other_voice);
        dest.writeString(this.heroin_words);
        dest.writeString(this.show_status);
        dest.writeString(this.interval_time);
        dest.writeString(this.show_tips);
        dest.writeString(this.next_chapter);
        dest.writeString(this.player_text);
        dest.writeString(this.answer);
        dest.writeString(this.content);
        dest.writeString(this.chapter_name);
        dest.writeString(this.ending_name);
        dest.writeString(this.others);
        dest.writeString(this.clue_tag);

    }

    public ChatBean() {
    }

    protected ChatBean(Parcel in) {
        this.type = in.readString();
        this.hero_text = in.readString();
        this.player_option1 = in.readString();
        this.hero_answer1 = in.readString();
        this.player_option2 = in.readString();
        this.hero_answer2 = in.readString();
        this.hero_voice = in.readString();
        this.other_voice = in.readString();
        this.heroin_words = in.readString();
        this.show_status = in.readString();
        this.interval_time = in.readString();
        this.show_tips = in.readString();
        this.next_chapter = in.readString();
        this.player_text = in.readString();
        this.answer = in.readString();
        this.content = in.readString();
        this.chapter_name = in.readString();
        this.ending_name = in.readString();
        this.others = in.readString();
        this.clue_tag = in.readString();

    }

    public static final Parcelable.Creator<ChatBean> CREATOR = new Parcelable.Creator<ChatBean>() {
        @Override
        public ChatBean createFromParcel(Parcel source) {
            return new ChatBean(source);
        }

        @Override
        public ChatBean[] newArray(int size) {
            return new ChatBean[size];
        }
    };
}
