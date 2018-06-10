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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    EditText myStickersView;
    EditText foreignStickersView;
    EditText compareResultView;

    Pattern splitPattern = Pattern.compile("[,./;: ]");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myStickersView = findViewById(R.id.myStickers);
        foreignStickersView = findViewById(R.id.foreignStickers);
        compareResultView = findViewById(R.id.compareResult);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                makeCompare(splitPattern.split(myStickersView.getText()),
                        splitPattern.split(foreignStickersView.getText()));
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
    }

}
