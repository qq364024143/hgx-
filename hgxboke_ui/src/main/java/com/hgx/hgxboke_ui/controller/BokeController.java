package com.hgx.hgxboke_ui.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgx.hgxboke_ui.commons.HTMLEncodeUtils;
import com.hgx.hgxboke_ui.commons.RedisUtil;
import com.hgx.hgxboke_ui.feign.BokeFeign;
import com.hgx.hgxboke_ui.feign.CommentFeign;
import com.hgx.my_boke_api.boke.entity.BokeClassVo;
import com.hgx.my_boke_api.boke.entity.BokeVo;
import com.hgx.my_boke_api.boke.entity.MarkBokeVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;

@Controller
public class BokeController {

	private Logger log = LoggerFactory.getLogger(BokeController.class);

	@Autowired
	private BokeFeign bokeFeign;

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private CommentFeign commentFeign;

	@RequestMapping("/")
	public String homePage(){
		log.info("访问欢迎页跳转到主页");
		return "redirect:index";
	}

	/**
	 * 关于我
	 * @return
	 */
	@RequestMapping("about_me")
	public String aboutMe(){
		log.info("访问《关于我》页面");
		return "about_me";
	}

	/**
	 * 主页,查询出最新的10条博客
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		log.info("访问主页面");


		//获取最新的20条博客
		List<BokeVo> recentBokes = new ArrayList<BokeVo>();
		ResponseResult<List<BokeVo>> result = bokeFeign.findRecentBokes();
		if("200".equals(result.getCode())){
			recentBokes = result.getData();
		}
		request.setAttribute("newBokes", recentBokes);

		//获取随即推荐的10个博客
		List<BokeVo>  randomBokes = new ArrayList<BokeVo>();
		ResponseResult<List<BokeVo>> randomBokesResult = bokeFeign.findRandomBokes();
		if("200".equals(randomBokesResult.getCode())){
			randomBokes  = randomBokesResult.getData();
		}
		request.setAttribute("randomBokes", randomBokes);

		//获取博客分类
		request.setAttribute("bokeClasses", findBokeClass());
		return "index";
	}


	/**
	 * 博客列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String pageList(HttpServletRequest request,BokeVo bokeVo){
		log.info("访问博客列表页面");

		//获取随即推荐的10个博客
		List<BokeVo>  randomBokes = new ArrayList<BokeVo>();
		ResponseResult<List<BokeVo>> randomBokesResult = bokeFeign.findRandomBokes();
		if("200".equals(randomBokesResult.getCode())){
			randomBokes  = randomBokesResult.getData();
		}
		request.setAttribute("randomBokes", randomBokes);

		//获取博客分类
		request.setAttribute("bokeClasses", findBokeClass());

		//统计总条数
		ResponseResult<Integer> result = bokeFeign.countBokes(bokeVo);
		if("200".equals(result.getCode())){
			request.setAttribute("counts", result.getData());
			//页面回显参数需要html转义
			bokeVo.setBokeKeyword(HTMLEncodeUtils.encodeHTML(bokeVo.getBokeKeyword()));
			log.info("博客关键字查询时转义后的参数为："+bokeVo.getBokeKeyword());
			request.setAttribute("bokeParam", bokeVo);
			return "list";
		}else{
			return "error";
		}
	}

	/**
	 * 收藏记录页面
	 * @return
	 */
	@RequestMapping("mark")
	public String bookMarkListPage(MarkBokeVo mark,HttpServletRequest request){
		log.info("访问收藏页面");

		//获取随即推荐的10个博客
		List<BokeVo>  randomBokes = new ArrayList<BokeVo>();
		ResponseResult<List<BokeVo>> randomBokesResult = bokeFeign.findRandomBokes();
		if("200".equals(randomBokesResult.getCode())){
			randomBokes  = randomBokesResult.getData();
		}
		request.setAttribute("randomBokes", randomBokes);

		//获取博客分类
		request.setAttribute("bokeClasses", findBokeClass());

		//获取总数居条数
		ResponseResult<Integer> result = bokeFeign.countMarkBoke(mark);
		//html转义，防止XSS攻击
		mark.setMarkDescript(HTMLEncodeUtils.encodeHTML(mark.getMarkDescript()));
		log.info("转义后的参数为："+mark.getMarkDescript());
		request.setAttribute("markBokeParam", mark);
		request.setAttribute("counts", result.getData());
		return "book_mark";
	}

	/**
	 * 分页查询收藏博客列表
	 * @param markPageParams
	 * @return
	 */
	@RequestMapping("findMarkBokes")
	@ResponseBody
	public List<MarkBokeVo> findMarkBokes(@RequestBody PageParams<MarkBokeVo> markPageParams){
		//params是页面取的html转义后的参数，需要反转义再查询
		MarkBokeVo params = markPageParams.getParams();
		params.setMarkDescript(HTMLEncodeUtils.decodeHTML(params.getMarkDescript()));
		ResponseResult<List<MarkBokeVo>> result = bokeFeign.findMarkBokes(markPageParams);
		if("200".equals(result.getCode())){
			return result.getData();
		}
		return new ArrayList<MarkBokeVo>();
	}

	/**
	 * 跳转到写博客页面
	 * @return
	 */
	@RequestMapping("write")
	public String toWriteBokeJsp(HttpServletRequest request){
		request.setAttribute("bokeClasses", findBokeClass());
		log.info("访问写博客页面");
		return "write_boke";
	}

