package com.checkcode.fsrl.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkcode.fsrl.R;
import com.checkcode.fsrl.adapter.ViewHolderAdapter;

import java.util.Random;

public class RandomNumListAdapter extends RecyclerView.Adapter<ViewHolderAdapter> {
    private Random random;

    public RandomNumListAdapter(int seed) {
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_textview;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {
        holder.getView().setText(String.valueOf(random.nextInt()));
    }



    @Override
    public int getItemCount() {
        return 100;
    }
}
