package secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/5.
 */
public class MaxTempReduce extends Reducer<ComboKey, NullWritable,IntWritable,IntWritable> {

    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int year = key.getYear();
        int temp = key.getTemp();
        System.out.println("============>reduce");
        for (NullWritable v :values
             ) {
            System.out.println(key.getYear() + ":" + key.getTemp());
        }
        context.write(new IntWritable(year),new IntWritable(temp));

    }

   /* protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max =Integer.MIN_VALUE;
            for (IntWritable iw : values){
                max =max > iw.get() ? max : iw.get();
            }
            context.write(key, new IntWritable(max));
    }*/
}
