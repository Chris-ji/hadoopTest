package data.skew;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Random;


/**
 * Created by Chris on 2019/1/6.
 */
public class RandomPartitioner extends Partitioner<Text, IntWritable> {
    public int getPartition(Text text, IntWritable intWritable, int i) {

        return new Random().nextInt(i);
    }
}
