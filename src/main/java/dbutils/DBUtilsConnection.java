package dbutils;

import bean.Goods;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DBUtils获得数据库连接
 */
public class DBUtilsConnection {

    /**
     * 数据库驱动
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * 连接数据库的url地址
     */
    private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";

    /**
     * 数据库用户名
     */
    private static final String userName = "root";

    /**
     * 数据库密码
     */
    private static final String password = "lizhipeng";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            DbUtils.loadDriver(driver);
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void query() {
        String sql = "select * from goods";
        try {
            convertGoodsList(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void convertGoodsList(String sql) throws SQLException {
        List<Goods> goodsList = new QueryRunner().query(getConnection(), sql, new ResultSetHandler<List<Goods>>() {
            @Override
            public List<Goods> handle(ResultSet resultSet) throws SQLException {
                List<Goods> list = new ArrayList<>();
                while (resultSet.next()) {
                    Goods goods = new Goods();
                    goods.setId(resultSet.getInt("id"));
                    goods.setName(resultSet.getString("name"));
                    goods.setIcon(resultSet.getString("icon"));
                    goods.setPrice(resultSet.getInt("price"));
                    list.add(goods);
                }
                return list;
            }
        });
        System.out.println(goodsList.toString());
    }

}
