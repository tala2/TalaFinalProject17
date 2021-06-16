package tala.mubarki.talafinalproject17.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tala.mubarki.talafinalproject17.R;

public class MyShopsAdaptor extends ArrayAdapter<Shop> {
    /**
     * constructor
     * @param context  the activity of (app) that this adapter belong to
     * @param resource XML  design of the item
     */
    //fix error
    public MyShopsAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
        /**
         * bulding single item view
         * @param position index item in list view
         * @param convertView item view
         * @param parent listview
         * @return
         */
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //3.1
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_shop_view1, parent, false);
        //3.2 find view by ID
        ImageButton btnDelete = v.findViewById(R.id.imgBtnDelete);
        TextView tvName = v.findViewById(R.id.itmTvname);
        //what is the best type of this field(tvtype+ tvdiscount)
        //sale
        TextView tvDiscount = v.findViewById(R.id.itmtVDiscount);
        //location
        TextView tvAddress = v.findViewById(R.id.itmTvAddress);
        //edit
        ImageButton btnEdit = v.findViewById(R.id.imgBtnEdit);
        //category
        TextView tvCategory=v.findViewById(R.id.itmTvCategory);

        // ImageButton btnLocation=v.findViewById(R.id.);

        //3.3 get the soutable shop object
        final Shop shop = getItem(position);
        //3.4 connect the dot to the view (view the data using item views)
        tvName.setText(shop.getName());
        tvAddress.setText(shop.getAddress());
        tvDiscount.setText(shop.getDiscountString());
        tvCategory.setText(shop.getCategory());
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
//read and
}
