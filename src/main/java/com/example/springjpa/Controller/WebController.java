package com.example.springjpa.Controller;

import com.example.springjpa.Model.Food;
import com.example.springjpa.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
  @Autowired
  FoodService foodService;

  @GetMapping("/")
  public String demo()
  {
     return "index";
  }
  @PostMapping("/food/add")
  public String addFood(
      @RequestParam String foodName,
      @RequestParam double price,
      @RequestParam(required = false) Boolean isAvailable) {

    Food food = new Food();

    food.setFoodName(foodName);
    food.setPrice(price);
    food.setAvailable(isAvailable != null && isAvailable);

    foodService.createFood(food);

    return "redirect:/";
  }

  @GetMapping("/foods")
  public String viewFood(Model model) {

    List<Food> foods = foodService.getFoodList();

    model.addAttribute("foods", foods);

    return "foods";
  }

  @GetMapping("/foods/{id}")
  public String viewFoodById(@PathVariable Long id, Model model) {

    Food food = foodService.findFoodById(id);

    model.addAttribute("food", food);

    return "food-details";
  }
}
