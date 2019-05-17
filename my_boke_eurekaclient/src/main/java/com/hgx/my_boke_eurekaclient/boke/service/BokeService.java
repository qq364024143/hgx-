package com.hgx.my_boke_eurekaclient.boke.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hgx.my_boke_api.boke.entity.BokeClassVo;
import com.hgx.my_boke_api.boke.entity.BokeVo;
import com.hgx.my_boke_api.boke.entity.MarkBokeVo;
import com.hgx.my_boke_eurekaclient.boke.dao.BokeDao;

@Transactional
@Service
public class BokeService {
	
	private Logger log = LoggerFactory.getLogger(BokeService.class);
	
	@Autowired
	private BokeDao bokeDao;

	/**
	 * 查询出最新的10条博客
	 * @return
	 */
	public List<BokeVo> findRecentBokes(){
		return bokeDao.findRecentBokes();
	}

	/**
	 * 查询随机推荐博客
	 * @return
	 */
	public List<BokeVo> findRandomBokes(){
		return bokeDao.findRandomBokes();
	}

	/**
	 * 查询博客分类
	 * @return
	 */
	public List<BokeClassVo> findBokeClasses(){
		return bokeDao.findBokeClasses();
	}

	/**
	 * 通过博客ID查询博客详情
	 * @param bokeId
	 * @return
	 */
	public BokeVo findBokeByBokeId(String bokeId){
		return bokeDao.findBokeByBokeId(bokeId);
	}

	/**
	 * 新增博客
	 * @param bokeVo
	 * @return
	 */
	public int saveBoke(BokeVo bokeVo){
		return bokeDao.saveBoke(bokeVo);
	}

	/**
	 * 查询博客列表
	 * @param bokeVo
	 * @return
	 */
	public List<BokeVo> findBokes(BokeVo bokeVo){
		
		if(bokeVo==null){
			bokeVo = new BokeVo();
		}
		
		if(bokeVo.getBokeKeyword()!=null&&!"".equals(bokeVo.getBokeKeyword())){//关键字搜索
			//HanLP分词器进行关键字分词
			List<String> keywordList = HanLP.extractKeyword(bokeVo.getBokeKeyword(),5);
			bokeVo.setIkSplitKeywords(keywordList);
			StringBuilder sb = new StringBuilder();
			for(String key:keywordList){
				sb.append("*");
				sb.append(key);
				sb.append("* ");
			}
			bokeVo.setMysqlFullTextIndexs(sb.toString().trim());
			List<BokeVo> bokeVos = bokeDao.findBokes(bokeVo);
			//替换text和title
			for(BokeVo boke:bokeVos){
				boke.setBokeText(subAndReplaceHeightLight(boke.getBokeText(),keywordList,200));
				boke.setBokeTitle(subAndReplaceHeightLight(boke.getBokeTitle(),keywordList,200));
			}
			return bokeVos;
		}else{//分类搜索或查所有
			return bokeDao.findBokes(bokeVo);
		}
	}


	//截取字符串与替换字符串，按关键字最多的地方截取并替换高亮 
	private String subAndReplaceHeightLight(String source,List<String> keywords,int subLength){
		//替换boke_text
		if(source!=null){
			int moveLength = source.length()-subLength;
			String replaceStr = "";
			if(moveLength<=0){
				replaceStr = source;
			}else{
				//key为包含关键字个数，value为截取的字符串
				Map<Integer,String> map = new HashMap<Integer,String>();
				for(int i=0; i<moveLength; i++){
					int count = 0;
					String substr = source.substring(i, subLength+i);
					for(String key:keywords){//忽略大小写
						if(substr.toUpperCase().contains(key.toUpperCase())){
							count++;
						}
					}
					map.put(count, substr);
				}
				//获取到匹配关键字最多的字符串
				Set<Integer> keySet = map.keySet();
				Iterator<Integer> it = keySet.iterator();
				if(it.hasNext()){
					Integer max = it.next();
					while(it.hasNext()){
						Integer next = it.next();
						if(max<next){
							max = next;
						}
					}
					replaceStr = map.get(max);
				}
			}
			//取到需要替换的字符串后替换关键字高亮
			for(String key:keywords){
				replaceStr = IgnoreCaseReplace(replaceStr,key);
			}
			return replaceStr;
		}
		return null;
	}

	/**
	 * 不区分大小写高亮替换
	 * @param source
	 * @param patternstring
	 * @return
	 */
	private String IgnoreCaseReplace(String source, String patternstring) {
		Pattern p = Pattern.compile(patternstring, Pattern.CASE_INSENSITIVE);
		Matcher mc = p.matcher(source);
		StringBuffer sb = new StringBuffer();
		while (mc.find()) {
			mc.appendReplacement(sb, "<font color='red' >"+mc.group()+"</font>");
		}
		mc.appendTail(sb);
		return sb.toString();
	}


