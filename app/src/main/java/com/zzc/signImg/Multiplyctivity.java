package com.zzc.signImg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zzc.adapter.MultiplyAdapter;

import java.util.ArrayList;
import java.util.List;

public class Multiplyctivity extends AppCompatActivity {

    String inspectionTtle[] = {"1、POS机具的数量型号与原登记是否相符", "2、是否妥善保管POS机具", "3、是否有出租、出借、挪用、私自拆卸、改动POS机或程序等情况？"
            , "4、POS设备运转是否良好、线路是否畅通、凭证是否充足？", "5、联机交易测试情况","6、测试"};
    boolean check[] = {true, true, false, true, true,true};
    int status[] = {0, 0, 1, 0, 0,0};

    RecyclerView recyclerView;
    List<WorkGirdBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply);

        recyclerView = findViewById(R.id.multiplyList);
        getData();

    }

    private void getData() {
        list = new ArrayList<>();
        for (int i = 0; i < inspectionTtle.length; i++) {
            if(i!=5){
                WorkGirdBean workGirdBean = new WorkGirdBean(0);
                workGirdBean.setOrderType(inspectionTtle[i]);
                workGirdBean.setCheck(check[i]);
                list.add(workGirdBean);
            }else if(i==5){
                WorkGirdBean workGirdBean = new WorkGirdBean(1);
                workGirdBean.setOrderType(inspectionTtle[i]);
                workGirdBean.setStatus(status[i]);
                list.add(workGirdBean);
            }
        }
        setAdapter();
    }

    private void setAdapter() {
        MultiplyAdapter adapter = new MultiplyAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}