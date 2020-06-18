package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class MybatisUtils {


		private static SqlSessionFactory sqlSessionFactory;

		public MybatisUtils() {
			super();
		}

		/*
		 * SqlSessionFactoryһ��������,Ӧ����Ӧ��ִ���ڼ䶼����.��Ӧ�������ڼ䲻Ҫ�ظ��������
		 */
		static {
			String resourse = "mybatis-config.xml";
			try {
				InputStream inputStream = Resources.getResourceAsStream(resourse);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static SqlSession getSqlSession() {
			// ��������ò������߲���Ϊfalse�����ֶ��ύ���񣬲�������Ϊtrue�����Զ��ύ����
			SqlSession sqlSession = sqlSessionFactory.openSession();
			return sqlSession;
		}
	


}
