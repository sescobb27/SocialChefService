SocialChef.UserController = Ember.ObjectController.extend({
  // needs: ['application'],
  // application: Ember.computed.alias("controllers.application")
  actions: {
      addProduct: function() {
          this.transitionToRoute('user_products.add');
      },
      showProducts: function() {
          this.transitionToRoute('user_products.index');
      }
  }
});
