package hsfs.mr.mysql;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by Chris on 2019/1/6.
 */
public class WCMapper extends Mapper<LongWritable,MyDBWritable,Text,IntWritable> {

    protected void map(LongWritable key, MyDBWritable value, Context context) throws IOException, InterruptedException {
          /*System.out.println(key);

          System.out.println(value.getId() + "," + value.getName());*/
          String line = value.getTxt();
          String [] arr = line.split(" ");
          for (String s :arr ){
              context.write(new Text(s),new IntWritable(1));
          }

    }
}
