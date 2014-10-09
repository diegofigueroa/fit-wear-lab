package codelab.fit.com.wear101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class VoiceCommandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_command);

        final CharSequence dictatedString = getMessageText();

        // update the activity content with the *dictatedString*
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.voice_command, menu);
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

    /**
     * Obtain the intent that started this activity by calling
     * Activity.getIntent() and get the associated voice input string.
     */

    private CharSequence getMessageText() {
        final Intent intent = getIntent();
        // implement me :)

        return "Uhh? Say what?";
    }

    private final String EXTRA_VOICE_REPLY = "extra_voice_reply";
}
