package com.lemi.msloan.service;

import java.util.List;

public interface BaseService<T>{
	  /**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */
    public T get(Integer id);

    /**
     * 根据属性跟值获取对象集合
     * 
     */
    public List<T> selectListByEntity(T entity);
    
    
    /**
     * 保存实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public Integer save(T entity);

    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public void update(T entity);

    /**
     * 根据ID删除实体对象.
     *
     * @param id 记录ID
     */
    public void delete(Integer id);

    /**
     * 根据ID数组删除实体对象.
     *
     * @param ids ID数组
     */
    public void delete(String[] ids);


    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public void updateByPrimaryKeySelective(T entity);
    
}
