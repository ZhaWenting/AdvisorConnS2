package alphabet.adviserconn.InvitationFromDarkNet.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import alphabet.adviserconn.InvitationFromDarkNet.adapter.ChatAdapter;
import alphabet.adviserconn.InvitationFromDarkNet.bean.Season2ChatBean;
import alphabet.adviserconn.InvitationFromDarkNet.utils.ConvertString;
import alphabet.adviserconn.InvitationFromDarkNet.utils.TimeUtils;
import alphabet.adviserconn.InvitationFromDarkNet.widget.ChooseAvatarDialog;
import alphabet.adviserconn.InvitationFromDarkNet.widget.ConfigDialogS2;
import alphabet.adviserconn.InvitationFromDarkNet.widget.EndingsDialogS2;
import alphabet.adviserconn.InvitationFromDarkNet.widget.InfoDialog;
import alphabet.adviserconn.InvitationFromDarkNet.widget.StaffDialogS2;
import alphabet.adviserconn.InvitationFromDarkNet.widget.StartDialogS2;
import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import alphabet.adviserconn.utils.FileUtils;
import alphabet.adviserconn.utils.LogUtil;
import alphabet.adviserconn.InvitationFromDarkNet.widget.TagsDialog;
import alphabet.adviserconn.widget.MyAlertDialog;
import butterknife.BindView;
import butterknife.OnClick;

