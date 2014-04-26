SocialChef.Router.map(function() {
    this.route('about');
    this.route('login');
    this.route('register');
    this.resource( 'index', { path: '/' } );
    this.resource( 'chefs', { path: '/chefs'} );
    this.resource( 'users', function() {
        this.resource( 'user', { path: '/account/:username' } );
        this.resource( 'user_products', function() {
            this.route( 'add' );
            this.route( 'edit' );
            this.route( 'delete' );
        } );
    });
    this.resource( 'products', function () {
        this.resource( 'product', { path: ':product_name' } );
    });
    this.resource('search', function(){
        this.route('results', {path: ':query'});
    });
    this.resource('purchase', {path: '/purchase/:product'});
});

SocialChef.UsersRoute = Ember.Route.extend({
    model: function (params) {
        console.log(params);
    }
});

SocialChef.PurchaseRoute = Ember.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  setupController: function(controller, model) {},
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},
  // model: function() {}
});


SocialChef.UserRoute = Ember.Route.extend({
    // activate: function() {},
    // deactivate: function() {},
    // setupController: function(controller, model) {},
    // renderTemplate: function() {},
    // beforeModel: function() {},
    // afterModel: function() {},
    setupController: function(controller, model) {
    }
});

SocialChef.ChefsRoute = Ember.Route.extend({
    model: function (params) {
        console.log(params);
    }
});

SocialChef.IndexRoute = Ember.Route.extend({
    model: function(params) {
        console.log(params);
    }
});

SocialChef.SearchRoute = Ember.Route.extend({
    model: function(params) {
        console.log(params);
    }
});

SocialChef.SearchResultsRoute = Ember.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  // setupController: function(controller, model) {},
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},

  setupController: function(controller, model) {
      var context = this;
      console.log('Query: '+ model.query);
      Ember.$.getJSON("/products/findby", {search_value: model.query})
          .then(function(products) {
                  controller.set('model', products);
                  controller.set('title', model.query);
          });
  }
});


SocialChef.SearchController = Ember.ArrayController.extend({
    query: '',
    category: ''
});

SocialChef.SearchResultsController = Ember.ArrayController.extend({
    query: '',
    category: ''
});


SocialChef.ProductsRoute = Ember.Route.extend({
    setupController: function(productsController) {
        console.log("Fetching Products");
        Ember.$.getJSON("/products").then(function(products) {
                productsController.set('model', products);
        });
    }
});

SocialChef.ProductRoute = Ember.Route.extend({
    model: function (params) {
        console.log("On Product Route: " + params);
        // return this.store.find('product', params.id);
    }
});

SocialChef.UserProductsAddRoute = Em.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  // setupController: function(controller, model) {},
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},

  model: function() {
      return ;
  }
});

SocialChef.UserProductsEditRoute = Em.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  // setupController: function(controller, model) {},
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},

  model: function() {
      return ;
  }
});

SocialChef.UserProductsDeleteRoute = Em.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  // setupController: function(controller, model) {},
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},

  model: function() {
      return ;
  }
});

SocialChef.UserProductsIndexRoute = Em.Route.extend({
  // activate: function() {},
  // deactivate: function() {},
  setupController: function(controller, model) {
      Ember.$.getJSON("/chefs/listproducts").then(function(products) {
              controller.set('model', products);
      });
  },
  // renderTemplate: function() {},
  // beforeModel: function() {},
  // afterModel: function() {},

  model: function() {
      return ;
  }
});
