package eu.kudan.kudansamples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;



public class AppActivity extends Activity implements View.OnClickListener {

    Button button;
    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emot);


        addListenerOnButton();
    }

    public void addListenerOnButton() {



        button = (Button) findViewById(R.id.button1);
        button = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button3);

        Button one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button1:
                Intent intent = new Intent(context, EmotionApiActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                Intent intent2 = new Intent(context, FaceActivity.class);
                startActivity(intent2);
                break;

            case R.id.button3:
                Intent intent3 = new Intent(context, MainActivityPager.class);
                startActivity(intent3);
                break;



            default:
                break;
        }

    }
}