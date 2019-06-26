import java.util.Calendar;
import java.util.Date;

public class functions {

    public String format_time(Date hora) {

        Calendar cal = Calendar.getInstance();
        cal.set(2018, 06, 26, 19,45,0);
        cal.add(Calendar.HOUR_OF_DAY, 24);

        return String.valueOf(cal);

    }

}
