Ember.TEMPLATES["components/add-banner"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  


  data.buffer.push("<div class=\"col-md-12\" id=\"add-banner\">\n  <div class=\"row carousel-holder\">\n    <div class=\"col-md-12\">\n      <div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">\n        <ol class=\"carousel-indicators\">\n          <li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>\n          <li data-target=\"#carousel-example-generic\" data-slide-to=\"1\"></li>\n          <li data-target=\"#carousel-example-generic\" data-slide-to=\"2\"></li>\n        </ol>\n        <div class=\"carousel-inner\">\n          <div class=\"item active\">\n            <img class=\"slide-image\" src=\"images/gourmet1.jpg\">\n          </div>\n          <div class=\"item\">\n            <img class=\"slide-image\" src=\"images/gourmet2.jpg\">\n          </div>\n          <div class=\"item\">\n            <img class=\"slide-image\" src=\"images/gourmet3.jpg\">\n          </div>\n        </div>\n        <a class=\"left carousel-control\" href=\"#carousel-example-generic\" data-slide=\"prev\">\n          <span class=\"glyphicon glyphicon-chevron-left\"></span>\n        </a>\n        <a class=\"right carousel-control\" href=\"#carousel-example-generic\" data-slide=\"next\">\n          <span class=\"glyphicon glyphicon-chevron-right\"></span>\n        </a>\n      </div>\n    </div>\n  </div>\n</div>");
  
});

Ember.TEMPLATES["components/left-panel"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n                          <li class=\"list-group-item\">\n                              ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "search.results", "", options) : helperMissing.call(depth0, "link-to", "search.results", "", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n                          </li>\n                      ");
  return buffer;
  }
function program2(depth0,data) {
  
  var stack1;
  stack1 = helpers._triageMustache.call(depth0, "", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  }

  data.buffer.push("<p class=\"lead\">Buscar Por</p>\n  <div class=\"panel-group\" id=\"accordion\">\n      <div class=\"panel panel-default\">\n          <div class=\"panel-heading\">\n              <h4 class=\"panel-title\">\n                  <a href=\"#collapseOne\" data-toggle=\"collapse\" data-parent=\"#accordion\"> Populares </a>\n              </h4>\n          </div>\n          <div id=\"collapseOne\" class=\"panel-collapse collapse in\">\n              <div class=\"panel-body\">\n                  Populares ...\n              </div>\n          </div>\n      </div>\n      <div class=\"panel panel-default\">\n          <div class=\"panel-heading\">\n              <h4 class=\"panel-title\">\n                  <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseTwo\">\n                      Nuevos\n                   </a>\n              </h4>\n          </div>\n          <div id=\"collapseTwo\" class=\"panel-collapse collapse\">\n              <div class=\"panel-body\">\n                  Nuevos ...\n              </div>\n          </div>\n      </div>\n      <div class=\"panel panel-default\">\n          <div class=\"panel-heading\">\n              <h4 class=\"panel-title\">\n                  <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\">\n                      Ubicaci&oacute;n\n                   </a>\n              </h4>\n          </div>\n          <div id=\"collapseThree\" class=\"panel-collapse collapse\">\n              <div class=\"panel-body\">\n                  <ul class=\"list-group\">\n                      ");
  stack1 = helpers.each.call(depth0, "locations", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n                  </ul>\n              </div>\n          </div>\n      </div>\n      <div class=\"panel panel-default\">\n          <div class=\"panel-heading\">\n              <h4 class=\"panel-title\">\n                  <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseFour\">\n                      Categorias\n                   </a>\n              </h4>\n          </div>\n          <div id=\"collapseFour\" class=\"panel-collapse collapse\">\n              <div class=\"panel-body\">\n                  <ul class=\"list-group\">\n                      ");
  stack1 = helpers.each.call(depth0, "categories", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n                  </ul>\n              </div>\n          </div>\n      </div>\n      <div class=\"panel panel-default\">\n          <div class=\"panel-heading\">\n              <h4 class=\"panel-title\">\n                  <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseFive\">\n                      Precios\n                   </a>\n              </h4>\n          </div>\n          <div id=\"collapseFive\" class=\"panel-collapse collapse\">\n              <div class=\"panel-body\">\n                  Precios ...\n              </div>\n          </div>\n      </div>\n  </div>");
  return buffer;
  
});

