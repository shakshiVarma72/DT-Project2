myApp.controller("userController",function($scope,$http, $window,$location,$rootScope,$cookieStore){
	
	$scope.user={loginName:'',firstName:'',lastName:'',password:'',email:'',role:'',onlineStatus:'',mobileNumber:''};
	
	$scope.Register=function(){
		console.log('Enter into Register function '+$scope.user.loginName+" "+$scope.user.firstName);
		
		
		$http({
		    url : "http://localhost:8088/Middleware1w/register",
		    method : 'POST',
		    data: $scope.user,
		    transformResponse: [
		        function (data) {
		        	$window.location.reload();
		        	alert('User Registered Succesfully');
		            return data;
		            
		        }
		    ]
		}) 
	}
	
	
	$scope.login=function(){
		console.log('Enter into login Function '+$scope.user.email+" "+$scope.user.password);
		$http({
		    url : "http://localhost:8088/Middleware1w/login",
		    method : 'POST',
		    data: $scope.user,
		
		}).then(function successCallback(response) {
				console.log("I m here");
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				$cookieStore.put('userDetails',response.data);
				
				console.log($rootScope.currentUser.role);
				
				if($rootScope.currentUser.role=='Role_User'){
					console.log('Role is User')
					$location.path("UserHome")
					
				}
				if($rootScope.currentUser.role=='Role_Admin'){
					console.log('Role is Admin');
					$location.path("AdminHome");
				}
				
				
		  }, function errorCallback(response) {
			  	alert('Username or password is incorrect. Try Again');
		});
	}
	
	
	$rootScope.logout=function(){
		console.log('Logout');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path("logout");
	}
	
	 $scope.fetchUserDetails=function(){
         console.log("Inside fetch user Details function "+$rootScope.currentUser.loginName);
         $http.get("http://localhost:8088/Middleware1w/getUser/"+$rootScope.currentUser.loginName)
         .then(function(response){
             console.log("fetched User");
              
             $scope.user=response.data;
             $rootScope.currentUser=response.data;
             $cookieStore.put('userDetails',response.data);
         });
          
     };
      
     $scope.updateProfilePicture=function(){
         console.log("Update Profile Picture");
          
     };
  
     $scope.updateProfile=function(){
         console.log("Update Profile function");
         $http({
         url:"http://localhost:8088/Middleware1w/updateUser",
         method :'POST',
         data:$scope.user,
         })
         
         .then(function successCallback(response){
        	 $scope.user=response.data;
             $rootScope.CurrentUser=response.data;
             $cookieStore.put('userDetails',response.data);
             
             
             alert("Profile Updated!!!");
             $location.path("viewProfile");
         },
         function errorCallback(response){
        	 alert("Profile Updated!!!");
        	 $location.path("viewProfile");
         });
     }
      
});