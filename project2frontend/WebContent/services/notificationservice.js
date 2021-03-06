/**
 * NotificationService
 */
app.factory('NotificationService',function($http){
	var notificationService={}
	
	notificationService.getAllNotifications=function(){
		return $http.get("http://localhost:8090/project2middleware/getallnotifications")
	}
	
	
	notificationService.getNotification=function(notificationId){
		return $http.get("http://localhost:8090/project2middleware/getnotification/"+notificationId)
	}
	
	notificationService.updateNotification=function(notificationId){
		return $http.put("http://localhost:8090/project2middleware/updatenotification/"+notificationId);
	}
	
	return notificationService;
})
