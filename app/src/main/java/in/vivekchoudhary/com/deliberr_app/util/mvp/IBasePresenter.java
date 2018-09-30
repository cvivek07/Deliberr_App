package in.vivekchoudhary.com.deliberr_app.util.mvp;

/**
 * Created by cvivek on 10-09-2018.
 */

public interface IBasePresenter<ViewT> {
    void onViewActive(ViewT view);

    void onViewInactive();
}
