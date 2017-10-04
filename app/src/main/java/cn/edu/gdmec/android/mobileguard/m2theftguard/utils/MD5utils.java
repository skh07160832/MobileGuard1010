package cn.edu.gdmec.android.mobileguard.m2theftguard.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.edu.gdmec.android.mobileguard.m2theftguard.dialog.SetupPasswordDialog;


public class MD5utils  {

    public static String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb =new StringBuilder();
            for (byte b : result){
                int number = b&0xff;
                String hex = Integer.toHexString(number);
                if (hex.length()==1){
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        return text;
    }

}
