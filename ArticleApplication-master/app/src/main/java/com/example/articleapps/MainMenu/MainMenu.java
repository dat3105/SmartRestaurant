package com.example.articleapps.MainMenu;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.articleapps.AdapterMainMenu.AdapterMainMenu;
import com.example.articleapps.LoginForm.ProfileActivity;
import com.example.articleapps.MainMenu.News.NewsActivity;
import com.example.articleapps.TableMenu.TableMenu;
import com.example.articleapps.FoodTab.MainFood;
import com.example.articleapps.Function.ChatBotMarvel.ChatBot;
import com.example.articleapps.Function.MusicList.ListenMusic;
import com.example.articleapps.Objectt.MainMenuObject;
import com.example.articleapps.R;
import com.example.articleapps.thongkevahoadon.thongke;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    RecyclerView rv_menu;
    EditText number;
    EditText message;
    TextView nameuser, walletuser, review, network, plugins, myapps, mainmenus,
            pagetitle, pagesubtitle;
    AdapterMainMenu mAdapter;
    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3,imgAvarta;
    ArrayList<MainMenuObject> img;
    MainMenuObject mainMenu;
    private Dialog dialog;
//    private Button btnSend;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            if(position==0){
                Intent i = new Intent(MainMenu.this, TableMenu.class);
                startActivity(i);
            }
            else if(position==1){
                Intent i = new Intent(MainMenu.this, MainFood.class);
                startActivity(i);
            }
            else if(position==2){
                Intent i = new Intent(MainMenu.this, ListenMusic.class);
                    startActivity(i);
                }
            else if(position==3){
                Intent i = new Intent(MainMenu.this, NewsActivity.class);
                startActivity(i);
            }else if(position==5){
                Intent i=new Intent(MainMenu.this, thongke.class);
                startActivity(i);
            }
            }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);
        rv_menu = findViewById(R.id.rv_menu);
//        btnSend=findViewById(R.id.buttonSend);
        number = findViewById(R.id.number);
        message=findViewById(R.id.content);

        rv_menu.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_menu.setLayoutManager(layoutManager);
        mainMenu = new MainMenuObject("Đặt bàn ăn",R.drawable.iclist,R.drawable.mau1);
        MainMenuObject mn2 = new MainMenuObject("Đặt món ăn",R.drawable.icbusinessplayer,R.drawable.mau2);
        MainMenuObject mn3 = new MainMenuObject("Nghe nhạc",R.drawable.icapps,R.drawable.mau3);
        MainMenuObject mn4 = new MainMenuObject("Tin tức",R.drawable.icguide,R.drawable.mau4);
        MainMenuObject mn5 = new MainMenuObject("Liên hệ",R.drawable.ic_lock,R.drawable.mau1);
        MainMenuObject mn6 = new MainMenuObject("Xem hóa đơn",R.drawable.icapps,R.drawable.mau2);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab_mess=findViewById(R.id.fab2);
        fab_mess.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog = new Dialog(MainMenu.this);
                                            dialog.setContentView(R.layout.dialog_sms);
                                            Button btnsend = v.findViewById(R.id.buttonSend);

                                            if(checkPermission(Manifest.permission.SEND_SMS)){

                                            }else{
                                                ActivityCompat.requestPermissions(getParent(),
                                                        new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
                                            }
                                            dialog.setTitle("Gửi SMS đến quản lý nhà hàng");
                                            dialog.show();
                                        }
                                        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        new IntentIntegrator(MainMenu.this).initiateScan(); // QR code
            }
        });
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { trả về giá trị quét mã QR
//            super.onActivityResult(requestCode, resultCode, data);
//            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//            if(result != null)
//                if (result.getContents() != null){
//                    tvBarCode.setText( result.getContents());
//                }else{
//                    tvBarCode.setText("Error");
//                }
//        }
        img = new ArrayList<>();
        img.add(mainMenu); img.add(mn2); img.add(mn3); img.add(mn4); img.add(mn5); img.add(mn6);

        mAdapter = new AdapterMainMenu( img,this);
        rv_menu.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);



        nameuser = findViewById(R.id.nameuser);
        walletuser = findViewById(R.id.walletuser);

        imageView3 = findViewById(R.id.imageView3);


        mainmenus = findViewById(R.id.mainmenus);

        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        btnguide = findViewById(R.id.btnguide);
        imgAvarta = findViewById(R.id.imageView2);
        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent i = new Intent(MainMenu.this, ChatBot.class);
        startActivity(i);
            }
        });

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        btnguide.startAnimation(atgthree);
imgAvarta.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainMenu.this, ProfileActivity.class);
        startActivity(i);
    }
});


    }
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
    public void onSend(View v){
        String phoneNumber = number.getText().toString();
        String smsMessage = message.getText().toString();

        if(phoneNumber == null || phoneNumber.length() == 0 ||
                smsMessage == null || smsMessage.length() == 0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(this, "Message Sent!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
    }

