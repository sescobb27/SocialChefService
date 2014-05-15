SocialChef.PurchaseController = Ember.ObjectController.extend({
  product: null,
  isProcessing: false,
  address: '',

  actions: {
      purchase: function() {
          var self = this;
          var promise = Ember.$.ajax({
              type: 'POST',
              url: "/purchase",
              contentType: "application/json",
              dataType: "json",
              data: JSON.stringify(self.serialize())
          });
          promise.success(function(response){
              Ember.run(function(){
                  self.success(response);
              });
          });
          promise.fail(function(response){
              Ember.run(function(){
                  self.failure(response);
              });
           });
      }
  },
  success: function(response) {
      alert(response);
  },
  failure: function(response) {
      this.set('isProcessing', false);
      alert(response);
  },
  serialize: function() {
      var product = this.get('product');
      return {
          id: product.id,
          name: product.name,
          price: product.price,
          entrega: this.get('address')
      };
  }
});
