package jl.facedetection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by DELL on 12/17/2017.
 */

public class Result extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
    Bundle extras = getIntent().getExtras();
    byte[] byteArray = extras.getByteArray("picture");

    Bitmap result = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


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
      vMyiew.setContent(result, faces);

      faceDetector.release();
  }
}
}
