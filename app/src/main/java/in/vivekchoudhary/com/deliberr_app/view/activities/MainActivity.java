package in.vivekchoudhary.com.deliberr_app.view.activities;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.DataRepository;
import in.vivekchoudhary.com.deliberr_app.model.pojo.launches.JsonRoot;
import in.vivekchoudhary.com.deliberr_app.model.pojo.launches.Launch;
import in.vivekchoudhary.com.deliberr_app.presenter.MainActivityPresenter;
import in.vivekchoudhary.com.deliberr_app.presenter.contracts.MainActivityContract;
import in.vivekchoudhary.com.deliberr_app.util.RetrofitBuilder;
import in.vivekchoudhary.com.deliberr_app.util.MainUiThread;
import in.vivekchoudhary.com.deliberr_app.util.ThreadExecutor;
import in.vivekchoudhary.com.deliberr_app.util.mvp.BaseView;
import in.vivekchoudhary.com.deliberr_app.view.fragments.LaunchFragment;
import in.vivekchoudhary.com.deliberr_app.view.fragments.ProfileFragment;

public class MainActivity extends BaseView implements
        MainActivityContract.View, View.OnClickListener {
    public static final String JSON_DATA = "JSON_DATA";
    private MainActivityContract.Presenter presenter;

    private ActionBar actionBar;
    private Bundle bundle;
    private Fragment currFragment;
    private JsonRoot jsonRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Launches");
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        MainUiThread mainUiThread = MainUiThread.getInstance();
        DataRepository dataRepository = RetrofitBuilder.provideDataRepository(mainUiThread,
                threadExecutor);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        presenter = new MainActivityPresenter(this, dataRepository, threadExecutor, mainUiThread);
        presenter.getData(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_launches:
                    actionBar.setTitle("Launches");
                    currFragment = new LaunchFragment();
                    currFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, currFragment, "Launch Fragment")
                    .commit();
                    return true;
                case R.id.navigation_profile:
                    actionBar.setTitle("Profile");
                    currFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, currFragment, "Profile Fragment")
                            .commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showData(JsonArray jsonObject) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Launch>>(){}.getType();
        List<Launch> launchList  = gson.fromJson(jsonObject.toString(), listType);
        bundle = new Bundle();
        if(jsonObject!=null) bundle.putParcelableArrayList(JSON_DATA, (ArrayList<? extends Parcelable>) launchList);
        currFragment = new LaunchFragment();
        currFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainlayout, currFragment, "Launch Fragment");
        transaction.commit();
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
