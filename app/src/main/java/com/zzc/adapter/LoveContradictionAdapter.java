package com.zzc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzc.signImg.R;
import com.zzc.signImg.WorkGirdBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.security.AccessController.getContext;

public class LoveContradictionAdapter extends BaseQuickAdapter<WorkGirdBean, BaseViewHolder> {

    private static int choiceNum = 0;
    Context context;
    private static List<String> choices = new ArrayList<>();

    public LoveContradictionAdapter(Context context,int layoutResId, @Nullable List<WorkGirdBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    //取用户选择了几个
    public static int getChoiceNum(){
        return choiceNum;
    }

    //获取用户选择的字符串；
    public static String getChoiceString(){
        String temp = "";
        //已经选了三个
        if(choiceNum == 3){
            for (int i = 0;i < 3; i++){
                if(i < 2){
                    temp += choices.get(i).toString()+",";
                }else {
                    temp += choices.get(i).toString();
                }
            }
        }
        return temp;
    }

    //清空数组；
    public static void clearAll(){
        //选中项归零
        choiceNum = 0;
        if(choices.size() >0){
            //清除选中的数组
            List<String> temp = new ArrayList<>();
            for(int i=0;i<choices.size();i++){
                temp.add(choices.get(i));
            }
            choices.removeAll(temp);
            choices.clear();
        }
    }

    //取消选中就移除字符串；
    private void removeChoice(String choiced){
        if(choices.size()>0){
            for(int i = 0 ;i < choices.size();i++){
                if(choiced.equals(choices.get(i))){
                    choices.remove(choiced);
                    Log.d("ADAD",choiced);
                    break;
                }
            }
        }else{
            Log.d("ADAD","数组为空");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkGirdBean item) {
//        helper.getView(R.id.list_father).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckBox view = helper.getView(R.id.list_choice);
//                TextView text = helper.getView(R.id.list_info);
//                String s = text.getText().toString();
//                if(view.isChecked()){
//                    view.setChecked(false);
//                    if(choiceNum >= 1){
//                        choiceNum -= 1;
//                        removeChoice(s);
//                    }
//                }else{
//                    if(choiceNum < 5){
//                        choiceNum += 1;
//                        choices.add(s);
//                        view.setChecked(true);
//                    }else{
//                        Toast.makeText(context,"最多只能选择五项",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                Log.d("ADAD","选中的数量："+choiceNum);
//                for(int i=0;i<choices.size();i++){
//                    Log.d("ADAD","选中的项目"+i+"："+choices.get(i));
//                }
//                Log.d("ADAD","选中的项目数组的索引："+choices.size());
//            }
//        });
    }

}
