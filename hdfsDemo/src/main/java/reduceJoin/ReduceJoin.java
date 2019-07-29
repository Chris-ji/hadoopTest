package reduceJoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Chris on 2019/1/13.
 */
public class ReduceJoin extends Reducer<ComboKey,NullWritable,Text,NullWritable>{

    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
        int type = key.getType();
        int cid = key.getCid();
        String customerInfo = key.getCustomerInfo();
        while (iterator.hasNext()){
            iterator.next();
            String orderInfo = key.getOrderInfo();
            context.write(new Text(customerInfo+","+ orderInfo),NullWritable.get());

        }

    }
}
