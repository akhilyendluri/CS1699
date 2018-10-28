package com.cs1699.project.blogspot.datasource;

public class Blog {
	
	private String username;
	private String blogData;
	private String postDate;
	
	public Blog() {
		super();
	}
	
	public Blog(String username, String blogData, String postDate) {

		this.username = username;
		this.blogData = blogData;
		this.postDate = postDate;
	}

	public String getUsername() {
		return username;
	}

	public String getBlogData() {
		return blogData;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBlogData(String blogData) {
		this.blogData = blogData;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		
		return "Blog [username=" + username + ", blodData=" + blogData + ", postDate=" +postDate+ "]";
		
	}
	
	
	
}
