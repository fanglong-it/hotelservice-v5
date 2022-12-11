package fiveman.hotelservice.xmlservice;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "settings")
public class Setting {
    private String wellcome_content;
    private String birthday_content;
    private String wish_content;
    private String mp3_content;
    public Setting() {
    }
    public Setting(String wellcome_content, String birthday_content, String wish_content, String mp3_content) {
        this.wellcome_content = wellcome_content;
        this.birthday_content = birthday_content;
        this.wish_content = wish_content;
        this.mp3_content = mp3_content;
    }
    public String getWellcome_content() {
        return wellcome_content;
    }
    public void setWellcome_content(String wellcome_content) {
        this.wellcome_content = wellcome_content;
    }
    public String getBirthday_content() {
        return birthday_content;
    }
    public void setBirthday_content(String birthday_content) {
        this.birthday_content = birthday_content;
    }
    public String getWish_content() {
        return wish_content;
    }
    public void setWish_content(String wish_content) {
        this.wish_content = wish_content;
    }
    public String getMp3_content() {
        return mp3_content;
    }
    public void setMp3_content(String mp3_content) {
        this.mp3_content = mp3_content;
    }
    @Override
    public String toString() {
        return "Setting [wellcome_content=" + wellcome_content + ", birthday_content=" + birthday_content
                + ", wish_content=" + wish_content + ", mp3_content=" + mp3_content + "]";
    }

    
    
}
