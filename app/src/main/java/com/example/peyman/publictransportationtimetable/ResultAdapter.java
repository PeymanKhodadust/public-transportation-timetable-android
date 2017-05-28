package com.example.peyman.publictransportationtimetable;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by peyman on 26.05.17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    //private final ListItemClickListener mOnClickListener;
    private List<String> mList;

    public ResultAdapter(List<String> list/*, ListItemClickListener listener*/) {
        mList = list;
        Log.v(this.getClass().toString(), "Date size: " + mList.size());
        //mOnClickListener = listener;

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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        Log.v(this.getClass().toString(), "Date size: " + mList.size());
        return mList.size();
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }



    class ResultViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

        TextView listItemResultView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            listItemResultView = (TextView) itemView.findViewById(R.id.result_item_one);
            //itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemResultView.setText(String.valueOf(mList.get(listIndex)));
        }

        /*
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition)  ;
        }
        */
    }
}
