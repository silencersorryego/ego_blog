package com.pc.service.impl;

import com.pc.domain.Blog;
import com.pc.domain.Tag;
import com.pc.mapper.IBlogMapper;
import com.pc.mapper.ITagMapper;
import com.pc.mapper.ITypeMapper;
import com.pc.mapper.IUserMapper;
import com.pc.service.IBlogService;
import com.pc.utils.MarkDownUtils;
import com.pc.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    IBlogMapper blogMapper;
    @Autowired
    ITypeMapper typeMapper;
    @Autowired
    ITagMapper tagMapper;
    @Autowired
    IUserMapper userMapper;

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);

        int i = blogMapper.saveBlog(blog);
        List<Tag> tags = blog.getTags();
        for (Tag tag : tags) {
            blogMapper.saveBlogAndTag(blog.getId(),tag.getId());
        }
        return i;
    }

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = blogMapper.getBlogById(id);
        blogMapper.addView(id,blog.getViews()+1);
        blog.setContent(MarkDownUtils.MarkDownToHtmlExtension(blog.getContent()));
        blog.setUser(userMapper.getUserById(blog.getUserId()));
        blog.setType(typeMapper.getTypeById(blog.getTypeId()));
        blog.setTags(blogMapper.listBlogTag(id));
        return blog;
    }

    @Override
    public int updateBlog(Blog blog) {
        return 0;
    }

    @Override
    public int deleteBlog(Integer id) {
        blogMapper.deleteBlogAndTags(id);
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<BlogQuery> getBlogBySearch(Blog blog) {
        return blogMapper.getBlogBySearch(blog);
    }

    @Override
    public List<Blog> listBlogByTypeId(Integer typeId) {
        return blogMapper.listBlogByTypeId(typeId);
    }

    @Override
    public List<Blog> listBlogByTagId(Integer tagId) {
        return blogMapper.listBlogByTagId(tagId);
    }
}
