package in.vivekchoudhary.com.deliberr_app.util.mvp;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by cvivek on 10-09-2018.
 */

public abstract class BaseView extends AppCompatActivity implements IBaseView {

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
