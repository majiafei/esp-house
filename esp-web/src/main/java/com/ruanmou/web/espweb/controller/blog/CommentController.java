package com.ruanmou.web.espweb.controller.blog;

import com.ruanmou.web.espweb.common.UserContext;
import com.ruanmou.web.espweb.constant.CommentType;
import com.ruanmou.web.espweb.model.CommentReq;
import com.ruanmou.web.espweb.service.blog.CommentService;
import com.ruanmou.web.espweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: espservers
 * @Auther: GERRY
 * @Date: 2018/12/5 20:57
 * @Description:
 */
@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired(required = false)
    private CommentService commentService;

    @PostMapping("leaveComment")
    public String addHouseComment(CommentReq commentReq) {
        UserVO user = UserContext.getUser();
        Long userId =  user.getId();
        commentReq.setUserId(userId);
        commentReq.setType(CommentType.HOUSE.value);
        commentService.addComment(commentReq);

        return "redirect:/house/detail?id=" + commentReq.getHouseId();
    }

    @PostMapping("leaveBlogComment")
    public String addBlogComment(CommentReq commentReq) {
        UserVO user = UserContext.getUser();
        Long userId =  user.getId();
        commentReq.setUserId(userId);
        commentReq.setType(CommentType.BLOG.value);
        commentService.addComment(commentReq);

        return "redirect:/blog/detail?id=" + commentReq.getBlogId();
    }
}
