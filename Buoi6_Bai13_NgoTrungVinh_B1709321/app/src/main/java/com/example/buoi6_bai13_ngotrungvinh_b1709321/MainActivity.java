package com.example.buoi6_bai13_ngotrungvinh_b1709321;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    TextView network,gprs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText weburl = (EditText) findViewById(R.id.urlweb);
        final TextView htkn = (TextView) findViewById(R.id.hienthicokn);
        final TextView hlkn = (TextView) findViewById(R.id.hienthiloaikn);

        Button lkn = (Button) findViewById(R.id.loaiketnoi);
        lkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if(wifi.isAvailable() && wifi.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED
                        || mobile.isAvailable() && mobile.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED)
                    htkn.setText("Network is not connected");
                else if(!wifi.isConnectedOrConnecting() || !mobile.isConnectedOrConnecting())
                    htkn.setText("Network not available");
                if(wifi.isConnectedOrConnecting() || mobile.isConnectedOrConnecting())
                    htkn.setText("Network OK");

                if(info != null){
                    int type = info.getType();
                    if(type == ConnectivityManager.TYPE_MOBILE)
                        hlkn.setText("MOBILE");
                    else if(type == ConnectivityManager.TYPE_WIFI)
//                        hlkn.setText("WIFI");
                        hlkn.setText("GPRS");
                }else
                    hlkn.setText("NULL");
            }
        });
        Button dweb = (Button) findViewById(R.id.duyetweb);
        dweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = weburl.getText().toString();
//                Intent intent = new Intent(MainActivity.this, Webview.class);
//                intent.putExtra("url", url);
//                startActivity(intent);
                EditText txt_url = (EditText) findViewById(R.id.urlweb);
                String url = txt_url.getText().toString();
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
