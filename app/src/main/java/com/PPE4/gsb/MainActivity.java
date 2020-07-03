package com.PPE4.gsb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.PPE4.gsb.mainFragments.ChangeFraisFragment;
import com.PPE4.gsb.mainFragments.FraisFragment;
import com.PPE4.gsb.mainFragments.NewFraisFragment;
import com.PPE4.gsb.mainFragments.ProfileFragment;
import com.PPE4.gsb.mainFragments.VisiteFragment;
import com.PPE4.gsb.objects.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static User _currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        BottomNavigationView bottomNav = findViewById(R.id.activity_main_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new VisiteFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()) {
                case R.id.action_profile:
                    selectedFragment = new ProfileFragment();
                    break;

                case R.id.action_checkVisites:
                    selectedFragment = new VisiteFragment();
                    break;

                case R.id.action_addFrais:
                    selectedFragment = new NewFraisFragment();
                    break;

                case R.id.action_changeFrais:
                    selectedFragment = new ChangeFraisFragment();
                    break;

                case R.id.action_checkFrais:
                    selectedFragment = new FraisFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, selectedFragment).commit();

            return true;
        }
    };

    public static void changeCurrentUser(User user) {
        _currentUser = user;
    }

    public static User getCurrentUser() {
        return _currentUser;
    }
}
