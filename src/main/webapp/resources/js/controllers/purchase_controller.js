SocialChef.PurchaseController = Ember.ObjectController.extend({
  product: null,
  isProcessing: false,
  address: '',

  actions: {
      purchase: function() {
          var self = this;
          this.set('isProcessing', true);
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
      alert(response.msg);
      this.setProperties({
          product: null,
          isProcessing: false,
          address: ''});
      this.transitionToRoute('index');
  },
  failure: function(response) {
      this.set('isProcessing', false);
      alert(response.errors);
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
