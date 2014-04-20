SocialChef.UserProductsAddController = Ember.ObjectController.extend({
  needs: ['application'],
  application: Ember.computed.alias("controllers.application"),
  product_name: '',
  price: "",
  product_image: '',
  isProcessing: false,
  file: null,
  errors: Ember.A([]),
  actions: {
      validate: function() {
          this.set('isProcessing', true);
          var name = this.get('product_name');
          var price = parseFloat(this.get('price'));
          var image = this.get('product_image');
          if (this.empty(name)) {
              this.get('errors').pushObject('El nombre del plato no puede ser vacio');
          }
          if (this.empty(image)) {
              this.get('errors').pushObject('Debe de haber una imagen del producto que vas a vender');
          }
          if (price <= 0 || isNaN(price)) {
              this.get('errors').pushObject('El precio del plato no puede ser <= 0');
          }

          if(this.get('errors').length > 0) {
              this.set('isProcessing', false);
              return false;
          }
          this.send('addProduct');
      },
      change: function(event) {
          var reader = new FileReader();
          var context = this;
          this.set('product_image', context.target.files[0]);
          reader.onload = function (event) {
              var image = event.target.result;
              Ember.run(function() {
                  $('#image_thumbnail').attr('src', image);
                  // context.set('file', image);
              });
          };
          return reader.readAsDataURL(event.target.files[0]);
      }
  },
  empty: function(obj) {
      return obj === "" || obj === null;
  },
  addProduct: function() {
      var self = this;
      var imageData = new FormData();
      imageData.append('username', self.get('application').get('username'));
      imageData.append('productname', self.get('product_name'));
      imageData.append('price', self.get('price'));
      imageData.append('image', this.get('product_image'), 'file');
      var promise = Ember.$.ajax({
          type: 'POST',
          url: "/chefs/addproduct",
          processData: false,
          contentType: false,
          data: imageData
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
  },
  success: function() {
      this.transitionToRoute('user', this.get('application').get('username'));
  }
});

SocialChef.UserProductsEditController = Ember.ObjectController.extend({
  // needs: [],
});

SocialChef.UserProductsDeleteController = Ember.ObjectController.extend({
  // needs: [],
});

SocialChef.UserProductsIndexController = Ember.ObjectController.extend({
  // needs: [],
});
