package com.bryantchang.autodepsys.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.bryantchang.autodepsys.bean.HadoopSettings;
import com.bryantchang.autodepsys.bean.SparkNode;
import com.bryantchang.autodepsys.bean.User;
import com.bryantchang.autodepsys.common.BaseConnection;
import com.mysql.jdbc.UpdatableResultSet;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author bryantchang
 * 数据库基础操作
 */

public class BaseDAO {


	private static Logger logger = Logger.getLogger(BaseDAO.class);
	/**
	 * 获取select语句
	 * @param cl 实体类对象
	 * @param conditions 筛选条件
	 * @return String select Sql语句
	 */
	private String getSelectSql(Class cl, Map<String, Object> conditions) {
		String sql = "";
		if(conditions != null) {
			String baseSqlStr = "select * from " + cl.getSimpleName().toLowerCase() + " where ";
			String conditionStr = "";
			Set keys = conditions.keySet();
			Iterator iterator = keys.iterator();
			while(iterator.hasNext()) {
				String key = (String) iterator.next();

				if (conditions.get(key).getClass().getSimpleName().equals("String") && conditions.get(key).toString().contains(",")) {
					if (iterator.hasNext()) {
						conditionStr += key + " in " + "(" + conditions.get(key) + ")" + " and ";
					}else {
						conditionStr += key + " in " + "(" + conditions.get(key) + ")";
					}
				}else {
					if (iterator.hasNext()) {
						conditionStr += key + "=" + "'" + conditions.get(key) + "'" + " and ";
					} else {
						conditionStr += key + "=" + "'" + conditions.get(key) + "'";
					}
				}
				
			}
			sql = baseSqlStr + conditionStr;
		}else {
			sql = "select * from " + cl.getSimpleName().toLowerCase();
		}
		 return sql;
	}
	
	
	/**
	 * 获取删除语句
	 * @param cl 实体类对象
	 * @param conditions 
	 * @return String delete Sql语句
	 */
	private String getDeleteSql(Class cl, Map<String, Object> conditions) {
		String sql = "";
		String baseSqlStr = "";
		String conditionStr = "";
		if(conditions == null) {
			baseSqlStr = "delete from " + cl.getSimpleName().toLowerCase();
			conditionStr = "";
		}else {
			baseSqlStr = "delete from " + cl.getSimpleName().toLowerCase() + " where ";
			Set keys = conditions.keySet();
			Iterator iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (conditions.get(key).getClass().getSimpleName().equals("String") && conditions.get(key).toString().contains(",")) {
					if (iterator.hasNext()) {
						conditionStr += key + " in " + "(" + conditions.get(key) + ")" + " and ";
					}else {
						conditionStr += key + " in " + "(" + conditions.get(key) + ")";
					}
				}else {
					if (iterator.hasNext()) {
						conditionStr += key + "=" + "'" + conditions.get(key) + "'" + " and ";
					} else {
						conditionStr += key + "=" + "'" + conditions.get(key) + "'";
					}
				}
			}
		}
		sql = baseSqlStr + conditionStr;
		return sql;
	}
	
	/**
	 * 通过实体类对象获得insert语句
	 * @param object
	 * @return
	 */
	private String getInsertPreparedSql(Object object) {
		String sql = "";
		Class cl = object.getClass();
		Field[] fields = cl.getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into ");
		buffer.append(cl.getSimpleName().toLowerCase());
		buffer.append(" (");
		for(int i = 0;i < fields.length; i++){
			if(fields[i].getName().toLowerCase().equals("id")) {
				continue;
			}
			buffer.append(fields[i].getName().toLowerCase());
			if(i != fields.length - 1){
				buffer.append(" , ");
			}
		}
		buffer.append(") values (");
		for(int i = 0;i < fields.length; i++){
			if(fields[i].getName().toLowerCase().equals("id")) {
				continue;
			}
			buffer.append(" ? ");
			if(i != fields.length-1){
				buffer.append(" , ");
			}
		}
		buffer.append(" ) ");
		return buffer.toString();
	}
	
	/**
	 * 通过实体类对象获得update语句
	 * @param object 实体类对象
	 * @return
	 */
	private String getUpdatePreparedSql(Object object) {
		String sql = "";
		Class cl = object.getClass();
		Field[] fields = cl.getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append(" update ");
		buffer.append(cl.getSimpleName().toLowerCase());
		buffer.append(" set ");
		
		for(int i = 1; i<fields.length; i++){
			fields[i].setAccessible(true);
			buffer.append(fields[i].getName().toLowerCase());
			buffer.append(" = ? ");
			if(i != fields.length-1){
				buffer.append(" , ");
			}
		}
		buffer.append(" where ");
		buffer.append(fields[0].getName().toLowerCase());
		buffer.append("=?");
		return buffer.toString();
	}
	
	
	/**
	 * 
	 * @param cl
	 * @param conditions
	 * @return
	 */
	public boolean deleteData(Class cl,  Map<String, Object> conditions) {
		String sql = this.getDeleteSql(cl, conditions);
		boolean succ = false;
		Connection connection = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Field[] fields = cl.getDeclaredFields();
		try {
			ps = connection.prepareStatement(sql);
			int res = ps.executeUpdate();
			if(res > 0) {
				succ = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseConnection.closeRes(connection, ps);
		}
		return succ;
	}
	
	
	public ArrayList getList(Class cl, Map<String, Object> conditions) {
		ArrayList list = new ArrayList();
		Connection connection = BaseConnection.getConnection();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String sql = "";
		sql = this.getSelectSql(cl, conditions);
//		System.out.println(sql);
		Field[] fields = cl.getDeclaredFields();
		try {
			pStatement = connection.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Object object = cl.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);
//					System.out.println(rSet.getObject(field.getName()) + "--" + field.getName());
					field.set(object, rSet.getObject(field.getName()));
				}
				list.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseConnection.closeRes(connection, pStatement, rSet);
		}
		return list;
	}


	public boolean insert(Object object) {
		boolean succ = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = object.getClass();
		Field[] fields = cl.getDeclaredFields();
		int index = 1;
		String sql = this.getInsertPreparedSql(object);
		logger.info(sql);
//		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < fields.length; i++){
				fields[i].setAccessible(true);
				if(fields[i].getName().toLowerCase().equals("id")) {
					continue;
				}
				ps.setObject(index, fields[i].get(object));
				logger.info("index=" + index + "field=" + fields[i].get(object));
				index++;
			}
			int res = ps.executeUpdate();
			if(res > 0) {
				succ = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return succ;
	}
	
	public boolean update(Object object) {
		boolean succ = false;
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		Class cl = object.getClass();
		Field[] fields = cl.getDeclaredFields();
		String sql = this.getUpdatePreparedSql(object);
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 1; i < fields.length; i++){
				fields[i].setAccessible(true);
				ps.setObject(i, fields[i].get(object));
			}
			fields[0].setAccessible(true);
			ps.setObject(fields.length, fields[0].get(object));
			int res = ps.executeUpdate();
			if(res > 0) {
				succ = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseConnection.closeRes(conn, ps);
		}
		return succ;
	}
	
}
