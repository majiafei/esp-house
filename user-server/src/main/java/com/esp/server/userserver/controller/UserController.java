/**
 *
 */
package com.esp.server.userserver.controller;

import com.esp.server.userserver.common.ApiResponse;
import com.esp.server.userserver.common.UserContext;
import com.esp.server.userserver.constant.UserEnum;
import com.esp.server.userserver.domain.Agency;
import com.esp.server.userserver.domain.User;
import com.esp.server.userserver.model.QiniuPutRet;
import com.esp.server.userserver.service.AgencyService;
import com.esp.server.userserver.service.FileService;
import com.esp.server.userserver.service.QiNiuService;
import com.esp.server.userserver.service.UserService;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author eric
 */
@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService accountService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private FileService fileService;

    @Autowired
    private QiNiuService qiNiuService;

    @Autowired
    private Gson gson;


//----------------------------注册流程-------------------------------------------
/*    @GetMapping(value = "accounts/register")
    public ApiResponse intiRegPage() {
        List<Agency> allAgency = agencyService.getAllAgency();

        return Objects.isNull(allAgency) ?
                ApiResponse.ofMessage(20001, "获取机构件列表失败") :
                ApiResponse.ofSuccess(allAgency);
    }*/

    @RequestMapping("accounts/register")
    private ApiResponse initRegPate() {
        List<Agency> allAgency = agencyService.getAllAgency();

        return CollectionUtils.isEmpty(allAgency) ? ApiResponse.ofMessage(20001, "获取机构列表失败") : ApiResponse.ofSuccess(allAgency);
    }

    @PostMapping(value = "accounts/upload")
    public ApiResponse avatarUpload(@RequestParam("multipartFile") MultipartFile avatarFile) {
        String photoUrl =  "";
        try {
            Response response = qiNiuService.uploadFile(avatarFile.getInputStream());
            QiniuPutRet qiniuPutRet = gson.fromJson(response.bodyString(), QiniuPutRet.class);
            photoUrl = qiniuPutRet.getKey();
            return ApiResponse.ofSuccess(photoUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofMessage(40001,"文件上传失败");
        }
    }

    @PostMapping(value = "accounts/register")
    public ApiResponse accountsSubmitToQiniu(@RequestBody User account) {
        boolean exist = accountService.isExist(account.getEmail());

        if (!exist) {
            accountService.addAccount(account);

            return ApiResponse.ofSuccess(account.getEmail());
        } else {
            return ApiResponse.ofMessage(UserEnum.EMAIL_REGISTERED.getCode(), UserEnum.EMAIL_REGISTERED.getMsg());
        }
    }

    @GetMapping("accounts/verify")
    public ApiResponse verify(String key) {
        boolean result = accountService.enableAccount(key);
        if (result) {
            return ApiResponse.ofSuccess();
        } else {
            return ApiResponse.ofMessage(UserEnum.EMAIL_INVALID.getCode(), UserEnum.EMAIL_INVALID.getMsg());
        }
    }


    //----------------------------登录操作流程-------------------------------------------

    @PostMapping(value = "/accounts/signin")
    public ApiResponse loginSubmit(@RequestBody User user) {
        try {
            User loginUser = accountService.login(user.getEmail(), user.getPassword());
            if (loginUser == null) {
                return ApiResponse.ofMessage(50001, "输入的账号或者密错误");
            } else {
                return ApiResponse.ofSuccess(loginUser);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofMessage(500, e.getMessage());
        }
    }

    /**
     * @param token
     * @return
     */
    @GetMapping("accounts/logout")
    public ApiResponse logout(String token) {
        accountService.logout(token);
        return ApiResponse.ofSuccess();
    }


    @PostMapping("accounts/remember")
    public String remember(String email, RedirectAttributes model, ModelMap modelMap,HttpServletRequest request) {
        if (StringUtils.isBlank(email)) {
            model.addFlashAttribute("errorMsg", "邮箱不能为空");
            return "redirect:/accounts/signin";
        }
        String baseUrl = StringUtils.substringBeforeLast(request.getRequestURL().toString(), "/accounts/remember");
        // 定义激活的连接地址
        String activeUrl = baseUrl+"/accounts/reset";
        accountService.resetNotify(email, activeUrl);
        modelMap.put("email", email);
        return "/accounts/remember";
    }


    @RequestMapping("accounts/reset")
    public String reset(String key, RedirectAttributes model, ModelMap modelMap) {
        String email = accountService.getResetKeyEmail(key);

        if (StringUtils.isBlank(email)) {
            model.addFlashAttribute("errorMsg","重置链接已过期");
            return "redirect:/accounts/signin";
        }

        modelMap.put("email", email);
        modelMap.put("success_key", key);

        return "/accounts/reset";
    }

    @RequestMapping(value = "accounts/resetSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public String resetSubmit(User user,RedirectAttributes model) {
        User updatedUser = accountService.reset(user.getKey(), user.getPassword());
        model.addFlashAttribute("successMsg", "密码重置成功");
        return "redirect:/index";
    }


    //----------------------------个人信息修改--------------------------------------
    @GetMapping("accounts/profile")
    public ApiResponse profile(String email) {
        try {
            User user = accountService.getUserByEmail(email);
            return ApiResponse.ofSuccess(user);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofMessage(500, e.getMessage());
        }
    }


    @RequestMapping(value = "accounts/profileSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public String profileSubmit(HttpServletRequest req, User updateUser, RedirectAttributes model) {
        User user = accountService.updateUser(updateUser);
        model.addFlashAttribute("successMsg", "个人信息更新成功");
        return "redirect:/accounts/profile?email="+user.getEmail();
    }

    /**
     * 修改密码操作
     *
     * @param email
     * @param password
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("accounts/changePassword")
    public String changePassword(String email, String password, String newPassword,
                                 String confirmPassword, RedirectAttributes model) {
        User user = accountService.login(email, password);
        if (user == null || !confirmPassword.equals(newPassword)) {
            model.addFlashAttribute("errorMsg", "密码错误");
            return "redirect:/accounts/profile?email="+email;
        }
        User updateUser = new User();
        updateUser.setPassword(newPassword);
        updateUser.setEmail(email);
        accountService.updateUser(updateUser);
        model.addFlashAttribute("successMsg", "密码更新成功");
        return "redirect:/accounts/signin";
    }

    //----------------------------鉴权--------------------------------------
    @GetMapping("/token/verify")
    public ApiResponse verifyUser(String token) {
        User user = accountService.getLoginUserByToken(token);
        return ApiResponse.ofSuccess(user);
    }

    @GetMapping("/accounts/getUser")
    public ApiResponse<User> getUserById(Long id) {
        return ApiResponse.ofSuccess(accountService.getUserById(id));
    }

}
