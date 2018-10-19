package alphabet.adviserconn.ImaginarySpace;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.utils.AnimeUtil;
import alphabet.adviserconn.utils.Contants;
import alphabet.adviserconn.utils.ConvertString;
import alphabet.adviserconn.utils.FileUtils;
import alphabet.adviserconn.widget.AdBannerDialog;
import alphabet.adviserconn.widget.ConfigDialog;
import alphabet.adviserconn.widget.EndingsDialog;
import alphabet.adviserconn.widget.MyAlertDialog;
import alphabet.adviserconn.widget.StaffDialog;
import alphabet.adviserconn.widget.StartDialog;
import alphabet.adviserconn.widget.TipsDialog;
import butterknife.BindView;
import butterknife.OnClick;

public class ImaginarySpaceActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    ChatAdapter chatAdapter;

    boolean clue1 = false;
    boolean clue2 = false;
    boolean clue3 = false;
    boolean clue4 = false;
    boolean clue5 = false;
    boolean clue6 = false;


    Context context;
    Gson gson;
    AssetManager assetManager = null;

    Handler handler = new Handler();
    Runnable runnable;

    ArrayList<ChatBean> playerSaveList = new ArrayList<>();

    ArrayList<ChatBean> currentList = new ArrayList<>();
    ArrayList<ChatBean> mainList = new ArrayList<>();
    ArrayList<ChatBean> branchList = new ArrayList<>();

    //需要保存的数据
    ArrayList<ChatBean> saveList;
    ArrayList<ChatBean> loadList;
    int saveMainCount = 0;
    int saveBranchCount = 0;
    int saveCount = 0;
    int savePosition = 0;
    String saveChapter = "";

    boolean saveDisabled = true;
    Resources resources;
    boolean isEnd = false;//判断是否已达成任意结局
    boolean isSelectMode = false;

    int mainTxtCount = 0;//文本读取计数器
    int branchTxtCount = 0;
    int count = 0;
    int skipMainFlag = 0;

    boolean isBranch = false;

    public static final int TEST_SPEED = 500;
    public static final int SLOW_SPEED = 4000;
    public static final int NORMAL_SPEED = 2000;
    public static final int FAST_SPEED = 1000;

    String textSpeed = "0";


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.config_iv)
    ImageView infoIv;
    @BindView(R.id.send_iv)
    ImageView sendIv;
    @BindView(R.id.send_tv)
    TextView sendTv;
    @BindView(R.id.select_one)
    TextView selectOne;
    @BindView(R.id.select_two)
    TextView selectTwo;
    @BindView(R.id.input_rl)
    RelativeLayout inputRl;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.info_content)
    TextView infoContent;
    @BindView(R.id.info_ll)
    LinearLayout infoLl;


    MyAlertDialog saveAlert;
    MyAlertDialog loadAlert;

    StartDialog startDialog;
    boolean isFirstTime = true;
    @BindView(R.id.imaginary_space_main)
    RelativeLayout imaginarySpaceMain;
    private int lastSave;
    boolean isLoad = false;
    boolean isLoading = false;
    @BindView(R.id.save_iv)
    ImageView saveIv;
    @BindView(R.id.load_iv)
    ImageView loadIv;
    boolean isUserClickMode = false;

    String optionContent = "";

    int delayTime = SLOW_SPEED;
    int position = 0;
    private final String TAG = this.getClass().toString();

    static Timer timer = new Timer();
    static TimerTask timerTask;
    SystemParams systemParams;

    @Override
    protected void initView() {
//        sendIv.setEnabled(false);
        selectOne.setVisibility(View.GONE);
        selectTwo.setVisibility(View.GONE);
        sendTv.setVisibility(View.GONE);
        saveIv.setVisibility(View.INVISIBLE);
        loadIv.setVisibility(View.INVISIBLE);

        systemParams = SystemParams.getInstance();
        startDialog = new StartDialog(this);
        startDialog.show();

        resources = getResources();
        gson = new Gson();

        context = getBaseContext();
        assetManager = getAssets();


        //加载item动画
        intItemAnima();

        loadProgress();
//        saveDisabled = false;
        Log.e(TAG, " loadFirst---loadChapter: " + saveChapter + " ---loadCount: " + saveCount + " ---loadPosition: " + savePosition + "---loadMainCount: " + saveMainCount + "---loadBranchCount: " + saveBranchCount);
        if (saveList == null || saveChapter == null || saveList.size() == 0 || saveCount == 0 || saveMainCount == 0 || savePosition == 0 || TextUtils.isEmpty(saveChapter)) {
//            chatAdapter = new ChatAdapter(this, currentList);
//            recyclerview.setAdapter(chatAdapter);
//            Log.e(TAG, " loadFirst---saveChapter: " + saveChapter+ " ---saveList: " + saveList  + " ---saveCount: " + saveCount + " ---savePosition: " + savePosition + "---saveMainCount: " + saveMainCount);
            Log.e(TAG, "loading error");
            loadFirst();

        } else {
//            Log.e(TAG, "load: " + saveList.get(1).getType());
            isLoad = true;
            for (ChatBean chatBean : saveList) {
                currentList.add(chatBean);
            }
//            currentList = saveList;
//            saveList = loadList;
            chatAdapter = new ChatAdapter(getApplicationContext(), currentList);
            recyclerview.setAdapter(chatAdapter);
            InputStream inputStream = getResources().openRawResource(ConvertString.convert(saveChapter));
            String charpter = FileUtils.getString(inputStream);
//            if (isBranch) {
//                branchList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
//                }.getType());
//                currentList = branchList;
//                branchTxtCount = saveBranchCount;
//                mainTxtCount = saveMainCount + 2;
//                count = saveBranchCount;
//                position = savePosition;
//            } else {
            mainList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
            }.getType());
