package com.example.springjpa.Service;

import com.example.springjpa.Model.Food;
import com.example.springjpa.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService
{
  @Autowired
  public FoodRepository foodRepository;
    public ResponseEntity<Food> createFood(Food food){
      foodRepository.save(food);
      return ResponseEntity.ok(food);
    }
  public ResponseEntity<List<Food>> getFood() {
    return ResponseEntity.ok(foodRepository.findAll());
  }
  public ResponseEntity<Food> getFoodById(Long id) {
    return foodRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
  public ResponseEntity<Food> updateFood(Long id, Food food) {

    if (!foodRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    food.setId(id);
    foodRepository.save(food);
    return ResponseEntity.ok(food);
  }
  public ResponseEntity<Void> deleteFood(Long id) {

    if (!foodRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    foodRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  public List<Food> getFoodList() {
    return foodRepository.findAll();
  }

  public Food findFoodById(Long id) {
    return foodRepository.findById(id).orElse(null);
  }
}
