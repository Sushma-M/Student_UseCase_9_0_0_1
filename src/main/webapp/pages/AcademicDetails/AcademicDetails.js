Application.$controller("AcademicDetailsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };


    // $scope.School_DBAcademicsDataonSuccess = function(variable, data) {
    //     debugger;
    //     console.log(data);
    //     var studentAcademics = $scope.Variables.School_DBStudentAcademics.dataSet.data;
    //     console.log(studentAcademics);
    //     var length = studentAcademics.length;
    //     var count = 0;
    //     for (var i = 0; i < length; i++) {
    //         var count = _.countBy(studentAcademics[i].standardId);
    //         count++;
    //         // var length=studentAcademics.length;
    //         // var count=0;
    //         // for(var i=0; i<length;i++){

    //     }
    // };


    $scope.School_DBStudentAcademicsonSuccess = function(variable, data) {
        debugger;
        var studentAcademics = $scope.Variables.School_DBStudentAcademics.dataSet.data;
        console.log(studentAcademics);
        var length = studentAcademics.length;
        // var count = 0;
        // for (var i = 0; i < length; i++) {
        //     var count = _.countBy(studentAcademics[i].standardId);
        //     count++;
        //     // var length=studentAcademics.length;
        // }
    };

}]);


Application.$controller("grid_academicsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform_academicsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);