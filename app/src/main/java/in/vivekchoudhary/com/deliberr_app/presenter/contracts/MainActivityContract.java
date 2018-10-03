package in.vivekchoudhary.com.deliberr_app.presenter.contracts;

import android.content.Context;

import com.google.gson.JsonArray;

import in.vivekchoudhary.com.deliberr_app.util.mvp.IBasePresenter;
import in.vivekchoudhary.com.deliberr_app.util.mvp.IBaseView;


/**
 * Created by cvivek on 10-09-2018.
 */

public interface MainActivityContract {

    interface View extends IBaseView {

        void showData(JsonArray jsonObject);

        void showError(Throwable throwable);
    }

    interface Presenter extends IBasePresenter<View> {
        void getData(Context context);
    }
}
