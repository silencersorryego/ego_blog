package com.pc;

import com.pc.domain.Blog;
import com.pc.domain.Tag;
import com.pc.domain.Type;
import com.pc.mapper.IBlogMapper;
import com.pc.mapper.ITagMapper;
import com.pc.mapper.ITypeMapper;
import com.pc.mapper.IUserMapper;
import com.pc.service.ITagService;
import com.pc.service.ITypeService;
import com.pc.vo.BlogQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    ITypeMapper typeMapper;
    @Autowired
    ITagMapper tagMapper;
    @Autowired
    IBlogMapper blogMapper;

    @Autowired
    IUserMapper userMapper;

    @Autowired
    ITypeService typeService;

    @Autowired
    ITagService tagService;

    @Test
    void contextLoads() {
        /*Type type = new Type();
        type.setName("as");
        iTypeMapper.saveType(type);*/
        /*Type typeById = iTypeMapper.getTypeById(1);
        System.out.println(typeById);
        Type typeByName = iTypeMapper.getTypeByName("jsd");
        System.out.println(typeByName);*/
        List<Type> types = typeMapper.listType();
        for (Type type : types) {
            System.out.println(type);
        }
    }

    @Test
    void testTag(){
        /*Tag tag1 = new Tag();
        tag1.setName("java");
        Tag tag2 = new Tag();
        tag2.setName("C#");
        tagMapper.saveTag(tag1);
        tagMapper.saveTag(tag2);
        List<Tag> tags = tagMapper.listTag();
        for (Tag tag : tags) {
            System.out.println(tag);
        }
        System.out.println(tagMapper.getTagById(2));
        tagMapper.updateTag(new Tag(1, "JDK"));*/
        tagMapper.deleteTag(2);
    }
    @Test
    void testBlog(){
        /*Blog blog = new Blog("mem", "sjsado", "原创", "ni renwhsiw ma", "sd", 0, true, true, true, true, true, new Date(), new Date());
        blogMapper.saveBlog(blog);
        System.out.println(blog);*/

        /*Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("好标题");
        blog.setFirstPicture("第一张图片");
        blog.setAppreciation(false);
        blog.setUpdateTime(new Date());
        blogMapper.updateBlog(blog);
        System.out.println(blogMapper.getBlogById(1));*/
        /*List<BlogQuery> blogs = blogMapper.listBlog();
        for (BlogQuery blog : blogs) {
            System.out.println(blog);
        }*/
        Blog blog = new Blog();
        blog.setTitle("re");
        blog.setRecommended(false);
        List<BlogQuery> blogBySearch = blogMapper.getBlogBySearch(blog);
        for (BlogQuery bySearch : blogBySearch) {
            System.out.println(bySearch);
        }
    }
    @Test
    void testBlogAndTag(){
        //blogMapper.deleteBlogAndTags(6);
        List<Blog> allBlog = blogMapper.getAllBlog();
        for (Blog blog : allBlog) {
            System.out.println(blog);
        }
    }
    @Test
    void testUser(){
        //System.out.println(userMapper.getUserByUserName("admin"));
        //System.out.println(typeMapper.listTypeCount(1));
        /*List<Type> types = typeService.listTypeAndCount();
        for (Type type : types) {
            System.out.println(type);
        }*/
        //System.out.println(tagMapper.getTagCount(15));
       // System.out.println(tagService.listTagAndCount());
        //System.out.println(blogMapper.getBlogById(1));
        //System.out.println(blogMapper.getBlogType(7));
        //System.out.println(blogMapper.listBlogTag(7));
        //blogMapper.addView(7,1);
        //System.out.println(userMapper.getUserById(1));
        /*List<Blog> blogs = blogMapper.listBlogByTypeId(1);
        System.out.println(blogs);*/
        /*List<Blog> blogs = blogMapper.listBlogByTagId(3);
        System.out.println(blogs);*/

        List<Type> typeList = typeService.listTypeAndCount();
        System.out.println(typeList);
    }
}
