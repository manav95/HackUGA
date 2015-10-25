package com.example.manavdutta1.hackuga;

import android.content.res.Resources;
import android.widget.Button;
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
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Collections;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import android.content.res.AssetManager;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity{
    private static String currRole;
    private static String currChampion;
    private HashMap<String, Champion> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AssetManager manager = getAssets();
        Resources resources = this.getResources();
        map = ReadCVS.exec(resources);
        Spinner champSpinner = (Spinner) findViewById(R.id.championSpinner);
        //ArrayAdapter<CharSequence> champAdapter = ArrayAdapter.createFromResource(this, R.array.item_array, R.l)
        List<CharSequence> roleSequence = new ArrayList<CharSequence>();
        //roleSequence.setDropDownViewResource(R.layout.spinner_layout);
        //champSpinner.setAdapter((SpinnerAdapter) roleSequence);
        roleSequence.add("Top");
        roleSequence.add("Jungle");
        roleSequence.add("Mid");
        roleSequence.add("Support");
        roleSequence.add("Ad Carry");
        List<String> charSequence = new ArrayList<String>();
        String [] laser = new String[1];
        String [] keys = map.keySet().toArray(laser);
        for (String key : keys) {
            String champName = null;
            for (CharSequence role : roleSequence) {
                if (key.contains(role)) {
                    champName = key.substring(0, key.indexOf((String) role));
                    if (!charSequence.contains(champName)) {
                        charSequence.add(champName);
                    }
                }
            }
        }
        Collections.sort(charSequence);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, charSequence);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        champSpinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> roleAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, roleSequence);
        Spinner roleSpinner = (Spinner) findViewById(R.id.roleSpinner);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(roleAdapter);
        roleSpinner.setOnItemSelectedListener(new RoleListener());
        champSpinner.setOnItemSelectedListener(new ChampionListener());
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String concat = currChampion + currRole;
                Champion value = map.get(concat);
                EditText numGames = (EditText) findViewById(R.id.numGamesTotal);
                EditText numExpected = (EditText) findViewById(R.id.numExpectedGames);
                EditText numGamesWithChamp = (EditText) findViewById(R.id.numGamesWithChamp);
                Double quoteValue = value.calculateQuote(value.getWinRate(), Double.parseDouble(numGamesWithChamp.getText().toString()), Double.parseDouble(numExpected.getText().toString()), Double.parseDouble(numGames.getText().toString()), value.getPopularity());
                DecimalFormat quoteValueMod = new DecimalFormat("#.00");
                String presentable = quoteValueMod.format(quoteValue);

                TextView view = (TextView) findViewById(R.id.textView5);
                view.setText("$" + presentable);
            }
        });

    }
    public static void setCurrRole(String role) {
        currRole = role;
    }
    public static void setCurrChampion(String champion) {
        currChampion = champion;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
