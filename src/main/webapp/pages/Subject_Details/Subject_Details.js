Application.$controller("Subject_DetailsPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
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

    function subjectData() {
        if ($scope.Widgets.txt_subjectName.datavalue != null) {
            $scope.Variables.LV_InsertSubjectDetails.createRecord();
            wmToaster.show('success', 'SUCCESS', 'Record Added successfully', 9000);
            subjectTextHide();
        } else if ($scope.Widgets.txt_subjectName.datavalue == null) {
            wmToaster.show('error', 'ERROR', 'Subject Name cannot be null', 9000);
            subjectTextShow();
        }
    }

    function subjectTextHide() {
        $scope.Widgets.txt_subjectName.datavalue = '';
        $scope.Widgets.composite_subjectName.show = false;
        $scope.Widgets.button_addNew.show = false;
        $scope.Widgets.button_cancel.show = false;
        $scope.Widgets.button_addsubject.show = true;
    }

    function subjectTextShow() {
        $scope.Widgets.composite_subjectName.show = true;
        $scope.Widgets.button_addNew.show = true;
        $scope.Widgets.button_cancel.show = true;
        $scope.Widgets.button_addsubject.show = false;
    }
    $scope.button_addsubjectClick = function($event, $isolateScope) {
        subjectTextShow();
    };

    $scope.txt_subjectNameKeydown = function($event, $isolateScope) {
        if ($event.keyCode === 13) {
            subjectData();
        }
    };

    $scope.button_cancelClick = function($event, $isolateScope) {
        subjectTextHide();
    };

    $scope.button_addNewClick = function($event, $isolateScope) {
        subjectData();
    };



    $scope.LV_InsertSubjectDetailsonError = function(variable, data) {
        subjectTextShow();
        wmToaster.show('error', 'ERROR', 'Subject Already Exists. Please try again!', 9000);


    };

}]);