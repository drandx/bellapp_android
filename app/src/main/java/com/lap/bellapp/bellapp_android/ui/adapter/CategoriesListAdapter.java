package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;

import java.util.List;

/**
 * Created by juangarcia on 1/3/16.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder>  {

    private final List<BusinessCategory> mCategoryItems;
    private final Context mContext;
    private final View.OnClickListener onClickListener;


    public CategoriesListAdapter(List<BusinessCategory> mCategoryItems, Context mContext, View.OnClickListener onClickListener) {
        this.mCategoryItems = mCategoryItems;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup catView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        catView.setOnClickListener(onClickListener);
        CategoryViewHolder categoryView = new CategoryViewHolder(catView);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        BusinessCategory content = mCategoryItems.get(position);
        holder.textCategoryTitle.setText(content.title);
    }

    @Override
    public int getItemCount() {
        return mCategoryItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public List<BusinessCategory> getItems(){
        return mCategoryItems;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView textCategoryTitle;
        ImageView imageCategoryImage;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageCategoryImage = (ImageView) itemView.findViewById(R.id.imageCategoryIcon);
            textCategoryTitle = (TextView) itemView.findViewById(R.id.textCategoryTitle);

        }
    }
}
