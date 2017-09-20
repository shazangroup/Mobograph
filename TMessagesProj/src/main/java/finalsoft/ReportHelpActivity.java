package finalsoft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.finalsoft.messenger.R;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

import java.io.File;

import static org.telegram.messenger.AndroidUtilities.getTypeface;



public class ReportHelpActivity  extends BaseFragment {

        int SendMail = 1 ;

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
        actionBar.setTitle(LocaleController.getString("ReportProblemHelp", R.string.ReportProblemHelp));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }else if (id == SendMail){
                        TLRPC.User user = MessagesController.getInstance().getUser(Integer.valueOf(UserConfig.getClientUserId()));
                        SendMAil(getParentActivity(), "spam@telegram.org", "SPAM", LocaleController.formatString("ReportSpamEmailContent", R.string.ReportSpamEmailContent, "+" + user.phone, "@" + user.username), null);
                }
            }
        });

        actionBar.createMenu().addItem(SendMail, R.drawable.ic_send_mail);





        fragmentView = new FrameLayout(context);
        fragmentView.setLayoutParams(new FrameLayout.LayoutParams(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));


        fragmentView = new LinearLayout(context);
        ((LinearLayout) fragmentView).setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(context);
        ((LinearLayout) fragmentView).addView(scrollView, LayoutHelper.createLinear(-1, -1));
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail1", R.string.ReportProblemHelpDetail1)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail2", R.string.ReportProblemHelpDetail2)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail3", R.string.ReportProblemHelpDetail3)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail4", R.string.ReportProblemHelpDetail4)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail5", R.string.ReportProblemHelpDetail5)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail6", R.string.ReportProblemHelpDetail6)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail7", R.string.ReportProblemHelpDetail7)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail8", R.string.ReportProblemHelpDetail8)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail9", R.string.ReportProblemHelpDetail9)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail10", R.string.ReportProblemHelpDetail10)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 18.0f);
        textView.setTextColor(Theme.MSG_TEXT_COLOR);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail11", R.string.ReportProblemHelpDetail11)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        textView = new TextView(context);
        textView.setTextSize(1, 17.0f);
        textView.setTextColor(0xFF222222);
        textView.setGravity(5);
        textView.setTypeface(getTypeface("fonts/rmedium.ttf"));
        textView.setText(AndroidUtilities.replaceTags(LocaleController.getString("ReportProblemHelpDetail12", R.string.ReportProblemHelpDetail12)));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -1, LocaleController.isRTL ? 5 : 3, 15, 10, 15, 0));
        scrollView.addView(linearLayout, LayoutHelper.createLinear(-1, -1));
        return fragmentView;


    }


    @Override
    public void onResume() {
        super.onResume();
        updateTheme();
        if (constant.AnalyticInitialized)
            ApplicationLoader.getInstance().trackScreenView("user Changes");
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

    public static void SendMAil(Context context, String str, String str2, String str3, File file) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        if (file != null) {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        }
        intent.putExtra("android.intent.extra.TEXT", str3);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_email)));
    }


}

