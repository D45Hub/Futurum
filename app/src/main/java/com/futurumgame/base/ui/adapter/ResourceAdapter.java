package com.futurumgame.base.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.futurumgame.base.R;
import com.futurumgame.base.ui.viewholder.ResourceViewHolder;
import com.futurumgame.base.resources.Resource;

import java.util.HashMap;
import java.util.Hashtable;

public final class ResourceAdapter extends TextViewAdapter<Resource, ResourceViewHolder> {

    private final HashMap<Integer, ResourceViewHolder> resourceViewIdMapping = new HashMap<>();
    private final Hashtable<Integer, Resource> resources;

    public ResourceAdapter(Hashtable<Integer, Resource> resources) {
        this.resources = resources;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResourceViewHolder(inflateListItemView(parent, R.layout.list_item_view));
    }

    @Override
    protected Resource getHolderObjectByPosition(int position) {
        return resources.get(position);
    }

    @Override
    protected void onBindViewHolder(ResourceViewHolder holder, Resource holderObject) {
        resourceViewIdMapping.put(holderObject.getID(), holder);
    }

    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResourceViewHolder(inflateListItemView(parent, R.layout.list_item_view));
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    public void updateResourceViewHolder(Resource resource) {
        updateViewHolder(resourceViewIdMapping.get(resource.getID()), resource);
    }
}
