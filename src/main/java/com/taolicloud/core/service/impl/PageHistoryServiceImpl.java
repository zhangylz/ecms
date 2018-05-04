/**
 *  桃李云平台版权声明<br/>
    <center>Copyright (c) 2017 www.taolicloud.com</center> 
 <center> 2018年5月3日上午11:18:00</center>
<center>贵州桃李云科技有限公司拥有本平台的所有资料（包括但不限于文字、图片、音频、视频资料及页面设计、排版、软件等）的版权和/或其他相关知识产权。</center>
<center>未经桃贵州桃李云科技有限公司事先书面许可,对本平台的任何内容、任何单位和个人不得以任何方式复制、转载、链接、转帖、引用、刊登、发表、反编译或者在非桃李云科技所属服务器上做镜像或以其他任何方式使用。</center>
<center>凡侵犯贵州桃李云科技有限公司版权等知识产权的，贵州桃李云科技有限公司将依法追究其法律责任。</center>
 */
package com.taolicloud.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.taolicloud.core.dao.PageHistoryDao;
import com.taolicloud.core.entity.PageHistory;
import com.taolicloud.core.entity.User;
import com.taolicloud.core.service.PageHistoryService;

/**
 * @author 马郡
 * @email Accpect_Majun@163.com / mj@taolicloud.com
 * @className PageHistoryServiceImpl
 * @date 2018年5月3日上午11:18:00
 * @desc [用一句话描述改文件的功能]
 */
@Service("pageHistoryService")
public class PageHistoryServiceImpl implements PageHistoryService {

	@Autowired
	private PageHistoryDao historyDao;

	@Override
	public List<PageHistory> findAll() {
		return historyDao.findAll();
	}

	@Override
	public List<PageHistory> findAll(Sort sort) {
		return historyDao.findAll(sort);
	}

	@Override
	public List<PageHistory> findAll(Iterable<Integer> ids) {
		return historyDao.findAll(ids);
	}

	@Override
	public PageHistory findById(Integer id) {
		return historyDao.findOne(id);
	}

	@Override
	public <S extends PageHistory> List<S> save(Iterable<S> entities) {
		return historyDao.save(entities);
	}

	@Override
	public <S extends PageHistory> S saveAndFlush(S entity) {
		return historyDao.saveAndFlush(entity);
	}

	@Override
	public <S extends PageHistory> void delete(S entity) {
		historyDao.delete(entity);
	}

	@Override
	public void deleteInBatch(Iterable<PageHistory> entities) {
		historyDao.deleteInBatch(entities);
	}

	@Override
	public <S extends PageHistory> List<S> findAll(Example<S> example) {
		return historyDao.findAll(example);
	}

	@Override
	public <S extends PageHistory> List<S> findAll(Example<S> example, Sort sort) {
		return historyDao.findAll(example, sort);
	}

	@Override
	public Page<PageHistory> findAll(Pageable pageable) {
		return historyDao.findAll(pageable);
	}

	@Override
	public PageHistory findByPageAndUserAndStatus(com.taolicloud.core.entity.Page page, User user, Boolean flag) {
		return historyDao.findByPageAndUserAndStatus(page, user, flag);
	}

	@Override
	public List<PageHistory> findAllByPageAndUserAndStatus(com.taolicloud.core.entity.Page page, User user, Boolean flag) {
		List<PageHistory> pageHistories = historyDao.findAll((root, query, builder) -> {

			List<Order> orders = new ArrayList<>();

			orders.add(builder.asc(root.<Long>get("id")));

			Predicate predicate = builder.conjunction();

			if (page != null && page.getId() > 0) {
				predicate.getExpressions().add(
						builder.equal(root.get("page").as(com.taolicloud.core.entity.Page.class), page));
			}
			
			if (user != null && user.getId() > 0) {
				predicate.getExpressions().add(
						builder.equal(root.get("user").as(User.class), user));
			}
			
			predicate.getExpressions().add(
					builder.equal(root.get("status").as(Boolean.class), flag));
			
			query.orderBy(orders);
			return predicate;
		});
		return pageHistories;
	}
}