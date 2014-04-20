SocialChef.ApplicationController = Ember.ObjectController.extend({
    username: '',
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
        }
    }
});