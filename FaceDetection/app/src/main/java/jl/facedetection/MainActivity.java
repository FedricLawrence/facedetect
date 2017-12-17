package jl.facedetection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

  Bitmap result;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.takepicture);

    Button takepic = (Button) findViewById(R.id.pic);

    Button detectface = (Button) findViewById(R.id.button4);

    detectface.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if(result!=null){
        Intent i = new Intent(getApplicationContext(), Result.class);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        i.putExtra("picture", byteArray);
        startActivity(i);
      }
      }
    });
  /*  detectface.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
          .setLandmarkType(FaceDetector.ALL_LANDMARKS)
          .setTrackingEnabled(false)
          .setMode(FaceDetector.FAST_MODE)
          .build();

if(result!=null){
          Frame frame = new Frame.Builder().setBitmap(result).build();
          SparseArray<Face> faces = faceDetector.detect(frame);

          if (!faceDetector.isOperational()) {
            Log.v("TAG", "Face detection error");
            return;
          }

          MyView vMyiew = (MyView) findViewById(R.id.my_view);
          vMyiew.setContext(result, faces);

          faceDetector.release();
        }
      }
    });*/

    takepic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 0);


      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    result = (Bitmap) data.getExtras().get("data");


  }
}
