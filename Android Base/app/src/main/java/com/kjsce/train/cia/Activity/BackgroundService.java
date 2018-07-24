package com.kjsce.train.cia.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.NotificationsAdapter;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.CardEntityToUpload;
import com.kjsce.train.cia.Entities.CardReferenceEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.AddCardListner;
import com.kjsce.train.cia.Listener.CardListener;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.Listener.OnTrainListChangeListener;
import com.kjsce.train.cia.Listener.OnTrainUpdated;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.CardUtility;
import com.kjsce.train.cia.Utilities.NotificationUtility;
import com.kjsce.train.cia.Utilities.TrainListUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BackgroundService extends Service implements NetworkStateReceiver.NetworkStateReceiverListener {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static NotificationUtility notificationUtility;
    public SharedData sharedData;
    private List<UserNotificationEntity> detailedCards;
    public static int id = 0;
    public static TrainListUtility trainListUtility;
    private NetworkStateReceiver networkStateReceiver;
    private CardUtility cardUtility;
    private List<CardEntityToUpload> cardEntityToUploads;
    private Helper helper;
    private static int i,x;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());

        cardUtility = new CardUtility();
        cardEntityToUploads = sharedData.getCardEntityList();

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        trainListUtility = new TrainListUtility(new OnTrainListChangeListener() {
            @Override
            public void OnDataChenged(List<String> newTrainList) {
                sharedData.setTrainList(newTrainList);
            }
        });


        notificationUtility = new NotificationUtility(sharedData.getUserEntity().getMobileNumber(), new OnNewNotificationAddedListener() {
            @Override
            public void onNotificationAdded(UserNotificationEntity newUserNotification) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle("New Report Generated!");
                mBuilder.setContentText(newUserNotification.getSender()+" has generated a report of train: "+newUserNotification.getTrainNumber()+" on "+
                        newUserNotification.getDateTime())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(newUserNotification.getSender()+" has generated a report of train: "+newUserNotification.getTrainNumber()+" on "+
                                getDate(newUserNotification.getDateTime())))
                .setPriority(Notification.PRIORITY_MAX);

                //Intent resultIntent = new Intent(context, Notifications.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(Notifications.class);

// Adds the Intent that starts the Activity to the top of the stack
                //stackBuilder.addNextIntent(resultIntent);
                /*PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);*/

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(id, mBuilder.build());
                id++;
            }
        },
                new OnNotificationListChangeListener() {
                    @Override
                    public void OnDataChanged(List<UserNotificationEntity> newNotificationList) {
                        System.out.println("notificationEntity: "+newNotificationList);
                        sharedData.setNotificationEntityList(newNotificationList);
                    }
                });
    }

    @Override
    public void networkAvailable() {
        uploadCards();
        Log.d("NetworkStatus", "Connected");
        /* TODO: Your connection-oriented stuff here */
    }

    @Override
    public void networkUnavailable() {
        Log.d("NetworkStatus", "Disconnected");
        /* TODO: Your disconnection-oriented stuff here */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                uploadCards();
                handler.postDelayed(runnable, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);
        uploadCards();
        return START_STICKY;
    }

    public void uploadCards(){
        cardEntityToUploads = sharedData.getCardEntityList();
        if(cardEntityToUploads==null)
            cardEntityToUploads = new ArrayList<CardEntityToUpload>();

        for(i=0;i<cardEntityToUploads.size() && helper.isInternetConnected();i++) {
            CardEntityToUpload cardEntityToUpload = cardEntityToUploads.get(i);
            cardUtility.uploadCard(cardEntityToUpload.getCardEntity(), new CardReferenceEntity(cardEntityToUpload.getBogeyNumber(),
                    cardEntityToUpload.getProblem(), cardEntityToUpload.getId(), cardEntityToUpload.getProblemStatus(), cardEntityToUpload.getSubTypeSelected()), new AddCardListner() {
                @Override
                public void onCompleteTask() {
                    cardEntityToUploads.remove(getIndex(cardEntityToUpload));
                    cardEntityToUploads.clear();
                    sharedData.setCardEntityList(cardEntityToUploads);
                    //TODO: How to find if completed and the only upload other
                }
            });
        }
    }

    public int getIndex(CardEntityToUpload cardEntityToUpload){
        cardEntityToUploads = sharedData.getCardEntityList();
        if(cardEntityToUploads==null)
            cardEntityToUploads = new ArrayList<CardEntityToUpload>();

        int j=0;
        for(j=0;j<cardEntityToUploads.size();j++){
            CardEntityToUpload c = cardEntityToUploads.get(j);
            if(cardEntityToUpload.getIdentifier()==c.getIdentifier())
                return j;
        }
        return 0;
    }


    public String getDate(String date){
        SimpleDateFormat spf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd/MM/yyyy | HH:mm");
        date = spf.format(newDate);
        return date;
    }
}
