package in.vivekchoudhary.com.deliberr_app.view.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.pojo.JsonRoot;
import in.vivekchoudhary.com.deliberr_app.util.Util;

/**
 * Created by cvivek on 24-09-2018.
 */

public class DetailFragment extends Fragment implements View.OnClickListener {

    private JsonRoot jsonRoot;
    private TextView mFlightno, mMissionname, mLaunchDate, mWikipedia, mYouttube, mCustInfo, mRocketName, mDescription,
            mWikipediaRocket, mEngineType, mEngineVersion, mLandingLegsNo, mLandingLegsMaterial;
    private static final String SAVE_STATE = "saved data";


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
            jsonRoot = getArguments().getParcelable(LaunchFragment.DETAIL_DATA);
        } else {
            jsonRoot = savedInstanceState.getParcelable(SAVE_STATE);
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
        if(jsonRoot !=null) showData();
        return view;
    }

    private void showData() {
        mFlightno.setText(String.valueOf(jsonRoot.getFlightNumber()));
        mMissionname.setText(String.valueOf(jsonRoot.getMissionName()));
        mLaunchDate.setText(String.valueOf(Util.getUTCTime(jsonRoot.getLaunchDateUtc())));
        mWikipedia.setText(jsonRoot.getLinks().getWikipedia());
        Linkify.addLinks(mWikipedia, Linkify.WEB_URLS);
        mYouttube.setText(jsonRoot.getLinks().getVideoLink());
        Linkify.addLinks(mYouttube, Linkify.ALL);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < jsonRoot.getRocket().getFirstStage().getCores().size(); i++) {
            String s1 = String.valueOf(jsonRoot.getRocket().getSecondStage().getPayloads().get(i).getCustomers());
            String string = String.valueOf(s1.replaceAll(Pattern.quote("["),"").replaceAll(Pattern.quote("]"), ""));
            s.append(string);
            mCustInfo.setText(s);
        }

        mRocketName.setText(String.valueOf(jsonRoot.getRocket().getRocketName()));
        mDescription.setText(jsonRoot.getDetails());
        mWikipediaRocket.setText(jsonRoot.getLinks().getWikipedia());
        Linkify.addLinks(mWikipediaRocket, Linkify.WEB_URLS);

        StringBuilder strbldr = new StringBuilder();
        for (int i = 0; i < jsonRoot.getRocket().getFirstStage().getCores().size(); i++) {
            String string = jsonRoot.getRocket().getFirstStage().getCores().get(i).getCoreSerial();
            strbldr.append(string);
            mEngineType.setText(strbldr);
        }
        StringBuilder strbldr1 = new StringBuilder();
        for (int i = 0; i < jsonRoot.getRocket().getFirstStage().getCores().size(); i++) {
            String string = String.valueOf(jsonRoot.getRocket().getFirstStage().getCores().get(i).getFlight());
            strbldr1.append(string);
            mEngineVersion.setText(strbldr1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jsonRoot.getRocket().getFirstStage().getCores().size(); i++) {
            String string = jsonRoot.getRocket().getFirstStage().getCores().get(i).getLandingType();
            stringBuilder.append(string);
            mLandingLegsNo.setText(stringBuilder);
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < jsonRoot.getRocket().getFirstStage().getCores().size(); i++) {
            String string = jsonRoot.getRocket().getFirstStage().getCores().get(i).getLandingVehicle();
            stringBuilder1.append(string);
            mLandingLegsMaterial.setText(stringBuilder1);
        }

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
        outState.putParcelable(SAVE_STATE, jsonRoot);
    }

}
