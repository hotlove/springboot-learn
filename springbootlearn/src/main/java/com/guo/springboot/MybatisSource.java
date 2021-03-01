package com.guo.springboot;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Date: 2021/1/18 14:46
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MybatisSource {
    public static void main(String[] args) throws IOException {
//        String resource = "mybatis/mapper/com/guo/springboot/dao/ProfileMapper.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        ProfileMapper mapper = sqlSession.getMapper(ProfileMapper.class);
//        Profile profile = mapper.selectByPrimaryKey(1);
        Thread t = Thread.currentThread();
        new Thread(() -> {
            try {
                for (;;) {
                    TimeUnit.SECONDS.sleep(5);
                    LockSupport.unpark(t);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        int count = 0;
        for (;;) {
            int i = count++;
            System.out.println(i);
            LockSupport.park(Thread.currentThread());
        }
    }
}
