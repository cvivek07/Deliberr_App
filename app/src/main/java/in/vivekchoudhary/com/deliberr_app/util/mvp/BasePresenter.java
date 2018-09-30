package in.vivekchoudhary.com.deliberr_app.util.mvp;

/**
 * Created by cvivek on 10-09-2018.
 */

public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {
    protected ViewT view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        view = null;
    }
}
