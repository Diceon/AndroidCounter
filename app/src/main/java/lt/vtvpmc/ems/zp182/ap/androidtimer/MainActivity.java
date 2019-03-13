package lt.vtvpmc.ems.zp182.ap.androidtimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView counterTextView;
    Button buttonCounterStartStop;

    Timer counterTimer;

    int counterCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        buttonCounterStartStop = findViewById(R.id.counterButtonStartStop);


        buttonCounterStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonCounterStartStop.getText() == getResources().getString(R.string.counter_button_start)) {
                    buttonCounterStartStop.setText(R.string.counter_button_stop);

                    counterTimer = new Timer();
                    counterCount = 0;

                    final Long currentTime = System.currentTimeMillis();


                        counterTimer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //counterTextView.setText(String.valueOf(counterCount));
                                        counterTextView.setText(String.valueOf(Math.abs(currentTime - System.currentTimeMillis())));
                                        //counterCount++;
                                    }
                                });
                            }
                        }, 10, 10);

                } else {
                    buttonCounterStartStop.setText(R.string.counter_button_start);

                    if (counterTimer != null) {
                        counterTimer.cancel();
                    }

                }
            }
        });

    }
}
