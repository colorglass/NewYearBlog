package com.newyearblog.blog.controller.admin;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.newyearblog.blog.entity.Tag;
import com.newyearblog.blog.entity.User;
import com.newyearblog.blog.service.TagService;

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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String TagsPage(
            @PageableDefault(size = 6, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/new")
    public String newTagPage(Model model) {
        Tag tag = (Tag) model.getAttribute("tag");

        if (tag == null) {
            model.addAttribute("tag", new Tag());
        }
        return "admin/editor-tags";
    }

    @PostMapping("/tags/save")
    public String newTag(Tag tag, HttpSession session, RedirectAttributes attributes) {

        // 判断是否输入标签名
        if (tag.getTagName().isEmpty()) {
            attributes.addFlashAttribute("message", "请输入标签名");
            attributes.addFlashAttribute("tag", tag);
            return "redirect:/admin/tags/new";
        }

        // 判断标签名是否重复
        if (tagService.getByTagName(tag.getTagName()) != null) {
            attributes.addFlashAttribute("message", "该标签已存在！");

            if (tag.getId() == null) {
                //新建操作
                attributes.addFlashAttribute("tag", tag);
                return "redirect:/admin/tags/new";
            } else {
                //修改操作
                attributes.addAttribute("id", tag.getId());
                return "redirect:/admin/tags/update";
            }
        }

        // 获取当前用户和时间
        User user = (User) session.getAttribute("user");
        tag.setUser(user);
        Date createTime = new Date(new java.util.Date().getTime());
        tag.setCreateTime(createTime);

        // 判断是新建操作还是修改操作
        if (tag.getId() == null) {
            // 保存到数据库
            tagService.saveTag(tag);
            attributes.addFlashAttribute("message", "新建成功！");
        } else {
            // 更新数据库
            tagService.updateTag(tag.getId(), tag);
            attributes.addFlashAttribute("message", "编辑成功！");
        }

        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/del")
    public String delTag(@RequestParam Long id, RedirectAttributes attributes) {

        // 从数据库中删除
        tagService.deletTag(id);
        attributes.addFlashAttribute("message", "删除操作成功！");

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/update")
    public String updateTagPage(@RequestParam Long id, Model model) {

        // 根据id获得当前选中Tag
        model.addAttribute("tag", tagService.getTag(id));

        return "admin/editor-tags";
    }

}
