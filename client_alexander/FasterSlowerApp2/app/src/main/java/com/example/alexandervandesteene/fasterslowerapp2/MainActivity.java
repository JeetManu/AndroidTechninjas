package com.example.alexandervandesteene.fasterslowerapp2;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView speedtext;
    private int total;
    private ImageView slowbutton,speedbutton,questionbutton,repeatbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        speedbutton = (ImageView) findViewById(R.id.speedup);
        slowbutton = (ImageView) findViewById(R.id.slowdown);
        speedtext = (TextView) findViewById(R.id.textViewSpeed);
        total = 0;
        questionbutton = (ImageView) findViewById(R.id.questionButton);
        repeatbutton = (ImageView) findViewById(R.id.repeatButton);
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
        if (id == R.id.feedback) {
            DialogFragment myfragment = new MyDialogFragment();

            myfragment.show(getFragmentManager(),"theDialog");
            return true;
        }else if(id == R.id.exit_the_app){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SpeedUpCourse(View view) {
        total++;
        speedtext.setText(String.valueOf(total));
        String yourResponse = "Speeding Up";

        Toast.makeText(this, yourResponse, Toast.LENGTH_SHORT).show();
    }

    public void SlowDownCourse(View view) {
        if(total <= 0){
            String yourResponse = "Can't get slower";
            Toast.makeText(this, yourResponse, Toast.LENGTH_SHORT).show();
        }else{
            total--;
            speedtext.setText(String.valueOf(total));

            String yourResponse = "Slowing Down";
            Toast.makeText(this, yourResponse, Toast.LENGTH_SHORT).show();
        }
    }

    public void IhaveQuestion(View view) {
        String yourResponse = "I have a Question";
        Toast.makeText(this, yourResponse, Toast.LENGTH_SHORT).show();
    }

    public void repeatstuk(View view) {
        String yourResponse = "Please Repeat";
        Toast.makeText(this, yourResponse, Toast.LENGTH_SHORT).show();
    }
}
