package com.example.admin.testcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    TextView tvResultPage2;
    Intent intent;
    int result;
    Button btnOK;
    EditText edtPage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initInstance();

    }

    private void initInstance() {
        intent = getIntent();
        result = intent.getIntExtra("result",0);
        tvResultPage2 = (TextView) findViewById(R.id.tvResultPage2);
        tvResultPage2.setText("result = "+result);

        edtPage2=findViewById(R.id.edtPage2);

        //Bundel intent
        Bundle bundle = new Bundle();
        bundle = intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        int z = bundle.getInt("z");

        //Parcelable intent
        CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");
        x = c3.x;
        y = c3.y;
        z = c3.z;
        Toast.makeText(this," "+x +y+z,Toast.LENGTH_LONG).show();
        btnOK=findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                String text = edtPage2.getText().toString();
                intentBack.putExtra("result",text);
                setResult(RESULT_OK,intentBack);
                finish();
            }
        });
    }
}
