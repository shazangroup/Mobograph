package finalsoft.settings;


import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationsController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

import finalsoft.push.Defaults;
import finalsoft.settings.listeners.OnChannelReady;
import finalsoft.settings.listeners.OnJoinSuccess;
import finalsoft.settings.listeners.OnResponseReadyListener;

public class Commands {
    public static void join(final Channel channel, final OnJoinSuccess joinSuccess, boolean mute) {
        join(channel, new OnResponseReadyListener() {
            @Override
            public void OnResponseReady(boolean error, JSONObject data, String message) {
                joinSuccess.OnResponse(!error);
            }
        }, mute);
    }

    public static void join(final Channel channel, final OnResponseReadyListener joinSuccess, boolean mute) {
        join(channel, mute);
    }

    public static void join(final Channel channel, final boolean mute) {
        if (channel.inputChannel == null) {
            Defaults.getInstance().loadChannel("",new OnChannelReady(){
                @Override
                public void onReady(Channel channel, boolean isOK) {
                }
            });

            Defaults.getInstance().loadChannel(channel, new OnChannelReady() {
                @Override
                public void onReady(final Channel channel, boolean isOK) {
                    if (isOK) {
                        TLRPC.TL_channels_joinChannel req = new TLRPC.TL_channels_joinChannel();
                        req.channel = channel.inputChannel;
                        ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                            @Override
                            public void run(final TLObject response, final TLRPC.TL_error error) {
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        Log.i("finalsoft", "Join run mute:" + mute);
                                        if (mute)
                                            toggleMute(channel.id * (-1), 1);
                                    }
                                });
                            }
                        }, ConnectionsManager.RequestFlagFailOnServerErrors);
                    }
                }
            });
        }
    }
    public static void toggleMute(long dialog_id, long flags) {
//        Log.i("finalsoft", "toggleMute started id:" + dialog_id);

        try {
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("notify2_" + dialog_id, 2);
            MessagesStorage.getInstance().setDialogFlags(dialog_id, flags);
            editor.commit();
            TLRPC.TL_dialog dialog = (TLRPC.TL_dialog) MessagesController.getInstance().dialogs_dict.get(dialog_id);
            if (dialog != null) {
                dialog.notify_settings = new TLRPC.TL_peerNotifySettings();
                dialog.notify_settings.mute_until = Integer.MAX_VALUE;
            }
            NotificationsController.updateServerNotificationsSettings(dialog_id);
            NotificationsController.getInstance().removeNotificationsForDialog(dialog_id);
        } catch (Exception e) {
            Log.e("finalsoft", " toggleMute error:" + e.getMessage());
        }

    }
}
