/**

 *  TCMS2.0 版权声明<br/>

    <center>Copyright (c) 2018 www.taolicloud.com</center> 

 <center> 2018年1月30日</center>

<center>TCMS(Taolicloud Content Management System)的所有资料（包括但不限于文字、图片、音频、视频资料及页面设计、排版、软件等）的版权和/或其他相关知识产权。</center>

<center>未经桃李云事先书面许可,对本平台的任何内容、任何单位和个人不得以任何方式复制、转载、链接、转帖、引用、刊登、发表、反编译或者在非桃李云授权的所属服务器上做镜像或以其他任何方式使用。</center>

<center>凡侵犯桃李云版权等知识产权的，桃李云将依法追究其法律责任。</center>

 */
package com.taolicloud.core.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * @author 马郡
 * @email  Accpect_Majun@163.com / mj@taolicloud.com 
 * @className UserService
 * @date   2018年3月16日上午9:56:21
 * @desc  [用一句话描述改文件的功能]
 */
public interface SimpleService<T, ID extends Serializable> {
	
	List<T> findAll();

	List<T> findAll(Sort sort);

	List<T> findAll(Iterable<ID> ids);
	
	T findById(ID id);

	<S extends T> List<S> save(Iterable<S> entities);

	<S extends T> S saveAndFlush(S entity);

	<S extends T> void delete(S entity);
	
	void deleteInBatch(Iterable<T> entities);

	<S extends T> List<S> findAll(Example<S> example);

	<S extends T> List<S> findAll(Example<S> example, Sort sort);
	
	Page<T> findAll(Pageable pageable);
}
