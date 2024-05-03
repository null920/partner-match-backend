package com.ycr.partnermatch.service;

import com.ycr.partnermatch.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 算法工具类测试
 *
 * @author null&&
 * @Date 2024/5/3 16:03
 */
public class AlgorithmUtilsTest {

    @Test
    void test() {
        List<String> list1 = Arrays.asList("Java", "男", "大一");
        List<String> list2 = Arrays.asList("Java", "男", "大二");
        List<String> list3 = Arrays.asList("Python", "女", "大三");

        System.out.println(AlgorithmUtils.minDistance(list1, list2));
        System.out.println(AlgorithmUtils.minDistance(list1, list3));
    }
}
