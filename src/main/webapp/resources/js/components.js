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
        var categories_promise = Ember.$
            .getJSON("/categories");
        categories_promise.success(function(response){
            Ember.run(function(){
                self.success("categories", response);
            });
        });
        categories_promise.fail(function(){
            Ember.run(function(){
                self.failure();
            });
         });
    },
    getLocations: function() {
        var self = this;
        var locations_promise = Ember.$
            .getJSON("/locations");
        locations_promise.success(function(response){
            Ember.run(function(){
                self.success("locations", response);
            });
        });
        locations_promise.fail(function(){
            Ember.run(function(){
                self.failure();
            });
         });
    },
    success: function(array_type, response) {
        var context = this;
        $.each(response, function(index, val) {
            context.get(array_type).pushObject(val.name);
        });
    },

    failure: function() {
    },
});