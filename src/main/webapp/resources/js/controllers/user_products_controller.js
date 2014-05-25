SocialChef.Category = Em.Object.extend({
    name: '',
    key: '',
});

SocialChef.UserProductsAddController = Ember.ObjectController.extend({
  needs: ['application'],
  application: Ember.computed.alias("controllers.application"),
  product_name: '',
  price: "",
  product_image: '',
  isProcessing: false,
  file: null,
  p_categories: Ember.A([]),
  p_locations: Ember.A([]),
  categories: Ember.A([]),
  locations: Ember.A([]),
  errors: Ember.A([]),


// ==========================================================================
// START ACTIONS
// ==========================================================================
  actions: {
      // ========================================================================
      // START ACTION VALIDATE
      // ========================================================================
      validate: function() {
          this.set('isProcessing', true);
          var name = this.get('product_name');
          var price = parseFloat(this.get('price'));
          var image = this.get('product_image');
          var errors = this.get('errors');
          errors.clear();
          if (this.empty(name)) {
              errors.pushObject('El nombre del plato no puede ser vacio');
          }
          if (this.empty(image)) {
              errors.pushObject('Debe de haber una imagen del producto que vas a vender');
          }
          if (price <= 0 || isNaN(price)) {
              errors.pushObject('El precio del plato no puede ser <= 0');
          }

          if(errors.length > 0) {
              this.set('isProcessing', false);
              return false;
          }
          this.send('addProduct');
      },
      // ======================================================================
      // END ACTION VALIDATE
      // ======================================================================


      // ======================================================================
      // START ACTION CHANGE
      // ======================================================================
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
      // ======================================================================
      // END ACTION CHANGE
      // ======================================================================


      // ======================================================================
      // START ACTION ADD_CATEGORY
      // ======================================================================
      addCategory: function(category) {
          var p_info = this.get('p_categories');
          var _category = category.replace(/ /g, '-');
          var btn = $('#'+_category+'');
          if (p_info.contains(category)) {
              p_info.removeObject(category);
              btn.disabled = false;
              btn.removeClass('btn-success').addClass('btn-info');
          } else {
              p_info.pushObject(category);
              btn.disabled = true;
              btn.removeClass('btn-info').addClass('btn-success');
          }
      },
      // ======================================================================
      // END ACTION ADD_CATEGORY
      // ======================================================================


      // ======================================================================
      // START ACTION ADD_LOCATION
      // ======================================================================
      addLocation: function(location) {
            var p_info = this.get('p_locations');
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
      }
      // ======================================================================
      // END ACTION ADD_LOCATION
      // ======================================================================

  },
// ==========================================================================
// END ACTIONS
// ==========================================================================


// ==========================================================================
// START FUNCTION EMPTY
// ==========================================================================
  empty: function(obj) {
      return obj === "" || obj === null;
  },
// ==========================================================================
// END FUNCTION EMPTY
// ==========================================================================


  // ==========================================================================
  // START FUNCTION ADD_PRODUCT
  // ==========================================================================
  addProduct: function() {
      var self = this;
      var imageData = new FormData();
      imageData.append('productname', self.get('product_name'));
      imageData.append('price', self.get('price'));
      imageData.append('image', this.get('product_image'), 'file');
      imageData.append('categories', self.get('p_categories'));
      imageData.append('locations', self.get('p_locations'));
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
  // ==========================================================================
  // END FUNCTION ADD_PRODUCT
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION RESET
  // ==========================================================================
  reset: function(){
      this.set('product_name', '');
      this.set('price', '');
      this.set('product_image', '');
      this.set('isProcessing', false);
      this.set('file', null);
      this.get('p_categories').clear();
      this.get('p_locations').clear();
      this.get('errors').clear();
  },
  // ==========================================================================
  // END FUNCTION RESET
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION SUCCESS
  // ==========================================================================
  success: function() {
      this.transitionToRoute('user', this.get('application').get('username'));
      this.reset();
  },
  // ==========================================================================
  // END FUNCTION SUCCESS
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION GET_CATEGORIES
  // ==========================================================================
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
  // ==========================================================================
  // END FUNCTION GET_CATEGORIES
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION GET_LOCATIONS
  // ==========================================================================
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
  // ==========================================================================
  // END FUNCTION GET_LOCATIONS
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION ADD_TO_ARRAY
  // ==========================================================================
  addToArray: function(array_type, response) {
      var context = this;
      $.each(response, function(index, val) {
          var object = val.name;
          if ( array_type === "categories") {
              object = SocialChef.Category.create({
                  name: val.name,
                  key: val.name.replace(/ /g, '-')
              });
          }
          if(!context.get(array_type).contains(object)) {
              context.get(array_type).pushObject(object);
          }
      });
  },
  // ==========================================================================
  // END FUNCTION ADD_TO_ARRAY
  // ==========================================================================


  // ==========================================================================
  // START FUNCTION FAILURE
  // ==========================================================================
  failure: function(response) {
    var context = this;
    $.each(response, function(index, val) {
        context.get('errors').pushObject(val);
    });
  }
  // ==========================================================================
  // END FUNCTION FAILURE
  // ==========================================================================
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
