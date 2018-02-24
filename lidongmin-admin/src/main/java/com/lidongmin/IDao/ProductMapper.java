package com.lidongmin.IDao;

import java.util.List;

import com.lidongmin.domain.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    int updateByPrimaryKey2(Product p);

	List<Product> findAll();

	List<Product> findAll(String find);

	List<Product> getMall(String ids);

	List<Product> findById(String id);

}