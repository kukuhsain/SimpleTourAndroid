package com.kukuhsain.simpletour.guest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kukuhsain.simpletour.guest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 22/10/16.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {
    private List<String> packages;

    public PackageAdapter(List<String> packages) {
        this.packages = packages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_package) TextView tvPackage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String onePackage) {
            tvPackage.setText(onePackage);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(packages.get(position));
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }
}
