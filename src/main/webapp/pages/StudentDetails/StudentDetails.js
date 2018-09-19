Application.$controller("StudentDetailsPageController", ["$scope", function($scope) {
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

}]);


Application.$controller("grid1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);





Application.$controller("grid_ResultsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform_ResultsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;



        $scope.academicTestSubjectsChange = function($event, $isolateScope, newVal, oldVal) {
            //debugger;
            // $scope.Variables.StVar_MarksSecured.dataSet.marksSecured = $scope.Widgets.marksSecured.datavalue;
            // $scope.Variables.StVar_MarksSecured.dataSet.academicTestSubjects = $scope.Widgets.academicTestSubjects.datavalue;
            var acheivedMarks = $scope.Widgets.marksSecured.datavalue;
            var maxmarks = $scope.Widgets.academicTestSubjects.datavalue.maxMarks
            var percentage = (acheivedMarks / maxmarks) * 100;
            var minval = Math.floor(percentage / 10);
            minval = minval * 10;
            $scope.Variables.LV_GradesByMinValue.setFilter('minValue', minval);
            $scope.Variables.LV_GradesByMinValue.listRecords({}, function() {
                $scope.Widgets.gradeDetails.datavalue = $scope.Variables.LV_GradesByMinValue.dataSet.data[0];
            });


        };


    }
]);

Application.$controller("grid4Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform4Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid_ResultsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid6_1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid_AcademicTestSubjectsController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveform_AcademicTestSubjectsController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);