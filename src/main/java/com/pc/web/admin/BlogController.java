package com.pc.web.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Blog;
import com.pc.domain.User;
import com.pc.service.IBlogService;
import com.pc.service.ITagService;
import com.pc.service.ITypeService;
import com.pc.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {



    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private ITagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    //显示
    @GetMapping("/blogs")
    public String listBlog(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<BlogQuery> allBlog = blogService.listBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        //System.out.println(pageInfo);
        return "admin/blogs";
    }

    //删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //去新增页面
    @GetMapping("/blogs/input")
    public String toAdd(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        User user =(User) session.getAttribute("user");
        blog.setUserId(user.getId());
        if (user.isType()){
            blog.setTypeId(blog.getType().getId());
            blog.setTags(tagService.getTagByString(blog.getTagIds()));
            blogService.saveBlog(blog);
            attributes.addFlashAttribute("message", "新增成功");
            return "redirect:/admin/blogs";
        }
        attributes.addFlashAttribute("message", "新增失败,你的权限不足");
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Blog searchBlog,Model model,
                         @RequestParam(defaultValue = "0",value = "type_id") Integer type_id,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        searchBlog.setTypeId(type_id);
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 3);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }

}
