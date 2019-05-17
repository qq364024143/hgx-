package com.hgx.my_boke_eurekaclient.boke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hgx.my_boke_api.boke.entity.BokeClassVo;
import com.hgx.my_boke_api.boke.entity.BokeVo;
import com.hgx.my_boke_api.boke.entity.MarkBokeVo;

@Repository
public interface BokeDao {
	
	/**
	 * 查询出最新的10条博客
	 * @return
	 */
	public List<BokeVo> findRecentBokes();
	
	
	/**
	 * 查询推荐博客10条 
	 * @return
	 */
	public List<BokeVo> findRandomBokes();
	
	/**
	 * 查询博客分类
	 * @return
	 */
	public List<BokeClassVo> findBokeClasses();
	
	/**
	 * 查询博客通过博客ID
	 * @param bokeId
	 * @return
	 */
	public BokeVo findBokeByBokeId(String bokeId);
	
	/**
	 * 新增博客
	 * @param bokeVo
	 * @return
	 */
	public int saveBoke(BokeVo bokeVo);
	
	/**
	 * 查找博客列表
	 * @param bokeVo
	 * @return
	 */
	public List<BokeVo> findBokes(BokeVo bokeVo);
	
	
	/**
	 * 统计博客总数
	 * @param bokeVo
	 * @return
	 */
	public int countBokes(BokeVo bokeVo);
	
	/**
	 * 获取当前博客的上一篇或下一篇
	 * @param bokeId
	 * @param type
	 * @return
	 */
	public BokeVo getLastOrNextBoke(@Param("bokeNums")String bokeNums,@Param("type")String type);
	
	/**
	 * 统计收藏博客数
	 * @param mark
	 * @return
	 */
	public int countMarkBoke(MarkBokeVo mark);
	
	/**
	 * 查询收藏博客列表
	 * @param mark
	 * @return
	 */
	public List<MarkBokeVo> findMarkBokes(MarkBokeVo mark);
	
	/**
	 * 添加收藏博客
	 * @param mark
	 */
	public int saveMarkBoke(MarkBokeVo mark);
	
	/**
	 * 根据当前博客title查找相关的10个博客 ，title使用IK分词处理
	 * @param titles
	 * @return
	 */
	public List<BokeVo> getRelateBokes(@Param("titles") String titles,@Param("bokeId")String bokeId);
}
