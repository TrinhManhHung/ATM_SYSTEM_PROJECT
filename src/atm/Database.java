//Class Database thiết lập kết nối cơ sở dữ liệu 
//và thực thi các truy vấn SQL trong ứng dụng ATM.
package atm;

/**
 *
 * @author HUNG
 */
import java.sql.*;
public class Database {
    private String user = "root";
    private String pass = "";
    private String url = "jdbc:mysql://localhost/atm";
    private Statement statement; //được sử dụng để thực thi các câu lệnh SQL.
    
    //constructor kết nối với CSDL
    public Database() {
        try{
            Connection con = DriverManager.getConnection(url, user, pass);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Statement getStatement() {
        return statement;
    }
}
