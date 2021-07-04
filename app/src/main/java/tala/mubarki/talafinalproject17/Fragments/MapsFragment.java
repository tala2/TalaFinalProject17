package tala.mubarki.talafinalproject17.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.MyUI.SignInActivity;
import tala.mubarki.talafinalproject17.R;

public class MapsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    //map
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private ListView shops_lst;
    private MyShopsAdaptor adaptor;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    Marker mCurrLocationMarker;
    private Spinner spinner_category;
    private Button btnFind;
    private Spinner spinner;
    private EditText etadress;
    public  String  adress="";
    public  String category="";

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            readTasksFromFirebase("");
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000 * 6); //1000MS=1S
            mLocationRequest.setFastestInterval(1000 * 6);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    mMap.setMyLocationEnabled(true);
                } else {
                    //Request Location Permission
                    checkLocationPermission();
                }
            } else {
                fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mMap.setMyLocationEnabled(true);
            }

        }
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        @Override
//
//        public View onCreateView(
//                LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState)
//        {
//            View view= inflater.inflate(R.layout.fragment_maps, container, false);
//            return view;
//        }

    };
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);


                if (mLastLocation == null) {
                    //move map camera
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                }
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                } else {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                }


                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                Toast.makeText(getContext(), "Current Position", Toast.LENGTH_SHORT).show();
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mCurrLocationMarker = mMap.addMarker(markerOptions);
                //move map camera
                // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        //adapter to itemview
        adaptor=new MyShopsAdaptor(getContext(),R.layout.item_shop_view1);
        //find view
        shops_lst = view.findViewById(R.id.shops_list);
        shops_lst.setAdapter(adaptor);
        etadress=view.findViewById(R.id.EtAdress1);
        spinner_category = view.findViewById(R.id.spinner_categ1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.kind, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter);
        btnFind=view.findViewById(R.id.btnFind2);
        readTasksFromFirebase("");
        /**
         * listener for the spinner, which will call the "onItemSelected" method when the item is selected.
         */
        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //signs out if the user chooses the last item un the spinner
                if (i == 6) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signOut();
                    Intent intent=new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //search
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //takes the value from the selected item
                category= ( String ) spinner_category.getSelectedItem();
                //takes the text
                adress=etadress.getText().toString();
                //go the readTasksFromFirebase
               readTasksFromFirebase("");
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(( Activity ) getContext(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSIONS_REQUEST_LOCATION);

            ;
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null) {
                    mLastLocation=location;
//                    if(route!=null)
//                    route.getPics().put(mLastLocation,"test");
                    LatLng latLng = new LatLng(location.getAltitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(""));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    Toast.makeText(getContext(), "Last Location:", Toast.LENGTH_LONG).show();
                }
            }
        });
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment)
//                getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(( OnMapReadyCallback ) getContext());
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = ( SupportMapFragment ) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(( Activity ) getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(( Activity ) getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(( Activity ) getContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (fusedLocationClient != null) {
            fusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    /**
     *        // Now, request for permission if not granted and get the result on onRequestPermissionsResult overridden method
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }
    //download from firebase
    private void readTasksFromFirebase(final String stTosearch) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        reference.child("AllShops").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adaptor.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Shop t = d.getValue(Shop.class);
                    Log.d("MyShops", t.toString());
                    if (adress.length() == 0 || category.length()==0) {
                       {
                           //adds to the adapter
                            adaptor.add(t);
                            //transforming the address of the shop into (latitude, langtitude) coordinations
                           Geocoder geocoder= new Geocoder(getContext());
                           //try and catch checks if there is mistake while running the project
                           try {
                               //list to collect the first tree results of the geocoder
                               List<Address> addressList = geocoder.getFromLocationName(t.getAddress(), 3);
                               //if he finds address
                               if(addressList.size()>0) {
                                   //takes only the first address
                                   Address address = addressList.get(0);
                                   //bulding a latlng of the address
                                   LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                   // if there is MAP
                                   if(mMap!=null) {
                                       //ADDS THE SHOP
                                       mMap.addMarker(new MarkerOptions().position(latLng).title(t.getName()));
                                       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                                   }
                               }
                               } catch (IOException e) {
                               //used to handle exceptions and errors.
                               e.printStackTrace();
                           }
                       }
                       //do the search, checks if the category and the address is any shop of the list
                    } else if (t.getCategory().contains(category)&&t.getAddress().contains(adress)) {
                        {
                            adaptor.add(t);
                            Geocoder geocoder= new Geocoder(getContext());
                            try {
                                List<Address> addressList = geocoder.getFromLocationName(t.getAddress(), 3);
                                if(addressList.size()>0) {
                                    Address address = addressList.get(0);
                                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                    if(mMap!=null) {
                                        mMap.addMarker(new MarkerOptions().position(latLng).title(t.getName()));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedShops=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(),selectedShops,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    @Override
    public void onResume() {
        super.onResume();
        readTasksFromFirebase("");
    }
}
