package com.ruanmou.web.espweb.controller.house;

import com.ruanmou.web.espweb.common.ApiResponse;
import com.ruanmou.web.espweb.common.PageData;
import com.ruanmou.web.espweb.common.PageParams;
import com.ruanmou.web.espweb.common.UserContext;
import com.ruanmou.web.espweb.model.Agency;
import com.ruanmou.web.espweb.model.House;
import com.ruanmou.web.espweb.model.ListResponse;
import com.ruanmou.web.espweb.service.house.AgencyService;
import com.ruanmou.web.espweb.service.house.HouseService;
import com.ruanmou.web.espweb.util.GeneralConvert;
import com.ruanmou.web.espweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: espservers
 * @Auther: GERRY
 * @Date: 2018/12/3 11:19
 * @Description:
 */
@Controller
@RequestMapping("agency")
public class AgencyController {

    @Autowired(required = false)
    private AgencyService agencyService;

    @Autowired(required = false)
    private HouseService houseService;

    /**
     * 初始化添加界面
     * @return
     */
    @GetMapping("create")
    public String initAdd(RedirectAttributes redirectAttributes) {
        UserVO user = UserContext.getUser();
        if (user == null && Objects.equals(user.getEmail(),"271314998@qq.com")) {
            redirectAttributes.addFlashAttribute("errorMsg", "请请先登录后操作");
            return "redirect:/accounts/signin";
        }

        return "user/agency/create";
    }

    /**
     * 新增经纪机构
     * @param agency
     * @return
     */
    @PostMapping("submit")
    public String add(Agency agency, RedirectAttributes redirectAttributes) {
        UserVO user = UserContext.getUser();
        if (user == null && Objects.equals(user.getEmail(),"271314998@qq.com")) {
            redirectAttributes.addFlashAttribute("errorMsg", "请请先登录后操作");
            return "redirect:/accounts/signin";
        }
        agencyService.create(agency);

        return "redirect:/index";
    }

    @GetMapping("list")
    public String agencyList(ModelMap modelMap) {
        // 获取热门房产信息
        ApiResponse<List<House>> hotHouse = houseService.getHotHouse(HouseController.HOT_SIZE);
        List<House> hotHouseData = hotHouse.getData();

        ApiResponse<List<Agency>> listResp = agencyService.list();
        List<Agency> agencyData = listResp.getData();
        // 封装页面渲染的数据
        modelMap.put("hotHouses", hotHouseData);
        modelMap.put("agencyList", agencyData);

        return "user/agency/agencyList";
    }

    @GetMapping("agencyDetail")
    public String agencyDetail(Integer id, ModelMap modelMap) {
        ApiResponse<Agency> detail = agencyService.detail(id);

        // 获取热门房产信息
        ApiResponse<List<House>> hotHouse = houseService.getHotHouse(HouseController.HOT_SIZE);
        List<House> hotHouseData = hotHouse.getData();

        ApiResponse<Agency> agencyApiResponse = agencyService.detail(id);
        Agency data = agencyApiResponse.getData();
        // 封装页面渲染的数据
        modelMap.put("hotHouses", hotHouseData);
        modelMap.put("agency", data);

        return "user/agency/agencyDetail";
    }

    @GetMapping("agentList")
    public String agentList(Integer pageSize, Integer pageNum, ModelMap modelMap) {

        if (pageSize == null) {
            pageSize = 6;
        }

        PageParams build = PageParams.build(pageSize, pageNum);

        // 调用房产服务提供的接口
        ApiResponse<ListResponse<UserVO>> listResponseApiResponse = agencyService.agentList(build.getLimit(), build.getOffset());
        // 把获取的数据强制转换为一个ListResponse对象
        ListResponse listResponse = GeneralConvert.converter(listResponseApiResponse.getData(), ListResponse.class);
        List<UserVO> list = GeneralConvert.convert2List(listResponse.getList(),UserVO.class);
        // 构建一个分页对象
        PageData<UserVO> pageData = PageData.buildPage(list,
                listResponse.getCount(), build.getLimit(), build.getPageNum());
        // 获取热门房产信息
        ApiResponse<List<House>> hotHouse = houseService.getHotHouse(HouseController.HOT_SIZE);
        List<House> hotHouseData = hotHouse.getData();

        // 封装页面渲染的数据
        modelMap.put("hotHouses", hotHouseData);
        modelMap.put("pageData", pageData);

        return "user/agent/agentList";
    }
}
