SocialChef.RegisterController = Ember.ObjectController.extend({
    // ==========================================================================
    // PROPERTIES
    // ==========================================================================
    name: '',
    last_name: '',
    username: '',
    email: '',
    password: '',
    confirm_password: '',
    invalidPass: false,
    isProcessing: false,
    // ==========================================================================
    // END PROPERTIES
    // ==========================================================================

    actions: {
        validate: function () {
            this.set('isProcessing', true);
            var name = $.trim(this.get("name"));
            var last_name = $.trim(this.get("last_name"));
            var username = $.trim(this.get("username"));
            var email = $.trim(this.get('email'));
            var pass = $.trim(this.get('password'));
            var confirm = $.trim(this.get('confirm_password'));
            if (!this.empty(pass) && !this.empty(confirm)) {
                if (pass === confirm) {
                    this.set('invalidPass', false);
                } else {
                    this.set('invalidPass', true);
                    return false;
                }
            } else {
                this.set('invalidPass', true);
                this.set('isProcessing', false);
                return false;
            }
            if (!this.empty(name) &&
                    !this.empty(last_name) &&
                    !this.empty(username) &&
                    !this.empty(email)) {
                this.send('register');
            }
        },

        register: function() {
            var self = this;
            var promise = Ember.$.ajax({
                type: 'POST',
                url: "/chefs",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(self.serialize())
            });
            promise.success(function(){
                Ember.run(function(){
                    self.success();
                });
            });
            promise.fail(function(){
                Ember.run(function(){
                    self.set('isProcessing', false);
                });
             });
        }
    },
    empty: function(obj) {
        return obj === "" || obj === null;
    },
    serialize: function() {
        return {
            name: this.get('name'),
            lastName: this.get('last_name'),
            userName: this.get('username'),
            email: this.get('email'),
            password: this.get('password')
        };
    },
    success: function() {
        var controller = SocialChef.__container__.lookup("controller:application");
        controller.send('loggedIn', this.get('username'));
        this.transitionToRoute('user', this.get('username'));
        this.reset();
    }
});
