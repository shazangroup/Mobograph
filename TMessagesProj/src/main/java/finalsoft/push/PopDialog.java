package finalsoft.push;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.finalsoft.messenger.R;
import com.squareup.picasso.Picasso;



public class PopDialog extends Activity {
    String ButtonLink;
    String ButtonText;
    String ImageLink;


    TextView MainText;
    String MatnText;
    Button Open;
    TextView Title;
    String TxtTitle;
    String bazzar = "com.farsitel.bazaar";
    ImageView img;
    String market;
    final String telegram = "org.telegram.plusplus";

    public void Close(View paramView) {
        try {
            finish();

            return;
        } catch (Exception localException) {
        }
    }

    public void Open(View paramView) {
        Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(this.ButtonLink));
        try {


            if (this.market.equals("telegram")) {
                localIntent1.setPackage(telegram);

                startActivity(localIntent1);
                finish();
                return;

            }
        } catch (
                Exception localException)

        {

            startActivity(localIntent1);
            finish();
            return;

        }

    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getWindow().requestFeature(1);
        setContentView(R.layout.activity_dialog);
        setFinishOnTouchOutside(false);
        this.Title = ((TextView) findViewById(R.id.title_dialog));
        this.MainText = ((TextView) findViewById(R.id.matn_dialog));
        this.img = ((ImageView) findViewById(R.id.img_dialog));
        this.Open = ((Button) findViewById(R.id.btn_dialog));
        this.TxtTitle = getIntent().getStringExtra("Title");
        this.MatnText = getIntent().getStringExtra("Matn");
        this.ImageLink = getIntent().getStringExtra("ImageLink");
        this.ButtonLink = getIntent().getStringExtra("ButtonLink");
        this.ButtonText = getIntent().getStringExtra("ButtonText");
        Picasso.with(this).load(this.ImageLink).into(this.img);
        this.Title.setText(this.TxtTitle);
        this.MainText.setText(this.MatnText);
        this.MainText.setLineSpacing(20.0F, 1.0F);
        this.MainText.setMovementMethod(new ScrollingMovementMethod());
        this.Open.setText(this.ButtonText);
        this.market = getIntent().getStringExtra("market");
    }
}