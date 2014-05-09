SocialChef.LoginController = Ember.ObjectController.extend({
    // ==========================================================================
    // PROPERTIES
    // ==========================================================================
    username: '',
    password: '',
    loginFailed: false,
    isProcessing: false,
    isSlowConnection: false,
    timeout: null,
    // ==========================================================================
    // END PROPERTIES
    // ==========================================================================
    actions : {
        login: function() {
            this.setProperties({
                loginFailed: false,
                isProcessing: true
            });

            var self = this;
            Ember.run.later(self, function(){
                self.slowConnection();
            }, 5000);

            var promise = Ember.$.ajax({
                            type: 'POST',
                            url: "/chefs/login",
                            contentType: "application/json",
                            dataType: "json",
                            data:  JSON.stringify(self.serialize())
                        });

            promise.success(function(response){
                Ember.run(function(){
                    self.success(response['username']);
                });
            }).then(function(){
                return true;
            });
            promise.fail(function(response){
                Ember.run(function(){
                    self.failure(response);
                });
             }).then(function(){
                return false;
            });
        }
    },

    success: function(username) {
        var controller = SocialChef.__container__.lookup("controller:application");
        controller.send('loggedIn', username);
        this.resetState();
        this.setProperties({
            username: '',
            password: ''
        });
        this.transitionToRoute('user', username);
    },

    failure: function(response) {
        this.resetState();
        this.set("loginFailed", true);
    },

    slowConnection: function() {
        if (!this.get('loginFailed')) {
            this.set("isSlowConnection", true);
        }
    },

    resetState: function() {
        clearTimeout(this.get("timeout"));
        this.setProperties({
            isProcessing: false,
            isSlowConnection: false
        });
    },

    serialize: function() {
        return {
            username: this.get('username'),
            password: this.get('password')
        };
    }
});
