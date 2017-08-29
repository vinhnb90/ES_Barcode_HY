package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import esolutions.com.barcodehungyenpc.R;

/**
 * Created by VinhNB on 8/28/2017.
 */

public class TypeSearchAdapter extends ArrayAdapter<String> implements Filterable, ListAdapter {
    private List<String> mContentSearch = new ArrayList<>();
    private Context mContext;

    public TypeSearchAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);

        mContentSearch.clear();
        mContentSearch.addAll(objects);
        mContext = context;
    }


    @Nullable
    @Override
    public String getItem(int position) {

        if (position < 0 || position >= mContentSearch.size()) {
            return null;
        }
        return mContentSearch.get(position);
    }

    @Override
    public int getCount() {
        return mContentSearch.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_auto_et_search_type2, parent,
                false);

//        RowViewHolder viewHolder = new RowViewHolder(this.getContext(), rowView);
//        Map<String, String> contactMap = mContactList.get(position);

//        nameView.setText(contactMap.get("name"));
//        phoneView.setText(contactMap.get("phone"));
//        typeView.setText(contactMap.get("type"));
        TextView tvRow = (TextView) rowView.findViewById(R.id.tv_search_type2);
        tvRow.setText(mContentSearch.get(position));
        tvRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Click item " + mContentSearch.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }

    class RowViewHolder extends View {
        public final TextView tvContent;


        public RowViewHolder(Context context, View itemView) {
            super(context);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_search_type2);
        }
    }

}
