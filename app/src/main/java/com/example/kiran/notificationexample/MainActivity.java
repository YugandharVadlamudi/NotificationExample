package com.example.kiran.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_click;
    EditText ed_name;
    String TAG = "NotificationManager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilization();
    }

    private void notificationShow() {
        Intent view = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, view, 0);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setTicker("hello")
                .setContentTitle("hello one")
                .setContentText("hello content text")
                // with out setsmallicon method notification wont work
                .setSmallIcon(R.drawable.profile)
//                .setLargeIcon(R.drawable.profile)
                .setLargeIcon(convertToBitMamp(R.drawable.profile))
                .setSound(soundParse())
                .setContentIntent(pendingIntent)
                .getNotification();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private Uri soundParse() {
        return Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.unknown_artist_iphone_alarm);
    }

    private Bitmap convertToBitMamp(int profile) {
        /*
        * Creates Bitmap objects BitmapFactory
        * return bitmap object
        * */
        return BitmapFactory.decodeResource(getApplicationContext().getResources(), profile);
    }

    private void initilization() {
        bt_click = (Button) findViewById(R.id.bt_click);
        ed_name = (EditText) findViewById(R.id.ed_name);
        bt_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_click) {
            Log.e(TAG, "onClick: button click");
            notificationShow();
        }

    }
}
