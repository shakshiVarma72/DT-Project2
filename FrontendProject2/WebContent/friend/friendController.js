myApp.controller('FriendCtrl', function($scope, $location, $rootScope, FriendService){
    
	function getAllSuggestedUsers(){
        console.log('Entering friend controller')
        FriendService.getAllSuggestedUsers().then(function(response){
                $scope.suggestedUsers=response.data
            },function(response){
                $rootScope.error=response.data
                if(response.status==401)
                    $location.path('/login')
            })
         
        }

	
	getAllSuggestedUsers();
    
	
    function getPendingRequests(){
        FriendService.getPendingRequests().then(
                function(response){
            $scope.pendingRequests=response.data
        },
                function(response){
            $rootScope.error=response.data
            if(response.status==401)
                $location.path('/login')
        })
    }
    
    getPendingRequests();
     
    $scope.addFriend=function(toId){
        FriendService.addFriend(toId).then(
                function(response){
            alert('Freind Request has been sent successfully..')
            getAllSuggestedUsers()
        },
                function(response){
            $rootScope.error=response.data
            if(response.status==401)
                $location.path('/login')
        })
         
        }
     
    $scope.acceptRequest=function(request){//object of friend
        //update status from 'P' to 'A'
        FriendService.acceptRequest(request).then(
                function(response){
                    getPendingRequests()
        },
                function(response){
            $rootScope.error=response.data
            if(response.status==401)
                $location.path('/login')
        })
    }
 
    $scope.deleteRequest=function(request){//object of friend
        //update status from 'P' to 'A'
        FriendService.deleteRequest(request).then(
                function(response){
                    getPendingRequests()
        },
                function(response){
            $rootScope.error=response.data
            if(response.status==401)
                $location.path('/login')
        })
    }
     
    $scope.getAllFriends=function(request){
    	console.log('Getting all friends');
    FriendService.getAllFriends().then(
            function(response){
            	console.log('Getting all friends success');
        $scope.friends=response.data
        console.log($scope.friends);
    },
            function(response){
    	console.log('Getting all error');
        $rootScope.error=response.data
        if(response.status==401)
            $location.path('/login')
    }) 
    }
}) 	