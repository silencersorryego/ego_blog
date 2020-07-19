package com.pc.service;

import com.pc.domain.Blog;
import com.pc.vo.BlogQuery;

import java.util.List;

public interface IBlogService {
    int saveBlog(Blog blog); //

    Blog getBlogById(Integer id); //

    int updateBlog(Blog blog);  //

    int deleteBlog(Integer id);  //

    List<BlogQuery> listBlog();  //
    List<Blog> getAllBlog();

    List<BlogQuery> getBlogBySearch(Blog blog);

    List<Blog> listBlogByTypeId(Integer typeId);

    List<Blog> listBlogByTagId(Integer tagId);
}
