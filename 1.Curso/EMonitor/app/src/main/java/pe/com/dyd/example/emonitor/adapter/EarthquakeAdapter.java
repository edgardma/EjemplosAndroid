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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(R.layout.eq_list_item, null);

        TextView magnitudeTextView = (TextView) rootView.findViewById(R.id.eq_list_item_magnitude);
        TextView placeTextView = (TextView) rootView.findViewById(R.id.eq_list_item_place);

        EarthquakeEntity earthquakeEntity = eqList.get(position);

        magnitudeTextView.setText(earthquakeEntity.getMagnitude());
        placeTextView.setText(earthquakeEntity.getPlace());

        return rootView;
    }
}
