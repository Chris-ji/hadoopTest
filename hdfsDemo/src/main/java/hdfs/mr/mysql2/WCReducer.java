package hdfs.mr.mysql2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;


/**
 * Created by Chris on 2019/1/6.
 */
public class WCReducer extends Reducer<Text,IntWritable,MyDBWritable,NullWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for(IntWritable iw: values){
            count = count + iw.get();
        }
        MyDBWritable keyout =  new MyDBWritable();
        keyout.setWord(key.toString());
        keyout.setWordCount(count);
        context.write(keyout,NullWritable.get());
    }

    /*protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
       int count = 0;
       for(IntWritable iw: values){
           count = count + iw.get();
       }
       context.write(key,new IntWritable(count));
   }*/
}
