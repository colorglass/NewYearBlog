package com.newyearblog.blog.controller.admin;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.newyearblog.blog.entity.Type;
import com.newyearblog.blog.entity.User;
import com.newyearblog.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String TypesPage(
            @PageableDefault(size = 6, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/new")
    public String newTypePage(Model model) {
        Type type = (Type) model.getAttribute("type");

        if (type == null) {
            model.addAttribute("type", new Type());
        }
        return "admin/editor-types";
    }

    @PostMapping("/types/save")
    public String newType(Type type, HttpSession session, RedirectAttributes attributes) {

        // 判断是否输入类型名
        if (type.getTypeName().isEmpty()) {
            attributes.addFlashAttribute("message", "请输入类型名");
            attributes.addFlashAttribute("type", type);
            return "redirect:/admin/types/new";
        }

        // 判断类型名是否重复
        if (typeService.getByTypeName(type.getTypeName()) != null) {
            attributes.addFlashAttribute("message", "该类型已存在！");

            if (type.getId() == null) {
                //新建操作
                attributes.addFlashAttribute("type", type);
                return "redirect:/admin/types/new";
            } else {
                //修改操作
                attributes.addAttribute("id", type.getId());
                return "redirect:/admin/types/update";
            }
        }

        // 获取当前用户和时间
        User user = (User) session.getAttribute("user");
        type.setUser(user);
        Date createTime = new Date(new java.util.Date().getTime());
        type.setCreateTime(createTime);

        // 判断是新建操作还是修改操作
        if (type.getId() == null) {
            // 保存到数据库
            typeService.saveType(type);
            attributes.addFlashAttribute("message", "新建成功！");
        } else {
            // 更新数据库
            typeService.updateType(type.getId(), type);
            attributes.addFlashAttribute("message", "编辑成功！");
        }

        return "redirect:/admin/types";
    }

    @PostMapping("/types/del")
    public String delType(@RequestParam Long id, RedirectAttributes attributes) {

        // 从数据库中删除
        typeService.deletType(id);
        attributes.addFlashAttribute("message", "删除操作成功！");

        return "redirect:/admin/types";
    }

    @GetMapping("/types/update")
    public String updateTypePage(@RequestParam Long id, Model model) {

        // 根据id获得当前选中Type
        model.addAttribute("type", typeService.getType(id));

        return "admin/editor-types";
    }

}