//            currentList.clear();
            currentList = mainList;
            mainTxtCount = saveMainCount;
            count = saveCount;
            position = savePosition;
            saveBranchCount = 0;
            branchTxtCount = 0;
//            }

            selectOne.setVisibility(View.GONE);
            selectTwo.setVisibility(View.GONE);
            currentBean = currentList.get(count);
            sendTv.setText(currentBean.getPlayer_text());
            sendTv.setVisibility(View.VISIBLE);
            recyclerview.scrollToPosition(position);
        }

        //==========测试==========
//        count = 136;
//        mainTxtCount = 136;
        //=======================
        updateTimer();
    }

    private void intItemAnima() {
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(200);
        recyclerview.setItemAnimator(defaultItemAnimator);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setOnTouchListener(this);
    }


    ChatBean currentBean;
    String lastContent = "";

    private void generateTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!saveDisabled) {
                            saveIv.setVisibility(View.VISIBLE);
                            loadIv.setVisibility(View.VISIBLE);
                        }
                        if (isBranch)
                            count = branchTxtCount;
                        else
                            count = mainTxtCount;
                        if (count < currentList.size()) {
                            currentBean = currentList.get(count);
                            switch (currentBean.getType()) {
                                case ("left_text"):
                                    currentBean.setContent(currentBean.getHero_text());
                                    addItem();
                                    break;
                                case ("right_text"):
                                    isUserClickMode = true;
                                    isSelectMode = true;
                                    saveMainCount = mainTxtCount;
                                    saveBranchCount = branchTxtCount;
                                    currentBean.setContent(currentBean.getPlayer_text());
                                    //若刚刚读取过进度或是在结尾，则不保存
                                    if (!lastContent.equals(currentBean.getPlayer_text()) && !isBranch)
                                        saveProgress();

                                    selectOne.setVisibility(View.GONE);
                                    selectTwo.setVisibility(View.GONE);
                                    sendTv.setText(currentBean.getPlayer_text());
                                    sendTv.setVisibility(View.VISIBLE);
                                    lastContent = currentBean.getPlayer_text();
                                    break;

                                case ("right_option"):
                                    isUserClickMode = true;
//                                    if (timer != null)
//                                        timer.cancel();
//                                    if (timerTask != null)
//                                        timerTask.cancel();

//                                    if(currentBean.getHero_answer1().startsWith("$ending")||currentBean.getHero_answer2().startsWith("$ending")||isBranch)
//                                        saveDisabled = true;
//                                    if (!lastContent.equals(currentBean.getHero_answer1())&&!isBranch)
//                                        saveProgress();

                                    if (currentBean.getHero_answer1().startsWith("$ending") || currentBean.getHero_answer2().startsWith("$ending") || isBranch) {
                                        saveDisabled = true;
                                        saveIv.setVisibility(View.INVISIBLE);
                                        loadIv.setVisibility(View.INVISIBLE);
                                    } else {
                                        saveDisabled = false;
                                        saveIv.setVisibility(View.VISIBLE);
                                        loadIv.setVisibility(View.VISIBLE);
                                    }

                                    isSelectMode = true;

                                    sendTv.setVisibility(View.GONE);
                                    selectOne.setText(currentBean.getPlayer_option1());
                                    selectTwo.setText(currentBean.getPlayer_option2());
                                    selectOne.setVisibility(View.VISIBLE);
                                    selectTwo.setVisibility(View.VISIBLE);
                                    lastContent = currentBean.getHero_answer1();
                                    break;

                                case ("status"):
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            title.setText(currentBean.getShow_status());
                                        }
                                    });
                                    mainTxtCount++;
                                    branchTxtCount++;
                                    break;
                                case ("left_voice"):
                                    currentBean.setContent(currentBean.getHero_voice());
                                    addItem();
                                    break;
                                case ("other_voice"):
                                    currentBean.setContent(currentBean.getOthers());
                                    addItem();
                                    break;
                                case ("heroin_voice"):
                                    currentBean.setContent(currentBean.getHeroin_words());
                                    addItem();
                                    break;
                                case ("left_answer"):
                                    if (currentBean.getHero_answer1().startsWith("$ending") || currentBean.getHero_answer2().startsWith("$ending") || isBranch)
                                        saveDisabled = true;
                                    //内容中出现引用符号
                                    if (optionContent.startsWith("$") && optionContent.endsWith("$")) {
                                        count = 0;
                                        branchTxtCount = 0;
                                        isBranch = true;
                                        InputStream inputStream = getResources().openRawResource(ConvertString.convert(optionContent));
                                        String charpter = FileUtils.getString(inputStream);
                                        branchList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                        }.getType());
                                        currentList.clear();
                                        currentList = branchList;
                                        saveChapter = optionContent;
                                    } else {
                                        currentBean.setContent(optionContent);
                                        addItem();
                                    }

                                    break;
                                case ("interval"):
                                    String[] intervalStr = currentBean.getInterval_time().split(",");
                                    int interValTime = 3000;
                                    switch (textSpeed) {
                                        case "1":
                                            interValTime = Integer.parseInt(intervalStr[0]);
                                            break;
                                        case "2":
                                            interValTime = Integer.parseInt(intervalStr[1]);
                                            break;
                                        case "3":
                                            interValTime = Integer.parseInt(intervalStr[2]);
                                            break;
                                    }
                                    handler.postDelayed(runnable, interValTime);
                                    mainTxtCount++;
                                    branchTxtCount++;
                                    break;
                                case ("tips"):
                                    infoContent.setText(currentBean.getShow_tips());
                                    AnimeUtil.expand(infoLl);
                                    mainTxtCount++;
                                    branchTxtCount++;
                                    break;
                                case ("skip"):
                                    optionContent = currentBean.getChapter_name();
                                    InputStream inputStream = getResources().openRawResource(ConvertString.convert(optionContent));
                                    isBranch = false;
                                    String charpter = FileUtils.getString(inputStream);
                                    mainList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                    }.getType());
                                    //手动读取存档
