package alphabet.adviserconn.InvitationFromDarkNet.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.utils.LogUtil;
import alphabet.adviserconn.widget.HistoryDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExplorerActivity extends BaseActivity implements HistoryDialog.ListClickListener {


    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.search_result)
    TextView searchResult;

    ArrayList<String> historyList = new ArrayList<>();
    HistoryDialog historyDialog;
    Resources resources;
    ArrayList<String> wordList = new ArrayList<>();
    ArrayList<String> contentList = new ArrayList<>();
    String[] wordArray;
    String[] contentArray;
    private String TAG = this.getClass().toString();


    @Override
    protected void initView() {

        resources = getResources();

        wordArray = resources.getStringArray(R.array.histroy_word);
        contentArray = resources.getStringArray(R.array.histroy_content);
        for (int i = 0; i < wordArray.length; i++) {
            if(systemParamsS2.getBoolean(wordArray[i]))
                wordList.add(wordArray[i]);
        }

        for (int i = 0; i < contentArray.length; i++) {
            if(systemParamsS2.getBoolean(contentArray[i]))
                contentList.add(contentArray[i]);
        }
        for (int i = 0; i < wordArray.length; i++) {
            if(systemParamsS2.getBoolean(wordArray[i])) {
                historyList.add(wordArray[i]);
            }
        }

        historyDialog = new HistoryDialog(this,historyList,this);

        Intent intent = getIntent();
        if(intent!=null) {
            String linkedText = getIntent().getStringExtra("linkedText");
            searchText.setText(linkedText);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (customInterstitialAd.isLoaded()) {
            customInterstitialAd.show();
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_explorer;
    }


    @OnClick({R.id.search_text, R.id.search_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_text:
                historyDialog.show();
                break;
            case R.id.search_bt:
                String searchContent = searchText.getText().toString();
                if(!TextUtils.isEmpty(searchContent)){
                    for (int i = 0; i < wordArray.length; i++) {
                        if(searchContent.equals(wordArray[i])) {
                            searchResult.setText(contentArray[i]);
                            systemParamsS2.setBoolean(wordArray[i],true);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void getListner(String clicked) {
        searchText.setText(clicked);
    }
}
