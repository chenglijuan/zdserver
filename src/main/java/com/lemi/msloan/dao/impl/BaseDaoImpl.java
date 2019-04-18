package com.lemi.msloan.dao.impl;


import com.lemi.msloan.dao.BaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class BaseDaoImpl<T>  implements BaseDao<T> {
	private Class<T> entityClass;   
	private String className="";
  //  protected transient XLogger log = XLoggerFactory.getXLogger(getClass());
    
    /**
    * sql session
    */
    @Autowired
    @Qualifier("sqlSessionTemplate")
    protected transient SqlSessionTemplate sqlSessionTemplate;

    @SuppressWarnings({ "unchecked", "rawtypes" })
   	public BaseDaoImpl() {
           this.entityClass = null;
           Class c = getClass();
           Type type = c.getGenericSuperclass();
           if (type instanceof ParameterizedType) {
               Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
               this.entityClass = (Class<T>) parameterizedType[0];
               String name=entityClass.getName();
               this.className=name.substring(name.lastIndexOf(".")+1);
           }
       }

	public T get(Integer id) {
		return sqlSessionTemplate.selectOne("tbl_"+this.className.toLowerCase()+".selectByPrimaryKey", id);
	}

	public Integer save(T entity) {
		return sqlSessionTemplate.insert("tbl_"+this.className.toLowerCase()+".insertSelective",entity);
	}

	public void update(T entity) {
		sqlSessionTemplate.update("tbl_"+this.className.toLowerCase()+".updateByPrimaryKey",entity);
	}

	@Override
	public void updateByPrimaryKeySelective(T entity) {
		sqlSessionTemplate.update("tbl_"+this.className.toLowerCase()+".updateByPrimaryKeySelective",entity);
	}


	public void delete(Integer id) {
		sqlSessionTemplate.delete("tbl_"+this.className.toLowerCase()+".deleteByPrimaryKey", id);
	}

	public void delete(String[] ids) {
		for(String id:ids){
			sqlSessionTemplate.delete("tbl_"+this.className.toLowerCase()+".deleteByPrimaryKey", id);
		}
	}


	public List<T> selectListByEntity(T entity) {
		return sqlSessionTemplate.selectList("tbl_"+this.className.toLowerCase()+".selectListByEntity", entity);
	}
}
