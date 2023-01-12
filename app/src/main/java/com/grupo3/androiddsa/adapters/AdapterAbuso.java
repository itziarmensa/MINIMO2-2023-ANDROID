package com.grupo3.androiddsa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo3.androiddsa.R;
import com.grupo3.androiddsa.domain.Issue;
import com.grupo3.androiddsa.domain.MyObjects;

import java.util.List;

public class AdapterAbuso extends RecyclerView.Adapter<AdapterAbuso.ViewHolder> {

    private List<Issue> listIssues;

    final AdapterAbuso.OnItemClickListener listener;



    public interface OnItemClickListener{
        void onItemClick(Issue issue);
    }


    public AdapterAbuso(List<Issue> listIssues, AdapterAbuso.OnItemClickListener listener) {
        this.listIssues = listIssues;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView mensage;
        //Button btnComprarObjeto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mensage = itemView.findViewById(R.id.description);
        }

        void bindData(final Issue issue){
            name.setText(issue.getInformer());
            mensage.setText(issue.getMessage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(issue);
                }
            });
        }

    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }

    @Override //Hace la comunicación entre el adaptador y la clase ViewHolder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listIssues.get(position));
    }

    @Override
    public int getItemCount() {
        return listIssues.size();
    }


}
