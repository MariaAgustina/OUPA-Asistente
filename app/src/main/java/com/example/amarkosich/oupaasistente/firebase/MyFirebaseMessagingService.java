package com.example.amarkosich.oupaasistente.firebase;

import android.content.Intent;
import android.util.Log;

import com.example.amarkosich.oupaasistente.activities.SOSAlarmActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.i(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());

            Intent sosAlertIntent = new Intent().setClassName("com.example.amarkosich.oupaasistente", "com.example.amarkosich.oupaasistente.activities.SOSAlarmActivity");
            getApplicationContext().startActivity(sosAlertIntent);

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
               //handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            Intent sosAlertIntent = new Intent().setClassName("com.example.amarkosich.oupaasistente", "com.example.amarkosich.oupaasistente.activities.SOSAlarmActivity");
            getApplicationContext().startActivity(sosAlertIntent);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}
