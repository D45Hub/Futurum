package com.futurumgame.base.ui.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.resources.Resource;

public  final class ResourceViewHolder extends TextViewViewHolder<Resource> {

    private TextView view;
    private ImageView iconView;

    public ResourceViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.ItemText);
        view.setTextColor(Colors.White.getColor());

        iconView = itemView.findViewById(R.id.itemIcon);
    }

    public void updateText(Resource resource) {
        view.setText(ResourceFormatter.Default.format(resource));
        view.setTextColor(Colors.White.getColor());

        iconView.setImageResource(resource.getResourceIconId());
    }
}
