package tala.mubarki.talafinalproject17.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tala.mubarki.talafinalproject17.R;

public class MySaleAdaptor extends ArrayAdapter<MySale> {
    /**
     * constractor
     *  @param context  the activity of (app) that this adapter belong to
     * @param resource XML  design of the item
     */
    //fix error

    public MySaleAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
    }
    @NonNull
    @Override
    public android.view.View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        //3.1
        android.view.View v= LayoutInflater.from(getContext()).inflate(R.layout.item_shop_view1,parent,false);
        //3.2 find view by ID
        TextView tvLastDate=v.findViewById(R.id.itm);
        TextView tvDiscount=v.findViewById(R.id.itemetDiscount);
        ImageButton btnEdit=v.findViewById(R.id.itemimgAdd);
        ImageButton btnDelete=v.findViewById(R.id.itemimgDelete);

//location
        //3.3 get the suitable shop object
        final MySale sale=getItem(position);
        //3.4 connect the dot to the view (view the data using item views)
//tvDiscount.setText(MySale.g);
        tvDiscount.setText( sale.getDiscountString());
        tvLastDate.setText((CharSequence) sale.getLastdate());
// tvDiscount (how to fill in the dis)

        /* I didnt understand this function
        switch(shop.getAdress()){
            case 1: tvDiscount.setBackgroundColor(Color.RED);break;
            case 2: tvDiscount.setBackgroundColor(Color.YELLOW);break;
            case 3: tvDiscount.setBackgroundColor(Color.CYAN);break;
            case 4: tvDiscount.setBackgroundColor(Color.MAGENTA);break;
            case 5: tvDiscount.setBackgroundColor(Color.RED);break;
        }
        switch(task.getNecessary()){
            case 5: tvImportant.setBackgroundColor(Color.RED);break;
            case 4: tvImportant.setBackgroundColor(Color.YELLOW);break;
            case 3: tvImportant.setBackgroundColor(Color.CYAN);break;
            case 2: tvImportant.setBackgroundColor(Color.MAGENTA);break;
            case 1: tvImportant.setBackgroundColor(Color.RED);break;
        }
         */
        //3.5 events

        btnEdit.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
            }
        });
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
            }
        });
        return v;

    }
}
