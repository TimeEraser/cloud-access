package zju.edu.als.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zzq on 16/8/26.
 */
public class DateFormatUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.ENGLISH);
    public static String format(long ms){
        return dateFormat.format(new Date(ms));
    }
    public static long parse(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr).getTime();
    }
}
