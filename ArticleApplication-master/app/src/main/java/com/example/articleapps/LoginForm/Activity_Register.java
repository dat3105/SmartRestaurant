package com.example.articleapps.LoginForm;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.articleapps.MainMenu.MainMenu;
import com.example.articleapps.R;
import com.gc.materialdesign.widgets.ProgressDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;

public class Activity_Register extends AppCompatActivity {
    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                    "\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+
                    "\\."+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                    ")+"
    );
    private static final String USER_AGENT ="" ;

    private RelativeLayout rlayout;
    private Animation animation;
    ImageView imgAvarta;
    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Menu menu;
    public static TextInputLayout etName;
    public static TextInputLayout etEmail;
    public static TextInputLayout etPassWord;
    public static TextInputLayout etRePassWord;
    private Button btnLogin;
    static String EmailInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPassWord=findViewById(R.id.etPassword);
        etRePassWord=findViewById(R.id.etRePassword);
        btnLogin=findViewById(R.id.btLogin);

        rlayout     = findViewById(R.id.rlayout);
        animation   = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        rlayout.setAnimation(animation);
        imgAvarta=findViewById(R.id.imgAvartaz);
        imgAvarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_Register.this, "dsdasdasdsa", Toast.LENGTH_SHORT).show();
                openFileChooser();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.setError(null);
                etName.setError(null);
                etRePassWord.setError(null);
                etPassWord.setError(null);

                if( etEmail.getEditText().getText().toString().isEmpty()){
                    etEmail.setError("Không được để trống Email");
                }
                if( etPassWord.getEditText().getText().toString().isEmpty()){
                    etPassWord.setError("Không được để trống Password");
                }
                if( etName.getEditText().getText().toString().isEmpty()){
                    etName.setError("Không được để trống Name");
                }


                JSONObject signUp = new JSONObject();
                try {
                    signUp.put("email", etEmail.getEditText().getText().toString());
                    signUp.put("password", etPassWord.getEditText().getText().toString());
                    signUp.put("repassword", etRePassWord.getEditText().getText().toString());
                    signUp.put("name", etName.getEditText().getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Request().execute(String.valueOf(signUp));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(imgAvarta);
        }
    }

    public class Request extends AsyncTask<String,String,String> {
        private static final String USER_AGENT = "";

        ProgressDialog progressDialog;
        protected void onPreExecute() {
           progressDialog = new ProgressDialog(Activity_Register.this,"Loading");
           progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonData=params[0];
            HttpURLConnection con=null;
            BufferedReader reader=null;
            try{
                URL url=new URL("https://smartrestaurantntd.herokuapp.com/api/user/postSignup.marvelTeam");
                con=(HttpURLConnection)url.openConnection();
                con.setDoOutput(true);

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type","application/json");
                con.setRequestProperty("Accept","application/json");

                Writer writer=new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
                writer.write(JsonData);
                writer.close();
                int responseCode=0;
                try {
                    responseCode = con.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader in = null;
                try {
                    in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String inputLine = null;
                StringBuffer response = new StringBuffer();

                while (true) {
                    try {
                        if (!((inputLine = in.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    response.append(inputLine);
                }

                JSONObject obj=new JSONObject(String.valueOf(response));

                String result=obj.getString("msg");
                return result;



            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog != null)
                progressDialog.dismiss();
            System.out.println(result);

             if(result.equals("Đăng ký thành công !!!")){
                Intent intent = new Intent(Activity_Register.this, Activity_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                 Toast.makeText(Activity_Register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
            }else if(result.equals("Email đã được đăng ký !!!")){
                etEmail.setError("Email đã được đăng kí");
            }else if(result.equals("Email sai định dạng !!!")){
                etPassWord.setError("Email sai định dạng");
            }else if(result.equals("Nhập lại mật khẩu không đúng !!!")){
                etRePassWord.setError("Nhập lại mật khẩu không đúng");
            }
            super.onPostExecute(result);
        }
    }




}
