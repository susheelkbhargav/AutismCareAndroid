package eu.kudan.kudansamples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class PasswordCheckActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    private SharedPreferences sharedPref;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(eu.kudan.kudansamples.R.layout.activity_check_password);

        sharedPref = getSharedPreferences(getString(eu.kudan.kudansamples.R.string.password), Context.MODE_PRIVATE);
        password = sharedPref.getString("password", "");

        editText = (EditText) findViewById(eu.kudan.kudansamples.R.id.password_editText);

        button = (Button) findViewById(eu.kudan.kudansamples.R.id.enter_password_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if password is wrong, try again, if right, go to Setting activity
                if (password.equals(editText.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
            }
        });

    }

}
