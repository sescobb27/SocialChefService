Ember.Test.registerHelper("exists", function(app, selector, quantity) {
    if (quantity === undefined) { quantity = 1;}
    return wait()
        .find(selector)
        .then(function(el){
            equal(el.length, quantity, "Should be " + quantity + " of " + selector);
        });
});
// in order to see the app running inside the QUnit runner
SocialChef.rootElement = '#ember-testing';

// Common test setup
SocialChef.setupForTesting();
SocialChef.injectTestHelpers();

var server;

module("Ajax tests", {
    setup: function() {
        Ember.run(SocialChef, SocialChef.advanceReadiness);
        SocialChef.reset();
        server = sinon.fakeServer.create();
        server.autoRespond = true;
    },

    teardown: function() {
        SocialChef.reset();
        server.restore();
    }
});

var products = [{
        id: 1,
        name: 'Plato 1',
        price: 9900,
        category: 'Pasta',
        description: 'Descripcion del plato 1.',
        cheff:'Pablo',
        image: 'images/gourmet1.jpg'
    }, {
        id: 2,
        name: 'Plato 2',
        price: 249,
        category: 'Carne',
        description: 'Descripcion del plato 2.',
        cheff:'Pedro',
        image: 'images/gourmet2.jpg'
    }, {
        id: 3,
        name: 'Plato 3',
        price: 499,
        category: 'Postre',
        description: 'Descripcion del plato 3.',
        cheff:'Jacinto',
        image: 'images/gourmet3.jpg'
    }
];

test('I should by able to search products by name', function () {
    server.respondWith("get", "http://localhost:8080/products/findby");
    visit("/")
      .fillIn('input#products-search', "Plato 1")
      .click('#search-btn')
      .then(function () {
          equal(server.requests.length, 1, "");
          equal(server.requests[0].url, "http://localhost:8080/products/findby?search_value=Plato+1", "");
      });
});

test('I should by able to search products by category', function () {
  server.respondWith("get", "http://localhost:8080/products/findby");
  visit("/")
    .fillIn('input#products-search', "Carne")
    .click('#search-btn')
    .then(function () {
        equal(server.requests.length, 1, "");
        equal(server.requests[0].url, "http://localhost:8080/products/findby?search_value=Carne", "");
    });
});

test('I should by able to search products by chef', function () {
  server.respondWith("get", "http://localhost:8080/products/findby");
  visit("/")
    .fillIn('input#products-search', "Jacinto")
    .click('#search-btn')
    .then(function () {
        equal(server.requests.length, 1, "");
        equal(server.requests[0].url, "http://localhost:8080/products/findby?search_value=Jacinto", "");
    });
});

test('I should see an error when submit an empty login', function() {
    server.respondWith("post", "http://localhost:8080/login");
    visit('/login')
    .fillIn("input#user_name","")
    .fillIn("input#user_password","")
    .click('#login_btn')
    .then(function() {
        equal(server.requests.length, 1, "");
        equal(server.requests[0].url, "http://localhost:8080/chefs/login", "");
    });
});

module("Integration tests", {
    setup: function() {
        // before each test, ensure the application is ready to run.
        Ember.run(SocialChef, SocialChef.advanceReadiness);
        SocialChef.reset();
    },

    teardown: function() {
        // reset the application state between each test
        SocialChef.reset();
    }
});

test("I should see  the App Name", function() {
    visit("/").exists("#appname");
});

test('I should see a carousel in the index page',function() {
    visit("/").exists("#add-banner");
});

test('I should see the Copyright all the time', function() {
    visit('/').exists("#copyright");
    visit('/products').exists("#copyright");
});

test('I Should see Product Search Bar', function() {
    visit('/').exists("#products-search");
    visit('/').exists("#productSearchForm");
});