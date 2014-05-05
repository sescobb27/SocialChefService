SocialChef.ProductsSearchComponent = Ember.Component.extend({
  tagName: '',
  classNames: [],
  actions: {
      search: function () {
          this.sendAction('search',{
              query: this.get("search_query")
          });
      }
  }
});

SocialChef.LeftPanelComponent = Ember.Component.extend({
    tagName: '',
    classNames: [],
    categories: Ember.A([]),
    locations: Ember.A([]),
    didInsertElement: function() {
      this.send('get') ;
    },

    actions: {
      get: function() {
          this.getCategories();
          this.getLocations();
      }
    },
    getCategories: function() {
        var self = this;
        var categories_promise = Ember.$.getJSON("/categories");
        categories_promise.success(function(response){
            Ember.run(function(){
                self.success("categories", response);
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
                self.success("locations", response);
            });
        });
        locations_promise.fail(function(response){
            Ember.run(function(){
                self.failure(response);
            });
         });
    },
    success: function(array_type, response) {
        var context = this;
        $.each(response, function(index, val) {
            context.get(array_type).pushObject(val.name);
        });
    },

    failure: function(response) {
    },
});

SocialChef.AuthPanelComponent = Ember.Component.extend({
  tagName: '',
  classNames: [],
  providers: Ember.A([]),
  didInsertElement: function() {
      this.get('providers').pushObjects(["Twitter", "Facebook"]);
  },

  actions: {
      signIn: function(provider) {
          var promise = null;
          OAuth.initialize('CGeO08Q36jRyhGwGEcryHMOOzlw');
          if ( provider=="Twitter" ) {
              OAuth.popup('twitter', function(error, result) {
                // result: {
                //   "oauth_token": "TOKEN",
                //   "oauth_token_secret": "TOKEN"
                // }
                if (error !== null) {
                    var timestamp = new Date().getTime();
                    var oauth_consumer_key = 'hIjIvn6gDwTcGm6cE12GNFT7G';
                    var oauth_nonce = '';
                    var oauth_signature = '';
                    var oauth_signature_method = 'HMAC-SHA1';
                    var oauth_timestamp = String(timestamp);
                    var oauth_token = result.oauth_token;
                    var oauth_version = '1.0';
                    // START SIGNATURE
                    var signature='GET&';
                    signature +=
                        encodeURIComponent(
                            'https://api.twitter.com/1.1/account/verify_credentials.json');
                    signature += '&';
                    signature += encodeURIComponent(
                        'include_entities=true&');
                    signature += encodeURIComponent(
                        "oauth_consumer_key="+oauth_consumer_key+"&");
                    signature += encodeURIComponent(
                        "oauth_nonce="+oauth_nonce+"&");
                    signature += encodeURIComponent(
                        "oauth_signature_method="+oauth_signature_method+"&");
                    signature += encodeURIComponent(
                        "oauth_timestamp="+oauth_timestamp+"&");
                    signature += encodeURIComponent(
                        "oauth_token="+oauth_token+"&");
                    signature += encodeURIComponent(
                        "oauth_version="+oauth_version+"&");
                    Ember.$.ajax({
                        url: "/signature",
                        dataType: 'json',
                        async: false,
                        data: {
                            'signature': signature,
                            'secret': result.oauth_token_secret
                        },
                        success: function(response){
                            oauth_signature = response.signature;
                        }
                    });
                    // END SIGNATURE

                    var authHeader = "OAuth ";
                    authHeader += "oauth_consumer_key=";
                    authHeader += "oauth_nonce=";
                    authHeader += "oauth_signature=";
                    authHeader += "oauth_signature_method=";
                    authHeader += "oauth_timestamp=";
                    authHeader += "oauth_token=";
                    authHeader += "oauth_version=";
                    promise = Ember.$.ajax({
                        url: '',
                        type: 'GET',
                        headers: {
                            'Authorization': authHeader
                        },
                        data: 'include_entities=true'
                    });
                    promise.success(function(response) {
                        console.log("success");
                        console.log(response);
                    });
                    promise.failure(function(response) {
                        console.log("error");
                    });

                }
                //handle error with error
                //use result.access_token in your API request
                console.log(result);
              });
          } else if ( provider=="Facebook" ) {
              OAuth.popup('facebook', function(error, result) {
                // result: {
                //   "access_token": "TOKEN",
                //   "expires_in": TIME
                // }
                //handle error with error
                //use result.access_token in your API request
                console.log(result);
              });
          }
      }
  },

  twitterSuccess: function(response) {
      console.log(response);
  }
});
