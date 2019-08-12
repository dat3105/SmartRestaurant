package com.example.articleapps.LoginForm;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.articleapps.Function.FingerprintHandler;
import com.example.articleapps.MainMenu.MainMenu;
import com.example.articleapps.R;
import com.gc.materialdesign.widgets.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btRegister;
    private ImageView circle1;
    private Button btlogin;
    TextInputLayout etName;
    TextInputLayout etPassword;
    TextView tvLogin;
    private KeyStore keyStore;
    private static final String KEY_NAME = "EDMTDev";
    private Cipher cipher;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);
        circle1 = findViewById(R.id.circle1);
        btlogin = findViewById(R.id.btLogin);
        etName=findViewById(R.id.etUser);
        etPassword=findViewById(R.id.etPassword);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setError(null);
                etPassword.setError(null);
                if(etName.getEditText().getText().toString().isEmpty()){
                    etName.setError("Không được để trống Email");
                }
                if(etPassword.getEditText().getText().toString().isEmpty()){
                    etPassword.setError("Không được để trống Password");
                }
                JSONObject Login = new JSONObject();
                try {
                    Login.put("email", etName.getEditText().getText().toString());
                    Login.put("password", etPassword.getEditText().getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Request().execute(String.valueOf(Login));



            }
        });

        btRegister.setOnClickListener(Activity_Login.this);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.USE_FINGERPRINT)!=PackageManager.PERMISSION_GRANTED)

        {

            return;
        }
        if(!fingerprintManager.isHardwareDetected())
            Toast.makeText(this,"Fingerprint authentication permission not enable",Toast.LENGTH_SHORT).

                    show();
        else

        {
            if (!fingerprintManager.hasEnrolledFingerprints())
                Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_SHORT).show();
            else {
                if (!keyguardManager.isKeyguardSecure())
                    Toast.makeText(this, "Lock screen security not enabled in Settings", Toast.LENGTH_SHORT).show();
                else
                    genKey();

                if (cipherInit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuthentication(fingerprintManager, cryptoObject);

                }
            }
        }

    }
    private boolean cipherInit() {

        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/"+KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey)keyStore.getKey(KEY_NAME,null);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            return true;
        } catch (IOException e1) {

            e1.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e1) {

            e1.printStackTrace();
            return false;
        } catch (CertificateException e1) {

            e1.printStackTrace();
            return false;
        } catch (UnrecoverableKeyException e1) {

            e1.printStackTrace();
            return false;
        } catch (KeyStoreException e1) {

            e1.printStackTrace();
            return false;
        } catch (InvalidKeyException e1) {

            e1.printStackTrace();
            return false;
        }

    }

    private void genKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build()
            );
            keyGenerator.generateKey();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == btRegister) {
            Intent a = new Intent(Activity_Login.this, Activity_Register.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "login");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Activity_Login.this, pairs);
            startActivity(a, activityOptions.toBundle());
        }
    }

    // dau van tay

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class Request extends AsyncTask<String,String,String> {
        private static final String USER_AGENT = "";

        ProgressDialog progressDialog;

        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Activity_Login.this, "Loading");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonData = params[0];
            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://smartrestaurantntd.herokuapp.com/api/user/checkLogin.marvelTeam");
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);

                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");

                Writer writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
                writer.write(JsonData);
                writer.close();
                int responseCode = 0;
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

                JSONObject obj = new JSONObject(String.valueOf(response));

                String result = obj.getString("msg");
                System.out.println("đbdbdbdbdbdbdbdbdbđebdbébdndndndmdmdm"+result);

                return result;


            } catch (Exception e) {
                return "ok";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (progressDialog != null)
                progressDialog.dismiss();
            System.out.println(result);

            if (result.equals("ok")) {
                Intent intent = new Intent(Activity_Login.this, MainMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                Toast.makeText(Activity_Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }else if(result.equals("Email sai định dạng !!!")){
                etName.setError("Email sai định dạng");
            }else if(result.equals("Sai email hoặc password !!!")){
                etPassword.setError("Mật khẩu không đúng");
            }
                super.onPostExecute(result);
            }
        }
    }