	/**
	 * 统计博客总数
	 * @param bokeVo
	 * @return
	 */
	public int countBokes(BokeVo bokeVo){
		if(bokeVo.getBokeKeyword()!=null&&!"".equals(bokeVo.getBokeKeyword())){
			//HanLP分词器进行分词
			List<String> keywordList = HanLP.extractKeyword(bokeVo.getBokeKeyword(), 5);
			bokeVo.setIkSplitKeywords(keywordList);
			StringBuilder sb = new StringBuilder();
			for(String key:keywordList){
				sb.append("*");
				sb.append(key);
				sb.append("* ");
			}
			bokeVo.setMysqlFullTextIndexs(sb.toString().trim());
		}
		return bokeDao.countBokes(bokeVo);
	}

	/**
	 * 查询博客相邻的博客
	 * @param bokeNums
	 * @return
	 */
	public BokeVo getLastAndNextBoke(String bokeNums,String type){
		return bokeDao.getLastOrNextBoke(bokeNums, type);
	}

	/**
	 * 统计收藏博客数
	 * @param mark
	 * @return
	 */
	public int countMarkBoke(MarkBokeVo mark){
		if(mark.getMarkDescript()!=null&&!"".equals(mark.getMarkDescript())){
			//HanLP分词器进行分词
			List<String> keywordList = HanLP.extractKeyword(mark.getMarkDescript(), 5);
			mark.setMarkKeywords(keywordList);
			StringBuilder sb = new StringBuilder();
			for(String key:keywordList){
				sb.append("*");
				sb.append(key);
				sb.append("* ");
			}
			mark.setMysqlFullTextIndexs(sb.toString().trim());
		}
		return bokeDao.countMarkBoke(mark);
	}

	/**
	 * 查询收藏博客列表
	 * @param mark
	 * @return
	 */
	public List<MarkBokeVo> findMarkBokes(MarkBokeVo mark){

		if(mark==null){
			mark = new MarkBokeVo();
		}
		//关键字分词查询
		if(mark.getMarkDescript()!=null&&!"".equals(mark.getMarkDescript())){
			//HanLP分词
			List<String> keywordList = HanLP.extractKeyword(mark.getMarkDescript(), 5);
			mark.setMarkKeywords(keywordList);
			StringBuilder sb = new StringBuilder();
			for(String key:keywordList){
				sb.append("*");
				sb.append(key);
				sb.append("* ");
			}
			mark.setMysqlFullTextIndexs(sb.toString().trim());
			log.info("查询收藏博客参数"+JSONObject.toJSONString(mark));
			List<MarkBokeVo> markBokes = bokeDao.findMarkBokes(mark);
			//替换descript为关键字高亮
			for(MarkBokeVo markBoke:markBokes){
				markBoke.setMarkDescript(subAndReplaceHeightLight(markBoke.getMarkDescript(),keywordList,200));
			}
			return markBokes;
		}else{
			List<MarkBokeVo> markBokes = bokeDao.findMarkBokes(mark);
			//截取200个字符
			for(MarkBokeVo markBoke:markBokes){
				if(markBoke.getMarkDescript()!=null&&markBoke.getMarkDescript().length()>200){
					markBoke.setMarkDescript(markBoke.getMarkDescript().substring(0,200));
				}
			}
			return markBokes;
		}
	}
	
	/**
	 * 添加收藏博客
	 * @param mark
	 * @return
	 */
	public int saveMarkBoke(MarkBokeVo mark){
		return bokeDao.saveMarkBoke(mark);
	}
	
	/**
	 * 查询当前博客相关的10个博客，title高亮处理
	 * @param title
	 * @return
	 */
	public List<BokeVo> getRelateBokes(String title,String bokeId){
		//HanLP分词
		List<String> keywordList = HanLP.extractKeyword(title, 5);
		StringBuilder sb = new StringBuilder();
		for(String key:keywordList){
			sb.append("*");
			sb.append(key);
			sb.append("* ");
		}
		List<BokeVo> bokes = bokeDao.getRelateBokes(sb.toString().trim(),bokeId);
		//title高亮处理
		for(BokeVo boke:bokes){
			String heightLightTitle = subAndReplaceHeightLight(boke.getBokeTitle(),keywordList,100);
			boke.setBokeTitle(heightLightTitle);
			boke.setBokeText(subAndReplaceHeightLight(boke.getBokeText(),keywordList,100));
		}
		
		
		return bokes;
	}
	
	/**
	 * IK分词处理
	 * @param words
	 * @return
	 */
	private List<String> IKSplitWords(String words){
		//IK分词器进行分词
		StringReader re = new StringReader(words);
		IKSegmenter ik = new IKSegmenter(re,true);//智能切分true,细粒度切分false
		List<String> keywords = new ArrayList<String>();
		Lexeme lex = null;
		try {
			while((lex=ik.next())!=null){
				keywords.add(lex.getLexemeText());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return keywords;
	}
	
}
