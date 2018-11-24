/**
 * UserCtrl
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope){
	
	$scope.registration=function(user)
	{
		UserService.registration(user)
		.then(function(response){
			$location.path('/login')
		},function(response){
			$scope.error=response.data
		})
	}
	
	$scope.login=function(user)
	{
		UserService.login(user).then(
				function(response)
				{
					$rootScope.user=response.data
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
				})
	}
	
	
	$rootScope.logout=function()
	{
	  
	  UserService.logout().then(function(response){
		  delete $rootScope.user
		  $location.path('/login')
	  },function(response){
		  delete $rootScope.user
		  if(response.status==401)
			  $location.path('/login')
	  })
	}
	
	
})
