package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

import alphabet.adviserconn.InvitationFromDarkNet.adapter.MyTagAdapter;
import alphabet.adviserconn.R;


public class TagsDialog extends Dialog implements MyTagAdapter.TagSelection {

    TagsAdapter tagsAdapter;

    private Context context;
    List<String> tagList;

    public TagsDialog(Context context, List<String> tagList,TagSelection tagSelection) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.tagList = tagList;
        this.tagSelection = tagSelection;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_tag);
        this.setCanceledOnTouchOutside(true);
        TagCloudView tagCloudView = findViewById(R.id.tagcloud);
        tagsAdapter = new MyTagAdapter(tagList,this);
        tagCloudView.setAdapter(tagsAdapter);

    }

    @Override
    public void selectTag(int position) {
        tagSelection.selectTag(position);
    }

    public interface TagSelection{
        void selectTag(int position);
    };

    TagSelection tagSelection;

}

