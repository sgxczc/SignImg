package com.zzc.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzc.signImg.R;
import com.zzc.signImg.WorkGirdBean;

import java.util.List;

public class MultiplyAdapter extends BaseMultiItemQuickAdapter<WorkGirdBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiplyAdapter(List<WorkGirdBean> data) {
        super(data);
        //设置当传入的itemType为某个常量显示不同的item
        addItemType(WorkGirdBean.second, R.layout.item_inspection);
        addItemType(WorkGirdBean.third,R.layout.item_inspection2);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkGirdBean item) {
        switch (helper.getItemViewType()){
            case WorkGirdBean.second:
                helper.setText(R.id.item_inspection_title,item.getOrderType());
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

                ll_yes.setOnClickListener(view -> {
                    yes.setBackgroundResource(R.mipmap.inspection_selected);
                    no.setBackgroundResource(R.mipmap.inspection_select);
                    item.setCheck(true);
                });

                ll_no.setOnClickListener(view -> {
                    yes.setBackgroundResource(R.mipmap.inspection_select);
                    no.setBackgroundResource(R.mipmap.inspection_selected);
                    item.setCheck(true);
                });
                break;
            case WorkGirdBean.third:
                helper.setText(R.id.item_inspection_title2,item.getOrderType());
                LinearLayout manyi = helper.getView(R.id.ll_img_manyi);
                LinearLayout bumanyi = helper.getView(R.id.ll_img_bumanyi);
                LinearLayout yiban = helper.getView(R.id.ll_img_yiban);
                ImageView manyiImg = helper.getView(R.id.img_manyi);
                ImageView bumanyiImg = helper.getView(R.id.img_bumanyi);
                ImageView yibanImg = helper.getView(R.id.img__yiban);
                /*赋值*/
                if (item.getStatus()==1) {
                    manyiImg.setBackgroundResource(R.mipmap.inspection_selected);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_select);
                } else if (item.getStatus()==2){
                    manyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_selected);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_select);
                }else if (item.getStatus()==0){
                    manyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_selected);
                }

                manyi.setOnClickListener(view -> {
                    manyiImg.setBackgroundResource(R.mipmap.inspection_selected);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_select);
                    item.setStatus(1);
                });

                bumanyi.setOnClickListener(view -> {
                    manyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_selected);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_select);
                    item.setStatus(2);
                });

                yiban.setOnClickListener(view -> {
                    manyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    bumanyiImg.setBackgroundResource(R.mipmap.inspection_select);
                    yibanImg.setBackgroundResource(R.mipmap.inspection_selected);
                    item.setStatus(0);
                });
                break;
        }
    }
}
