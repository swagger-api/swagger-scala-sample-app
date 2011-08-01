$(function() {
   $("#button_logout").hide();
   $("#button_login").click(function() {
        login();
   });
   $("#button_logout").click(function() {
        logout();
   });

});

function login() {
    var username = jQuery.trim($("#input_username").val());
    var password = jQuery.trim($("#input_password").val());
    if (username.length == 0 || password.length == 0) {
        $("#input_username").wiggle();
    } else {
        var invocationUrl = "http://localhost:8002/swagger-sample-app/api/user.json/login?username="+username+"&password="+password;
        $.getJSON(invocationUrl, function(response){
           $("#button_login").hide();
           $("#input_username").hide();
           $("#input_password").hide();
           $("#login_message").hide();
           $("#button_logout").show();
           resourceListController.init();
        });
    }
};

function logout() {
    var invocationUrl = "http://localhost:8002/swagger-sample-app/api/user.json/logout";
    $.getJSON(invocationUrl, function(response){
       $("#button_login").show();
       $("#input_username").show();
       $("#input_password").show();
       $("#button_logout").hide();
       $("#login_message").show();
       resourceListController.init();
    });
};
