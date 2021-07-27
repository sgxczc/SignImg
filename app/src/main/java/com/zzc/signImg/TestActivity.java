package com.zzc.signImg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zzc.adapter.InspectionListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    String inspectionTtle[] = {"1、POS机具的数量型号与原登记是否相符", "2、是否妥善保管POS机具", "3、是否有出租、出借、挪用、私自拆卸、改动POS机或程序等情况？"
            , "4、POS设备运转是否良好、线路是否畅通、凭证是否充足？", "5、联机交易测试情况"};
    boolean check[] = {true, true, false, true, true};
    List<WorkGirdBean> list;
    RecyclerView recyclerView;
    String message = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView textView = findViewById(R.id.test);
        recyclerView = findViewById(R.id.recyclerView);
        Switch homeTakers = findViewById(R.id.inspection__takers);
        homeTakers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    message = "2";
                    EventBus.getDefault().post(new EventMessage(message));
                } else {
                    message = "1";
                    EventBus.getDefault().post(new EventMessage(message));
                }
            }
        });
        getData();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this,GridviewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        list = new ArrayList<>();
        for (int i = 0; i < inspectionTtle.length; i++) {
            WorkGirdBean workGirdBean = new WorkGirdBean();
            workGirdBean.setOrderType(inspectionTtle[i]);
            workGirdBean.setCheck(check[i]);
            list.add(workGirdBean);
        }
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        InspectionListAdapter listAdapter = new InspectionListAdapter(this,R.layout.item_inspection, list);
        recyclerView.setAdapter(listAdapter);
        EventBus.getDefault().post(new EventMessage(message));
    }

    public void disableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(true);
        }
    }
}