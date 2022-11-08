package com.example.thithu_hk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button tat,mo;
    private TextView hienthitrangthai;
    private  boolean trangthai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mo = (Button) findViewById(R.id.btnMo);
        mo.setOnClickListener(this);
        tat = (Button) findViewById(R.id.btnTat);
        tat.setOnClickListener(this);
        final Button thoat = (Button) findViewById(R.id.btnThoat);
        thoat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        hienthitrangthai = (TextView)findViewById(R.id.status);
        if(v==tat){
            hienthitrangthai.setText("Đã tắt");
            trangthai = false;
            intent = new Intent(MainActivity.this,Activity1.class);
            intent.putExtra("trangthai",trangthai);
            startActivity(intent);
        }else if(v==mo){
            hienthitrangthai.setText("Đã mở");
            trangthai = true;
            intent = new Intent(MainActivity.this,Activity1.class);
            intent.putExtra("trangthai",trangthai);
            startActivity(intent);
        }
    }
}
