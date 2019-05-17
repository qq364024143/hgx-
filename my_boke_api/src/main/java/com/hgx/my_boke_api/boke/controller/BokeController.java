package com.hgx.my_boke_api.boke.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hgx.my_boke_api.boke.entity.BokeClassVo;
import com.hgx.my_boke_api.boke.entity.BokeVo;
import com.hgx.my_boke_api.boke.entity.MarkBokeVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;

public interface BokeController {
	/**
	 * 查询最新20条博客
	 * @return
	 */
	@RequestMapping(value="recent/boke",method=RequestMethod.GET)
	public ResponseResult<List<BokeVo>> findRecentBokes();
	
	/**
	 * 查询随机推荐 博客
	 * @return
	 */
	@RequestMapping(value="random/boke", method=RequestMethod.GET)
	public ResponseResult<List<BokeVo>> findRandomBokes();
	
	/**
	 * 查询博客分类
	 * @return
	 */
	@RequestMapping(value="bokeclass", method=RequestMethod.GET)
	public ResponseResult<List<BokeClassVo>> findBokeClasses();
	
	/**
	 * 查询博客详情,GET请求必须加RequestParam不然会强制转为POST请求
	 * @param bokeId
	 * @return
	 */
	@RequestMapping(value="findBokeByBokeId" ,method=RequestMethod.GET)
	public ResponseResult<BokeVo> findBokeByBokeId(@RequestParam("bokeId") String bokeId);
	
	/**
	 * 新增博客,使用requestBody时必须加comsumes为application/json
	 * @param bokeVo
	 * @return
	 */
	@RequestMapping(value="saveBoke",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<Integer> saveBoke(@RequestBody BokeVo bokeVo);
	
	/**
	 * 查询博客列表
	 * @param bokePageParam
	 * @return
	 */
	@RequestMapping(value="findBokes",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<PageResult<BokeVo>> findBokes(@RequestBody PageParams<BokeVo> bokePageParam);
	
	/**
	 * 统计博客总数
	 * @param bokeVo
	 * @return
	 */
	@RequestMapping(value="countBokes", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<Integer> countBokes(@RequestBody BokeVo bokeVo);
	
	/**
	 * 查询当前博客的上一条和下一条
	 * @param bokeNums
	 * @return
	 */
	@RequestMapping("getLastAndNextBoke")
	public ResponseResult<Map<String,BokeVo>> getLastAndNextBoke(@RequestParam("bokeNums")String bokeNums);
	
	/**
	 * 统计收藏博客数
	 * @param mark
	 * @return
	 */
	@RequestMapping(value="countMarkBoke",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<Integer> countMarkBoke(@RequestBody MarkBokeVo mark);
	
	/**
	 * 查询收藏博客列表
	 * @param mark
	 * @return
	 */
	@RequestMapping(value="findMarkBokes",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<List<MarkBokeVo>> findMarkBokes(@RequestBody PageParams<MarkBokeVo> markPageParams);
	
	/**
	 * 添加收藏博客
	 * @param mark
	 * @return
	 */
	@RequestMapping(value="saveMarkBoke",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<Integer> saveMarkBoke(@RequestBody MarkBokeVo mark);
	
	/**
	 * 查询当前博客10个相关博客
	 * @param title
	 * @return
	 */
	@RequestMapping(value="getRelateBokes",method=RequestMethod.POST)
	public ResponseResult<List<BokeVo>> getRelateBokes(@RequestParam("title")String title,@RequestParam("bokeId") String bokeId);
}
