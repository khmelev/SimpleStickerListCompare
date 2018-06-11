package ru.av3969.simplestickerlistcompare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText myStickersView;
    EditText foreignStickersView;
    EditText compareResultView;

    TextView statMy;
    TextView statForeign;
    TextView statCompare;

    Pattern splitPattern = Pattern.compile("[,./;:\\s_\n]+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myStickersView = findViewById(R.id.myStickers);
        foreignStickersView = findViewById(R.id.foreignStickers);
        compareResultView = findViewById(R.id.compareResult);

        statMy = findViewById(R.id.statMy);
        statForeign = findViewById(R.id.statForeign);
        statCompare = findViewById(R.id.statCompare);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_compare:
                makeCompare(splitPattern.split(myStickersView.getText()),
                        splitPattern.split(foreignStickersView.getText()));
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeCompare(String[] myStickers, String[] foreignStickers) {
        List<String> matchesList = new ArrayList<>();
        StringBuilder matches = new StringBuilder();

        for (String fSticker : foreignStickers) {
            for (String mSticker : myStickers) {
                if (fSticker.equals(mSticker) && fSticker.length() > 0)
                    matchesList.add(fSticker);
            }
        }

        for (String str : matchesList) {
            if (matches.length() > 0) matches.append(", ");
            matches.append(str);
        }

        compareResultView.setText(matches.toString());

        statMy.setText(String.valueOf(myStickers.length));
        statForeign.setText(String.valueOf(foreignStickers.length));
        statCompare.setText(String.valueOf(matchesList.size()));
    }

}
