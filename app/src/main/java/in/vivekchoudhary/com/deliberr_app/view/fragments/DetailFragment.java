package in.vivekchoudhary.com.deliberr_app.view.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.regex.Pattern;

import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.DataRepository;
import in.vivekchoudhary.com.deliberr_app.model.pojo.launches.Launch;
import in.vivekchoudhary.com.deliberr_app.model.pojo.one_rocket.OneRocket;
import in.vivekchoudhary.com.deliberr_app.presenter.DetailFragmentPresenter;
import in.vivekchoudhary.com.deliberr_app.presenter.contracts.DetailFragmentContract;
import in.vivekchoudhary.com.deliberr_app.util.RetrofitBuilder;
import in.vivekchoudhary.com.deliberr_app.util.MainUiThread;
import in.vivekchoudhary.com.deliberr_app.util.ThreadExecutor;
import in.vivekchoudhary.com.deliberr_app.util.Util;
import in.vivekchoudhary.com.deliberr_app.view.activities.MainActivity;


public class DetailFragment extends Fragment implements View.OnClickListener, DetailFragmentContract.View {

    private Launch launch;
    private TextView mFlightno, mMissionname, mLaunchDate, mWikipedia, mYouttube, mCustInfo, mRocketName, mDescription,
            mWikipediaRocket, mEngineType, mEngineVersion, mLandingLegsNo, mLandingLegsMaterial;
    private static final String SAVE_STATE = "saved data";

private  DetailFragmentContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        if(savedInstanceState ==null){
            launch = getArguments().getParcelable(LaunchFragment.DETAIL_DATA);
        } else {
            launch = savedInstanceState.getParcelable(SAVE_STATE);
        }

        mFlightno = view.findViewById(R.id.flightnumber);
        mMissionname = view.findViewById(R.id.missionname);
        mLaunchDate = view.findViewById(R.id.launchdate);
        mWikipedia = view.findViewById(R.id.wikipedia);
        mWikipedia.setOnClickListener(this);
        mYouttube = view.findViewById(R.id.youtube);
        mYouttube.setOnClickListener(this);
        mCustInfo = view.findViewById(R.id.custinfo);
        mRocketName = view.findViewById(R.id.rocketname);
        mDescription = view.findViewById(R.id.description);
        mWikipediaRocket = view.findViewById(R.id.wikipediarocket);
        mEngineType = view.findViewById(R.id.enginetype);
        mEngineVersion = view.findViewById(R.id.enginever);
        mLandingLegsNo = view.findViewById(R.id.legsnumber);
        mLandingLegsMaterial = view.findViewById(R.id.legsmat);
        if(launch !=null) showData();
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        MainUiThread mainUiThread = MainUiThread.getInstance();
        DataRepository dataRepository = RetrofitBuilder.provideDataRepository(mainUiThread,
                threadExecutor);
        presenter = new DetailFragmentPresenter(this, dataRepository, threadExecutor, mainUiThread);
        presenter.getOneRocket(getContext(), launch.getRocket().getRocketId());
        return view;
    }

    private void showData() {
        mFlightno.setText(String.valueOf(launch.getFlightNumber()));
        mMissionname.setText(String.valueOf(launch.getMissionName()));
        mLaunchDate.setText(String.valueOf(Util.getUTCTime(launch.getLaunchDateUtc())));
        mWikipedia.setText(launch.getLinks().getWikipedia());
        Linkify.addLinks(mWikipedia, Linkify.WEB_URLS);
        mYouttube.setText(launch.getLinks().getVideoLink());
        Linkify.addLinks(mYouttube, Linkify.ALL);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < launch.getRocket().getFirstStage().getCores().size(); i++) {
            String s1 = String.valueOf(launch.getRocket().getSecondStage().getPayloads().get(i).getCustomers());
            String string = String.valueOf(s1.replaceAll(Pattern.quote("["),"").replaceAll(Pattern.quote("]"), ""));
            s.append(string);
            mCustInfo.setText(s);
        }

        mRocketName.setText(String.valueOf(launch.getRocket().getRocketName()));
        mDescription.setText(launch.getDetails());
        mWikipediaRocket.setText(launch.getLinks().getWikipedia());
        Linkify.addLinks(mWikipediaRocket, Linkify.WEB_URLS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wikipedia:
                launchBrowser(String.valueOf(mWikipedia.getText()));
                break;
            case R.id.youtube:
                String str[] = mYouttube.getText().toString().split(Pattern.quote("?"));
                launchYoutube(str[1]);
                break;
        }
    }

    private void launchYoutube(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    private void launchBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No Browser Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVE_STATE, launch);
    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void showOneRocket(JsonObject jsonObject) {
        Gson gson =new Gson();
        OneRocket rocket = gson.fromJson(jsonObject.toString(), OneRocket.class);
        mEngineType.setText(String.valueOf(rocket.getEngines().getType()));
        mEngineVersion.setText(String.valueOf(rocket.getEngines().getVersion()));
        if(rocket.getLandingLegs().getMaterial()!=null) {
            mLandingLegsMaterial.setText(String.valueOf(rocket.getLandingLegs().getMaterial()));
        } else {
            mLandingLegsMaterial.setText(getContext().getString(R.string.unavailable));
        }
        if(rocket.getLandingLegs().getNumber()!=null) {
            mLandingLegsNo.setText(String.valueOf(rocket.getLandingLegs().getNumber()));
        } else {
            mLandingLegsNo.setText(getContext().getString(R.string.unavailable));
        }
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
