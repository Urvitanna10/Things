package com.example.things.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.things.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private Spinner spinner_country;
    private EditText editText_number;
    private Button button_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_country = findViewById(R.id.spinnerCountries);

        //set All countryNames in Spinner
        spinner_country.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,CountryData.countryNames));

        editText_number = findViewById(R.id.editTextPhone);
        button_continue = findViewById(R.id.buttonContinue);

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = CountryData.countryAreaCodes[spinner_country.getSelectedItemPosition()];

                String number = editText_number.getText().toString().trim();


                if (number.isEmpty() || number.length() < 10) {
                    editText_number.setError("Valid number is required");
                    editText_number.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;
                Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);

            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
   

}