Ember.TEMPLATES["components/products-search"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', helper, options, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;


  data.buffer.push("<div class=\"container\">\n    <div class=\"row\">\n        <form ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "search", {hash:{
    'on': ("submit")
  },hashTypes:{'on': "STRING"},hashContexts:{'on': depth0},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                    class=\"form-horizontal\"\n                    method=\"get\"\n                    accept-charset=\"utf-8\"\n                    id=\"productSearchForm\"\n                    role=\"form\">\n\n            <div class=\"form-group input-group\">\n                ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("search"),
    'id': ("products-search"),
    'placeholder': ("Buscador de Comidas"),
    'value': ("search_query"),
    'class': ("form-control")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n                <span class=\"input-group-btn\">\n                    <button class=\"submit btn btn-primary\"\n                        ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "search", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                        id=\"search-btn\">Buscar</button>\n                </span>\n            </div>\n        </form>\n    </div>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["application"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, helper, options, self=this, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;

function program1(depth0,data) {
  
  
  data.buffer.push("SocialChef");
  }

function program3(depth0,data) {
  
  
  data.buffer.push(" Products ");
  }

function program5(depth0,data) {
  
  
  data.buffer.push(" About ");
  }

function program7(depth0,data) {
  
  
  data.buffer.push(" Login ");
  }

function program9(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n              <li> ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(10, program10, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "user", "username", options) : helperMissing.call(depth0, "link-to", "user", "username", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" </li>\n          ");
  return buffer;
  }
function program10(depth0,data) {
  
  var buffer = '', stack1;
  data.buffer.push(" ");
  stack1 = helpers._triageMustache.call(depth0, "username", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" ");
  return buffer;
  }

function program12(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n              <li> ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(13, program13, data),contexts:[depth0],types:["STRING"],data:data},helper ? helper.call(depth0, "register", options) : helperMissing.call(depth0, "link-to", "register", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" </li>\n          ");
  return buffer;
  }
