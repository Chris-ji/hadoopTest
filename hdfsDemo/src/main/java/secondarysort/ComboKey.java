package secondarysort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义key
 * Created by Chris on 2019/1/5.
 */
public class ComboKey implements WritableComparable<ComboKey> {
    private  int year ;
    private  int temp;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * 对key进行比较实现
     * @param o
     * @return
     */
    public int compareTo(ComboKey o) {
        int y0 = o.getYear();
        int t0 = o.getTemp();
        //年份相同（升序）
        if(year == y0){
            return  -(temp - t0);
        }else{
            //气温降序
            return  year - y0 ;
        }

    }

    /**
     * 串行过程
     * @param dataOutput
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.write(year);
        dataOutput.write(temp);
    }

    /**
     * 反串行
     * @param dataInput
     * @throws IOException
     */
    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        temp = dataInput.readInt();

    }
}
