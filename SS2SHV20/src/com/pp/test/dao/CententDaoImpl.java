package com.pp.test.dao;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Centent;

public class CententDaoImpl extends BaseDaoImpl<Centent,Integer> implements CententDao {
	public void AddCentent(Centent cen) throws Exception {
		this.save(cen);
	}
}