package alphabet.adviserconn.InvitationFromDarkNet.bean;

/**
 * Created by user on 2018/4/1.
 */

public class MailBean {
    int ImgRes;
    String sender_name;
    String receiver_name;
    String title;
    String content;
    String attachment;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImgRes() {
        return ImgRes;
    }

    public void setImgRes(int imgRes) {
        ImgRes = imgRes;
    }

    public String getSenderName() {
        return sender_name;
    }

    public void setSenderName(String senderName) {
        this.sender_name = senderName;
    }

    public String getReceiverName() {
        return receiver_name;
    }

    public void setReceiverName(String receiverName) {
        this.receiver_name = receiverName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
