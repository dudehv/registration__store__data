package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;


public class HomePage extends RecyclerView.Adapter<HomePage.MyViewHolder> {

    Context context;
    ArrayList<Users> list;
    ArrayList<Users> filterData;

    public HomePage(Context context, ArrayList<Users> list) {
        this.context=context;
        this.list=list;
        this.filterData=new ArrayList<Users>();
        filterData.addAll(list);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.home_page,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvData.setText(""+list.get(position).getName());
        holder.emaildata.setText(""+list.get(position).getEmail());
        holder.passdata.setText(""+list.get(position).getPass());

    }

    @Override
    public int getItemCount() {
        return filterData.size();
    }

    public void filterData(CharSequence charText) {
        filterData.clear();
        if(charText.length()==0){
           filterData.addAll(list);
        }else {
            for (Users us : list) {
                if(us.getName().contains(charText)){
                    filterData.add(us);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvData;
    TextView emaildata;
    TextView passdata;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvData=itemView.findViewById(R.id.tvdata);
            emaildata=itemView.findViewById(R.id.emaildata);
            passdata=itemView.findViewById(R.id.passdata);


        }
    }
}
