package com.imooc.sell.repository;

import com.imooc.sell.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductionCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
//        这边因为java8的optional的原因导致不能使用repository.findOne(id),改用findById().get()代替
//        ProductCategory productCategory = repository.findOne(1);
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
//    测试中的@transcational和service中的不一样，后者是发生错误时回滚，而前者是测试结束之后不保留测试的结果
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("男生最爱", 3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        这两个是一样的效果
//        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
//        在进行这种查询时需要有一个无参的构造方法，否则会报错
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

}