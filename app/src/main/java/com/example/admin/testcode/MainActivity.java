package com.example.admin.testcode;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvFirst,tvTwo,tvResult;
    EditText edtUnderTextTwo,edtInput1,edtInput2;
    Button btnCopy,calculate;
    Integer inputNum1,inputNum2,sumInput1and2;
    RadioGroup rgMath;
//    RadioButton rbPlus,rbMinus,rbMultiply,rbDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

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


}
