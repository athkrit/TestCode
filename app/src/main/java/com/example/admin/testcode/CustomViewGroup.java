package com.example.admin.testcode;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.FrameLayout;

public class CustomViewGroup extends FrameLayout {
    private Button btnCustomViewGroup;
    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public CustomViewGroup( Context context,AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public CustomViewGroup(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(21)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }
    private void initInflate(){
        inflate(getContext(),R.layout.sample_layout,this);
    }
    private void initInstance(){
        btnCustomViewGroup = findViewById(R.id.btnCustomViewGroup);
    }
    public void setTextBtn(String text){
        btnCustomViewGroup.setText(text);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        //save children's state as a bundle
        Bundle childrenStates = new Bundle();
        for(int i =0 ; i < getChildCount() ; i++){
            int id = getChildAt(i).getId();
            if(id != 0){
                SparseArray childrenState = new SparseArray();
                getChildAt(i).saveHierarchyState(childrenState);
                childrenStates.putSparseParcelableArray(String.valueOf(id),childrenState);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("childrenStates",childrenStates);

        BundleSavedState ss = new BundleSavedState(superState);
        ss.setBundle(bundle);
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = ( BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle childrenStates = ss.getBundle().getBundle("childrenStates");
        for(int i = 0 ; i<getChildCount() ; i++){
            int id = getChildAt(i).getId();
            if(id != 0){
                if(childrenStates.containsKey(String.valueOf(id))){
                    SparseArray childrenState =
                            childrenStates.getSparseParcelableArray(String.valueOf(id));
                    getChildAt(i).restoreHierarchyState(childrenState);
                }
            }
        }
    }
}
