package com.tour.controller;

import com.tour.model.Pack;
import com.tour.repository.PackRepository;
import com.tour.service.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RequestMapping("/pack")
@Controller
public class PackController {

    @Autowired
    PackRepository repo;

    @RequestMapping("/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("datalist", repo.findAll());
        return "pack";
    }

    @RequestMapping("/create")
    public String create(Model model, HttpServletRequest request) {
        return "pack_create";
    }

    @RequestMapping("/save")
    public String save(Pack obj, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Optional<Pack> idobj = repo.findTopByOrderByIdDesc();
        String id = null;
        if (idobj.isPresent()) {
            int idnum = Integer.parseInt(idobj.get().getPackageId().substring(5));
            idnum++;
            id = "PACK3" + idnum;
        } else {
            id = "PACK362353";
        }

        String imgUrl = id + multipartFile.getOriginalFilename();
        obj.setPackageId(id);
        obj.setImgUrl(imgUrl);

        repo.save(obj);

        String uploadDir = "uploads";

        FileUploadUtil.saveFile(uploadDir, imgUrl, multipartFile);
        return "redirect:/pack/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model, HttpServletRequest request) {
        model.addAttribute("obj", repo.findById(id).get());
        return "pack_show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Pack> obj = repo.findById(id);
        repo.delete(obj.get());

        return "redirect:/pack/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("obj", repo.findById(id).get());
        return "pack_edit";
    }

    @RequestMapping("/update")
    public String update(Pack obj, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String imgUrl = obj.getPackageId() + multipartFile.getOriginalFilename();
            obj.setImgUrl(imgUrl);
            String uploadDir = "uploads";
            FileUploadUtil.saveFile(uploadDir, imgUrl, multipartFile);
        }
        repo.save(obj);
        return "redirect:/pack/show/" + obj.getId();
    }
}
