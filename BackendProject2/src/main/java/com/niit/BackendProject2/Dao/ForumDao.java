package com.niit.BackendProject2.Dao;

import java.util.List;

import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.Forum;
import com.niit.BackendProject2.dto.ForumComment;

public interface ForumDao {
	
		public boolean addForum(Forum forum);
		public boolean deleteForum(Forum forum);
		public boolean updateForum(Forum forum);
		public Forum getForum(int forumId);
		public boolean approveForum(Forum forum);
		public boolean rejectForum(Forum forum);
		public List<Forum> listForums(String userName);
		public boolean addForumComment(ForumComment ForumComment);
		public boolean deleteForumComment(ForumComment ForumComment);
		public ForumComment getForumComment(int commentId);
		public List<ForumComment> listForumComments(int ForumId);
		public List<Forum> listAllApprovedForums();
		public List<Forum> listPendingForums();
	}

