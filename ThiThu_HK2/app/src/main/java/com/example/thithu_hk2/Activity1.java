package com.example.thithu_hk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Activity1 extends AppCompatActivity {
    private ToggleButton toggleButton;
    private Button thoat;
    private Boolean nhan;
    private Boolean settrangthai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Intent intent = getIntent();
        nhan = intent.getBooleanExtra("trangthai", false);
        toggleButton = (ToggleButton) this.findViewById(R.id.btnOnOff);
        if(nhan == true){
            settrangthai = true;
        }
        if(nhan == false){
            settrangthai = false;
        }
        toggleButton.setChecked(settrangthai);
        thoat = (Button) this.findViewById(R.id.btnBack);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
