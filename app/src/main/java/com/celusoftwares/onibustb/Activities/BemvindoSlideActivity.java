package com.celusoftwares.onibustb.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.celusoftwares.onibustb.R;

import ConfCompartilhada.PrefManager;
import adapter.ScreenSlidePageAdapter;

/**
 * Created by Jhonathan on 25/02/17.
 */

public class BemvindoSlideActivity extends AppCompatActivity {

    private PrefManager prefManager;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int[] layouts;
    private Button pular;
    private Button proximo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()){
            abrirTelaPrincipal();
            finish();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.bemvindo_activity);

        layouts = new int[]{
                R.layout.slide_bemvindo_1,
                R.layout.slide_bemvindo_2,
                R.layout.slide_bemvindo_3
        };

        pular = (Button) findViewById(R.id.btn_pular);
        proximo = (Button) findViewById(R.id.btn_proximo);

        viewPager = (ViewPager) findViewById(R.id.pager_bemvindo);
        pagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == layouts.length -1){
                    pular.setVisibility(View.GONE);
                    proximo.setText(R.string.entendi);
                }
                else{
                    pular.setVisibility(View.VISIBLE);
                    proximo.setText(R.string.proximo);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void abrirTelaPrincipal(){
        prefManager.setFirstTimeLaunch(false);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() -1);
        }

    }

    public void onPularPress(View v){
        if (viewPager.getCurrentItem() != 2){
            abrirTelaPrincipal();
            finish();
        }
    }
    public void onProximoPress(View v){
        if (viewPager.getCurrentItem() == 2){
            abrirTelaPrincipal();
            finish();
        }else {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+ 1);
        }
    }
}
