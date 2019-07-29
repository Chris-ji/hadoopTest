package maxtemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/5.
 */
public class MaxTempReduce extends Reducer<IntWritable, IntWritable,IntWritable,IntWritable> {

    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max =Integer.MIN_VALUE;
            for (IntWritable iw : values){
                max =max > iw.get() ? max : iw.get();
            }
            context.write(key, new IntWritable(max));
    }
}
