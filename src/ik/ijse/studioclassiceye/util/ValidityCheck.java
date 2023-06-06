package ik.ijse.studioclassiceye.util;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidityCheck {
    public static boolean checkName( String value) {
        Pattern namePattern;
        Matcher matcher;

                namePattern = Pattern.compile("[a-zA-Z]{2,}");
                matcher = namePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
                return false;
        }
        public static boolean checkContact(String value) {
        Pattern namePattern;
        Matcher matcher;

        namePattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9,10}$");
        matcher = namePattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkCusId(String value) {
        Pattern namePattern;
        Matcher matcher;

        namePattern = Pattern.compile("^[C0][0-9]{2,}$");
        matcher = namePattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkEmpId(String value) {
        Pattern namePattern;
        Matcher matcher;

        namePattern = Pattern.compile("^[E0][0-9]{2,}$");
        matcher = namePattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkSalary(String value){
        Pattern namePattern;
        Matcher matcher;

        namePattern = Pattern.compile("^[0-9]{4,}$");
        matcher = namePattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
