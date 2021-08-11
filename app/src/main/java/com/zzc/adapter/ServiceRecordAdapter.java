package com.zzc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzc.signImg.R;
import com.zzc.signImg.WorkGirdBean;

import java.util.ArrayList;
import java.util.List;

public class ServiceRecordAdapter extends BaseQuickAdapter<WorkGirdBean, BaseViewHolder> {

    Context context;
    private static int choiceNum = 0;
    private static List<String> choices = new ArrayList<>();
    List<WorkGirdBean> data;

    public ServiceRecordAdapter(Context context, int layoutResId, @Nullable List<WorkGirdBean> data) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkGirdBean item) {
        helper.setText(R.id.item_grid_categoryname,item.getOrderType());

        TextView textView = helper.getView(R.id.item_grid_categoryname);
        int position = helper.getLayoutPosition();

        if(item.isCheck()){
            textView.setTextColor(Color.parseColor("#4C86FF"));
        }else {
            textView.setTextColor(Color.parseColor("#666666"));
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isCheck()){
                    item.setCheck(false);
//                    if(choiceNum >= 1){
//                        choiceNum -= 1;
//                        removeChoice(textView.getText().toString());
//                    }
                    textView.setTextColor(Color.parseColor("#666666"));
                }else{
                    if(choiceNum < data.size()){
                        choiceNum += 1;
                        item.setOrderType(textView.getText().toString());
                        item.setCheck(true);
                        textView.setTextColor(Color.parseColor("#4C86FF"));
                    }else{
                        Toast.makeText(context,"最多只能选择五项",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
