package reduceJoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区类，按照cid分区
 * Created by Chris on 2019/1/13.
 */
public class CidPartitioner extends Partitioner<ComboKey,NullWritable> {


    public int getPartition(ComboKey comboKey, NullWritable nullWritable, int i) {
        return comboKey.getCid() % i;
    }
}
