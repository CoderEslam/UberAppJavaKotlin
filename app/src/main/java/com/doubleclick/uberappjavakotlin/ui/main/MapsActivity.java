package com.doubleclick.uberappjavakotlin.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;

import com.doubleclick.uberappjavakotlin.R;

import com.doubleclick.uberappjavakotlin.Views.lib.SmoothBottomBar;
import com.doubleclick.uberappjavakotlin.ui.Fragments.SignInFragment;
import com.doubleclick.uberappjavakotlin.ui.Fragments.SignUpFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity  {

    private GoogleMap googleMap;
    private SmoothBottomBar bottomBar;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        bottomBar = findViewById(R.id.bottomBar);
        navController = Navigation.findNavController(this, R.id.main_fragment_maps);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.profileFragment, R.id.mapsFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        setupSmoothBottomMenu();
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


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
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//        // Add a marker in Sydney and move the camera
//        LatLng alex = new LatLng(31.2349224, 30.0014967);
//        googleMap.addMarker(new MarkerOptions().position(alex).title("Alexndria"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alex, 12)); //0-21
//    }
    private void setupSmoothBottomMenu() {
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.menu_bottom);
        Menu menu = popupMenu.getMenu();
        bottomBar.setupWithNavController(menu, navController);
    }

}