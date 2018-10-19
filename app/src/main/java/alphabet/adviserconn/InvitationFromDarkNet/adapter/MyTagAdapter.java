package alphabet.adviserconn.InvitationFromDarkNet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

import alphabet.adviserconn.R;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/7/13 0013
 */
public class MyTagAdapter extends TagsAdapter{

    private List<String> tags;

    public MyTagAdapter(List<String> tags,TagSelection tagSelection){
        this.tags = tags;
        this.tagSelection = tagSelection;
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public View getView(Context context, final int position, ViewGroup parent) {
        TextView textView = (TextView) View.inflate(context, R.layout.item_tag,null);
        textView.setText((String)getItem(position));
        textView.setTextColor(context.getResources().getColor(R.color.windowBackground));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagSelection.selectTag(position);
            }
        });
        return textView;
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }


    public interface TagSelection{
        void selectTag(int position);
    };

    TagSelection tagSelection;

}
