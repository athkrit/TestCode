package com.example.admin.testcode;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.annotation.Nullable;
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
    CustomViewGroup customViewGroup1,customViewGroup2;
    RadioGroup rgMath;

    Integer inputNum1,inputNum2,sumInput1and2;

    Point size = new Point();
    Display display;

//    RadioButton rbPlus,rbMinus,rbMultiply,rbDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set content
        //UI
        //findViewId
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
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

        customViewGroup1 =(CustomViewGroup) findViewById(R.id.viewGroup1);
        customViewGroup2 =findViewById(R.id.viewGroup2);

        customViewGroup1.setTextBtn("Hello");
        customViewGroup2.setTextBtn("World");

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
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("result",sumInput1and2);
                Coordinate c1;
                c1 = new Coordinate();
                c1.x = 15;
                c1.y = 20;
                c1.z = 25;
                Bundle bundle = new Bundle();
                bundle.putInt("x",c1.x);
                bundle.putInt("y",c1.y);
                bundle.putInt("z",c1.z);
                intent.putExtra("cBundle" , bundle);
                CoordinateParcelable c3= new CoordinateParcelable();
                c3.x = 12;
                c3.y = 17;
                c3.z = 22;
                intent.putExtra("cParcelable",c3);
                startActivityForResult(intent , 12345);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("result");
                Toast.makeText(MainActivity.this, "Result is " + text, Toast.LENGTH_LONG).show();
            }
        }
    }

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

    @Override
    protected void onStart() {
        super.onStart();
        //Reserve a Resource(camera,GPS)
        //Animation
        //Timer
    }
    @Override
    protected void onStop() {
        super.onStop();
        //release a reserved-Resource(camera,GPS)
        //Stop animation
        //stop timer
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //commit unsaved change
        //pause game
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save thing
//        outState.putString("text",tvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore thing
//        tvResult.setText(savedInstanceState.getString("text"));
    }
}