function program13(depth0,data) {
  
  
  data.buffer.push(" Register ");
  }

  data.buffer.push("<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n  <div class=\"container\">\n    <div class=\"navbar-header\">\n      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\n        <span class=\"sr-only\">Toggle navigation</span>\n        <span class=\"icon-bar\"></span>\n        <span class=\"icon-bar\"></span>\n        <span class=\"icon-bar\"></span>\n      </button>\n      ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{
    'class': ("navbar-brand"),
    'id': ("appname")
  },hashTypes:{'class': "STRING",'id': "STRING"},hashContexts:{'class': depth0,'id': depth0},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["STRING"],data:data},helper ? helper.call(depth0, "index", options) : helperMissing.call(depth0, "link-to", "index", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    </div>\n\n    <nav class=\"navbar-inner collapse navbar-collapse navbar-ex1-collapse\">\n      <ul class=\"nav navbar-nav navbar-right\">\n        <li> ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(3, program3, data),contexts:[depth0],types:["STRING"],data:data},helper ? helper.call(depth0, "products", options) : helperMissing.call(depth0, "link-to", "products", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" </li>\n          <li> ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(5, program5, data),contexts:[depth0],types:["STRING"],data:data},helper ? helper.call(depth0, "about", options) : helperMissing.call(depth0, "link-to", "about", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" </li>\n          <li> ");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(7, program7, data),contexts:[depth0],types:["STRING"],data:data},helper ? helper.call(depth0, "login", options) : helperMissing.call(depth0, "link-to", "login", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push(" </li>\n          ");
  stack1 = helpers['if'].call(depth0, "username", {hash:{},hashTypes:{},hashContexts:{},inverse:self.program(12, program12, data),fn:self.program(9, program9, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n      </ul>\n    </nav>\n  </nav>\n</nav>\n<div class=\"container\">\n    <div><h1>SocialChef</h1></div>\n    <div class=\"row\">\n      ");
  data.buffer.push(escapeExpression((helper = helpers['products-search'] || (depth0 && depth0['products-search']),options={hash:{
    'search': ("search")
  },hashTypes:{'search': "STRING"},hashContexts:{'search': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "products-search", options))));
  data.buffer.push("\n      <div class=\"col-md-3\">\n          ");
  stack1 = helpers._triageMustache.call(depth0, "left-panel", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n      </div>\n      <div class=\"container\">\n          <div class=\"col-md-9\">\n            ");
  stack1 = helpers._triageMustache.call(depth0, "outlet", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n          </div>\n      </div>\n    </div>\n</div>\n<div class=\"container\">\n  <hr>\n  <footer>\n      <div class=\"row\">\n          <div class=\"col-lg-12\">\n              <p id=\"copyright\">Copyright &copy; SocialChef 2014</p>\n          </div>\n        </div>\n    </footer>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["chefs"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["index"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var stack1;


  stack1 = helpers._triageMustache.call(depth0, "add-banner", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  
});

Ember.TEMPLATES["login"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, helper, options, self=this, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  
  data.buffer.push("\n        <div id=\"error_msg\" class=\"alert alert-danger\">\n            Invalid username or password.\n        </div>\n    ");
  }

function program3(depth0,data) {
  
  
  data.buffer.push("\n        <div id=\"info_msg\" class=\"alert alert-info\">\n            The request seems to be taking more time than usual, please wait.\n        </div>\n    ");
  }

  data.buffer.push("<div class=\"row\">\n    ");
  stack1 = helpers['if'].call(depth0, "loginFailed", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    ");
  stack1 = helpers['if'].call(depth0, "isSlowConnection", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(3, program3, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    <form ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "login", {hash:{
    'on': ("submit")
  },hashTypes:{'on': "STRING"},hashContexts:{'on': depth0},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                class=\"form-horizontal form-group\"\n                method=\"post\"\n                accept-charset=\"utf-8\"\n                id=\"loginForm\"\n                role=\"form\">\n        <div class=\"col-xs-6\">\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("text"),
    'id': ("user_name"),
    'placeholder': ("Usuario"),
    'value': ("username"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("password"),
    'id': ("user_password"),
    'placeholder': ("Contraseña"),
    'value': ("password"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            <button id=\"login_btn\"\n                            class=\"submit btn btn-primary\"\n                            ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'disabled': ("isProcessing")
  },hashTypes:{'disabled': "STRING"},hashContexts:{'disabled': depth0},contexts:[],types:[],data:data})));
  data.buffer.push("\n                            type=\"submit\">Aceptar</button>\n        </div>\n    </form>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["product"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, helper, options, escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var stack1;
  stack1 = helpers._triageMustache.call(depth0, "product.name", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  }

  data.buffer.push("<div class=\"col-sm-4 col-lg-4 col-md-4\">\n    <div class=\"thumbnail\">\n        <img  ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'src': ("product.image")
  },hashTypes:{'src': "STRING"},hashContexts:{'src': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">\n        <div class=\"caption\">\n            <h4 class=\"pull-right\">$ ");
  stack1 = helpers._triageMustache.call(depth0, "product.price", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n            <h4>");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "product", "product.name", options) : helperMissing.call(depth0, "link-to", "product", "product.name", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n            <button class='btn btn-success' ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "buy", "product", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0,depth0],types:["STRING","ID"],data:data})));
  data.buffer.push(">Comprar</button>\n            <p>");
  stack1 = helpers._triageMustache.call(depth0, "product.description", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n        </div>\n    </div>\n    <div class=\"ratings\">\n        <p class=\"pull-right\">");
  stack1 = helpers._triageMustache.call(depth0, "product.rate", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n        <p>\n            <span class=\"glyphicon glyphicon-star\"></span>\n            <span class=\"glyphicon glyphicon-star\"></span>\n            <span class=\"glyphicon glyphicon-star\"></span>\n            <span class=\"glyphicon glyphicon-star\"></span>\n            <span class=\"glyphicon glyphicon-star-empty\"></span>\n        </p>\n    </div>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["products"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n                <div class=\"col-sm-4 col-lg-4 col-md-4\">\n                    <div class=\"thumbnail\">\n                        <img  ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'src': ("product.image")
  },hashTypes:{'src': "STRING"},hashContexts:{'src': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">\n                        <div class=\"caption\">\n                            <h4 class=\"pull-right\">$ ");
  stack1 = helpers._triageMustache.call(depth0, "product.price", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <h4>");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{
    'class': ("btn btn-success"),
    'tagName': ("button")
  },hashTypes:{'class': "STRING",'tagName': "STRING"},hashContexts:{'class': depth0,'tagName': depth0},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "product", "product.name", options) : helperMissing.call(depth0, "link-to", "product", "product.name", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <button class='btn btn-success' ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "buy", "product", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0,depth0],types:["STRING","ID"],data:data})));
  data.buffer.push(">Comprar</button>\n                            <p>");
  stack1 = helpers._triageMustache.call(depth0, "product.description", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n                        </div>\n                    </div>\n                    <div class=\"ratings\">\n                        <p class=\"pull-right\">");
  stack1 = helpers._triageMustache.call(depth0, "product.rate", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n                        <p>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star-empty\"></span>\n                        </p>\n                    </div>\n                </div>\n        ");
  return buffer;
  }
function program2(depth0,data) {
  
  var stack1;
  stack1 = helpers._triageMustache.call(depth0, "product.name", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  }

  data.buffer.push("<div class=\"row\">\n    <div class=\"col-md-12\">\n        <h1>Despliegue de todos los productos</h1>\n        ");
  stack1 = helpers.each.call(depth0, "product", "in", "model", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0,depth0,depth0],types:["ID","ID","ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    </div>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["purchase"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, escapeExpression=this.escapeExpression;


  data.buffer.push("<h1>Factura</h1>\n<form ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "purchase", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push(" method=\"post\" accept-charset=\"utf-8\">\n   <h3>");
  stack1 = helpers._triageMustache.call(depth0, "product.name", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h3>\n   <h3>");
  stack1 = helpers._triageMustache.call(depth0, "product.price", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h3>\n   <button ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "purchase", {hash:{
    'on': ("submit")
  },hashTypes:{'on': "STRING"},hashContexts:{'on': depth0},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push(" type='submit'>Aceptar</button>\n</form>");
  return buffer;
  
});

Ember.TEMPLATES["register"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, helper, options, escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  
  data.buffer.push("\n            <div id=\"error_msg\" class=\"alert alert-danger\">\n                la contraseña y la confirmación de la contraseña no concuerdan\n            </div>\n        ");
  }

  data.buffer.push("<div class=\"row\">\n    <form ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "validate", {hash:{
    'on': ("submit")
  },hashTypes:{'on': "STRING"},hashContexts:{'on': depth0},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                class=\"form-horizontal form-group\"\n                method=\"post\"\n                accept-charset=\"utf-8\"\n                id=\"registerForm\"\n                role=\"form\">\n        ");
  stack1 = helpers['if'].call(depth0, "invalidPass", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n        <div class=\"col-xs-6\">\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("text"),
    'id': ("user_name"),
    'placeholder': ("Nombre"),
    'value': ("name"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("text"),
    'id': ("user_last_name"),
    'placeholder': ("Apellido"),
    'value': ("last_name"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("text"),
    'id': ("username"),
    'placeholder': ("Usuario"),
    'value': ("username"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("email"),
    'id': ("email"),
    'placeholder': ("Correo"),
    'value': ("email"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("password"),
    'id': ("user_password"),
    'placeholder': ("Contraseña"),
    'value': ("password"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("password"),
    'id': ("confirm_password"),
    'placeholder': ("Confirmacion Contraseña"),
    'value': ("confirm_password"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            <button class=\"submit btn btn-primary\"\n                ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "validate", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'disabled': ("isProcessing")
  },hashTypes:{'disabled': "STRING"},hashContexts:{'disabled': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">Aceptar</button>\n        </div>\n    </form>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["user"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', escapeExpression=this.escapeExpression;


  data.buffer.push("<div class=\"row\">\n    <div class=\"col-md-12\">\n      <button class=\"btn btn-primary\"\n          ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "addProduct", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push(">Agregar Producto</button>\n      <button class=\"btn btn-primary\"\n          ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "showProducts", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push(">Ver Productos</button>\n    </div>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["search/results"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n                <div class=\"col-sm-4 col-lg-4 col-md-4\">\n                    <div class=\"thumbnail\">\n                        <img  ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'src': ("product.image")
  },hashTypes:{'src': "STRING"},hashContexts:{'src': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">\n                        <div class=\"caption\">\n                            <h4 class=\"pull-right\">$ ");
  stack1 = helpers._triageMustache.call(depth0, "product.price", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <h4>");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{
    'class': ("btn btn-success"),
    'tagName': ("button")
  },hashTypes:{'class': "STRING",'tagName': "STRING"},hashContexts:{'class': depth0,'tagName': depth0},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "product", "product.name", options) : helperMissing.call(depth0, "link-to", "product", "product.name", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <button class='btn btn-success' ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "buy", "product", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0,depth0],types:["STRING","ID"],data:data})));
  data.buffer.push(">Comprar</button>\n                            <p>");
  stack1 = helpers._triageMustache.call(depth0, "product.description", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n                        </div>\n                    </div>\n                    <div class=\"ratings\">\n                        <p class=\"pull-right\">");
  stack1 = helpers._triageMustache.call(depth0, "product.rate", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n                        <p>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star-empty\"></span>\n                        </p>\n                    </div>\n                </div>\n        ");
  return buffer;
  }
function program2(depth0,data) {
  
  var stack1;
  stack1 = helpers._triageMustache.call(depth0, "product.name", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  }

  data.buffer.push("<div class=\"row\">\n    <div class=\"col-md-12\">\n        <h1>");
  stack1 = helpers._triageMustache.call(depth0, "title", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h1>\n        ");
  stack1 = helpers.each.call(depth0, "product", "in", "controller", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0,depth0,depth0],types:["ID","ID","ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    </div>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["user_products/add"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, helper, options, self=this, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var buffer = '', stack1;
  data.buffer.push("\n            ");
  stack1 = helpers.each.call(depth0, "error", "in", "errors", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0,depth0,depth0],types:["ID","ID","ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n        ");
  return buffer;
  }
function program2(depth0,data) {
  
  var buffer = '', stack1;
  data.buffer.push("\n                <div id=\"error_msg\" class=\"alert alert-danger\">\n                    ");
  stack1 = helpers._triageMustache.call(depth0, "error", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n                </div>\n            ");
  return buffer;
  }

  data.buffer.push("<div class=\"row\">\n    <form ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "validate", {hash:{
    'on': ("submit")
  },hashTypes:{'on': "STRING"},hashContexts:{'on': depth0},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                class=\"form-horizontal form-group\"\n                method=\"post\"\n                accept-charset=\"utf-8\"\n                id=\"addUseProductForm\"\n                role=\"form\">\n        ");
  stack1 = helpers['if'].call(depth0, "errors", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n        <div class=\"col-xs-6\">\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("text"),
    'id': ("product_name"),
    'placeholder': ("Nombre del Plato"),
    'value': ("product_name"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{
    'type': ("number"),
    'id': ("product_price"),
    'placeholder': ("Precio ($Pesos) ejemplo 7250"),
    'value': ("price"),
    'class': ("form-control col-xs-3")
  },hashTypes:{'type': "STRING",'id': "STRING",'placeholder': "STRING",'value': "ID",'class': "STRING"},hashContexts:{'type': depth0,'id': depth0,'placeholder': depth0,'value': depth0,'class': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, "input", options))));
  data.buffer.push("\n            ");
  data.buffer.push(escapeExpression(helpers.view.call(depth0, "SocialChef.UploadFile", {hash:{
    'name': ("product_image"),
    'file': ("product_image")
  },hashTypes:{'name': "STRING",'file': "ID"},hashContexts:{'name': depth0,'file': depth0},contexts:[depth0],types:["ID"],data:data})));
  data.buffer.push("\n            <div id=\"logo\" class=\"thumbnail\">\n                <img id=\"image_thumbnail\" alt=\"Imagen Plato\">\n            </div>\n            <button class=\"submit btn btn-primary\"\n                ");
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "validate", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["STRING"],data:data})));
  data.buffer.push("\n                ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'disabled': ("isProcessing")
  },hashTypes:{'disabled': "STRING"},hashContexts:{'disabled': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">Aceptar</button>\n        </div>\n    </form>\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["user_products/delete"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["user_products/edit"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["user_products/index"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  var buffer = '', stack1, helper, options;
  data.buffer.push("\n                <div class=\"col-sm-4 col-lg-4 col-md-4\">\n                    <div class=\"thumbnail\">\n                        <img  ");
  data.buffer.push(escapeExpression(helpers['bind-attr'].call(depth0, {hash:{
    'src': ("product.image")
  },hashTypes:{'src': "STRING"},hashContexts:{'src': depth0},contexts:[],types:[],data:data})));
  data.buffer.push(">\n                        <div class=\"caption\">\n                            <h4 class=\"pull-right\">$ ");
  stack1 = helpers._triageMustache.call(depth0, "product.price", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <h4>");
  stack1 = (helper = helpers['link-to'] || (depth0 && depth0['link-to']),options={hash:{
    'class': ("btn btn-success"),
    'tagName': ("button")
  },hashTypes:{'class': "STRING",'tagName': "STRING"},hashContexts:{'class': depth0,'tagName': depth0},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0,depth0],types:["STRING","ID"],data:data},helper ? helper.call(depth0, "product", "product.name", options) : helperMissing.call(depth0, "link-to", "product", "product.name", options));
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</h4>\n                            <p>");
  stack1 = helpers._triageMustache.call(depth0, "product.description", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("</p>\n                        </div>\n                    </div>\n                    <div class=\"ratings\">\n                        <p class=\"pull-right\">18 reviews</p>\n                        <p>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star\"></span>\n                            <span class=\"glyphicon glyphicon-star-empty\"></span>\n                        </p>\n                    </div>\n                </div>\n        ");
  return buffer;
  }
function program2(depth0,data) {
  
  var stack1;
  stack1 = helpers._triageMustache.call(depth0, "product.name", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:["ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  }

  data.buffer.push("<div class=\"row\">\n    <div class=\"col-md-12\">\n        ");
  stack1 = helpers.each.call(depth0, "product", "in", "model", {hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0,depth0,depth0],types:["ID","ID","ID"],data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\n    </div>\n</div>");
  return buffer;
  
});