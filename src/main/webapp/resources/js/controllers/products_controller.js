SocialChef.ProductsController = Ember.ArrayController.extend({
    needs: ['purchase'],
    purchase: Ember.computed.alias("controllers.purchase"),
    result: function(products) {
        console.log(products);
        // this.transitionToRoute('products', products);
    },
    actions: {
      buy: function(product) {
          this.transitionToRoute('purchase', product.name);
          this.get('purchase').set('product', product);
      }
    }
});

SocialChef.ProductController = Ember.ObjectController.extend({

});
