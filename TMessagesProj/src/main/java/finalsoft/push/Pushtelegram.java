package finalsoft.push;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Pushtelegram extends Activity {
    String PushLink;
    String market;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        this.PushLink = getIntent().getStringExtra("PushLink");
        this.market = getIntent().getStringExtra("market");
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.PushLink));
        try {
            if (this.market.equals("telegram")) {
                localIntent.setPackage("org.telegram.plusplus");


            } else if (this.market.equals("mobo")) {
                localIntent.setPackage("com.hanista.mobogram");


            } else if (this.market.equals("telefarsi")) {
                localIntent.setPackage("ir.persianfox.plusplus");
            }
            startActivity(localIntent);
            finish();
            return;
        } catch (Exception localException) {

            startActivity(localIntent);
            finish();
            return;
        }
    }
}

