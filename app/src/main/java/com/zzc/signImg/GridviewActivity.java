package com.zzc.signImg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zzc.adapter.ServiceRecordAdapter;
import com.zzc.utils.SimpleDividerItem;

import java.util.ArrayList;
import java.util.List;

public class GridviewActivity extends AppCompatActivity {

    String content[] = {"设备投放", "取回维修", "设备撤机", "部件更换", "程序更新", "商户培训", "耗材配送", "备机顶用", "设备送回", "日常巡检", "其他服务"};
    List<WorkGirdBean> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        TextView add_gridview = findViewById(R.id.add_gridview);
        recyclerView = findViewById(R.id.gridview);
        getDate();

        add_gridview.setOnClickListener(view -> {
            Toast.makeText(this,list.toString(),Toast.LENGTH_SHORT).show();
        });
    }

    private void getDate() {
        list = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            WorkGirdBean workGirdBean = new WorkGirdBean();
            workGirdBean.setOrderType(content[i]);
            list.add(workGirdBean);
        }
        setAdapter();
    }

    private void setAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SimpleDividerItem(this,30,50));
        ServiceRecordAdapter serviceRecordAdapter = new ServiceRecordAdapter(this,R.layout.item_work_grid,list);
        recyclerView.setAdapter(serviceRecordAdapter);
        serviceRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkGirdBean workGirdBean = list.get(position);
                /*多选*/
                TextView textView = view.findViewById(R.id.item_grid_categoryname);
                if(view.isActivated()){
                    view.setActivated(false);
                    list.get(position).setCheck(false);
                    textView.setTextColor(Color.parseColor("#666666"));
                }else {
                    view.setActivated(true);
                    list.get(position).setCheck(true);
                    textView.setTextColor(Color.parseColor("#4C86FF"));
                }
            }
        });
    }
}