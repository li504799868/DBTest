import bean.Goods;
import dbutils.DBUtilsConnection;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBTest {

    @Test
    public void testHibernate() {
        Session session = HibernateUtils.getSession();
        // 设置connection为自动提交
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                connection.setAutoCommit(true);
            }
        });

        Goods goods = new Goods();
        goods.setId(3);
        goods.setName("安踏");
        goods.setPrice(100);
        session.save(goods);
        session.flush();

        session.beginTransaction();
        List<Goods> result = session.createQuery("from Goods ", Goods.class).list();
        for (Goods item : result) {
            System.out.println(item.toString());
        }
        session.getTransaction().commit();

        HibernateUtils.destroy();
    }

    @Test
    public void testDBUtils() {
        DBUtilsConnection.query();
    }

}
