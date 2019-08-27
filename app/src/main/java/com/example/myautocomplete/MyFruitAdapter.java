package com.example.myautocomplete;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import android.widget.Filter;

public class MyFruitAdapter extends ArrayAdapter<Fruit> {
    private Context context;
    private int resourceId;
    private List<Fruit> items, tempItems, suggestions;
    public MyFruitAdapter(@NonNull Context context, int resourceId, List<Fruit> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            Fruit fruit = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.tvtitle);
            //ImageView imageView = view.findViewById(R.id.imageView);
            //imageView.setImageResource(fruit.getImage());
            name.setText(fruit.getFruitname());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    @Nullable
    @Override
    public Fruit getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }
    private Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Fruit fruit = (Fruit) resultValue;
            return fruit.getFruitname();
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Fruit fruit: tempItems) {
                    if (fruit.getFruitname().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Fruit> tempValues = (ArrayList<Fruit>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Fruit fruitObj : tempValues) {
                    add(fruitObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}