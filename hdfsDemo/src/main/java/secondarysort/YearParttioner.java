package secondarysort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区类
 * Created by Chris on 2019/1/5.
 */
public class YearParttioner extends Partitioner<ComboKey,NullWritable>{

    public int getPartition(ComboKey comboKey, NullWritable nullWritable, int i) {
       int year = comboKey.getYear();
        return year %  i ;//年份进入自定义分区
    }
}
