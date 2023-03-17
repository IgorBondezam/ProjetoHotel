package entities.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatarData(Date data){
        return sdf.format(data);
    }
}
