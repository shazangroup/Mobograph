package finalsoft.Theming.online;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.finalsoft.messenger.R;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import finalsoft.Cashing;
import finalsoft.constant;


public class ThemStore extends BaseFragment {


    private ListView listView;
    List<theme> themes = new ArrayList<>();
    themAdapter listAdapter;
    ProgressDialog progressDialog;
    private final int MENU_RESET = 1;

    boolean reseting = false ;


    @Override
    public boolean onFragmentCreate() {
        super.onFragmentCreate();


        return true;
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    @Override
    public View createView(final Context context) {
        actionBar.setBackgroundColor(Theme.ACTION_BAR_MEDIA_PICKER_COLOR);
        actionBar.setItemsBackgroundColor(Theme.ACTION_BAR_PICKER_SELECTOR_COLOR);
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle(LocaleController.getString("ThemeStore", R.string.ThemeStore));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }else if (id == MENU_RESET) {
                    ResetTheme();
                }
            }
        });


        fragmentView = new FrameLayout(context);
        fragmentView.setLayoutParams(new FrameLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        ActionBarMenu menu = actionBar.createMenu();
        menu.addItemWithWidth(MENU_RESET, R.drawable.ic_refresh, AndroidUtilities.dp(56));


        FrameLayout frameLayout = (FrameLayout) fragmentView;


        listView = new ListView(context);

        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        listView.setBackgroundColor(preferences.getInt("prefBGColor", 0xffffffff));
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setVerticalScrollBarEnabled(false);
        int def = preferences.getInt("themeColor", AndroidUtilities.defColor);
        int hColor = preferences.getInt("prefHeaderColor", def);
        AndroidUtilities.setListViewEdgeEffectColor(listView, /*AvatarDrawable.getProfileBackColorForId(5)*/ hColor);
        frameLayout.addView(listView);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) listView.getLayoutParams();
        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.TOP;
        listView.setLayoutParams(layoutParams);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(context.getString(R.string.Loading));

        if (Cashing.themes.size() < 1) {
            progressDialog.show();
            new getThemes(context, constant.GET_THEMES).execute();
        } else {
            themes = Cashing.themes;
            refreshDisplay(context);
        }

        return fragmentView;
    }


    private void ResetTheme(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
        builder.setMessage(LocaleController.getString("AreYouSure", R.string.AreYouSure));
        builder.setTitle(LocaleController.getString("ResetThemeSettings", R.string.ResetThemeSettings));
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (reseting) {
                    return;
                }
                reseting = true;
                AndroidUtilities.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        reseting = false;
                        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
                        SharedPreferences.Editor editor = themePrefs.edit();
                        editor.clear();
                        editor.commit();
                        //Stock Background
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        editor = preferences.edit();
                        editor.putInt("selectedBackground", 1000001);
                        editor.putInt("selectedColor", 0);
                        editor.commit();
                        File toFile = new File(ApplicationLoader.applicationContext.getFilesDir(), "wallpaper.jpg");
                        if (toFile.exists()) {
                            toFile.delete();
                        }
                        if (getParentActivity() != null) {
                            Toast toast = Toast.makeText(getParentActivity(), LocaleController.getString("ResetThemeToastText", R.string.ResetThemeToastText), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                AndroidUtilities.needRestart = true;
                AndroidUtilities.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getParentActivity() != null) {
                            Toast toast = Toast.makeText(getParentActivity(), LocaleController.getString("AppWillRestart", R.string.AppWillRestart), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());

    }

    private void refreshDisplay(Context context) {

        // get data from the table by the ListAdapter
        progressDialog.dismiss();
        if (themes != null) {
            listAdapter = new themAdapter(context, R.layout.theme_row, themes);
            listView.setAdapter(listAdapter);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        updateTheme();
        if (constant.AnalyticInitialized)
            ApplicationLoader.getInstance().trackScreenView("Theme Store");
    }


    class getThemes extends AsyncTask<String, Void, String> {
        Context c;
        private String MYURL = "";


        public getThemes(Context c, String url) {
            this.c = c;
            this.MYURL = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(MYURL);
                httpGet.addHeader("Cache-Control", "no-cache");
                HttpResponse response = httpClient.execute(httpGet);

                HttpEntity entity = response.getEntity();
                String s = IOUtils.toString(entity.getContent(), "utf-8");

                themes = new ArrayList<>();

                JSONObject json = null;
                JSONArray items = null;
                try {
                    json = new JSONObject(s);
                    items = json.getJSONArray("themes");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject arr = items.getJSONObject(i);
                        theme item = new theme(arr.getString("name"),
                                arr.getString("description"),
                                arr.getString("thumb1"),
                                arr.getString("thumb2"),
                                arr.getString("thumb3"),
                                arr.getString("xmllink"),
                                arr.getString("imagelink"),
                                arr.getString("xmldata"));
                        themes.add(item);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {

            }

            return "success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            refreshDisplay(c);


        }


    }


    private void updateTheme() {
//        Log.i("finalsoft","updateTheme");
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);
        actionBar.setBackgroundColor(themePrefs.getInt("prefHeaderColor", def));
        actionBar.setTitleColor(themePrefs.getInt("prefHeaderTitleColor", 0xffffffff));

        Drawable back = getParentActivity().getResources().getDrawable(R.drawable.ic_ab_back);
        back.setColorFilter(themePrefs.getInt("prefHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
        actionBar.setBackButtonDrawable(back);
    }


}
