package in.vivekchoudhary.com.deliberr_app.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.pojo.launches.Launch;

/**
 * Created by cvivek on 17-09-2018.
 */

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private List<Launch> launchList = new ArrayList<>();
    private Context mContext;


    public RecViewAdapter(Context context, List<Launch> myDataset) {
        mContext = context;
        launchList = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mLaunchName;

        public ViewHolder(View v) {
            super(v);
            mLaunchName = v.findViewById(R.id.launch_name);
        }
    }

    @Override
    public RecViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (launchList.size()>0 && launchList!=null)
            holder.mLaunchName.setText(launchList.get(position).getMissionName() + ", " + launchList.get(position).getLaunchYear());
    }

    @Override
    public int getItemCount() {
        return launchList.size();
    }
}
