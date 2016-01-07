package com.lpiem_lyon1.comautis;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lpiem_lyon1.comautis.Adapters.DrawerAdapter;
import com.lpiem_lyon1.comautis.Models.DrawerItem;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;
    private List<DrawerItem> mDrawerItemList;
    private ListView mDrawerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = getBaseContext();

        //Find items in layout
        mDrawerListView = (ListView)findViewById(R.id.list_view_drawer);

        //init drawer menu item
        mDrawerItemList = new ArrayList<>();
        mDrawerItemList.add(new DrawerItem(getString(R.string.child_list),R.drawable.ic_child_face));
        mDrawerItemList.add(new DrawerItem(getString(R.string.library),R.drawable.ic_library));

        //init list view with menu items
        DrawerAdapter drawerAdapter = new DrawerAdapter(mContext , mDrawerItemList);
        mDrawerListView.setAdapter(drawerAdapter);
    }
}
