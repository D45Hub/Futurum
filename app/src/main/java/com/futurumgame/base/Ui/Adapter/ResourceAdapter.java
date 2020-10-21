package com.futurumgame.base.Ui.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.Ui.ViewHolder.ResourceViewHolder;
import com.futurumgame.base.resources.Resource;

import java.util.HashMap;
import java.util.Hashtable;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceViewHolder> {

    private final HashMap<Integer, ResourceViewHolder> resourceViewIdMapping = new HashMap<>();
    private final Hashtable<Integer, Resource> resources;

    public ResourceAdapter(Hashtable<Integer, Resource> resources) {
        this.resources = resources;
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ResourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        Resource resource = resources.get(position);
        updateResourceViewHolder(holder, resource);
        resourceViewIdMapping.put(resource.getID(), holder);
    }

    public void updateResourceViewHolder(Resource resource) {
       updateResourceViewHolder(resourceViewIdMapping.get(resource.getID()), resource);
    }
    private void updateResourceViewHolder(ResourceViewHolder holder, Resource resource){
        if(holder!= null) {
            holder.upDateText(resource);
        }
    }
}
