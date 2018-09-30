package in.vivekchoudhary.com.deliberr_app.presenter;

import android.content.Context;

import com.google.gson.JsonObject;

import in.vivekchoudhary.com.deliberr_app.model.DataRepository;
import in.vivekchoudhary.com.deliberr_app.model.DataSource;
import in.vivekchoudhary.com.deliberr_app.presenter.contracts.MainActivityContract;
import in.vivekchoudhary.com.deliberr_app.util.MainUiThread;
import in.vivekchoudhary.com.deliberr_app.util.ThreadExecutor;
import in.vivekchoudhary.com.deliberr_app.util.mvp.BasePresenter;


/**
 * Created by cvivek on 10-09-2018.
 */

public class MainActivityPresenter extends BasePresenter<MainActivityContract.View> implements MainActivityContract.Presenter {
    public static final String TAG = MainActivityPresenter.class.getSimpleName();
    private DataRepository dataRepository;
    private ThreadExecutor threadExecutor;
    private MainUiThread mainUiThread;

    public MainActivityPresenter(MainActivityContract.View view, DataRepository dataRepository, ThreadExecutor threadExecutor, MainUiThread mainUiThread) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.threadExecutor = threadExecutor;
        this.mainUiThread = mainUiThread;
    }

    @Override
    public void getData(Context context) {
        if (view == null) {
            return;
        }

        dataRepository.getData(context, new DataSource.GetDataCallback() {
            @Override
            public void onSuccess(JsonObject jsonData) {
                if (view != null) {
                    view.showData(jsonData);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable);
            }

            @Override
            public void onNetworkFailure() {

            }
        });
    }
}
