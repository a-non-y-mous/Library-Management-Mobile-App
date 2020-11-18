package com.example.librarymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.librarymanagement.SearchResultAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , SearchResultAdapter.ListItemClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Menu menu;
    ImageButton mNotifs, mCart;
    Toolbar toolbar;
    private Toast mToast;
    private SearchResultAdapter mAdapter;
    private RecyclerView mList;
    int NUM_LIST_ITEMS=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mNotifs = (ImageButton) findViewById(R.id.mNotifButton) ;
        mCart = (ImageButton) findViewById(R.id.mCartButton);
        mList = (RecyclerView) findViewById(R.id.books_list);

        //show or hide
        //Menu menu = navigationView.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);
        //menu.findItem(R.id.nav_profile).setVisible(false);
        //toolbar as actionbar
        setSupportActionBar(toolbar);
        //recyclerViewsetup
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        mList.setHasFixedSize(true);
        mAdapter = new SearchResultAdapter(100,this);
        mList.setAdapter(mAdapter);
        //nav_view
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        //onClickListeners for the notifs and cart button on the homepage
        mNotifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* TODO add a notifs activity */
                Toast.makeText(getApplicationContext(),"Notifs pressed",Toast.LENGTH_SHORT).show();
            }
        });
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });

    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.action_refresh) {
            mAdapter = new SearchResultAdapter(NUM_LIST_ITEMS, this);
            mList.setAdapter(mAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home: break;
            case R.id.nav_cart:
                startActivity(new Intent(MainActivity.this,CartActivity.class));break;
            case R.id.nav_help:
                startActivity(new Intent(MainActivity.this,HelpActivity.class));break;
            case R.id.nav_wishlist:
                startActivity(new Intent(MainActivity.this,WishListActivity.class));break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this,UserProfile.class));break;
            case R.id.nav_login:
                //Toast.makeText(this,"LogIn",Toast.LENGTH_SHORT).show();break;
                startActivity(new Intent(MainActivity.this,MainLoginActivity.class));break;
            case R.id.nav_logout:
                Toast.makeText(this,"LogOut",Toast.LENGTH_SHORT).show();break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}