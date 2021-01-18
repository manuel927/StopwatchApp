package co.edu.unipiloto.stopwatchapp;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private ListView listview;
    private ArrayList<String> vueltas;
    private int seconds = 0;
    private boolean running;
    private String vuelta;
    private int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        listview = (ListView) findViewById(R.id.lista);
        vueltas = new ArrayList<String>();
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vueltas);
        runTimer();
    }

    public void onSaveInstancesState(Bundle savedInstancesState){
        savedInstancesState.putInt("seconds",seconds);
        savedInstancesState.putBoolean("running",running);
    }

    //Start the stopwatch running when the Start button is clicked
    public void onClickStart(View view){
        running = true;
    }
    //Stop the stopwatch running when the Stop button is clicked
    public void onClickStop(View view){
        running = false;
    }
    //Reset the stopwatch running when the Reset button is clicked
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    public void onClickVuelta(View view){
        contador ++;
        vueltas.add("Vuelta " + contador + ":  " + vuelta);
        seconds = 0;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vueltas);
        listview.setAdapter(adapter);
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);

                vuelta = time;

                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });


    }






}