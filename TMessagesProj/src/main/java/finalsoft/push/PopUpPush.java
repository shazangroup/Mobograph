package finalsoft.push;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class PopUpPush extends Activity {
    String PushLink;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
//        setContentView(R.layout.activity_ppouppush);
        this.PushLink = getIntent().getStringExtra("PushLink");


        try {

            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.PushLink)));


            finish();
            return;
        } catch (Exception localException) {
            Log.i("LOG", "log");
            finish();
        }
    }
}