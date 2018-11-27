myApp.controller("forumController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
	$scope.forum={forumName:'',forumContent:'',createDate:'',status:'',loginName:''};
	
	$scope.addForum=function(){
		console.log('Enter into addforum function ');
		
		
		$http({
		    url : "http://localhost:8088/Middleware1w/addForum",
		    method : 'POST',
		    data: $scope.forum,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('forum Added Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	};
	
	$scope.listAllForums=function(){
		console.log('Entered into listAllForums function');
		$http.get("http://localhost:8088/Middleware1w/listForums")
		.then(function(response){
			console.log('Data found');
			$scope.allForums=response.data;
		},
		function(error){
			console.log('No Forums found');
			$scope.allForums=[];
			$scope.viewMessage="No Forums Found...!!!";
		});
	};	
	$scope.listPendingForums=function(){
        console.log('Entered into listAllForums function');
        $http.get("http://localhost:8088/Middleware1w/listPendingForums")
        .then(function(response){
            console.log('Data found');
            $scope.allForums=response.data;
        },
        function(error){
            console.log('No Forums found');
            $scope.allForums=[];
            $scope.viewMessage="No Forums Found...!!!";
        });
	};
});
