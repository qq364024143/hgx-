package com.hgx.hgxboke_ui.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.hgx.my_boke_api.comment.controller.CommentController;
import com.hgx.my_boke_api.comment.entity.CommentVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;

/**
 * 评论API   Feign
 * @author hgx
 *
 */
@FeignClient(value="eureka-client")
public interface CommentFeign extends CommentController{

}
