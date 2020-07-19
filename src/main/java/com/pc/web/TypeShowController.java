package com.pc.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Blog;
import com.pc.domain.Type;
import com.pc.service.IBlogService;
import com.pc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Integer id, Model model) {
        List<Type> types = typeService.listTypeAndCount();
        System.out.println(types);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        System.out.println(types);
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.listBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
