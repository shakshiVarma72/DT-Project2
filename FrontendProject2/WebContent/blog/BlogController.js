myApp.controller("blogController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
	$scope.blog={blogName:'',blogContent:'',createDate:'',likes:'',status:'',loginName:''};
	
	$scope.addBlog=function(){
		console.log('Enter into addBlog function ');
		
		
		$http({
		    url : "http://localhost:8088/Middleware1w/addBlog",
		    method : 'POST',
		    data: $scope.blog,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('Blog Added Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	};
	
	
	$scope.listAllBlogs=function(){
		console.log('Entered into listAllBlogs function');
		$http.get("http://localhost:8088/Middleware1w/listBlogs")
		.then(function(response){
			console.log('Data found');
			$scope.allBlogs=response.data;
		},
		function(error){
			console.log('No Blogs found');
			$scope.allBlogs=[];
			$scope.viewMessage="No Blogs Found...!!!";
		});
	};	
	
    
	$scope.listPendingBlogs=function(){
	        console.log('Entered into listAllBlogs function');
	        $http.get("http://localhost:8088/Middleware1w/listPendingBlogs")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allBlogs=response.data;
	        },
	        function(error){
	            console.log('No Blogs found');
	            $scope.allBlogs=[];
	            $scope.viewMessage="No Blogs Found...!!!";
	        });
	    };
	    
	    

	    $scope.approveBlog=function(bid){
	        console.log('Entered into approve blog function function');
	        $http({
			    url : "http://localhost:8081/Middleware1w/approveBlog/"+bid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('Approved Blog Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
		
	    $scope.rejectBlog=function(bid){
	        console.log('Entered into reject blog function function');
	        $http({
			    url : "http://localhost:8081/Middleware1w/rejectBlog/"+bid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('Rejected Blog Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
    
		$scope.listAllApprovedBlogs=function(){
	        console.log('Entered into listApprovedBlogs function');
	        $http.get("http://localhost:8088/Middleware1w/listAllApprovedBlogs")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allBlogs=response.data;
	        },
	        function(error){
	            console.log('No Blogs found');
	            $scope.allBlogs=[];
	            $scope.viewMessage="No Blogs Found...!!!";
	        });
	    };
	    
	    $scope.blogComment={commentText:'',commentDate:'',blogId:'',loginName:''};
		
	    $scope.addblogComment=function(bid){
			console.log('Enter into addBlogComment function :' +bid);
			
			$scope.blogComment.blogId=bid;
			$rootScope.blogComment=$scope.blogComment;
			
			console.log($rootScope.blogComment.blogId);
			console.log($scope.blogComment.blogId);
			
			$location.path("CommentBlogForm")
	    };
	    
	    
	    $scope.addBlogCommentProcess=function(bid){
	    	$scope.blogComment.blogId=$rootScope.blogComment.blogId;
			$http({
			    url : "http://localhost:8088/Middleware1w/addBlogComment",
			    method : 'POST',
			    data: $scope.blogComment,
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('BlogComment Added Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		};
		$scope.listAllBlogComments=function(){
	        console.log('Entered into listAllBlogComments function');
	        $http.get("http://localhost:8088/Middleware1w/listAllBlogComments")
	        .then(function(response){
	            console.log('Data found');
	            $scope.allBlogComment=response.data;
	        },
	        function(error){
	            console.log('No Blogs found');
	            $scope.allBlogComment=[];
	            $scope.viewMessage="No Blogs Found...!!!";
	        });
	    };
	    
	    $scope.incrementBlogLikes=function(bid){
	        console.log('Entered into likes  function');
	        
	        $http({
	        	url : "http://localhost:8088/Middleware1w/incrementBlogLikes/"+bid,
			    method : 'GET',
			    transformResponse: [
			        function (data) {
			        	$window.location.reload();
			        	alert('increment Succesfully');
			            return data;
			            
			        }
			    ]
			}) 
		
		};
});
