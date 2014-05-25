SocialChef.SearchResultsController = Ember.ArrayController.extend({
    needs: ['purchase'],
    purchase: Ember.computed.alias("controllers.purchase"),
    title: '',
    actions: {
        buy: function(product) {
            this.transitionToRoute('purchase', product.name);
            this.get('purchase').set('product', product);
        }
    }
});
