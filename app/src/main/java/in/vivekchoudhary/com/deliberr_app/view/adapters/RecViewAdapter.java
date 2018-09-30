package in.vivekchoudhary.com.deliberr_app.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import in.vivekchoudhary.com.deliberr_app.R;
import in.vivekchoudhary.com.deliberr_app.model.pojo.JsonRoot;

/**
 * Created by cvivek on 17-09-2018.
 */

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private JsonRoot mJsonRoot;
    private Context mContext;
    private int pos;

    public RecViewAdapter(Context context, JsonRoot myDataset) {
        mContext = context;
        mJsonRoot = myDataset;
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
        if (mJsonRoot != null)
            holder.mLaunchName.setText("Launch One(Mission Name: " + mJsonRoot.getMissionName() + ")");
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
