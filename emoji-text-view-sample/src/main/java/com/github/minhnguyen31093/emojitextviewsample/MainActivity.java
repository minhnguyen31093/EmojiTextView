package com.github.minhnguyen31093.emojitextviewsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.github.minhnguyen31093.emojitextview.EmojiTextView;
import com.github.minhnguyen31093.emojitextview.EmojiUtils;

/**
 * Created by Minh. Nguyen Le on 3/10/2016.
 */
public class MainActivity extends AppCompatActivity {

    private EmojiTextView txtEmoji;
    private EditText edtEmoji;
    private Button btnEmoji, btnNotiEmoji;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mn_mainActivity_btnEmoji:
                    txtEmoji.setEmojiText(edtEmoji.getText().toString());
                    edtEmoji.setText("");
                    break;
                case R.id.mn_mainActivity_btnNotiEmoji:
                    createNotification();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_activity_main);
        initView();
        initEvent();
        fillData();
    }

    private void initView() {
        txtEmoji = (EmojiTextView) findViewById(R.id.mn_mainActivity_txtEmoji);
        edtEmoji = (EditText) findViewById(R.id.mn_mainActivity_edtEmoji);
        btnEmoji = (Button) findViewById(R.id.mn_mainActivity_btnEmoji);
        btnNotiEmoji = (Button) findViewById(R.id.mn_mainActivity_btnNotiEmoji);
    }

    private void initEvent() {
        btnEmoji.setOnClickListener(onClickListener);
        btnNotiEmoji.setOnClickListener(onClickListener);
    }

    private void fillData() {
//        txtEmoji.setIsFacebook();
        txtEmoji.setEmojiText("QWERTYUIOPASDFGHJKLZXCVBNM\r\n:) :( ;) :D ;;) >:D< :-/ :x :\"> :P :-* =(( :-O X( :> B-) :-S " +
                "#:-S >:) :(( :)) :| /:) =)) O:-) :-B =; :-c :)] ~X( :-h :-t 8-> I-) 8-| L-) :-& :-$ " +
                "[-( :O) 8-} <:-P (:| =P~ :-? #-o =D> :-SS @-) :^o :-w :-< >:P <):) X_X :!! \\m/ :-q " +
                ":-bd ^#(^ :ar! ^_^ :3 :v");
    }

    public void createNotification() {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.mn_custom_notification);

        // Set Notification Title
        String strtitle = getString(R.string.app_name);
        // Set Notification Text
        String strtext = txtEmoji.getText().toString();

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, MainActivity.class);
        // Send data to NotificationView Class
        intent.putExtra("title", strtitle);
        intent.putExtra("text", strtext);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.mipmap.ic_launcher)
                        // Set Ticker Message
                .setTicker(getString(R.string.app_name))
                        // Dismiss Notification
                .setAutoCancel(true)
                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                        // Set RemoteViews into Notification
                .setContent(remoteViews);

        // Locate and set the Image into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.imagenotileft, R.mipmap.ic_launcher);
        remoteViews.setImageViewResource(R.id.imagenotiright, R.mipmap.ic_launcher);

        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title, getString(R.string.app_name));
        remoteViews.setTextViewText(R.id.text,
                EmojiUtils.getEmojiSpanned(this, null, ":) :( ;) :D ;;) >:D< :-/ :x :\"> :P :-* =(( :-O X( :> B-) :-S " +
                "#:-S >:) :(( :)) :| /:) =)) O:-) :-B =; :-c :)] ~X( :-h :-t 8-> I-) 8-| L-) :-& :-$ " +
                "[-( :O) 8-} <:-P (:| =P~ :-? #-o =D> :-SS @-) :^o :-w :-< >:P <):) X_X :!! \\m/ :-q " +
                ":-bd ^#(^ :ar! ^_^ :3 :v")
        );


        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
    }
}