public class XEAChatActivityS2 extends BaseActivity implements View.OnTouchListener, ChatAdapter.ClickTextListener, ChatAdapter.LinkedTextListener {
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.send_text_season2)
    TextView sendTextSeason2;
    @BindView(R.id.send_bt_season2)
    ImageView sendBtSeason2;
    @BindView(R.id.avatar_ll)
    LinearLayout avatarLl;

    StartDialogS2 startDialogS2;


    public static final int TEST_SPEED = 500;
    public static final int SLOW_SPEED = 3000;
    public static final int NORMAL_SPEED = 2000;
    public static final int FAST_SPEED = 1000;
    int delayTime = NORMAL_SPEED;

    Resources resources;
    Context context;
    Gson gson;
    AssetManager assetManager = null;

    int position = 0;
    int count = 0;
    int mainTxtCount = 0;
    int branchTxtCount = 0;
    boolean isBranch = false;
    boolean isSelectionMode = false;
    boolean isMultiSelection = false;
    boolean isConfirmMode = false;
    private final String TAG = this.getClass().toString();
    String optionContent = "";
    int saveChapter;
    boolean isEnding = false;

    Timer timer = new Timer();
    TimerTask timerTask;
    ChatAdapter chatAdapter;

    ArrayList<Season2ChatBean> currentList = new ArrayList<>();
    ArrayList<Season2ChatBean> mainList = new ArrayList<>();
    ArrayList<Season2ChatBean> branchList = new ArrayList<>();
    ArrayList<Season2ChatBean> saveList = new ArrayList<>();
    ArrayList<Season2ChatBean> autoSavedList = new ArrayList<>();

    Season2ChatBean currentBean;
    Season2ChatBean lastBean;

    TagsDialog tagsDialog;
    String answer;
    int selection = 0;
    int savePosition = 0;
    InfoDialog infoDialog;

    @Override
    protected void initView() {

        avatarLl.setEnabled(false);
        sendTextSeason2.setEnabled(false);
        sendBtSeason2.setEnabled(false);

        resources = getResources();
        gson = new Gson();

        context = getBaseContext();
        assetManager = getAssets();

        intiItemAnima();

        //auto load
        autoSavedList = systemParamsS2.getDataListSeason2("autoSavedListS2");
        mainTxtCount = systemParamsS2.getInt("autoSavedMainCountS2", 0);
        savePosition = systemParamsS2.getInt("autoSavedPositionS2", 0);
        String chapter = systemParamsS2.getString("autoSavedChapter", "");

        if (autoSavedList != null && autoSavedList.size() > 0 && savePosition > 0) {
            if ("".equals(chapter)) {
                saveChapter = R.raw.season2_day1;
            }
            else {
                saveChapter = ConvertString.convert(chapter);
                if(!chapter.equals("$season2_day1$"))
                    avatarLl.setEnabled(true);
            }

            currentList.clear();
            saveList.clear();
            for (int i = 0; i < autoSavedList.size(); i++) {
                currentList.add(autoSavedList.get(i));
                saveList.add(autoSavedList.get(i));
            }
            position = savePosition;

            // load history
            if (chatAdapter == null) {
                chatAdapter = new ChatAdapter(XEAChatActivityS2.this, currentList, XEAChatActivityS2.this, XEAChatActivityS2.this, this);
                LogUtil.d(TAG, "initView->chatAdapter");
                recyclerview.setAdapter(chatAdapter);
                recyclerview.scrollToPosition(position);
            }

//            LogUtil.d(TAG, "autoSaved->autoSavedList:" + autoSavedList.size() + " chatAdapter:" + chatAdapter.getItemCount()
//                    +" savePosition"+savePosition+" mainTxtCount"+mainTxtCount+" saveChapter"+saveChapter);
            //load new
            InputStream inputStream = getResources().openRawResource(saveChapter);
            String chapter1 = FileUtils.getString(inputStream);
            try {
                inputStream.close();
                mainList = gson.fromJson(chapter1, new TypeToken<List<Season2ChatBean>>() {
                }.getType());
                currentList = mainList;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
//            LogUtil.d(TAG, "loadFirst->savePosition"+savePosition+" mainTxtCount"+mainTxtCount+" saveChapter"+saveChapter);
            loadFirst();
        }

        updateTimer();

        infoDialog = new InfoDialog(this);

    }

    @OnClick({R.id.config, R.id.send_text_season2, R.id.send_bt_season2,R.id.avatar_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_ll:
                new ChooseAvatarDialog(this,"XEAChatActivityS2").show();
                break;
            case R.id.config:
                ConfigDialogS2 configDialogS2 = new ConfigDialogS2(this, delayTime, isEnding) {
                    @Override
                    public void restart() {
                        showRestartDialog();
                    }

                    @Override
                    public void endings() {
                        new EndingsDialogS2(XEAChatActivityS2.this,systemParamsS2).show();
                    }

                    @Override
                    public void staff() {
                        new StaffDialogS2(XEAChatActivityS2.this).show();
                    }

                    @Override
                    public void changeTextSpeed(int textSpeed) {
                        switch (textSpeed) {
                            case 1:
                                delayTime = SLOW_SPEED;
                                break;
                            case 2:
                                delayTime = NORMAL_SPEED;
                                break;
                            case 3:
                                delayTime = FAST_SPEED;
                                break;
                        }
                        updateTimer();
                        showToast("文本速度已调整");
                    }
                };
                configDialogS2.show();
                break;
            case R.id.send_text_season2:
                if (isConfirmMode) {
                    sendTextSeason2.setText(currentBean.getPlayer_single());
                    sendBtSeason2.setEnabled(true);
                } else if (isSelectionMode || isMultiSelection) {
                    tagsDialog.show();
                }
                break;
            case R.id.send_bt_season2:
                if (!TextUtils.isEmpty(sendTextSeason2.getText().toString())) {
                    sendTextSeason2.setEnabled(false);
                    sendBtSeason2.setEnabled(false);
                    currentBean.setContent(sendTextSeason2.getText().toString());
                    sendTextSeason2.setText("");

                    if (isMultiSelection) {
                        LogUtil.d(TAG, "isMultiSelection");
                        position++;
                        Season2ChatBean temp = new Season2ChatBean();
                        temp.setContent(currentBean.getContent());
                        temp.setType(currentBean.getType());
                        chatAdapter.addData(position, temp);
                        saveList.add(temp);
                        if (!isUserTouching)
                            recyclerview.scrollToPosition(position);
                        mainTxtCount++;
                        isSelectionMode = false;
                        isMultiSelection = false;
                        restartTimer();
                    } else if (isSelectionMode) {
                        LogUtil.d(TAG, "isSelectionMode");
                        currentBean.setType("player_clickable");
                        isSelectionMode = false;
                        addItem();
                    } else {
                        LogUtil.d(TAG, "isSingle");
                        currentBean.setType("player_text");
                        isConfirmMode = false;
                        addItem();
                    }

                }
                break;
        }
    }


    private void loadFirst() {
        //Set the first line of the adapter.
        mainTxtCount = 0;
        position = 0;
        saveChapter = R.raw.season2_day1;
        currentBean = new Season2ChatBean();
        currentBean.setType("date");
        long time = systemParamsS2.getLong("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(time);
        currentBean.setContent(format.substring(4, 6) + "月" + format.substring(6, 8) + "日");
        currentList = new ArrayList<>();
        currentList.add(currentBean);
        if (saveList == null)
            saveList = new ArrayList<>();
        saveList.add(currentBean);
        chatAdapter = new ChatAdapter(XEAChatActivityS2.this, currentList, this, this, this);
        recyclerview.setAdapter(chatAdapter);

        InputStream inputStream = getResources().openRawResource(saveChapter);
        String chapter1 = FileUtils.getString(inputStream);
        try {
            inputStream.close();
            mainList = gson.fromJson(chapter1, new TypeToken<List<Season2ChatBean>>() {
            }.getType());
            currentList = mainList;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isBranch)
                            count = branchTxtCount;
                        else
                            count = mainTxtCount;
                        if (count < currentList.size()) {
                            currentBean = currentList.get(count);
                            LogUtil.d(TAG, "type:" + currentBean.getType());
                            switch (currentBean.getType()) {
                                case "ending":
                                    if (lastBean != currentBean) {
                                        String dialogTitle = "";
                                        switch (currentBean.getEnding()){
                                            case "ending_falldown":
                                                dialogTitle = "对方已离线";
                                                systemParamsS2.setBoolean("ending_falldown",true);
                                                break;
                                        }
                                        new MyAlertDialog(XEAChatActivityS2.this,dialogTitle,"重新开始","查看结局") {
                                            @Override
                                            public void buttonOne() {
                                                showRestartDialog();
                                            }

                                            @Override
                                            public void buttonTwo() {
                                                new EndingsDialogS2(XEAChatActivityS2.this,systemParamsS2).show();
                                            }
                                        }.show();
                                        lastBean = currentBean;
                                    }

                                    break;

                                case "start_flag":
                                    if(systemParamsS2.getBoolean("virus_day2"))
                                        mainTxtCount++;
                                    break;
                                case "hero_text_linked":
                                    LogUtil.d(TAG, "linked" + currentBean.getHero_text_linked() + " word" + currentBean.getLinked_word());
                                    currentBean.setContent(currentBean.getHero_text_linked());
                                    addItem();
                                    break;
                                case "hero_text":
                                    currentBean.setContent(currentBean.getHero_text());
                                    addItem();
                                    break;
                                case "hero_image":
                                    addItem();
                                    break;
                                case "player_select":
                                    if (lastBean != currentBean) {
                                        autoSave();
                                        isSelectionMode = true;
                                        sendTextSeason2.setEnabled(true);
                                        sendBtSeason2.setEnabled(true);
                                        saveProgress(Integer.parseInt(currentBean.getSelection_num()));
                                        tagsDialog = new TagsDialog(XEAChatActivityS2.this, currentBean.getPlayer_select(), new TagsDialog.TagSelection() {
                                            @Override
                                            public void selectTag(int position) {
                                                selection = position;
                                                sendTextSeason2.setText(currentBean.getPlayer_detail().get(position));
                                                tagsDialog.dismiss();
                                            }
                                        });
//                                        tagsDialog.show();
                                        lastBean = currentBean;
                                    }
                                    break;

                                case "hero_answer":
                                    answer = currentBean.getHero_answer().get(selection);

                                    if (answer.startsWith("$") && answer.endsWith("$")) {
                                        branchTxtCount = 0;
                                        isBranch = true;
                                        InputStream inputStream = getResources().openRawResource(ConvertString.convert(answer));
                                        String charpter = FileUtils.getString(inputStream);
                                        try {
                                            inputStream.close();
                                            branchList = gson.fromJson(charpter, new TypeToken<List<Season2ChatBean>>() {
                                            }.getType());
                                            currentList = branchList;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    } else {
                                        currentBean.setContent(answer);
                                        currentBean.setType("hero_text");
                                        addItem();
                                    }
                                    break;

                                case "interval":
                                    String interval[] = currentBean.getInterval().split(",");
                                    switch (interval[1]) {
                                        case "d":
                                            //Wait X days only in the slow text speed mode.
                                            if (delayTime > FAST_SPEED) {
                                                long startTime = setIntervalStart();
                                                //Compare whether it is more than one day
                                                if (TimeUtils.compareDay(startTime,Integer.parseInt(interval[0])))
                                                    mainTxtCount++;
                                            } else
                                                mainTxtCount++;
                                            break;
                                        case "m":
                                            if (delayTime >  FAST_SPEED) {
                                                long startTime = setIntervalStart();
                                                if (TimeUtils.compareMinutes(startTime,Integer.parseInt(interval[0])))
                                                    mainTxtCount++;
                                            } else
                                                mainTxtCount++;
                                            break;

                                        case "h":
                                            if (delayTime > FAST_SPEED) {
                                                long startTime = setIntervalStart();
                                                if (TimeUtils.compareHours(startTime,Integer.parseInt(interval[0])))
                                                    mainTxtCount++;
                                            } else
                                                mainTxtCount++;
                                            break;
                                    }
                                    break;


                                case "next_day":
                                    String next_day = currentBean.getNext_day();
                                    mainTxtCount = 0;
                                    isBranch = false;
                                    InputStream inputStream = getResources().openRawResource(ConvertString.convert(next_day));
                                    String chapter = FileUtils.getString(inputStream);
                                    mainList = gson.fromJson(chapter, new TypeToken<List<Season2ChatBean>>() {
                                    }.getType());
                                    currentList = mainList;
                                    systemParamsS2.setString("autoSavedChapter", next_day);
//                                        saveDisabled = false;
                                    break;

                                case "return_chapter":
                                    optionContent = currentBean.getReturn_chapter();
                                    inputStream = getResources().openRawResource(ConvertString.convert(optionContent));
                                    isBranch = false;
                                    String charpter = FileUtils.getString(inputStream);
                                    mainList = gson.fromJson(charpter, new TypeToken<List<Season2ChatBean>>() {
                                    }.getType());
                                    mainTxtCount++;
                                    currentList = mainList;
                                    break;
                                // System will save the progress if it is text to be click in the main chapter, because user will launch another activity.

                                case "player_single":
                                    if (lastBean != currentBean) {
                                        autoSave();
                                        isConfirmMode = true;
                                        sendTextSeason2.setEnabled(true);
//                                        sendTextSeason2.setText(currentBean.getPlayer_single());
//                                        sendBtSeason2.setEnabled(true);
                                        lastBean = currentBean;
                                    }
                                    break;
                                // System will save the progress if it is a wait flag, because user will launch another activity.
                                case "wait_flag":
                                    if (lastBean != currentBean) {
                                        autoSave();
                                        //Wait for player reacting on other interfaces
                                        systemParamsS2.setBoolean(currentBean.getWait_flag(), true);
                                        mainTxtCount++;
                                        switch (currentBean.getWait_flag()){
                                            case "mail1":
                                            case "mail2":
                                            case "mail3":
                                            case "mail4":
                                            case "mail5":
                                                systemParamsS2.setInt("wait_count", mainTxtCount);
                                                break;
                                            case "virus_day2":
                                                avatarLl.setEnabled(true);
                                                break;
                                        }
                                        lastBean = currentBean;
                                    }
                                    break;

                                case "restart_flag":
                                    if (systemParamsS2.getBoolean(currentBean.getRestart_flag(), false)) {
                                        mainTxtCount = systemParamsS2.getInt("wait_count");
                                        mainTxtCount++;
                                    }
                                    break;
                                //If player click the text in the branch, system will not save the progress
                                case "player_single_branch":
                                    if (lastBean != currentBean) {
                                        isConfirmMode = true;
                                        sendTextSeason2.setText(currentBean.getPlayer_single_branch());
                                        sendBtSeason2.setEnabled(true);
                                        lastBean = currentBean;
                                    }

                                    break;

                                case "player_select_multi":
                                    cancelTimer();
                                    if (lastBean != currentBean) {
                                        lastBean = currentBean;
                                        sendBtSeason2.setEnabled(false);
                                        sendTextSeason2.setEnabled(true);
                                        tagsDialog = new TagsDialog(XEAChatActivityS2.this, currentBean.getPlayer_select_multi(), new TagsDialog.TagSelection() {
                                            @Override
                                            public void selectTag(int position) {
                                                selection = position;
                                                sendTextSeason2.setText(currentBean.getPlayer_detail().get(position));
                                                tagsDialog.dismiss();
                                                sendBtSeason2.setEnabled(true);
                                            }
                                        });
                                        isMultiSelection = true;
//                                        saveProgress(Integer.parseInt(currentBean.getSelection_num()));
                                    }
                                    break;

                                case "hero_answer_multi":
                                    cancelTimer();
                                    int correctAnswer;
                                    answer = currentBean.getHero_answer_multi().get(selection);
                                    try {
                                        correctAnswer = Integer.parseInt(currentBean.getCorrect_answer());
                                    } catch (Exception e) {
                                        LogUtil.e(TAG, e.toString());
                                        break;
                                    }
                                    currentBean.setContent(answer);
                                    LogUtil.d(TAG, "correctAnswer:" + correctAnswer + " selection:" + selection + " mainTxtCount:" + mainTxtCount);
                                    if (selection == correctAnswer) {
                                        isMultiSelection = false;
                                        addItem();
                                    } else {
                                        position++;
                                        Season2ChatBean temp = new Season2ChatBean();
                                        temp.setContent(currentBean.getContent());
                                        temp.setType(currentBean.getType());
                                        chatAdapter.addData(position, temp);
                                        saveList.add(temp);
                                        if (!isUserTouching)
                                            recyclerview.scrollToPosition(position);
                                        //If it is not correct answer, return to multi selection.
                                        sendBtSeason2.setEnabled(true);
                                        sendTextSeason2.setEnabled(true);
                                        mainTxtCount--;
//                                        LogUtil.d(TAG, "hero_answer_multi->mainTxtCount:" + mainTxtCount);
                                        isMultiSelection = true;
                                    }
                                    restartTimer();
                                    break;
                                //显示弹出的提示
                                case "show_toast":
                                    InfoDialog infoDialog = new InfoDialog(XEAChatActivityS2.this, currentBean.getShow_toast(), ConvertString.convert(currentBean.getToast_pic()));
                                    infoDialog.show();
                                    mainTxtCount++;
                                    break;
                                case "date":
                                    Integer date = Integer.parseInt(currentBean.getDate());
                                    String time = TimeUtils.addDay(systemParamsS2.getLong("time"), date - 1);
                                    currentBean.setContent(time);
                                    addItem();
                                    break;

                            }
                        }
                    }
                });
            }
        };
    }

    private long setIntervalStart() {
        long startTime;
        if (lastBean != currentBean) {
            startTime = System.currentTimeMillis();
            systemParamsS2.setLong("startTime", startTime);
            lastBean = currentBean;
        } else
            startTime = systemParamsS2.getLong("startTime");
        return startTime;
    }

    //Load the progress when user click the selection on the right.
    @Override
    public void clickText(int selectNum) {
        if (customInterstitialAd.isLoaded()) {
            customInterstitialAd.show();
        } else {
            startDialogS2 = new StartDialogS2(this);
            startDialogS2.show();
        }

        isBranch = false;
        branchTxtCount = 0;
        count = mainTxtCount;

        //Clear all the flags saved in the sharedpreferences
//        systemParamsS2.setBoolean("mail1", false);
//        systemParamsS2.setBoolean("mail2", false);
//        systemParamsS2.setBoolean("mail3", false);
//        systemParamsS2.setBoolean("mail4", false);
//        systemParamsS2.setBoolean("mail5", false);
//        systemParamsS2.setBoolean("mail1Read", false);
//        systemParamsS2.setBoolean("mail2Read", false);
//        systemParamsS2.setBoolean("mail3Read", false);
//        systemParamsS2.setBoolean("mail4Read", false);
//        systemParamsS2.setBoolean("mail5Read", false);

        //Initialize the savedList and the text to send
        sendTextSeason2.setText("");
        if (saveList != null)
            saveList.clear();
        else
            saveList = new ArrayList<>();

        //Load the progress
        saveChapter = systemParamsS2.getInt(selectNum + "-saveChapter", 0);
        mainTxtCount = systemParamsS2.getInt(selectNum + "-saveMainCount", 0);
        savePosition = systemParamsS2.getInt(selectNum + "-savePosition", 0);
        saveList = systemParamsS2.getDataListSeason2(selectNum + "-saveList");

        if (saveList == null) {
            LogUtil.e(TAG, "clickText->saveList为空");
        }
        if (saveList.size() == 0) {
            LogUtil.e(TAG, "clickText->saveList无内容");
        }

        if (saveList == null || saveList.size() == 0 || savePosition == 0 || saveChapter == 0) {
            if (chatAdapter == null) {
                chatAdapter = new ChatAdapter(XEAChatActivityS2.this, currentList, XEAChatActivityS2.this, XEAChatActivityS2.this, this);
                recyclerview.setAdapter(chatAdapter);
            }
            LogUtil.d(TAG, "clickText(loadFirst)->selectNum:" + selectNum + " savePosition:" + savePosition +
                    " mainTxtCount:" + mainTxtCount + " count:" + count + " branchTxtCount:" + branchTxtCount);
            loadFirst();
            updateTimer();

            return;
        }


        LogUtil.d(TAG, "clickText->selectNum:" + selectNum + " savePosition:" + savePosition +
                " mainTxtCount:" + mainTxtCount + " count:" + count + " branchTxtCount:" + branchTxtCount);


        //Compute the rows which should be deleted.
        int deleteCount = position - savePosition;
        for (int i = 0; i < deleteCount; i++) {
            chatAdapter.removeData(savePosition + 1);
        }
        chatAdapter.notifyDataSetChanged();
        position = savePosition;

        //Read the text that will appear after loading.
        InputStream inputStream = getResources().openRawResource(saveChapter);
        String chapter2 = FileUtils.getString(inputStream);
        try {
            inputStream.close();
            mainList = gson.fromJson(chapter2, new TypeToken<List<Season2ChatBean>>() {
            }.getType());
            currentList = new ArrayList<>();
            currentList = mainList;
            recyclerview.scrollToPosition(position);
            sendTextSeason2.setEnabled(true);
            sendBtSeason2.setEnabled(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //When player click the text with explorer icon, the app will start an explorer activity.
    @Override
    public void clickText(String linkedText) {

        Intent intent = new Intent(this, ExplorerActivity.class);
        intent.putExtra("linkedText", linkedText);
        startActivity(intent);
    }


    private void addItem() {
        position++;
        chatAdapter.addData(position, currentBean);
        saveList.add(currentBean);
        if (!isUserTouching)
            recyclerview.scrollToPosition(position);
        if (isBranch)
            branchTxtCount++;
        else
            mainTxtCount++;
    }


    private void intiItemAnima() {
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(200);
        recyclerview.setItemAnimator(defaultItemAnimator);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setOnTouchListener(this);
    }


    private void saveProgress(int selectNum) {
        systemParamsS2.setInt(selectNum + "-saveChapter", saveChapter);
        systemParamsS2.setDataListSeason2(selectNum + "-saveList", saveList);
        systemParamsS2.setInt(selectNum + "-saveMainCount", mainTxtCount);
        systemParamsS2.setInt(selectNum + "-savePosition", position);


    }

    //AutoSave
    private void autoSave() {
        systemParamsS2.setInt("saveChapterS2", saveChapter);
        systemParamsS2.setDataListSeason2("autoSavedListS2", saveList);
        systemParamsS2.setInt("autoSavedMainCountS2", mainTxtCount);
        systemParamsS2.setInt("autoSavedPositionS2", position);

//        LogUtil.d(TAG, "autoSave->saveList:" + saveList.size() + " autoSavedPosition:" + position +
//                " mainTxtCount:" + mainTxtCount + " count:" + count + " branchTxtCount:" + branchTxtCount);
    }


    private void showRestartDialog() {
        new MyAlertDialog(XEAChatActivityS2.this, "重新开始将会删除所有对话", "确定", "取消") {
            @Override
            public void buttonOne() {
//                if (customInterstitialAd.isLoaded()) {
//                    customInterstitialAd.show();
//                } else
//                    startDialogS2.show();
                restart();
            }

            @Override
            public void buttonTwo() {

            }
        }.show();
    }

    private void restart() {
        branchList.clear();
        saveList = null;
        mainList.clear();
        currentList.clear();
        count = 0;
        branchTxtCount = 0;
        mainTxtCount = 0;
        sendTextSeason2.setText("");
        position = 0;

        systemParamsS2.setInt("saveChapterS2", R.raw.season2_day1);
        systemParamsS2.setDataListSeason2("autoSavedListS2", null);
        systemParamsS2.setInt("autoSavedMainCountS2", 0);
        systemParamsS2.setInt("autoSavedPositionS2", 0);
        loadFirst();
        updateTimer();
        recyclerview.scrollToPosition(position);
    }

    private void updateTimer() {
        cancelTimer();
        restartTimer();
    }

    private void restartTimer() {
        timer = new Timer();
        generateTimer();
        timer.schedule(timerTask, delayTime, delayTime);
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    boolean isUserTouching = false;
    Handler userTouchHandler = new Handler();
    Runnable userTouchRunnable = new Runnable() {
        @Override
        public void run() {
            isUserTouching = false;
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isUserTouching = true;
                break;
            case MotionEvent.ACTION_MOVE:
                isUserTouching = true;
                break;
            case MotionEvent.ACTION_UP:
                userTouchHandler.postDelayed(userTouchRunnable, 1000);
                break;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (customInterstitialAd.isLoaded()) {
            customInterstitialAd.show();
        }
//        startDialogS2.show();

    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        updateTimer();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (startDialogS2 != null)
            startDialogS2.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (startDialogS2 != null)
            startDialogS2.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();

//        if (mainList != null) {
//            mainList.clear();
//            mainList = null;
//        }
//
//        if (branchList != null) {
//            branchList.clear();
//            branchList = null;
//        }

//        if (currentList != null) {
//            currentList.clear();
//            currentList = null;
//        }
//
//        if (chatAdapter != null) {
//            chatAdapter.removeAll();
//            chatAdapter = null;
//        }
//
//        if (recyclerview != null) {
//            recyclerview.removeAllViews();
//            recyclerview = null;
//        }
        if (startDialogS2 != null) {
            startDialogS2.dismiss();
            startDialogS2 = null;
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_chat_season2;
    }


}
