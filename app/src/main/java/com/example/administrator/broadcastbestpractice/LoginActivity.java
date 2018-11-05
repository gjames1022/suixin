package com.example.administrator.broadcastbestpractice;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/24.
 */

public class LoginActivity extends AppCompatActivity{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    private EditText tuser;
    private EditText pass;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref= PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);

        tuser=accountEdit;
        SpannableString ss = new SpannableString("请输入用户名");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15,true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tuser.setHint(new SpannedString(ss));

        pass=passwordEdit;
        SpannableString ss1 = new SpannableString("请输登录密码");//定义hint的值
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(15,true);//设置字体大小 true表示单位是sp
        ss1.setSpan(ass1, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        pass.setHint(new SpannedString(ss1));


        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        login=(Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password",false);

        if(isRemember){
            String account  = pref.getString("account","");
            String password  = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin")&& password.endsWith("123456")){

                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码无效",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
