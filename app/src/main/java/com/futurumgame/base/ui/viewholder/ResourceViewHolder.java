package com.futurumgame.base.ui.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.resources.Resource;

public class ResourceViewHolder extends RecyclerView.ViewHolder {

    private TextView view;

    public ResourceViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.ReourceText);
        view.setTextColor(Colors.White.getColor());
    }

    public int getViewID(){
        return view.getId();
    }

    public void upDateText(Resource resource){
        view.setText(ResourceFormatter.Default.format(resource));
        view.setTextColor(Color.WHITE);
    }
}
