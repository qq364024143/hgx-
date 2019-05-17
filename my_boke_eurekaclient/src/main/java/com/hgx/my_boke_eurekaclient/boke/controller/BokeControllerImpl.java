package com.hgx.my_boke_eurekaclient.boke.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgx.my_boke_api.boke.controller.BokeController;
import com.hgx.my_boke_api.boke.entity.BokeClassVo;
import com.hgx.my_boke_api.boke.entity.BokeVo;
import com.hgx.my_boke_api.boke.entity.MarkBokeVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;
import com.hgx.my_boke_eurekaclient.boke.service.BokeService;

@RestController
public class BokeControllerImpl implements BokeController{
	
	private Logger log = LoggerFactory.getLogger(BokeControllerImpl.class);
	
	@Autowired
	private BokeService bokeService;
	
	/**
	 * 获取最新 10条博客
	 */
	@Override
	public ResponseResult<List<BokeVo>> findRecentBokes() {
		log.info("============查询最新10条博客===================");
		ResponseResult<List<BokeVo>> result = new ResponseResult<List<BokeVo>>();
		try{
			List<BokeVo> bokes = bokeService.findRecentBokes();
			result.setCode("200");
			result.setData(bokes);
			result.setMessage("获取最新10条博客成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("获取最新10条博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 查询随机推荐的10个博客
	 */
	@Override
	public ResponseResult<List<BokeVo>> findRandomBokes() {
		log.info("======================查询随机推荐的10条博客======================");
		ResponseResult<List<BokeVo>> result = new ResponseResult<List<BokeVo>>();
		try{
			List<BokeVo> bokes = bokeService.findRandomBokes();
			result.setCode("200");
			result.setData(bokes);
			result.setMessage("获取随机推荐的10个博客成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("获取最新10条博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 查询博客分类
	 */
	@Override
	public ResponseResult<List<BokeClassVo>> findBokeClasses() {
		log.info("================查询博客分类====================");
		ResponseResult<List<BokeClassVo>> result = new ResponseResult<List<BokeClassVo>>();
		
		try{
			List<BokeClassVo> bokeClasses =  bokeService.findBokeClasses();
			result.setCode("200");
			result.setData(bokeClasses);
			result.setMessage("查询博客分类成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("==========查询博客分类失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 通过博客ID查询博客详情
	 */
	@Override
	public ResponseResult<BokeVo> findBokeByBokeId(@RequestParam("bokeId") String bokeId) {
		log.info("通过博客ID查询博客详情，博客ID为["+bokeId+"]");
		ResponseResult<BokeVo>result = new ResponseResult<BokeVo>();
		try{
			BokeVo boke = bokeService.findBokeByBokeId(bokeId);
			log.info("查询出的博客为："+JSONObject.toJSONString(boke));
			result.setCode("200");
			result.setMessage("通过博客ID查询博客详情成功");
			result.setData(boke);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("通过博客ID查询博客详情失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
		
	}

	/**
	 * 新增博客
	 */
	@Override
	public ResponseResult<Integer> saveBoke(@RequestBody BokeVo bokeVo) {
		log.info("新增博客："+JSONObject.toJSONString(bokeVo));
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		try{
			int b = bokeService.saveBoke(bokeVo);
			if(b>0){
				result.setCode("200");
				result.setMessage("新增博客成功");
				result.setData(b);
			}else{
				result.setCode("500");
				result.setMessage("新增博客失败");
				result.setData(b);
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("新增博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		log.info("新增博客响应结果："+JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 查询博客列表
	 * @param bokePageParam
	 * @return
	 */
	@Override
	public ResponseResult<PageResult<BokeVo>> findBokes(@RequestBody PageParams<BokeVo> bokePageParam){
		
		log.info("查询博客列表，参数为："+JSONObject.toJSONString(bokePageParam));
		ResponseResult<PageResult<BokeVo>> result = new ResponseResult<PageResult<BokeVo>>();
		PageResult<BokeVo> pageResult = new PageResult<BokeVo>();
		try{
			//pagehelper分页参数设置
			PageHelper.startPage(bokePageParam.getCpage(), bokePageParam.getPageSize());
			List<BokeVo> bokeVos = bokeService.findBokes(bokePageParam.getParams());
			PageInfo<BokeVo> pageInfo = new PageInfo<BokeVo>(bokeVos);
			pageResult.setCpage(pageInfo.getPageNum());
			pageResult.setPageSize(pageInfo.getPageSize());
			pageResult.setTotal(pageInfo.getTotal());
			pageResult.setData(pageInfo.getList());
			
			result.setCode("200");
			result.setMessage("查询博客列表成功");
			result.setData(pageResult);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("查询博客列表失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		
		return result;
	}

	/**
	 * 统计博客总数
	 */
	@Override
	public ResponseResult<Integer> countBokes(@RequestBody(required=false) BokeVo bokeVo) {
		log.info("统计博客总数，参数为："+JSONObject.toJSONString(bokeVo));
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		try{
			int counts = bokeService.countBokes(bokeVo);
			result.setCode("200");
			result.setMessage("统计博客总数成功");
			result.setData(counts);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("统计博客总数失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 查找当前博客的上一条和下一条
	 */
	@Override
	public ResponseResult<Map<String, BokeVo>> getLastAndNextBoke(
			@RequestParam("bokeNums")String bokeNums) {
		log.info("查询上一条和下一条博客，bokeNums:"+bokeNums);
		ResponseResult<Map<String,BokeVo>> result = new ResponseResult<Map<String,BokeVo>>();
		Map<String,BokeVo> map = new HashMap<String,BokeVo>();
		try{
			BokeVo last = bokeService.getLastAndNextBoke(bokeNums, "last");
			BokeVo next = bokeService.getLastAndNextBoke(bokeNums, "next");
			map.put("last", last);
			map.put("next", next);
			result.setData(map);
			result.setCode("200");
			result.setMessage("查询上一条和下一条博客成功");
		}catch(Exception e){
			log.info("查询上一条和下一条博客，bokeNums:"+bokeNums+"失败。异常原因："+ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("查询上一条和下一条博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 统计收藏博客数
	 */
	@Override
	public ResponseResult<Integer> countMarkBoke(@RequestBody MarkBokeVo mark) {
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		log.info("统计收藏博客数，参数："+JSONObject.toJSONString(mark));
		try{
			int count = bokeService.countMarkBoke(mark);
			result.setCode("200");
			result.setData(count);
			result.setMessage("查询统计收藏博客数成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("统计收藏博客数失败，原因："+ExceptionUtils.getStackTrace(e));
			log.info("统计收藏博客数失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 查询博客收藏列表,分页
	 */
	@Override
	public ResponseResult<List<MarkBokeVo>> findMarkBokes(@RequestBody PageParams<MarkBokeVo> markPageParams) {
		log.info("查询收藏博客列表，参数为："+JSONObject.toJSONString(markPageParams));
		ResponseResult<List<MarkBokeVo>> result = new ResponseResult<List<MarkBokeVo>>();
		try{
			//设置分页
			PageHelper.startPage(markPageParams.getCpage(), markPageParams.getPageSize());
			List<MarkBokeVo> markBokes = bokeService.findMarkBokes(markPageParams.getParams());
			//获取分页数据
			PageInfo<MarkBokeVo> pageInfo = new PageInfo<MarkBokeVo>(markBokes);
			result.setCode("200");
			result.setData(pageInfo.getList());
			result.setMessage("查询收藏博客列表成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("查询收藏博客列表失败，原因"+ExceptionUtils.getStackTrace(e));
			log.info("查询收藏博客列表失败，原因"+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 添加收藏博客
	 */
	@Override
	public ResponseResult<Integer> saveMarkBoke(@RequestBody MarkBokeVo mark) {
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		log.info("添加收藏博客，参数为："+JSONObject.toJSONString(mark));
		try{
			int b = bokeService.saveMarkBoke(mark);
			result.setCode("200");
			result.setData(b);
			result.setMessage("添加收藏博客成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("添加收藏博客失败，原因："+ExceptionUtils.getStackTrace(e));
			log.info("添加收藏博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 查询当前博客的10个相关博客
	 */
	@Override
	public ResponseResult<List<BokeVo>> getRelateBokes(@RequestParam("title")String title,@RequestParam("bokeId")String bokeId) {
		log.info("查询当前博客相关的10个博客，参数为："+title);
		ResponseResult<List<BokeVo>> result = new ResponseResult<List<BokeVo>>();
		try{
			List<BokeVo> bokes = bokeService.getRelateBokes(title,bokeId);
			result.setCode("200");
			result.setData(bokes);
			result.setMessage("查询当前"+title+"博客的10个相关博客成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("查询当前博客的10个相关博客失败，原因："+ExceptionUtils.getStackTrace(e));
			log.info("查询当前博客的10个相关博客失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
	

}
