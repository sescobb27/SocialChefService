SocialChef.PurchaseController = Ember.ObjectController.extend({
  needs: ['product'],
  product: null,
  isProcessing: false,

  actions: {
      purchase: function() {
          var self = this;
          var promise = Ember.$.ajax({
              type: 'POST',
              url: "/purchase",
              contentType: "application/json",
              dataType: "json",
              data: JSON.stringify(self.get('product'))
          });
          promise.success(function(){
              Ember.run(function(){
                  self.success();
              });
          });
          promise.fail(function(){
              Ember.run(function(){
                  self.set('isProcessing', false);
              });
           });
      }
  },
  success: function() {

  }
});
