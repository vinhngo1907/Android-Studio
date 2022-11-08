package com.example.buoi7_bai17_ngotrungvinh_b1709321;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    ListView lvUser, lvChat;
    EditText editContent;
    ImageButton btnSend, btnAdd;

    ArrayList<String> arrayUser, arrayChat;
    ArrayAdapter adapterUser, adapterChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        try {
            mSocket= IO.socket("http://10.13.128.52:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.on("server-send-result", onRetrieveResult);
        mSocket.on("server-send-user", onListUser);
        mSocket.on("server-send-chat", onListChat);

        arrayUser = new ArrayList<>();
        adapterUser = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayUser);
        lvUser.setAdapter(adapterUser);

        arrayChat = new ArrayList<>();
        adapterChat = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayChat);
        lvChat.setAdapter(adapterChat);
        //mSocket.emit("client-send-data","Lap trinh android");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editContent.getText().toString().trim().length() > 0){
                    mSocket.emit("client-rigister-user", editContent.getText().toString());
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editContent.getText().toString().trim().length() > 0){
                    mSocket.emit("client-send-chat", editContent.getText().toString());
                }
            }
        });
    }
    public void AnhXa(){
        btnAdd      = (ImageButton)findViewById(R.id.imagebuttonAdd);
        btnSend     = (ImageButton)findViewById(R.id.imagebuttonSend);
        editContent = (EditText)findViewById(R.id.editTextContent);
        lvChat      = (ListView)findViewById(R.id.listviewChat);
        lvUser      = (ListView)findViewById(R.id.listviewUser);
    }
    private Emitter.Listener onListUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject)args[0];
                    try {
                        JSONArray array = object.getJSONArray("danhsach");
                        arrayUser.clear();
                        for(int i=0; i<array.length(); i++){
                            String username = array.getString(i);
                            arrayUser.add(username);
                            //Toast.makeText(MainActivity.this, username, Toast.LENGTH_LONG).show();
                        }
                        adapterUser.notifyDataSetChanged();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onRetrieveResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    try {
                        boolean exits = object.getBoolean("ketqua");
                        if(exits){
                            Toast.makeText(MainActivity.this,"Tai khoan nay da ton tai", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this,"dang ki thanh cong", Toast.LENGTH_LONG).show();
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    private Emitter.Listener onListChat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject)args[0];
                    try {
                        String noidung = object.getString("chatcontent");
                        arrayChat.add(noidung);
                        adapterChat.notifyDataSetChanged();
                        //Toast.makeText(MainActivity.this, username, Toast.LENGTH_LONG).show();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    };
}
