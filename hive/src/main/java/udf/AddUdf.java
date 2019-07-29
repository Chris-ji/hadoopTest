package udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by Chris on 2019/1/19.
 */
@Description(name = "myadd",value = "myadd(int a,int b) ==> return a+b",extended = "Example :\n"+"myadd (1,1)==> 2 \n"
+"add (1,2,3) ==> 6;"
)
public class AddUdf extends UDF{
    public int evaluate(int a , int b){

        return a + b;
    }
    public int evaluate(int a , int b, int c){

        return a + b + c;
    }
}
