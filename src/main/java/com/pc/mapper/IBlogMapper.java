package com.pc.mapper;


import com.pc.domain.Blog;
import com.pc.domain.Tag;
import com.pc.domain.Type;
import com.pc.vo.BlogQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IBlogMapper {

    int saveBlog(Blog blog); //

    Blog getBlogById(Integer id); //

    int updateBlog(Blog blog);  //

    @Update("update blog set views = #{view} where id =#{blog_id}")
    int addView(int blog_id,int view);

    int deleteBlog(Integer id);  //

    List<BlogQuery> listBlog();  //

    List<Blog> getAllBlog();

    int saveBlogAndTag(@Param("blogId") Integer blogId,@Param("tagId") Integer tagId);

    int deleteBlogAndTags(Integer id);
    List<BlogQuery> getBlogBySearch(Blog blog);

    @Select("select t.id,t.name from blog_tag bt inner join tag t on bt.tag_id=t.id where blog_id = #{blog_id}")
    List<Tag> listBlogTag(int blog_id);

    @Select("select t.id,t.name from blog b inner join type t on b.type_id=t.id where b.id = #{blog_id}")
    Type getBlogType(int blog_id);

    List<Blog> listBlogByTypeId(Integer typeId);

    List<Blog> listBlogByTagId(Integer tagId);
}
