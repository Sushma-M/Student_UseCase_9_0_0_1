Application.$controller("Academic_DetailsPageController", ["$scope", function($scope) {
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


Application.$controller("grid_academicYearController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform_academicYearController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.startYearChange = function($event, $isolateScope, newVal, oldVal) {
            var acStartYear = $scope.Widgets.startYear.datavalue;
            $scope.Widgets.endYear.datavalue = acStartYear + 1;
            $scope.Widgets.academicYear.datavalue = acStartYear + '-' + $scope.Widgets.endYear.datavalue;
        };
    }
]);