package com.example.admin.testcode;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvFirst,tvTwo,tvResult;
    EditText edtUnderTextTwo,edtInput1,edtInput2;
    Button btnCopy,calculate;
    Integer inputNum1,inputNum2,sumInput1and2;
    RadioGroup rgMath;
    Point size = new Point();
    Display display;
//    RadioButton rbPlus,rbMinus,rbMultiply,rbDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        getDisplaySize();

    }

    private void getDisplaySize() {
        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(MainActivity.this,"width = "+width+" Height = "+ height,Toast.LENGTH_LONG).show();
    }

    private void initInstance() {
        tvFirst = findViewById(R.id.tvFirst);
        tvFirst.setMovementMethod(LinkMovementMethod.getInstance());//ใส่เพื่อให้สามารถกดลิ้งได้
        tvFirst.setText(Html.fromHtml("<a href=\"https://www.facebook.com/\">faceBook"));

        tvTwo = findViewById(R.id.tvShow);

        tvResult = findViewById(R.id.tvResult);

        edtUnderTextTwo = findViewById(R.id.edtInputText);
        edtUnderTextTwo.setOnEditorActionListener(onEditorActionListener);

        edtInput1 = findViewById(R.id.edtInputNum1);

        edtInput2 = findViewById(R.id.edtInputNum2);

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(listener);

        calculate= findViewById(R.id.btnCalculate);
        calculate.setOnClickListener(listener);

        rgMath = findViewById(R.id.rgMath);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnCopy) {
                tvTwo.setText(edtUnderTextTwo.getText());
            }
            if (v== calculate){
                try {
                    inputNum1=Integer.parseInt(edtInput1.getText().toString());

                }
                catch (NumberFormatException e){
                    inputNum1=0;
                }
                try {
                    inputNum2=Integer.parseInt(edtInput2.getText().toString());
                }
                catch (NumberFormatException e){
                    inputNum2=0;
                }
                // RadioGroup case
                switch (rgMath.getCheckedRadioButtonId()){
                    case R.id.rbPlus:
                        sumInput1and2 = inputNum2+inputNum1;
                        break;
                    case R.id.rbMinus:
                        sumInput1and2 = inputNum2-inputNum1;
                        break;
                    case R.id.rbMultiply:
                        sumInput1and2 = inputNum2*inputNum1;
                        break;
                    case R.id.rbDivide:
                        sumInput1and2 = inputNum2/inputNum1;
                        break;
                }

                tvResult.setText("result = "+sumInput1and2);
            }
        }
    };

    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(v == edtUnderTextTwo){
                tvTwo.setText(edtUnderTextTwo.getText());
                return true; // don't forget return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            //Do what you want
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
