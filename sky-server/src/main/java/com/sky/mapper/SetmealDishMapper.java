package com.sky.mapper;

import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);


    void insert(List<SetmealDish> setmealDishes);

    /*
     * 根据套餐id查询套餐里的菜品
     * */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> selectById(Long id);


    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void delete(Long id);



}
