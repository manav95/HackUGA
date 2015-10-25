package com.example.manavdutta1.hackuga;

import android.widget.AdapterView;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;
import java.util.ArrayList;
import android.content.res.AssetManager;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by manavdutta1 on 10/24/15.
 */
public class RoleListener  implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence role = (CharSequence) parent.getItemAtPosition(position);
        MainActivity.setCurrRole(role.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
           MainActivity.setCurrRole(null);
    }
}
