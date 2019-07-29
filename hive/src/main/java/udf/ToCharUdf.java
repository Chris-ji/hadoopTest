package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chris on 2019/1/19.
 */
public class ToCharUdf extends UDF {
    /**
     * 取出服务器的当前系统时间
     */
    @Test
    public  String evaluate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy/MM/dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
