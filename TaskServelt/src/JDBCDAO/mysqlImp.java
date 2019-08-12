package JDBCDAO;

import java.sql.*;

public class mysqlImp {
//	public static void main(String[] args) {
//		mysqlImp mImp=new mysqlImp();
//		//mImp.insert("zhanghu", "dgbt", "10319");
//		mImp.testQuery("zhanghu","dgbt1");
//	}
	public boolean testQuery(String tableName,String username) {
		// 查询
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag=false;
		try {
			// 1. 获取连接对象
			conn = JDBCUtil.getConn();


			String sql = "select * from "+tableName+" where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			// 5.遍历每一条记录特殊模式判断是否空集
			if(rs.next()) {
				do {
					flag=true;
					int id=rs.getInt("id");
					String name=rs.getString("username");
					String password=rs.getString("password");
					// int age=rs.getInt("age");
					// String brith=rs.getString("brith");
					// String description=rs.getString("description");
					System.out.println("id="+id+",username="+name+",password="+password);
					System.out.flush();
				} while(rs.next());
			}


			//  遍历结果集
			if(flag) {
				System.out.println("用户名已存在,注册失败");
				System.out.flush();

				return true;
			}
			else {
				System.out.println("用户名不存在，");
				System.out.flush();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
		return false;
	}
	public boolean testQueryLog(String tableName,String username,String password) {
		// 查询
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag=false;
		try {
			// 1. 获取连接对象
			conn = JDBCUtil.getConn();


			String sql = "select * from "+tableName+" where username= ? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			// 5.遍历每一条记录特殊模式判断是否空集
			if(rs.next()) {
				do {
					flag=true;
					int id=rs.getInt("id");
					String name=rs.getString("username");
					String pwd=rs.getString("password");
					// int age=rs.getInt("age");
					// String brith=rs.getString("brith");
					// String description=rs.getString("description");
					System.out.println("id="+id+",username="+name+",password="+pwd);
					System.out.flush();
				} while(rs.next());
			}


			//  遍历结果集
			if(flag) {
				System.out.println("登陆成功！");
				System.out.flush();

				return true;
			}
			else {
				System.out.println("登录失败");
				System.out.flush();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
		return false;
	}
	public boolean insert(String tableName,String username,String password) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int rs ;
		try {
			conn = JDBCUtil.getConn();
			//3.创建PrepareStatement
			//			String sql = "insert into ? values(?,?)";
			//			ps = conn.prepareStatement(sql);
			//			ps.setString(1, tableName);
			//			ps.setString(2, username);
			//			ps.setString(3, password);
			String sql = "insert into "+tableName+" values(null,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs=ps.executeUpdate();

			//5.判断
			if (rs>0) {
				System.out.println("添加成功");
				System.out.flush();
				return true;
			}
			else {
				System.out.println("添加失败");
				System.out.flush();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);

		}
		return false;

	}
	public void testDelete(){

		// 查询
		Connection conn = null;
		Statement st = null;
		try {
			// 1. 获取连接对象
			conn = JDBCUtil.getConn();
			// 2. 根据连接对象，得到statement
			st = conn.createStatement();

			//3. 执行添加
			String sql = "delete from t_stu where name='aobama'";
			//影响的行数， ，如果大于0 表明操作成功。 否则失败
			int result = st.executeUpdate(sql);

			if(result >0 ){
				System.out.println("删除成功");
			}else{
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(conn, st);
		}

	}
	public void testUpdate(){

		// 查询
		Connection conn = null;
		Statement st = null;
		try {
			// 1. 获取连接对象
			conn = JDBCUtil.getConn();
			// 2. 根据连接对象，得到statement
			st = conn.createStatement();

			//3. 执行添加
			String sql = "update t_stu set age = 26 where name ='qyq'";
			//影响的行数， ，如果大于0 表明操作成功。 否则失败
			int result = st.executeUpdate(sql);

			if(result >0 ){
				System.out.println("更新成功");
			}else{
				System.out.println("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.release(conn, st);
		}

	}
}
