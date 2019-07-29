package reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/13.
 */
public class MapJoin extends Mapper<LongWritable,Text,ComboKey,NullWritable> {

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //判断cu还是orders
        String line = value.toString();
        FileSplit split =(FileSplit)context.getInputSplit();
        String path = split.getPath().toString();
        ComboKey comboKey = new ComboKey();
        if (path.contains("customers")){
            String cid = line.substring(0,line.indexOf(","));
            String custInfo = line;
            comboKey.setType(0);
            comboKey.setCid(Integer.parseInt(cid));
            comboKey.setCustomerInfo(custInfo);

        }
        else{
            String cid = line.substring(line.lastIndexOf(",")+1);
            String oid = line.substring(0,line.indexOf(","));
            String oinfo = line.substring(0,line.lastIndexOf(","));
            comboKey.setType(1);
            comboKey.setCid(Integer.parseInt(cid));
            comboKey.setOid(Integer.parseInt(oid));
            comboKey.setOrderInfo(oinfo);

        }
        context.write(comboKey,NullWritable.get());
    }
}
