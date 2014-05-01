SocialChef.UserProductsAddController = Ember.ObjectController.extend({
  needs: ['application'],
  application: Ember.computed.alias("controllers.application"),
  product_name: '',
  price: "",
  product_image: '',
  isProcessing: false,
  file: null,
  product_info: Ember.A([]),
  categories: Ember.A([]),
  locations: Ember.A([]),
  errors: Ember.A([]),

  getCategories: function() {
      var self = this;
      var categories_promise = Ember.$.getJSON("/categories");
      categories_promise.success(function(response){
          Ember.run(function(){
              self.addToArray("categories", response);
          });
      });
      categories_promise.fail(function(response){
          Ember.run(function(){
              self.failure(response);
          });
       });
  },

  getLocations: function() {
      var self = this;
      var locations_promise = Ember.$.getJSON("/locations");
      locations_promise.success(function(response){
          Ember.run(function(){
              self.addToArray("locations", response);
          });
      });
      locations_promise.fail(function(response){
          Ember.run(function(){
              self.failure(response);
          });
       });
  },

  addToArray: function(array_type, response) {
      var context = this;
      $.each(response, function(index, val) {
          context.get(array_type).pushObject(val.name);
      });
  },

  failure: function(response) {
    var context = this;
    $.each(response, function(index, val) {
        context.get('errors').pushObject(val);
    });
  },

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
      },

      addCategory: function(category) {
          var p_info = this.get('product_info');
          var btn = $('#'+category+'');
          if (p_info.contains(category)) {
              p_info.removeObject(category);
              btn.disabled = false;
              btn.removeClass('btn-success').addClass('btn-info');
          } else {
              p_info.pushObject(category);
              btn.disabled = true;
              btn.removeClass('btn-info').addClass('btn-success');
          }
          console.log(p_info);
      },

      addLocation: function(location) {
            var p_info = this.get('product_info');
            var btn = $('#'+location+'');
            if (p_info.contains(location)) {
                p_info.removeObject(location);
                btn.disabled = false;
                btn.removeClass('btn-success').addClass('btn-info');
            } else {
                p_info.pushObject(location);
                btn.disabled = true;
                btn.removeClass('btn-info').addClass('btn-success');
            }
            console.log(p_info);
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
      this.reset();
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
