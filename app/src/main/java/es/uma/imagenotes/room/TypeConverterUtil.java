package es.uma.imagenotes.room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class TypeConverterUtil {

    @TypeConverter
    public static Date toDate(Long date) {
        return new Date(date);
    }

    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Bitmap toBitmap(byte[] bitmap) {
        return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
    }

    @TypeConverter
    public static byte[] fromBitmap(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
