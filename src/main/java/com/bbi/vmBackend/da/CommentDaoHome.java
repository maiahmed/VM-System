package com.bbi.vmBackend.da;

import java.util.List;

import com.bbi.vmBackend.da.dao.Comment;

public interface CommentDaoHome {
	public List<Comment> listAll();

	public Comment getById(int id);

	public boolean insert(Comment comment);

	public boolean update(Comment comment);

	public boolean delete(Comment comment);
}
