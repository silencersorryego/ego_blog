package com.pc.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Blog;
import com.pc.domain.Tag;
import com.pc.service.IBlogService;
import com.pc.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IBlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      @PathVariable int id, Model model) {
        List<Tag> tags = tagService.listTagAndCount();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.listBlogByTagId(id);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogs",blogs);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
