package com.example.librarymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.NumberViewHolder> {

    private static final String ID = SearchResultAdapter.class.getSimpleName();
    final private ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private final int mNumberItems;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
    public SearchResultAdapter(int numberOfItems, ListItemClickListener listener){
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIDforListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIDforListItem,parent,shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
//        int backgroundColorForViewHolder = ColorShader.getViewHolderBGColFromInstance(context,viewHolderCount);
//        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        viewHolderCount++;
        Log.d(ID,"onCreateViewHolder: number of viewHolders created: "+ viewHolderCount);
        return viewHolder;
    }

    @Override
    public int getItemCount(){
        return mNumberItems;
    }
    @Override
    public void onBindViewHolder(NumberViewHolder holder,int pos){
        Log.d(ID,"#"+pos);
        holder.bind(pos);
    }
    class NumberViewHolder extends RecyclerView.ViewHolder implements OnClickListener{
        TextView listItemNumberView;
        TextView viewHolderIndex;
        public NumberViewHolder(View itemView){
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.view_holder_instance);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }


}