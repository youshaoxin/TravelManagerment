package you.shaoxin.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能:
 * Time: 2019/8/12 20:18 --游菜花
**/
public class DateUtils {
    
    //日期转换为字符串方法
    public static String date2String(Date date,String patt){

        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    //字符串转化为日期方法
    public static Date string2date(String str,String patt) throws Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;


    }



}
