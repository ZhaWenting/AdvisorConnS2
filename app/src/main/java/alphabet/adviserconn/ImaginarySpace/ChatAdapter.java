package alphabet.adviserconn.ImaginarySpace;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alphabet.adviserconn.R;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = this.getClass().toString();
    private Context context;
    private ArrayList<ChatBean> list;
    String content;

    public ChatAdapter(Context context, ArrayList<ChatBean> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1) {
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.left_text, parent,
                    false));
            return holder;
        }else if(viewType==2){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.right_text, parent,
                    false));
            return holder;
        }else if(viewType==3){
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.left_voice_txt, parent,
                    false));
            return holder;
        }else if(viewType==4) {
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.other_voice_text, parent,
                    false));
            return holder;
        }else if(viewType==5) {
            TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.heroin_voice_text, parent,
                    false));
            return holder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TextViewHolder){
//            ((TextViewHolder) holder).tv.setTag(list.get(position).getContent());
            ((TextViewHolder) holder).tv.setText(list.get(position).getContent());
        }else if(holder instanceof ImageViewHolder){

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position,List<Object> payloads) {
        if(payloads.isEmpty()){
            onBindViewHolder(holder,position);
        }else {
            if(holder instanceof TextViewHolder){
//                ((TextViewHolder) holder).tv.setTag(list.get(position).getContent());
                ((TextViewHolder) holder).tv.setText(list.get(position).getContent());
            }else if(holder instanceof ImageViewHolder){

            }
        }
    }

    @Override
    public int getItemCount() {
        if(list==null||list.size()==0){
            return 0;
        }
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()) {
            case ("left_text"):
            case ("left_answer"):
                return 1;
            case ("right_text"):
                return 2;
            case ("left_voice"):
                return 3;
            case ("other_voice"):
                return 4;
            case ("heroin_voice"):
                return 5;
        }
        return 0;
    }

    //  添加数据
//    public void addData(int position,ChatBean chatBean,String content) {
//        this.content = content;
//        mainList.add(position,chatBean);
//        notifyItemInserted(position);
//    }

    public void addData(int position,ChatBean chatBean) {
//      在list中添加数据，并通知条目加入一条
        list.add(position,chatBean);
        notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class TextViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ImageViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.loading);
        }
    }



}
