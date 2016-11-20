package labs.mobile.victor.lab_1.Lab_4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import labs.mobile.victor.lab_1.R;

/**
 * Created by Victor on 20.11.2016.
 */
public class LazyAdapter extends ArrayAdapter<String>{

    List<String> list;
    Context context;

    public LazyAdapter(Context context, List<String> list){
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("getView", position  + "");
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.photo = (ImageView) view.findViewById(R.id.photo);
            holder.progress = (ProgressBar) view.findViewById(R.id.progress);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String url = getItem(position);
        if (url != null) {
            new ImageLoader(holder.photo, holder.progress, url).execute();
        }
        return view;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class ViewHolder {
        private ImageView photo;
        private ProgressBar progress;
    }


}
