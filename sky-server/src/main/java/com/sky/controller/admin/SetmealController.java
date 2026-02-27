package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin/setmeal")
@RestController
@Slf4j
@Api(tags="套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService ;


    /*
    * 新增套餐
    * params SetmealDTO
    * */

    @PostMapping
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache",key="#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐:{}",setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return  Result.success();
    }


    /*
    * 套餐分页查询
    *
    * */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public  Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询参数:{}",setmealPageQueryDTO);
         PageResult pageResult =  setmealService.pageQuery(setmealPageQueryDTO);
         return Result.success(pageResult);
    }


    /*
    *
    * 删除套餐
    *
    *
    * */
    @DeleteMapping
    @ApiOperation("删除套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam  List<Integer> ids){
        setmealService.deleteById(ids);
        return  Result.success();
    }


    /*
    * 根据id查询套餐
    *
    * */

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealDTO> selectById(@PathVariable Long id){
        SetmealDTO setmealDTO = setmealService.selectById(id);
        return Result.success(setmealDTO);
    }

    /*
    * 修改套餐
    *
    * */
    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public  Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return  Result.success();
    }

    /*
    * 停售启售套餐
    *
    * */

    @PostMapping("status/{status}")
    @ApiOperation("停售启售套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public  Result startOrStop(@PathVariable Integer status,Long id){
        setmealService.startOrStop(status,id);
        return  Result.success();
    }






}
