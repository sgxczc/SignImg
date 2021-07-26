package com.zzc.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzc.signImg.EventMessage;
import com.zzc.signImg.R;
import com.zzc.signImg.WorkGirdBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/*
 * 巡检
 * */
public class InspectionListAdapter extends BaseQuickAdapter<WorkGirdBean, BaseViewHolder> {

    Context context;
    String message;

    public InspectionListAdapter(Context context, int layoutResId, @Nullable List<WorkGirdBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Subscribe
    public void onEvent(EventMessage eventMessage) {
        message = eventMessage.getMessage();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkGirdBean item) {
        helper.setText(R.id.item_inspection_title, item.getOrderType());

        LinearLayout ll_yes = helper.getView(R.id.ll_img_yes);
        LinearLayout ll_no = helper.getView(R.id.ll_img_no);
        ImageView yes = helper.getView(R.id.img_yes);
        ImageView no = helper.getView(R.id.img_no);

        /*赋值*/
        if (item.isCheck()) {
            yes.setBackgroundResource(R.mipmap.inspection_selected);
            no.setBackgroundResource(R.mipmap.inspection_select);
        } else {
            yes.setBackgroundResource(R.mipmap.inspection_select);
            no.setBackgroundResource(R.mipmap.inspection_selected);
        }


        ll_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message.equals("2")) {
                    yes.setBackgroundResource(R.mipmap.inspection_selected);
                    no.setBackgroundResource(R.mipmap.inspection_select);
                    item.setCheck(true);
                }
            }
        });

        ll_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message.equals("2")) {
                    yes.setBackgroundResource(R.mipmap.inspection_select);
                    no.setBackgroundResource(R.mipmap.inspection_selected);
                    item.setCheck(true);
                }
            }
        });
    }

}