//                                    if (loadManually) {
//                                        mainTxtCount = systemParams.getInt("playerSaveMainCount");
//                                        loadManually = false;
//                                    } else
//                                        mainTxtCount = systemParams.getInt("skipMainFlag");

                                    mainTxtCount++;
                                    currentList = mainList;
                                    saveChapter = optionContent;
                                    systemParams.setString("saveChapter", optionContent);
                                    systemParams.setInt("saveMainCount", mainTxtCount);
                                    systemParams.setInt("saveBranchCount", branchTxtCount);
                                    systemParams.setInt("saveCount", count);
//                                    saveDisabled = false;
                                    break;
                                case ("next"):
                                    String next_chapter = currentBean.getNext_chapter();
                                    if ("alternative".equals(next_chapter)) {
                                        int clueCount = 0;
                                        if (clue1 == true)
                                            clueCount++;
                                        if (clue2 == true)
                                            clueCount++;
                                        if (clue3 == true)
                                            clueCount++;

                                        if (!clue6) {
                                            if (clueCount == 3)
                                                inputStream = getResources().openRawResource(R.raw.ending_wakeup);
                                            else
                                                inputStream = getResources().openRawResource(R.raw.ending_infinity);
                                        } else {
//                                            boolean ending_wakeup = systemParams.getBoolean("ending_wakeup", false);
//                                            boolean ending_chaos = systemParams.getBoolean("ending_chaos", false);
//                                            boolean ending_infinity = systemParams.getBoolean("ending_infinity", false);
                                            if (clueCount == 3)
                                                inputStream = getResources().openRawResource(R.raw.ending_rebirth);
                                            else
                                                inputStream = getResources().openRawResource(R.raw.ending_chaos);
                                        }

                                        charpter = FileUtils.getString(inputStream);
                                        count = 0;
                                        branchTxtCount = 0;
                                        mainTxtCount = 0;
                                        mainList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                        }.getType());
                                        currentList = mainList;

                                    } else {
                                        count = 0;
                                        mainTxtCount = 0;
                                        isBranch = false;
                                        inputStream = getResources().openRawResource(ConvertString.convert(next_chapter));
                                        charpter = FileUtils.getString(inputStream);
                                        mainList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                        }.getType());
                                        currentList = mainList;
                                        saveChapter = next_chapter;
                                        systemParams.setString("saveChapter", next_chapter);
