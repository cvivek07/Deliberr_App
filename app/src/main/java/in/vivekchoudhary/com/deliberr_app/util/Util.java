package in.vivekchoudhary.com.deliberr_app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by cvivek on 30-09-2018.
 */

public class Util {

    public static String getUTCTime(String dateToParse) {
        String result = "";
        if(dateToParse!=null) {
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parsed = null;
            try {
                parsed = sourceFormat.parse(dateToParse);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TimeZone tz = TimeZone.getDefault();
            SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
            destFormat.setTimeZone(tz);

            result = destFormat.format(parsed);
        }
        return result;
    }
}
