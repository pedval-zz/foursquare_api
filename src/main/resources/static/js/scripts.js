var app = angular.module('squareView',[]);

app.controller('ShowMapController', ['$scope', "$http", function($scope, $http) {
    angular.element(document).ready(function () {
        $scope.init();
    });

    $scope.search = function(){

        $http.get('/poi/find/'+encodeURIComponent($scope.query)).
            //Return code = 200
            success(function(locations) {
                var marker;
                $scope.markers = [];

                for (var i = 0; i < locations.length; i++) {
                    // Add the markers
                    marker = new google.maps.Marker({
                        position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
                        map: $scope.map,
                        animation: google.maps.Animation.DROP,
                        icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
                    });

                    $scope.markers.push(marker);

                    //Add Info per marker (name, address and postalCode)
                    google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function() {
                            var info = locations[i].name;
                            if(locations[i].address) {
                                info = info.concat("<br/>"+locations[i].address);
                            }
                            if(locations[i].postalCode) {
                                info = info.concat("<br/>"+locations[i].postalCode);
                            }
                            $scope.infowindow.setContent(info);
                            $scope.infowindow.open($scope.map, marker);
                        }
                    })(marker, i));

                    //Center view to pois
                    var bounds = new google.maps.LatLngBounds();

                    $.each($scope.markers, function (index, marker) {
                        bounds.extend(marker.position);
                    });

                    $scope.map.fitBounds(bounds);
                }

            }).
            //Return code != 200
            error(function() {
                $scope.init();
            });
    }


    /*This function place the view in central London,
        and will be called on page load and when no result
        returned
     */
    $scope.init = function(){

        var latlng = new google.maps.LatLng(51.510492, -0.127147);

        var mapOptions = {
            center: latlng,
            scrollWheel: false,
            zoom: 13
        };

        $scope.infowindow = new google.maps.InfoWindow({
            maxWidth: 180
        });

        $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
    }
}]);
