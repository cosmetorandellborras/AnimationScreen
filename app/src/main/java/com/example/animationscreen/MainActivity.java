package com.example.animationscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.animationscreen.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        binding.TextViewTopTitle.startAnimation(fade1);

        Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        //Carga y arranca a la vez
        LayoutAnimationController controller = new LayoutAnimationController(spinin);
        for(int i=0;i<binding.TableLayout01.getChildCount();i++){
            TableRow row = (TableRow) binding.TableLayout01.getChildAt(i);
            row.setLayoutAnimation(controller);
        }

        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        binding.TextViewBottomTitle.startAnimation(fade2);
        fade2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        binding.TextViewTopTitle.clearAnimation();

        for(int i=0;i<binding.TableLayout01.getChildCount();i++){
            TableRow row = (TableRow) binding.TableLayout01.getChildAt(i);
            row.clearAnimation();
        }
        binding.TextViewBottomTitle.clearAnimation();
    }
}