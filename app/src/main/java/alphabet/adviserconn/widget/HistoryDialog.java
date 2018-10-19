package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import alphabet.adviserconn.R;


public class HistoryDialog extends Dialog implements AdapterView.OnItemClickListener {
    ArrayList<String> historyList;
    Context context;

    public HistoryDialog(Context context,ArrayList<String> historyList,ListClickListener listener) {
        super(context, R.style.DialogTheme);
        this.historyList = historyList;
        this.context = context;
        this.listClickListener = listener;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_history);
        this.setCanceledOnTouchOutside(true);
        ListView listView = findViewById(R.id.history_list);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context,R.layout.item_tag);
        listAdapter.addAll(historyList);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listClickListener.getListner(historyList.get(position));
        dismiss();
    }

    public interface ListClickListener{
        void getListner(String clicked);
    }
    ListClickListener listClickListener;
}

