package chainMapper; /**
 * Created by Chris on 2019/1/2.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


//前面是输入的kv，后面是输出的kv

/**
 * map函数
 * @author Chris
 *
 */
public class MyChainMapper2 extends Mapper<Text, IntWritable, Text, IntWritable>{

    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
     if (!key.toString().equals("falungong")){
         context.write(key,value);
     }
    }
}
