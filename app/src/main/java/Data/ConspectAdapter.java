package Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.reclect.R;
import java.util.ArrayList;
import Model.Conspect;

public class ConspectAdapter extends ArrayAdapter<Conspect> {


    public ConspectAdapter(Context context, int resource, ArrayList<Conspect> ar) {
        super(context, resource, ar);
    }

    @Nullable
    @Override
    public Conspect getItem(int position) {
        return super.getItem(position);
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Conspect cP = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conspect_item, parent, false);
        }

        TextView cName = convertView.findViewById(R.id.conspect_item_name);
        TextView cTag = convertView.findViewById(R.id.conspect_item_tag);

        cName.setText(cP.getConspectName());
        cTag.setText("T");

        return convertView;
    }
}
