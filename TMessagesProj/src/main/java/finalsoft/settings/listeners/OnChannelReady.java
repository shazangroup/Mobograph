package finalsoft.settings.listeners;


import finalsoft.settings.Channel;

/**
 * Created by Masoud on 6/1/2016.
 */
public interface OnChannelReady {

    void onReady(Channel channel, boolean isOK);
}
