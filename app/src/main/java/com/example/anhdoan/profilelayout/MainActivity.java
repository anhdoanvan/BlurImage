package com.example.anhdoan.profilelayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity {
    private  CircularImageView circularImageView;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        circularImageView = (CircularImageView)findViewById(R.id.image);
// Set Border
        circularImageView.setBorderColor(Color.WHITE);
        circularImageView.setBorderWidth(10);
// Add Shadow with default param
        circularImageView.addShadow();
// or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.WHITE);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.images);
        circularImageView.setImageBitmap(icon);

        image=(ImageView) findViewById(R.id.imageView1);
//        Glide.with(this)
//                .load("http://coc.vnhow.vn/img/uploads/layouts/thumbs/hall4/war/h4w1.jpg")
//                .into(circularImageView);
        Picasso.with(this)
                .load("http://tineye.com/images/widgets/mona.jpg")
                .error(R.drawable.images)
                .into(circularImageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                //Success image already loaded into the view
                                Bitmap bitmap = ((BitmapDrawable)circularImageView.getDrawable()).getBitmap();
                                Bitmap blurredBitmap = BlurBuilder.blur( MainActivity.this, bitmap );
                                circularImageView.setImageBitmap(blurredBitmap);
                            }

                            @Override
                            public void onError() {
                                //Error placeholder image already loaded into the view, do further handling of this situation here
                            }
                        }
                );
        //loadImageSimpleTarget();
        //https://drive.google.com/drive/folders/0B1Fd8LpsJklZR20wMFVfeHd3ZnM?usp=sharing
       // Picasso.with(this).load("http://coc.vnhow.vn/img/uploads/layouts/thumbs/hall4/war/h4w1.jpg").into(image);
//        Picasso.with( this )
//                .load( "http://coc.vnhow.vn/img/uploads/layouts/thumbs/hall4/war/h4w1.jpg" )
//                .placeholder( R.drawable.images )
//                .into( circularImageView);
    }

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
           // circularImageView.setImageBitmap( bitmap );
           // Bitmap bitmap1 = BitmapFactory.decodeResource(bitmap.);
            Bitmap blurredBitmap = BlurBuilder.blur( MainActivity.this, bitmap );

            circularImageView.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );
            //circularImageView.setImageBitmap(bitmap);

        }
    };

    private void loadImageSimpleTarget() {
        Glide
                .with( this ) // could be an issue!
                .load("http://coc.vnhow.vn/img/uploads/layouts/thumbs/hall4/war/h4w1.jpg")
                .asBitmap()
                .into( target );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private static final float BLUR_RADIUS = 25f;

//    public Bitmap blur(Bitmap image) {
//        if (null == image) return null;
//
//        Bitmap outputBitmap = Bitmap.createBitmap(image);
//        final RenderScript renderScript = RenderScript.create(this);
//        Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
//        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);
//
//        //Intrinsic Gausian blur filter
//        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
//        theIntrinsic.setRadius(BLUR_RADIUS);
//        theIntrinsic.setInput(tmpIn);
//        theIntrinsic.forEach(tmpOut);
//        tmpOut.copyTo(outputBitmap);
//        return outputBitmap;
//    }

}
