package com.lpiem_lyon1.comautis;

import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.lpiem_lyon1.comautis.Adapters.GalleryAdapter;
import com.lpiem_lyon1.comautis.Fragment.SlideshowDialogFragment;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends BaseActivity {

    private String TAG = GalleryActivity.class.getSimpleName();
    private ArrayList<Picture> mPicturesList = new ArrayList<Picture>();
    private GalleryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private File[] mFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        fetchPictures();

        if (mPicturesList.size() == 0){
            ImageView iv = new ImageView(this);
            Glide.with(this)
                    .load(R.mipmap.ic_missing_picture)
                    .crossFade().thumbnail(0.5f)
                    .into(iv);
            RelativeLayout relativeLayout = findViewById(R.id.relativeGalleryLayout);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            iv.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            relativeLayout.addView(iv, layoutParams);
        }

        getSupportActionBar().setTitle(R.string.library);
        mRecyclerView = findViewById(R.id.recyclerGridView);
        mAdapter = new GalleryAdapter(mPicturesList, this.getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), mRecyclerView, new GalleryAdapter.MyClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pictures", mPicturesList);
                bundle.putSerializable("position", mRecyclerView.getChildAdapterPosition(view));

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                SlideshowDialogFragment mFragmentZoom = SlideshowDialogFragment.newInstance();
                mFragmentZoom.setArguments(bundle);
                mFragmentZoom.show(fragmentTransaction, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void fetchPictures(){

        mFiles = PictureUtils.getPictures();
        for (int i = 0; i < mFiles.length; i++){
            Picture picture = new Picture();
            picture.setmPicturePath(mFiles[i].getAbsolutePath());
            picture.setName(mFiles[i].getName());
            picture.setOrder(String.valueOf(i));
            picture.setBitmap(PictureUtils.getBitmapFromPath(picture.getPicturePath()));
            mPicturesList.add(picture);
        }
    }
}