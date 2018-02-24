package com.lidongmin.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lidongmin.IDao.ProductMapper;
import com.lidongmin.IDao.UserMapper;
import com.lidongmin.domain.Product;
import com.lidongmin.domain.User;

@Service
public class MyService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProductMapper productMapper;
	
	public boolean login(User user) {
		
		int login = userMapper.login(user);
		if(login > 0){
			return true;
		}
		return false;
	}

	public List<Product> findAll() {
		return productMapper.findAll();
	}

	public List<Product> findAll(String find) {
		return productMapper.findAll(find);
	}

	public int add(Product p) {
		return productMapper.insert(p);
	}

	public Product selectById(String id) {
		return productMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	public int update(Product p) {
		return productMapper.updateByPrimaryKey(p);
	}

	public int update2(Product p) {
		return productMapper.updateByPrimaryKey2(p);
	}

	public void del(String id) {
		productMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}
	
	public List<Product> getMall(String ids) {
		return productMapper.getMall(ids);
	}

	public List<Product> getMall2(HttpServletRequest request, String ids) {
		List<Product> mall = productMapper.getMall(ids);
		Iterator<Product> iterator = mall.iterator();
		BigDecimal money = new BigDecimal("0.0");
		while(iterator.hasNext()){
			Product p = iterator.next();
			money = money.add(p.getPrice());
			System.out.println(money.doubleValue());
		}
		request.setAttribute("pay", money);
		return mall;
	}

	public List<Product> getProduct(HttpServletRequest request, String id) {
		List<Product> findById = productMapper.findById(id);
		Product p = findById.get(0);
		BigDecimal price = p.getPrice();
		request.setAttribute("pay", price);
		return findById;
	}
}
