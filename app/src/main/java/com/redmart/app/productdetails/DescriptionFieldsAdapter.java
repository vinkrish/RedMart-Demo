package com.redmart.app.productdetails;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redmart.app.R;
import com.redmart.app.model.DescriptionField;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionFieldsAdapter extends RecyclerView.Adapter<DescriptionFieldsAdapter.ViewHolder> {
    private List<DescriptionField> items;

    DescriptionFieldsAdapter() {
        this.items = new ArrayList<>();
    }

    @UiThread
    public void setItems(List<DescriptionField> descriptionFields) {
        this.items = descriptionFields;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_desc_fields_item, parent, false);
        return new DescriptionFieldsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.desc_name) TextView descNameTV;
        @BindView(R.id.desc_content) TextView descContentTV;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(DescriptionField descriptionField) {
            descNameTV.setText(descriptionField.getName());
            descContentTV.setText(descriptionField.getContent());
        }
    }
}
