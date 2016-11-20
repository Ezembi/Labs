package labs.mobile.victor.lab_1.Lab_4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by Victor on 20.11.2016.
 *
 */
public class ImageLoader extends AsyncTask<Void, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private final WeakReference<ProgressBar> progressBarReference;
    private String url;

    public ImageLoader(ImageView imageView, ProgressBar progress, String url) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<>(imageView);
        progressBarReference = new WeakReference<>(progress);
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        //clear old image
        final ImageView imageView = imageViewReference.get();
        imageView.setImageDrawable(null);
        //show progressBar
        toggleProgressBar(true);
        super.onPreExecute();
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Void... urls) {
        URL url = null;
        try {
            url = new URL(this.url);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
        //dismiss
        toggleProgressBar(false);
    }

    @Override
    protected void onCancelled() {
        //dismiss
        toggleProgressBar(false);
        super.onCancelled();
    }

    private void toggleProgressBar(boolean show) {
        final ProgressBar progressBar = progressBarReference.get();
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}