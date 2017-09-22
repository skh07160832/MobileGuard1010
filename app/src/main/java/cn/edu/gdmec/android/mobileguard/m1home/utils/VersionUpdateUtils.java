package cn.edu.gdmec.android.mobileguard.m1home.utils;


import android.app.Activity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

import cn.edu.gdmec.android.mobileguard.m1home.entity.VersionEntity;

/**
 * Created by Administrator on 2017/9/22.
 */

public class VersionUpdateUtils {
    private  String mVersion;
    private Activity context;
    private VersionEntity versionEntity;

    public  VersionUpdateUtils(String mVersion,Activity context){
        this.mVersion=mVersion;
        this.context=context;
    }




    public void getCloudVersion(){
        try {
            HttpClient httpclient=new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),5000);
            HttpConnectionParams.setSoTimeout(httpclient.getParams(),5000);
           HttpGet httpGet=new HttpGet("http://android2017.duapp.com/updateinfo.html");
            HttpResponse execute=httpclient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity=execute.getEntity();
                String result= EntityUtils.toString(httpEntity,"utf-8");
                JSONObject jsonObject=new JSONObject(result);

                versionEntity=new VersionEntity();
                versionEntity.versionCode=jsonObject.getString("code");
                versionEntity.description=jsonObject.getString("des");
                versionEntity.apkurl=jsonObject.getString("apkurl");

                if(!mVersion.equals(versionEntity.description)){
                    //版本不同，需要进行升级
                    System.out.println(versionEntity.description);
                    DownloadUtils downloadUtils=new DownloadUtils();
                    downloadUtils.downloadApk(versionEntity.apkurl,"mobileguard.apk",context);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


}
