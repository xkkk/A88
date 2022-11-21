package com.baorun.handbook.a88.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;

import com.baorun.handbook.a88.utils.SampleSizeUtil;import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapDecoder {


    public static int[] decodeBound(Resources res, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        return new int[] {options.outWidth, options.outHeight};
    }


    /**
     * ******************************* decode resource ******************************************
     */

    public static Bitmap decodeSampled(Resources resources, int resId, int reqWidth, int reqHeight) {
        return decodeSampled(resources, resId, getSampleSize(resources, resId, reqWidth, reqHeight));
    }

    public static int getSampleSize(Resources resources, int resId, int reqWidth, int reqHeight) {
        // decode bound
        int[] bound = decodeBound(resources, resId);

        // calculate sample size
        int sampleSize = SampleSizeUtil.calculateSampleSize(bound[0], bound[1], reqWidth, reqHeight);

        return sampleSize;
    }


    public static Bitmap decodeSampled(Resources res, int resId, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        // RGB_565
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // sample size
        options.inSampleSize = sampleSize;

        try {
            return BitmapFactory.decodeResource(res, resId, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

        return null;
    }

}
