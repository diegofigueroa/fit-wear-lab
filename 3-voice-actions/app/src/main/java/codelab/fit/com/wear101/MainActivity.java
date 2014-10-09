package codelab.fit.com.wear101;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSimpleNotification(final View button) {
        createSimpleNotification("Test", "Hi I'm a simple notification, you should be able to se mee on your watch!");
    }

    public void showSimpleNotificationWithAction(final View button) {
        createNotificationWithAction("Test", "Hi I'm a simple notification, with some actions.");
    }

    public void showSimpleNotificationWithWearAction(final View button) {
        createNotificationWithWearAction("Test", "Hi I'm a simple notification, with actions too!");
    }

    public void showSimpleNotificationWithVoiceAction(final View button) {
        createNotificationWithVoiceAction("Test", "Hi! Are you there? Want to say something?");
    }

    private void createSimpleNotification(final String title, final String content) {
        // An id for the notification to be showed, it should be unique.
        final int notificationId = 001;

        // Build intent for notification content
        final Intent viewIntent = new Intent(this, MainActivity.class);
        final PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(viewPendingIntent);

        // Get an instance of the NotificationManager service
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Builds the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void createNotificationWithAction(final String title, final String content) {
        // An id for the notification to be showed, it should be unique.
        final int notificationId = 002;

        // Build an intent for an action to view a map
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        final Uri geoUri = Uri.parse("geo:0,0?q=");
        mapIntent.setData(geoUri);

        final PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 0, mapIntent, 0);
        final PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(viewPendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_launcher, "View map", mapPendingIntent);

        // Get an instance of the NotificationManager service
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Builds the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void createNotificationWithWearAction(final String title, final String content) {
        // An id for the notification to be showed, it should be unique.
        final int notificationId = 003;

        // Build an intent for an action to view a map
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        final Uri geoUri = Uri.parse("geo:0,0?q=");
        mapIntent.setData(geoUri);

        final PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 0, mapIntent, 0);
        final PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        // Create a wear only action to extend the notification
        final NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "View map", mapPendingIntent).build();

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(viewPendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_launcher, "Open map", mapPendingIntent)
                .extend(new NotificationCompat.WearableExtender().addAction(action));

        // Get an instance of the NotificationManager service
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Builds the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private void createNotificationWithVoiceAction(final String title, final String content) {
        // An id for the notification to be showed, it should be unique.
        final int notificationId = 004;

        // Build a remote input to enable voice capture
        final RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel("Say something!")
                .build();

        // Create an intent for the reply action
        final Intent replyIntent = new Intent(this, VoiceCommandActivity.class);
        final PendingIntent replyPendingIntent = PendingIntent.getActivity(this, 0, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input
        final NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Say something!", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        // Build the notification and add the action via WearableExtender
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .extend(new NotificationCompat.WearableExtender().addAction(action));

        // Get an instance of the NotificationManager service
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Builds the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }


    private final String EXTRA_VOICE_REPLY = "extra_voice_reply";
}
