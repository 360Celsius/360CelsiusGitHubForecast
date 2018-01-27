package forcast.celsius.com.forcast.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import forcast.celsius.com.datamodel.SideMenuItemDataModel;
import forcast.celsius.com.forcast.R;

/**
 * Created by dennisshar on 20/10/2017.
 */

public class SideMenuListCustomAdapter extends ArrayAdapter<SideMenuItemDataModel> implements View.OnClickListener{

    private ArrayList<SideMenuItemDataModel> dataSet;
    Context mContext;
    private int lastPosition = -1;


    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        ImageView info;
        RelativeLayout rowWrapper;
    }

    public SideMenuListCustomAdapter(ArrayList<SideMenuItemDataModel> data, Context context) {
        super(context, R.layout.side_menu_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SideMenuItemDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        //final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.side_menu_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.row_item_name);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.row_item_image);
            viewHolder.rowWrapper = (RelativeLayout)  convertView.findViewById(R.id.row_wrapper);
            //result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //result=convertView;
        }


//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getCategoryName());
        viewHolder.info.setImageResource (dataModel.getCategoryImage());
        viewHolder.rowWrapper.setBackgroundColor(dataModel.getColor());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;    }
}
