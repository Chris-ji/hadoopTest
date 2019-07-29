package hsfs.mr.mysql;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库输入格式
 * Created by Chris on 2019/1/6.
 */
public class MyDBWritable implements DBWritable,Writable {

    private int id;
    private  String name;
    private  String txt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(txt);
    }

    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        name = dataInput.readUTF();
        txt = dataInput.readUTF();
    }

    /**
     * 写入db
     * @param preparedStatement
     * @throws SQLException
     */
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,txt);
    }

    /**
     * 从db里读出
     * @param resultSet
     * @throws SQLException
     */
    public void readFields(ResultSet resultSet) throws SQLException {
         id = resultSet.getInt(1);
         name= resultSet.getString(2);
         txt = resultSet.getString(3);
    }


}
