/**
 * JobService
 */
app.factory('JobService',function($http){
var jobService={}
	
jobService.addJob=function(job){
	return $http.post("http://localhost:8090/project2middleware/addjob",job)
}

jobService.getAlljobs=function(){
	return $http.get("http://localhost:8090/project2middleware/getalljobs")
}

return jobService;
})



