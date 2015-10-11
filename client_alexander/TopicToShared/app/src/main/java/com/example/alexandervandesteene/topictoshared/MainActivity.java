package com.example.alexandervandesteene.topictoshared;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPref";
    private String topic = "";
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();

        readAll();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();

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
        if (id == R.id.add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Add Topic");

            // Set up the input
            final EditText input = new EditText(MainActivity.this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            //input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    topic = input.getText().toString();
                    itemsAdapter.add(topic);
                    Write(topic);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                // Remove the item within array at position
                Remove(items.get(pos).toString());
                items.remove(pos);
                // Refresh the adapter
                itemsAdapter.notifyDataSetChanged();
                // Return true consumes the long click event (marks it handled)
                return true;
            }

        });
    }

    private void readAll(){

        Map<String, ?> allEntries = pref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            items.add(entry.getValue().toString());
        }
    };

    private void Write(String topic)
    {

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(topic,topic);
        editor.commit();
    }

    private void Remove(String topic){
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(topic);
        editor.apply();
    }

}
