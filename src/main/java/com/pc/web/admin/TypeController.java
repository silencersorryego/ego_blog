package com.pc.web.admin;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pc.domain.Type;
import com.pc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    //列表页
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Type> allType = typeService.listType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    //去新增页面
    @GetMapping("/types/input")
    public String toAdd() {
        return "admin/types-input";
    }



    @PostMapping("/types/add")
    public String Add(Type type, RedirectAttributes attributes, BindingResult result) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        }
        //添加操作
        int t =typeService.saveType(type);
        if (t <= 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }


    //到修改页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        Type type = typeService.getTypeById(id);
        System.out.println(type);
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    //进行修改
    @PostMapping("/types/update")
    public String editPost(Type type) {
        typeService.updateType(type);
        return "redirect:/admin/types";
    }


    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }



}
