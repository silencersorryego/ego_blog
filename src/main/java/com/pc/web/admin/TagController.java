package com.pc.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Tag;
import com.pc.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    ITagService tagService;

    @GetMapping("/tags")
    public String listTag(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 4);
        List<Tag> tags = tagService.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAdd() {
        return "admin/tags-input";
    }
    @PostMapping("/tags/add")
    public String saveTag(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "已有这个标签，不能添加重复");
            return "redirect:/admin/tags/input";
        }
        int t = tagService.saveTag(tag);
        if (t <= 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }
    @PostMapping("/tags/update")
    public String updateTag(Tag tag) {
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-update";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable("id") Integer id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

}
