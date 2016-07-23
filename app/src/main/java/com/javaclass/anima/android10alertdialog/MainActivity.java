package com.javaclass.anima.android10alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String[] opt = {"Android","ios","windows","other"};
    AlertDialog alertDialog;

    Button Dialog4sec, DialogButtons, DialogListadapter, DialogCustomer;
    TextView info;
    EditText eNickname,ePassword,ePassword2,ePhonenum,eUsername;
    Timer timer;
    uiHandler handler;
    EditText filename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        handler = new uiHandler();

        info = (TextView) findViewById(R.id.info);





        Dialog4sec = (Button) findViewById(R.id.Dialog4sec);
        DialogButtons = (Button) findViewById(R.id.DialogButtons);

        DialogCustomer = (Button) findViewById(R.id.DialogCustomer);
        DialogListadapter = (Button) findViewById(R.id.DialogListadapter);

        Dialog4sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog4sec();
            }
        });

        DialogButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogButtons();
            }
        });

        DialogListadapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogListAdapter();
            }
        });

        DialogCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.newreg, null);
                eUsername = (EditText) item.findViewById(R.id.reg_name);
                ePassword = (EditText) item.findViewById(R.id.password);

                eNickname = (EditText)item.findViewById(R.id.reg_nickname);
                ePassword2 = (EditText)item.findViewById(R.id.reg_password2);
                ePhonenum = (EditText) item.findViewById(R.id.reg_phonenum);

              AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder  .setTitle("  拉拉網 新用戶註冊  ")
                        .setCancelable(false)
                        .setView(item)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Toast.makeText(getApplicationContext(),"HELLO"+eUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                alertDialog.dismiss();
                            }
                        });


                alertDialog = builder.create();
                alertDialog.show();



            }
        });

    }






    private void ShowDialogListAdapter(){

        String[] from={"icon","title","des"};
        int[] to = {R.id.imageIcon,R.id.title,R.id.message};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您的作業系統為何？");
        builder.setIcon(R.drawable.note);

        ArrayList data = new ArrayList();

        HashMap<String,Object> item0 = new HashMap<>();
        item0.put(from[0],R.drawable.android);
        item0.put(from[1],opt[0]);
        item0.put(from[2],"就是你正在學的東西");
        data.add(item0);

        HashMap<String,Object> item1 = new HashMap<>();
        item1.put(from[0],R.drawable.windows);
        item1.put(from[1],opt[2]);
        item1.put(from[2],"pc 市場的霸主");
        data.add(item1);

        HashMap<String,Object> item2 = new HashMap<>();
        item2.put(from[0],R.drawable.ios);
        item2.put(from[1],opt[1]);
        item2.put(from[2],"橉一個陣營的好東西");
        data.add(item2);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.list_adapter_dialog_item,from,to);

        builder.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                info.setText("您選擇的為："+opt[i]);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


    private void ShowDialog4sec() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認對話框：").setMessage("歡迎使用強大的 APP");
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();

        timer.schedule(new CloseDialogTask(), 4000);
    }

    private void ShowDialogButtons() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.question);
        builder.setTitle("確認對話框：").setMessage("請輸入檔案名稱：");
        filename = new EditText(MainActivity.this);
        builder.setView(filename);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton("再說", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog = builder.create();
        alertDialog.show();


    }

    private class uiHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3: {
                    alertDialog.dismiss();
                }
            }
        }
    }

    private class CloseDialogTask extends TimerTask {

        @Override
        public void run() {
            handler.sendEmptyMessage(3);
        }
    }

}
