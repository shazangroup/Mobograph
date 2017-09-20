package finalsoft;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.finalsoft.messenger.R;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;


public class changeCharactersActivity extends BaseFragment {

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
        actionBar.setTitle(LocaleController.getString("zibasazi", R.string.zibasazi));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }
            }
        });


        fragmentView = new FrameLayout(context);
        fragmentView.setLayoutParams(new FrameLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        fragmentView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.editstring_activity, null);
        RadioGroup radioGroup = (RadioGroup) fragmentView.findViewById(R.id.radio_group);
        final RadioButton[] rb = new RadioButton[8];
        editString ed = new editString();
        String str = context.getResources().getString(R.string.test);
        for (int i = 0; i < 8; i++) {
            rb[i] = new RadioButton(context);
            rb[i].setText(ed.getNewString(str, i + 1));
            rb[i].setId(i + 100);
            radioGroup.addView(rb[i]);
        }


        final SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
        int rec_int = preferences.getInt("editStringRadioID", 1);

        radioGroup.check(rec_int);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case 100:
                        preferences.edit().putInt("edit_ch", 1).commit();
                        break;
                    case 101:
                        preferences.edit().putInt("edit_ch", 2).commit();
                        break;
                    case 102:
                        preferences.edit().putInt("edit_ch", 3).commit();
                        break;
                    case 103:
                        preferences.edit().putInt("edit_ch", 4).commit();
                        break;
                    case 104:
                        preferences.edit().putInt("edit_ch", 5).commit();
                        break;
                    case 105:
                        preferences.edit().putInt("edit_ch", 6).commit();
                        break;
                    case 106:
                        preferences.edit().putInt("edit_ch", 7).commit();
                        break;
                    case 107:
                        preferences.edit().putInt("edit_ch", 8).commit();
                        break;
                    case 108:
                        preferences.edit().putInt("edit_ch", 9).commit();
                        break;
                    case 109:
                        preferences.edit().putInt("edit_ch", 10).commit();
                        break;
                }
                preferences.edit().putInt("editStringRadioID", i).commit();
            }
        });


        return fragmentView;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateTheme();
        if (constant.AnalyticInitialized)
            ApplicationLoader.getInstance().trackScreenView("change String Activity");
    }

    private void updateTheme() {
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);
        actionBar.setBackgroundColor(themePrefs.getInt("prefHeaderColor", def));
        actionBar.setTitleColor(themePrefs.getInt("prefHeaderTitleColor", 0xffffffff));

        Drawable back = getParentActivity().getResources().getDrawable(R.drawable.ic_ab_back);
        back.setColorFilter(themePrefs.getInt("prefHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
        actionBar.setBackButtonDrawable(back);
    }


}
