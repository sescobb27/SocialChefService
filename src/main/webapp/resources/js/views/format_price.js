Ember.Handlebars.helper('formatPrice', function(value, options) {
  return parseInt(value).format()+"$";
});