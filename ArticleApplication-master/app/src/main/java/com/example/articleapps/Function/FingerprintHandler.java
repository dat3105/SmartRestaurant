package com.example.articleapps.Function;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.articleapps.LoginForm.Activity_Register;
import com.example.articleapps.MainMenu.MainMenu;
import com.example.articleapps.R;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cenCancellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject,cenCancellationSignal,0,this,null);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        new TTFancyGifDialog.Builder((Activity) context)
                .setTitle("Sai dấu vân tay")
                .setMessage("Có vẻ như chúng tôi không tìm thấy dấu vân tay của bạn trong hệ thống,vui lòng vào phần cài đặt để thiết lập ")
                .setPositiveBtnText("Cài đặt")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Trở về")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.wrong)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Activity_Register.class)) ;
                    }
                })
                .OnNegativeClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        new TTFancyGifDialog.Builder((Activity) context)
                .setTitle("Đăng nhập thành công")
                .setMessage("Chào mừng quý khách hàng ")
                .setPositiveBtnText("Cài đặt")
                .setPositiveBtnBackground("#22b573")
                .setGifResource(R.drawable.sucess)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        context.startActivity(new Intent(context, MainMenu.class));
                    }
                })
                .build();


    }
}
