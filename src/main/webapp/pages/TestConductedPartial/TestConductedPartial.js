Application.$controller("TestConductedPartialPageController", ["$scope", function($scope) {
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

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid_TestConductedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid_ViewResultsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialog2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.grid_UpdateResultsRowclick = function($event, $rowData) {
            console.log('grid_UpdateResultsRowclick');
        };

    }
]);

Application.$controller("grid_UpdateResultsController", ["$scope", "wmToaster",
    function($scope, wmToaster) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.marksSecuredOnChange = function($event, $isolateScope, rowData) {
            debugger;
            var marksSecuredValue = new RegExp(/^\d{2}$/);
            if (marksSecuredValue.test($isolateScope.datavalue)) {
                var acheivedMarks = $isolateScope.datavalue;
                var maxmarks = $scope.Widgets.grid_TestConducted.dataset.data[0].academicTestSubjects.maxMarks;
                var percentage = (acheivedMarks / maxmarks) * 100;
                var minval = Math.floor(percentage / 10);
                minval = minval * 10;
                $scope.Variables.LV_GradesByMinValue.listRecords({}, function() {
                    var gradeData = $scope.Variables.LV_GradesByMinValue.getData();
                    var gradeDataLength = gradeData.data.length;
                    for (var i = 0; i < gradeDataLength; i++) {
                        if (minval >= gradeData.data[i].minValue && minval <= gradeData.data[i].maxValue) {
                            minval = gradeData.data[i].minValue;
                        }
                    }
                    $scope.Variables.LV_GradesByMinValue.setFilter('minValue', minval);
                    $scope.Variables.LV_GradesByMinValue.listRecords({}, function() {
                        $scope.Widgets.grid_UpdateResults.formfields['gradeDetails.gradeId'].setProperty('value', $scope.Variables.LV_GradesByMinValue.dataSet.data[0].gradeId);
                        $scope.Widgets.grid_UpdateResults.formfields['gradeDetails.grade'].setProperty('value', $scope.Variables.LV_GradesByMinValue.dataSet.data[0].grade);
                    });
                });

            } else {
                wmToaster.show('error', 'ERROR', 'Please Enter Valid Marks', 5000);
            }
        };

        // $scope.presenceOnChange = function($event, $isolateScope, rowData) {
        //     debugger;
        //     $scope.Widgets.grid_UpdateResults.columns.marksSecured.disabled = !$isolateScope.datavalue;

        // };


        // $scope.presenceOnBlur = function($event, $isolateScope, rowData) {
        //     debugger;
        //     $scope.Widgets.grid_UpdateResults.columns.marksSecured.disabled = !$isolateScope.datavalue;
        // };

    }
]);