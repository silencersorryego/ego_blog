package com.pc.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Blog;
import com.pc.domain.Tag;
import com.pc.domain.Type;
import com.pc.service.IBlogService;
import com.pc.service.ITagService;
import com.pc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class IndexController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ITypeService typeService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<Blog> allFirstPageBlog = blogService.getAllBlog();
        List<Type> allType = typeService.listTypeAndCount();
        List<Tag> allTag = tagService.listTagAndCount();
       // List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        //model.addAttribute("recommendedBlogs", recommendedBlog);
        //System.out.println(pageInfo);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        //List<Comment> comments = commentService.listCommentByBlogId(id);
        //model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        //System.out.println(blog);
        return "blog";
    }
}
