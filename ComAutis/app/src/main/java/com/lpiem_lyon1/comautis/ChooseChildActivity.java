package com.lpiem_lyon1.comautis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lpiem_lyon1.comautis.Adapters.ListChildAdapter;
import com.lpiem_lyon1.comautis.Database.RequestCallback;
import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.Models.Model;

import java.util.ArrayList;
import java.util.List;

public class ChooseChildActivity extends BaseActivity {

    private ListView mChildListView;
    EditText etNameChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_child);

        mChildListView = (ListView) findViewById(R.id.lv_child);

        //init list child item
        loadChild();

        FloatingActionButton fabAddChild = (FloatingActionButton) findViewById(R.id.btn_add_child);
        fabAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                //Associate view to layout modal dialog
                View dialogAddLayout = inflater.inflate(R.layout.dialog_add_child, null);
                //Create a builder for the dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
                //Associate the view to the builder
                mBuilder.setView(dialogAddLayout);
                etNameChild = (EditText)dialogAddLayout.findViewById(R.id.et_ad_child_name);

                //Setting Buttons Yes or No
                // Setting Positive "Yes" Button
                mBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button.
                        String nameChild = etNameChild.getText().toString();

                        if (nameChild != null && !nameChild.isEmpty()) {
                            //TODO
                            Child myChild = new Child();
                            myChild.setName(nameChild);
                            mLocalDb.insertChild(myChild, null);
                            Toast.makeText(getApplicationContext(), "Child created",
                                    Toast.LENGTH_SHORT).show();
                            loadChild();
                        } else {
                            Toast.makeText(getApplicationContext(), "Retry and please give a name",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Setting Negative "NO" Button
                mBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button.
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                //Creation AlertDialog and associate to the builder showed.
                AlertDialog alertDialogCreateChild = mBuilder.show();
            }
        });
    }

    private void loadChild(){
        final List<Child> listChild = new ArrayList<Child>();
        mLocalDb.requestChild(new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                for (int i = 0; i < entities.size(); i++) {
                    listChild.add((Child) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });


        //init list view with list child items
        ListChildAdapter listChildAdapter = new ListChildAdapter(listChild, getBaseContext());
        mChildListView.setAdapter(listChildAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
