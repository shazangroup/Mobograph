package finalsoft.HideChats;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finalsoft.messenger.BuildConfig;
import com.finalsoft.messenger.R;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationCompat.AnimatorListenerAdapterProxy;
import org.telegram.messenger.AnimationCompat.ObjectAnimatorProxy;
import org.telegram.messenger.AnimationCompat.ViewProxy;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.MenuDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.Adapters.DialogsSearchAdapter;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Glow;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PlayerView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShortcutActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.ProfileActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import finalsoft.Menu.interfaces.OnMenuItemClickListener;

public class HiddenChats  extends BaseFragment implements NotificationCenter.NotificationCenterDelegate,
        PhotoViewer.PhotoViewerProvider , OnMenuItemClickListener {

    private RecyclerListView listView;
    private LinearLayoutManager layoutManager;
    private DialogsAdapter dialogsAdapter;
    private DialogsSearchAdapter dialogsSearchAdapter;

    private ProgressBar progressView;
    private LinearLayout emptyView;
    private ActionBarMenuItem passcodeItem;


    public static Context thiscontext;

    private AlertDialog permissionDialog;


    private boolean checkPermission = true;

    private String selectAlertString;
    private String selectAlertStringGroup;
    private String addToGroupAlertString;
    private int dialogsType;

    public static boolean dialogsLoaded;
    private boolean searching;
    private boolean searchWas;
    private boolean onlySelect;
    private long selectedDialog;
    private String searchString;
    private long openedDialogId;

    public static boolean needRefreshCategory = false;

    hideDBAdapter hideDBAdapter;

    private HiddenChats.HiddenChatsDelegate delegate;

    private float touchPositionDP;
    private int user_id = 0;
    private int chat_id = 0;
    private BackupImageView avatarImage;

    private Button toastBtn;

    private FrameLayout tabsView;
    private LinearLayout tabsLayout;
    private int tabsHeight;
    private ImageView allTab;
    private ImageView usersTab;
    private ImageView groupsTab;
    private ImageView superGroupsTab;
    private ImageView channelsTab;
    private ImageView botsTab;
    private ImageView hiddenTab;
    private ImageView favsTab;
    private TextView allCounter;
    private TextView usersCounter;
    private TextView groupsCounter;
    private TextView sGroupsCounter;
    private TextView botsCounter;
    private TextView hiddensCounter;
    private TextView channelsCounter;
    private TextView favsCounter;
    private boolean countSize;



    private boolean hideTabs;
    private int selectedTab;
    private DialogsAdapter dialogsBackupAdapter;
    private boolean tabsHidden;
    private boolean disableAnimation;
    private boolean ShowTabsInBottomRow;



    // *****
    private boolean isHiddenMode = true;
    public static int hiddenCode = 0;





    ActionBarMenu menu;


    public static HiddenChats mdialog = null;

    private HiddenChats.DialogsOnTouch onTouchListener = null;
    //private DisplayMetrics displayMetrics;


    public interface HiddenChatsDelegate {
        void didSelectDialog(HiddenChats fragment, long dialog_id, boolean param);
    }

    public HiddenChats(Bundle args) {
        super(args);
    }

    @Override
    public boolean onFragmentCreate() {
        super.onFragmentCreate();

        if (getArguments() != null) {
            onlySelect = arguments.getBoolean("onlySelect", false);
            dialogsType = arguments.getInt("dialogsType", 0);
            selectAlertString = arguments.getString("selectAlertString");
            selectAlertStringGroup = arguments.getString("selectAlertStringGroup");
            addToGroupAlertString = arguments.getString("addToGroupAlertString");

            isHiddenMode = arguments.getBoolean("hiddens", false);
            if (isHiddenMode) {
                hiddenCode = arguments.getInt("hiddenCode", 0);

            }

            if (mdialog != null) {
                mdialog.finishFragment();
                mdialog = null;
            }

        }

        if (searchString == null) {
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.dialogsNeedReload);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.emojiDidLoaded);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.updateInterfaces);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.encryptedChatUpdated);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.contactsDidLoaded);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.appDidLogout);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.openedChatChanged);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.notificationsSettingsUpdated);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageReceivedByAck);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageReceivedByServer);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.messageSendError);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.didSetPasscode);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.needReloadRecentDialogsSearch);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.didLoadedReplyMessages);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.refreshTabs);
            NotificationCenter.getInstance().addObserver(this, NotificationCenter.messagesDidLoaded);


        }


        if (!dialogsLoaded) {
//            Log.i("finalsoft","loadDialogs2");
            MessagesController.getInstance().loadDialogs(0, 100, true);
            ContactsController.getInstance().checkInviteText();
            dialogsLoaded = true;
        }
        return true;
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (searchString == null) {
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.dialogsNeedReload);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.emojiDidLoaded);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.updateInterfaces);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.encryptedChatUpdated);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.contactsDidLoaded);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.appDidLogout);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.openedChatChanged);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageReceivedByAck);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageReceivedByServer);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.messageSendError);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didSetPasscode);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.needReloadRecentDialogsSearch);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didLoadedReplyMessages);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.refreshTabs);
        }
        delegate = null;
    }

    @SuppressWarnings("ResourceType")
    @Override
    public View createView(final Context context) {
        searching = false;
        searchWas = false;
        thiscontext = context;
        Theme.loadResources(context);

        hideDBAdapter = new hideDBAdapter(context);

        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int iconColor = themePrefs.getInt("chatsHeaderIconsColor", 0xffffffff);
        int tColor = themePrefs.getInt("chatsHeaderTitleColor", 0xffffffff);
        avatarImage = new BackupImageView(context);
        avatarImage.setRoundRadius(AndroidUtilities.dp(30));
        menu = actionBar.createMenu();
        if (!onlySelect && searchString == null) {
            Drawable lock = getParentActivity().getResources().getDrawable(R.drawable.lock_close);
            lock.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
            passcodeItem = menu.addItem(1, lock);
            updatePasscodeButton();
        }




        if (onlySelect) {
            //actionBar.setBackButtonImage(R.drawable.ic_ab_back);
            Drawable back = getParentActivity().getResources().getDrawable(R.drawable.ic_ab_back);
            if (back != null) back.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
            actionBar.setBackButtonDrawable(back);
            actionBar.setTitle(LocaleController.getString("SelectChat", R.string.SelectChat));

        } else {
            if (searchString != null) {
                actionBar.setBackButtonImage(R.drawable.ic_ab_back);
            } else {
                actionBar.setBackButtonDrawable(new MenuDrawable());
            }
            if (BuildVars.DEBUG_VERSION) {
                actionBar.setTitle(LocaleController.getString("AppNameBeta", R.string.AppNameBeta));
            } else {
                actionBar.setTitle(LocaleController.getString("AppName", R.string.AppName));
            }
        }
        actionBar.setAllowOverlayTitle(true);

        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    if (onlySelect) {
                        finishFragment();
                    } else if (parentLayout != null) {
                        //
                        //if (!hideTabs) {
                        //    parentLayout.getDrawerLayoutContainer().setAllowOpenDrawer(true, false);
                        //}
                        //
                        parentLayout.getDrawerLayoutContainer().openDrawer(false);
                    }
                } else if (id == 1) {
                    UserConfig.appLocked = !UserConfig.appLocked;
                    UserConfig.saveConfig(false);
                    updatePasscodeButton();
                }
            }
        });

        paintHeader(false);

        FrameLayout frameLayout = new FrameLayout(context);
        fragmentView = frameLayout;

        // *** JetGram
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        plusPreferences.edit().putInt("SpecialHiddenCounter",0).commit();


        listView = new RecyclerListView(context);
        listView.setVerticalScrollBarEnabled(true);
        listView.setItemAnimator(null);
        listView.setInstantClick(true);
        listView.setLayoutAnimation(null);
        layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        if (Build.VERSION.SDK_INT >= 11) {
            listView.setVerticalScrollbarPosition(LocaleController.isRTL ? ListView.SCROLLBAR_POSITION_LEFT : ListView.SCROLLBAR_POSITION_RIGHT);
        }
        frameLayout.addView(listView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        onTouchListener = new HiddenChats.DialogsOnTouch(context);
        listView.setOnTouchListener(onTouchListener);

        listView.setOnItemClickListener(new RecyclerListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                long dialog_id = 0;
                int message_id = 0;
                RecyclerView.Adapter adapter = listView.getAdapter();
                if (adapter == dialogsAdapter) {
                    TLRPC.TL_dialog dialog = dialogsAdapter.getItem(position);
                    if (dialog == null) {
                        return;
                    }
                    dialog_id = dialog.id;
                } else if (adapter == dialogsSearchAdapter) {
                    Object obj = dialogsSearchAdapter.getItem(position);
                    if (obj instanceof TLRPC.User) {
                        dialog_id = ((TLRPC.User) obj).id;
                        if (dialogsSearchAdapter.isGlobalSearch(position)) {
                            ArrayList<TLRPC.User> users = new ArrayList<>();
                            users.add((TLRPC.User) obj);
                            MessagesController.getInstance().putUsers(users, false);
                            MessagesStorage.getInstance().putUsersAndChats(users, null, false, true);
                        }
                        if (!onlySelect) {
                            dialogsSearchAdapter.putRecentSearch(dialog_id, (TLRPC.User) obj);
                        }
                    } else if (obj instanceof TLRPC.Chat) {
                        if (dialogsSearchAdapter.isGlobalSearch(position)) {
                            ArrayList<TLRPC.Chat> chats = new ArrayList<>();
                            chats.add((TLRPC.Chat) obj);
                            MessagesController.getInstance().putChats(chats, false);
                            MessagesStorage.getInstance().putUsersAndChats(null, chats, false, true);
                        }
                        if (((TLRPC.Chat) obj).id > 0) {
                            dialog_id = -((TLRPC.Chat) obj).id;
                        } else {
                            dialog_id = AndroidUtilities.makeBroadcastId(((TLRPC.Chat) obj).id);
                        }
                        if (!onlySelect) {
                            dialogsSearchAdapter.putRecentSearch(dialog_id, (TLRPC.Chat) obj);
                        }
                    } else if (obj instanceof TLRPC.EncryptedChat) {
                        dialog_id = ((long) ((TLRPC.EncryptedChat) obj).id) << 32;
                        if (!onlySelect) {
                            dialogsSearchAdapter.putRecentSearch(dialog_id, (TLRPC.EncryptedChat) obj);
                        }
                    } else if (obj instanceof MessageObject) {
                        MessageObject messageObject = (MessageObject) obj;
                        dialog_id = messageObject.getDialogId();
                        message_id = messageObject.getId();
                        dialogsSearchAdapter.addHashtagsFromMessage(dialogsSearchAdapter.getLastSearchString());
                    } else if (obj instanceof String) {
                        actionBar.openSearchField((String) obj);
                    }
                }

                if (dialog_id == 0) {
                    return;
                }

                if (LocaleController.isRTL ? (touchPositionDP > 330) : (touchPositionDP < 65)) {
                    SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                    //if(preferences.getInt("dialogsClickOnGroupPic", 0) == 2)MessagesController.getInstance().loadChatInfo(chat_id, null, false);
                    user_id = 0;
                    chat_id = 0;
                    int lower_part = (int) dialog_id;
                    int high_id = (int) (dialog_id >> 32);

                    if (lower_part != 0) {
                        if (high_id == 1) {
                            chat_id = lower_part;
                        } else {
                            if (lower_part > 0) {
                                user_id = lower_part;
                            } else if (lower_part < 0) {
                                chat_id = -lower_part;
                            }
                        }
                    } else {
                        TLRPC.EncryptedChat chat = MessagesController.getInstance().getEncryptedChat(high_id);
                        user_id = chat.user_id;
                    }

                    if (user_id != 0) {
                        int picClick = plusPreferences.getInt("dialogsClickOnPic", 1);
                        if (picClick == 2) {
                            Bundle args = new Bundle();
                            args.putInt("user_id", user_id);
                            presentFragment(new ProfileActivity(args));
                            return;
                        } else if (picClick == 1) {
                            TLRPC.User user = MessagesController.getInstance().getUser(user_id);
                            if (user.photo != null && user.photo.photo_big != null) {
                                PhotoViewer.getInstance().setParentActivity(getParentActivity());
                                PhotoViewer.getInstance().openPhoto(user.photo.photo_big, HiddenChats.this);
                            }
                            return;
                        }

                    } else if (chat_id != 0) {
                        int picClick = plusPreferences.getInt("dialogsClickOnGroupPic", 2);
                        if (picClick == 2) {
                            MessagesController.getInstance().loadChatInfo(chat_id, null, false);
                            Bundle args = new Bundle();
                            args.putInt("chat_id", chat_id);
                            ProfileActivity fragment = new ProfileActivity(args);
                            presentFragment(fragment);
                            return;
                        } else if (picClick == 1) {
                            TLRPC.Chat chat = MessagesController.getInstance().getChat(chat_id);
                            if (chat.photo != null && chat.photo.photo_big != null) {
                                PhotoViewer.getInstance().setParentActivity(getParentActivity());
                                PhotoViewer.getInstance().openPhoto(chat.photo.photo_big, HiddenChats.this);
                            }
                            return;
                        }
                    }
                }

                //
                if (onlySelect) {
                    didSelectResult(dialog_id, true, false);
                } else {
                    Bundle args = new Bundle();
                    int lower_part = (int) dialog_id;
                    int high_id = (int) (dialog_id >> 32);
                    if (lower_part != 0) {
                        if (high_id == 1) {
                            args.putInt("chat_id", lower_part);
                        } else {
                            if (lower_part > 0) {
                                args.putInt("user_id", lower_part);
                            } else if (lower_part < 0) {
                                if (message_id != 0) {
                                    TLRPC.Chat chat = MessagesController.getInstance().getChat(-lower_part);
                                    if (chat != null && chat.migrated_to != null) {
                                        args.putInt("migrated_to", lower_part);
                                        lower_part = -chat.migrated_to.channel_id;
                                    }
                                }
                                args.putInt("chat_id", -lower_part);
                            }
                        }
                    } else {
                        args.putInt("enc_id", high_id);
                    }
                    if (message_id != 0) {
                        args.putInt("message_id", message_id);
                    } else {
                        if (actionBar != null) {
                            actionBar.closeSearchField();
                        }
                    }
                    if (AndroidUtilities.isTablet()) {
                        if (openedDialogId == dialog_id && adapter != dialogsSearchAdapter) {
                            return;
                        }
                        if (dialogsAdapter != null) {
                            dialogsAdapter.setOpenedDialogId(openedDialogId = dialog_id);
                            updateVisibleRows(MessagesController.UPDATE_MASK_SELECT_DIALOG);
                        }
                    }
                    if (searchString != null) {
                        if (MessagesController.checkCanOpenChat(args, HiddenChats.this)) {
                            NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats);
                            presentFragment(new ChatActivity(args));
                        }
                    } else {
                        if (MessagesController.checkCanOpenChat(args, HiddenChats.this)) {
                            args.putBoolean("fromHiddens", dialogsType == 10 );
                            presentFragment(new ChatActivity(args));
                        }
                    }
                }
            }
        });
        listView.setOnItemLongClickListener(
                new RecyclerListView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position) {

                        // Clear search history
                        if (onlySelect || searching && searchWas || getParentActivity() == null) {
                            if (searchWas && searching || dialogsSearchAdapter.isRecentSearchDisplayed()) {
                                RecyclerView.Adapter adapter = listView.getAdapter();
                                if (adapter == dialogsSearchAdapter) {
                                    Object item = dialogsSearchAdapter.getItem(position);
                                    if (item instanceof String || dialogsSearchAdapter.isRecentSearchDisplayed()) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                                        builder.setMessage(LocaleController.getString("ClearSearch", R.string.ClearSearch));
                                        builder.setPositiveButton(LocaleController.getString("ClearButton", R.string.ClearButton).toUpperCase(), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (dialogsSearchAdapter.isRecentSearchDisplayed()) {
                                                    dialogsSearchAdapter.clearRecentSearch();
                                                } else {
                                                    dialogsSearchAdapter.clearRecentHashtags();
                                                }
                                            }
                                        });
                                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                        showDialog(builder.create());
                                        return true;
                                    }
                                }
                            }
                            return false;
                        }

                        TLRPC.TL_dialog dialog;
                        ArrayList<TLRPC.TL_dialog> dialogs = getDialogsArray();
                        if (position < 0 || position >= dialogs.size()) {
                            return false;
                        }

                        dialog = dialogs.get(position);
                        selectedDialog = dialog.id;

                        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                        int lower_id = (int) selectedDialog;
                        int high_id = (int) (selectedDialog >> 32);

                        if (dialog instanceof TLRPC.TL_dialogChannel) {
                            final TLRPC.Chat chat = MessagesController.getInstance().getChat(-lower_id);
                            if (dialogsType == 10 ) {

                                builder.setItems(new CharSequence[]{LocaleController.getString("unHideAChat", R.string.unHideAChat)}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {

                                        DeleteHidden((int) selectedDialog);

                                    }
                                });
                            }else{
                                builder.setItems(new CharSequence[]{LocaleController.getString("unHideSpecialChat", R.string.unHideSpecialChat) , chat == null || !chat.creator ? LocaleController.getString("LeaveChannelMenu", R.string.LeaveChannelMenu) : LocaleController.getString("ChannelDeleteMenu", R.string.ChannelDeleteMenu)}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {
                                        if (which == 0){
                                            DeleteHidden((int) selectedDialog);
                                        }else{
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                                            //builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                                            builder.setTitle(chat != null ? chat.title : LocaleController.getString("AppName", R.string.AppName));

                                                if (chat != null && chat.megagroup) {
                                                    if (!chat.creator) {
                                                        builder.setMessage(LocaleController.getString("MegaLeaveAlert", R.string.MegaLeaveAlert));
                                                    } else {
                                                        builder.setMessage(LocaleController.getString("MegaDeleteAlert", R.string.MegaDeleteAlert));
                                                    }
                                                } else {
                                                    if (chat == null || !chat.creator) {
                                                        builder.setMessage(LocaleController.getString("ChannelLeaveAlert", R.string.ChannelLeaveAlert));
                                                    } else {
                                                        builder.setMessage(LocaleController.getString("ChannelDeleteAlert", R.string.ChannelDeleteAlert));
                                                    }
                                                }
                                                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        MessagesController.getInstance().deleteUserFromChat((int) -selectedDialog, UserConfig.getCurrentUser(), null);
                                                        if (AndroidUtilities.isTablet()) {
                                                            NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, selectedDialog);
                                                        }
                                                    }
                                                });
                                            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                            showDialog(builder.create());
                                        }
                                    }
                                });
                            }
                            showDialog(builder.create());
                        } else {
                            final boolean isChat = (int) dialog.id < 0 && (int) (dialog.id >> 32) != 1;
                                  TLRPC.User user = null;
                            if (!isChat && lower_id > 0 && high_id != 1) {
                                user = MessagesController.getInstance().getUser(lower_id);
                            }
                            if (dialogsType == 10) {

                                builder.setItems(new CharSequence[]{  LocaleController.getString("unHideAChat", R.string.unHideAChat)}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {

                                        DeleteHidden((int) selectedDialog);

                                    }
                                });
                                showDialog(builder.create());

                            } else {
                                builder.setItems(new CharSequence[]{ LocaleController.getString("unHideSpecialChat", R.string.unHideSpecialChat) }, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {
                                        DeleteHidden((int) selectedDialog);
                                    }
                                });
                                showDialog(builder.create());
                            }
                        }
                        return true;
                    }
                });

        emptyView = new LinearLayout(context);
        emptyView.setOrientation(LinearLayout.VERTICAL);
        emptyView.setVisibility(View.GONE);
        emptyView.setGravity(Gravity.CENTER);
        frameLayout.addView(emptyView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        //emptyView.setOnTouchListener(new View.OnTouchListener() {
        //
        //    @Override
        //    public boolean onTouch(View v, MotionEvent event) {
        //        return true;
        //    }
        //});
        emptyView.setOnTouchListener(onTouchListener);
        TextView textView = new TextView(context);
        textView.setText(LocaleController.getString("NoChats", R.string.NoChats));
        textView.setTextColor(0xff959595);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        emptyView.addView(textView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT));

        textView = new

                TextView(context);

        String help = LocaleController.getString("NoChatsHelp", R.string.NoChatsHelp);
        if (AndroidUtilities.isTablet() && !AndroidUtilities.isSmallTablet())

        {
            help = help.replace('\n', ' ');
        }

        textView.setText(help);
        textView.setTextColor(0xff959595);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(AndroidUtilities.dp(8), AndroidUtilities.dp(6), AndroidUtilities.dp(8), 0);
        textView.setLineSpacing(AndroidUtilities.dp(2), 1);
        emptyView.addView(textView, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT));

        progressView = new

                ProgressBar(context);

        progressView.setVisibility(View.GONE);
        frameLayout.addView(progressView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));


        int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);

        tabsView = new FrameLayout(context);

        createTabs(context);


        SharedPreferences mpref = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean ShowTabsInBottomRow = mpref.getBoolean("ShowTabsInBottomRow", false);

        //if(dialogsType == 0 || dialogsType > 2){
        frameLayout.addView(tabsView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, tabsHeight, ShowTabsInBottomRow ? Gravity.BOTTOM : Gravity.TOP, 0, 0, 0, 0));
        //}

        final int hColor = themePrefs.getInt("chatsHeaderColor", def);
        listView.setOnScrollListener(new RecyclerView.OnScrollListener()

                                     {
                                         @Override
                                         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                             if (newState == RecyclerView.SCROLL_STATE_DRAGGING && searching && searchWas) {
                                                 AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
                                             }
                                             Glow.setEdgeGlowColor(listView, hColor);
                                         }

                                         @Override
                                         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                             int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                                             int visibleItemCount = Math.abs(layoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
                                             int totalItemCount = recyclerView.getAdapter().getItemCount();

                                             if (searching && searchWas) {
                                                 if (visibleItemCount > 0 && layoutManager.findLastVisibleItemPosition() == totalItemCount - 1 && !dialogsSearchAdapter.isMessagesSearchEndReached()) {
                                                     dialogsSearchAdapter.loadMoreSearchMessages();
                                                 }
                                                 return;
                                             }
                                             if (visibleItemCount > 0) {
                                                 if (layoutManager.findLastVisibleItemPosition() >= getDialogsArray().size() - 10) {
//                                                     Log.i("finalsoft","loadDialogs1");
                                                     MessagesController.getInstance().loadDialogs(-1, 100, !MessagesController.getInstance().dialogsEndReached);
                                                 }
                                             }

                                             if (!hideTabs) {
                                                 //if(!disableAnimation) {
                                                 if (dy > 1) {
                                                     //Down (HIDE)
                                                     if (recyclerView.getChildAt(0).getTop() < 0) {
                                                         if (!disableAnimation) {
                                                             hideTabsAnimated(true);
                                                         }
                                                     }

                                                 }
                                                 if (dy < -1) {
                                                     //Up (SHOW)
                                                     if (!disableAnimation) {
                                                         hideTabsAnimated(false);
                                                         if (firstVisibleItem == 0) {
                                                             SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                                                             boolean disable = preferences.getBoolean("ShowTabsInBottomRow", false);
                                                             listView.setPadding(0, AndroidUtilities.dp(disable ? 0 : tabsHeight), 0, 0);
                                                         }
                                                     }
                                                 }
                                                 //}
                                             }
                                         }
                                     }

        );

        if (searchString == null)

        {
            dialogsAdapter = new DialogsAdapter(context, dialogsType);
            if (AndroidUtilities.isTablet() && openedDialogId != 0) {
                dialogsAdapter.setOpenedDialogId(openedDialogId);
            }
            listView.setAdapter(dialogsAdapter);
            dialogsBackupAdapter = dialogsAdapter;
        }

        int type = 0;
        if (searchString != null)

        {
            type = 2;
        } else if (!onlySelect)

        {
            type = 1;
        }

        dialogsSearchAdapter = new DialogsSearchAdapter(context, type, dialogsType);


        if (MessagesController.getInstance().loadingDialogs && MessagesController.getInstance().dialogs.isEmpty())

        {

            emptyView.setVisibility(View.GONE);
            listView.setEmptyView(progressView);
        } else

        {
            progressView.setVisibility(View.GONE);
            listView.setEmptyView(emptyView);
        }

        if (searchString != null)

        {
            actionBar.openSearchField(searchString);
        }

        //if (!onlySelect && dialogsType == 0) {
        if (!onlySelect && (dialogsType == 0 || dialogsType > 2))

        {
            frameLayout.addView(new PlayerView(context, this), LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 39, Gravity.TOP | Gravity.LEFT, 0, -36, 0, 0));
        }

        toastBtn = new

                Button(context);

        toastBtn.setVisibility(AndroidUtilities.themeUpdated ? View.VISIBLE : View.GONE);
        if (AndroidUtilities.themeUpdated)

        {
            AndroidUtilities.themeUpdated = false;
            String tName = themePrefs.getString("themeName", "");
            //int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);
            //int hColor = themePrefs.getInt("chatsHeaderColor", def);
            toastBtn.setText(LocaleController.formatString("ThemeUpdated", R.string.ThemeUpdated, tName));
            if (Build.VERSION.SDK_INT >= 14) toastBtn.setAllCaps(false);
            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(AndroidUtilities.dp(4));
            shape.setColor(hColor);
            toastBtn.setBackgroundDrawable(shape);
            toastBtn.setTextColor(tColor);
            toastBtn.setTextSize(16);
            ViewProxy.setTranslationY(toastBtn, -AndroidUtilities.dp(100));
            ObjectAnimatorProxy animator = ObjectAnimatorProxy.ofFloatProxy(toastBtn, "translationY", 0).setDuration(500);
            //animator.setInterpolator(tabsInterpolator);
            animator.start();

            toastBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String packageName = "es.rafalense.themes";
                        if (BuildConfig.DEBUG) packageName = "es.rafalense.themes.beta";
                        Intent intent = ApplicationLoader.applicationContext.getPackageManager().getLaunchIntentForPackage(packageName);
                        if (intent != null) {
                            ApplicationLoader.applicationContext.startActivity(intent);
                        }
                        toastBtn.setVisibility(View.GONE);
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                }
            });
            frameLayout.addView(toastBtn, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.TOP | Gravity.CENTER, 0, 10, 0, 0));
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimatorProxy animator = ObjectAnimatorProxy.ofFloatProxy(toastBtn, "translationY", -AndroidUtilities.dp(100)).setDuration(500);
                            //animator.setInterpolator(tabsInterpolator);
                            animator.start();
                        }
                    });
                }
            }, 4000);
        }


        return fragmentView;
    }




    private void markAsReadDialog(final boolean all) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
        TLRPC.Chat currentChat = MessagesController.getInstance().getChat((int) -selectedDialog);
        TLRPC.User user = MessagesController.getInstance().getUser((int) selectedDialog);
        String title = currentChat != null ? currentChat.title : user != null ? UserObject.getUserName(user) : LocaleController.getString("AppName", R.string.AppName);
        builder.setTitle(all ? getHeaderAllTitles() : title);
        builder.setMessage((all ? LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead) : LocaleController.getString("MarkAsRead", R.string.MarkAsRead)) + '\n' + LocaleController.getString("AreYouSure", R.string.AreYouSure));
        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (all) {
                    ArrayList<TLRPC.TL_dialog> dialogs = getDialogsArray();
                    if (dialogs != null && !dialogs.isEmpty()) {
                        for (int a = 0; a < dialogs.size(); a++) {
                            TLRPC.TL_dialog dialg = getDialogsArray().get(a);
                            if (dialg.unread_count > 0) {
                                MessagesController.getInstance().markDialogAsRead(dialg.id, dialg.last_read, Math.max(0, dialg.top_message), dialg.last_message_date, true, false);
                            }
                        }
                    }
                } else {
                    TLRPC.TL_dialog dialg = MessagesController.getInstance().dialogs_dict.get(selectedDialog);
                    if (dialg.unread_count > 0) {
                        MessagesController.getInstance().markDialogAsRead(dialg.id, dialg.last_read, Math.max(0, dialg.top_message), dialg.last_message_date, true, false);
                    }
                }
            }
        });
        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(builder.create());
    }

    private void addShortcut() {

        Intent shortcutIntent = new Intent(ApplicationLoader.applicationContext, ShortcutActivity.class);
        shortcutIntent.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        shortcutIntent.setFlags(32768);

        TLRPC.TL_dialog dialg = MessagesController.getInstance().dialogs_dict.get(selectedDialog);
        TLRPC.Chat currentChat = MessagesController.getInstance().getChat((int) -selectedDialog);
        TLRPC.User user = MessagesController.getInstance().getUser((int) selectedDialog);
        TLRPC.EncryptedChat encryptedChat = null;

        AvatarDrawable avatarDrawable = new AvatarDrawable();
        long dialog_id = dialg.id;

        int lower_id = (int) dialog_id;
        int high_id = (int) (dialog_id >> 32);
        if (lower_id != 0) {
            if (high_id == 1) {
                currentChat = MessagesController.getInstance().getChat(lower_id);
                shortcutIntent.putExtra("chatId", lower_id);
                avatarDrawable.setInfo(currentChat);
            } else {
                if (lower_id < 0) {
                    currentChat = MessagesController.getInstance().getChat(-lower_id);
                    if (currentChat != null && currentChat.migrated_to != null) {
                        TLRPC.Chat chat2 = MessagesController.getInstance().getChat(currentChat.migrated_to.channel_id);
                        if (chat2 != null) {
                            currentChat = chat2;
                        }
                    }
                    shortcutIntent.putExtra("chatId", -lower_id);
                    avatarDrawable.setInfo(currentChat);
                } else {
                    user = MessagesController.getInstance().getUser(lower_id);
                    shortcutIntent.putExtra("userId", lower_id);
                    avatarDrawable.setInfo(user);
                }
            }
        } else {
            encryptedChat = MessagesController.getInstance().getEncryptedChat(high_id);
            if (encryptedChat != null) {
                user = MessagesController.getInstance().getUser(encryptedChat.user_id);
                shortcutIntent.putExtra("encId", high_id);
                avatarDrawable.setInfo(user);
            }
        }

        final String name = currentChat != null ? currentChat.title : user != null && encryptedChat == null ? UserObject.getUserName(user) : encryptedChat != null ? new String(Character.toChars(0x1F512)) + UserObject.getUserName(user) : null;
        //Log.e("HiddenChats", "addShortcut " + user.id);
        if (name == null) {
            return;
        }
        //Log.e("Plus", "addShortcut " + name + " dialog_id " + dialog_id);

        TLRPC.FileLocation photoPath = null;

        if (currentChat != null) {
            if (currentChat.photo != null && currentChat.photo.photo_small != null && currentChat.photo.photo_small.volume_id != 0 && currentChat.photo.photo_small.local_id != 0) {
                photoPath = currentChat.photo.photo_small;
            }
        } else if (user != null) {
            if (user.photo != null && user.photo.photo_small != null && user.photo.photo_small.volume_id != 0 && user.photo.photo_small.local_id != 0) {
                photoPath = user.photo.photo_small;
            }
        }
        BitmapDrawable img = null;

        if (photoPath != null) {
            img = ImageLoader.getInstance().getImageFromMemory(photoPath, null, "50_50");
        }

        String action = "com.android.launcher.action.INSTALL_SHORTCUT";
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        if (img != null) {
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, getRoundBitmap(img.getBitmap()));
        } else {
            int w = AndroidUtilities.dp(40);
            int h = AndroidUtilities.dp(40);
            Bitmap mutableBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mutableBitmap);
            avatarDrawable.setBounds(0, 0, w, h);
            avatarDrawable.draw(canvas);
            //if(mutableBitmap != null){
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, getRoundBitmap(mutableBitmap));
            //} else{
            //addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, R.drawable.intro1));
            //}
        }

        addIntent.putExtra("duplicate", false);
        addIntent.setAction(action);
        //addIntent.setPackage(ApplicationLoader.applicationContext.getPackageName());
        boolean error = false;
        if (ApplicationLoader.applicationContext.getPackageManager().queryBroadcastReceivers(new Intent(action), 0).size() > 0) {
            ApplicationLoader.applicationContext.sendBroadcast(addIntent);
        } else {
            error = true;
        }
        final String msg = error ? LocaleController.formatString("ShortcutError", R.string.ShortcutError, name) : LocaleController.formatString("ShortcutAdded", R.string.ShortcutAdded, name);
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (getParentActivity() != null) {
                    Toast toast = Toast.makeText(getParentActivity(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private Bitmap getRoundBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int radius = Math.min(h / 2, w / 2);
        Bitmap output = Bitmap.createBitmap(w + 8, h + 8, Bitmap.Config.ARGB_8888);
        Paint p = new Paint();
        p.setAntiAlias(true);
        Canvas c = new Canvas(output);
        c.drawARGB(0, 0, 0, 0);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(bitmap, 4, 4, p);
        return output;
    }

    public class DialogsOnTouch implements View.OnTouchListener {

        private DisplayMetrics displayMetrics;
        //private static final String logTag = "SwipeDetector";
        private static final int MIN_DISTANCE_HIGH = 40;
        private static final int MIN_DISTANCE_HIGH_Y = 60;
        private float downX, downY, upX, upY;
        private float vDPI;

        Context mContext;

        public DialogsOnTouch(Context context) {
            this.mContext = context;
            displayMetrics = context.getResources().getDisplayMetrics();
            vDPI = displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT;
            //Log.e("HiddenChats","DialogsOnTouch vDPI " + vDPI);
        }

        public boolean onTouch(View view, MotionEvent event) {

            touchPositionDP = Math.round(event.getX() / vDPI);
            //Log.e("HiddenChats","onTouch touchPositionDP " + touchPositionDP + " hideTabs " + hideTabs);
            if (hideTabs) {
                return false;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    downX = Math.round(event.getX() / vDPI);
                    downY = Math.round(event.getY() / vDPI);
                    //Log.e("HiddenChats", "view " + view.toString());
                    if (touchPositionDP > 50) {
                        parentLayout.getDrawerLayoutContainer().setAllowOpenDrawer(false, false);
                        //Log.e("HiddenChats", "DOWN setAllowOpenDrawer FALSE");
                    }
                    //Log.e("HiddenChats", "DOWN downX " + downX);
                    return view instanceof LinearLayout; // for emptyView
                }
                case MotionEvent.ACTION_UP: {
                    upX = Math.round(event.getX() / vDPI);
                    upY = Math.round(event.getY() / vDPI);
                    float deltaX = downX - upX;
                    float deltaY = downY - upY;
                    //Log.e(logTag, "MOVE X " + deltaX);
                    //Log.e(logTag, "MOVE Y " + deltaY);
                    //Log.e("HiddenChats", "UP downX " + downX);
                    //Log.e("HiddenChats", "UP upX " + upX);
                    //Log.e("HiddenChats", "UP deltaX " + deltaX);
                    // horizontal swipe detection
                    if (Math.abs(deltaX) > MIN_DISTANCE_HIGH && Math.abs(deltaY) < MIN_DISTANCE_HIGH_Y) {
                        //if (Math.abs(deltaX) > MIN_DISTANCE_HIGH) {
                        refreshDialogType(deltaX < 0 ? 0 : 1);//0: Left - Right 1: Right - Left
                        downX = Math.round(event.getX() / vDPI);
                        refreshAdapter(mContext);
                        //dialogsAdapter.notifyDataSetChanged();
                        refreshTabAndListViews(false);
                        //return true;
                    }
                    //Log.e("HiddenChats", "UP2 downX " + downX);
                    if (touchPositionDP > 50) {
                        parentLayout.getDrawerLayoutContainer().setAllowOpenDrawer(true, false);

                    }
                    //downX = downY = upX = upY = 0;
                    return false;
                }
            }

            return false;
        }
    }


    @Override
    public void onResume() {
        super.onResume();


        if (dialogsAdapter != null) {
            dialogsAdapter.notifyDataSetChanged();
        }
        if (dialogsSearchAdapter != null) {
            dialogsSearchAdapter.notifyDataSetChanged();
        }

        if (checkPermission && !onlySelect && Build.VERSION.SDK_INT >= 23) {
            Activity activity = getParentActivity();
            if (activity != null) {
                checkPermission = false;
                if (activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED || activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                        builder.setMessage(LocaleController.getString("PermissionContacts", R.string.PermissionContacts));
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                        showDialog(permissionDialog = builder.create());
                    } else if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                        builder.setMessage(LocaleController.getString("PermissionStorage", R.string.PermissionStorage));
                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                        showDialog(permissionDialog = builder.create());
                    } else {
                        askForPermissons();
                    }
                }
            }
        }
        updateTheme();
        unreadCount();
        this.actionBar.changeGhostModeVisibility();


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void askForPermissons() {
        Activity activity = getParentActivity();
        if (activity == null) {
            return;
        }
        ArrayList<String> permissons = new ArrayList<>();
        if (activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            permissons.add(Manifest.permission.READ_CONTACTS);
            permissons.add(Manifest.permission.WRITE_CONTACTS);
            permissons.add(Manifest.permission.GET_ACCOUNTS);
        }
        if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissons.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissons.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        String[] items = permissons.toArray(new String[permissons.size()]);
        activity.requestPermissions(items, 1);
    }



    @Override
    protected void onDialogDismiss(Dialog dialog) {
        super.onDialogDismiss(dialog);
        if (permissionDialog != null && dialog == permissionDialog && getParentActivity() != null) {
            askForPermissons();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onRequestPermissionsResultFragment(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int a = 0; a < permissions.length; a++) {
                if (grantResults.length <= a || grantResults[a] != PackageManager.PERMISSION_GRANTED) {
                    continue;
                }
                switch (permissions[a]) {
                    case Manifest.permission.READ_CONTACTS:
                        ContactsController.getInstance().readContacts();
                        break;
                    case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                        ImageLoader.getInstance().checkMediaPaths();
                        break;
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void didReceivedNotification(int id, Object... args) {
        if (id == NotificationCenter.dialogsNeedReload) {
            if (dialogsAdapter != null) {
                if (dialogsAdapter.isDataSetChanged()) {
                    dialogsAdapter.notifyDataSetChanged();
                } else {
                    updateVisibleRows(MessagesController.UPDATE_MASK_NEW_MESSAGE);
                }
            }
            if (dialogsSearchAdapter != null) {
                dialogsSearchAdapter.notifyDataSetChanged();
            }
            if (listView != null) {
                try {
                    if (MessagesController.getInstance().loadingDialogs && MessagesController.getInstance().dialogs.isEmpty()) {
                        emptyView.setVisibility(View.GONE);
                        listView.setEmptyView(progressView);
                    } else {
                        progressView.setVisibility(View.GONE);
                        if (searching && searchWas) {
                            emptyView.setVisibility(View.GONE);
                        } else {
                            listView.setEmptyView(emptyView);
                        }
                    }
                } catch (Exception e) {
                    FileLog.e(e); //TODO fix it in other way?
                }
            }
        } else if (id == NotificationCenter.emojiDidLoaded) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.updateInterfaces) {
            updateVisibleRows((Integer) args[0]);
        } else if (id == NotificationCenter.appDidLogout) {
            dialogsLoaded = false;
        } else if (id == NotificationCenter.encryptedChatUpdated) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.contactsDidLoaded) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.openedChatChanged) {
            if (dialogsType == 0 && AndroidUtilities.isTablet()) {
                boolean close = (Boolean) args[1];
                long dialog_id = (Long) args[0];
                if (close) {
                    if (dialog_id == openedDialogId) {
                        openedDialogId = 0;
                    }
                } else {
                    openedDialogId = dialog_id;
                }
                if (dialogsAdapter != null) {
                    dialogsAdapter.setOpenedDialogId(openedDialogId);
                }
                updateVisibleRows(MessagesController.UPDATE_MASK_SELECT_DIALOG);
            }
        } else if (id == NotificationCenter.notificationsSettingsUpdated) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.messageReceivedByAck || id == NotificationCenter.messageReceivedByServer || id == NotificationCenter.messageSendError) {
            updateVisibleRows(MessagesController.UPDATE_MASK_SEND_STATE);
        } else if (id == NotificationCenter.didSetPasscode) {
            updatePasscodeButton();
        } else if (id == NotificationCenter.refreshTabs) {
            updateTabs();
            hideShowTabs((int) args[0]);
        }

        if (id == NotificationCenter.needReloadRecentDialogsSearch) {
            if (dialogsSearchAdapter != null) {
                dialogsSearchAdapter.loadRecentSearch();
            }
        } else if (id == NotificationCenter.didLoadedReplyMessages) {
            updateVisibleRows(0);
        } else if (id == NotificationCenter.messagesDidLoaded) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            if (sharedPreferences.getBoolean("setViewEnable", false)) {
                int messageId = sharedPreferences.getInt("setViewMessageId", 0);
                int dialogId = sharedPreferences.getInt("setViewdialogId", 0);

                ArrayList<MessageObject> messArr = (ArrayList<MessageObject>) args[2];
                if (messArr != null)
                    for (int i = 0; i < messArr.size(); i++) {
                        if (messArr.get(i).getDialogId() == dialogId && messArr.get(i).getId() == messageId) {

                            MessagesController.getInstance().markMessageAsRead(messArr.get(i).getDialogId(), messArr.get(i).getId(), messArr.get(i).messageOwner.ttl);
                            MessagesController.getInstance().addToViewsQueue(messArr.get(i).messageOwner, false);
                            sharedPreferences.edit().putBoolean("setViewEnable", false).commit();
                        }

                    }

            }


        }
    }

    private ArrayList<TLRPC.TL_dialog> getDialogsArray() {

        if (dialogsType == 0) {
            return MessagesController.getInstance().dialogs;
        } else if (dialogsType == 1) {
            return MessagesController.getInstance().dialogsServerOnly;
        } else if (dialogsType == 2) {
            return MessagesController.getInstance().dialogsGroupsOnly;
        }
        //Customized:
        else if (dialogsType == 3) {
            return MessagesController.getInstance().dialogsUsers;
        } else if (dialogsType == 4) {
            return MessagesController.getInstance().dialogsGroups;
        } else if (dialogsType == 5) {
            return MessagesController.getInstance().dialogsChannels;
        } else if (dialogsType == 6) {
            return MessagesController.getInstance().dialogsBots;
        } else if (dialogsType == 7) {
            return MessagesController.getInstance().dialogsMegaGroups;
        } else if (dialogsType == 8) {
            return MessagesController.getInstance().dialogsFavs;
        } else if (dialogsType == 9) {
            return MessagesController.getInstance().dialogsGroupsAll;
        } else if (dialogsType == 10) {
            return MessagesController.getInstance().dialogsHides;

        } else if (dialogsType == 12) {
            return MessagesController.getInstance().dialogsSpecialHides;
        }
        //
        return null;
    }




    private void updatePasscodeButton() {
        if (passcodeItem == null) {
            return;
        }
        if (UserConfig.passcodeHash.length() != 0 && !searching) {
            passcodeItem.setVisibility(View.VISIBLE);
            SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
            int iconColor = themePrefs.getInt("chatsHeaderIconsColor", 0xffffffff);
            if (UserConfig.appLocked) {
                //passcodeItem.setIcon(R.drawable.lock_close);
                Drawable lockC = getParentActivity().getResources().getDrawable(R.drawable.lock_close);
                if (lockC != null) lockC.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
                passcodeItem.setIcon(lockC);
            } else {
                //passcodeItem.setIcon(R.drawable.lock_open);
                Drawable lockO = getParentActivity().getResources().getDrawable(R.drawable.lock_open);
                if (lockO != null) lockO.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
                passcodeItem.setIcon(lockO);
            }
        } else {
            passcodeItem.setVisibility(View.GONE);
        }
    }


    private void updateVisibleRows(int mask) {
        if (listView == null) {
            return;
        }
        int count = listView.getChildCount();
        for (int a = 0; a < count; a++) {
            View child = listView.getChildAt(a);
            if (child instanceof DialogCell) {
                if (listView.getAdapter() != dialogsSearchAdapter) {
                    DialogCell cell = (DialogCell) child;
                    if ((mask & MessagesController.UPDATE_MASK_NEW_MESSAGE) != 0) {
                        cell.checkCurrentDialogIndex();
                        if (dialogsType == 0 && AndroidUtilities.isTablet()) {
                            cell.setDialogSelected(cell.getDialogId() == openedDialogId);
                        }
                    } else if ((mask & MessagesController.UPDATE_MASK_SELECT_DIALOG) != 0) {
                        if (dialogsType == 0 && AndroidUtilities.isTablet()) {
                            cell.setDialogSelected(cell.getDialogId() == openedDialogId);
                        }
                    } else {
                        cell.update(mask);
                    }
                }
            } else if (child instanceof UserCell) {
                ((UserCell) child).update(mask);
            } else if (child instanceof ProfileSearchCell) {
                ((ProfileSearchCell) child).update(mask);
            }
        }
        updateListBG();
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                unreadCount();

            }
        });
    }

    private void unreadCount() {
        unreadCount(MessagesController.getInstance().dialogs, allCounter);
        unreadCount(MessagesController.getInstance().dialogsUsers, usersCounter);
        unreadCount(MessagesController.getInstance().dialogsBots, botsCounter);
        unreadCount(MessagesController.getInstance().dialogsChannels, channelsCounter);
        unreadCount(MessagesController.getInstance().dialogsFavs, favsCounter);
        unreadCountGroups();

    }

