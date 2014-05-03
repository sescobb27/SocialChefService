SocialChef.ProductsSearchComponent = Ember.Component.extend({
  tagName: '',
  classNames: [],
  actions: {
      search: function () {
          this.sendAction('search',{
              query: this.get("search_query")
          });
      }
  }
});

SocialChef.LeftPanelComponent = Ember.Component.extend({
    tagName: '',
    classNames: [],
    categories: Ember.A([]),
    locations: Ember.A([]),
    didInsertElement: function() {
      this.send('get') ;
    },

    actions: {
      get: function() {
          this.getCategories();
          this.getLocations();
      }
    },
    getCategories: function() {
        var self = this;
        var categories_promise = Ember.$.getJSON("/categories");
        categories_promise.success(function(response){
            Ember.run(function(){
                self.success("categories", response);
            });
        });
        categories_promise.fail(function(response){
            Ember.run(function(){
                self.failure(response);
            });
         });
    },
    getLocations: function() {
        var self = this;
        var locations_promise = Ember.$.getJSON("/locations");
        locations_promise.success(function(response){
            Ember.run(function(){
                self.success("locations", response);
            });
        });
        locations_promise.fail(function(response){
            Ember.run(function(){
                self.failure(response);
            });
         });
    },
    success: function(array_type, response) {
        var context = this;
        $.each(response, function(index, val) {
            context.get(array_type).pushObject(val.name);
        });
    },

    failure: function(response) {
    },
});

SocialChef.AuthPanelComponent = Ember.Component.extend({
  tagName: '',
  classNames: [],
  providers: Ember.A([]),
  didInsertElement: function() {
      this.get('providers').pushObjects(["Twitter", "Facebook", "Instagram"]);
  },

  actions: {
      signIn: function(provider) {
          var promise = null;
          if ( provider=="Twitter" ) {
                  var self = this;
                  promise = Ember.$.ajax({
                      type: 'POST',
                      url: "https://api.twitter.com/oauth/request_token",
                  });
                  promise.success(function(response){
                      Ember.run(function(){
                          self.twitterSuccess(response);
                      });
                  });
                  promise.fail(function(response){
                      Ember.run(function(){
                          self.failure(response);
                      });
                  });
          } else if ( provider=="Facebook" ) {

          } else if ( provider == "Instagram" ) {

          }
      }
  },

  twitterSuccess: function(response) {
      console.log(response);
  }
});
