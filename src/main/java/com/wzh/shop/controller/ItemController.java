package com.wzh.shop.controller;

import com.wzh.shop.entity.Item;
import com.wzh.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/list")
    @ResponseBody
    public List<Item> list(){
        return itemRepository.findAll();
    }

    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file,Item item) throws IOException {
        String f = ItemController.class.getResource("/static/images").getFile();
        String imageName = DigestUtils.md5DigestAsHex(file.getOriginalFilename().getBytes())+".jpg";
        file.transferTo(new File(f,imageName));
        item.setImage("/static/images/"+imageName);
        itemRepository.save(item);
        return "redirect:/templates/item_list.html";
    }

}
