package com.hoverdroids.roundedimageview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO apply with the toolbar since the is the main actionbar here
        applyHoverDroidsBranding();
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Spinner navSpinner = (Spinner) findViewById(R.id.spinner_nav);

        navSpinner.setAdapter(ArrayAdapter.createFromResource(
                navSpinner.getContext(),
                R.array.action_list,
                android.R.layout.simple_spinner_dropdown_item));

        navSpinner.setOnItemSelectedListener(this);

        if (savedInstanceState == null) {
            navSpinner.setSelection(0);
        }
    }

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Fragment newFragment;
        switch (position) {
            default:
            case 0:
                // bitmap
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.DEFAULT);
                break;
            case 1:
                // oval
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.OVAL);
                break;
            case 2:
                // select
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.SELECT_CORNERS);
                break;
            case 3:
                // picasso
                newFragment = new PicassoFragment();
                break;
            case 4:
                // color
                newFragment = new ColorFragment();
                break;
            case 5:
                // background
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.BACKGROUND);
                break;
        }
        /*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .commit();*/
    }

    @Override public void onNothingSelected(AdapterView<?> parent) { }

    //None of this is essential for the example. It is only used to brand HoverDroids examples
    private void applyHoverDroidsBranding() {
        //Show app icon
        ActionBar actBar = getSupportActionBar();
        if (actBar != null) {
            actBar.setDisplayShowHomeEnabled(true);
            actBar.setIcon(R.mipmap.ic_ab_hoverdroids);
            actBar.setTitle(R.string.ab_title);
        }

        //Set actions to take when the AB is clicked
        Toolbar ab = findViewById(R.id.action_bar);
        if (ab != null) {
            for (int i = 0; i < ab.getChildCount(); i++) {
                setActionBarChildActions(ab.getChildAt(i));
            }
        }
    }

    private void setActionBarChildActions(View child) {
        //Send users to HoverDroids.com when the title or icon are clicked
        if (child instanceof TextView || child instanceof ImageView) {
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://www.HoverDroids.com";

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }
    }
}