	/**
	 * 查询博客详情
	 * @param request
	 * @return
	 */
	@RequestMapping("boke_info")
	public String bokeInfo(HttpServletRequest request){
		String bokeId = request.getParameter("bokeId");
		log.info("访问博客详情页面,博客ID："+bokeId);
		ResponseResult<BokeVo> result = bokeFeign.findBokeByBokeId(bokeId);
		if("200".equals(result.getCode())&&result.getData()!=null){
			//博客详情
			request.setAttribute("boke", result.getData());
			//分类
			request.setAttribute("bokeClasses", findBokeClass());
			//相关博客
			ResponseResult<List<BokeVo>> relateBokesResult = bokeFeign.getRelateBokes(result.getData().getBokeTitle(),result.getData().getBokeId());
			if("200".equals(relateBokesResult.getCode())){
				request.setAttribute("relateBokes", relateBokesResult.getData());
			}else{
				request.setAttribute("relateBokes", new ArrayList<BokeVo>());
			}
			
			ResponseResult<Integer> bokeCommentTotalResult = commentFeign.countBokeCommentTotal(bokeId);
			request.setAttribute("bokeCommentTotal", bokeCommentTotalResult.getData());
			
			return "info";
		}else{
			return "404";//为了抛出404让 spring boot处理，这里return一个不存在的视图
		}

	}

	/**
	 * 保存博客
	 * @param bokeVo
	 * @param request
	 * @return
	 */
	@RequestMapping("saveBoke")
	public String saveBoke(BokeVo bokeVo,HttpServletRequest request){
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		bokeVo.setBokeCreateTime(fmt.format(d));
		bokeVo.setBokeId(UUID.randomUUID().toString().replaceAll("-", ""));
		bokeVo.setBokeUpdateTime(fmt.format(d));
		//bokeVo.setNickname(((UserVo)request.getSession().getAttribute("user")).getNickname());
		//bokeVo.setUserId(((UserVo)request.getSession().getAttribute("user")).getNickname());
		ResponseResult<Integer> result = bokeFeign.saveBoke(bokeVo);
		if("200".equals(result.getCode())){
			request.setAttribute("look", "查看博客详情");
			request.setAttribute("lookUrl", "boke_info?bokeId="+bokeVo.getBokeId());
			request.setAttribute("addagain", "继续写博客");
			request.setAttribute("addagainUrl", "write");
			return "success";
		}else{
			return "fail";
		}

	}

	/**
	 * 根据分页查找博客列表
	 * @param bokeClassId
	 * @return
	 */
	@RequestMapping("findBokes")
	@ResponseBody
	public List<BokeVo> findBokes(@RequestBody PageParams<BokeVo> bokePageParam){
		//param是页面取的HTML转义后的值，需要反转义后再查询
		BokeVo params = bokePageParam.getParams();
		params.setBokeKeyword(HTMLEncodeUtils.decodeHTML(params.getBokeKeyword()));
		ResponseResult<PageResult<BokeVo>> result = bokeFeign.findBokes(bokePageParam);
		if("200".equals(result.getCode())){
			return result.getData().getData();
		}else{
			return new ArrayList<BokeVo>();
		}
	}

	/**
	 * 跳转到添加收藏博客页面
	 * @return
	 */
	@RequestMapping("addBokeMark")
	public String addBokeMark(){
		return "add_boke_mark";
	}

	/**
	 * 获取当前博客的上一条和下一条
	 * @param bokeNums
	 * @return
	 */
	@RequestMapping("getLastAndNextBoke")
	@ResponseBody
	public Map<String,BokeVo> getLastAndNextBoke(@RequestParam("bokeNums")String bokeNums){
		ResponseResult<Map<String,BokeVo>> result = bokeFeign.getLastAndNextBoke(bokeNums);
		return result.getData();

	}


	/**
	 * 获取博客分类
	 * @return
	 */
	private List<BokeClassVo> findBokeClass(){
		//获取博客分类，加入redis缓存
		List<BokeClassVo> bokeClasses = new ArrayList<BokeClassVo>();
		if(redisUtil.exists("bokeClasses")){
			//存在缓存中
			bokeClasses = (List<BokeClassVo>) redisUtil.get("bokeClasses");
		}else{
			//不存在缓存
			ResponseResult<List<BokeClassVo>> bokeClassesResult = bokeFeign.findBokeClasses();
			if("200".equals(bokeClassesResult.getCode())){
				bokeClasses = bokeClassesResult.getData();
				//加入缓存
				redisUtil.set("bokeClasses", bokeClasses);
			}
		}
		return bokeClasses;
	}

	/**
	 * 添加收藏博客
	 * @param mark
	 * @return
	 */
	@RequestMapping("saveMarkBoke")
	public String saveMarkBoke(MarkBokeVo mark,HttpServletRequest request){
		mark.setMarkId(UUID.randomUUID().toString().replace("-", ""));
		ResponseResult<Integer> result = bokeFeign.saveMarkBoke(mark);
		if("200".equals(result.getCode())){
			request.setAttribute("look", "回到收藏列表");
			request.setAttribute("lookUrl", "mark");
			request.setAttribute("addagain", "继续添加收藏");
			request.setAttribute("addagainUrl", "addBokeMark");
			return "success";
		}else{
			return "fail";
		}
	}
}
