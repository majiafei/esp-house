package com.ruanmou.web.espweb.service.blog;

import com.ruanmou.web.espweb.common.ApiResponse;
import com.ruanmou.web.espweb.model.Blog;
import com.ruanmou.web.espweb.model.BlogQueryReq;
import com.ruanmou.web.espweb.model.ListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: espservers
 * @Auther: GERRY
 * @Date: 2018/12/5 14:40
 * @Description:
 */
@FeignClient("gateway")
public interface BlogService {

    @PostMapping("/comment/blog/list")
    ApiResponse<ListResponse<Blog>> listBlog(@RequestBody BlogQueryReq query);

    @GetMapping("/comment/blog/one")
    ApiResponse<Blog> queryOneBlog(@RequestParam("id") Integer id);

}
