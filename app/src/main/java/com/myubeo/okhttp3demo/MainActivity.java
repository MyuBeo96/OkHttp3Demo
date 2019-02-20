package com.myubeo.okhttp3demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://tranhdantuongvietnam.com/wp-content/uploads/2014/11/tranh-phong-canh-TPC-743-600x420.jpg";
    Button downloadBtn;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadBtn = (Button) findViewById(R.id.button);
        mImage = (ImageView) findViewById(R.id.imageView);

        downloadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                downloadBtn.setVisibility(View.INVISIBLE);
                OkHttpHandler handler = new OkHttpHandler();
                byte[] image;

                try {
                    image = handler.execute(URL).get();

                    if (image != null && image.length > 0) {

                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,
                                image.length);
                        mImage.setImageBitmap(bitmap);
                        mImage.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Connect Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_SignIn) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
