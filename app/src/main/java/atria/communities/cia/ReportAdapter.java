package atria.communities.cia;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private ArrayList<ReportModel> dataSet;
    Context context;

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView timestamp;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.content = (TextView) itemView.findViewById(R.id.content);
            this.timestamp = (TextView) itemView.findViewById(R.id.timestamp);


            //  this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ReportAdapter(ArrayList<ReportModel> data,Context context) {
        this.context=context;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_report_rv, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView title = holder.title;
        TextView content = holder.content;
        TextView timestamp = holder.timestamp;


        //    ImageView imageView = holder.imageViewIcon;
        title.setText(dataSet.get(listPosition).getTitle());
        content.setText(dataSet.get(listPosition).getContent());
        timestamp.setText(dataSet.get(listPosition).getTimestamp());

      /*  String TAG = "MyActivity";
        Log.d(TAG,"ID: "+ dataSet.get(listPosition).getId());
        Log.d(TAG,"Problem: "+ dataSet.get(listPosition).getTitle());
*/



        //    imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}