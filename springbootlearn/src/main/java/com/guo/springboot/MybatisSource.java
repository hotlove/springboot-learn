package com.guo.springboot;

import com.guo.springboot.dao.ProfileMapper;
import com.guo.springboot.domain.Profile;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Date: 2021/1/18 14:46
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class MybatisSource {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis/mapper/com/guo/springboot/dao/ProfileMapper.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProfileMapper mapper = sqlSession.getMapper(ProfileMapper.class);
        Profile profile = mapper.selectByPrimaryKey(1);
    }
}
