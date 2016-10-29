package com.example.alice.udemymedia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 123;
    public static final int REQUEST_CODE_GALERY = 234;
    private ImageView imageViewHolder;
    private VideoView videoViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewHolder = (ImageView) findViewById(R.id.imageVHolder);
        videoViewHolder = (VideoView) findViewById(R.id.videoVHolder);
    }

    /**
     * play song located in raw folder
     * @param view
     */
    public void playSong(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.casino_sound);
        mediaPlayer.start();
    }

    public void playVideo(View view) {
        String strPath = "android.resource://"  + getPackageName() +"/"+ R.raw.fantastic_video;
        Uri uri = Uri.parse(strPath);
        videoViewHolder.setVideoURI(uri);
        videoViewHolder.setMediaController(new MediaController(this));
        videoViewHolder.start();
        videoViewHolder.requestFocus();
    }

    public void takeCameraPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    public void selectGaleryImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_CAMERA){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageViewHolder.setImageBitmap(bitmap);

            }else if (requestCode == REQUEST_CODE_GALERY && data != null){
                Uri selectedImageURI = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImageURI,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                imageViewHolder.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            }
        }
    }
}
