package finalsoft.Theming.online;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.telegram.messenger.AndroidUtilities;


public class theme implements Parcelable{

    private  String name , description,thumb1 ,thumb2 , thumb3 , xmllink , imagelink ,xmldata ;

    int color = AndroidUtilities.defColor;

    public theme(){

    }

    public theme(String name, String description, String thumb1, String thumb2, String thumb3, String xmllink, String imagelink , String xmldata) {
//        Log.i("finalsoft","theme");
        this.name = name;
        this.description = description;
        this.thumb1 = thumb1;
        this.thumb2 = thumb2;
        this.thumb3 = thumb3;
        this.xmllink = xmllink;
        this.imagelink = imagelink;
        this.xmldata = xmldata ;

    }


    protected theme(Parcel in) {
        name = in.readString();
        description = in.readString();
        thumb1 = in.readString();
        thumb2 = in.readString();
        thumb3 = in.readString();
        xmllink = in.readString();
        imagelink = in.readString();
        xmldata = in.readString();
        color = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }

    public String getThumb2() {
        return thumb2;
    }

    public void setThumb2(String thumb2) {
        this.thumb2 = thumb2;
    }

    public String getThumb3() {
        return thumb3;
    }

    public void setThumb3(String thumb3) {
        this.thumb3 = thumb3;
    }

    public String getXmllink() {
        return xmllink;
    }

    public void setXmllink(String xmllink) {
        this.xmllink = xmllink;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getXmldata() {
        return xmldata;
    }

    public void setXmldata(String xmldata) {
        this.xmldata = xmldata;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static Creator<theme> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<theme> CREATOR = new Creator<theme>() {
        @Override
        public theme createFromParcel(Parcel in) {
            return new theme(in);
        }

        @Override
        public theme[] newArray(int size) {
            return new theme[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(thumb1);
        parcel.writeString(thumb2);
        parcel.writeString(thumb3);
        parcel.writeString(xmllink);
        parcel.writeString(imagelink);
        parcel.writeString(xmldata);
        parcel.writeInt(color);
    }
}
