package com.example.fashionboomer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashionboomer.dao.UserDao;
import com.example.fashionboomer.entity.User;

public class vipActivity extends AppCompatActivity {
    EditText name = null;
    EditText username = null;
    EditText password = null;
    EditText phone = null;
    EditText age = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
    }


    public void register(View view){



        String cname = name.getText().toString();
        String cusername = username.getText().toString();
        String cpassword = password.getText().toString();

        System.out.println(phone.getText().toString());

        String cphone = phone.getText().toString();
        int cgae = Integer.parseInt(age.getText().toString());

        if(cname.length() < 2 || cusername.length() < 2 || cpassword.length() < 2 ){
            Toast.makeText(getApplicationContext(),"입력한내용 정확하지 않아서 다시 입력하세요",Toast.LENGTH_LONG).show();
            return;

        }


        User user = new User();

        user.setName(cname);
        user.setUsername(cusername);
        user.setPassword(cpassword);
        user.setAge(cgae);
        user.setPhone(cphone);

        new Thread(){
            @Override
            public void run() {

                int msg = 0;

                UserDao userDao = new UserDao();

                User uu = userDao.findUser(user.getName());

                if(uu != null){
                    msg = 1;
                }

                boolean flag = userDao.register(user);
                if(flag){
                    msg = 2;
                }
                hand.sendEmptyMessage(msg);

            }
        }.start();


    }
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"회원가입 실페하였습니다",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"이미 존재하는 아이디입니다",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 2)
            {
                //startActivity(new Intent(getApplication(),MainActivity.class));

                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","회원가입");
                setResult(RESULT_CANCELED,intent);
                finish();
            }

        }
    };
}