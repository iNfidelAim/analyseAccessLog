package org.example.util;



import java.text.DecimalFormat;
import java.text.ParseException;

public class FloatFormatter {

        private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

        public static String toStringForamt ( float value){
        return decimalFormat.format(value);
    }

        public static float toFloatFormat (String value){
        try {
            return decimalFormat.parse(value).floatValue();
        } catch (ParseException e) {
            ErrorHandler.printStackTrace(e.getStackTrace());
            throw new RuntimeException(e.getMessage());
        }
    }

        public static float transformFormat ( float value){
        return toFloatFormat(toStringForamt(value));
    }
}

