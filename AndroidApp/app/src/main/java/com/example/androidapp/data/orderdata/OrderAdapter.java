package com.example.androidapp.data.orderdata;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;

import java.util.ArrayList;
import java.util.List;

//Adapter for RecyclerView
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private List<Order> mListOrder = new ArrayList<>();
    private OnItemClickListener listener;


    public void setOrder(List<Order> mListOrder) {
        this.mListOrder = mListOrder;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_recycler, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if (order == null) {
            return;
        }

        holder.tvOrderName.setText(order.getClientName());
        holder.tvOrderDate.setText(order.getDate());
        holder.tvOrderTime.setText(order.getTime());
        holder.tvOrderPrice.setText("1000");

        if (order.getPaid() == true){
            Log.d("paid", "true");
            holder.flagPaid.setVisibility(View.VISIBLE);
        } else {
            Log.d("paid", "false");
            holder.flagPaid.setVisibility(View.INVISIBLE);
        }

        if (order.getShip() == true){

            holder.flagShip.setVisibility(View.VISIBLE);
        } else {
            holder.flagShip.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    public Order getOrderAt(int pos){
        return mListOrder.get(pos);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderName;
        private TextView tvOrderDate;
        private TextView tvOrderTime;
        private TextView tvOrderPrice;
        private View flagPaid;
        private View flagShip;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderName = itemView.findViewById(R.id.order_name);
            tvOrderDate = itemView.findViewById(R.id.order_day);
            tvOrderTime = itemView.findViewById(R.id.order_time);
            tvOrderPrice = itemView.findViewById(R.id.order_price);
            flagPaid = itemView.findViewById(R.id.flag_paid);
            flagShip = itemView.findViewById(R.id.flag_ship);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(mListOrder.get(pos));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Order order);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
