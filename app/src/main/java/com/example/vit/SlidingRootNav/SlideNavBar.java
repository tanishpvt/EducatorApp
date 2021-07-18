package com.example.vit.SlidingRootNav;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.vit.Authentication.MainActivityHandler;
import com.example.vit.Drawer.AboutUsFragment;
import com.example.vit.Drawer.ContactUsFragment;
import com.example.vit.Drawer.DashBoardFragment;
import com.example.vit.Drawer.DrawerAdapter;
import com.example.vit.Drawer.DrawerItem;
import com.example.vit.Drawer.MyProfileFragment;
import com.example.vit.Drawer.NearbyResFragment;
import com.example.vit.Drawer.SettingsFragment;
import com.example.vit.Drawer.SimpleItem;
import com.example.vit.Drawer.SpaceItem;
import com.example.vit.MainActivity;
import com.example.vit.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;


public class SlideNavBar extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTING = 4;
    private static final int POS_CONTACT_US = 5;
    private static final int POS_ABOUT_US = 6;
    private static final int POS_LOGOUT = 8;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //yea code hai tha
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_slide_nav_bar);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        setTitle("WELCOME TO PLUS");
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_RES),
                createItemFor(POS_SETTING),
                createItemFor(POS_CONTACT_US),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position],screenTitles[position])
                .withIconTint(color(R.color.drawer_icon_color))
                .withTextTint(color(R.color.textColor))
                .withSelectedIconTint(color(R.color.drawer_icon_color))
                .withSelectedTextTint(color(R.color.purple_200));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[   ] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons =  new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++){
            int id = ta .getResourceId(i, 0);
            if (id != 0 ){
                icons[i] = ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public boolean onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case POS_CLOSE:
                slidingRootNav.closeMenu();

            case POS_DASHBOARD:
                DashBoardFragment dashBoardFragment = new DashBoardFragment();
                transaction.replace(R.id.container, dashBoardFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;

            case POS_MY_PROFILE:
                MyProfileFragment myProfileFragment = new MyProfileFragment();
                transaction.replace(R.id.container, myProfileFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;


            case POS_NEARBY_RES:
                NearbyResFragment nearbyResFragment = new NearbyResFragment();
                transaction.replace(R.id.container, nearbyResFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;

            case POS_SETTING:
                SettingsFragment settingsFragment = new SettingsFragment();
                transaction.replace(R.id.container, settingsFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;

            case POS_ABOUT_US:
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                transaction.replace(R.id.container, aboutUsFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;

            case POS_CONTACT_US:
                ContactUsFragment contactUsFragment = new ContactUsFragment();
                transaction.replace(R.id.container, contactUsFragment);
                transaction.commit();
                slidingRootNav.closeMenu();
                return true;

            case POS_LOGOUT:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SlideNavBar.this, MainActivityHandler.class));
                finish();
        }

        transaction.addToBackStack(null);
        return false;
    }

    int doubleBackToExitPressed = 1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressed == 1) {
            Intent send = new Intent(SlideNavBar.this, MainActivity.class);
            startActivity(send);
//            finishAffinity();
//            System.exit(0);
        }
        else if (doubleBackToExitPressed == 2)
        {
            finishAffinity();
            System.exit(0);
        }
        else {
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(() -> doubleBackToExitPressed=1, 2000);
    }


}