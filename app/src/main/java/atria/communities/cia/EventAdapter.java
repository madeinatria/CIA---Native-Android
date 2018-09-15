package atria.communities.cia;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private ArrayList<EventModel> dataSet;
    Context context;

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventtitle;
        TextView eventshortdescription;
        TextView eventtimestamp;
        CardView click;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.click = itemView.findViewById(R.id.click);
            this.eventtitle = (TextView) itemView.findViewById(R.id.eventtitle);
            this.eventshortdescription = (TextView) itemView.findViewById(R.id.eventshortdescription);
            this.eventtimestamp = (TextView) itemView.findViewById(R.id.eventtimestamp);


            //  this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public EventAdapter(ArrayList<EventModel> data, Context context) {
        this.context=context;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_event, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {



        CardView click = holder.click;
        TextView eventtitle = holder.eventtitle;
        TextView eventshortdescription = holder.eventshortdescription;
        TextView eventtimetamp = holder.eventtimestamp;


        //    ImageView imageView = holder.imageViewIcon;
        eventtitle.setText(dataSet.get(listPosition).getEventtitle());
        eventshortdescription.setText(dataSet.get(listPosition).getEventshortdescription());
        Timestamp time = dataSet.get(listPosition).getEventtimestamp();


       /* Date date= new Date(time.getSeconds());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-20YY");
        String fomatedDate = sdf.format(date);*/

       Date date = time.toDate();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-20YY hh:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm  dd-MM");
        String fomatedDate = sdf.format(date);


        eventtimetamp.setText(" "+fomatedDate);

      //  Toast.makeText(context, ""+fomatedDate, Toast.LENGTH_SHORT).show();




//       eventtimetamp.setText(dataSet.get(listPosition).getEventtimestamp().toString());

      /*  String TAG = "MyActivity";
        Log.d(TAG,"ID: "+ dataSet.get(listPosition).getId());
        Log.d(TAG,"Problem: "+ dataSet.get(listPosition).getTitle());
*/


      click.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              // Toast.makeText(mContext, ""+albumList.get(position).getHotelAddress(), Toast.LENGTH_SHORT).show();
              Gson gson = new Gson();
              String myJson = gson.toJson(dataSet.get(listPosition
              ));
              Intent intent= new Intent(context,FullEventActivity.class);
              //  Toast.makeText(context, ""+data.get(position), Toast.LENGTH_SHORT).show();
              intent.putExtra("selectedEvent", myJson);
              context.startActivity(intent);
          }
      });



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}