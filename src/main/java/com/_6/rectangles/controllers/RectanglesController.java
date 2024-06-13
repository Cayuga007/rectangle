package com._6.rectangles.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com._6.rectangles.models.Rectangle;
import com._6.rectangles.models.RectangleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class RectanglesController {

    @Autowired
    private RectangleRepository rectangleRepo;
    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all rectangles");
        List<Rectangle> rectangles = rectangleRepo.findAllByOrderByUidAsc();
        model.addAttribute("recs", rectangles);
        return "rectangles/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Map<String, String> newrectangle, HttpServletResponse response) {
        System.out.println("ADD rectangle");
        String newName = newrectangle.get("name");
        int newWidth = Integer.parseInt(newrectangle.get("width"));
        int newHeight = Integer.parseInt(newrectangle.get("height"));
        String newColor = newrectangle.get("color");
        rectangleRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
        response.setStatus(201);
        return "rectangles/addedRectangle";
    }

    @GetMapping("/rectangles/delete/{uid}")
    public String deleteRectangle(@PathVariable Integer uid) {
        System.out.println("DELETE rectangle");
        rectangleRepo.deleteById(uid);
        return "rectangles/deletedRectangle";
    }

    @GetMapping("/rectangles/display/{uid}")
    public String displayRectangle(Model model, @PathVariable Integer uid) {
        System.out.println("DISPLAY rectangle");
        model.addAttribute("rec", rectangleRepo.getReferenceById(uid));
        return "rectangles/displayRectangle";
    }

    @GetMapping("/rectangles/change/{uid}/{name}/{width}/{height}/{color}")
    public String postMethodName(@PathVariable Integer uid, @PathVariable String name, @PathVariable Integer width, @PathVariable Integer height, @PathVariable String color) {
        System.out.println("CHANGE rectangle");
        Rectangle currentrectangle = rectangleRepo.getReferenceById(uid);
        currentrectangle.setName(name);
        currentrectangle.setWidth(width);
        currentrectangle.setHeight(height);
        currentrectangle.setColor(color);
        rectangleRepo.save(currentrectangle);
        return "rectangles/changedRectangle";
    }
    
}
