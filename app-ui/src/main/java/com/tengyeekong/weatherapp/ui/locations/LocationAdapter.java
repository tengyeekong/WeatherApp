package com.tengyeekong.weatherapp.ui.locations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tengyeekong.weatherapp.ui.databinding.ItemLocationBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private final Interaction interaction;

    LocationAdapter(Interaction interaction) {
        this.interaction = interaction;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemLocationBinding.inflate(layoutInflater, parent, false), interaction);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLocationBinding binding;
        Interaction         interaction;

        public ViewHolder(@NonNull final ItemLocationBinding binding, Interaction interaction) {
            super(binding.getRoot());
            this.binding = binding;
            this.interaction = interaction;
        }

        void bind(String location) {
            binding.name.setText(location.substring(location.lastIndexOf(",") + 1));
            binding.coordinates.setText(location.substring(0, location.lastIndexOf(",")));
            binding.getRoot().setOnClickListener(v -> interaction.onItemClicked(location));
            binding.remove.setOnClickListener(v -> interaction.onItemRemoved(location));
            binding.executePendingBindings();
        }
    }

    DiffUtil.ItemCallback<String> differCallback = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull final String oldItem, @NonNull final String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull final String oldItem, @NonNull final String newItem) {
            return oldItem.equals(newItem);
        }
    };

    AsyncListDiffer<String> differ = new AsyncListDiffer<>(this, differCallback);

    interface Interaction {
        void onItemClicked(String location);

        void onItemRemoved(String location);
    }
}
