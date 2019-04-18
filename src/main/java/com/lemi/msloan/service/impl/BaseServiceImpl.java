package com.lemi.msloan.service.impl;


import com.lemi.msloan.dao.BaseDao;
import com.lemi.msloan.service.BaseService;

import java.util.List;


public class BaseServiceImpl<T>implements BaseService<T> {
    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
	public T get(Integer id) {
		return baseDao.get(id);
	}
	
	public Integer save(T entity) {
		return baseDao.save(entity);
	}
	
	public void update(T entity) {
		baseDao.update(entity);
	}
	
	public void delete(Integer id) {
		baseDao.delete(id);
	}
	
	public void delete(String[] ids) {
		baseDao.delete(ids);
	}

	@Override
	public void updateByPrimaryKeySelective(T entity) {
		baseDao.updateByPrimaryKeySelective(entity);
	}

	public List<T> selectListByEntity(T entity) {
		return baseDao.selectListByEntity(entity);
	}
}
