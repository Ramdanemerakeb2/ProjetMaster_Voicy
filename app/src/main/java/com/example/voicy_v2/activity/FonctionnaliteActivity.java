package com.example.voicy_v2.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.voicy_v2.R;
import com.google.android.material.navigation.NavigationView;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class FonctionnaliteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    protected DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fonctionnalite);

        // 6 - Configure all views

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_drawer_acceuil :
                Intent intentMain = new Intent(FonctionnaliteActivity.this, MainActivity.class);
                startActivity(intentMain);
                break;
            case R.id.activity_main_drawer_ajoutPatient:
                Intent intentAjoutPatient = new Intent(FonctionnaliteActivity.this, AjoutPatientActivity.class);
                startActivity(intentAjoutPatient);
                break;
            case R.id.activity_main_drawer_recherchePatient:
                Intent intentRecherchePatient = new Intent(FonctionnaliteActivity.this, RecherchePatientActivity.class);
                startActivity(intentRecherchePatient);
                break;
            case R.id.activity_main_drawer_listePatient:
                Intent intentListPatient = new Intent(FonctionnaliteActivity.this, ListePatientActivity.class);
                startActivity(intentListPatient);
                break;
            case R.id.activity_main_drawer_ajoutExo:
                break;
            case R.id.activity_main_drawer_rechercheExo:
                break;
            case R.id.activity_main_drawer_logout:
                Intent intentLogout = new Intent(FonctionnaliteActivity.this, ConnexionActivity.class);
                startActivity(intentLogout);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_fonctionnalite);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}