//                                        saveDisabled = false;
                                    }
                                    break;
                                case ("clue"):
                                    mainTxtCount++;
                                    branchTxtCount++;
                                    switch (currentBean.getClue_tag()) {
                                        case "1":
                                            clue1 = true;
                                            systemParams.setBoolean("clue1", clue1);
                                            break;
                                        case "2":
                                            clue2 = true;
                                            systemParams.setBoolean("clue2", clue2);
                                            break;
                                        case "3":
                                            clue3 = true;
                                            systemParams.setBoolean("clue3", clue3);
                                            break;
                                        case "4":
                                            clue4 = true;
                                            systemParams.setBoolean("clue4", true);
                                            break;
                                        case "5":
                                            clue5 = true;
                                            systemParams.setBoolean("clue5", true);
                                            break;
                                        case "6":
                                            clue6 = true;
                                            systemParams.setBoolean("clue6", true);
                                            break;
                                    }
                                    break;
                                case "secret":
                                    if (clue4 == true) {
                                        inputStream = getResources().openRawResource(R.raw.branch_secret1);
                                        isBranch = true;
                                        count = 0;
                                        branchTxtCount = 0;
                                        charpter = FileUtils.getString(inputStream);
                                        branchList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                        }.getType());
                                        currentList.clear();
                                        currentList = branchList;
                                    } else if (clue5 == true) {
                                        inputStream = getResources().openRawResource(R.raw.branch_secret2);
                                        isBranch = true;
                                        count = 0;
                                        branchTxtCount = 0;
                                        charpter = FileUtils.getString(inputStream);
                                        branchList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                        }.getType());
                                        currentList.clear();
                                        currentList = branchList;
                                    } else
                                        mainTxtCount++;
                                    break;
                                case ("ending"):
                                    timer.cancel();
                                    timerTask.cancel();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            title.setText(Contants.APP_CHINESE_NAME);
                                        }
                                    });

                                    isSelectMode = true;


                                    switch (currentBean.getEnding_name()) {
                                        case "wakeup":
                                            saveChapter = "$ending_wakeup$";
                                            systemParams.setString("saveChapter", "$ending_wakeup$");
                                            systemParams.setBoolean("ending_wakeup", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "服务器已关闭", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                        case "infinity":
                                            saveChapter = "$ending_infinity$";
                                            systemParams.setString("saveChapter", "$ending_infinity$");
                                            systemParams.setBoolean("ending_infinity", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "角色资料已损坏无法修复", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                        case "rebirth":
                                            saveChapter = "$ending_rebirth$";
                                            systemParams.setString("saveChapter", "$ending_rebirth$");
                                            systemParams.setBoolean("ending_rebirth", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "对方已离线", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                        case "chaos":
                                            saveChapter = "$ending_chaos$";
                                            systemParams.setString("saveChapter", "$ending_chaos$");
                                            systemParams.setBoolean("ending_chaos", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "对方无响应", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                        case "cutoff":
                                            systemParams.setString("ending_cutoff", "true");
                                            systemParams.setBoolean("ending_cutoff", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "角色资料已损坏无法修复", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                        case "amnesia":
                                            systemParams.setString("ending_amnesia", "true");
                                            systemParams.setBoolean("ending_amnesia", true);
                                            systemParams.setBoolean("isEnd", true);
                                            new MyAlertDialog(ImaginarySpaceActivity.this, "对方未添加你为好友", "重新开始", "查看提示") {
                                                @Override
                                                public void buttonOne() {
                                                    showRestartDialog();
                                                }

                                                @Override
                                                public void buttonTwo() {
                                                    new TipsDialog(ImaginarySpaceActivity.this).show();
                                                }
                                            }.show();
                                            break;
                                    }
                                    break;
                            }
                        }
                    }
                });
            }
        };
    }

    @OnClick({R.id.send_iv, R.id.select_one, R.id.select_two, R.id.config_iv, R.id.save_iv, R.id.load_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_iv:
                isSelectMode = false;
                if (sendTv.getVisibility() == View.VISIBLE) {
                    currentBean.setContent(sendTv.getText().toString());
                    addItem();
                    sendTv.setVisibility(View.GONE);
                    saveDisabled = true;
                    saveIv.setVisibility(View.INVISIBLE);
                    loadIv.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.select_one:
                selectOne.setVisibility(View.GONE);
                selectTwo.setVisibility(View.GONE);
                isSelectMode = false;
                currentBean.setContent(selectOne.getText().toString());
                //选择不相信小唯
                if (Contants.SPECIAL_SELECT_ONE.equals(selectOne.getText().toString())) {
                    clue6 = false;
                    systemParams.setBoolean("clue6", clue6);
                }
                currentList.get(count).setType("right_text");
                addItem();
                //设置选择的为1
                optionContent = currentList.get(count + 1).getHero_answer1();
                saveDisabled = true;
                saveIv.setVisibility(View.INVISIBLE);
                loadIv.setVisibility(View.INVISIBLE);
                break;
            case R.id.select_two:

                selectOne.setVisibility(View.GONE);
                selectTwo.setVisibility(View.GONE);
                isSelectMode = false;
                currentBean.setContent(selectTwo.getText().toString());
                //选择相信小唯
                if (Contants.SPECIAL_SELECT_TWO.equals(selectTwo.getText().toString())) {
                    clue6 = true;
                    systemParams.setBoolean("clue6", clue6);
                }
                currentList.get(count).setType("right_text");
                addItem();
                //设置选择的为2
                optionContent = currentList.get(count + 1).getHero_answer2();
                saveDisabled = true;
                saveIv.setVisibility(View.INVISIBLE);
                loadIv.setVisibility(View.INVISIBLE);
                break;
            case R.id.config_iv:
                isEnd = systemParams.getBoolean("isEnd");
                ConfigDialog configDialog = new ConfigDialog(ImaginarySpaceActivity.this, delayTime, isEnd) {
                    @Override
                    public void tips() {
                        new TipsDialog(ImaginarySpaceActivity.this).show();
                    }

                    @Override
                    public void restart() {
                        showRestartDialog();
                    }

                    @Override
                    public void endings() {
                        new EndingsDialog(ImaginarySpaceActivity.this, systemParams).show();
                    }

                    @Override
                    public void staff() {
                        new StaffDialog(ImaginarySpaceActivity.this).show();
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
                                delayTime = TEST_SPEED;
                                break;
                        }
                        updateTimer();
                        showToast("文本速度已调整");
                    }
                };
                configDialog.show();
                break;
            case R.id.save_iv:
                if ((!saveDisabled) && (isSelectMode) && (!isBranch)) {
                    saveAlert = new AdBannerDialog(ImaginarySpaceActivity.this, "是否保存当前对话？", "确定", "取消") {
                        @Override
                        public void buttonOne() {
                            systemParams.setInt("playerSaveCount", count);
                            systemParams.setInt("playerSaveMainCount", mainTxtCount);
                            systemParams.setInt("playerSaveBranchCount", branchTxtCount);
                            systemParams.setInt("playerSavePosition", position);
                            systemParams.setBoolean("playerSaveIsBranch", isBranch);
                            systemParams.setDataList("playerSaveList", null);
                            systemParams.setDataList("playerSaveList", saveList);
                            systemParams.setString("playerSaveChapter", saveChapter);

                            systemParams.setBoolean("playerSaveClue1", systemParams.getBoolean("clue1", false));
                            systemParams.setBoolean("playerSaveClue2", systemParams.getBoolean("clue2", false));
                            systemParams.setBoolean("playerSaveClue3", systemParams.getBoolean("clue3", false));
                            systemParams.setBoolean("playerSaveClue4", systemParams.getBoolean("clue4", false));
                            systemParams.setBoolean("playerSaveClue5", systemParams.getBoolean("clue5", false));
                            systemParams.setBoolean("playerSaveClue6", systemParams.getBoolean("clue6", false));

                            if (customInterstitialAd.isLoaded()) {
                                customInterstitialAd.show();
                            }
//                            startDialog.show();
                            showToast("存档成功！");
                        }

                        @Override
                        public void buttonTwo() {

                        }
                    };
                    saveAlert.show();
                }
                break;
            case R.id.load_iv:
                if ((!saveDisabled) && (isSelectMode) && (!isBranch)) {

                    loadAlert = new MyAlertDialog(ImaginarySpaceActivity.this, "是否读取上一次对话？", "确定", "取消") {
                        @Override
                        public void buttonOne() {

//                            saveList.clear();

                            if (timer != null)
                                timer.cancel();
                            if (timerTask != null)
                                timerTask.cancel();
                            loadManually = true;
                            if (customInterstitialAd.isLoaded()) {
                                customInterstitialAd.show();
                            }
                            startDialog.show();

                            int playerSaveCount = systemParams.getInt("playerSaveCount", 0);
                            int playerSavePosition = systemParams.getInt("playerSavePosition", 0);
                            boolean playerSaveIsBranch = systemParams.getBoolean("playerSaveIsBranch", false);
                            playerSaveList.clear();
                            playerSaveList = systemParams.getDataList("playerSaveList");
                            String playerSaveChapter = systemParams.getString("playerSaveChapter", "");
                            int playerSaveMainCount = systemParams.getInt("playerSaveMainCount", 0);
                            int playerSaveBranchCount = systemParams.getInt("playerSaveBranchCount", 0);

                            clue1 = systemParams.getBoolean("playerSaveClue1", false);
                            clue2 = systemParams.getBoolean("playerSaveClue2", false);
                            clue3 = systemParams.getBoolean("playerSaveClue3", false);
                            clue4 = systemParams.getBoolean("playerSaveClue4", false);
                            clue5 = systemParams.getBoolean("playerSaveClue5", false);
                            clue6 = systemParams.getBoolean("playerSaveClue6", false);

                            if (playerSaveList == null || playerSaveChapter == null || playerSaveList.size() == 0 || playerSaveMainCount == -1 || playerSaveBranchCount == -1 || playerSaveCount == -1 || playerSavePosition == -1 || TextUtils.isEmpty(playerSaveChapter)) {
                                showToast("没有读取到存档");
                                Log.e(TAG, "load_iv: " + playerSaveList + " --- " + playerSaveChapter);
                            } else {
                                Log.e(TAG, "load_iv: " + playerSaveList + " --- " + playerSaveChapter + "---" + playerSaveMainCount + "---" + playerSaveBranchCount + "---" + playerSaveIsBranch);
                                currentList.clear();
                                recyclerview.removeAllViews();
                                chatAdapter.notifyDataSetChanged();
                                currentList = playerSaveList;
//                                chatAdapter = new ChatAdapter(getApplicationContext(), currentList);
//                                recyclerview.setAdapter(chatAdapter);
                                InputStream inputStream = getResources().openRawResource(ConvertString.convert(playerSaveChapter));
                                String charpter = FileUtils.getString(inputStream);
//                                if (playerSaveIsBranch) {
//                                    branchList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
//                                    }.getType());
//                                    currentList = new ArrayList<>();
//                                    currentList = branchList;
//                                    branchTxtCount = playerSaveBranchCount;
//                                    mainTxtCount = playerSaveMainCount + 2;
//                                    position = playerSavePosition;
//                                } else {
                                mainList = gson.fromJson(charpter, new TypeToken<List<ChatBean>>() {
                                }.getType());
                                currentList = new ArrayList<>();
                                currentList = mainList;
                                mainTxtCount = playerSaveMainCount;
                                position = playerSavePosition;
//                                }
                                recyclerview.scrollToPosition(position);
                                timer = new Timer();
                                generateTimer();
                                timer.schedule(timerTask, delayTime, delayTime);
                            }
                        }

                        @Override
                        public void buttonTwo() {

                        }
                    };
                    loadAlert.show();
                }
        }
    }

    boolean loadManually = false;

    private void loadFirst() {
        currentBean = new ChatBean();
        currentBean.setType("left_text");
        currentBean.setContent(Contants.FIRST_TXT);
        currentList = new ArrayList<>();
        currentList.add(currentBean);
        chatAdapter = new ChatAdapter(getApplicationContext(), currentList);
        recyclerview.setAdapter(chatAdapter);

        if (saveList == null) {
            saveList = new ArrayList<>();
        }
        saveList.add(currentList.get(0));
        //加载charpter1剧本
        InputStream inputStream = getResources().openRawResource(R.raw.chapter1);
        //==========测试===========
        String chapter1 = FileUtils.getString(inputStream);
        mainList = gson.fromJson(chapter1, new TypeToken<List<ChatBean>>() {
        }.getType());
        currentList = mainList;
        saveChapter = "$chapter1$";
        systemParams.setString("saveChapter", "$chapter1$");

    }


    private void addItem() {
//        tempFlag = false;
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

    private void showRestartDialog() {
        new MyAlertDialog(ImaginarySpaceActivity.this, "重新开始将会删除所有对话", "确定", "取消") {
            @Override
            public void buttonOne() {

                startDialog.show();
//                deleteProgress();
//                saveDisabled = false;

                branchList.clear();
                saveList = null;
                mainList.clear();
                currentList.clear();
                count = 0;
                branchTxtCount = 0;
                mainTxtCount = 0;
                selectTwo.setText("");
                selectOne.setText("");
                sendTv.setText("");
                infoLl.setVisibility(View.INVISIBLE);
                position = 0;

                clue1 = false;
                clue2 = false;
                clue3 = false;
                clue4 = false;
                clue5 = false;
                clue6 = false;

                systemParams.setInt("count", 0);
                systemParams.setInt("position", 0);
                systemParams.setString("saveChapter", null);
                systemParams.setString("saveBranchCount", null);
                systemParams.setString("saveMainCount", null);
                systemParams.setString("saveCount", null);
                systemParams.setString("savePosition", null);
                systemParams.setDataList("saveList", null);
                systemParams.setBoolean("isBranch", false);

                systemParams.setBoolean("clue1", false);
                systemParams.setBoolean("clue2", false);
                systemParams.setBoolean("clue3", false);
                systemParams.setBoolean("clue4", false);
                systemParams.setBoolean("clue5", false);
                systemParams.setBoolean("clue6", false);

                loadFirst();
                updateTimer();
                selectOne.setVisibility(View.GONE);
                selectTwo.setVisibility(View.GONE);
                sendTv.setVisibility(View.GONE);
                recyclerview.scrollToPosition(position);
                loadIv.setVisibility(View.INVISIBLE);
                saveIv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void buttonTwo() {

            }
        }.show();
    }

    //保存当前进度
    private void saveProgress() {
//        if (!saveDisabled) {
//            saveCount = count;
//            savePosition = position;

//            systemParams.setDataList("saveList", null);
        systemParams.setDataList("saveList", saveList);
        systemParams.setInt("saveMainCount", mainTxtCount);
        systemParams.setInt("saveBranchCount", branchTxtCount);
        systemParams.setInt("saveCount", count);
        systemParams.setInt("savePosition", position);
        systemParams.setBoolean("isBranch", isBranch);

//            clue1 = false;
//            clue2 = false;
//            clue3 = false;
//            clue4 = false;
//            clue5 = false;
//            clue6 = false;

        systemParams.setBoolean("clue1", clue1);
        systemParams.setBoolean("clue2", clue2);
        systemParams.setBoolean("clue3", clue3);
        systemParams.setBoolean("clue4", clue4);
        systemParams.setBoolean("clue5", clue5);
        systemParams.setBoolean("clue6", clue6);

        Log.e(TAG, "saveProgress: " + saveList.size() + " ----saveChapter: " + saveChapter + " ---saveCount: " + count + " ---savePosition:" + position + "---saveMainCount:" + mainTxtCount + "---saveBranchCount:" + branchTxtCount);
//        }
    }

    //读取当前进度
    private void loadProgress() {
        sendTv.setText("");
        selectOne.setText("");
        selectTwo.setText("");
        if (saveList != null && saveList.size() > 0)
            saveList.clear();
        else
            saveList = new ArrayList<>();
        saveChapter = systemParams.getString("saveChapter", "");
//        saveCount = systemParams.getInt("saveCount", 0);
        branchTxtCount = systemParams.getInt("saveBranchCount", 0);
        mainTxtCount = systemParams.getInt("saveMainCount", 0);
        saveCount = systemParams.getInt("saveCount", 0);
        saveMainCount = systemParams.getInt("saveMainCount", 0);
        savePosition = systemParams.getInt("savePosition", 0);
        saveList = systemParams.getDataList("saveList");
        isBranch = systemParams.getBoolean("isBranch", isBranch);

        clue1 = systemParams.getBoolean("clue1", false);
        clue2 = systemParams.getBoolean("clue2", false);
        clue3 = systemParams.getBoolean("clue3", false);
        clue4 = systemParams.getBoolean("clue4", false);
        clue5 = systemParams.getBoolean("clue5", false);
        clue6 = systemParams.getBoolean("clue6", false);

        Log.e(TAG, " loadProgress----saveChapter: " + saveChapter + " ---saveCount: " + saveCount + " ---savePosition:" + savePosition + "---saveBranchCount" + branchTxtCount + "---saveMainCount" + mainTxtCount);
    }

    //更新计时器
    private void updateTimer() {
        if (timer != null)
            timer.cancel();
        if (timerTask != null)
            timerTask.cancel();
        timer = new Timer();
        generateTimer();
        timer.schedule(timerTask, delayTime, delayTime);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        updateTimer();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        updateTimer();
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//        if (timerTask != null) {
//            timerTask.cancel();
//            timerTask = null;
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//        if (timerTask != null) {
//            timerTask.cancel();
//            timerTask = null;
//        }
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (currentList != null) {
            currentList.clear();
            currentList = null;
        }
        if (recyclerview != null) {
            recyclerview.removeAllViews();
            recyclerview = null;
        }

        if (loadAlert != null) {
            loadAlert.dismiss();
            loadAlert = null;
        }

        if (saveAlert != null) {
            saveAlert.dismiss();
            saveAlert = null;
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
                userTouchHandler.postDelayed(userTouchRunnable, 2000);
                break;
        }

        return false;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_imaginary_space;
    }


}
