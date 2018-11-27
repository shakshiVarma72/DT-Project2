myApp.controller("JobController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
	$scope.job={jobDesignation:'',jobDesc:'',company:'',location:'',salary:'',lastDateApply:''};
	
	$scope.addJob=function(){
		console.log('Enter into addJob function ');
		
		
		$http({
		    url : "http://localhost:8088/Middleware1w/addJob",
		    method : 'POST',
		    data: $scope.job,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('Job Added Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	};
	$scope.listAllJobs=function(){
		console.log('Entered into listAllJobs function');
		$http.get("http://localhost:8088/Middleware1w/listAllJobs")
		.then(function(response){
			console.log('Data found');
			$scope.allJobs=response.data;
		},
		function(error){
			console.log('No Jobs found');
			$scope.allJobs=[];
			$scope.viewMessage="No Jobs Found...!!!";
		});
	};	
	$scope.addApplyJob=function(){
		console.log("Enter into apply job function")
		$http({
		    url : "http://localhost:8088/Middleware1w/addApplyJob",
		    method : 'GET',
		   
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('applyJob Added Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	};
		
	
	
});
