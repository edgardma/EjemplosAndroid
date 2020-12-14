package pe.com.dyd.example.emonitor.adapter;

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

import pe.com.dyd.example.emonitor.R;
import pe.com.dyd.example.emonitor.entity.EarthquakeEntity;

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeEntity> {
    private ArrayList<EarthquakeEntity> eqList;
    private Context context;
    private int layaoudId;

    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull List<EarthquakeEntity> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layaoudId = resource;
        eqList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layaoudId, null);

            holder = new ViewHolder();
            holder.magnitudeTextView = (TextView) convertView.findViewById(R.id.eq_list_item_magnitude);
            holder.placeTextView = (TextView) convertView.findViewById(R.id.eq_list_item_place);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EarthquakeEntity earthquakeEntity = eqList.get(position);

        holder.magnitudeTextView.setText(earthquakeEntity.getMagnitude());
        holder.placeTextView.setText(earthquakeEntity.getPlace());

        return convertView;
    }

    private class ViewHolder {
        public TextView magnitudeTextView;
        public TextView placeTextView;
    }
}
