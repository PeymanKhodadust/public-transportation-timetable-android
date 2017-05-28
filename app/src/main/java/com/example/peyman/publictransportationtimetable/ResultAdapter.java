package com.example.peyman.publictransportationtimetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by peyman on 26.05.17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private JSONArray mResultList;
    ListItemClickListener mOnClickListener;

    public ResultAdapter(JSONArray resultList, ListItemClickListener listener) {
        mResultList = resultList;
        Log.v(this.getClass().toString(), "Date size: " + resultList.length());
        mOnClickListener = listener;

    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.result_list_item;
        boolean shouldAttachToParentImediately = false;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImediately);

        ResultViewHolder viewHolder = new ResultViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        JSONObject jsonObject = null;
        try {
            jsonObject = mResultList.getJSONObject(position);
        }catch (JSONException je) {
            je.printStackTrace();
        }
        holder.bind(jsonObject);
    }

    @Override
    public int getItemCount() {
        Log.v(this.getClass().toString(), "Date size: " + mResultList.length());
        return mResultList.length();
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }



    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView depTime, transfers, arrTime;

        public ResultViewHolder(View itemView) {
            super(itemView);
            depTime = (TextView) itemView.findViewById(R.id.dep_time);
            arrTime = (TextView) itemView.findViewById(R.id.arr_time);
            transfers = (TextView) itemView.findViewById(R.id.transfers);
            itemView.setOnClickListener(this);
        }

        void bind(JSONObject jsonObject) {
            String dTime, aTime, trans;
            dTime = "";
            aTime = "";
            trans = "";

            try {
                dTime = jsonObject.getJSONObject("from").get("departure").toString();
                aTime = jsonObject.getJSONObject("to").getString("arrival");
                trans = Integer.toString(jsonObject.getInt("transfers"));
            }catch (JSONException je){
                je.printStackTrace();
            }
            depTime.setText(dTime);
            arrTime.setText(aTime);
            transfers.setText("transfers: " + trans);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition)  ;
        }
    }
}
