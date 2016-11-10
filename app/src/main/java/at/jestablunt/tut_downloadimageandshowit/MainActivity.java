package at.jestablunt.tut_downloadimageandshowit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView downloadedImg;
    private ProgressDialog simpleWaitDialog;
    private String downloadUrl = "http://www.jestablunt.at/Group-Party.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button imageDownloaderBtn = (Button) findViewById(R.id.downloadButton);
        downloadedImg = (ImageView) findViewById(R.id.imageView);

        imageDownloaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImageDownloader().execute(downloadUrl);
            }
        });
    }

    private class ImageDownloader extends AsyncTask {
        @Override
        protected Bitmap doInBackground(String... param) {
            return downloadBitmap(param[0]);
        }
        @Override
        protected void onPreExecute() {
            Log.i("Async-Example","onPreExecute called");
            simpleWaitDialog = ProgressDialog.show(ImageDownloaderActivity.this, "Wait", "Downloading Image")
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("Async-Example", "onPostExecute called");
            downloadedImg.setImageBitmap(result);
        }
    }
}
