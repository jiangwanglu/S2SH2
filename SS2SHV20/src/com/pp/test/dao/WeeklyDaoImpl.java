package com.pp.test.dao;
import java.util.List;
import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.RunTimeDate;
public class WeeklyDaoImpl extends BaseDaoImpl<Dianlang,Integer>  implements WeeklyDao{
	//抓取到今天的数据
	public  List<Dianlang> Weekly(String date) throws Exception {
		
		String strsql ="from Dianlang where date = ?";
		Object[]values=new String[]{date};
		return (List<Dianlang>) this.find(strsql,values);
	}
	
	//抓取到1号和周一的数据
	public List<Dianlang> weeklydao(String str) throws Exception {
		String strsql = "from Dianlang where date = ?";
		Object[]values=new String[]{str};
		return (List<Dianlang>) this.find(strsql,values);
	}
}