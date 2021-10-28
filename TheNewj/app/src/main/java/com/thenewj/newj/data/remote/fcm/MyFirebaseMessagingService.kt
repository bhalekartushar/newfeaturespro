package com.thenewj.newj.data.remote.fcm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
import com.thenewj.newj.R
import com.thenewj.newj.ui.main.MainActivity

class MyFirebaseMessagingService /*: FirebaseMessagingService() */{
/*
    companion object {
        const val TAG: String = "MFMS"
        var sharedPref: SharedPreferences? = null
        var token: String?
            get() {
                return sharedPref?.getString("Token", "")
            }
            set(value) {
                sharedPref?.edit()?.putString("Token", value)?.apply()
            }
        const val channelId: String = "newj_fcm_message_id"
        const val channelName: String = "com.thenewj.newj.fcm_notification_message"
    }

    override fun onNewToken(newToken: String) {
        Log.d(TAG, "Refreshed token: $newToken")
        token = newToken
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            if (*//* Check if data needs to be processed by long running job *//* true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob(remoteMessage.data)
            } else {
                // Handle message within 10 seconds
                handleNow(remoteMessage.data)
            }
        }
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            it.body?.let { it1 -> it.title?.let { it2 -> showNotification(it2, it1) } }
        }
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(100, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            *//*.setContentTitle(title)
            .setContentText(message)*//*

        builder.setContent(getRemoteView(title, message))
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.thenewj.newj", R.layout.notification)
        remoteView.setTextViewText(R.id.tv_title, title)
        remoteView.setTextViewText(R.id.tv_message, message)
        return remoteView
    }

    private fun scheduleJob(data: MutableMap<String, String>) {
        val work = OneTimeWorkRequest.Builder(MyFcmWorker::class.java).build()
        WorkManager.getInstance(this).beginWith(work).enqueue()
    }

    private fun handleNow(data: MutableMap<String, String>) {

    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }*/
}