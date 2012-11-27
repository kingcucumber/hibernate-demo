package junit.test;

import java.util.Date;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoHibernateImpl;
import com.demo.domain.User;

public class DaoTest {

	public static void main(String[] args) {

		UserDao dao = new UserDaoHibernateImpl();
		User user = new User();
		user.setName("name");
		user.setBirthday(new Date());
		System.out.println("11111");
		dao.saveUser(user);

		user.setName("new name");
		System.out.println("22222");

		dao.updateUser(user);

		User u = dao.findUserByName(user.getName());

		System.out.println("33333");
		dao.remove(u);
	}
}
