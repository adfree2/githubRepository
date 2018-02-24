package com.lidongmin.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lidongmin.domain.Product;
import com.lidongmin.domain.User;
import com.lidongmin.service.MyService;

@Controller
public class MyController {
	@Autowired
	private MyService service;

	@RequestMapping("login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		User user = new User();
		user.setName(name);
		user.setPassword(password);

		boolean bl = service.login(user);
		if (bl) {
			Cookie myCookie = new Cookie("login", "true");
			myCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(myCookie);
			return "redirect:list.do";
		}
		return "../index";
	}

	@RequestMapping("list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String find = request.getParameter("find");
		if (find == null) {
			find = "";
		}
		List<Product> list = service.findAll(find);
		request.setAttribute("list", list);
		return "list";
	}

	@RequestMapping("toAdd.do")
	public String toAdd(HttpServletRequest request, HttpServletResponse response) {
		return "add";
	}

	@RequestMapping("add.do")
	@Transactional
	public String add(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String name = new String(request.getParameter("name"));

		double price = Double.valueOf(request.getParameter("price"));
		Product p = new Product();
		p.setImageUrl("images/" + file.getOriginalFilename());
		p.setName(name);
		p.setPrice(BigDecimal.valueOf(price));

		int count = service.add(p);

		if (count > 0 && !file.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/images/");
			// 上传文件名
			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			try {
				file.transferTo(new File(path + File.separator + filename));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:list.do";
		}
		return "redirect:toAdd.do";
	}

	@RequestMapping("toUpdate.do")
	public String toUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Product p = service.selectById(id);
		request.setAttribute("product", p);
		return "update";
	}

	@RequestMapping("update.do")
	@Transactional
	public String update(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		double price = Double.valueOf(request.getParameter("price"));
		Product p = new Product();
		int count = 0;
		
		if(file.isEmpty()){
			p.setId(Integer.parseInt(id));
			p.setName(name);
			p.setPrice(BigDecimal.valueOf(price));
			
			count = service.update2(p);
			
			if(count>0){
				return "redirect:list.do";
			}else{
				return "redirect:toUpdate.do";
			}
		}else{
			p.setId(Integer.parseInt(id));
			p.setImageUrl("images/" + file.getOriginalFilename());
			p.setName(name);
			p.setPrice(BigDecimal.valueOf(price));
			
			count = service.update(p);
			
			if (count > 0) {
				// 上传文件路径
				String path = request.getServletContext().getRealPath("/images/");
				// 上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File(path, filename);
				// 判断路径是否存在，如果不存在就创建一个
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// 将上传文件保存到一个目标文件当中
				try {
					file.transferTo(new File(path + File.separator + filename));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "redirect:list.do";
			}else{
				return "redirect:toUpdate.do";
			}
		}
	}
	
	@RequestMapping("del.do")
	public String del(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		service.del(id);
		return "redirect:list.do";
	}
	
	@RequestMapping("addToMall.do")
	@ResponseBody
	public Boolean addToMall(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		boolean flag = false;
		for(Cookie c : cookies){
			if("mall".equals(c.getName())){
				String idTemp = c.getValue();
				if(!"".equals(idTemp)){
					idTemp = idTemp+","+id;
				}else{
					idTemp = id;
				}
				c.setValue(idTemp);
				cookie = c;
				flag = true;
			}
		}
		if(!flag){
			cookie = new Cookie("mall", id);
		}
		response.addCookie(cookie);
		return true;
	}
	
	@RequestMapping("clearMall.do")
	@ResponseBody
	public Boolean clearMall(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			if("mall".equals(c.getName())){
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		return true;
	}
	
	@RequestMapping("toMall.do")
	public String toMall(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		String ids = null;
		for(Cookie c : cookies){
			if("mall".equals(c.getName())){
				ids = c.getValue();
//				System.out.println(ids);
				List<Product> list = service.getMall(ids);
				request.setAttribute("list", list);
			}
		}
		return "mall";
	}
	
	@RequestMapping("toPay.do")
	public String toPay(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		List<Product> list = service.getProduct(request, id);
		request.setAttribute("list", list);
		return "pay";
	}
	
	@RequestMapping("toMallPay.do")
	public String toMallPay(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		String ids = null;
		for(Cookie c : cookies){
			if("mall".equals(c.getName())){
				ids = c.getValue();
				List<Product> list = service.getMall2(request, ids);
				request.setAttribute("list", list);
			}
		}
		return "pay";
	}
}
