// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.

var map;
var userMarkers = {};

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 19
  });
  var infoWindow = new google.maps.InfoWindow({map: map});

  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      infoWindow.setPosition(pos);
      infoWindow.setContent('BDD Room');

      map.setCenter(pos);
    }, function() {
      handleLocationError(true, infoWindow, map.getCenter());
    });
  } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
  }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser does not support geolocation.');
}

function fetchUserLocations() {

  var req = new XMLHttpRequest();
  req.onreadystatechange = function() {
      if (req.readyState == 4 && req.status == 200) {
          showLocations(req.responseText);
      }
  }
  req.open("GET", "https://sportstalker.apispark.net/v1/users/", true);
  req.setRequestHeader("Authorization", "Basic Y2VkMTlkYzctZGE1Zi00N2ViLWJiYzAtZmM0YTNjNjZjNjZiOjhkNzIwNWM4LTBmNGItNDE0Yy1iMDBiLWE5NWZmMWViYjA5Mw==");
  req.send(null);
}

function showLocations(responseText) {
  var users = JSON.parse(responseText);

  users.forEach(function(user) {
    var marker = userMarkers[user.username];
    if (marker) {
        marker.setPosition({lat: parseFloat(user.latitude), lng: parseFloat(user.longitude)});
    } else {
        var marker = new google.maps.Marker({
          map: map,
          position: {lat: parseFloat(user.latitude), lng: parseFloat(user.longitude)},
          icon: {url: 'mini_icon_run.png'},
          title: user.username
        });
        userMarkers[user.username] = marker;
    }
    
  });
}

setInterval(fetchUserLocations, 1000);