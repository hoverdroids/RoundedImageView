package com.hoverdroids.roundedimageview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyHoverDroidsBranding();

        //Show the bitmap option by default
        Fragment newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.DEFAULT);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.menu_main);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item){
                        return onOptionsItemSelected(item);
                    }
                }
        );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Fragment newFragment;
        switch (item.getItemId()) {
            default:
            case R.id.action_bitmap:
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.DEFAULT);
                break;
            case R.id.action_oval:
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.OVAL);
                break;
            case R.id.action_select:
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.SELECT_CORNERS);
                break;
            case R.id.action_picasso:
                newFragment = new PicassoFragment();
                break;
            case R.id.action_color:
                newFragment = new ColorFragment();
                break;
            case R.id.action_background:
                newFragment = RoundedFragment.getInstance(RoundedFragment.ExampleType.BACKGROUND);
                break;
        }

        if(newFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .commit();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //None of this is essential for the example. It is only used to brand HoverDroids examples
    private void applyHoverDroidsBranding() {

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);

        //Take actions when users click the icon and title
        if (tb != null) {
            for (int i = 0; i < tb.getChildCount(); i++) {
                setActionBarChildActions(tb.getChildAt(i));
            }
        }

        //Set the toolbar as the ActionBar and disable the default title since we included it already
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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