//    public void MenuCount(TextView tv) {
//        SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
//        int notifCount = sharedPreferences.getInt("notifCount", 0);
//        int OldNotifCount = sharedPreferences.getInt("OldNotifCount", 0);
//        int unreadCount = notifCount - OldNotifCount;
//
//
//        if (unreadCount == 0) {
//            tv.setVisibility(View.GONE);
//        } else {
//            tv.setVisibility(View.VISIBLE);
//            tv.setText("" + unreadCount);
//
//            SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
////            int size = themePrefs.getInt("chatsHeaderTabCounterSize", 11);
////            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
//            int cColor = themePrefs.getInt("chatsHeaderTabCounterColor", 0xffffffff);
//            tv.getBackground().setColorFilter(themePrefs.getInt("chatsHeaderTabCounterBGColor", 0xffd32f2f), PorterDuff.Mode.SRC_IN);
//            tv.setTextColor(cColor);
//        }
//    }

    private void unreadCountGroups() {
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
        if (hideSGroups) {
            unreadCount(MessagesController.getInstance().dialogsGroupsAll, groupsCounter);
        } else {
            unreadCount(MessagesController.getInstance().dialogsGroups, groupsCounter);
            unreadCount(MessagesController.getInstance().dialogsMegaGroups, sGroupsCounter);
        }
    }

    private void unreadCount(ArrayList<TLRPC.TL_dialog> dialogs, TextView tv) {
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean hTabs = plusPreferences.getBoolean("hideTabs", false);
        if (hTabs) return;
        boolean hideCounters = plusPreferences.getBoolean("hideTabsCounters", false);
        if (hideCounters) {
            tv.setVisibility(View.GONE);
            return;
        }
        boolean allMuted = true;
        boolean countDialogs = plusPreferences.getBoolean("tabsCountersCountChats", false);
        boolean countNotMuted = plusPreferences.getBoolean("tabsCountersCountNotMuted", false);
        int unreadCount = 0;

        if (dialogs != null && !dialogs.isEmpty()) {
            for (int a = 0; a < dialogs.size(); a++) {
                TLRPC.TL_dialog dialg = dialogs.get(a);
                boolean isMuted = MessagesController.getInstance().isDialogMuted(dialg.id);
                if (!isMuted || !countNotMuted) {
                    int i = dialg.unread_count;
                    if (i > 0) {
                        if (countDialogs) {
                            if (i > 0) unreadCount = unreadCount + 1;
                        } else {
                            unreadCount = unreadCount + i;
                        }
                        if (i > 0 && !isMuted) allMuted = false;
                    }
                }
            }
        }

        if (unreadCount == 0) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText("" + unreadCount);

            SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
            int size = themePrefs.getInt("chatsHeaderTabCounterSize", 11);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
            tv.setPadding(AndroidUtilities.dp(size > 10 ? size - 7 : 4), 0, AndroidUtilities.dp(size > 10 ? size - 7 : 4), 0);
            int cColor = themePrefs.getInt("chatsHeaderTabCounterColor", 0xffffffff);
            if (allMuted) {
                tv.getBackground().setColorFilter(themePrefs.getInt("chatsHeaderTabCounterSilentBGColor", 0xffb9b9b9), PorterDuff.Mode.SRC_IN);
                tv.setTextColor(cColor);
            } else {
                tv.getBackground().setColorFilter(themePrefs.getInt("chatsHeaderTabCounterBGColor", 0xffd32f2f), PorterDuff.Mode.SRC_IN);
                tv.setTextColor(cColor);
            }
        }
    }

    private void updateListBG() {


        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int mainColor = themePrefs.getInt("chatsRowColor", 0xffffffff);
        int value = themePrefs.getInt("chatsRowGradient", 0);
        boolean b = true;//themePrefs.getBoolean("chatsRowGradientListCheck", false);
        if (value > 0 && b) {
            GradientDrawable.Orientation go;
            switch (value) {
                case 2:
                    go = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case 3:
                    go = GradientDrawable.Orientation.TL_BR;
                    break;
                case 4:
                    go = GradientDrawable.Orientation.BL_TR;
                    break;
                default:
                    go = GradientDrawable.Orientation.TOP_BOTTOM;
            }

            int gradColor = themePrefs.getInt("chatsRowGradientColor", 0xffffffff);
            int[] colors = new int[]{mainColor, gradColor};
            GradientDrawable gd = new GradientDrawable(go, colors);
            listView.setBackgroundDrawable(gd);
        } else {
            listView.setBackgroundColor(mainColor);
        }
    }

    public void setDelegate(HiddenChats.HiddenChatsDelegate delegate) {
        this.delegate = delegate;
    }



    private void didSelectResult(final long dialog_id, boolean useAlert, final boolean param) {
        if (addToGroupAlertString == null) {
            if ((int) dialog_id < 0 && ChatObject.isChannel(-(int) dialog_id) && !ChatObject.isCanWriteToChannel(-(int) dialog_id)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
                builder.setMessage(LocaleController.getString("ChannelCantSendMessage", R.string.ChannelCantSendMessage));
                builder.setNegativeButton(LocaleController.getString("OK", R.string.OK), null);
                showDialog(builder.create());
                return;
            }
        }
        if (useAlert && (selectAlertString != null && selectAlertStringGroup != null || addToGroupAlertString != null)) {
            if (getParentActivity() == null) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("AppName", R.string.AppName));
            int lower_part = (int) dialog_id;
            int high_id = (int) (dialog_id >> 32);
            if (lower_part != 0) {
                if (high_id == 1) {
                    TLRPC.Chat chat = MessagesController.getInstance().getChat(lower_part);
                    if (chat == null) {
                        return;
                    }
                    builder.setMessage(LocaleController.formatStringSimple(selectAlertStringGroup, chat.title));
                } else {
                    if (lower_part > 0) {
                        TLRPC.User user = MessagesController.getInstance().getUser(lower_part);
                        if (user == null) {
                            return;
                        }
                        builder.setMessage(LocaleController.formatStringSimple(selectAlertString, UserObject.getUserName(user)));
                    } else if (lower_part < 0) {
                        TLRPC.Chat chat = MessagesController.getInstance().getChat(-lower_part);
                        if (chat == null) {
                            return;
                        }
                        if (addToGroupAlertString != null) {
                            builder.setMessage(LocaleController.formatStringSimple(addToGroupAlertString, chat.title));
                        } else {
                            builder.setMessage(LocaleController.formatStringSimple(selectAlertStringGroup, chat.title));
                        }
                    }
                }
            } else {
                TLRPC.EncryptedChat chat = MessagesController.getInstance().getEncryptedChat(high_id);
                TLRPC.User user = MessagesController.getInstance().getUser(chat.user_id);
                if (user == null) {
                    return;
                }
                builder.setMessage(LocaleController.formatStringSimple(selectAlertString, UserObject.getUserName(user)));
            }

            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    didSelectResult(dialog_id, false, false);
                }
            });
            builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
            showDialog(builder.create());
        } else {
            if (delegate != null) {
                delegate.didSelectDialog(HiddenChats.this, dialog_id, param);
                delegate = null;
            } else {
                finishFragment();
            }
        }
    }

    private String getHeaderTitle() {

        String title = hiddenCode == 0123 ? LocaleController.getString("HiddenChats", R.string.HiddenChats) :  LocaleController.getString("SpecialHiddenChats", R.string.SpecialHiddenChats) ;

        return title;
    }

    private String getHeaderAllTitles() {


        switch (dialogsType) {
            case 3:
                return LocaleController.getString("Users", R.string.Users);
            case 4:
            case 9:
                return LocaleController.getString("Groups", R.string.Groups);
            case 5:
                return LocaleController.getString("Channels", R.string.Channels);
            case 6:
                return LocaleController.getString("Bots", R.string.Bots);
            case 7:
                return LocaleController.getString("SuperGroups", R.string.SuperGroups);
            case 8:
                return LocaleController.getString("Favorites", R.string.Favorites);
            case 10 :
                return  LocaleController.getString("HiddenChats", R.string.HiddenChats);
            case 12 :
                return LocaleController.getString("SpecialHiddenChats", R.string.SpecialHiddenChats);
            default:
                return getHeaderTitle();
        }
    }


    private void paintHeader(boolean tabs) {
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        actionBar.setTitleColor(themePrefs.getInt("chatsHeaderTitleColor", 0xffffffff));
        int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);
        int hColor = themePrefs.getInt("chatsHeaderColor", def);
        /*if(!tabs){
            actionBar.setBackgroundColor(hColor);
        }else{
            tabsView.setBackgroundColor(hColor);
        }*/
        if (!tabs) actionBar.setBackgroundColor(hColor);
        if (tabs) {
            tabsView.setBackgroundColor(hColor);
        }
        int val = themePrefs.getInt("chatsHeaderGradient", 0);
        if (val > 0) {
            GradientDrawable.Orientation go;
            switch (val) {
                case 2:
                    go = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case 3:
                    go = GradientDrawable.Orientation.TL_BR;
                    break;
                case 4:
                    go = GradientDrawable.Orientation.BL_TR;
                    break;
                default:
                    go = GradientDrawable.Orientation.TOP_BOTTOM;
            }
            int gradColor = themePrefs.getInt("chatsHeaderGradientColor", def);
            int[] colors = new int[]{hColor, gradColor};
            GradientDrawable gd = new GradientDrawable(go, colors);
            if (!tabs) actionBar.setBackgroundDrawable(gd);
            if (tabs) {
                tabsView.setBackgroundDrawable(gd);
            }
            /*if(!tabs){
                actionBar.setBackgroundDrawable(gd);
            }else{
                tabsView.setBackgroundDrawable(gd);
            }*/
        }
    }

    private void updateTheme() {
        paintHeader(false);
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int def = themePrefs.getInt("themeColor", AndroidUtilities.defColor);
        int iconColor = themePrefs.getInt("chatsHeaderIconsColor", 0xffffffff);
        try {
            int hColor = themePrefs.getInt("chatsHeaderColor", def);
            //Customized:
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Bitmap bm = BitmapFactory.decodeResource(getParentActivity().getResources(), R.drawable.ic_launcher);
                ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(getHeaderTitle(), bm, hColor);
                getParentActivity().setTaskDescription(td);
                bm.recycle();
            }


        } catch (NullPointerException e) {
            FileLog.e(e);
        }
        try {
            Drawable search = getParentActivity().getResources().getDrawable(R.drawable.ic_ab_search);
            if (search != null) search.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
            Drawable lockO = getParentActivity().getResources().getDrawable(R.drawable.lock_close);
            if (lockO != null) lockO.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
            Drawable lockC = getParentActivity().getResources().getDrawable(R.drawable.lock_open);
            if (lockC != null) lockC.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
            Drawable clear = getParentActivity().getResources().getDrawable(R.drawable.ic_close_white);
            if (clear != null) clear.setColorFilter(iconColor, PorterDuff.Mode.MULTIPLY);
        } catch (OutOfMemoryError e) {
            FileLog.e(e);
        }
        refreshTabs();
        paintHeader(true);
    }

    private void createTabs(final Context context) {
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = plusPreferences.edit();


        boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
        boolean hideGroups = plusPreferences.getBoolean("hideGroups", false);
        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
        boolean hideChannels = plusPreferences.getBoolean("hideChannels", false);
        boolean hideBots = plusPreferences.getBoolean("hideBots", false);
        boolean hideHiddens = plusPreferences.getBoolean("hideHiddens", false);
        boolean hideFavs = plusPreferences.getBoolean("hideFavs", false);


        hideTabs = plusPreferences.getBoolean("hideTabs", false);

        if (isHiddenMode )
            hideTabs = true;


        disableAnimation = plusPreferences.getBoolean("disableTabsAnimation", false);
        ShowTabsInBottomRow = plusPreferences.getBoolean("ShowTabsInBottomRow", false);

        if (hideUsers && hideGroups && hideSGroups && hideChannels && hideBots && hideHiddens && hideFavs) {
            if (!hideTabs) {
                hideTabs = true;
                editor.putBoolean("hideTabs", true).apply();
            }
        }

        tabsHeight = plusPreferences.getInt("tabsHeight", 40);

        refreshTabAndListViews(false);

        int t = plusPreferences.getInt("defTab", -1);
        selectedTab = t != -1 ? t : plusPreferences.getInt("selTab", 0);

        if (!hideTabs && dialogsType != selectedTab) {
            dialogsType = selectedTab == 4 && hideSGroups ? 9 : selectedTab;
            dialogsAdapter = new DialogsAdapter(context, dialogsType);
            listView.setAdapter(dialogsAdapter);
            dialogsAdapter.notifyDataSetChanged();
        }

        dialogsBackupAdapter = new DialogsAdapter(context, 0);


        if (isHiddenMode && hiddenCode == 0123) {
            dialogsBackupAdapter = new DialogsAdapter(context, 10);
            if (dialogsAdapter != null)
                dialogsAdapter.notifyDataSetChanged();
        }
        if (isHiddenMode && hiddenCode != 0123) {
            dialogsBackupAdapter = new DialogsAdapter(context, 12);
            if (dialogsAdapter != null)
                dialogsAdapter.notifyDataSetChanged();
        }


        tabsLayout = new LinearLayout(context);
        tabsLayout.setOrientation(LinearLayout.HORIZONTAL);
        tabsLayout.setGravity(Gravity.CENTER);

        //1
        allTab = new ImageView(context);
        //allTab.setScaleType(ImageView.ScaleType.CENTER);

        Drawable tab_all = getParentActivity().getResources().getDrawable(R.drawable.tab_all);
        tab_all.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
        allTab.setImageDrawable(tab_all);

        //tabsLayout.addView(allTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));

        allCounter = new TextView(context);
        allCounter.setTag("ALL");
        addTabView(context, allTab, allCounter, true);

        Drawable tab_user = getParentActivity().getResources().getDrawable(R.drawable.tab_user);
        tab_user.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
        //2
        usersTab = new ImageView(context);
        usersTab.setImageDrawable(tab_user);
        /*usersTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideUsers) {
            tabsLayout.addView(usersTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        usersCounter = new TextView(context);
        usersCounter.setTag("USERS");
        addTabView(context, usersTab, usersCounter, !hideUsers);
        //3

        Drawable tab_group = getParentActivity().getResources().getDrawable(R.drawable.tab_group);
        tab_group.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        groupsTab = new ImageView(context);
        groupsTab.setImageDrawable(tab_group);
        /*groupsTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideGroups) {
            tabsLayout.addView(groupsTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        groupsCounter = new TextView(context);
        groupsCounter.setTag("GROUPS");
        addTabView(context, groupsTab, groupsCounter, !hideGroups);
        //4
        Drawable tab_supergroup = getParentActivity().getResources().getDrawable(R.drawable.tab_supergroup);
        tab_supergroup.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        superGroupsTab = new ImageView(context);
        superGroupsTab.setImageDrawable(tab_supergroup);
        /*superGroupsTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideSGroups){
            tabsLayout.addView(superGroupsTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        sGroupsCounter = new TextView(context);
        sGroupsCounter.setTag("SGROUP");
        addTabView(context, superGroupsTab, sGroupsCounter, !hideSGroups);
        //5
        Drawable tab_channel = getParentActivity().getResources().getDrawable(R.drawable.tab_channel);
        tab_channel.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        channelsTab = new ImageView(context);
        channelsTab.setImageDrawable(tab_channel);
        /*channelsTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideChannels){
            tabsLayout.addView(channelsTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        channelsCounter = new TextView(context);
        channelsCounter.setTag("CHANNELS");
        addTabView(context, channelsTab, channelsCounter, !hideChannels);
        //6
        Drawable tab_bot = getParentActivity().getResources().getDrawable(R.drawable.tab_bot);
        tab_bot.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        botsTab = new ImageView(context);
        botsTab.setImageDrawable(tab_bot);
        /*botsTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideBots){
            tabsLayout.addView(botsTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        botsCounter = new TextView(context);
        botsCounter.setTag("BOTS");
        addTabView(context, botsTab, botsCounter, !hideBots);

        //7
        //Customized:
        Drawable tab_hidden = getParentActivity().getResources().getDrawable(R.drawable.tab_hidden);
        tab_bot.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        hiddenTab = new ImageView(context);
        hiddenTab.setImageDrawable(tab_hidden);
        botsCounter = new TextView(context);
        botsCounter.setTag("HIDDENS");
        addTabView(context, hiddenTab, botsCounter, !hideHiddens);
        //8
        Drawable tab_favs = getParentActivity().getResources().getDrawable(R.drawable.tab_favs);
        tab_favs.setColorFilter(AndroidUtilities.getIntDef("chatHeaderIconsColor", 0xffffffff), PorterDuff.Mode.MULTIPLY);

        favsTab = new ImageView(context);
        favsTab.setImageDrawable(tab_favs);
        /*favsTab.setScaleType(ImageView.ScaleType.CENTER);
        if(!hideFavs){
            tabsLayout.addView(favsTab, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }*/
        favsCounter = new TextView(context);
        favsCounter.setTag("FAVS");
        addTabView(context, favsTab, favsCounter, !hideFavs);

        tabsView.addView(tabsLayout, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        allTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 0) {
                    dialogsType = 0;
                    refreshAdapter(context);
                }
            }
        });

        allTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("All", R.string.All));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 0;
                final int def = plusPreferences.getInt("defTab", -1);
                final int sort = plusPreferences.getInt("sortAll", 0);

                CharSequence cs2 = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                CharSequence cs0 = LocaleController.getString("HideShowTabs", R.string.HideShowTabs);
                items = new CharSequence[]{cs0, cs1, cs2, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                            createTabsDialog(context, builder);
                            builder.setNegativeButton(LocaleController.getString("Done", R.string.Done), null);
                            showDialog(builder.create());
                        } else if (which == 1) {
                            editor.putInt("sortAll", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 3) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        usersTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 3) {
                    dialogsType = 3;
                    refreshAdapter(context);
                }
            }
        });

        usersTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("Users", R.string.Users));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 3;
                final int sort = plusPreferences.getInt("sortUsers", 0);
                final int def = plusPreferences.getInt("defTab", -1);
                CharSequence cs = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                items = new CharSequence[]{sort == 0 ? LocaleController.getString("SortByStatus", R.string.SortByStatus) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage), cs, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 1) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 0) {
                            editor.putInt("sortUsers", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        groupsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
                int i = hideSGroups ? 9 : 4;
                if (dialogsType != i) {
                    dialogsType = i;
                    refreshAdapter(context);
                }
            }
        });

        groupsTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("Groups", R.string.Groups));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
                final int tabVal = 4;
                final int sort = plusPreferences.getInt("sortGroups", 0);
                final int def = plusPreferences.getInt("defTab", -1);

                CharSequence cs2 = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                CharSequence cs0 = hideSGroups ? LocaleController.getString("ShowSuperGroupsTab", R.string.ShowSuperGroupsTab) : LocaleController.getString("HideSuperGroupsTab", R.string.HideSuperGroupsTab);
                items = new CharSequence[]{cs0, cs1, cs2, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {

                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 0) {
                            RelativeLayout rl = (RelativeLayout) superGroupsTab.getParent();
                            editor.putBoolean("hideSGroups", !hideSGroups).apply();
                            if (!hideSGroups) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 7) {
                                    dialogsType = 9;
                                    refreshAdapter(context);
                                }
                            } else {
                                boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
                                tabsLayout.addView(rl, hideUsers ? 2 : 3, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                            unreadCountGroups();
                        } else if (which == 1) {
                            editor.putInt("sortGroups", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 3) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        superGroupsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 7) {
                    dialogsType = 7;
                    refreshAdapter(context);
                }
            }
        });

        superGroupsTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("SuperGroups", R.string.SuperGroups));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 7;
                final int def = plusPreferences.getInt("defTab", -1);
                final int sort = plusPreferences.getInt("sortSGroups", 0);
                final boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
                CharSequence cs2 = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                CharSequence cs0 = hideSGroups ? LocaleController.getString("ShowSuperGroupsTab", R.string.ShowSuperGroupsTab) : LocaleController.getString("HideSuperGroupsTab", R.string.HideSuperGroupsTab);
                items = new CharSequence[]{cs0, cs1, cs2, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();

                        if (which == 0) {
                            RelativeLayout rl = (RelativeLayout) superGroupsTab.getParent();
                            editor.putBoolean("hideSGroups", !hideSGroups).apply();
                            if (!hideSGroups) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 7) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                            } else {
                                tabsLayout.addView(rl, 3, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                            unreadCountGroups();
                        } else if (which == 1) {
                            editor.putInt("sortSGroups", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 3) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        channelsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 5) {
                    dialogsType = 5;
                    refreshAdapter(context);
                }
            }
        });

        channelsTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("Channels", R.string.Channels));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 5;
                final int sort = plusPreferences.getInt("sortChannels", 0);
                final int def = plusPreferences.getInt("defTab", -1);
                CharSequence cs = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                items = new CharSequence[]{cs1, cs, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 1) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 0) {
                            editor.putInt("sortChannels", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            markAsReadDialog(true);
                        }

                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        botsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 6) {
                    dialogsType = 6;
                    refreshAdapter(context);
                }
            }
        });

        botsTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("Bots", R.string.Bots));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 6;
                final int sort = plusPreferences.getInt("sortBots", 0);
                final int def = plusPreferences.getInt("defTab", -1);
                CharSequence cs = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                items = new CharSequence[]{cs1, cs, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 1) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 0) {
                            editor.putInt("sortBots", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        hiddenTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 10) {
                    dialogsType = 10;
                    refreshAdapter(context);
                }
            }
        });

        hiddenTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("HiddenChats", R.string.HiddenChats));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 9;
                final int sort = plusPreferences.getInt("sortHiddens", 0);
                final int def = plusPreferences.getInt("defTab", -1);
                CharSequence cs = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                items = new CharSequence[]{cs1, cs, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 1) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 0) {
                            editor.putInt("sortHiddens", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            markAsReadDialog(true);
                        }
                    }
                });
                showDialog(builder.create());
                return true;
            }
        });

        favsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogsType != 8) {
                    dialogsType = 8;
                    refreshAdapter(context);
                }
            }
        });

        favsTab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getParentActivity());
                builder.setTitle(LocaleController.getString("Favorites", R.string.Favorites));
                CharSequence items[];
                SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                final int tabVal = 8;
                final int sort = plusPreferences.getInt("sortFavs", 0);
                final int def = plusPreferences.getInt("defTab", -1);
                CharSequence cs = def == tabVal ? LocaleController.getString("ResetDefaultTab", R.string.ResetDefaultTab) : LocaleController.getString("SetAsDefaultTab", R.string.SetAsDefaultTab);
                CharSequence cs1 = sort == 0 ? LocaleController.getString("SortByUnreadCount", R.string.SortByUnreadCount) : LocaleController.getString("SortByLastMessage", R.string.SortByLastMessage);
                items = new CharSequence[]{cs1, cs, LocaleController.getString("MarkAllAsRead", R.string.MarkAllAsRead)};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();
                        if (which == 1) {
                            editor.putInt("defTab", def == tabVal ? -1 : tabVal).apply();
                        } else if (which == 0) {
                            editor.putInt("sortFavs", sort == 0 ? 1 : 0).apply();
                            if (dialogsAdapter.getItemCount() > 1) {
                                dialogsAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            markAsReadDialog(true);
                        }

                    }
                });
                showDialog(builder.create());
                return true;
            }
        });


    }



    private void addTabView(Context context, ImageView iv, TextView tv, boolean show) {
        //SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        //int cColor = themePrefs.getInt("chatsHeaderTabCounterColor", 0xffffffff);
        //int bgColor = themePrefs.getInt("chatsHeaderTabCounterBGColor", 0xffff0000);

        iv.setScaleType(ImageView.ScaleType.CENTER);
        //int size = themePrefs.getInt("chatsHeaderTabCounterSize", 11);
        //tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        tv.setGravity(Gravity.CENTER);
        //tv.setTextColor(cColor);

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(AndroidUtilities.dp(32));
        //shape.setColor(bgColor);

        tv.setBackgroundDrawable(shape);
        //tv.setPadding(AndroidUtilities.dp(size > 10 ? size - 7 : 4), 0, AndroidUtilities.dp(size > 10 ? size - 7 : 4), 0);
        RelativeLayout layout = new RelativeLayout(context);
        layout.addView(iv, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        layout.addView(tv, LayoutHelper.createRelative(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, 0, 0, 3, 6, RelativeLayout.ALIGN_PARENT_RIGHT));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        tv.setLayoutParams(params);
        if (show) {
            tabsLayout.addView(layout, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
        }

    }

    private AlertDialog.Builder createTabsDialog(final Context context, AlertDialog.Builder builder) {
        builder.setTitle(LocaleController.getString("HideShowTabs", R.string.HideShowTabs));

        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
        boolean hideGroups = plusPreferences.getBoolean("hideGroups", false);
        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
        boolean hideChannels = plusPreferences.getBoolean("hideChannels", false);
        boolean hideBots = plusPreferences.getBoolean("hideBots", false);
        boolean hideHiddens = plusPreferences.getBoolean("hideHiddens", false);
        boolean hideFavs = plusPreferences.getBoolean("hideFavs", false);

        builder.setMultiChoiceItems(
                new CharSequence[]{LocaleController.getString("Users", R.string.Users), LocaleController.getString("Groups", R.string.Groups), LocaleController.getString("SuperGroups", R.string.SuperGroups), LocaleController.getString("Channels", R.string.Channels), LocaleController.getString("Bots", R.string.Bots), LocaleController.getString("HiddenChats", R.string.HiddenChats), LocaleController.getString("Favorites", R.string.Favorites)},
                new boolean[]{!hideUsers, !hideGroups, !hideSGroups, !hideChannels, !hideBots, !hideHiddens, !hideFavs},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = plusPreferences.edit();

                        boolean hide = plusPreferences.getBoolean("hideTabs", false);

                        boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
                        boolean hideGroups = plusPreferences.getBoolean("hideGroups", false);
                        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
                        boolean hideChannels = plusPreferences.getBoolean("hideChannels", false);
                        boolean hideBots = plusPreferences.getBoolean("hideBots", false);
                        boolean hideHiddens = plusPreferences.getBoolean("hideHiddens", false);
                        boolean hideFavs = plusPreferences.getBoolean("hideFavs", false);

                        if (which == 0) {
                            RelativeLayout rl = (RelativeLayout) usersTab.getParent();
                            editor.putBoolean("hideUsers", !hideUsers).apply();
                            if (!hideUsers) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 3) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideUsers = true;
                            } else {
                                tabsLayout.addView(rl, 1, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        } else if (which == 1) {
                            RelativeLayout rl = (RelativeLayout) groupsTab.getParent();
                            editor.putBoolean("hideGroups", !hideGroups).apply();
                            if (!hideGroups) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 4) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideGroups = true;
                            } else {
                                tabsLayout.addView(rl, hideUsers ? 1 : 2, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        } else if (which == 2) {
                            RelativeLayout rl = (RelativeLayout) superGroupsTab.getParent();
                            editor.putBoolean("hideSGroups", !hideSGroups).apply();
                            if (!hideSGroups) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 7) {
                                    dialogsType = 4;
                                    refreshAdapter(context);
                                }
                                hideSGroups = true;
                            } else {
                                int pos = 3;
                                if (hideUsers) pos = pos - 1;
                                if (hideGroups) pos = pos - 1;
                                tabsLayout.addView(rl, pos, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        } else if (which == 3) {
                            RelativeLayout rl = (RelativeLayout) channelsTab.getParent();
                            editor.putBoolean("hideChannels", !hideChannels).apply();
                            if (!hideChannels) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 5) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideChannels = true;
                            } else {
                                int place = tabsLayout.getChildCount();
                                if (!hideFavs) --place;
                                if (!hideBots) --place;
                                tabsLayout.addView(rl, place, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        } else if (which == 4) {
                            RelativeLayout rl = (RelativeLayout) botsTab.getParent();
                            editor.putBoolean("hideBots", !hideBots).apply();
                            if (!hideBots) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 6) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideBots = true;
                            } else {
                                int place = tabsLayout.getChildCount();
                                if (!hideFavs) --place;
                                tabsLayout.addView(rl, place, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f, Gravity.TOP, 0, 0, 0, 0));
                            }
                        } else if (which == 5) {
                            RelativeLayout rl = (RelativeLayout) hiddenTab.getParent();
                            editor.putBoolean("hideHiddens", !hideHiddens).apply();
                            if (!hideHiddens) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 10) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideHiddens = true;
                            } else {
                                tabsLayout.addView(rl, tabsLayout.getChildCount(), LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        } else if (which == 6) {
                            RelativeLayout rl = (RelativeLayout) favsTab.getParent();
                            editor.putBoolean("hideFavs", !hideFavs).apply();
                            if (!hideFavs) {
                                tabsLayout.removeView(rl);
                                if (dialogsType == 8) {
                                    dialogsType = 0;
                                    refreshAdapter(context);
                                }
                                hideFavs = true;
                            } else {
                                tabsLayout.addView(rl, tabsLayout.getChildCount(), LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
                            }
                        }
                        if (hideUsers && hideGroups && hideSGroups && hideChannels && hideBots && hideFavs) {
                            hideTabs = true;
                            editor.putBoolean("hideTabs", true).apply();
                            refreshTabAndListViews(true);
                        }
                        if (isChecked && hide) {
                            hideTabs = false;
                            editor.putBoolean("hideTabs", false).apply();
                            refreshTabAndListViews(false);
                        }
                    }
                });
        return builder;
    }

    private void refreshAdapter(Context context) {

        refreshAdapterAndTabs(new DialogsAdapter(context, dialogsType));
    }

    private void refreshAdapterAndTabs(DialogsAdapter adapter) {
        dialogsAdapter = adapter;
        listView.setAdapter(dialogsAdapter);
        dialogsAdapter.notifyDataSetChanged();
        if (!onlySelect) {
            selectedTab = dialogsType == 9 ? 4 : dialogsType;
            SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = plusPreferences.edit();
            editor.putInt("selTab", selectedTab).apply();
        }
        refreshTabs();
    }

    private void refreshTabs() {
        //resetTabs();
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);
        int defColor = themePrefs.getInt("chatsHeaderIconsColor", 0xffffffff);
        int iconColor = themePrefs.getInt("chatsHeaderTabIconColor", defColor);

        int iColor = themePrefs.getInt("chatsHeaderTabUnselectedIconColor", AndroidUtilities.getIntAlphaColor("chatsHeaderTabIconColor", defColor, 0.3f));

        allTab.setBackgroundResource(0);
        usersTab.setBackgroundResource(0);
        groupsTab.setBackgroundResource(0);
        superGroupsTab.setBackgroundResource(0);
        channelsTab.setBackgroundResource(0);
        botsTab.setBackgroundResource(0);
        hiddenTab.setBackgroundResource(0);
        favsTab.setBackgroundResource(0);

        allTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        usersTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        groupsTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        superGroupsTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        channelsTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        botsTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        hiddenTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);
        favsTab.setColorFilter(iColor, PorterDuff.Mode.SRC_IN);

        Drawable selected = getParentActivity().getResources().getDrawable(R.drawable.tab_selected);
        selected.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);

        switch (dialogsType == 9 ? 4 : dialogsType) {
            case 3:
                usersTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                usersTab.setBackgroundDrawable(selected);
                break;
            case 4:
                groupsTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                groupsTab.setBackgroundDrawable(selected);
                break;
            case 5:
                channelsTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                channelsTab.setBackgroundDrawable(selected);
                break;
            case 6:
                botsTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                botsTab.setBackgroundDrawable(selected);
                break;
            case 7:
                superGroupsTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                superGroupsTab.setBackgroundDrawable(selected);
                break;
            case 8:
                favsTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                favsTab.setBackgroundDrawable(selected);
                break;
            case 10:
                hiddenTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                hiddenTab.setBackgroundDrawable(selected);
                break;
            default:
                allTab.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
                allTab.setBackgroundDrawable(selected);
        }

        String t = getHeaderAllTitles();
        actionBar.setTitle(t);
        paintHeader(true);

        if (getDialogsArray() != null && getDialogsArray().isEmpty()) {
            progressView.setVisibility(View.GONE);

            if (emptyView.getChildCount() > 0) {
                TextView tv = (TextView) emptyView.getChildAt(0);
                if (tv != null) {
                    tv.setText(dialogsType < 3 ? LocaleController.getString("NoChats", R.string.NoChats) : dialogsType == 8 ? LocaleController.getString("NoFavoritesHelp", R.string.NoFavoritesHelp) : t);
                    tv.setTextColor(themePrefs.getInt("chatsNameColor", 0xff212121));
                }
                if (emptyView.getChildAt(1) != null)
                    emptyView.getChildAt(1).setVisibility(View.GONE);
            }

            emptyView.setVisibility(View.VISIBLE);
            emptyView.setBackgroundColor(themePrefs.getInt("chatsRowColor", 0xffffffff));
            listView.setEmptyView(emptyView);
        }
    }

    private void hideShowTabs(int i) {
        RelativeLayout rl = null;
        int pos = 0;
        boolean b = false;
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
        boolean hideGroups = plusPreferences.getBoolean("hideGroups", false);
        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
        boolean hideBots = plusPreferences.getBoolean("hideBots", false);
        boolean hideHiddens = plusPreferences.getBoolean("hideHiddens", false);
        boolean hideFavs = plusPreferences.getBoolean("hideFavs", false);
        switch (i) {
            case 0: // Users
                rl = (RelativeLayout) usersTab.getParent();
                pos = 1;
                b = hideUsers;
                break;
            case 1: //Groups
                rl = (RelativeLayout) groupsTab.getParent();
                pos = hideUsers ? 1 : 2;
                b = hideGroups;
                break;
            case 2: //Supergroups
                rl = (RelativeLayout) superGroupsTab.getParent();
                pos = 3;
                if (hideGroups) pos = pos - 1;
                if (hideUsers) pos = pos - 1;
                b = hideSGroups;
                break;
            case 3: //Channels
                rl = (RelativeLayout) channelsTab.getParent();
                pos = tabsLayout.getChildCount();
                if (!hideBots) pos = pos - 1;
                if (!hideFavs) pos = pos - 1;
                b = plusPreferences.getBoolean("hideChannels", false);
                break;
            case 4: //Bots
                rl = (RelativeLayout) botsTab.getParent();
                pos = tabsLayout.getChildCount();
                if (!hideFavs) pos = pos - 1;
                b = hideBots;
                break;
            case 5: //hiddens
                rl = (RelativeLayout) hiddenTab.getParent();
                pos = tabsLayout.getChildCount();
                b = hideHiddens;
                break;

            case 6: //Favorites
                rl = (RelativeLayout) favsTab.getParent();
                pos = tabsLayout.getChildCount();
                b = hideFavs;
                break;
            default:
                updateTabs();
        }

        if (rl != null) {
            if (!b) {
                tabsLayout.addView(rl, pos, LayoutHelper.createLinear(0, LayoutHelper.MATCH_PARENT, 1.0f));
            } else {
                tabsLayout.removeView(rl);
            }
        }

    }



    private void updateTabs() {


        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        hideTabs = plusPreferences.getBoolean("hideTabs", false);
        disableAnimation = plusPreferences.getBoolean("disableTabsAnimation", false);
        ShowTabsInBottomRow = plusPreferences.getBoolean("ShowTabsInBottomRow", false);

        tabsHeight = plusPreferences.getInt("tabsHeight", 40);

        refreshTabAndListViews(false);

        if (hideTabs && dialogsType > 2) {
            dialogsType = 0;
            refreshAdapterAndTabs(dialogsBackupAdapter);
        }


        //hideTabsAnimated(false);
    }

    private void refreshTabAndListViews(boolean forceHide) {
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean disable = preferences.getBoolean("ShowTabsInBottomRow", false);
        if (hideTabs || forceHide) {
            tabsView.setVisibility(View.GONE);
            listView.setPadding(0, 0, 0, 0);
        } else {
            tabsView.setVisibility(View.VISIBLE);
            int h = AndroidUtilities.dp(tabsHeight);

            ViewGroup.LayoutParams params = tabsView.getLayoutParams();

            if (params != null) {
                params.height = h;
            }
            tabsView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, tabsHeight, disable ? Gravity.BOTTOM : Gravity.TOP, 0, 0, 0, 0));

            listView.setPadding(0, disable ? 0 : h, 0, disable ? h : 0);
            hideTabsAnimated(false);
        }
        listView.scrollToPosition(0);
    }

    private void hideTabsAnimated(final boolean hide) {
        if (tabsHidden == hide) {
            return;
        }
        tabsHidden = hide;
        if (hide) listView.setPadding(0, 0, 0, 0);
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean disable = preferences.getBoolean("ShowTabsInBottomRow", false);

        ObjectAnimatorProxy animator = ObjectAnimatorProxy.ofFloatProxy(tabsView, "translationY", hide ? (disable ? AndroidUtilities.dp(tabsHeight) : -AndroidUtilities.dp(tabsHeight)) : 0).setDuration(300);
        animator.addListener(new AnimatorListenerAdapterProxy() {
            @Override
            public void onAnimationEnd(Object animation) {
                if (!tabsHidden) listView.setPadding(0, AndroidUtilities.dp(tabsHeight), 0, 0);
            }
        });
        animator.start();
    }

    private void refreshDialogType(int d) {
        if (hideTabs) return;
        SharedPreferences plusPreferences = ApplicationLoader.applicationContext.getSharedPreferences("TelegramConfig", Activity.MODE_PRIVATE);
        boolean hideUsers = plusPreferences.getBoolean("hideUsers", false);
        boolean hideGroups = plusPreferences.getBoolean("hideGroups", false);
        boolean hideSGroups = plusPreferences.getBoolean("hideSGroups", false);
        boolean hideChannels = plusPreferences.getBoolean("hideChannels", false);
        boolean hideBots = plusPreferences.getBoolean("hideBots", false);
        boolean hideFavs = plusPreferences.getBoolean("hideFavs", false);
        boolean loop = plusPreferences.getBoolean("infiniteTabsSwipe", false);
        if (d == 1) {
            switch (dialogsType) {
                case 3: // Users
                    if (hideGroups) {
                        dialogsType = !hideSGroups ? 7 : !hideChannels ? 5 : !hideBots ? 6 : !hideFavs ? 8 : loop ? 0 : dialogsType;
                    } else {
                        dialogsType = hideSGroups ? 9 : 4;
                    }
                    break;
                case 4: //Groups
                    dialogsType = !hideSGroups ? 7 : !hideChannels ? 5 : !hideBots ? 6 : !hideFavs ? 8 : loop ? 0 : dialogsType;
                    break;
                case 9: //Groups
                case 7: //Supergroups
                    dialogsType = !hideChannels ? 5 : !hideBots ? 6 : !hideFavs ? 8 : loop ? 0 : dialogsType;
                    break;
                case 5: //Channels
                    dialogsType = !hideBots ? 6 : !hideFavs ? 8 : loop ? 0 : dialogsType;
                    break;
                case 6: //Bots
                    dialogsType = !hideFavs ? 8 : loop ? 0 : dialogsType;
                    break;
                case 8: //Favorites
                    if (loop) {
                        dialogsType = 0;
                    }
                    break;
                default: //All
                    dialogsType = !hideUsers ? 3 : !hideGroups && hideSGroups ? 9 : !hideGroups ? 7 : !hideChannels ? 5 : !hideBots ? 6 : !hideFavs ? 8 : loop ? 0 : dialogsType;
            }
        } else {
            switch (dialogsType) {
                case 3: // Users
                    dialogsType = 0;
                    break;
                case 4: //Groups
                case 9: //Groups
                    dialogsType = !hideUsers ? 3 : 0;
                    break;
                case 7: //Supergroups
                    dialogsType = !hideGroups ? 4 : !hideUsers ? 3 : 0;
                    break;
                case 5: //Channels
                    dialogsType = !hideSGroups ? 7 : !hideGroups ? 9 : !hideUsers ? 3 : 0;
                    break;
                case 6: //Bots
                    dialogsType = !hideChannels ? 5 : !hideSGroups ? 7 : !hideGroups ? 9 : !hideUsers ? 3 : 0;
                    break;
                case 8: //Favorites
                    dialogsType = !hideBots ? 6 : !hideChannels ? 5 : !hideSGroups ? 7 : !hideGroups ? 9 : !hideUsers ? 3 : 0;
                    break;
                default: //All
                    if (loop) {
                        dialogsType = !hideFavs ? 8 : !hideBots ? 6 : !hideChannels ? 5 : !hideSGroups ? 7 : !hideGroups ? 9 : !hideUsers ? 3 : 0;
                    }
            }
        }

    }

    @Override
    public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, TLRPC.FileLocation fileLocation, int index) {
        if (fileLocation == null) {
            return null;
        }

        TLRPC.FileLocation photoBig = null;
        if (user_id != 0) {
            TLRPC.User user = MessagesController.getInstance().getUser(user_id);
            if (user != null && user.photo != null && user.photo.photo_big != null) {
                photoBig = user.photo.photo_big;
            }
        } else if (chat_id != 0) {
            TLRPC.Chat chat = MessagesController.getInstance().getChat(chat_id);
            if (chat != null && chat.photo != null && chat.photo.photo_big != null) {
                photoBig = chat.photo.photo_big;
            }
        }

        if (photoBig != null && photoBig.local_id == fileLocation.local_id && photoBig.volume_id == fileLocation.volume_id && photoBig.dc_id == fileLocation.dc_id) {
            int coords[] = new int[2];
            avatarImage.getLocationInWindow(coords);
            PhotoViewer.PlaceProviderObject object = new PhotoViewer.PlaceProviderObject();
            object.viewX = coords[0];
            object.viewY = coords[1] - AndroidUtilities.statusBarHeight;
            object.parentView = avatarImage;
            object.imageReceiver = avatarImage.getImageReceiver();
            object.dialogId = user_id;
            object.thumb = object.imageReceiver.getBitmap();
            object.size = -1;
            object.radius = avatarImage.getImageReceiver().getRoundRadius();
            return object;
        }
        return null;
    }

    @Override
    public Bitmap getThumbForPhoto(MessageObject messageObject, TLRPC.FileLocation fileLocation, int index) {
        return null;
    }

    @Override
    public void willSwitchFromPhoto(MessageObject messageObject, TLRPC.FileLocation fileLocation, int index) {

    }

    @Override
    public void willHidePhotoViewer() {

    }

    @Override
    public boolean isPhotoChecked(int index) {
        return false;
    }

    @Override
    public void setPhotoChecked(int index) {

    }

    @Override
    public boolean cancelButtonPressed() {
        return true;
    }

    @Override
    public void sendButtonPressed(int index, VideoEditedInfo videoEditedInfo) {

    }


    @Override
    public int getSelectedCount() {
        return 0;
    }

    @Override
    public void updatePhotoAtIndex(int index) {

    }

    @Override
    public boolean allowCaption() {
        return false;
    }

    @Override
    public boolean scaleToFill() {
        return false;
    }

    public void onMenuItemClick(View clickedView, int position) {

    }

    //    mode 0 for hide a chat and mode 1 for show a chat
    private void DeleteHidden(int dialog_id) {





            hideObjc h = new hideObjc();
            hideDBAdapter.open();
            hideDBAdapter.delete(dialog_id);
            hideDBAdapter.close();
            TLRPC.TL_dialog dialg = MessagesController.getInstance().dialogs_dict.get(selectedDialog);
            if (dialg != null ) {
                if (dialogsType == 10)
                MessagesController.getInstance().dialogsHides.remove(dialg);
                else if (dialogsType == 12)
                    MessagesController.getInstance().dialogsSpecialHides.remove(dialg);
            }

            if (dialogsAdapter != null) {
                dialogsAdapter.notifyDataSetChanged();
            }

            if (dialogsBackupAdapter != null)
                dialogsBackupAdapter.notifyDataSetChanged();


            if (!hideTabs) {
                updateTabs();
            }



    }


}

