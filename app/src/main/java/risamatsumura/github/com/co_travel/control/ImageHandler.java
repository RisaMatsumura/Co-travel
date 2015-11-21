package risamatsumura.github.com.co_travel.control;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by risam on 29/10/2015.
 */
public class ImageHandler {

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static Bitmap getBitmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static Bitmap getSmallBitmap(byte[] byteArray){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        return bm;
    }

}
