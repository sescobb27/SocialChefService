SocialChef.ProductsController = Ember.ArrayController.extend({
    result: function(products) {
        console.log(products);
        // this.transitionToRoute('products', products);
    }
});

SocialChef.ProductController = Ember.ObjectController.extend({

});
