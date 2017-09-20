package finalsoft;

import com.finalsoft.messenger.R;

import java.io.Serializable;


public class constant implements Serializable {


    public static int[] voiceColor2 = new int[]{R.drawable.mic, R.drawable.mic1, R.drawable.mic2, R.drawable.mic3, R.drawable.mic4, R.drawable.mic5};
    //Customized:
    public static final String API_URL = "http://jetgram.ir/application/v1/";
    public static final String BASE_URL = "http://jetgram.ir/application/";
    public static final String IMAGES = "http://jetgram.ir/uploads/channelCovers/";
    public static final String PACKAGE = "com.finalsoft.messenger";
    public static final String GET_THEMES = API_URL + "themes"; // GET
    public static final String THEMES_BASE_URL = BASE_URL + "themes/";
    public static final int REPORT_BOT_ID = 178220800;
    public static boolean AnalyticInitialized = false;
    public static final String SPAMBOT_URL = "https://telegram.me/SpamBot";
    public static final String DEVELOPER_ID = "fereidoonSavadkoohi";
    public static final String OFFICIALCHANNEL_URL = "https://telegram.me/pushadd";
    public static final String OFFICIALCHANNEL_ID = "pushadd";
    public static final String OFFICIAL_ID_URL = "https://telegram.me/sadegh_arfa";
    public static final String WebSite = "https://jetgram.ir";
}

