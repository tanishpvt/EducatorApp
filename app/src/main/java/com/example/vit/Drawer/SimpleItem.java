package com.example.vit.Drawer;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.vit.R;

import org.jetbrains.annotations.NotNull;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private final Drawable icon;
    private final String title;

    //creating a constructor
    public SimpleItem(Drawable icon, String title){
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option, parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
    }

    //check for error ... below icon code
    public SimpleItem withSelectedIconTint(int selectedIconTint){
        this.selectedItemIconTint = selectedIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedItemTextTint){
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public  SimpleItem withIconTint(int normalItemIconTint){
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public  SimpleItem withTextTint(int normalItemTextTint){
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder{

        private final ImageView icon;
        private final TextView title;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);

        }
    }

}
