package com.ruanmou.web.espweb.service.house;

import com.ruanmou.web.espweb.common.ApiResponse;
import com.ruanmou.web.espweb.model.Agency;
import com.ruanmou.web.espweb.model.ListResponse;
import com.ruanmou.web.espweb.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: espservers
 * @Auther: GERRY
 * @Date: 2018/11/30 14:26
 * @Description: 经纪人接口
 */
@FeignClient(name = "gateway")
public interface AgencyService {
    @GetMapping("user/agency/detail")
    ApiResponse<UserVO> getAgencyDetail(@RequestParam("id") Long id);

    @PostMapping("user/agency/create")
    ApiResponse create(@RequestBody Agency agency);

    @GetMapping("user/agency/list")
    ApiResponse<List<Agency>> list();

    @GetMapping("user/agency/AgencyDetail")
    ApiResponse<Agency> detail(@RequestParam("id") Integer id);

    @GetMapping("user/agency/agentList")
    ApiResponse<ListResponse<UserVO>> agentList(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset);
}
