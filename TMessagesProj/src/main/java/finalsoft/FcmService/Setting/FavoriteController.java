package finalsoft.FcmService.Setting;

import android.content.Context;

/**
 * Created by Pouya on 6/13/2016.
 */
public class FavoriteController {
    private static FavoriteController fav;
    private static Context thiscontext;

    public static void addToFavor(Long id){
        String m=Setting.getFavorList();
        m=m+"-"+String.valueOf(id);
        Setting.setFavorList(m);
    }
    public static void addToFavor(String user){
        String m=Setting.getFavorList();
        m=m+"-"+String.valueOf(user);
        Setting.setFavorList(m);
    }
    public static Boolean isFavor(String user){
        boolean m=Setting.getFavorList().toLowerCase().contains(user);
        return m;
    }
    public static Boolean isFavor(Long id){
        boolean m=Setting.getFavorList().toLowerCase().contains(String.valueOf(id));
        return m;
    }

    public static boolean IsFaver(Long aLong) {
        return isFavor(aLong);
    }

    public static void RemoveFromFavor(long selectedDialog) {
        String m=Setting.getFavorList();
        m=m.replace(String.valueOf(selectedDialog),"");
        Setting.setFavorList(m);
    }
}
