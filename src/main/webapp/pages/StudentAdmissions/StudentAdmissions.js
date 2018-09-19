Application.$controller("StudentAdmissionsPageController", ["$scope", function($scope) {
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
        var x = $scope.Variables.School_DBStudentDetailsData.listRecords({}, function() {
            console.log(x);
            var studentData = $scope.Variables.School_DBStudentDetailsData.dataSet.data;
            var studentLength = studentData.length;
            var i = 0;
            for (i = 0; i < studentLength; i++) {
                $scope.Variables.StVar_StudentData.dataSet.push(studentData[i]);
            }
        });
    };
}]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.liveform_StudentDetailsSuccess = function($event, $operation, $data) {
            $scope.Variables.School_DBStudentDetailsData.listRecords({}, function(newData) {
                debugger;
                console.log(newData);
                var studentData = $scope.Variables.School_DBStudentDetailsData.getData();
                var studentLength = studentData.length;
                $scope.Variables.StVar_StudentData.dataSet.splice(1, studentLength);
                var i = 0;
                for (i = 0; i < studentLength; i++) {
                    $scope.Variables.StVar_StudentData.dataSet.push(studentData[i]);
                }
            });
        };

    }
]);
Application.$controller("liveform_StudentDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.currentDay = moment().format("YYYY-MM-DD");
    }
]);