package com.example.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;


import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Objects;
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    GoogleMap mMap;
    private static final String TAG = "MapsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2368AA"));
        actionBar.setBackgroundDrawable(colorDrawable);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        Places.initialize(getApplicationContext(), "AIzaSyAR17a2D0DzwSCnaQcrfVaeOqGSvb1ljnQ");
        PlacesClient placesClient = Places.createClient(this);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);
        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(37.0902, 95.7129),
                new LatLng(38.0000, 97.0000)));
        autocompleteFragment.setCountries("US");
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull final Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getLatLng());
                Marker placeMarker = mMap.addMarker(new MarkerOptions()
                        .position(Objects.requireNonNull(place.getLatLng()))
                        .title(place.getName()));
                placeMarker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 10));
            }
            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
   @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class );
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to Exit Gyms Near You?");
        alert.setCancelable(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
            }

        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.create().show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }
    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .title(poi.name));
                poiMarker.showInfoWindow();
                poiMarker.setTag("poi");
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        setPoiClick(mMap);
        enableMyLocation();

        LatLng PF1 = new LatLng(43.218653,-70.885168);
        mMap.addMarker(new MarkerOptions().position(PF1).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Planet Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PF1));
        LatLng PF2 = new LatLng(43.313564,-70.995469);
        mMap.addMarker(new MarkerOptions().position(PF2).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Planet Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PF2));
        LatLng PF3 = new LatLng(43.216578,-71.530601);
        mMap.addMarker(new MarkerOptions().position(PF3).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Planet Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PF3));
        LatLng PF4 = new LatLng(43.053115,-70.769049);
        mMap.addMarker(new MarkerOptions().position(PF4).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Planet Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PF4));
        LatLng PF5 = new LatLng(42.140877,-72.579382);
        mMap.addMarker(new MarkerOptions().position(PF5).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Planet Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PF5));
        LatLng AF1 = new LatLng(43.08173851,-94.23463608);
        mMap.addMarker(new MarkerOptions().position(AF1).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF1));
        LatLng AF2 = new LatLng(37.79175111,-90.43660294);
        mMap.addMarker(new MarkerOptions().position(AF2).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF2));
        LatLng AF3 = new LatLng(43.00959377,-89.72478588);
        mMap.addMarker(new MarkerOptions().position(AF3).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF3));
        LatLng AF4 = new LatLng(43.05463534,-89.4501029);
        mMap.addMarker(new MarkerOptions().position(AF4).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF4));
        LatLng AF5 = new LatLng(27.9392744,-82.77348015);
        mMap.addMarker(new MarkerOptions().position(AF5).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF5));
        LatLng AF6 = new LatLng(36.77886782,-76.58157957);
        mMap.addMarker(new MarkerOptions().position(AF6).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF6));
        LatLng AF7 = new LatLng(34.0396216,-84.52936213);
        mMap.addMarker(new MarkerOptions().position(AF7).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF7));
        LatLng AF8 = new LatLng(32.74674408,-97.79834719);
        mMap.addMarker(new MarkerOptions().position(AF8).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF8));
        LatLng AF9 = new LatLng(28.54803386,-81.70761405);
        mMap.addMarker(new MarkerOptions().position(AF9).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF9));
        LatLng AF10 = new LatLng(41.92405688,-70.80649568);
        mMap.addMarker(new MarkerOptions().position(AF10).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Anytime Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF10));
        LatLng AF11 = new LatLng(33.97653217399466, -84.02019411868062);
        mMap.addMarker(new MarkerOptions().position(AF11).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Gold's Gym"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF11));
        LatLng AF12 = new LatLng(33.84194573350129, -84.01339667534421);
        mMap.addMarker(new MarkerOptions().position(AF12).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Workout Anytime"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF12));
        LatLng AF13 = new LatLng(33.99366166230684, -84.08159401104108);
        mMap.addMarker(new MarkerOptions().position(AF13).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Life Time"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF13));
        LatLng AF14 = new LatLng(33.97959741054176, -84.07737234937159);
        mMap.addMarker(new MarkerOptions().position(AF14).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("LA Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF14));
        LatLng AF15 = new LatLng(33.94668868074568, -84.19263124380889);
        mMap.addMarker(new MarkerOptions().position(AF15).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Musclecity Barbell"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF15));
        LatLng AF16 = new LatLng(34.112284229191395, -84.00071382495861);
        mMap.addMarker(new MarkerOptions().position(AF16).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Georgia Fitness of Buford"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF16));
        LatLng AF17 = new LatLng(34.07450598886953, -83.97833347290899);
        mMap.addMarker(new MarkerOptions().position(AF17).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Fitness Studio"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF17));
        LatLng AF18 = new LatLng(33.96341938695069, -84.05437946425381);
        mMap.addMarker(new MarkerOptions().position(AF18).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Fitness 621"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF18));
        LatLng AF19 = new LatLng(33.95288078920199, -84.0532680576715);
        mMap.addMarker(new MarkerOptions().position(AF19).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Kick Start Gym"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF19));
        LatLng AF20 = new LatLng(33.924300861796155, -84.02058434341839);
        mMap.addMarker(new MarkerOptions().position(AF20).icon((BitmapDescriptorFactory.fromResource(R.drawable.gym_logo))).title("Tapout Fitness"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AF20));
    }
}