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
  oauth_consumer_key: 'hIjIvn6gDwTcGm6cE12GNFT7G',
  didInsertElement: function() {
      this.get('providers').pushObjects(["Twitter", "Facebook"]);
  },

  actions: {
      signIn: function(provider) {
          var promise = null;
          // OAuth.initialize('CGeO08Q36jRyhGwGEcryHMOOzlw');
          if ( provider=="Twitter" ) {
                var result = this.getAccessToken();
                // result: {
                //   "oauth_token": "TOKEN",
                //   "oauth_token_secret": "TOKEN"
                // }
                if (error !== null) {
                    var timestamp = new Date().getTime();
                    var url = 'https://api.twitter.com/1.1/account/verify_credentials.json';
                    var oauth_nonce = this.generateNonce();
                    var oauth_signature = '';
                    var oauth_signature_method = 'HMAC-SHA1';
                    var oauth_timestamp = String(timestamp);
                    var oauth_token = result.oauth_token;
                    var oauth_version = '1.0';
                    // START SIGNATURE
                    var signature='GET&';
                    signature += encodeURIComponent(url);
                    signature += '&';
                    signature += encodeURIComponent(
                        'include_entities=true&');
                    signature += encodeURIComponent(
                        "oauth_consumer_key="+this.get('oauth_consumer_key')+"&");
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
                        type: 'POST',
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
                    authHeader += "oauth_consumer_key="+
                        this.get('oauth_consumer_key');
                    authHeader += "oauth_nonce="+oauth_nonce;
                    authHeader += "oauth_signature="+oauth_signature;
                    authHeader += "oauth_signature_method="+oauth_signature_method;
                    authHeader += "oauth_timestamp="+oauth_timestamp;
                    authHeader += "oauth_token="+oauth_token;
                    authHeader += "oauth_version="+oauth_version;
                    promise = Ember.$.ajax({
                        url: url,
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
                        console.log(response);
                    });

                }
          } else if ( provider=="Facebook" ) {

          }
      }
  },

  twitterSuccess: function(response) {
      console.log(response);
  },
  generateNonce: function() {
      var nonce = '';
      var promise = Ember.$.ajax({
          url: 'js/libs/hmac-sha1.js',
          async: false,
          dataType: "script",
          success: function(){
              nonce = CryptoJS.lib.WordArray.random(32);
          }
      });
      return nonce.toString();
  },
  getAccessToken: function() {
      var timestamp = new Date().getTime();
      var url = 'https://api.twitter.com/oauth/request_token';
      var oauth_nonce = this.generateNonce();
      var oauth_signature = '';
      var oauth_signature_method = 'HMAC-SHA1';
      var oauth_timestamp = String(timestamp);
      var oauth_version = '1.0';
      var authHeader = "OAuth ";

      // START SIGNATURE
      var signature='POST&';
      signature += encodeURIComponent(url);

      signature += '&';

      signature += encodeURIComponent(
          "oauth_nonce="+oauth_nonce+"&");

      signature += encodeURIComponent(
          "oauth_signature_method="+oauth_signature_method+"&");

      signature += encodeURIComponent(
          "oauth_timestamp="+oauth_timestamp+"&");

      signature += encodeURIComponent(
          "oauth_consumer_key="+this.get('oauth_consumer_key')+"&");

      signature += encodeURIComponent(
          "oauth_version="+oauth_version+"&");

      Ember.$.ajax({
          type: 'POST',
          url: "/signature",
          dataType: 'json',
          async: false,
          data: {
              'signature': signature
          },
          success: function(response){
              oauth_signature = response.signature;
          }
      });
      // END SIGNATURE

      authHeader += "oauth_nonce="+oauth_nonce;
      authHeader += "oauth_signature_method="+oauth_signature_method;
      authHeader += "oauth_timestamp="+oauth_timestamp;
      authHeader += "oauth_consumer_key="+
          this.get('oauth_consumer_key');
      authHeader += "oauth_signature="+oauth_signature;
      authHeader += "oauth_version="+oauth_version;

      var promise = Ember.$.ajax({
          type: 'POST',
          url: 'https://api.twitter.com/oauth/request_token',
          headers: {
              Authorization: authHeader
          }
      });
      promise.success(function(response) {
          console.log(response);
      });
      promise.failure(function(response) {
          console.log(response);
      });
  }
});
