package com.kukuhsain.simpletour.guest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.pojo.Package;
import com.kukuhsain.simpletour.guest.view.PackagesActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 22/10/16.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {
    private Context context;
    private List<Package> packages;

    public PackageAdapter(Context context, List<Package> packages) {
        this.context = context;
        this.packages = packages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_package) TextView tvPackage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Context context, Package onePackage) {
            tvPackage.setText(onePackage.getTitle());
            itemView.setOnClickListener(view -> {
                ((PackagesActivity) context).onItemClicked(onePackage);
            });
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
        holder.bind(context, packages.get(position));
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }
}
