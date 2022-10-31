package com.guo.springboot;

import java.lang.annotation.*;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/7/20 23:18
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface TestService {
}
