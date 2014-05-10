SocialChef.ApplicationController = Ember.ObjectController.extend({
    username: '',
    password: null,
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
            this.set('username', username);
        },
        login: function() {
            var user = this.get('username');
            var pass = this.get('password');
            var login_controller =
                SocialChef.__container__.lookup("controller:login");
            login_controller.set('username', user);
            login_controller.set('password', pass);
            if (!login_controller.send('login')) {
                login_controller.resetState();
                this.transitionToRoute('login');
            }
            Ember.$('#login-modal').modal('toggle');
            this.set('username', '');
            this.set('password', '');
        },
        register: function() {
            Ember.$('#login-modal').modal('toggle');
            this.transitionToRoute('register');
        }
    }
});