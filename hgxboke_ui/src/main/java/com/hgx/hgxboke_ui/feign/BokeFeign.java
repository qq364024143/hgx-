package com.hgx.hgxboke_ui.feign;


import org.springframework.cloud.openfeign.FeignClient;

import com.hgx.my_boke_api.boke.controller.BokeController;

@FeignClient(value="eureka-client")
public interface BokeFeign extends BokeController{

}
