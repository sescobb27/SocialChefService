SocialChef.ApplicationController = Ember.ObjectController.extend({
    user_username: '',
    user_password: null,
    category: '',
    actions: {
        search: function (search_query) {
            this.transitionToRoute('search.results', search_query);
        },
        change: function(category) {
            console.log('Change Category');
            console.log(category);
            // console.log('Category: ' + this.get('category'));
            this.set('category', category);
        },
        loggedIn: function(username) {
            this.set('user_username', username);
        },
        login: function() {
            var user = this.get('user_username');
            var pass = this.get('user_password');
            var login_controller =
                SocialChef.__container__.lookup("controller:login");
            login_controller.set('username', user);
            login_controller.set('password', pass);
            if (!login_controller.send('login')) {
                login_controller.resetState();
                this.transitionToRoute('login');
            }
            Ember.$('#login-modal').modal('toggle');
            this.set('user_username', '');
            this.set('user_password', '');
        },
        register: function() {
            Ember.$('#login-modal').modal('toggle');
            this.transitionToRoute('register');
        }
    }
});