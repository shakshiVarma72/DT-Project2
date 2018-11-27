
 myApp.factory('FriendService',function($http){
	    var friendService={}
	     
	    friendService.getAllSuggestedUsers=function(){
	        return $http.get("http://localhost:8088/Middleware1w/suggestedusers")
	    }
	    friendService.addFriend=function(toId){
	        return $http.post("http://localhost:8088/Middleware1w/addfriend",toId)
	    }
	     
	    friendService.getPendingRequests=function(){
	        return $http.get("http://localhost:8088/Middleware1w/pendingrequests")
	    }
	     
	    friendService.acceptRequest=function(request){
	        return $http.put("http://localhost:8088/Middleware1w/acceptrequest",request)
	    }
	     
	    friendService.deleteRequest=function(request){
	        return $http.put("http://localhost:8088/Middleware1w/deleterequest",request)
	    }
	     
	    friendService.getAllFriends=function(){
	        return $http.get("http://localhost:8088/Middleware1w/friends")
	    }
	     
	    return friendService;
	})