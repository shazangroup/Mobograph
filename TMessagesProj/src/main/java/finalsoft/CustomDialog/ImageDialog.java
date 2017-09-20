package finalsoft.CustomDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalsoft.messenger.R;

import org.telegram.messenger.AndroidUtilities;


public class ImageDialog extends AlertDialog.Builder {

    TextView title, message;
    ImageView image;
    View mlLayout;

    public ImageDialog(Context context) {
        super(context);

        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.special_dialog, null);

        title = (TextView) view.findViewById(R.id.dialogTitle);
        message = (TextView) view.findViewById(R.id.dialogText);
        image = (ImageView) view.findViewById(R.id.dialogImage);
        mlLayout = view.findViewById(R.id.mllayout);
        setView(view);

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitleText(String title) {
        this.title.setText(title);
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessageText(String  message) {
        this.message.setText(message);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImageHeight(int height){
        this.mlLayout.getLayoutParams().height = AndroidUtilities.dp(height);
        this.mlLayout.requestLayout();
    }
}
