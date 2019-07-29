package chainMapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by Chris on 2019/1/6.
 */
public class MyReduceMapper extends Mapper<Text,IntWritable,Text,IntWritable> {

    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
       if (value.get() > 5 ){
           context.write(key,value);
       }
    }
}
